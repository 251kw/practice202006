package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ログアウト時に呼び出される
 * sesstitoを破棄し、indexに処理を転送する
 */
@WebServlet("/logout")
public class LogoutTopResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutTopResultServlet() {
        super();
    }

	/**
	 * 直接アクセスがあった場合はindex.jspに処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * ログアウトする場合に呼び出される
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションを破棄
		HttpSession session = request.getSession();
		session.invalidate();

		//doGetメソッドを呼び出し、index.jspに処理転送
		doGet(request, response);
	}

}
