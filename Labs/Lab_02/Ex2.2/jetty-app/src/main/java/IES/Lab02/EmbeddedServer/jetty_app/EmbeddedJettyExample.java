package IES.Lab02.EmbeddedServer.jetty_app;
import org.eclipse.jetty.server.Server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
    @WebServlet(name = "Tabuada", urlPatterns = {"/Tabuada"})
    public static class HelloServlet extends HttpServlet 
    {
    	private static final long serialVersionUID = -1915463532411657451L;
    	 
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
        	 String username = request.getParameter("username");
             if (username==null){
                 username="Guest";
             }
             Integer a;
             String A = request.getParameter("A");
             try{
                 a = Integer.valueOf(A);
             }catch(Exception e){
                 a=null;
             }
             
            
             response.setContentType("text/html;charset=UTF-8");
             PrintWriter out = response.getWriter();
             try {
                 // Write some content
                 out.println("<html>");
                 out.println("<head>");
                 out.println("<title>CalendarServlet</title>");
                 out.println("</head>");
                 out.println("<body>");
                 out.println("<h2>Hello " + username + "</h2>");
                 if (a!=null)
                     for (int i=1; i<11; i++)
                         out.println("<h2>" +i+"*"+a+"=" + i*a + "</h2>");
                 else
                     for (int i=1; i<11; i++)
                         for (int j=1; j<11; j++)
                             out.println("<h2>" +i+"*"+j+"=" + i*j + "</h2>");
                             out.println("<br/>");
                 out.println("</body>");
                 out.println("</html>");
             } finally {
                 out.close();
             }
        }
    }	
}