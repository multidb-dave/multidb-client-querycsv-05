--- physical column name in northwind.suppliers is supplier_id NOT SupplierID
--- physical column name in northwind.suppliers is product_name NOT ProductName

--- table northwind_b.suppliers from SQL Server database source
--- table northwind.suppliers from PostgreSQL database source
SELECT s.SupplierID, p.product_name, S.CompanyName 
FROM northwind_b.suppliers s 
JOIN northwind.suppliers p 
ON s.SupplierID = p.supplier_id 
WHERE s.CompanyName 
IN ('Exotic Liquids','Specialty Biscuits, Ltd.','Escargots Nouveaux') 
ORDER BY s.SupplierID

--- SELECT s.SupplierID, p.ProductName, S.CompanyName
--- FROM northwind_b.suppliers s JOIN northwind.suppliers p 
--- ON s.SupplierID = p.SupplierID WHERE s.CompanyName 
--- IN ('Exotic Liquids','Specialty Biscuits, Ltd.','Escargots Nouveaux')
--- ORDER BY s.SupplierID

--- Column names which are referred in the virtual table has to correspond
--- to the physical column names of the physical table.
--- Each table may have different column names in different database source.
--- column name SupplierID for SQL Server table products
--- column name supplier_id for PostgreSQL table products
--- column name ProductName for SQL Server table products
--- column name product_name for PostgreSQL table products
