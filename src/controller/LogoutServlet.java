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
 * セッションを破棄してログアウトし、ログイン画面に戻る
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogoutServlet() {
        super();
    }

    // 直接アクセスがあった場合はLoginTop.jspに処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("LoginTop.jsp");
			dispatcher.forward(request, response);
	}

	// BoardTop.jspの「ログアウト」ボタンから呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// セッションを破棄
		HttpSession session = request.getSession();
		session.invalidate();

		// doGetメソッドを呼び出し、LoginTop.jspに処理を転送
		doGet(request,response);
	}

}
