package connecter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 検索入力画面から掲示板に移動するために経由するサーブレット
 */
@WebServlet("/TurnBoardTop")
public class TurnBoardTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TurnBoardTopServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		// チェックボックスの保持を一旦リセット
		String[] select = null;
		session.setAttribute("select", select);

		// 掲示板に移動
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("boardTop.jsp");
		dispatcher.forward(request,response);
	}

}