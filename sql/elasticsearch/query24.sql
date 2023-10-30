--- table northwind_a.Categories from PostgreSQL database source 
--- table northwind_c.Products from PostgreSQL database source 

SELECT c.category_id, p.product_name
FROM northwind_c.products p
JOIN northwind_a.categories c
ON c.category_id = p.category_id
