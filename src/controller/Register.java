package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserDTO;

/**
 * Servlet implementation class Register
 */
@WebServlet("/rst")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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

		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("pass");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("remarks");

		RequestDispatcher dispatcher = null;
		String message = null;

		if (loginId.equals("") || password.equals("") || userName.equals("") || profile.equals("")) {
			//未入力
			message = "*入力もれがあります";

			//エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			//jspに処理を転送
			dispatcher = request.getRequestDispatcher("new.jsp");
			dispatcher.forward(request, response);
		} else
			// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();

		UserDTO user = null;
		user = new UserDTO();
		user.setLoginId(loginId);
		user.setPassword(password);
		user.setUserName(userName);
		user.setIcon(icon);
		user.setProfile(profile);

		session.setAttribute("user", user);

		session.setAttribute("loginId", loginId);
		session.setAttribute("password", password);
		session.setAttribute("userName", userName);
		session.setAttribute("icon",icon);
		session.setAttribute("profile", profile);


		//jspに処理を転送
		dispatcher = request.getRequestDispatcher("new2.jsp");
		dispatcher.forward(request, response);

	}

}
