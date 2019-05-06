package com.rabobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.controller.StatementServiceController;

@SpringBootApplication
public class StatementProcessorServiceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StatementProcessorServiceApplication.class, args);
		/*StatementServiceController controller= (StatementServiceController) context.getBean(StatementServiceController.class);
		controller.readStatements(new MultipartFile());*/

	
	}

}
