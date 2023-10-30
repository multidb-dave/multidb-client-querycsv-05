--- table northwind_e.categories from Elasticsearch database source 

SELECT _MAP['CategoryName'], _MAP['Description']
FROM northwind_e.categories
WHERE _MAP['CategoryID'] = '7'
