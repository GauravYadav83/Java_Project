package com.file_object.model;

import java.io.Serializable;

public class File_object implements Serializable{
	private int fd;
	private String filename;
	private int file_size;			//Setting to 1 MB by default -- Support for future features of file size by update 
	private String path;			//Setting to '/' + filename  -- Support for future features of multi dir structure
	private int access_permissions;	//Setting to '777' by default -- Support for future features of access control
	private String data;			//Setting to a default message -- Support for further features of data manipulation
	
	public File_object() {
		super();
	}

	public int getFd() {
		return fd;
	}

	public void setFd(int fd) {
		this.fd = fd;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getFile_size() {
		return file_size;
	}

	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getAccess_permissions() {
		return access_permissions;
	}

	public void setAccess_permissions(int access_permissions) {
		this.access_permissions = access_permissions;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "File_object data for fd=" + fd + "\n filename=" + filename + "\n file_size=" + file_size + "\n file_path=" + path
				+ "\n access_permissions=" + access_permissions + "\n data : \n" + data + "\n";
	}
	
}
