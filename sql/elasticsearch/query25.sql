--- table northwind_a.Categories from PostgreSQL database source 
--- table northwind_e.Products from Elasticsearch database source 

--- select _MAP['ProductName'], _MAP['CategoryID']
--- FROM northwind_a.categories a, northwind_e.products b
--- WHERE a.category_id = b.CategoryID

-- SELECT c.category_id, _MAP['ProductName']
-- FROM northwind_a.categories c
-- JOIN northwind_e.products p
-- ON c.category_id = p._MAP['CategoryID']

SELECT c.category_id, _MAP['ProductName']
FROM northwind_a.categories c, northwind_e.products p
WHERE c.category_id = p._MAP['CategoryID']

