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
 * ユーザー検索結果のの更新ボタンからの呼び出され
 * "updateUserInput.jspに処理を転送する
 */
@WebServlet("/updateUser")
public class UpdateUserInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateUserInputServlet() {
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
	 * ユーザー検索結果の更新ボタンからの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		DBManager dbm = new DBManager();
		UserDTO updateUser = dbm.getUser(loginId);

		request.setAttribute("originalloginId",updateUser.getLoginId());
		request.setAttribute("loginID",updateUser.getLoginId());
		request.setAttribute("password",updateUser.getPassword());
		request.setAttribute("icon",updateUser.getIcon());
		request.setAttribute("userName",updateUser.getUserName());
		request.setAttribute("profile",updateUser.getProfile());

		//処理の転送先を.jspに指定
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("updateUserInput.jsp");
		dispatcher.forward(request, response);

	}

}
