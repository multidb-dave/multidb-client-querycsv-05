--- table northwind_e.categories from MongoDB database source 
--- table northwind_e.products from MongoDB database source 

SELECT c._MAP['CategoryID'], p._MAP['ProductName']
FROM northwind_f.categories c, northwind_f.products p
WHERE c._MAP['CategoryID'] = 8
