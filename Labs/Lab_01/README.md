# IES
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false


Build Project:
mvn package
java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App



Como usar logging: Log4j2

Dependência Adicionada a Pom.xml
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.8.2</version>
</dependency>

Criar file no classpath designado log4j2.xml e adicionar:
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="INFO">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>

Tipos de Appenders:
ConsoleAppender – logs messages to the System console
FileAppender – writes log messages to a file
RollingFileAppender – writes the messages to a rolling log file
		    - o ficheiro é cleaned e rescrito
			
	<Appenders>
    <RollingFile name="RollingFileAppender" fileName="logs/app.log"
      filePattern="logs/$${date:yyyy-MM}/app-%d{MM-dd-yyyy}-%i.log.gz">
        <PatternLayout>
            <Pattern>%d [%t] %p %c - %m%n</Pattern>
        </PatternLayout>
        <Policies>
            <OnStartupTriggeringPolicy />
            <TimeBasedTriggeringPolicy />
            <SizeBasedTriggeringPolicy size="50 MB" />
        </Policies>
        <DefaultRolloverStrategy max="20" />
    </RollingFile>
</Appenders>

JDBCAppender – uses a relational database for logs
	-  write logs to a relational database
<Appenders>
    <JDBC name="JDBCAppender" tableName="logs">
        <DataSource jndiName="java:/comp/env/jdbc/LoggingDataSource" />
        <Column name="date" isEventTimestamp="true" />
        <Column name="logger" pattern="%logger" />
        <Column name="level" pattern="%level" />
        <Column name="message" pattern="%message" />
        <Column name="exception" pattern="%ex{full}" />
    </JDBC>
</Appenders>


AsyncAppender – contains a list of other appenders and determines the logs for these to be written in a separate thread


Maven Phase:
	A Maven phase represents a stage in the Maven build lifecycle. Each phase is responsible for a specific task.
	Examples:
		validate: check if all information necessary for the build is available
		compile: compile the source code
		test-compile: compile the test source code
		test: run unit tests
		package: package compiled source code into the distributable format (jar, war, …)
		integration-test: process and deploy the package if needed to run integration tests
		install: install the package to a local repository
		deploy: copy the package to the remote repository


Maven Goal
	Each phase is a sequence of goals, and each goal is responsible for a specific task.
When we run a phase – all goals bound to this phase are executed in order.
	Phases and default goals bound to them:
		compiler:compile – the compile goal from the compiler plugin is bound to the compile phase
		compiler:testCompile is bound to the test-compile phase
		surefire:test is bound to test phase
		install:install is bound to install phase
		jar:jar and war:war is bound to package phase






DOCKER-----------------------------------------------------
docker run hello-world
docker image ls -> Lists images in pc
docker ps --all

Why do we require volumes for Docker?
On deleting containers, volumes will not get deleted.
Attach or detach volume to the containers.
Share volumes (storage/data) among different containers.
Decoupling containers from storage.
Store volume on remote host or cloud.
Build and test image:

docker build --tag bulletinboard:1.0  -> Builds image under this name

docker run --publish 8000:8080 --detach --name bb bulletinboard:1.0

INSTALL:
	$ npm install
	$ node server.js

Stop a running cpontainer:
	$ docker rm --force bb

Instalar o portainer
	$ docker volume create portainer_data
	$ docker run -d -p 8000:8000 -p 9000:9000 --name=portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce

Dockerize PostgreSQL
	$touch Dockerfile
		Conteudo: 
////////////////
#
# example Dockerfile for https://docs.docker.com/engine/examples/postgresql_service/
#

FROM ubuntu:16.04

# Add the PostgreSQL PGP key to verify their Debian packages.
# It should be the same key as https://www.postgresql.org/media/keys/ACCC4CF8.asc
RUN apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys B97B0AFCAA1A47F044F244A07FCC7D46ACCC4CF8

# Add PostgreSQL's repository. It contains the most recent stable release
#  of PostgreSQL.
RUN echo "deb http://apt.postgresql.org/pub/repos/apt/ precise-pgdg main" > /etc/apt/sources.list.d/pgdg.list

# Install ``python-software-properties``, ``software-properties-common`` and PostgreSQL 9.3
#  There are some warnings (in red) that show up during the build. You can hide
#  them by prefixing each apt-get statement with DEBIAN_FRONTEND=noninteractive
RUN apt-get update && apt-get install -y python-software-properties software-properties-common postgresql-9.3 postgresql-client-9.3 postgresql-contrib-9.3

# Note: The official Debian and Ubuntu images automatically ``apt-get clean``
# after each ``apt-get``

# Run the rest of the commands as the ``postgres`` user created by the ``postgres-9.3`` package when it was ``apt-get installed``
USER postgres

# Create a PostgreSQL role named ``docker`` with ``docker`` as the password and
# then create a database `docker` owned by the ``docker`` role.
# Note: here we use ``&&\`` to run commands one after the other - the ``\``
#       allows the RUN command to span multiple lines.
RUN    /etc/init.d/postgresql start &&\
    psql --command "CREATE USER docker WITH SUPERUSER PASSWORD 'docker';" &&\
    createdb -O docker docker

# Adjust PostgreSQL configuration so that remote connections to the
# database are possible.
RUN echo "host all  all    0.0.0.0/0  md5" >> /etc/postgresql/9.3/main/pg_hba.conf

# And add ``listen_addresses`` to ``/etc/postgresql/9.3/main/postgresql.conf``
RUN echo "listen_addresses='*'" >> /etc/postgresql/9.3/main/postgresql.conf

# Expose the PostgreSQL port
EXPOSE 5432

# Add VOLUMEs to allow backup of config, logs and databases
VOLUME  ["/etc/postgresql", "/var/log/postgresql", "/var/lib/postgresql"]

# Set the default command to run when starting the container
CMD ["/usr/lib/postgresql/9.3/bin/postgres", "-D", "/var/lib/postgresql/9.3/main", "-c", "config_file=/etc/postgresql/9.3/main/postgresql.conf"]

//////////////////
Build an Image:
	$docker build -t eg_postgresql .
Run in foreground:
	$ docker run --rm -P --name pg_test eg_postgresql
	
2o terminal:
	1a opcao:
		docker run --rm -t -i --link pg_test:pg eg_postgresql bash
		
		
		postgres@7ef98b1b7243:/$ psql -h $PG_PORT_5432_TCP_ADDR -p $PG_PORT_5432_TCP_PORT -d docker -U docker --password
		
	2a opcao:
		docker ps
		psql -h localhost -p 49153 -d docker -U docker --password
		
Depois podemos testar a database:
		$ docker=# CREATE TABLE cities (
		docker(#     name            varchar(80),
		docker(#     location        point
		docker(# );
		CREATE TABLE
		$ docker=# INSERT INTO cities VALUES ('San Francisco', '(-194.0, 53.0)');
		INSERT 0 1
		$ docker=# select * from cities;
		     name      | location
		---------------+-----------
		 San Francisco | (-194,53)
		(1 row)
		
		
		
		
Create page using flask:
Dockerfile:
	FROM python:3.7-alpine
	WORKDIR /code
	ENV FLASK_APP=app.py
	ENV FLASK_RUN_HOST=0.0.0.0
	RUN apk add --no-cache gcc musl-dev linux-headers
	COPY requirements.txt requirements.txt
	RUN pip install -r requirements.txt
	EXPOSE 5000
	COPY . .
	CMD ["flask", "run"]



Docker Compose.yml
	version: "3.8"
	services:
	  web:
	    build: .
	    ports:
	      - "5000:5000"
	    volumes:
	      - .:/code
	    environment:
	      FLASK_ENV: development
	  redis:
	    image: "redis:alpine"

		----> Para as atalizações serem instantaneas
		

docker container ls --all

	CONTAINER ID        IMAGE                    COMMAND             CREATED             STATUS                  PORTS                                            NAMES
	d9099fd109cd        portainer/portainer-ce   "/portainer"        2 days ago          Up 3 minutes            0.0.0.0:8000->8000/tcp, 0.0.0.0:9000->9000/tcp   portainer
	658d562ca468        hello-world              "/hello"            2 days ago          Exited (0) 2 days ago                                                    wizardly_bohr
	34bd9d3dbd12        hello-world              "/hello"            2 days ago          Exited (0) 2 days ago                                                    dreamy_austin

		
		
		
