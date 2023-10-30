--- table northwind_c.suppliers from MySQL database source
--- table northwind_f.products from MongoDB database source
SELECT s.SupplierID, p._MAP['ProductName'], S.CompanyName
FROM northwind_c.suppliers s
JOIN northwind_f.products p
ON s.SupplierID = p._MAP['SupplierID']
WHERE s.CompanyName IN ('Exotic Liquids','Specialty Biscuits, Ltd.','Escargots Nouveaux')
ORDER BY s.SupplierID
