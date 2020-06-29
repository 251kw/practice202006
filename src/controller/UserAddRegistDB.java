package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Register2Servlet
 */
@WebServlet("/register2")
public class UserAddRegistDB extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddRegistDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("index.jsp");

		Connection conn = null;
		PreparedStatement pstmt1 = null;

		HttpSession session = request.getSession();
		String newloginId = (String)session.getAttribute("newloginId");
		String newpassword = (String)session.getAttribute("newpassword");
		String newuserName = (String)session.getAttribute("newuserName");
		String newicon = (String)session.getAttribute("newicon");
		String newprofile = (String)session.getAttribute("newprofile");


		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DSN,USER,PASSWORD);

			String sql = "INSERT INTO users(loginId,password,userName,icon,profile) VALUES(?,?,?,?,?)";

			pstmt1 = conn.prepareStatement(sql);

			pstmt1.setString(1,newloginId);
			pstmt1.setString(2,newpassword);
			pstmt1.setString(3,newuserName);
			pstmt1.setString(4,newicon);
			pstmt1.setString(5,newprofile);

			pstmt1.executeUpdate();

			dispatcher = request.getRequestDispatcher("UserAddResult.jsp");
			dispatcher.forward(request,response);

		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt1.close();
			}catch(SQLException e) {}
		}

	}
}
