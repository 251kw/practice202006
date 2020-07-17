package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBUserAddInput;
import util.ErrorCheck;

/**
 * Servlet implementation class NewUser
 * 新規ユーザー登録の情報管理
 */
@WebServlet("/nu")
public class UserAddConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAddConfirm() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * getで呼ばれた場合
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 *userAddInput.jspから”登録”ボタンで呼出し
	 *登録情報のチェックを行う
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//送信データの取得
		String userName = request.getParameter("userName");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		//情報をセット
		request.setAttribute("userName", userName);
		request.setAttribute("loginId", loginId);
		request.setAttribute("password", password);
		request.setAttribute("icon", icon);
		request.setAttribute("profile", profile);

		String message = null;

		DBUserAddInput dnu = new DBUserAddInput();
		ErrorCheck ec = new ErrorCheck();

		int flag = 0;

		//入力画面の入力必須項目の空白をチェック
		if (loginId.equals("") || password.equals("") || userName.equals("")) {
			message = "ログインIDとパスワード、名前は必須入力です";
			//エラーメッセージをオブジェクトに保存
			request.setAttribute("alert", message);

			flag = 1;

			//新規登録画面に転送
			RequestDispatcher dispatcher = request.getRequestDispatcher("userAddInput.jsp");
			dispatcher.forward(request, response);

		}else {
			//半角チェック
			if (flag != 1) {
				if (ec.halfCheck(loginId)) {

				} else {
					message = "半角英数字で入力してください";
					//エラーメッセージをオブジェクトに保存
					request.setAttribute("alert2", message);

					//新規登録画面に転送
					RequestDispatcher dispatcher = request.getRequestDispatcher("userAddInput.jsp");
					dispatcher.forward(request, response);

				}
			}

			//ログインIDに重複がないかチェック
			if (flag != 1) {
				if (dnu.UserCheck(loginId)) {
					message = "このログインIDは既に使用されています";
					//エラーメッセージをオブジェクトに保存
					request.setAttribute("alert", message);

					//新規登録画面に転送
					RequestDispatcher dispatcher = request.getRequestDispatcher("userAddInput.jsp");
					dispatcher.forward(request, response);
				} else {
					//確認画面へ
					RequestDispatcher dispatcher = request.getRequestDispatcher("userAddConfirm.jsp");
					dispatcher.forward(request, response);
				}

			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("userAddInput.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
