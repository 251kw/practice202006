package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;

/**
 * Servlet implementation class Register2
 */
@WebServlet("/rst2")
public class Register2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		String loginId = (String)session.getAttribute("loginId");
		String password = (String)session.getAttribute("password");
		String userName = (String)session.getAttribute("userName");
		String icon = (String)session.getAttribute("icon");
		String profile = (String)session.getAttribute("profile");

		// １度だけ DataManager オブジェクトを生成
		if (dbm == null) {
			dbm = new DBManager();
		}

		dbm.setNewUser(loginId, password, userName, icon, profile);

		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//jspに処理を転送
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("new3.jsp");
		dispatcher.forward(request, response);

	}

}
