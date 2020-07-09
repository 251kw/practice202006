package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.ShoutDTO;
import dto.UserDTO;

/**
 * ログインサーブレット
 * doGet
 * doPost
 * @author y.sato
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	/**
	 *  直接アクセスがあった場合は index.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * index.jspから呼び出される
	 * doPostメソッド
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = null;
		String botton = request.getParameter("btn");

		if (botton.equals("ログイン")) {

			String loginId = request.getParameter("loginId");
			String password = request.getParameter("password");

			String message = null;

			if (loginId.equals("") || password.equals("")) {
				//ログインIDとパスワードどちらか、もしくは双方未入力なら
				message = "ログインIDとパスワードは必須入力です";

				//エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				request.setAttribute("id", loginId);
				request.setAttribute("pass", password);

				//index.jspに処理を転送
				dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);

			} else {
				//ログイン認証を行い、ユーザー情報を取得
				DBManager dbm = new DBManager();
				UserDTO user = dbm.getLoginUser(loginId, password);

				if (user != null) {
					//ユーザー情報を取得出来たら、書き込み内容リストを取得
					ArrayList<ShoutDTO> list = dbm.getShoutList();
					HttpSession session = request.getSession();

					//ログインユーザー情報、書き込み内容リストとしてセッションに保存
					session.setAttribute("user", user);
					session.setAttribute("shouts", list);

					//処理の転送先をtop.jspに指定
					dispatcher = request.getRequestDispatcher("top.jsp");

				} else {
					//ユーザー情報が取得できない場合
					//エラーメッセージをリクエストオブジェクトの保存
					message = "ログインIDまたはパスワードが違います";
					request.setAttribute("alert", message);
					request.setAttribute("id", loginId);
					request.setAttribute("pass", password);

					//処理の転送先をindex.jspに指定
					dispatcher = request.getRequestDispatcher("index.jsp");
				}
				//処理を転送
				dispatcher.forward(request, response);

			}

		} else if (botton.equals("新規登録")) {
			//初回のテキストボックスに
			request.setAttribute("loginId", "");
			request.setAttribute("pass", "");
			request.setAttribute("userName", "");
			request.setAttribute("icon", "");
			request.setAttribute("profile", "");

			dispatcher = request.getRequestDispatcher("signup_input.jsp");
			dispatcher.forward(request, response);

		}

	}

}
