package com.rabobank.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.exception.FileFormatException;
import com.rabobank.readerfactory.StatementReader;
import com.rabobank.readerimpl.CSVStatementReaderImpl;
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
	private static final Logger logger = LoggerFactory.getLogger(CSVStatementReaderImpl.class);

	@Autowired
	@Qualifier("csvreader")
	StatementReader<RecordList> csvReader;

	@Autowired
	@Qualifier("xmlreader")
	StatementReader<RecordList> xmlReader;

	/**
	 * @param inputFile
	 * @return concrete implementaiton of the respective reader
	 */
	public StatementReader<RecordList> getFileReader(MultipartFile inputFile) {
		StatementFileType fileType = StatementFileType.getFileType(inputFile.getOriginalFilename());
		logger.info("StatementReaderFactory.filetype ::"+fileType.toString());
		switch (fileType) {
		case CSV:
			return csvReader;
		case XML:
			return xmlReader;
		default:
			throw new FileFormatException(fileType);
		}
	}

}
