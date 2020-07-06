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
 * Servlet implementation class MultiDeleteUserResult
 */
@WebServlet("/multiDeleteUserResult")
public class MultiDeleteUserResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     *  セッションに入っているdeleteUserを削除する
     */
    public MultiDeleteUserResult() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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

		for (String log : loginIds) {
			if(log.equals(loginUser.getLoginId())) {
				request.setAttribute("alert", loginUser.getLoginId());
			}
		}

		String sql = MakeSelectSQL.makeSelects(loginIds);
		ArrayList<UserDTO> deleteUser = dbm.getSearchUserList(sql);
		//削除結果に表示するデータをリクエストスコープにいれる
		request.setAttribute("deleteUser", deleteUser);

		//DELETE文の実行メソッド
		sql = MakeSelectSQL.makeDeletes(loginIds);
		dbm.deleteUsers(sql);

		//処理の転送先を.jspに指定deleteUserResult
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("multiDeleteUserResult.jsp");
		dispatcher.forward(request, response);
	}

}
