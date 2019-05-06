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
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rabobank.readerfactory.StatementReader;
import com.rabobank.readerrows.Record;
import com.rabobank.readerrows.RecordList;

/**
 * @author Preethi
 *
 */
@Component
@Qualifier("csvreader")
public class CSVStatementReaderImpl implements StatementReader<RecordList> {

	private static final Logger logger = LoggerFactory.getLogger(CSVStatementReaderImpl.class);
	   int ctr =0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rabobank.reader.StatementReader#readStatement(org.springframework.web
	 * .multipart.MultipartFile)
	 * 
	 * this take csv file as input and parse it .
	 */
	 @Override
	public RecordList readStatement(MultipartFile file ) {
		List<Record> customerStatements = new ArrayList<>();
		String SAMPLE_CSV_FILE_PATH = "C:\\Preethi\\assignment - BE\\records.csv";
		RecordList records = new RecordList();
        System.out.println("CSV list "+file.getOriginalFilename());
		Reader fileReader;
		CSVReader csvReader;
		try (
	            Reader reader = new InputStreamReader(file.getInputStream());//Files.newBufferedReader(file.getInputStream());
	        ) {
			System.out.println("********************************\n"+file.getInputStream());

		     /* fileReader = new BufferedReader(new FileReader(file.getOriginalFilename()));
		      csvReader = new CSVReader(fileReader);*/
		      

			/*csvReader = new InputStreamReader(Record.class.getClassLoader()
	                .getResourceAsStream(file.getOriginalFilename()));*/
			 // csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

			CsvToBean<Record> csvToBean = new CsvToBeanBuilder<Record>(reader).withType(Record.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			  // records = (RecordList) csvToBean.parse();
			   records.setRecords(csvToBean.parse());
			   for(Record rec:records.getRecords()) {
		            System.out.println("CSV list "+rec.toString());

		        }
			csvToBean.forEach(record -> {
				System.out.println( record.toString());
				/*Record customerRecords = new Record((String)record.getReference(), record.getAccountNumber(),
						record.getDescription(), record.getStartBalance(), record.getMutation(),
						record.getEndBalance());

				customerStatements.add(customerRecords);*/
			});
			//records.setRecords(customerStatements);

		} catch (Exception e) {
			logger.error("IO Exception While reading the statement from CSV", e);
		}

		return records;

	}
	
	public static void main(String[] args) {
		CSVStatementReaderImpl reader = new CSVStatementReaderImpl();
		//reader.readStatement();
	}


}
