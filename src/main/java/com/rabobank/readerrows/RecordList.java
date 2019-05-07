package com.rabobank.readerrows;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author Preethi
 * 
 *
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class RecordList {

	@XmlElement(name = "record")
	private List<Record> recordList;

	public RecordList(List<Record> recordList) {
		this.recordList = recordList;
	}

}
