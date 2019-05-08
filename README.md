# statement-processor-service
## Statement Processor Service
         This project is to process customer statement by validating duplicate entries & accounts validation for the Customers .
## Assumptions :
          -Input 	file format can be either CSV or XML
 	
          -Input 	file will have list of entries of Customer's accounts such as 	"accountNumber","description","endBalance","mutation","reference","startBalance"
 	
          -A service need to validate Duplicate entries of "Reference" 	& sum of "EndBalance"
 	
          -Output will be a list a valid json

## Softwares / Technologies Used:
	-Eclipse 	- IDE for Developement
 	
	-JDK 8
 	
	-Embedded tomcat
 	
	-Spring Boot 2
 	
	-Junit 4
 	
	-Maven
 	
	-OpenCSV for CSV
 	
	-JAXB for xml
 	
	-Lombok
 	
	-Spring Boot maven plugin
 	
	-PostMan for API Testing
 	
	-Sl4J for logging 	


## Build & Deployment :
          -git clone https://github.com/preethisu/statement-processor-service.git
 	
          -cd statement-processor-service/
 	
          -mvn spring-boot:run or mvn spring-boot:run (if we want use installed 	maven)
          
          -Application will be started with tomcat 


## Running & Testing :
          -As we are using PostMan , we can use postman application for testing our API
          
          -Open PostMan desktop app
          
          -hit the url http://localhost:8080/statementservice/processStatements	
          
          -upload input file using choosefile button( (Body → 	Form-Data)
          
          -click Send button
          
          -ResponseBody will be a list of valid JSONs






