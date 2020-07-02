package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class UpdateUserInputServlet
 */
@WebServlet("/updateUser")
public class UpdateUserInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserInputServlet() {
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
		String loginId = request.getParameter("loginId");
		DBManager dbm = new DBManager();
		UserDTO updateUser = dbm.getUser(loginId);

		request.setAttribute("updateUser",updateUser );

		request.setAttribute("loginID",updateUser.getLoginId());
		request.setAttribute("password",updateUser.getPassword());
		request.setAttribute("icon",updateUser.getIcon());
		request.setAttribute("userName",updateUser.getUserName());
		request.setAttribute("profile",updateUser.getProfile());

		//処理の転送先を.jspに指定
				RequestDispatcher dispatcher;
				dispatcher = request.getRequestDispatcher("updateUserInput.jsp");
				dispatcher.forward(request, response);

	}

}
