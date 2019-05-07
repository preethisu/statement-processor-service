package com.rabobank.service.validationservice;

import java.util.List;

import com.rabobank.readerrows.Record;

/**
 * @author Preethi
 *
 */
public interface ValidationService {

//	void validateDuplicate(Record record);

	//void validateEndBalance(Record record);

	List<Record> validateDuplicate(List<Record> records);

	void validateEndBalance(List<Record> records);

}
