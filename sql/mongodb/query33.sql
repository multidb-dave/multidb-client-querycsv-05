--- table northwind_b.suppliers from SQL Server database source 
--- table northwind_f.products from MongoDB database source 
SELECT s.SupplierID, p._MAP['ProductName'], s.CompanyName
FROM northwind_b.suppliers s
JOIN northwind_f.products p
ON s.SupplierID = p._MAP['SupplierID']
WHERE s.CompanyName IN ('Exotic Liquids','Specialty Biscuits, Ltd.','Escargots Nouveaux')
ORDER BY s.SupplierID
