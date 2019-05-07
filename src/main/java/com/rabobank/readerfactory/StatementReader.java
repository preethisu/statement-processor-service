package com.rabobank.readerfactory;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.rabobank.readerrows.Record;

/**
 * @author Preethi
 *
 */
public interface StatementReader<T> {

	List<Record> readStatement(MultipartFile file);

}
