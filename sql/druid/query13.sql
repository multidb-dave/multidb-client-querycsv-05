--- table northwind_b.suppliers from SQL Server database source 
--- table northwind_d.products from DRUID database source 
SELECT s.SupplierID, p.product_name, S.CompanyName
FROM northwind_b.suppliers s
JOIN northwind_d.products p
ON s.SupplierID = p.supplier_id
WHERE s.CompanyName IN ('Exotic Liquids','Specialty Biscuits, Ltd.','Escargots Nouveaux')
ORDER BY s.SupplierID
