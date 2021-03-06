package com.file_object.DAO;

import java.util.List;

import com.file_object.exception.file_objectException;
import com.file_object.model.File_object;

public interface File_objectDAO {
	public File_object createFile_object(File_object file) throws file_objectException;
	public void deleteFile_object(String filename) throws file_objectException;
	public File_object findFile_object(String filename) throws file_objectException;
	
	public List<File_object> getAllFile_objects() throws file_objectException;
	public void finalized() throws file_objectException;
}
