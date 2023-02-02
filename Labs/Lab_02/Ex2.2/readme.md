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






