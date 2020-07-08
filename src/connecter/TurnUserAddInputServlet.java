package connecter;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 新規登録画面用のJSPに移行するためのサーブレット
 */
@WebServlet("/TurnUserAddInput")
public class TurnUserAddInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TurnUserAddInputServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("LoginTop.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 新規登録画面に出力する変数
		String newloginId = "";
		String newpassword = "";
		String newuserName = "";
		String newicon = "";
		String newprofile = "";

		// リクエストスコープに保存
		request.setAttribute("newloginId", newloginId);
		request.setAttribute("newpassword", newpassword);
		request.setAttribute("newuserName", newuserName);
		request.setAttribute("newicon", newicon);
		request.setAttribute("newprofile", newprofile);

		// 新規登録画面に移動
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("UserAddInput.jsp");
		dispatcher.forward(request,response);
	}

}
