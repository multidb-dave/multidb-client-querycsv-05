--- table northwind_e.products from Elasticsearch database source 

--- table northwind_a.Categories from PostgreSQL database source 
--- table northwind_e.Products from Elasticsearch database source 

-- SELECT c.category_id, p.product_name
-- FROM northwind_c.products p
-- JOIN northwind_a.categories c
-- ON c.category_id = p.category_id

SELECT c.category_id, p._MAP['ProductName']
FROM northwind_a.categories c
INNER JOIN northwind_e.products p
ON c.category_id = p._MAP['CategoryID']

