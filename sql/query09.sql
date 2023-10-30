--- table northwind_a.employees from CSV database source
SELECT FirstName, LastName, BirthDate
FROM northwind_a.employees
where BirthDate >= '1950-01-01'
AND BirthDate < '1960-01-01'
