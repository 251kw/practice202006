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
 * multiDELETEUserConfirm.jspの削除確定ボタンからの呼び出され
 * 送られてきたloginIdをもとにuserテーブルから
 * ユーザーを1件削除する
 */
@WebServlet("/deleteUserResult")
public class DeleteUserResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteUserResultServlet() {
        super();

    }

	/**
	 *  直接アクセスがあった場合は indexInput.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("indexInput.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * multiDELETEUserConfirm.jspの削除確定ボタンからの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("loginId"); //indexで送った値

		//DELETE文の実行メソッド
		DBManager dbm = new DBManager();
		//削除結果に表示するデータをリクエストスコープにいれる
		UserDTO deleteUser = dbm.getUser(loginId);
		request.setAttribute("deleteUser", deleteUser);
		dbm.deleteUserShout(loginId);
		dbm.deleteUser(loginId);

		//処理の転送先を.jspに指定deleteUserResult
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("deleteUserResult.jsp");
		dispatcher.forward(request, response);
	}

}
