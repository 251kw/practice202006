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
 * Servlet implementation class UppdateUserInsertUserConfirmServlet
 */
@WebServlet("/uppdateUserInsertUserConfirmServlet")
public class UppdateUserInsertUserConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UppdateUserInsertUserConfirmServlet() {
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

	//updateuserConfirm.jspの更新確定ボタンからの呼び出し
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		request.setCharacterEncoding("UTF-8");

		//入力情報の取得
		String originalLoginId = request.getParameter("originalLoginId");
		String loginID = request.getParameter("loginID");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String userName = request.getParameter("userName");
		String profile = request.getParameter("profile");

		//UserDTOにset
		UserDTO udto;
		udto = new UserDTO(loginID, password, userName,  icon,  profile);

		//DBに接続しuserテーブルを更新
		DBManager dbm = new DBManager();
		dbm.uppdateUser(udto,originalLoginId);

		//もし更新したユーザーがログイン中のユーザーならログインユーザーの変更処理
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("user");
		if(originalLoginId.equals(loginUser.getLoginId())) {
			session.setAttribute("user", udto); //ログインユーザー
		}

		//udtoをリクエストスコープに入れる
		request.setAttribute("user", udto);

		dispatcher = request.getRequestDispatcher("updateUserResult.jsp");
		dispatcher.forward(request, response);
	}

}
