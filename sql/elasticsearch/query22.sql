--- table northwind_e.products from Elasticsearch database source 
SELECT _MAP['CategoryID'] as CategoryID, _MAP['ProductName'] as ProductName
FROM NORTHWIND_E.products
