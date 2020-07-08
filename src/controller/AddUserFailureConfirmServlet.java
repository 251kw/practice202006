package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * addUSerConfirm/jspの戻るボタンからの呼び出し
 * 入力情報を再セットし、"addUserInput.jspに処理を転送
 */
@WebServlet("/fails")
public class AddUserFailureConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddUserFailureConfirmServlet() {
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
	 * addUserConfirm.jspの戻るボタンからの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		request.setCharacterEncoding("UTF-8");

		//入力情報の取得
		String loginID = request.getParameter("loginID");
		String icon = request.getParameter("icon");
		String userName = request.getParameter("userName");
		String profile = request.getParameter("profile");

		//入力情報の受け渡し
		request.setAttribute("loginID",loginID);
		request.setAttribute("icon", icon);
		request.setAttribute("userName", userName);
		request.setAttribute("profile", profile);

		//addUserInput.jspに処理を転送
		dispatcher = request.getRequestDispatcher("addUserInput.jsp");
		dispatcher.forward(request, response);
	}

}
