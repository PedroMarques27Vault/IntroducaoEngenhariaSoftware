
###########################################################################Exercicio 2.1#############################################################################

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


###########################################################################Exercicio 2.2#############################################################################
#Working with jetty

>Follow tutorial on 
https://examples.javacodegeeks.com/enterprise-java/jetty/embedded-jetty-server-example/


Dependencies:
<dependencies>
    <dependency>
        <groupId>org.eclipse.jetty</groupId>
        <artifactId>jetty-server</artifactId>
        <version>9.2.15.v20160210</version>
    </dependency>
    <dependency>
        <groupId>org.eclipse.jetty</groupId>
        <artifactId>jetty-servlet</artifactId>
        <version>9.2.15.v20160210</version>
    </dependency>
</dependencies>

#Simple Embedded Jetty Server
Create a java source file under src->main->java as EmbeddedJettyExample.java .
	

import org.eclipse.jetty.server.Server;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class EmbeddedJettyExample {
 
        public static void main(String[] args) throws Exception {
              Server server = new Server(8680);
              try {
         server.start();
         server.dumpStdErr();
             server.join();
          } catch (Exception e) {           
            e.printStackTrace();
          }  
        }
}

>Resultado =  HTTP ERROR: 404
		Problem accessing /. Reason:
		    Not Found
		Powered by Jetty://

#Embedded Jetty Server with ServletHandler
package IES.Lab02.EmbeddedServer.jetty_app;
import org.eclipse.jetty.server.Server;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.eclipse.jetty.servlet.ServletHandler;
	 
public class EmbeddedJettyExample {
 
    public static void main(String[] args) throws Exception {
         
        Server server = new Server(8682);       
         
        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);
                 
        servletHandler.addServletWithMapping(HelloServlet.class, "/");
         
        server.start();
        server.join();
 
    }
     
    public static class HelloServlet extends HttpServlet 
    {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>New Hello Simple Servlet</h1>"); 
               } 
        }
	 
}




>Aceder a localhost:8682




###########################################################################Exercicio 2.3#############################################################################
#Base maven project for app with spring boot
https://start.spring.io/
Adicionar dependencia Spring Web
#Run:
	$ mvn install -DskipTests && java -jar target\webapp1-0.0.1-SNAPSHOT.jar
	or
	$./mvnw spring-boot:run

#Access  http://localhost:8080/
Whitelabel Error Page
	This application has no explicit mapping for /error, so you are seeing this as a fallback.
	Sat Oct 31 17:02:15 WET 2020
	There was an unexpected error (type=Not Found, status=404).
	

#Numa abordagem com spring, HTTP requests são feitos por um controller
>Selecionar spring initializr dependencies: Thymeleaf e SpringWeb

package IES.Lab02.o;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class GreetingController {
    @GetMapping("/greeting")		//Http requests to "/greeting" are mapped to greeting()
    public String greeting(@RequestParam(name="name", required=false, defaultValue = "World") String name, Model model){	//@requestParam binds query string 'name' to String name, default value = "World"
        model.addAttribute("name", name);
        return "greeting";				//HTML file name

    }
}

# Spring Initializr creates an application class for you that needs to further modifications
@SpringBootApplication
public class OApplication {

	public static void main(String[] args) {
		SpringApplication.run(OApplication.class, args);
	}

}

#Running the app and jar
>If you use Maven, you can run the application by using ./mvnw spring-boot:run. 
Alternatively, you can build the JAR file with 
./mvnw clean package 
and then run the JAR file, as follows:
java -jar target/filename.jar

#Add a home page
Static resources, including HTML and JavaScript and CSS, can be served from your Spring Boot application by dropping them into the right place in the source code. 
By default, Spring Boot serves static content from resources in the classpath at /static (or /public). 
The index.html resource is special because, if it exists, it is used as a welcome page







#Restful Web Service
>Returns a json object as such:

	{
	    "id": 1,					#unique identifier for greeting
	    "content": "Hello, World!"		#textual representation of greeting
	}

>Datamodel:
	public class Greeting {
		private final long id;
		private final String content;

		public Greeting(long id, String content) {
			this.id = id;
			this.content = content;
		}
		public long getId() {
			return id;
		}
		public String getContent() {
			return content;
		}
	}


>Another Greeting Controller:
#The implementation of the method body creates and returns a new Greeting object with 
#id and content attributes based on the next value from the counter and 
#formats the given name by using the greeting template.

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController				<NOTE: No Longer just a controller>
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}

#curl -v http://localhost:8080/greeting?name=Pedro
Return value: JSON DATA
	*   Trying 127.0.0.1:8080...
	* TCP_NODELAY set
	* Connected to localhost (127.0.0.1) port 8080 (#0)
	> GET /greeting?name=Pedro HTTP/1.1
	> Host: localhost:8080
	> User-Agent: curl/7.68.0
	> Accept: */*
	> 
	* Mark bundle as not supporting multiuse
	< HTTP/1.1 200 
	< Content-Type: application/json
	< Transfer-Encoding: chunked
	< Date: Sat, 31 Oct 2020 19:40:42 GMT
	< 
	* Connection #0 to host localhost left intact
	{"id":3,"content":"Hello, Pedro!"}



### Web applications in Java: Stand-Alone Applications or Embedded
#Info retirada de 
#https://stackoverflow.com/questions/20736356/embedded-vs-stand-alone-tomcat-http-server
#https://www.theserverside.com/definition/embedded-Tomcat

Spring Boot is a popular Java-based framework to develop microservices. By default, the Spring Tool Suite will automatically create an embedded server with the microservices developed each time a build or deployment occurs. 
Additional features are built into STS to facilitate microservices-based development, including the ability to set breakpoints and subsequently step through code as it executes on the embedded Tomcat server.

**Embedded Application Servers** 
single Java web application along with a full server distribution, packaged together and compressed into a single JAR, WAR or ZIP file.
Useful when you treat your application as an OS process and it will be started with something like java -jar youapp.jar. 
Such applications can be run by the end user without needing extra installation and configuration of an app server.
When deploying on cloud services, wrapping the app server within your jar eliminates the need to get the server installed on such cloud boxes.
Single web app runs on a given embedded server. 
With an embedded Tomcat server, the ratio between the server and the application is 1-to-1.
All the files associated with both the server and deployed application are compressed into a single archive file, typically with a .zip, .jar or .war extension.

Each server requires its own Java process and additional threads handle incoming requests. 
A Java process requires more processing power than a single thread of execution. 
As a result, an embedded server requires more resources to run when compared to a standalone server that hosts multiple applications.
With an embedded instance, individual applications can be taken offline or restarted without affecting others.

**Standalone Application Servers** 
In a traditional setup, an enterprise would use a single, standalone Tomcat server and deploy all Java web applications to that one instance. 
That one application server would then be clustered and scaled both horizontally and vertically to manage throughput and capacity. 
The cardinality between the Tomcat server and the applications deployed to it is always 1-to-many.
A standalone server is a server that runs alone and is not a part of a group. 
Performance and correctness: servers like nginx are highly optimized and tested for complete HTTP correctness, which your app then gets for free.


#Spring Convention over configuration
#From https://docs.spring.io/spring-framework/docs/3.0.0.M3/spring-framework-reference/html/ch16s10.html
Sticking to established conventions and having reasonable defaults is just what a lot of projects. This theme of convention-over-configuration now has support in Spring Web MVC. If you establish a set of naming conventions and suchlike, you can substantially cut down on the amount of configuration that is required to set up handler mappings, view resolvers, ModelAndView instances, etc. This is a great boon with regards to rapid prototyping, and can also lend a degree of (always good-to-have) consistency across a codebase should you choose to move forward with it into production.

#Annotations:
#From http://zetcode.com/springboot/annotations/
    @Bean - indicates that a method produces a bean to be managed by Spring.
    @Service - indicates that an annotated class is a service class.
    @Repository - indicates that an annotated class is a repository, which is an abstraction of data access and storage.
    @Configuration - indicates that a class is a configuration class that may contain bean definitions.
    @Controller - marks the class as web controller, capable of handling the requests.
    @RequestMapping - maps HTTP request with a path to a controller method.
    @Autowired - marks a constructor, field, or setter method to be autowired by Spring dependency injection.
    @SpringBootApplication - enables Spring Boot autoconfiguration and component scanning.
    
@Bean, @Repository and @RequestMapping are transitively included in the @SpringBootApplication?







	
