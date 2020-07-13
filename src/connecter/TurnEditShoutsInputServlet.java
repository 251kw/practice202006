package connecter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 叫び内容編集入力画面に移行するために経由するサーブレット
 */
@WebServlet("/TurnEditShoutsInput")
public class TurnEditShoutsInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TurnEditShoutsInputServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;

		// 該当する叫びを特定するための情報を保持
		String sheloginId = request.getParameter("sheloginId");
		request.setAttribute("sheloginId", sheloginId);
		String shdloginId = request.getParameter("shdloginId");
		request.setAttribute("shdloginId", shdloginId);

		// 編集入力画面に移動
		dispatcher = request.getRequestDispatcher("editShoutsInput.jsp");
		dispatcher.forward(request,response);
	}

}
