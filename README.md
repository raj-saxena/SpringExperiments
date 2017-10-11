# SpringExperiments

### Notes
* Difference between a controller test with `MockMVC` vs `TestRestTemplate`
    * `MockMVc` - Spring mocks the request and response but no actual network calls are made. Servlet container is not started
    * `TestRestTemplate` - Actual servlet container started and a network call is made.
