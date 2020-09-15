package com.file_object.model;

import java.util.Comparator;

public class File_objectComparator implements Comparator<File_object>{

	@Override
	public int compare(File_object o1, File_object o2) {
		
		return o1.getFilename().compareTo(o2.getFilename());
	}

}

