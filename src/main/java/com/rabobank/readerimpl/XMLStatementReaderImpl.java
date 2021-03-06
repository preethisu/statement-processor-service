package com.rabobank.readerimpl;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.readerfactory.StatementReader;
import com.rabobank.readerrows.Record;
import com.rabobank.readerrows.RecordList;

/**
 * @author Preethi
 *
 */
@Component
@Qualifier("xmlreader")
public class XMLStatementReaderImpl implements StatementReader {
	private static final Logger logger = LoggerFactory.getLogger(XMLStatementReaderImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rabobank.reader.StatementReader#readStatement(org.springframework.web
	 * .multipart.MultipartFile)
	 * 
	 * this take xml file as input and parse it .
	 */
	@Override
	public List<Record> readStatement(MultipartFile file) {
		RecordList records = new RecordList();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RecordList.class);
			Unmarshaller unmarshallerObj = jaxbContext.createUnmarshaller();
			records = (RecordList) unmarshallerObj.unmarshal(file.getInputStream());
			return records.getRecordList();

		} catch (JAXBException e) {
			logger.error("JAXBException While reading the statement from XML", e);
		} catch (Exception e) {
			logger.error("Exception While reading the statement from XML", e);
		}
		return null;

	}

	public static void main(String[] args) {
		
	}
}
