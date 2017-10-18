## Spring2-kotlin-trial

Trying SpringBoot2 with kotlin, MongoDb as repository and docker for containerization.

* Clean and build => ```./gradlew clean build```

#### Running locally
* Install Mongo locally or run Mongo docker container locally connected to local host and port:  
```docker run --name myMongoDB -p 27017:27017 -d mongo```

* run the app 
```./gradlew bootRun```


#### Building and running as docker service
* Start the mongo container as 
```docker run --name myMongoDB -d mongo```

* Create the application image as
```./gradlew buildDocker```

* Run and link it to the db as
```docker run -d -p 8080:8080 --link myMongoDB com.example/spring2-kotlin-trial:0.0.1-SNAPSHOT```