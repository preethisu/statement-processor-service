package com.rabobank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.factory.StatementReaderFactory;
import com.rabobank.readerfactory.StatementReader;
import com.rabobank.readerrows.Record;
import com.rabobank.readerrows.RecordList;
import com.rabobank.service.validationservice.ValidationService;


/**
 * @author Preethi
 *
 */
@Controller
@RequestMapping(value = "/statementservice")
public class StatementServiceController {

	@Autowired
	StatementReaderFactory statementReaderType;
	
	@Autowired
	ValidationService validationService;
	
	 
	@RequestMapping(value = "/processStatements", method = RequestMethod.POST)
	   public List<Record> processStatements(
	           @RequestParam("file") MultipartFile file )
	           throws ServletException, IOException {
		RecordList records = statementReaderType.getFileReader(file).readStatement(file);
		  records.setRecords(validationService.validateDuplicate(records));
		  validationService.validateEndBalance(records);
		  return records.getRecords();
	}
}
