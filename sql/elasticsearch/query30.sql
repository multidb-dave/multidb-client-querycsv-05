--- table northwind_e.categories from Elasticsearch database source 
--- table northwind_e.products from Elasticsearch database source 

SELECT c._MAP['CategoryID'], p._MAP['CategoryID'], p._MAP['ProductName']
FROM northwind_e.categories c, northwind_e.products p
WHERE c._MAP['CategoryID'] = p._MAP['CategoryID']
