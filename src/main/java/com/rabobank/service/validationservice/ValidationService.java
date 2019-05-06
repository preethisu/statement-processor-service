package com.rabobank.service.validationservice;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rabobank.readerrows.Record;
import com.rabobank.readerrows.RecordList;

/**
 * @author Preethi
 *
 */
public interface ValidationService {

//	void validateDuplicate(Record record);

	//void validateEndBalance(Record record);

	List<Record> validateDuplicate(RecordList records);

	void validateEndBalance(RecordList records);

}
