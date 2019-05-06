package com.rabobank.util;

import org.apache.commons.io.FilenameUtils;

/**
 * @author Preethi
 * 
 *         this file used to validate the input and output statement file type.
 *         throws unsupported file format for types other than csv and xml
 *
 */
public enum StatementFileType {
	CSV("csv"), XML("xml"), FILE_TYPE_NOT_SUPPORTED("unsupported File Format !!!");

	String fileExtension;

	private StatementFileType(final String fileExtension) {
		this.fileExtension = fileExtension;
	}

	String getFileExtension() {
		return fileExtension;
	}

	/**
	 * @param inputFilePath
	 * @return StatementFileType
	 * 
	 *         This method is used to identify input file type
	 */
	public static StatementFileType getFileType(String inputFilePath) {
		if (FilenameUtils.isExtension(inputFilePath.toLowerCase(), CSV.getFileExtension())) {
			return CSV;
		} else if (FilenameUtils.isExtension(inputFilePath.toLowerCase(), XML.getFileExtension())) {
			return XML;
		}
		return FILE_TYPE_NOT_SUPPORTED;
	}

}
