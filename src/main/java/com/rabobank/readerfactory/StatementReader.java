package com.rabobank.readerfactory;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Preethi
 *
 */
public interface StatementReader<T> {

	T readStatement(MultipartFile file);

}
