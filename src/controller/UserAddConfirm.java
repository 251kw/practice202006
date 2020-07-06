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
 * Servlet implementation class Register2
 */
@WebServlet("/rst2")
public class UserAddConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAddConfirm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 送られてきた情報をデータベースに登録する
		 * 値の保持のためにリクエストスコープに情報を送る
		 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String loginId = (String)request.getParameter("loginId");
		String password = (String)request.getParameter("password");
		String userName = (String)request.getParameter("userName");
		String icon = (String)request.getParameter("icon");
		String profile = (String)request.getParameter("profile");

		// １度だけ DataManager オブジェクトを生成
		if (dbm == null) {
			dbm = new DBManager();
		}

		//ユーザーを新規登録
		dbm.setNewUser(loginId, password, userName, icon, profile);

		//値の保持のため
		request.setAttribute("loginId", loginId);
		request.setAttribute("password", password);
		request.setAttribute("userName", userName);
		request.setAttribute("icon", icon);
		request.setAttribute("profile", profile);

		//jspに処理を転送
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("UserAddResult.jsp");
		dispatcher.forward(request, response);

	}

}
