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
import util.Check;


/**
 * 新規登録入力サーブレット
 * doGet
 * doPost
 * @author y.sato
 */
@WebServlet("/sui")
public class SignUpInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpInputServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 直接アクセスがあった場合に起動する
	 * doGetメソッド
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * signup_input.jspから呼び出される
	 * doPostメソッド
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();
		String botton = request.getParameter("btn");
		String message = null;

		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String pass = request.getParameter("pass");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		UserDTO newuser = new UserDTO(loginId, pass, userName, icon, profile);

		if (botton.equals("確認画面へ")) {
			request.setAttribute("newuser", newuser);

			if (loginId.equals("") || pass.equals("")) {
				//ログインIDとパスワードどちらか、もしくは双方未入力なら
				message = "ログインIDとパスワードは必須入力です";

				//エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				message = "";

				//signup_input.jspに処理を転送
				dispatcher = request.getRequestDispatcher("signup_input.jsp");

			} else if(Check.checkLogic(loginId)==false) {
				message = "ログインIDに使用できるのは半角英数字のみです。";

				//エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				message = "";

				//signup_input.jspに処理を転送
				dispatcher = request.getRequestDispatcher("signup_input.jsp");

			} else if (dbm.SameCheckId(loginId)) {
				//signup_confirm.jspに処理を転送
				dispatcher = request.getRequestDispatcher("signup_confirm.jsp");

			} else {
				message = "このログインIDは既に使われています。";
				request.setAttribute("alert", message);
				message = "";

				//signup_input.jspに処理を転送
				dispatcher = request.getRequestDispatcher("signup_input.jsp");
			}

		} else if (botton.equals("登録する")) {
			request.setAttribute("newuser", newuser);

			dbm.SignUp(newuser);	//新規登録メソッド
			//登録結果画面へ
			dispatcher = request.getRequestDispatcher("signup_result.jsp");

		} else if(botton.equals("戻る")) {
			dispatcher = request.getRequestDispatcher("signup_input.jsp");
		}
		//jspに送る
		request.setAttribute("loginId",loginId);
		request.setAttribute("password",pass);
		request.setAttribute("userName",userName);
		request.setAttribute("icon",icon);
		request.setAttribute("profile",profile);

		dispatcher.forward(request, response);

	}

}
