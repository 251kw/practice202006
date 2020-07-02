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
 * Servlet implementation class SearchFiristUserInputServlet
 */
@WebServlet("/searchFirst")
public class SearchFiristUserInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFiristUserInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//直接アクセスがあった場合はindexInput.jspに処理を転送
				RequestDispatcher dispatcher = request.getRequestDispatcher("indexInput.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
