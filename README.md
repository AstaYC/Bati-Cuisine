The stages of carrying out the project

1. Requirements Analysis: Identified the need for a cost management system for construction projects, focusing on materials, labor, and project details.
2. Database Design: Designed the PostgreSQL schema with key tables for projects, customers, materials, labor, and a component table to link materials and labor to each project using foreign keys.
3. Backend Development: Implemented the business logic in Java, using JDBC to connect to the PostgreSQL database. Applied DAO pattern to handle database operations.
4. Cost Calculation: Developed methods to calculate the total project cost, including material and labor costs, VAT, and profit margins.
5. Testing and Refinement: Tested the system thoroughly to ensure accurate calculations and database integrity, refining the project based on feedback.

How to use the Application and Demo !
=== Welcome to the Kitchen Renovation Project Management Application ===

=== Main Menu ===
1. Create a new project
2. Display existing projects
3. Calculate the cost of a project
4. Exit

Choose an option: 1

--- Customer Search ---
Would you like to search for an existing customer or add a new one?
1. Search for an existing customer
2. Add a new customer

Choose an option: 1

--- Searching for Existing Customer ---
Enter the customer's name: Mrs. Dupont
Customer found!
Name: Mrs. Dupont
Address: 12 Rue des Fleurs, Paris
Phone number: 06 12345678

Would you like to continue with this customer? (y/n): y

--- Creating a New Project ---
Enter the project name: Kitchen Renovation Mrs. Dupont
Enter the kitchen area (in m²): 20

--- Adding Materials ---
Enter the name of the material: Tiles
Enter the quantity of this material (in m²): 20
Enter the unit cost of this material (€/m²): 30
Enter the transport cost of this material (€): 50
Enter the quality coefficient of the material (1.0 = standard, > 1.0 = high quality): 1.1

Material added successfully!
Would you like to add another material? (y/n): y

Enter the name of the material: Paint
Enter the quantity of this material (in liters): 10
Enter the unit cost of this material (€/liter): 15
Enter the transport cost of this material (€): 20
Enter the quality coefficient of the material (1.0 = standard, > 1.0 = high quality): 1.0

Material added successfully!
Would you like to add another material? (y/n): n

--- Adding Labor ---
Enter the type of labor (e.g., Basic Worker, Specialist): Basic Worker
Enter the hourly rate of this labor (€/h): 20
Enter the number of hours worked: 40
Enter the productivity factor (1.0 = standard, > 1.0 = high productivity): 1.0

Labor added successfully!
Would you like to add another type of labor? (y/n): y

Enter the type of labor (e.g., Basic Worker, Specialist): Specialized Worker
Enter the hourly rate of this labor (€/h): 35
Enter the number of hours worked: 20
Enter the productivity factor (1.0 = standard, > 1.0 = high productivity): 1.1

Labor added successfully!
Would you like to add another type of labor? (y/n): n

--- Calculating Total Cost ---
Would you like to apply VAT to the project? (y/n): y
Enter the VAT percentage (%): 20
Would you like to apply a profit margin to the project? (y/n): y
Enter the profit margin percentage (%): 15

Calculating costs...

--- Calculation Result ---
Project Name: Kitchen Renovation Mrs. Dupont
Client: Mrs. Dupont
Project Address: 12 Rue des Fleurs, Paris
Area: 20 m²

--- Cost Details ---
1. Materials:
   - Tiles: €710.00 (quantity: 20 m², unit cost: €30/m², quality: 1.1, transport: €50)
   - Paint: €170.00 (quantity: 10 liters, unit cost: €15/liter, transport: €20)

**Total material cost before VAT: €880.00**
**Total material cost with VAT (20%): €1,056.00**

2. Labor:
   - Basic Worker: €800.00 (hourly rate: €20/h, hours worked: 40 h, productivity: 1.0)
   - Specialized Worker: €770.00 (hourly rate: €35/h, hours worked: 20 h, productivity: 1.1)

**Total labor cost before VAT: €1,570.00**
**Total labor cost with VAT (20%): €1,884.00**

3. Total cost before margin: €2,940.00
4. Profit margin (15%): €441.00

**Final total cost of the project: €3,381.00**

--- Saving the Quote ---
Enter the quote issue date (format: dd/mm/yyyy): 10/09/2024
Enter the quote validity date (format: dd/mm/yyyy): 10/10/2024
Would you like to save the quote? (y/n): y

Quote saved successfully!

--- End of Project ---
