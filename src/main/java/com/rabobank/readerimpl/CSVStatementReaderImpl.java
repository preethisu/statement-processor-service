package com.rabobank.readerimpl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rabobank.readerfactory.StatementReader;
import com.rabobank.readerrows.Record;

/**
 * @author Preethi
 *
 */
@Component
@Qualifier("csvreader")
public class CSVStatementReaderImpl implements StatementReader {

	private static final Logger logger = LoggerFactory.getLogger(CSVStatementReaderImpl.class);
	   int ctr =0;

	/*
	 * this take csv file as input and parses it .
	 */
	 @Override
	public List<Record> readStatement(MultipartFile file ) {
		List<Record> records = new ArrayList<Record>();
		try (
	            Reader reader = new InputStreamReader(file.getInputStream());
	        ) {
			CsvToBean<Record> csvToBean = new CsvToBeanBuilder<Record>(reader).withType(Record.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			records = csvToBean.parse();
		} catch (Exception e) {
			logger.error("IO Exception While reading the statement from CSV", e);
		}

		return records;

	}
	
}
