To run separately

1-st microservice:
java -jar \
/Users/Rage/IdeaProjects/My/myapp/build/libs/myapp.jar \
--spring.profiles.active=dev \
--server.port=8080 \
--spring.application.name=MyApp-1

2-nd microservice:
java -jar \
/Users/Rage/IdeaProjects/My/myapp/build/libs/myapp.jar \
--spring.profiles.active=dev \
--server.port=8090 \
--spring.application.name=MyApp-2