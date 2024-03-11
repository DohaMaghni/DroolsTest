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


Keep coding joyfully! 😊💻











