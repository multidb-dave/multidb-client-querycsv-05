--- table northwind_c.order_details from Oracle database source 
--- table northwind_c.orders from SQL Server database source
--- table northwind_f.customers from MongoDB database source
SELECT od.Order_ID, c._MAP['ContactName'], od.Unit_Price, od.Quantity, od.Discount
FROM northwind_c.order_details od
JOIN northwind_c.orders o
ON od.Order_ID = o.OrderID
JOIN northwind_f.customers c
ON c._MAP['CustomerID'] = o.CustomerID
WHERE od.Discount <> '0'
