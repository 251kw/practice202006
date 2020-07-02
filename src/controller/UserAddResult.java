package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBUserAddInput;

/**
 * Servlet implementation class UserAdd
 */
@WebServlet("/uar")
public class UserAddResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddResult() {
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

		DBUserAddInput dnu = new DBUserAddInput();
		String icon = (String) request.getParameter("icon");
		String loginId = (String) request.getParameter("sloginId");
		String userName = (String) request.getParameter("suserName");
		String password = (String) request.getParameter("spassword");
		String profile = (String) request.getParameter("sprofile");
		dnu.setNewUser(loginId, password, userName, icon, profile);

		request.setAttribute("userName", userName);
		request.setAttribute("loginId", loginId);
		request.setAttribute("password", password);
		request.setAttribute("icon", icon);
		request.setAttribute("profile", profile);
		RequestDispatcher dispatcher = request.getRequestDispatcher("userAddResult.jsp");
		dispatcher.forward(request, response);
	}

}
