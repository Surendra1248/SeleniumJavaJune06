 Feature: Application Login
 
 Scenario: Login with valid credentials
 Given Open any browser
 And Navigate to login page
 When User enters username as "phaniatw2@gmail.com" and password as "atw@123" in to the fields
 And User clicks on login button
 Then verify user is able to successfully login
 
 