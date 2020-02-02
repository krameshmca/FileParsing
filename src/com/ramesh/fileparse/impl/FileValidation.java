package com.ramesh.fileparse.impl;

import java.io.File;

import com.ramesh.fileparse.constants.Constants;

public class FileValidation {

	boolean validationStatus = false;

	public boolean validateExtn(String filePath) {

		boolean retVal = false;
		determineFileExtn(filePath);
		if (Constants.fileextn.equalsIgnoreCase("pdf") || Constants.fileextn.equalsIgnoreCase("doc")
				|| Constants.fileextn.equalsIgnoreCase("docx") || Constants.fileextn.equalsIgnoreCase("txt")) {
			retVal = true;
		}

		return retVal;
	}

	public boolean validateFile(String filePath) {
		validationStatus = chkFileExists(filePath);
		return validationStatus;
	}

	public void getFileInformation(String filePath) {
		int i = filePath.lastIndexOf('\\');
		if (i > 0) {
			Constants.fileName = filePath.substring(i + 1);
		}
		System.out.println("File name: " + Constants.fileName);
		System.out.println("File format: " + Constants.fileextn);
		File file = new File(filePath);
		Constants.fileSize = file.length() + " bytes";
		System.out.println("File Size: " + Constants.fileSize);

	}

	public void determineFileExtn(String filePath) {

		int i = filePath.lastIndexOf('.');
		if (i > 0) {
			Constants.fileextn = filePath.substring(i + 1);
		}

	}

	public boolean chkFileExists(String filePath) {
		boolean ret = false;
		File tempFile = new File(filePath);
		boolean exists = tempFile.exists();
		if (exists) {
			ret = true;
		}
		return ret;
	}

}
