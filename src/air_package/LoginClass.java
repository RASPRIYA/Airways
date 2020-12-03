package air_package;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginClass
 */
@WebServlet("/LoginClass")
public class LoginClass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get information
		String uname=request.getParameter("uname");
		String psw=request.getParameter("psw");
		PreparedStatement pst=null;
		ResultSet rs=null;
		PrintWriter out=response.getWriter();
			
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con;
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","halsey","Halsey@2000");
			String sql = "SELECT * FROM login_info1 WHERE username=? and password=?";
			pst = con.prepareStatement(sql);
		    pst.setString(1, uname);
		    pst.setString(2, psw);
		    rs = pst.executeQuery();
		    if (rs.next()){
		    	response.sendRedirect("http://localhost:8082/Airways/Airways_main.html");
		    }
		    else {
		    	out.println("Invalid username or password");
		    }
		}
		catch(Exception e) {
			out.println(e);
		}
		
		
	}

}
