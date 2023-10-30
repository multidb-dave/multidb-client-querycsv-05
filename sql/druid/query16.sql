--- table northwind_c.order_details from Oracle database source 
--- table northwind_c.orders from SQL Server database source
--- table northwind_d.customers from DRUID database source
SELECT od.Order_ID, c.Contact_Name, od.Unit_Price, od.Quantity, od.Discount
FROM northwind_c.order_details od
JOIN northwind_c.orders o
ON od.Order_ID = o.OrderID
JOIN northwind_d.customers c
ON c.Customer_ID = o.CustomerID
WHERE od.Discount <> '0'
