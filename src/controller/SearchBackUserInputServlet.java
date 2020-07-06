package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SearchResult.jspから戻るボタンでの呼び出され
 * searchInput.jspに処理を戻す
 */
@WebServlet("/searchBack")
public class SearchBackUserInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchBackUserInputServlet() {
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
	 * SearchResult.jspから戻るボタンでの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		request.setCharacterEncoding("UTF-8");

		// searchInput.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("searchInput.jsp");
		dispatcher.forward(request, response);
	}

}
