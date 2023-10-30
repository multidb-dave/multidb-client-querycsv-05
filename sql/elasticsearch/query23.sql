--- table northwind_b.suppliers from SQL Server database source 
--- table northwind_e.products from Elasticsearch database source 
SELECT s.SupplierID, _MAP['ProductName'], S.CompanyName, _MAP['SupplierID']
FROM northwind_b.suppliers s
JOIN northwind_e.products p
ON s.SupplierID = SupplierID
WHERE s.CompanyName IN ('Exotic Liquids','Specialty Biscuits, Ltd.','Escargots Nouveaux')
ORDER BY s.SupplierID
