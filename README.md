# professional-API

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
