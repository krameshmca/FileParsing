package com.ramesh.fileparse;

import com.ramesh.fileparse.impl.FileParseImpl;

public class FileParse {

	public static void main(String[] args) {
		String filePath = null;
		String searchString = null;
		FileParseImpl impl = new FileParseImpl();
		try {
			if (args.length == 2) {
				if (args[0] != null) {
					filePath = args[0];
				}
				if (args[1] != null) {
					searchString = args[1];
				}
				String returnVal = impl.parseFile(filePath, searchString);
				System.out.println("Line number list: " + returnVal);
			} else {
				System.out.println("Two Input Parameters expected!! Full File Path and Search String is expected.. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
