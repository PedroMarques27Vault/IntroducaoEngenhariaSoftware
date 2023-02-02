#Using Postman Utility
SpringBoot App Initialized with IntelliJ

Treat the resource like a noun, and the HTTP method as a verb. If you do it like that, you'll end up with something like this:
GET /blogposts - gets all the blog posts.
GET /blogposts/12 - gets the blog post with the id 12.
POST /blogposts - adds a new blog post and returns the details.
DELETE /blogposts/12 - removes the blog post with the id 12.
GET /authors/3/blogposts - gets all the blog posts of the author with id 3.

#Commonly seen codes include:

    200 OK 
    400 Bad Request 
    500 Internal Server Error 

    201 Created 
    204 No Content 
    401 Unauthorized 
    403 Forbidden 
    404 Not Found 

#GET Result on Postman
localhost:8080/employees
{
    "_embedded": {
        "employeeList": [
            {
                "id": 1,
                "name": "Bilbo Baggins",
                "role": "burglar",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/1"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            },
            {
                "id": 2,
                "name": "Frodo Baggins",
                "role": "thief",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/2"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees"
        }
    }
}

>RESPONSE BODY> {"_embedded":{"employeeList":[{"id":1,"name":"Bilbo Baggins","role":"burglar","_links":{"self":{"href":"http://localhost:8080/employees/1"},"employees":{"href":"http://localhost:8080/employees"}}},{"id":2,"name":"Frodo Baggins","role":"thief","_links":{"self":{"href":"http://localhost:8080/employees/2"},"employees":{"href":"http://localhost:8080/employees"}}}]},"_links":{"self":{"href":"http://localhost:8080/employees"}}}


#GET Result on Postman
localhost:8080/employees/2
{
    "id": 2,
    "name": "Frodo Baggins",
    "role": "thief",
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/2"
        },
        "employees": {
            "href": "http://localhost:8080/employees"
        }
    }
}
>RESPONSE BODY > {"id":2,"name":"Frodo Baggins","role":"thief","_links":{"self":{"href":"http://localhost:8080/employees/2"},"employees":{"href":"http://localhost:8080/employees"}}}



#POST Result on Postman
{
    "_embedded": {
        "employeeList": [
            {
                "id": 1,
                "name": "Bilbo Baggins",
                "role": "burglar",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/1"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            },
            {
                "id": 2,
                "name": "Frodo Baggins",
                "role": "thief",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/2"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            },
            {
                "id": 3,
                "name": "Smeagul",
                "role": "gollum",
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/3"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees"
        }
    }
}

>Response Body> {"_embedded":{"employeeList":[{"id":1,"name":"Bilbo Baggins","role":"burglar","_links":{"self":{"href":"http://localhost:8080/employees/1"},"employees":{"href":"http://localhost:8080/employees"}}},{"id":2,"name":"Frodo Baggins","role":"thief","_links":{"self":{"href":"http://localhost:8080/employees/2"},"employees":{"href":"http://localhost:8080/employees"}}},{"id":3,"name":"Smeagul","role":"gollum","_links":{"self":{"href":"http://localhost:8080/employees/3"},"employees":{"href":"http://localhost:8080/employees"}}}]},"_links":{"self":{"href":"http://localhost:8080/employees"}}}


#UPDATE with Post
{
        "id": 2,
        "name": "Frodo Baggins",
        "role": "ring",				<--------------------
        "_links": {
            "self": {
                "href": "http://localhost:8080/employees/2"
            },
            "employees": {
                "href": "http://localhost:8080/employees"
            }
        }
    }

Json Data Updates:
{
 ...
            {
                "id": 2,
                "name": "Frodo Baggins",
                "role": "ring",			<---------------
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/employees/2"
                    },
                    "employees": {
                        "href": "http://localhost:8080/employees"
                    }
                }
            },
        ...
}
>RESPONSE BODY> {"id":2,"name":"Frodo Baggins","role":"ring"}


#On Restart -> Updated/Added data is lost
Change method so that, on POST, data is stored to a database.
Method to change:
@PostMapping("/employees")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}
	
Next, change method initDatabase on LoadDatabase class so that it loads Database
data instead of adding the data on each app restart
Method to change:
@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {

		return args -> {
			log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
			log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
		};
	}


#When trying to access localhost:8080/employee/987987
Error 404 - Not found
"Could not find employee 987987"


#Describe the role of the elements modeled in the previous point.
**@Entity**
An entity represents a table stored in a database. Every instance of an entity represents a row in the table.
	Employee:
		>Representa uma tabela employee, com colunas name, role e id
	
**@RESTController**
The @RestController annotation was introduced in Spring 4.0 to simplify the creation of RESTful web services. It's a convenience annotation that combines @Controller and @ResponseBody – which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody annotation.
Classic controllers can be annotated with the @Controller annotation. This is simply a specialization of the @Component class and allows implementation classes to be autodetected through the classpath scanning.

@Controller is typically used in combination with a @RequestMapping annotation used on request handling methods.
	EmployeeController:
		>Mapping and request handling	
	
	
	
**@ControllerAdvice**
Specialization of the @Component annotation which allows to handle exceptions across the whole application in one global handling component. It can be viewed as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar. 
	EmployeeNotFoundAdvice
		>@ResponseBody
		 @ExceptionHandler(EmployeeNotFoundException.class)    -> EmployeeNotFoundException.java => Exception Handler
		 @ResponseStatus(HttpStatus.NOT_FOUND)
		 
		 
	 

		
LoadDatabase.java
		- Class loaded automatically by SpringBoot. All CommandLineRunner beans are run once the application context is loaded. This runner will request a copy of the EmployeeRepository and use it to create two entities (storing them).
		

- **Repositories** 
interfaces with methods supporting reading, updating, deleting, and creating records against a back end data store
	EmployeeRepository
		
	

	
###Ex3.2#################################################################################

Starting the mySQL instance with docker:

$docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=password-e MYSQL_DATABASE=demo-e MYSQL_USER=demo-e MYSQL_PASSWORD=password-p 3306:3306 -d mysql/mysql-server:5.7


#MUITO IMPORTANTE: DEFINIR CONNECTION PROPERTIES em APPLICATION.PROPERTIES
https://howtodoinjava.com/spring-boot2/datasource-configuration/#configurations
	# MySQL
	spring.datasource.url=jdbc:mysql://localhost:3306/demo
	spring.datasource.username=demo
	spring.datasource.password=password
	spring.datasource.driver-class-name=com.mysql.jdbc.Driver
	spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

	spring.jpa.hibernate.ddl-auto = update

@Table	-> refere-se às tabelas SQL. Neste projeto estamos a usar MySQL como linguagem da database. Assim sendo, utilizamos esta anotação por cima de uma classe para a representar como uma tabela.
@Column -> refere-se às colunas da tabela. Anotação colocada acima dos atributos para os indicar como colunas da tabela

@Id -> colocado em cima de um atributo para o identificar como o ID de cada row na tabela

#Fonte https://stackoverflow.com/questions/19414734/understanding-spring-autowired-usage
The @Autowired annotation allows you to skip configurations elsewhere of what to inject and just does it for you. 



###Ex3.3#################################################################################

id adicionado a partir do template

#Difference between RestController and Controller
 The @RestController annotation in Spring MVC is nothing but a combination of @Controller and @ResponseBody annotation. It was added into Spring 4.0 to make the development of RESTful Web Services in Spring framework easier. 

The job of @Controller is to create a Map of the model object and find a view but @RestController simply returns the object and object data is directly written into HTTP response as JSON or XML.

Read more: https://javarevisited.blogspot.com/2017/08/difference-between-restcontroller-and-controller-annotations-spring-mvc-rest.html#ixzz6dnblnri3




#Both the RestController and the Controllerneed to access the database, using a Repository. How do they get a valid instance of the Repository to work with?
Spring Boot automatically spins up Spring Data JPA to create a concrete implementation of the Repository and configure it to talk to a back end in-memory database by using JPA.
Spring Data REST builds on top of Spring MVC. It creates a collection of Spring MVC controllers, JSON converters, and other beans to provide a RESTful front end. These components link up to the Spring Data JPA backend. When you use Spring Boot, this is all autoconfigured. 
The Constructor on Controller has a repository as an argument. On creation, Spring Boot automatically sets an instance of the repository as constructor argument

