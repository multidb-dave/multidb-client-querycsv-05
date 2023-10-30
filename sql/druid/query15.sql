--- table northwind_d.employees from DRUID database source 
SELECT first_name, last_name, birth_date
FROM northwind_d.employees
where birth_date >= '1950-01-01'
AND birth_date < '1960-01-01'
