--- table northwind_d.categories from DRUID database source 
SELECT c.category_name, c.description
FROM northwind_d.categories c
WHERE category_id = '7'
