package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * updateUserComfirm.jspのキャンセルボタンからの呼び出し
 * 必要な情報をリクエストスコープに詰めて
 * updateUserInput.jspに処理を転送する
 */
@WebServlet("/uppdateUserFailureConfirm")
public class UppdateUserFailureConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UppdateUserFailureConfirmServlet() {
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
	 * updateUserComfirm.jspのキャンセルボタンからの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		request.setCharacterEncoding("UTF-8");

		//入力情報の取得
		String loginID = request.getParameter("loginID");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String userName = request.getParameter("userName");
		String profile = request.getParameter("profile");

		//入力情報の受け渡し
		request.setAttribute("loginID",loginID);
		request.setAttribute("password",password);
		request.setAttribute("icon", icon);
		request.setAttribute("userName", userName);
		request.setAttribute("profile", profile);

		dispatcher = request.getRequestDispatcher("updateUserInput.jsp");
		dispatcher.forward(request, response);
	}

}
