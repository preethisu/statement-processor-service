package com.rabobank.service.validationservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rabobank.readerimpl.CSVStatementReaderImpl;
import com.rabobank.readerrows.Record;
import com.rabobank.readerrows.RecordList;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author Preethi
 *
 */
@Component
public class ValidationServiceImpl implements ValidationService {

	/* 
	 * This method validate duplicate records based on the reference field in inputfile
	 */
	@Override
	public List<Record> validateDuplicate(List<Record> records) {
		List<Record> listWithoutDuplicates = records.stream().collect(collectingAndThen(
				toCollection(() -> new TreeSet<>(comparingLong(Record::getReference))), ArrayList::new));
		return listWithoutDuplicates;
	}

	/*
	 * this method validates end balance field in input file
	 */
	@Override
	public void validateEndBalance(List<Record> records) {
		records.parallelStream().forEach(record -> {
 			if (record != null
					&& record.getStartBalance().add(record.getMutation()).compareTo(record.getEndBalance()) == 0) {
				record.setValidEndBalance(true);
			}
		});
	}
 
}
