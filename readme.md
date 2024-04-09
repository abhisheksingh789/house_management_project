# HomeSphere Real Estate Management System (REMS)

## Introduction
Welcome to HomeSphere, a full-fledged Real Estate Management System developed to simplify and facilitate the property management processes. Designed for real estate offices, agents, buyers, and administrators, HomeSphere allows for effective management of property listings and seamless coordination between various stakeholders in the real estate market.

## Features

### Property Search and Filtering
- Advanced property search based on location, property type, and price.
- Intuitive browsing of search results with detailed property information.

### User Account Management
- Secure login system for different roles including buyers, agents, and administrators.
- Role-specific access control to ensure users only see relevant data and actions.

### Property and Agent Information
- Comprehensive property listings including images, sizes, types, and status.
- Direct access to agent contact information for facilitated buyer-agent communication.

### Transaction Management
- Detailed record-keeping of property transactions for agents and buyers.
- Status updates and history tracking for all property listings.

## Technical Details

- **Language**: Java
- **Database**: MySQL
- **DB Connector**: MySQL Connector/J
- **GUI Toolkit**: Swing

## Project Structure

### Java Source Files
- `mypro.java`: Main application entry point.
- `Login`: Classes for handling login logic for various user roles.
- `Success`: Classes for successful login operations for different users.
- `Filter & Query`: Functionality for property searching and query formulation.
- `Agent Management`: Administrative tools for managing agents.
- `Property & Transaction`: Interfaces and logic for managing property listings and transactions.

### Configuration Files
- `settings.json`: Configuration file for database connections and application settings.

### SQL Scripts
- `Database Schema`: Scripts for creating the necessary database schema.
- `Data Population`: Scripts for populating the database with sample data.
- `Relationship Management`: Scripts for defining relationships between data entities.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- MySQL Database Server
- MySQL Connector/J library

### Installation
1. Clone the project repository to your local machine.
2. Set up a MySQL database and import the schema from the provided SQL scripts.
3. Configure the `settings.json` to point to your database and MySQL Connector/J library.
4. Compile the Java source files using `javac` or your preferred IDE.
5. Run `mypro.java` to launch the application.

## Contributing
We welcome contributions from the community. To contribute to HomeSphere, please follow the standard fork-and-pull request workflow.

- Fork the repository.
- Create your feature branch (`git checkout -b feature/AmazingFeature`).
- Commit your changes (`git commit -m 'Add some AmazingFeature'`).
- Push to the branch (`git push origin feature/AmazingFeature`).
- Open a pull request.

## Versioning
We use [SemVer](http://semver.org/) for versioning. For the available versions, see the [tags on this repository](#).

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

## Authors
- **Abhishek Kumar** - *Initial work* - [Your GitHub](https://github.com/abhisheksingh789)

See also the list of [contributors](https://github.com/abhisheksingh789/house_management_project/tree/main/gitcontributors) who participated in this project.

## Acknowledgments
- Hat tip to anyone whose code was used
- Inspiration
- etc.

---

