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
 * 登録確認画面から新規登録情報入力画面へ移動する
 * 値を保存して入力画面へ戻る
 */
@WebServlet("/TurnInput")
public class TurnInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TurnInputServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		// 入力画面に値を保持するための処理
		String newloginId = request.getParameter("newloginId");
		String newpassword = request.getParameter("newpassword");
		String newuserName = request.getParameter("newuserName");
		String newicon = request.getParameter("newicon");
		String newprofile = request.getParameter("newprofile");

		request.setAttribute("newloginId", newloginId);
		request.setAttribute("newpassword", newpassword);
		request.setAttribute("newuserName", newuserName);
		request.setAttribute("newicon", newicon);
		request.setAttribute("newprofile", newprofile);

		// チェックボックスの保持を一旦リセット
		String[] select = null;
		session.setAttribute("select", select);

		// 入力画面へ移動
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("userAddInput.jsp");
		dispatcher.forward(request,response);
	}

}
