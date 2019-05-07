package com.rabobank.factory;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

import com.rabobank.exception.FileFormatException;
import com.rabobank.readerfactory.StatementReader;
import com.rabobank.readerimpl.CSVStatementReaderImpl;
import com.rabobank.readerimpl.XMLStatementReaderImpl;

/**
 * @author Preethi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatementFactoryTest {
	private static final Logger logger = LoggerFactory.getLogger(StatementFactoryTest.class);

	@Autowired
	StatementReaderFactory statementFactory;

	MultipartFile csvFile;

	MultipartFile xmlFile;

	MultipartFile wrongFile;

	@Autowired
	@Qualifier("csvreader")
	StatementReader csvReader;

	@Autowired
	@Qualifier("xmlreader")
	StatementReader xmlReader;

	@Before
	public void init() {

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
	public void getFileReaderCsvTest() {
		assertEquals(true,
				!(statementFactory.getFileReader(csvFile).getClass().isInstance(CSVStatementReaderImpl.class)));

	}

	@Test()
	public void getFileReaderXmlTest() {

		assertEquals(true,
				!(statementFactory.getFileReader(xmlFile).getClass().isInstance(XMLStatementReaderImpl.class)));

	}

	@Test(expected = FileFormatException.class)
	public void getFileReaderUnSupportedTest() {

		statementFactory.getFileReader(wrongFile).readStatement(wrongFile).isEmpty();

	}


}
