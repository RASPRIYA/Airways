package air_package;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getting information;
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		String gender=request.getParameter("gender");
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String psw=request.getParameter("psw");
		PrintWriter out=response.getWriter();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con;
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","halsey","Halsey@2000");
			
			
			Statement statement=con.createStatement();
			statement.executeUpdate("insert into login_info1 values('"+name+"',"+age+",'"+gender+"','"+username+"','"+email+"','"+psw+"')");
			response.sendRedirect("http://localhost:8086/Airways/LoginPage.html");
			con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
}

