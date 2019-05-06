package com.rabobank.readerrows;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * @author Preethi
 * 
 *
 */
@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecordList {

	@XmlElement(name = "record")
	private List<Record> recordList;

	public RecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

	public RecordList() {
	}

	public List<Record> getRecords() {
		return recordList;
	}

	public void setRecords(List<Record> record) {
		this.recordList = record;
	}

}
