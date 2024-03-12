# What is Kie?
"KIE" stands for Knowledge Is Everything, and it refers to a set of projects and capabilities provided by the Drools and jBPM communities. Drools and jBPM are open-source business rules management systems (BRMS) and business process management (BPM) suites, respectively, both developed under the umbrella of the KIE project.

Here's a brief overview of the key components associated with KIE:

### Drools:
Drools is a business rule management system (BRMS) that allows developers to express and manage business logic in the form of rules. These rules can be easily modified without altering the application's code, providing flexibility and agility to business processes.

### jBPM (Java Business Process Management):
jBPM is a workflow and business process management platform. It allows the modeling, execution, and monitoring of business processes. jBPM integrates with Drools, enabling the incorporation of business rules into complex business processes.

### KieContainer:
A KieContainer is a container for compiled rules and processes. It holds the knowledge artifacts (such as rules and processes) and provides a runtime environment for executing them. It is a central concept in the KIE project, used to manage and organize knowledge assets.

### KieSession:
A KieSession is a runtime session that represents a working memory for a set of rules. It is the main entry point for interacting with the Drools rule engine. During a session, facts (data) are inserted, and rules are fired based on the defined conditions.

### KieBuilder and KieModule:
The KieBuilder is responsible for building knowledge artifacts from resources, such as DRL (Drools Rule Language) files. The resulting compiled knowledge is organized into a KieModule, which can be used to create a KieContainer.

# Drools Spring Boot Integration

This repository demonstrates the integration of Drools with a Spring Boot application to apply dynamic rules for order discounts based on card types and order prices. The rules are defined in Drools' DRL files, and the application provides a REST endpoint to process orders and calculate discounts.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven
- Git

### Clone the Repository
```bash
git clone https://github.com/DohaMaghni/DroolsTest.git
cd testDrools
```

### Build and Run
```bash
mvn clean install
mvn spring-boot:run
```
The Spring Boot application will start, and you can access it at http://localhost:8080.

### Usage
Send a POST request to the /order endpoint with a JSON payload representing an order. Use tools like cURL, Postman, or any API testing tool.

Example using cURL:
```bash
curl -X POST -H "Content-Type: application/json" -d '{"name":"test", "cardType":"DBS", "price":16000}' http://localhost:8080/order
```
Example using postman:
Post request to : http://localhost:8080/order
```json
{
"name":"test",
"cardType":"DBS",
"price":16000
}
```
The application will process the order, apply Drools rules, and print the discount information in the console.

### Drools Rules
Drools rules are defined in the offer.drl file under the src/main/resources directory. You can customize and extend these rules as needed.
```drools
rule "HDFC"
    when
        orderObject : Order(cardType == "HDFC", price > 10000);
    then
        System.out.println("Discount to 10%");
        orderObject.setDiscount(10);
end

rule "ICICI"
    when
        orderObject : Order(cardType == "ICICI", price > 15000);
    then
        System.out.println("Discount to 8%");
        orderObject.setDiscount(8);
end

rule "DBS"
    when
        orderObject : Order(cardType == "DBS", price > 15000);
    then
        System.out.println("Discount to 15%");
        orderObject.setDiscount(15);
end
```
Feel free to modify these rules according to your business logic.

## Method explained

### getKieFileSystem() method:

This method is responsible for creating a new KieFileSystem and adding a Drools rule file ("offer.drl") to it.
It returns the configured KieFileSystem object.

### getKieContainer() method:

This method creates and configures a Drools KieContainer which is used to hold and manage compiled Drools rules.
It calls the getKieFileSystem() method to obtain the configured KieFileSystem.
It then creates a new KieBuilder using the obtained KieFileSystem and builds all the rules.
The resulting KieModule is used to create a new KieContainer, and this container is returned.

### getKieRepository() method:

This method retrieves the KieRepository from the KieServices and adds a new KieModule to it.
The KieModule returned by the anonymous class has a getReleaseId() method that uses the default release ID from the repository.

### getKieSession() method:

This method creates and returns a new Drools KieSession, which is an entry point for interacting with the Drools rule engine.
It calls the getKieContainer() method to obtain the configured KieContainer and then creates a new session from that container.

### DroolConfig constructor:

Initializes the KieServices instance using KieServices.Factory.get().

### @Bean annotation for KieContainer:

Marks the getKieContainer() method as a Spring bean, allowing it to be managed by the Spring IoC container.

### @Bean annotation for KieSession:

Marks the getKieSession() method as a Spring bean, making it available for dependency injection in other Spring components.

## Keep coding joyfully! 😊💻











