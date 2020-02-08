# QualityCompendium (QC)

This is a simple unofficial implementation of the basic portfolio visual features available in SonarQube's Enterprise offering. This was created due to budget restrictions but a need to display multiple sonarqube results in a single location.

[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=com.stevenpg.qc&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.stevenpg.qc)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.stevenpg.qc&metric=coverage)](https://sonarcloud.io/dashboard?id=com.stevenpg.qc)

[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.stevenpg.qc&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.stevenpg.qc)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.stevenpg.qc&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.stevenpg.qc)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.stevenpg.qc&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.stevenpg.qc)
-----------------------------

## What is this?

Smaller companies using SonarQube's community offering may look to other tools or may manually build out dashboards that will provide a comprehensive view of a collection of SonarQube projects.

## Who is this for?

There are many great capabilities that come along with SonarQube Enterprise Edition and above, the Portfolio feature being just one. 

It is trivial for a team to use the array of available plugins and tools to create dashboards that collect SonarQube data across projects. However, not all organizations have the tools available that integrate with SonarQube to generate similar dashboards.

QC is designed to be a simple tool that can be configured with a single file, that will dynamically update dashboards with SonarQube data as an alternative to manual dashboards or purchasing other tools' plugins.

------------------------------

## What can it do?

### Configurations

By default, you are able to provide the following configuration items in the single configuration file:

    {
      "sonarhosturl": "<single sonarqube ce url>",
      "page" : [
        {
          "entryname" : "<logical groupings of project keys>",
          "projectKeys" : [
            "<sonarqube project key>"
          ]
        }
      ],
      "sonarReadonlyUser" : "<user account username>",
      "sonarReadonlyPass" : "<user account password>"
    }
    
![Image of qc showing example overview](images/full_image.png?raw=true)

### Releasability Rating

The Core Quality scale is defined as the average passing projects out of total projects in a Project Pages grouping.

    - A: > 80%
    - B: > 60%
    - C: > 40%
    - D: > 20%
    - E: <= 20%

![Image of qc showing example overview](images/releasability.png?raw=true)

### UI Components

#### API & SonarQube connection polling

![Image of qc showing example overview](images/api_status.png?raw=true)

#### Measures Included

![Image of qc showing example overview](images/measures.png?raw=true)

------------------------------

## Version Compatibility

| Version  | Working |
| ------------- | ------------- |
| 8.1  | Yes (build 31237)  |
| 7.9  | Yes  |
| 7.6  | Yes  |
| 6.7.6  | Yes  |

------------------------------

## How to configure qc via CLI

When executing the qc jar, provide the following spring property override at runtime. (*Required*)

     java -jar SonarQube-Portfolio-Free-X.X.X-SNAPSHOT.jar --qc.file-location=/path/to/file
     
You can also set how often the binary will attempt to read this file using the following property:

    # update-interval in ms
     java -jar SonarQube-Portfolio-Free-X.X.X-SNAPSHOT.jar --qc.update-interval=5000
     
## Configuring through Docker

    # Mount the directory that contains the file, and reference the path using qc.file-location
    # The application lives in /app
    docker run -v host:/app/config <containerId> --qc.file-location=/app/config/myfile.json --qc.update-interval=5000

------------------------------

## Handling Self-Signed Certificates on SonarQube

#### Kubernetes

Create a configmap that contains the cacerts file to be used within the JVM included in the image.

    kubectl -n <optional-namespace> create configmap ca-pemstore — from-file=your-cert.pem
    
Mount this certificate in the following location (example below)

    apiVersion: v1 
    kind: Pod
    metadata:
      name: example-pod
    spec:
          containers:
          - name: qc-container
            image: qc-some-version:latest
            volumeMounts:
            - name: ca-pemstore
              mountPath: /etc/ssl/certs/your-cert.pem
              subPath: your-cert.pem
              readOnly: false
            ...
            ...
          volumes:
          - name: ca-pemstore
            configMap:
              name: ca-pemstore
