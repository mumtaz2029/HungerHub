# HungerHub

HungerHub is a Java 21 Maven WAR web application built for Apache Tomcat 9. This repository is structured as a multi-deployment DevOps project that demonstrates manual deployment, Jenkins Freestyle CI/CD, Jenkins Pipeline CI/CD, and automated artifact storage in an Amazon S3 bucket.

The frontend is a modern single-page food delivery interface for **HungerHub** with featured cuisines, customer favorites, testimonials, and app download sections. The backend uses a Java Servlet API endpoint to serve menu data to the UI.



<img width="1917" height="1026" alt="Screenshot 2026-04-20 150630" src="https://github.com/user-attachments/assets/55db5064-0cda-4ca7-bbe8-620c9574bb88" />


## Project Objective

The main goal of this project is to implement and document multiple deployment strategies for the same Java web application on Apache Tomcat:

1. Manual WAR deployment using the Tomcat Manager UI.
2. Automated build and deployment using a Jenkins Freestyle Job.
3. Automated build and deployment using a Jenkins Pipeline Job.
4. Automated storage of generated deployment artifacts in an Amazon S3 bucket.

## Tech Stack

- Java 21
- Maven 3.8+
- Apache Tomcat 9.x
- Servlet API 4.0 using `javax.servlet`
- Jenkins
- Git and GitHub
- Amazon S3 for artifact storage
- HTML5, CSS3, and vanilla JavaScript

## Application Structure

```text
HungerHub
|-- pom.xml
|-- README.md
`-- src
    `-- main
        |-- java
        |   `-- com
        |       `-- hungerhub
        |           |-- MenuItem.java
        |           `-- MenuServlet.java
        `-- webapp
            |-- WEB-INF
            |   `-- web.xml
            |-- assets
            |   |-- css
            |   |   `-- styles.css
            |   `-- js
            |       `-- app.js
            `-- index.html
```

## Build The Application

Run the Maven package command:

```bash
mvn clean package
```

After a successful build, Maven creates the WAR file:

```text
target/hungerhub.war
```

This WAR file is the deployable artifact used in Tomcat.

## Deployment Method 1: Manual Deployment Using Tomcat Manager

This method documents the direct Tomcat Manager deployment flow before the same release process is automated through Jenkins.

### Steps Followed

1. Built the project using Maven.

   ```bash
   mvn clean package
   ```

2. Verified that the WAR file was generated inside the `target` folder.

   ```text
   target/hungerhub.war
   ```

3. Opened the Tomcat Manager application in the browser.

   ```text
   http://<tomcat-server-ip>:8080/manager/html
   ```

4. Scrolled to the **WAR file to deploy** section.

5. Clicked **Choose File** and selected `hungerhub.war` from the local downloaded project folder.

6. Clicked **Deploy**.

7. Confirmed that the application was deployed successfully and visible in the Tomcat applications list.

8. Accessed the deployed application using:

   ```text
   http://<tomcat-server-ip>:8080/hungerhub/
   ```

### Manual Deployment Evidence

The manual deployment process is documented with screenshots showing:

- Selecting `hungerhub.war` from the local `target` folder.



<img width="1919" height="1036" alt="Screenshot 2026-04-20 150554" src="https://github.com/user-attachments/assets/11b209e8-c999-415f-9853-9705a567b1b4" />


- Uploading the WAR file through the Tomcat Manager deployment section.




<img width="1910" height="1029" alt="Screenshot 2026-04-20 150613" src="https://github.com/user-attachments/assets/8853e262-3a6f-4bbe-b3a6-542f51ae2424" />

## Deployment Method 2: Jenkins Freestyle Job CI/CD

After completing manual deployment, the next deployment approach automates the build and deployment using a Jenkins Freestyle Job.

### Purpose

The Freestyle Job automates the CI/CD process through Jenkins UI configuration, showing how the application can be built and deployed without a pipeline script.

### Steps Followed

1. Created a new Jenkins Freestyle Project.

2. Configured GitHub repository access in the **Source Code Management** section.

   ```text
   https://github.com/mumtaz2029/HungerHub.git
   ```

3. Selected the branch to build.

   ```text
   main
   ```

4. Added a build step to run Maven.

   ```bash
   mvn clean package
   ```

5. Generated the WAR file during the Jenkins build.

   ```text
   target/hungerhub.war
   ```

6. Added a deployment step to copy or deploy the generated WAR file to the Tomcat server.

7. Ran the Jenkins Freestyle Job.

8. Verified that Jenkins built the project successfully and deployed the WAR file to Tomcat.

9. Opened the Tomcat application URL to confirm that HungerHub was running.

   ```text
   http://<tomcat-server-ip>:8080/hungerhub/
   ```

### Freestyle Job Result

The Jenkins Freestyle Job successfully automated:

- Pulling source code from GitHub.
- Building the Java Maven project.
- Creating the WAR artifact.
- Deploying the WAR file to Tomcat.

## Deployment Method 3: Jenkins Pipeline Job CI/CD

The final deployment method uses a Jenkins Pipeline Job. This approach organizes checkout, build, deployment, and artifact handling into a structured CI/CD workflow.

### Purpose

The pipeline job was created to automate the complete deployment lifecycle:

- Checkout source code from GitHub.
- Build the application using Maven.
- Generate the WAR file.
- Deploy the WAR file to Tomcat.
- Store the generated deployment artifact in Amazon S3.

### Pipeline Job Stages

The Jenkins Pipeline Job followed this general flow:

1. **Checkout Stage**

   Jenkins pulled the latest HungerHub source code from GitHub.

2. **Build Stage**

   Maven built the project and generated the WAR file.

   ```bash
   mvn clean package
   ```

3. **Package Stage**

   The deployable WAR artifact was created.

   ```text
   target/hungerhub.war
   ```

4. **Deploy Stage**

   The WAR file was deployed to Apache Tomcat 9.

5. **Artifact Backup Stage**

   The generated WAR file was uploaded to an Amazon S3 bucket for artifact storage.

### Pipeline Job Result

The Jenkins Pipeline Job successfully automated:

- Source code checkout.
- Maven build.
- WAR packaging.
- Tomcat deployment.
- S3 artifact upload.

## Artifact Storage In Amazon S3

As part of the multi-deployment workflow, the deployed WAR artifacts were stored in an S3 bucket through Jenkins pipeline automation.

### Why S3 Artifact Storage Was Added

Storing build artifacts in S3 helps with:

- Keeping a backup of deployed versions.
- Tracking deployment history.
- Reusing a previous WAR file if rollback is required.
- Separating application source code from generated deployment artifacts.

### Artifact Stored

```text
hungerhub.war
```

### Artifact Flow

```text
GitHub Repository
        |
        v
Jenkins Pipeline Job
        |
        v
Maven Build
        |
        v
target/hungerhub.war
        |
        v
Tomcat Deployment
        |
        v
Amazon S3 Artifact Storage
```

## Jenkins Automation Evidence

The Jenkins automation process is documented with two screen recordings:

### freestyle CI/CD Automation visual

https://github.com/user-attachments/assets/dde8dcf9-22f7-47fd-bb9b-7274f23bf096

### pipeline CI/CD Automation visual


https://github.com/user-attachments/assets/f4a37145-9b51-4cb0-b73f-a28877df9fa0


These recordings show the automated Jenkins deployment work, including Freestyle Job and Pipeline Job execution.

## Final Application URL

After deployment, the application can be accessed from Tomcat using:

```text
http://<tomcat-server-ip>:8080/hungerhub/
```

## Project Highlights

- Java web application packaged as a Maven WAR artifact.
- Manual deployment through Apache Tomcat Manager.
- Jenkins Freestyle Job for automated build and deployment.
- Jenkins Pipeline Job for structured CI/CD automation.
- Artifact backup and version retention using Amazon S3.
- End-to-end integration of GitHub, Jenkins, Maven, Tomcat, and S3.

## Project Summary

HungerHub is both a food delivery web application and a complete multi-deployment DevOps project. It demonstrates how the same Java WAR application can be released manually, through Jenkins Freestyle automation, through Jenkins Pipeline automation, and with deployment artifacts preserved in Amazon S3.
