package com.rabobank.service.validationservice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

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
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rabobank.services.ValidationService#validateDuplicate(com.rabobank.
	 * domain.Record)
	 * 
	 * This method validate duplicate reference field in inputfile
	 */
	@Override
	public List<Record> validateDuplicate(RecordList records) {
		/*if (record != null) {
			List<CustomerStatements> statement = customerStatementsRepository.findByReference(record.getReference());
			record.setIsUniqueStatement(statement.isEmpty());
		}*/
		  // ArrayList with duplicate elements
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println(numbersList);
        List<Record> listWithoutDuplicates = records.getRecords().stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Record::getReference))),
                ArrayList::new));
       /*List<Record> listWithoutDuplicates = records.getRecords().stream().distinct().collect(Collectors.toList());
        List<Record> unique = records.getRecords().stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Record::getReference))),
                                         ArrayList::new));*/
       // List<Customer> distinctCustomers = customersList.stream().distinct().collect(Collectors.toList());
       for(Record rec:listWithoutDuplicates) {
           System.out.println("unique list "+rec.toString());

       }
        System.out.println("unique list size"+listWithoutDuplicates.size());
//     /   System.out.println("unique  size"+unique);
        return listWithoutDuplicates;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rabobank.services.ValidationService#validateEndBalance(com.rabobank.
	 * domain.Record)
	 * 
	 * this method validates endBalance field in input file
	 */
	@Override
	public void validateEndBalance(RecordList records) {
		records.getRecords().parallelStream().forEach(record -> {
			System.out.println("###"+record.getStartBalance().add(record.getMutation()).compareTo(record.getEndBalance()));
			if (record != null
					&& record.getStartBalance().add(record.getMutation()).compareTo(record.getEndBalance()) == 0) {
				record.setIsValidEndBalance(true);
			}
		});
	}
public static void main(String[] args) {
	ValidationServiceImpl impl = new ValidationServiceImpl();
	//impl.validateDuplicate(null);
}
}
