--- table northwind_b.products from CSV database source
--- table northwind_c.suppliers from MySQL database source
--- table northwind_f.categories from MongoDB database source
SELECT s.SupplierID, s.CompanyName, c._MAP['CategoryName'], p.ProductName, p.UnitPrice
FROM northwind_b.products p
JOIN northwind_c.suppliers s
ON s.SupplierID = p.SupplierID
JOIN northwind_f.categories C
On c._MAP['CategoryID'] = p.CategoryID;
