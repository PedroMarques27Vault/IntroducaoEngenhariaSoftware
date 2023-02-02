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

