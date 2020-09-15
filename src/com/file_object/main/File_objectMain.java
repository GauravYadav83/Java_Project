package com.file_object.main;

import java.util.List;
import java.util.Scanner;

import com.file_object.exception.file_objectException;
import com.file_object.model.File_object;
import com.file_object.service.File_object_Service;
import com.file_object.service.impl.File_object_ServiceImpl;

public class File_objectMain {

	public static void main(String[] args) {
		System.out.println("Welcome to Custom File Management System V1.0");
		System.out.println("Author : Gaurav Yadav");
		int ch = 0;
		Scanner scanner = new Scanner(System.in);
		File_object_Service service = new File_object_ServiceImpl();
		do {
			System.out.println("\nMain Menu");
			System.out.println("===================");
			System.out.println("1)Create a File");
			System.out.println("2)Delete a File");
			System.out.println("3)Search File By Name");
			System.out.println("4)List All SuperHeros");
			System.out.println("5)EXITTTTTT");
			System.out.println("Please Enter your appropriate choice(1-5)");
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {

			}
			switch (ch) {
			case 1:
				System.out.println("Enter File Details Below.....");
				File_object fo =new File_object();
				System.out.println("Enter File Name");
				fo.setFilename(scanner.nextLine());
				try {
					fo = service.createFile_object(fo);
					System.out.println("File created with details "+fo);
				} catch (file_objectException e1) {
					System.out.println(e1.getMessage());
				}
				break;
				
			case 2:
				System.out.println("Enter File Details Below.....");
				System.out.println("Enter File Name");
				String filename = scanner.nextLine();
				try {
					service.deleteFile_object(filename);
					System.out.println("File deleted successfully.");
				} catch (file_objectException e1) {
					System.out.println(e1.getMessage());
				}
				break;
				
			case 3:
				System.out.println("Enter File Details Below.....");
				System.out.println("Enter File Name");
				String filenamefind = scanner.nextLine();
				try {
					File_object fo_found = service.findFile_object(filenamefind);
					System.out.println("File found with details ....." + fo_found);
				} catch (file_objectException e1) {
					System.out.println(e1.getMessage());
				}
				break;
				
		
			case 4:
				try {
					List<File_object> File_objectList=service.getAllFile_objects();
					if(File_objectList!=null && File_objectList.size()>0) {
						System.out.println("We found "+File_objectList.size()+" Files... Here are the details");
						for(File_object s:File_objectList) {
							System.out.println(s);
						}
					}
				} catch (file_objectException e) {
					System.out.println(e.getMessage());
				}

				break;
				
			default:
				System.out.println("Entered choice is invalid it should be numbers between 1-5 only");
				break;
			
			case 5:
				try {
					service.finish();
				} catch (file_objectException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} while (ch != 5);
	}

}
