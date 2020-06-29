package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBNewUser;

/**
 * Servlet implementation class UserAdd
 */
@WebServlet("/ua")
public class UserAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAdd() {
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
		//文字化け対策
				request.setCharacterEncoding("UTF-8");
		DBNewUser dnu = new DBNewUser();
		HttpSession session = request.getSession();
		String icon = (String) session.getAttribute("icon");
		String loginId = (String) session.getAttribute("loginId");
		String userName = (String) session.getAttribute("userName");
		String password = (String) session.getAttribute("password");
		String profile = (String) session.getAttribute("profile");
		dnu.setNewUser(loginId, password, userName, icon, profile);
		RequestDispatcher dispatcher = request.getRequestDispatcher("complete.jsp");
		dispatcher.forward(request, response);
	}

}
