--- select distinct b.*, a.Category_Name
--- table northwind_a.Categories from PostgreSQL database source
--- table northwind_b.Products from CSV database source
select b.*, a.Category_Name
from northwind_a.Categories a 
inner join northwind_b.Products b on a.Category_ID = b.CategoryID
where b.Discontinued = 0
order by b.ProductName
