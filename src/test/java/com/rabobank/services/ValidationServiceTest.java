package com.rabobank.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabobank.readerrows.Record;
import com.rabobank.readerrows.RecordList;
import com.rabobank.service.validationservice.ValidationService;

/**
 * @author Preethi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationServiceTest {

	@Autowired
	ValidationService validationService;

/*	@MockBean
	CustomerStatementsRepository customerStatementsRepository;*/
	@Mock
	private Record record;

	@Mock
	private RecordList recordList;
	
	private RecordList emptyRecords = null;
	@Mock
	private RecordList accurateRecordList;



	@Before
	public void init() {

		record.setAccountNumber("NL91RABO0315273637");
		record.setReference(Long.valueOf(194261));
		record.setDescription("Clothes from Jan Bakker");
		record.setStartBalance(BigDecimal.valueOf(21.6));
		record.setEndBalance(BigDecimal.valueOf(-41.83));
		record.setMutation(BigDecimal.valueOf(-20.23));

		/*BeanUtils.copyProperties(record, statments);
		resultMatch.add(statments);*/
	}
	
	@Test
	public void validateEndBalanceForEmpty() {
		validationService.validateEndBalance(recordList.getRecordList());
		for(Record rec:recordList.getRecordList()) {
			assertEquals(false, rec.isValidEndBalance());
		}

	}
	@Test(expected = NullPointerException.class)
	public void validateEndBalanceNull() {
		validationService.validateEndBalance(emptyRecords.getRecordList());
		for(Record rec:recordList.getRecordList()) {
			assertEquals(false, rec.isValidEndBalance());
		}

	}
	@Test
	public void validateEndBalanceInAccurate() {
		validationService.validateEndBalance(accurateRecordList.getRecordList());
		for(Record rec:accurateRecordList.getRecordList()) {
			assertEquals(false, rec.isValidEndBalance());
		}

	}

	/*@Test(expected = NullPointerException.class)
	public void validateDuplicateNull() {

		when(validationService.findByReference(any(Long.class))).thenReturn(resultMatch);
		validationService.validateDuplicate(emptyRecord);
		assertEquals(false, emptyRecord.getIsUniqueStatement());

	}
	
	@Test
	public void validateDuplicateAccurate() {

		when(customerStatementsRepository.findByReference(any(Long.class))).thenReturn(resultMatch);
		validationService.validateDuplicate(record);
		assertEquals(false, record.getIsUniqueStatement());
	}

	@Test
	public void validateDuplicateInAccurate() {

		when(customerStatementsRepository.findByReference(any(Long.class))).thenReturn(resultNotMatch);
		validationService.validateDuplicate(record);
		assertEquals(true, record.getIsUniqueStatement());

	}

	@Test(expected = NullPointerException.class)
	public void validateEndBalanceNull() {
		validationService.validateEndBalance(emptyRecord);
		assertEquals(false, emptyRecord.getIsValidEndBalance());

	}

	
	@Test
	public void validateEndBalanceAccurate() {
		record.setStartBalance(BigDecimal.valueOf(15));
		record.setMutation(BigDecimal.valueOf(-5));
		record.setEndBalance(BigDecimal.valueOf(10));
		validationService.validateEndBalance(record);
		assertEquals(true, record.getIsValidEndBalance());

	}*/

}
