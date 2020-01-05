# shopping-list-backend
java spring backend for the very simple shopping list app

Super-simple shopping list app backend, that works as a REST-api and doesn't use a data-base but just a local list. 

# API-specification:
Get-request gives back the sorted list of items, with the cheapest at the top of the list. 
Delete at /id deletes the specified item from the list. 
Post-request with { item: "banana", cost: "10"} adds a banana with cost 10 to the list. 
Get-request at /summary gives back a summary with the most expensive and cheapest item, as well as the total cost of all items on the list. 
