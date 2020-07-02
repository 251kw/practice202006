package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.UserDTO;

/**
 * Servlet implementation class DeleteUserResult
 */
@WebServlet("/deleteUserResult")
public class DeleteUserResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserResult() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("loginId"); //indexで送った値

		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("user"); //ログインユーザー
		if(!loginId.equals(loginUser.getLoginId())) {
			request.setAttribute("output", "output");
		}

		//DELETE文の実行メソッド
		DBManager dbm = new DBManager();
		//削除結果に表示するデータをリクエストスコープにいれる
		UserDTO deleteUser = dbm.getUser(loginId);
		request.setAttribute("deleteUser", deleteUser);
		dbm.deleteUser(loginId);

		//処理の転送先を.jspに指定deleteUserResult
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("deleteUserResult.jsp");
		dispatcher.forward(request, response);
	}

}
