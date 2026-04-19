# HungerHub

HungerHub is a Java 21 Maven WAR web application designed for Apache Tomcat 9. It serves a modern single-page food delivery UI and exposes a small Servlet API used by the frontend.

## Tech Stack

- Java 21
- Maven 3.8+
- Apache Tomcat 9.x
- Servlet API 4.0 (`javax.servlet`)
- HTML5, CSS3, and vanilla JavaScript

## Build

```bash
mvn clean package
```

The deployable artifact is created at:

```text
target/hungerhub.war
```

## Deploy To Tomcat 9

Copy the WAR file into Tomcat's `webapps` directory:

```bash
cp target/hungerhub.war /path/to/apache-tomcat-9.0.117/webapps/
```

Then start or restart Tomcat. The app will be available at:

```text
http://<server-ip>:8080/hungerhub/
```

## Jenkins Pipeline Job Notes

No `Jenkinsfile` is included because this project is intended to be configured as a Jenkins Pipeline Job from the Jenkins UI. A typical job should:

1. Pull this GitHub repository.
2. Run `mvn clean package`.
3. Copy `target/hungerhub.war` to the Tomcat 9 `webapps` directory.
4. Restart or reload Tomcat if your environment requires it.

The application context path is controlled by the WAR name: `hungerhub.war`.
