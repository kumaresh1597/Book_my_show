# Book_my_show
The Spring Boot application is designed to maintain records of movies, users, theaters, shows associated with theaters, and ticket bookings. It provides more than 15 APIs for various operations. 
The application consists of several entities, including Movie, User, Theater, Show, and Ticket, each having specific attributes to capture relevant information. 
Controllers and endpoints are used to expose the functionality as RESTful APIs.

1) The MovieController manages operations related to movies, including retrieving a list of movies, creating new movies, updating movie details, and deleting movies.
2) The UserController handles similar operations for users, while the TheaterController focuses on theaters.
3) The ShowController manages shows, enabling users to retrieve show information, add new shows, update show details, and remove shows.
4) The TicketController oversees ticket-related operations, encompassing retrieving ticket details, creating new ticket bookings, updating ticket information, and canceling tickets.

Services handle the business logic, while repositories interact with the underlying database, which can be a relational database like MySQL.
Additionally, the application integrates with email functionality to facilitate email notifications or communication. 
This low-level design establishes a robust foundation for building a Spring Boot application to effectively manage movies, users, theaters, shows, and ticket bookings, incorporating email integration, and allowing further customization and expansion based on specific requirements.

# Tech stack & tools
Spring Boot,JPA,Hibernate,MySQL,Java,Maven
Postman,Intellij,workbench
