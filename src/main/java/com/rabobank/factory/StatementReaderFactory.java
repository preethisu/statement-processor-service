package com.rabobank.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.exception.FileFormatException;
import com.rabobank.readerfactory.StatementReader;
import com.rabobank.readerrows.RecordList;
import com.rabobank.util.StatementFileType;

/**
 * @author Preethi
 * 
 *         Factory class to invoke concrete implementation
 *
 */

@Component
public class StatementReaderFactory {

	@Autowired
	@Qualifier("csvreader")
	StatementReader<RecordList> csvReader;

	@Autowired
	@Qualifier("xmlreader")
	StatementReader<RecordList> xmlReader;

	/*@Autowired
	@Qualifier("csvwriter")
	StatementWriter csvwriter;

	@Autowired
	@Qualifier("xmlwriter")
	StatementWriter xmlwriter;*/

	/**
	 * @param inputFile
	 * @return concrete impl
	 * 
	 *         This method return xmlReaderImpl or csvReaderImpl based on file
	 *         type else throw exception
	 */
	public StatementReader<RecordList> getFileReader(MultipartFile inputFile) {
		StatementFileType fileType = StatementFileType.getFileType(inputFile.getOriginalFilename());
		System.out.println("fileType"+fileType.toString());
		switch (fileType) {
		case CSV:
			return csvReader;
		case XML:
			return xmlReader;
		default:
			throw new FileFormatException(fileType);
		}
	}

	/**
	 * @param inputFile
	 * @return concrete impl
	 * 
	 *         This method return xmlWriterImpl or csvWriterImpl based on file
	 *         type else throw exception
	 */
	/*public StatementWriter getFileWriter(MultipartFile inputFile) {
		StatementFileType fileType = StatementFileType.getFileType(inputFile.getOriginalFilename());
		switch (fileType) {
		case CSV:
			return csvwriter;
		case XML:
			return xmlwriter;
		default:
			throw new FileFormatException(fileType);
		}
	}*/

}
