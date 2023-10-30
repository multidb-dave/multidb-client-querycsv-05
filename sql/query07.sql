--- https://github.com/ChickenLeg05/Northwind-Database
--- table northwind_c.suppliers from MySQL database source
--- table northwind_c.products from PostgreSQL database source
SELECT s.SupplierID, p.Product_Name, S.CompanyName
FROM northwind_c.suppliers s
JOIN northwind_c.products p
ON s.SupplierID = p.Supplier_ID
WHERE s.CompanyName IN ('Exotic Liquids','Specialty Biscuits, Ltd.','Escargots Nouveaux')
ORDER BY s.SupplierID
