--- table northwind_a.employees from CSV database source
SELECT UPPER(FirstName) AS FirstName, UPPER(LastName) AS LastName, HireDate
FROM northwind_a.employees
ORDER BY HireDate
