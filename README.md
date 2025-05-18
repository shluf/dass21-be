# Dass 21
<small>
DASS 21 was created by Australian Peter Lovibond, PhD, and consists of measuring different levels of depression, anxiety and stress. It is carried out through a questionnaire with 21 questions related to the intensity of symptoms and situations experienced over a period of seven days.
</small>

---

### Set Up and run
1. Java Installation: Ensure Java Development Kit (JDK) is installed on your machine. This project requires Java 21.
2. IDE Setup: Choose an Integrated Development Environment (IDE) like IntelliJ IDEA, Eclipse, or Spring Tool Suite for easier development.
3. Ensure that you have Docker installed
4. Run from IDE: Click on the run button in your IDE.
5. Command Line: ```mvn spring-boot:run```.

---

### Postman
To facilitate, I created a file called ```postman-collection.json``` 
inside the path ```src/main/resources/postman``` which has all the endpoints which is declared
in the project. To use the collection, just import it inside the postman.

---

### Grafana
It is structured for analysis and decision-making, a dashboard with data obtained from the forms.

#### How to access?
1. http://localhost:3000/

#### Connect the data source
1. Click on: `Add datasource`
2. Select `postgresql`
3. Host -> `host.docker.internal:5432`
4. Database -> `dass21_db`
5. User -> `postgres`
6. Password -> `postgres`
7. In the end of the page, click to `Save & Test`

#### Import the dashbord
1. Open the dashboard page
2. Click in the `new` button
3. Click in the `import` button
4. Copy all the JSON from the `dashboard.json` located inside the folder ```src/main/resources/grafana``` or just drag and drop the file inside the upload section
5. Paste inside the `Import via dashboard JSON model` text area
6. Click in the `Load` button

#### Access the dashboard
1. Open the dashboard page
2. Select the dashboard which you created in the previous step-by-step

---

### SonarQube
The main point of sonarqube is to see the quality of the code,
so, I already set up the docker image, when you run the project will be available on http://localhost:9000/
To generate a analysis, you should follow the step by step gived by the sonarqube platform

---

### Prometheus
It is used in this project only for grafana access to the database

---

### Swagger
The application swagger can be found in the url http://localhost:8080/swagger-ui/index.html#/