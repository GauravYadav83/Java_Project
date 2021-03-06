package com.file_object.service.impl;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.file_object.DAO.File_objectDAO;
import com.file_object.dao.impl.File_objectDAOImpl;
import com.file_object.exception.file_objectException;
import com.file_object.model.File_object;
import com.file_object.service.File_object_Service;

public class File_object_ServiceImpl implements File_object_Service{

	private File_objectDAO dao = new File_objectDAOImpl();
	@Override
	public File_object createFile_object(File_object file) throws file_objectException {
		// TODO Auto-generated method stub
		if(!isValidName(file.getFilename())){
			throw new file_objectException("Entered name "+file.getFilename()+" is invalid");
		}
		if(dao.findFile_object(file.getFilename())!=null) {
			throw new file_objectException("Entered file "+file.getFilename()+" already exists");
		}
		File_object fo = dao.createFile_object(file);
		try(FileOutputStream fos = new FileOutputStream(file.getFilename());
            BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            byte[] bytes = file.getData().getBytes();
            bos.write(bytes);
            bos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return fo;
	}

	@Override
	public void deleteFile_object(String filename) throws file_objectException {
		// TODO Auto-generated method stub
		if(!isValidName(filename)){
			throw new file_objectException("Entered name "+filename+" is invalid");
		}
		dao.deleteFile_object(filename);
		Path path  = Paths.get("/home/gauravyadavprol/eclipse-workspace/Java_Project/"+filename); 

	    try {
	        Files.deleteIfExists(path); 
	    } 
	    catch (IOException e) {  
	        e.printStackTrace(); 
	    } 
		
	}

	@Override
	public File_object findFile_object(String filename) throws file_objectException {
		// TODO Auto-generated method stub
		if(!isValidName(filename)){
			throw new file_objectException("Entered name "+filename+" is invalid");
		}
		File_object fo = dao.findFile_object(filename);
		if(fo==null) {
			throw new file_objectException("Entered name "+filename+" doesn't exists");			
		}
		return fo;
	}

	@Override
	public List<File_object> getAllFile_objects() throws file_objectException {
		// TODO Auto-generated method stub
		
		return dao.getAllFile_objects();
	}
	
	private boolean isValidName(String name) {
		boolean b=false;
		if(name.trim().matches("[a-zA-Z_]{3,15}")) {
			b=true;
		}
		return b;
	}

	@Override
	public void finish() throws file_objectException {
		dao.finalized();
		
	}
	

}
