package com.ramesh.fileparse.impl;

import java.io.IOException;

import com.ramesh.fileparse.constants.Constants;

public class FileParseImpl {

	public String parseFile(String filePath, String searchString) throws IOException {

		boolean validateFile = false;
		boolean determineExtn = false;
		FileValidation fv = new FileValidation();
		validateFile = fv.validateFile(filePath);
		if (validateFile) {
			determineExtn = fv.validateExtn(filePath);
		} else {
			System.out.println("File validation failed & unable to determine file extension");
		}
		if (validateFile == true && determineExtn == false) {
			System.out.println("File extension is not from allowed type");
		}
		if (validateFile && determineExtn) {
			fv.getFileInformation(filePath);
			FileSearch fs = new FileSearch();
			fs.searchStringInFile(filePath, searchString);
		}

		return Constants.lineNumbers;
	}

}
