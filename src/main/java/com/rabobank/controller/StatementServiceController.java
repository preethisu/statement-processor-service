package com.rabobank.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.factory.StatementReaderFactory;
import com.rabobank.readerrows.Record;
import com.rabobank.service.validationservice.ValidationService;


/**
 * @author Preethi
 *This class proceses the statments
 */
@RestController
@RequestMapping(value = "/statementservice")
public class StatementServiceController {

	@Autowired
	StatementReaderFactory statementReaderType;
	
	@Autowired
	ValidationService validationService;
	
	/**
	 * this method processed the uploaded file and validates
	 * */
	@RequestMapping(value = "/processStatements", method = RequestMethod.POST)
	   public List<Record> processStatements(
	           @RequestParam("file") MultipartFile file )
	           throws ServletException, IOException {
		  List<Record> records = statementReaderType.getFileReader(file).readStatement(file);
		  records = (validationService.validateDuplicate(records));
		  validationService.validateEndBalance(records);
		  return records;
	}
	
}
