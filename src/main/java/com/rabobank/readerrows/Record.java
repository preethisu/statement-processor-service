package com.rabobank.readerrows;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Preethi
 *
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {
	/**
	 * 
	 */
	@XmlAttribute
	@CsvBindByName
	private Long reference;
	@CsvBindByName
	private String accountNumber;
	@CsvBindByName(column = "Start Balance")
	private BigDecimal startBalance;
	@CsvBindByName
	private BigDecimal mutation;
	@CsvBindByName
	private String description;
	@CsvBindByName(column = "End Balance")
	private BigDecimal endBalance;
	private boolean isValidEndBalance;
	private boolean isUniqueStatement;

	public Record(Long reference, String accountNumber, String description, BigDecimal startBalance,
			BigDecimal mutation, BigDecimal endBalance) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.description = description;
		this.endBalance = endBalance;
		this.isValidEndBalance = false;
		this.isUniqueStatement = false;

	}

}
