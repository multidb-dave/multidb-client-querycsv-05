--- table northwind_f.employees from MongoDB database source 
SELECT _MAP['FirstName'], _MAP['LastName'], _MAP['BirthDate']
FROM northwind_f.employees
where _MAP['BirthDate'] >= '1950-01-01'
AND _MAP['BirthDate'] < '1960-01-01'
