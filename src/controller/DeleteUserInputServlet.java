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
 * Servlet implementation class DeleteUserInputServlet
 */
@WebServlet("/deleteUser")
public class DeleteUserInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserInputServlet() {
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

		//ログインユーザー情報、書き込み内容リストを取得
		UserDTO loginUser = (UserDTO)session.getAttribute("user"); //ログインユーザー

		//削除予定のuserの取得
		DBManager dbm = new DBManager();
		UserDTO deleteUser = dbm.getUser(loginId);
		if(loginId.equals(loginUser.getLoginId())) {
			String message = "このユーザーは現在ログイン中のユーザーのため削除を実行すると、自動的にログアウトします";
			request.setAttribute("alert", message);
		}

		request.setAttribute("deleteUser", deleteUser);

			//処理の転送先をtopInput.jspに指定
			RequestDispatcher dispatcher;
			dispatcher = request.getRequestDispatcher("deleteUserConfirm.jsp");
			dispatcher.forward(request, response);
	}

}
