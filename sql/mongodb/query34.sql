--- table northwind_a.Categories from PostgreSQL database source 
--- table northwind_f.Products from MongoDB database source 
select b.*, a.Category_Name
from northwind_a.Categories a 
inner join northwind_f.Products b on a.Category_ID = b._MAP['CategoryID']
where b._MAP['Discontinued'] = 0
order by b._MAP['ProductName']
