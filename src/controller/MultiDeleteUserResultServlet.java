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
import dto.UserDTO;
import util.MakeSelectSQL;

/**
 * セッションに入っているdeleteUserを削除する
 */
@WebServlet("/multiDeleteUserResult")
public class MultiDeleteUserResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MultiDeleteUserResultServlet() {
        super();
    }

	/**
	 *  直接アクセスがあった場合は index.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * セッションに入っているdeleteUserを削除する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] loginIds = request.getParameterValues("loginId");
		DBManager dbm = new DBManager();
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("user"); //ログインユーザー
		request.setAttribute("alert", "");

		//ログインしているユーザーが含まれていればリクエストスコープにalertをセット
		for (String log : loginIds) {
			if(log.equals(loginUser.getLoginId())) {
				request.setAttribute("alert", loginUser.getLoginId());
			}
		}

		//Shoutsの削除
		String sql = MakeSelectSQL.makeDeletesShoutslogIds(loginIds);
		dbm.deleteSQL(sql);

		//削除結果に表示するデータをリクエストスコープにいれる
		sql = MakeSelectSQL.makeSelects(loginIds);
		ArrayList<UserDTO> deleteUser = dbm.getSearchUserList(sql);
		request.setAttribute("deleteUser", deleteUser);

		//usersの削除
		sql = MakeSelectSQL.makeDeletes(loginIds);
		dbm.deleteSQL(sql);

		//処理の転送先を.jspに指定deleteUserResult
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("multiDeleteUserResult.jsp");
		dispatcher.forward(request, response);
	}

}