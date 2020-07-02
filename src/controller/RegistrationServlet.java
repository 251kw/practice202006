package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/registraction")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;	// ログインユーザ情報、書き込み内容管理クラス

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	//insertConfの「OK」ボタンから呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DBManagerのgetEndUserメソッドを実行、DBに登録

		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		//登録結果を代入する用の変数
		boolean result;

		//dbmでDBmanagerをインスタンス化
		dbm = new DBManager();
		result = dbm.getEndUser(loginId,password,userName,icon,profile);
		request.setAttribute("result", result);

		//insertCompに処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("insertComp.jsp");
		dispatcher.forward(request, response);
	}
}
