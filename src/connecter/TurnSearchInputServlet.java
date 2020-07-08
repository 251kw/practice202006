package connecter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 検索入力画面に移動するために経由するサーブレット
 */
@WebServlet("/TurnSearchInput")
public class TurnSearchInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TurnSearchInputServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("LoginTop.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 検索入力画面の値を格納する変数
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");

		// 最初はnullなので空文字に変換
		if(sloginId==null ) {
			sloginId = "";
			suserName = "";
			sicon = "";
			sprofile = "";
		}

		request.setAttribute("sloginId", sloginId);
		request.setAttribute("suserName", suserName);
		request.setAttribute("sicon", sicon);
		request.setAttribute("sprofile", sprofile);

		// 検索入力画面に移動
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("UserSearchInput.jsp");
		dispatcher.forward(request,response);
	}

}
