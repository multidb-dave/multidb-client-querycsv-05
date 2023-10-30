--- table northwind_a.Categories from PostgreSQL database source 
--- table northwind_d.Products from DRUID database source 
select b.*, a.Category_Name
from northwind_a.Categories a 
inner join northwind_d.Products b on a.Category_ID = b.category_id
where b.Discontinued = 0
order by b.product_name
