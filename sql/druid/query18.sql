--- table northwind_b.products from CSV database source
--- table northwind_c.suppliers from MySQL database source
--- table northwind_d.categories from DRUID database source
SELECT s.SupplierID, s.CompanyName, c.Category_Name, p.ProductName, p.UnitPrice
FROM northwind_b.products p
JOIN northwind_c.suppliers s
ON s.SupplierID = p.SupplierID
JOIN northwind_d.categories C
On c.Category_ID = p.CategoryID;
