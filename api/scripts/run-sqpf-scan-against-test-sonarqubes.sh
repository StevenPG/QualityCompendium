mvn clean install -f ../pom.xml sonar:sonar -Dsonar.host.url=http://localhost:19100 -Dsonar.projectKey=project1
mvn clean install -f ../pom.xml sonar:sonar -Dsonar.host.url=http://localhost:19100 -Dsonar.projectKey=project2
mvn clean install -f ../pom.xml sonar:sonar -Dsonar.host.url=http://localhost:19200 -Dsonar.projectKey=project1
mvn clean install -f ../pom.xml sonar:sonar -Dsonar.host.url=http://localhost:19200 -Dsonar.projectKey=project2
mvn clean install -f ../pom.xml sonar:sonar -Dsonar.host.url=http://localhost:19300 -Dsonar.projectKey=project1
mvn clean install -f ../pom.xml sonar:sonar -Dsonar.host.url=http://localhost:19300 -Dsonar.projectKey=project2
