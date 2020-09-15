package com.file_object.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.file_object.DAO.File_objectDAO;
import com.file_object.exception.file_objectException;
import com.file_object.model.File_object;
import com.file_object.model.File_objectComparator;


public class File_objectDAOImpl implements File_objectDAO{

	private static Map<String, File_object> file_object_Map = null;
	private static int file_descriptor;
		
	public File_objectDAOImpl() {
		super();
		init_FileMap();
	}

	@Override
	public File_object createFile_object(File_object file) throws file_objectException {
		file.setFd(file_descriptor++);
		file.setFile_size(1);
		file.setPath('/'+file.getFilename());
		file.setAccess_permissions(777);
		file.setData("This is a file created using File Object implementation \n" + "The file name is " + file.getFilename());
		file_object_Map.put(file.getFilename(), file);
		return file;
	}

	@Override
	public void deleteFile_object(String filename) throws file_objectException {
		if (file_object_Map.containsKey(filename)) {
			file_object_Map.remove(filename);
		} else {
			throw new file_objectException("Entered filename " + filename + " doesnt exist");
		}
		
	}

	@Override
	public File_object findFile_object(String filename) throws file_objectException {
		if (file_object_Map.containsKey(filename)) {
			return file_object_Map.get(filename);
		} else {
			throw new file_objectException("Entered filename " + filename + " doesnt exist");
		}
	}

	@Override
	public List<File_object> getAllFile_objects() throws file_objectException {
		List<File_object> sorted_list = file_object_Map.values().stream().collect(Collectors.toList());
		Collections.sort(sorted_list, new File_objectComparator());
				return sorted_list;
	}
	
	private void init_FileMap() {
		
		if(file_object_Map==null) {
			file_object_Map = new HashMap<>();
			try(FileInputStream fis=new FileInputStream("fileMetadata.txt");
					ObjectInputStream ois=new ObjectInputStream(fis);
					){
				while(ois.available()>0) {
					File_object fo =(File_object) ois.readObject();	
					file_object_Map.put(fo.getFilename(), fo);
				}
				
			} catch (IOException | ClassNotFoundException e) {
				System.out.println(e);
			}	
		}
		else
		{
			file_object_Map = new HashMap<>();
		}
	}
	
	public void finalized() {
		
		try(FileOutputStream fos=new FileOutputStream("fileMetadata.txt", false);
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				){
			for(File_object fo:file_object_Map.values()) {
				oos.writeObject(fo);
			}
			
			System.out.println("Data dumped to file Successfullyyyyyyyy");
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
			
		
	}
	
	

}
