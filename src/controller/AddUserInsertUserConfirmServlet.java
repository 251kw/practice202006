package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

/**
 * addUserComfirm.jspから登録確定ボタンでの呼び出され
 * 入力された情報をuserテーブルに追加する
 *
 */
@WebServlet("/InsertUser")
public class AddUserInsertUserConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddUserInsertUserConfirmServlet() {
        super();

    }

	/**
	 * 直接アクセスがあった場合は indexInput.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("indexInput.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * addUserComfirm.jspから登録確定ボタンでの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		request.setCharacterEncoding("UTF-8");

		//入力情報の取得
		String loginID = request.getParameter("loginID");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String userName = request.getParameter("userName");
		String profile = request.getParameter("profile");

		//UserDTOにset
		UserDTO udto;
		udto = new UserDTO(loginID, password, userName,  icon,  profile);

		//udtoをリクエストスコープに入れる
		request.setAttribute("user", udto);

		//DBに接続しuserテーブルに追加
		DBManager dbm = new DBManager();
		dbm.setUser(udto);
		dispatcher = request.getRequestDispatcher("addUserResult.jsp");
		dispatcher.forward(request, response);
	}

}
