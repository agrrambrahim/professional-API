# MAIN TASK : 

develop an app for consuming the company professionals and their projects.
Now, for serving data to the app you need to implement a back-end service to provide RESTful
APIs for the following tasks:
Task 1
Allow an editor to create/update/delete a professional.
• display one professional.
• list all professionals for a given period for updated date(e.g.: 30days or 1 year).
• list all professionals by latitude and longitude.
• find all professionals for specific type(s).
A professional consists of(minimum) the following information:
- header
- first name
- last name
- user name
- company name
- phone
- email
- website
- city
- country
- latitude
- longitude
- updated date
- type(s)
- project(s)
Task 2
Allow an editor or a professional to create/update/delete a project for a professional.
• display one project.
• list all projects for a given professional.
• list all projects for a given period of duration.(e.g.: 30days or 1 year).
• find all projects for specific type(s).
A project consists of(minimum) the following information:
- header
- title
- description
- duration
- owner(i.e. -professional)
- type(s)
A owner consists of(minimum) the following information:
- display name(first occurrence of “first name + last name/user name/company name”)
- phone
- email
- website
- city
- country

Bonus Task
Security: secure the API. It's up to you how you are going to implement the security.
Each endpoint should only return the data that is really needed to fulfil the use case.
Hints:
Please use the Java technology you are most comfortable with (we use spring-boot). The data does
not need to be persisted after the application is shut down.
We review
We will consideration your experience for the implementation.
We value quality over completeness. You can leave things aside as long as you mention them in

your project's README. The goal of this code sample is to find what you consider production-
ready code. You should consider this code ready for final review with your colleague, i.e. this would

be the last step before deploying to production.
The assessment of your implementation includes following points:
• Architecture: how you implement interfaces.
• Correctness: your application is running. We find missing pieces(if any) in README.
• Code quality: you write simple, easy to understand, and maintainable code. Your
implementation follows object-oriented code principles such as the single responsibility
principle and consistent throughout the codebase.
• Testing: you implementation is covered with automated testings. What happen to the test
cases if the requirement change afterwards. You have both unit and integration testing. For
sure we're not asking for full test coverage (given time constraint) however you still
demonstrated your testing skills.
• Technical choices: you choose appropriate libraries, databases, architecture etc.

# README : professional-API

you can look read the documentation and test this sample solution http://localhost:8088/swagger-ui.html. "username" :"api", "password" : "password"

Since this application will be owned by the resource owner and there is no plan to expose these APIs to third party applications,
i protected this api with a simple JWT token based authorization is most simple and reasonable

if you want to test this sample application using another tool ,the custom JWT token based authentication flow can be designated as the following steps : 
    Get the JWT based token from the authentication endpoint, eg /auth/signin.
    Extract token from the authentication result.
    Set the HTTP header Authorization value as Bearer jwt_token.
    Then send a request to access the protected resources.
    If the requested resource is protected, Spring Security will use our custom Filter to validate the JWT token, and build an Authentication object and set it in Spring Security specific SecurityContextHolder to complete the authentication progress.
    If the JWT token is valid it will return the requested resource to client.
