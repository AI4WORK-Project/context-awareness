# AI4Work Context Awareness Module

The AI4Work Context Awareness Module

## Preconditions to build and run Context Awareness Module

To build and run Context Awareness Module, the following software is required:

- Java (at least version 11)
- Apache Maven (at least version 3.5.4)
- Docker (for running tests and deploying Context Awareness Module)
- docker-compose (for running local sample instance only)

### Maven configuration

You need to update your local settings.xml
Add the following to your local settings.xml

```
<servers>
  <server>
    <id>github</id>
    <username>YOUR_GH_USERNAME</username>
    <password>YOUR_GH_PERSONAL_ACCESS_TOKEN</password>
  </server>
</servers>
```

## How to build Context Awareness Module

Context Awareness Module can be built using maven with the following command:

  ```shell
  mvn install
  ```

In order to build and push a container image that can be deployed, the following command can be used:

  ```shell
  mvn install
  mvn jib:build -pl context-extraction -Djib.to.image="${IMAGE_NAME:IMAGE_TAG}" -Djib.to.auth.username="${CONTAINER_REGISTRY_USERNAME}" -Djib.to.auth.password="${CONTAINER_REGISTRY_TOKEN}"
  ```

## How to run Context Awareness Module

A sample configuration and docker-compose file can be found in the [samples folder](https://github.com/eclipse-opensmartclide/smartclide-context/tree/main/samples).

You can run the sample with the following command: 

   ```shell
   docker-compose -f samples/docker-compose.yml up
   ```
   
## How to configure Context Awareness Module

### Monitoring Config

**monitoring-config.xml**

An example monitoring configuration can be found here: [monitoring-config.xml](https://github.com/eclipse-opensmartclide/smartclide-context/blob/main/samples/config/monitoring-config.xml)

**monitoring-config.xsd**

The corresponding XSD file can be found here: [monitoring-config.xsd](https://github.com/eclipse-opensmartclide/smartclide-context/blob/main/samples/config/monitoring-config.xsd)

### Description

#### indexes

Each index entry has the following mandatory attributes

- id: The unique name of the index
- location: The URI of the location the index is stored

#### datasources

Each datasource entry has the following mandatory attributes

- id:The unique name of the datasource
- type:The type of the datasource. Possible values are: filesystem, webservice, database, messageBroker
- monitor:The class of the monitor to be used. Possible values are:
    - package org.eclipse.opensmartclide.context.monitoring.monitors.database.DatabaseMonitor
    - package org.eclipse.opensmartclide.context.monitoring.monitors.file.FileSystemMonitor
    - package org.eclipse.opensmartclide.context.monitoring.monitors.file.FilePairSystemMonitor
    - package org.eclipse.opensmartclide.context.monitoring.monitors.file.FileTripletSystemMonitor
    - package org.eclipse.opensmartclide.context.monitoring.monitors.webservice.MessageBrokerMonitor
    - package org.eclipse.opensmartclide.context.monitoring.monitors.webservice.WebServiceMonitor
    - package org.eclipse.opensmartclide.context.monitoring.monitors.GitlabCommitMonitor
- options: Options for the datasource can be entered using this value. The options are dependent on the datasource to be used
- uri:The uri of the data source to be monitored
- class:The following datasource implementations are available
    - package org.eclipse.opensmartclide.context.monitoring.config.models.datasources.DatabaseDataSource
    - package org.eclipse.opensmartclide.context.monitoring.config.models.datasources.FilePairSystemDataSource
    - package org.eclipse.opensmartclide.context.monitoring.config.models.datasources.FileSystemDataSource
    - package org.eclipse.opensmartclide.context.monitoring.config.models.datasources.FileTripletSystemDataSource
    - package org.eclipse.opensmartclide.context.monitoring.config.models.datasources.MessageBrokerDataSource
    - package org.eclipse.opensmartclide.context.monitoring.config.models.datasources.WebServiceDataSource
    - package org.eclipse.opensmartclide.context.monitoring.config.models.datasources.GitlabDataSource

#### interpreters

Each interpreter entry has the following mandatory attributes

- id: The unique name of the interpreter
- configuration
    - analyser: The analyser class to be used. The following implementations are available:
        - package org.eclipse.opensmartclide.context.monitoring.analyser.database.DatabaseAnalyser
        - package org.eclipse.opensmartclide.context.monitoring.analyser.file.FileAnalyser
        - package org.eclipse.opensmartclide.context.monitoring.analyser.file.FilePairAnalyser
        - package org.eclipse.opensmartclide.context.monitoring.analyser.file.FileTripletAnalyser
        - package org.eclipse.opensmartclide.context.monitoring.analyser.messagebroker.MessageBrokerAnalyser
        - package org.eclipse.opensmartclide.context.monitoring.analyser.webservice.WebServiceAnalyser
        - package org.eclipse.opensmartclide.context.monitoring.analyser.webserviceGitAnalyser
        - package org.eclipse.opensmartclide.context.monitoring.analyser.webservice.GitlabCommitAnalyser
    - parser: The parser class to be used. The following implementations are available:
        - package org.eclipse.opensmartclide.context.monitoring.parser.database.DatabaseParser
        - package org.eclipse.opensmartclide.context.monitoring.parser.file.FileParser
        - package org.eclipse.opensmartclide.context.monitoring.parser.file.FilePairParser
        - package org.eclipse.opensmartclide.context.monitoring.parser.file.FileTripletParser
        - package org.eclipse.opensmartclide.context.monitoring.parser.messagebroker.MessageBrokerParser
        - package org.eclipse.opensmartclide.context.monitoring.parser.webservice.WebServiceParser
        - package org.eclipse.opensmartclide.context.monitoring.parser.GitlabCommitParser

    - type: Currently only used for File analyser and parser. Defines the file extensions to be used.

#### monitors

Each monitor entry has the following mandatory attributes

- id: The unique name of the monitor
- datasource: The id of one previously defined datasource (see above)
- interpreter: The id of one previously defined interpreter (see above)
- index: The id of one previously defined index (see above)

