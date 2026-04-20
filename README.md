# HungerHub

HungerHub is a Java 21 Maven WAR web application built for Apache Tomcat 9. The project was created as a DevOps practice application to demonstrate manual deployment, Jenkins freestyle CI/CD, Jenkins pipeline CI/CD, and automated artifact storage in an Amazon S3 bucket.

The frontend is a modern single-page food delivery interface for **HungerHub** with featured cuisines, customer favorites, testimonials, and app download sections. The backend uses a Java Servlet API endpoint to serve menu data to the UI.

## Project Objective

The main goal of this project is to practice deploying a Java web application to Tomcat in multiple real-world DevOps ways:

1. Manual WAR deployment using the Tomcat Manager UI.
2. Automated build and deployment using a Jenkins Freestyle Job.
3. Automated build and deployment using a Jenkins Pipeline Job.
4. Storing generated deployment artifacts in an Amazon S3 bucket through pipeline automation.

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

This method was used to understand the basic Tomcat deployment flow before automating it with Jenkins.

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
- Uploading the WAR file through the Tomcat Manager deployment section.

Screenshots used for documentation:

<img width="1919" height="1036" alt="Screenshot 2026-04-20 150554" src="https://github.com/user-attachments/assets/11b209e8-c999-415f-9853-9705a567b1b4" />




<img width="1910" height="1029" alt="Screenshot 2026-04-20 150613" src="https://github.com/user-attachments/assets/8853e262-3a6f-4bbe-b3a6-542f51ae2424" />

## Deployment Method 2: Jenkins Freestyle Job CI/CD

After completing manual deployment, the next step was to automate the build and deployment using a Jenkins Freestyle Job.

### Purpose

The freestyle job was created to automate the CI/CD process without writing a Jenkins pipeline script. This helped practice Jenkins job configuration through the UI.

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

The final deployment method was done using a Jenkins Pipeline Job. This approach is closer to real DevOps CI/CD workflows because the build, deployment, and artifact handling stages can be managed as a pipeline.

### Purpose

The pipeline job was created to automate the complete deployment lifecycle:

- Checkout source code from GitHub.
- Build the application using Maven.
- Generate the WAR file.
- Deploy the WAR file to Tomcat.
- Store the generated deployment artifact in Amazon S3.

### Pipeline Job Stages Practiced

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

As an additional DevOps practice, the deployed WAR artifacts were stored in an S3 bucket through Jenkins pipeline automation.

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

freestyle CI/CD

https://github.com/user-attachments/assets/dde8dcf9-22f7-47fd-bb9b-7274f23bf096

pipeline CI/CD 



https://github.com/user-attachments/assets/f4a37145-9b51-4cb0-b73f-a28877df9fa0



These recordings show the automated Jenkins deployment work, including Freestyle Job and Pipeline Job execution.

## Final Application URL

After deployment, the application can be accessed from Tomcat using:

```text
http://<tomcat-server-ip>:8080/hungerhub/
```

## Key Learnings

- How to build a Java web application as a WAR file using Maven.
- How to manually deploy a WAR file using Tomcat Manager.
- How Jenkins Freestyle Jobs automate build and deployment steps.
- How Jenkins Pipeline Jobs provide a structured CI/CD workflow.
- How to deploy a Java web application to Apache Tomcat 9.
- How to store generated WAR artifacts in Amazon S3.
- How GitHub, Jenkins, Maven, Tomcat, and S3 work together in a DevOps workflow.

## Project Summary

HungerHub is not only a food delivery web application, but also a complete DevOps deployment practice project. It demonstrates the journey from manual deployment to automated CI/CD using Jenkins, and finally to artifact storage using Amazon S3.
