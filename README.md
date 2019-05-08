# statement-processor-service

Statement Processor Service
          This project is to process customer statement by validating duplicate entries & accounts validation for the Customers .
          
          
Assumptions :
          Input file format can be either CSV or XML
          Input file will have list of entries of Customer's accounts such as "accountNumber","description","endBalance","mutation","reference","startBalance"
          A service need to validate Duplicate entries of "Reference" & sum of "EndBalance"
          Output will be a list a valid json
          "Unsupported file format" message will be sent to client if the input type is other than CSV or XML .
          
          
Softwares / Technologies Used:
          Eclipse - IDE for Developement
          JDK 8
          Embedded tomcat
          Spring Boot 2
          Junit 4
          Maven
          OpenCSV for CSV
          JAXB for xml
          Lombok
          Spring Boot maven plugin
          PostMan for API Testing
          Sl4J for logging 
          
Build & Deployment :
          1.git clone https://github.com/preethisu/statement-processor-service.git
          2.cd statement-processor-service/
          3.mvnw spring-boot:run or mvn spring-boot:run (if we want use installed maven) 4)Application will be started with tomcat
          Running & Testing :
          As we are using PostMan , we can use postman application for testing our API
          1.Open PostMan desktop app
          2. hit the url http://localhost:8080/statementservice/processStatements?file 	
          3.upload input file using choosefile button( (Body → Form-Data)
          4.click Send button
          5.ResponseBody will be a list of valid JSONs


