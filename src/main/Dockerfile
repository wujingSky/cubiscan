FROM frolvlad/alpine-oraclejdk8:slim
VOlUME /tmp
COPY ../../target/cubi-0.0.1.jar cubiApp.jar
ENTRYPOINT ["java","-jar","/cubiApp.jar"]
