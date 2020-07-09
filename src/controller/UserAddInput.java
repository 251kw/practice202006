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
 * 入力された情報を確認画面に持っていく
 * IDかぶりや、未記入などもはじく
 * top.jspから送られる
 * add_confirm.jspに返す
 */
@WebServlet("/rst")
public class UserAddInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserAddInput() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		//値の保持のため送信
		request.setAttribute("loginId", loginId);
		request.setAttribute("password", password);
		request.setAttribute("userName", userName);
		request.setAttribute("icon", icon);
		request.setAttribute("profile", profile);

		RequestDispatcher dispatcher = null;
		String message = null;
		DBManager dbm = new DBManager();
		UserDTO check = dbm.checkUser(loginId);

		if (loginId.equals("") || password.equals("")) {
			//未入力
			message = "*ログインIDとパスワードは必須です";

			//エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			//jspに処理を転送
			dispatcher = request.getRequestDispatcher("add_input.jsp");
			dispatcher.forward(request, response);
		} else if (check != null) {
			//Idかぶり
			message = "*このIDは使われています";

			//エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			//jspに処理を転送
			dispatcher = request.getRequestDispatcher("add_input.jsp");
			dispatcher.forward(request, response);
		} else {
			UserDTO user = null;
			user = new UserDTO();
			user.setLoginId(loginId);
			user.setPassword(password);
			user.setUserName(userName);
			user.setIcon(icon);
			user.setProfile(profile);

			request.setAttribute("user", user);

			//jspに処理を転送
			dispatcher = request.getRequestDispatcher("add_confirm.jsp");
			dispatcher.forward(request, response);
		}

	}

}
