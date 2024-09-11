# Inversion of control (IoC) and dependency Injection (DI)

- 🔄 **Inversion of Control (IoC)**: IoC is a design principle in Spring Boot where the control flow of a program is inverted. Instead of the developer manually creating objects and managing dependencies, the Spring IoC container takes over these responsibilities. It creates, configures, and manages the lifecycle of objects (beans) in your application, ensuring they are wired together according to the configuration.


- 🛠️ **Dependency Injection (DI)**: DI is a specific implementation of IoC in Spring Boot. It allows the Spring framework to inject dependencies (objects) into a class automatically, rather than the class having to create those dependencies itself. This is typically done through constructor injection, setter injection, or field injection. DI promotes loose coupling, making the code more modular and easier to test.


- 🔄 **Inversion of Control (IoC) Example**:
    - Imagine you have a service class `OrderService` that depends on another class `PaymentService`.
    - In a traditional approach, you might manually create an instance of `PaymentService` inside `OrderService` like this:

      ```java
      public class OrderService {
          private PaymentService paymentService = new PaymentService();
          
          public void processOrder() {
              paymentService.processPayment();
          }
      }
      ```

    - In this approach, `OrderService` is tightly coupled to `PaymentService`, and you are responsible for creating and managing the lifecycle of `PaymentService`.

    - With IoC in Spring Boot, the control is inverted. Instead of `OrderService` creating `PaymentService`, the Spring IoC container creates and injects `PaymentService` into `OrderService`.



- 🛠️ **Dependency Injection (DI) Example**:
    - Using DI, you can rewrite `OrderService` to receive `PaymentService` from the Spring container:

      ```java
      @Service
      public class OrderService {
          private final PaymentService paymentService;
  
          @Autowired
          public OrderService(PaymentService paymentService) {
              this.paymentService = paymentService;
          }
          
          public void processOrder() {
              paymentService.processPayment();
          }
      }
      ```

    - Here’s what happens:
        - The `@Autowired` annotation tells Spring to inject the `PaymentService` dependency when it creates an instance of `OrderService`.
        - You no longer need to manually instantiate `PaymentService`; Spring does that for you.

    - To make this work, you also need to declare `PaymentService` as a Spring-managed bean:

      ```java
      @Service
      public class PaymentService {
          public void processPayment() {
              // Payment processing logic
          }
      }
      ```

    - Now, `OrderService` is loosely coupled with `PaymentService`, as the dependency is injected by Spring at runtime, promoting better modularity and testability.