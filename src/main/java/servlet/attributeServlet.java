import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

// Import Java Libraries
import java.io.*;
import java.util.Enumeration;

@WebServlet(
	name = "MyServlet",
	urlPatterns = {"/attributeServlet"}
)

public class attributeServlet extends HttpServlet
{
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
   // Get session object
   HttpSession session = request.getSession();

   String name1 = request.getParameter("attrib_name1");
   String name2 = request.getParameter("attrib_name2");
   String value1 = request.getParameter("attrib_value1");
   String value2 = request.getParameter("attrib_value2");
   String remove = request.getParameter("attrib_remove");

   if (remove != null && remove.equals("on"))
   {
      session.removeAttribute(name1);
      session.removeAttribute(name2);
   }
   else
   {
      if ((name1 != null && name1.length() > 0) && (value1 != null && value1.length() > 0))
      {
         session.setAttribute(name1, value1);
      }
      if ((name2 != null && name2.length() > 0) && (value1 != null && value2.length > 0))
      {
	 session.setAttribute(name2, value2);
      }

   }

   response.setContentType("text/html");
   PrintWriter out = response.getWriter();

   out.println("<html>");
   // no-cache lets the page reload by clicking on the reload link
   out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
   out.println("<head>");
   out.println(" <title>Session lifecycle</title>");
   out.println("</head>");
   out.println("");

   out.println("<body>");
   out.println("<h1><center>Session attributes</center></h1>");

   out.println("Enter name and value for both attributes");

   // String url = response.encodeURL ("offutt/servlet/attributeServlet");
   String url = response.encodeURL("attributeServlet");
   out.println("<form action=\"" + url + "\" method=\"GET\">");
   out.println(" Name: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_name1\">");

   out.println(" Value: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_value1\">");

   out.println("<br>Name: ");
   out.println("<input type=\"text\" size=\"10\" name =\"attrib_name2\"");

   out.println(" Value: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_value2\">");

   out.println(" <br><input type=\"checkbox\" name=\"attrib_remove\">Remove");
   out.println(" <input type=\"submit\" name=\"update\" value=\"Update\">");
   out.println("</form>");
   out.println("<hr>");

   out.println("Attributes in this session:");
   Enumeration e = session.getAttributeNames();
   while (e.hasMoreElements())
   {
      String att_name  = (String) e.nextElement();
      String att_value = (String) session.getAttribute(att_name);

      out.print  ("<br><b>Name:</b> ");
      out.println(att_name);
      out.print  ("<br><b>Value:</b> ");
      out.println(att_value);
   } //end while

   out.println("</body>");
   out.println("</html>");
   out.close();
} // End doGet
} //End  SessionLifeCycle
