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
 * top.jspにユーザー検索からの呼び出され
 * リクエストスコープに空文字を詰め
 * searchInput.jspに処理を転送
 */
@WebServlet("/searchFirst")
public class SearchFiristUserInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchFiristUserInputServlet() {
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
	 * top.jspにユーザー検索からの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();

		//空文字をリクエストスコープにset
		session.setAttribute("select_loginId", "");
		session.setAttribute("icon_car","");
		session.setAttribute("icon_paperclip","");
		session.setAttribute("icon_radio","");
		session.setAttribute("userName","");
		session.setAttribute("profile","");
		// input.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("searchInput.jsp");
		dispatcher.forward(request, response);
	}

}
