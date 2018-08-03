# QA-Coding-Challenge


REST

1. Write test (using any library eg. rest assured) that will call
GET    on /comments endpoint and will assert the response code and will
assert that number of comments is greater than 0
assert that body has comment with email: "Jayne_Kuhic@sydney.com"

2. Write POJO class named 'Comment' that will represent the call from the previous exercise.
Retrieve the comments from endpoint /comments and deserialize response into the collection of objects 'Comment'

3. Write code that will filter the collection from point 2 using given condition:
remove all comments from the collection that has postId different than 1.
Remove all comments that do not contain word "non" in the body.
4. Please use JUnit as testing framework and gradle or maven.