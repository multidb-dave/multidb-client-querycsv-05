--- https://github.com/ChickenLeg05/Northwind-Database
--- table northwind_c.employees from MySQL database source
SELECT UPPER(FirstName) AS FirstName, UPPER(LastName) AS LastName, HireDate
FROM northwind_c.employees
ORDER BY HireDate
