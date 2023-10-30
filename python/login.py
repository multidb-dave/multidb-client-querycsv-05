import requests

# Specify the API URL we want to send our JSON to
url = "http://localhost:8080/auth/login"
url = "http://api001.multidb.net:8080/auth/login"
# Specify the appropriate header for the POST request
headers = {"Content-Type": "application/json"}
# Specify the JSON data we want to send
data = {"email": "multidbuser@onesql.com", "password": "Mdb2023xyzbcd"}
# response = requests.post(url, headers=headers, json=data)
# print(response.status_code)
# print(response.text)

 
response = requests.post(url, headers=headers, data=data)
 
print("Status Code", response.status_code)
# print("JSON Response ", response.json())
print(response.text)
