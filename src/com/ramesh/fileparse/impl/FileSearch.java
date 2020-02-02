package com.ramesh.fileparse.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.ramesh.fileparse.constants.Constants;

public class FileSearch {

	public void searchStringInFile(String filePath, String searchString) throws IOException {

		String fileAsTxt = readFile(filePath, searchString);
		System.out.println("----------------------------------------");
		System.out.println("File Content:");
		System.out.println("--------------");
		System.out.println(fileAsTxt);
		System.out.println("----------------------------------------");
		System.out.println("Search String is:" + searchString);
		Constants.lineNumbers = searchString(fileAsTxt, searchString);

	}

	public String searchString(String fileAsTxt, String searchString) {

		int lineNumber = 0;
		// ArrayList<String> list = new ArrayList<String>();
		Set<String> lineList = new HashSet<String>();
		Scanner scanner = new Scanner(fileAsTxt);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			lineNumber++;

			int lastIndex = 0;
			int count = 0;

			while (lastIndex != -1) {

				lastIndex = line.indexOf(searchString, lastIndex);

				if (lastIndex != -1) {
					lineList.add(Integer.toString(lineNumber));
					count++;
					lastIndex += searchString.length();
				}
			}
			Constants.searchCount += count;

		}
		scanner.close();
		System.out.println("word count : " + Constants.searchCount);
		String finalLineList = String.join(",", lineList);
		return finalLineList;

	}

	public String readFile(String filePath, String searchString) throws IOException {
		String retVal = null;

		if (Constants.fileextn.equalsIgnoreCase("pdf")) {
			try {
				PDDocument doc = PDDocument.load(new File(filePath));
				retVal = new PDFTextStripper().getText(doc);
				// System.out.println("PDF Content:");
				// System.out.println(retVal);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (Constants.fileextn.equalsIgnoreCase("docx")) {
			FileInputStream fis = null;

			try {

				File file = new File(filePath);
				fis = new FileInputStream(file.getAbsolutePath());

				XWPFDocument document = new XWPFDocument(fis);
				XWPFWordExtractor extractor = new XWPFWordExtractor(document);
				retVal = extractor.getText();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				fis.close();
			}

		} else if (Constants.fileextn.equalsIgnoreCase("doc")) {
			try {
				File file = new File(filePath);
				FileInputStream fis = new FileInputStream(file.getAbsolutePath());

				HWPFDocument doc = new HWPFDocument(fis);

				WordExtractor we = new WordExtractor(doc);

				retVal = we.getText();

				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (Constants.fileextn.equalsIgnoreCase("txt")) {

			Scanner scanner = null;
			try {
				scanner = new Scanner(Paths.get(filePath), StandardCharsets.UTF_8.name());
				retVal = scanner.useDelimiter("\\A").next();
				scanner.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return retVal;
	}

}
