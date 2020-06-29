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
import dao.ErrorCheck;

/**
 * Servlet implementation class NewUser
 */
@WebServlet("/nu")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		//String loginId = request.getParameter("loginId");
		String loginId = (String) session.getAttribute("loginId");
		String password = (String) session.getAttribute("password");
		String userName = (String) session.getAttribute("userName");
		String icon = (String) session.getAttribute("icon");
		String profile = (String) session.getAttribute("profile");

		String message = null;

		DBNewUser dnu = new DBNewUser();
		ErrorCheck ec = new ErrorCheck();

		int flag = 0;

		if (loginId.equals("") || password.equals("") || userName.equals("")) {
			message = "ログインIDとパスワード、名前は必須入力です";
			//エラーメッセージをオブジェクトに保存
			request.setAttribute("alert", message);

			flag = 1;

			//新規登録画面に転送
			RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
			dispatcher.forward(request, response);

		}
		//半角チェック
		if (ec.halfCheck(loginId)) {

		}else {
			message = "半角英数字で入力してください";
			//エラーメッセージをオブジェクトに保存
			request.setAttribute("alert2", message);

			//新規登録画面に転送
			RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
			dispatcher.forward(request, response);

		}

		//ログインIDに重複がないかチェック
		if (flag != 1) {
			if (dnu.UserCheck(loginId)) {
				message = "このログインIDは既に使用されています";
				//エラーメッセージをオブジェクトに保存
				request.setAttribute("alert", message);

				//新規登録画面に転送
				RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
				dispatcher.forward(request, response);
			} else {
				dnu.setNewUser(loginId, password, userName, icon, profile);

				RequestDispatcher dispatcher = request.getRequestDispatcher("complete.jsp");
				dispatcher.forward(request, response);
			}

		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
			dispatcher.forward(request, response);
		}
	}

}
