This repository represents a Spring Boot microservices architecture designed around Apache Kafka for event-driven communication and AI integration using Google Gemini.

The system demonstrates how synchronous REST APIs and asynchronous Kafka messaging can work together to build a scalable, decoupled backend system.

The architecture consists of multiple Spring Boot services communicating through:

REST APIs (via API Gateway)
Apache Kafka (for asynchronous processing)
Dedicated databases per service
External AI system (Google Gemini)
Kafka is used to decouple business services from AI processing.

<img width="957" height="624" alt="springimageAI" src="https://github.com/user-attachments/assets/b72dff83-0549-4406-b0ee-c57f29108363" />
