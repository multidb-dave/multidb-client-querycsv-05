--- https://github.com/ChickenLeg05/Northwind-Database
--- table northwind_b.suppliers from SQL Server database source
--- table northwind_b.products from CSV database source
SELECT s.SupplierID, p.ProductName, S.CompanyName
FROM northwind_b.suppliers s
JOIN northwind_b.products p
ON s.SupplierID = p.SupplierID
WHERE s.CompanyName IN ('Exotic Liquids','Specialty Biscuits, Ltd.','Escargots Nouveaux')
ORDER BY s.SupplierID
