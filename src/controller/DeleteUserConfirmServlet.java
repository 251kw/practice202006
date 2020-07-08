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
import dto.UserDTO;

/**
 * Servlet implementation class DeleteUserInputServlet
 */
@WebServlet("/deleteUser")
public class DeleteUserConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * ユーザー検索で一件削除をする際に呼び出される
     * 削除予定のユーザーをSQLから呼び出し
     * deleteUserConfirm.jspに処理を転送
     */
    public DeleteUserConfirmServlet() {
        super();

    }

	/**
	 * 直接アクセスがあった場合は index.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * ユーザー検索で一件削除をする際に呼び出される
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		HttpSession session = request.getSession();

		//ログインユーザー情報、書き込み内容リストを取得
		UserDTO loginUser = (UserDTO)session.getAttribute("user"); //ログインユーザー

		//削除予定のuserの取得
		DBManager dbm = new DBManager();
		UserDTO deleteUser = dbm.getUser(loginId);
		if(loginId.equals(loginUser.getLoginId())) {
			String message = "このユーザーは現在ログイン中のユーザーのため削除を実行すると、自動的にログアウトします";
			request.setAttribute("alert", message);
		}

		//削除予定のユーザー情報をリクエストスコープに入れる
		request.setAttribute("deleteUser", deleteUser);

			//処理の転送先をdeleteUserConfirm.jspに指定
			RequestDispatcher dispatcher;
			dispatcher = request.getRequestDispatcher("deleteUserConfirm.jsp");
			dispatcher.forward(request, response);
	}

}
