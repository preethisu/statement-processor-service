package com.rabobank.reader;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.factory.StatementFactoryTest;
import com.rabobank.factory.StatementReaderFactory;
import com.rabobank.readerfactory.StatementReader;
import com.rabobank.readerrows.Record;

/**
 * @author Preethi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatementReaderTest {
	private static final Logger logger = LoggerFactory.getLogger(StatementFactoryTest.class);

	@Autowired
	StatementReaderFactory statementFactory;

	@Autowired
	@Qualifier("csvreader")
	StatementReader csvReader;

	@Autowired
	@Qualifier("xmlreader")
	StatementReader xmlReader;

	MultipartFile csvFile;

	MultipartFile xmlFile;

	MultipartFile wrongFile;

	@Before
	public void init() {

		Record record = new Record();
		record.setAccountNumber("NL91RABO0315273637");
		record.setReference(Long.valueOf(194261));
		record.setDescription("Clothes from Jan Bakker");
		record.setStartBalance(BigDecimal.valueOf(21.6));
		record.setEndBalance(BigDecimal.valueOf(-20.23));
		record.setMutation(BigDecimal.valueOf(-41.83));
		record.setUniqueStatement(false);

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File csvTestFile = new File(classLoader.getResource("records.csv").getFile());
			csvFile = new MockMultipartFile("records.csv", csvTestFile.getName(), "text/csv",
					IOUtils.toByteArray(new FileInputStream(csvTestFile)));
			File xmlTestFile = new File(classLoader.getResource("records.xml").getFile());
			xmlFile = new MockMultipartFile("records.xml", xmlTestFile.getName(), "text/xml",
					IOUtils.toByteArray(new FileInputStream(xmlTestFile)));
			File wrongTestFile = new File(classLoader.getResource("dummy.txt").getFile());
			wrongFile = new MockMultipartFile("dummy.txt", wrongTestFile.getName(), "text/plain",
					IOUtils.toByteArray(new FileInputStream(wrongTestFile)));
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

	@Test()
	public void readStatementCsvTest() {

		assertEquals(Long.valueOf(194261), ((Record) csvReader.readStatement(csvFile).get(0)).getReference());

	}

	@Test()
	public void readStatementXmlTest() {

		assertEquals(Long.valueOf(194261), ((Record) xmlReader.readStatement(xmlFile).get(0)).getReference());

	}

}
