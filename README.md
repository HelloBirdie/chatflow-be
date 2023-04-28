# ChatFlow-be


# Environment Config
* Java 17
* SpringBoot 3.0.6
* Gradle
* Docker compose
* PostgreSQL
* Lombok
* Mapstruct
* Spring Data JPA
* H2 in-memory DB
* Flyway
* Makefile

## Database
### PostgreSQL is being applied here, and we are also using Docker Compose to make local development convenient
* When running for the first time, initialise the Docker dependencies using the below make target:
  ```shell
  make app_local_compose_up
  ```
* Afterwards, Docker dependencies can be started using the below target:
  ```shell
  make app_local_compose_start
  ```
* To stop the Docker dependencies and **keep data**, run the below target:
  ```shell
  make app_local_compose_stop
  ```
* To completely tear-down the Docker dependencies, run:
  ```shell
  make app_local_compose_down
  ```

## Building
* Run the below command under the root directory
  ```shell
  make app_local_build 
  ```

## Unit Testing
* Run unit tests by using the command shown
  ```shell
  make app_local_test
  ```

## Running
* Have PostgresSQL up and running by docker-compose before running the application
* Run the application by typing below command:
  ```shell
  make app_local_run
  ```
## Additional Information
* Makefile is used for central command management, if any new command needs to be used in the future, please add it to `Makefile` with alias
* As we are using Flyway for database migration, if there is any change for database needed please add new script under **db/migration** folder to make it done, don't modify existing scripts!!!
