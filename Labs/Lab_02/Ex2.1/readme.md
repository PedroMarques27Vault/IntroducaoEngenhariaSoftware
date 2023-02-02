#Tomcat:
	sudo systemctl start tomcat
	sudo systemctl status tomcat
	
#Deploy into localhost:8080/manager
	MVN INSTALL -> target -> file.war
	
	check page http://localhost:8080/file
	
#Server Logs:
	/opt/tomcat/logs
	
	ex:
		"29-Oct-2020 09:18:33.530 INFO [http-nio-8080-exec-3] org.apache.catalina.startup.HostConfig.deployWAR Deploying web application archive [/opt/tomcat/webapps/Lab_02-IEs.war]"
		
		
#Visual Studio:
	Classes Java: adicionar pasta "java" em src/main

#Servlet imports:
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import java.util.Date;	
	
#Basic Class: 
	import java.io.IOException;
	import java.io.PrintWriter;
	
	@WebServlet(name = "MyFirstServlet", urlPatterns = {"/MyFirstServlet"})
	public class MyFirstServlet extends HttpServlet {
	 
	    private static final long serialVersionUID = -1915463532411657451L;
	 
	    @Override
	    protected void doGet(HttpServletRequest request,
		    HttpServletResponse response) throws ServletException, IOException 
	    {
		//Do some work
	    }
	     
	    @Override
	    protected void doPost(HttpServletRequest request,
		    HttpServletResponse response) throws ServletException, IOException {
		//Do some other work
	    }
	}
	
	
#Passar Parametros No URL:
	uri?param="xyz"
	request.getParameter(param) -> returns xyz
	
	
	
	

#Correr com o VSCode:
	MVN INSTALL
	Tomcat Server -> "+" e selecionar pasta /opt/tomcat/
	right click-> debug war package
	
	
#Exception Adicionada: Numero passado como parâmetro pode ser nulo
	Mensagem no localhost:

		HTTP Status 500 – Internal Server Error
		Type Exception Report
		Message For input string: ""
		Description The server encountered an unexpected condition that prevented it from fulfilling the request.
		Exception
		java.lang.NumberFormatException: For input string: ""
			java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
			java.base/java.lang.Integer.parseInt(Integer.java:662)
			java.base/java.lang.Integer.valueOf(Integer.java:983)
			Servlet.doGet(Servlet.java:25)
			javax.servlet.http.HttpServlet.service(HttpServlet.java:626)
			javax.servlet.http.HttpServlet.service(HttpServlet.java:733)
			org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
		Note The full stack trace of the root cause is available in the server logs.


#Deploy Web App into Docker
1) clone a tomcat image in your local machine from Docker Hub.
	docker image pull tomcat:8.0
	docker image ls # list all images in your docker
2) create and start a tomcat container from the image
	docker container create --publish 8082:8080 --name my-tomcat-container tomcat:8.0
	docker container ls -a # it will list all the containers
	docker container start my-tomcat-container

#Tomcat application can be accessed in http://localhost:8082

#Outras notas:
	# to get inside your docker tomcat container directory...
	docker container exec -it my-tomcat-container bash
	# it will list tomcat directory inside your docker as
	# :/usr/local/tomcat# ls
	# LICENSE  NOTICE  RELEASE-NOTES RUNNING.txt  bin  conf include  lib # logs  native-jni-lib  temp  webapps  work

#create a docker image file for .war and deploy it in docker
	Create Dockerfile
		FROM tomcat:8.0
		MAINTAINER admin
		# COPY  path-to-your-application-war path-to-webapps-in-docker-tomcat 
		COPY target/Lab_02-IEs.war /opt/tomcat/webapps/


#Build docker image file for your application from Dockerfile
	# docker image build -t your_name/some-app location_of_dockerfile
	docker image build -t admin/tabuada ./
		Sending build context to Docker daemon  50.18kB
		Step 1/3 : FROM tomcat:8.0
		 ---> ef6a7c98d192
		Step 2/3 : MAINTAINER admin
		 ---> Running in e10f8a607749
		Removing intermediate container e10f8a607749
		 ---> c49585368813
		Step 3/3 : COPY target/Lab_02-IEs.war /opt/tomcat/webapps/
		 ---> 8d2a6be4701f
		Successfully built 8d2a6be4701f
		Successfully tagged admin/tabuada:latest

	# now if you check list of images in your docker, you will see as
	# your_name/some-app-image image in it...
	docker image ls
	REPOSITORY               TAG                 IMAGE ID            CREATED             SIZE
	admin/tabuada            latest              8d2a6be4701f        38 seconds ago      356MB
	bulletinboard            1.0                 310546878d37        13 days ago         184MB
	node                     current-slim        c0f0d070c334        2 weeks ago         167MB
	portainer/portainer-ce   latest              a0a227bf03dd        2 months ago        196MB
	hello-world              latest              bf756fb1ae65        10 months ago       13.3kB
	tomcat                   8.0                 ef6a7c98d192        2 years ago         356MB


# creating and running a container
docker container run -it --publish 8081:8080 your_name/some-app-image

Available in http://localhost:8081

>Servlet Containers > responsável pela criação, execução e destruição de servlets
#Log catalina.out
NOTE: Picked up JDK_JAVA_OPTIONS:  --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.io=ALL-UNNAMED --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED
01-Nov-2020 10:47:40.414 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server version name:   Apache Tomcat/9.0.39
01-Nov-2020 10:47:40.422 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server built:          Oct 6 2020 14:11:46 UTC
01-Nov-2020 10:47:40.423 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Server version number: 9.0.39.0
01-Nov-2020 10:47:40.423 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log OS Name:               Linux
01-Nov-2020 10:47:40.423 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log OS Version:            5.4.0-52-generic
01-Nov-2020 10:47:40.423 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Architecture:          amd64
01-Nov-2020 10:47:40.423 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Java Home:             /usr/lib/jvm/java-11-openjdk-amd64
01-Nov-2020 10:47:40.423 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log JVM Version:           11.0.8+10-post-Ubuntu-0ubuntu120.04
01-Nov-2020 10:47:40.424 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log JVM Vendor:            Ubuntu
01-Nov-2020 10:47:40.424 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log CATALINA_BASE:         /opt/tomcat
01-Nov-2020 10:47:40.424 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log CATALINA_HOME:         /opt/tomcat
01-Nov-2020 10:47:40.447 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: --add-opens=java.base/java.lang=ALL-UNNAMED
01-Nov-2020 10:47:40.447 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: --add-opens=java.base/java.io=ALL-UNNAMED
01-Nov-2020 10:47:40.450 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: --add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED
01-Nov-2020 10:47:40.450 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.util.logging.config.file=/opt/tomcat/conf/logging.properties
01-Nov-2020 10:47:40.451 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager
01-Nov-2020 10:47:40.451 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.awt.headless=true
01-Nov-2020 10:47:40.451 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.security.egd=file:/dev/./urandom
01-Nov-2020 10:47:40.451 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djdk.tls.ephemeralDHKeySize=2048
01-Nov-2020 10:47:40.451 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.protocol.handler.pkgs=org.apache.catalina.webresources
01-Nov-2020 10:47:40.451 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dorg.apache.catalina.security.SecurityListener.UMASK=0027
01-Nov-2020 10:47:40.452 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Xms512M
01-Nov-2020 10:47:40.452 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Xmx1024M
01-Nov-2020 10:47:40.452 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -XX:+UseParallelGC
01-Nov-2020 10:47:40.452 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dignore.endorsed.dirs=
01-Nov-2020 10:47:40.452 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dcatalina.base=/opt/tomcat
01-Nov-2020 10:47:40.452 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Dcatalina.home=/opt/tomcat
01-Nov-2020 10:47:40.453 INFO [main] org.apache.catalina.startup.VersionLoggerListener.log Command line argument: -Djava.io.tmpdir=/opt/tomcat/temp
01-Nov-2020 10:47:40.455 INFO [main] org.apache.catalina.core.AprLifecycleListener.lifecycleEvent The Apache Tomcat Native library which allows using OpenSSL was not found on the java.library.path: [/usr/java/packages/lib:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib]
01-Nov-2020 10:47:40.940 INFO [main] org.apache.coyote.AbstractProtocol.init Initializing ProtocolHandler ["http-nio-8080"]
01-Nov-2020 10:47:40.976 INFO [main] org.apache.catalina.startup.Catalina.load Server initialization in [917] milliseconds
01-Nov-2020 10:47:41.043 INFO [main] org.apache.catalina.core.StandardService.startInternal Starting service [Catalina]
01-Nov-2020 10:47:41.044 INFO [main] org.apache.catalina.core.StandardEngine.startInternal Starting Servlet engine: [Apache Tomcat/9.0.39]
01-Nov-2020 10:47:41.064 INFO [main] org.apache.catalina.startup.HostConfig.deployWAR Deploying web application archive [/opt/tomcat/webapps/Lab_02-IEs.war]
01-Nov-2020 10:47:41.420 INFO [main] org.apache.catalina.startup.HostConfig.deployWAR Deployment of web application archive [/opt/tomcat/webapps/Lab_02-IEs.war] has finished in [355] ms
01-Nov-2020 10:47:41.421 INFO [main] org.apache.catalina.startup.HostConfig.deployDirectory Deploying web application directory [/opt/tomcat/webapps/ROOT]
01-Nov-2020 10:47:41.447 INFO [main] org.apache.catalina.startup.HostConfig.deployDirectory Deployment of web application directory [/opt/tomcat/webapps/ROOT] has finished in [26] ms
01-Nov-2020 10:47:41.447 INFO [main] org.apache.catalina.startup.HostConfig.deployDirectory Deploying web application directory [/opt/tomcat/webapps/examples]
01-Nov-2020 10:47:41.714 INFO [main] org.apache.catalina.startup.HostConfig.deployDirectory Deployment of web application directory [/opt/tomcat/webapps/examples] has finished in [266] ms
01-Nov-2020 10:47:41.714 INFO [main] org.apache.catalina.startup.HostConfig.deployDirectory Deploying web application directory [/opt/tomcat/webapps/host-manager]
01-Nov-2020 10:47:41.747 INFO [main] org.apache.catalina.startup.HostConfig.deployDirectory Deployment of web application directory [/opt/tomcat/webapps/host-manager] has finished in [33] ms
01-Nov-2020 10:47:41.747 INFO [main] org.apache.catalina.startup.HostConfig.deployDirectory Deploying web application directory [/opt/tomcat/webapps/docs]
01-Nov-2020 10:47:41.766 INFO [main] org.apache.catalina.startup.HostConfig.deployDirectory Deployment of web application directory [/opt/tomcat/webapps/docs] has finished in [19] ms
01-Nov-2020 10:47:41.766 INFO [main] org.apache.catalina.startup.HostConfig.deployDirectory Deploying web application directory [/opt/tomcat/webapps/manager]
01-Nov-2020 10:47:41.791 INFO [main] org.apache.catalina.startup.HostConfig.deployDirectory Deployment of web application directory [/opt/tomcat/webapps/manager] has finished in [24] ms
01-Nov-2020 10:47:41.797 INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["http-nio-8080"]
01-Nov-2020 10:47:41.822 INFO [main] org.apache.catalina.startup.Catalina.start Server startup in [845] milliseconds


#what are the responsibilities/services of a “servlet container”?
#From https://dzone.com/articles/what-servlet-container and http://r4r.in/servlet/interview-question-answers/?request_id=3-3&question_id=465
the basic idea of servlet container is using java to dynamically generate the web page on the server side. so servlet container is essentially a part of a web server that interacts with the servlets. 



Communication Support:- The servlet container provide the medium for inter servlet comunications and to the clints its browsers also. 
Lifecycle and Resource Management: The life cyclee and all releated methods and instances get persed and handled by the web container by itself . The web container also provides utility like JNDI for resource pooling and management.
Multithreading Support: Container creates new thread for every request to the servlet and provide them request and response objects to process the request. So servlets are not initialized for each request. It shares the common one time memory for each request and saves time and memory.
JSP Support: JSPs doesn�t look like normal java classes. It looks like an HTML document but every JSP in the application is compiled by container and converted to Servlet and then container manages them like other servlets and get inter linked between the servlets by the web.xml deployer.








	
