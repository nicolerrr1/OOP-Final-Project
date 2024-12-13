# EventHub: Your Local Event Organizer
---
## I. Project Overview
**EventHub** is an intuitive event management application designed to simplify the process of scheduling and managing events. It ensures conflict-free scheduling by checking date availability and provides suggestions for alternative dates when needed. Users can efficiently perform operations such as creating, viewing, updating, and deleting events.

By automating event organization, **EventHub** enhances productivity and promotes effective community engagement. The project aligns with **Sustainable Development Goal (SDG) 11 - Sustainable Cities and Communities**, advocating for improved resource management and organized urban development.

---
## II. Application of OOP Principles
EventHub leverages fundamental Object-Oriented Programming (OOP) principles to ensure modularity, reusability, and maintainability. Below is how these principles are integrated into the project:

### 1. Encapsulation
- All data in the classes such as *id*, *name*, *description*, and date are private and accessible only via public getter and setter methods.
- The ***Event*** class securely encapsulates event-related data, allowing controlled modifications.

 ### 2. Inheritance
-  The ***Event*** class acts as a base class for managing event properties and functionality, enabling the potential for extending the application by creating specialized event types in the future.

### Polymorphism
- Polymorphism is demonstrated through the ***displayDetails*** method which can have different implementations depending on the event type.
- This flexibility allows the program to handle various event types while maintaining a unified interface.

### Abstraction
- The *** EventManager *** class manages high-level operations such as creating, updating, and deleting events. Users interact with this interface without needing to understand the internal implementation details.
- Database operations are abstracted through the ***EvnetDAO*** class.

---
## Chosen SDG and Integration
### Sustainable Development Goal 11 - Sustainable Cities and Communities
EventHub supports SDG 11 by:
- **Optimizing Resource Management**: Prevents scheduling conflicts, ensuring shared resources like venues are utilized efficiently.
- **Enchacing Organization**: Automates event planning, reducing administrative workload and minimizing errors.
-  **Promoting Community Engagement**: ENcourages organized activities that strengthen social ties and sustainable practices.

EventHub fosters sustainability and better community resource utilization through features like automatic date suggestions and centralized event management.

---
## IV. Instruction for Running the Program
### Prerequisites
- Java Development Kit (JDK) 8 or later.

### Steup Steps
**1. Clone the Repository**
```
git clone https://github.com/nicolerrr1/OOP-Final-Project-EventHUB.git  
cd OOP-Final-Project-EventHUB
```
**2. Compile the Java Files**
- Compile the Java files:
```
javac Final_Project/*.java
```
- Run the main application
```
java Final_project.EventOrganizerApp
```
**3.Using EventHub**
- The application provides a menu-driven interface:
  -   **1:** Create a new event.
  -   **2:** View all events.
  -   **3:** Update an existing event
  -   **4:** Delete an event
  -   **5:** Exit the application
- Follow the prompts to mange event seamlessly.
