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
 * updateuserConfirm.jspの更新確定ボタンからの呼び出され
 * loginIdをもとに入力された情報に更新する
 */
@WebServlet("/uppdateUserInsertUserConfirmServlet")
public class UppdateUserInsertUserConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UppdateUserInsertUserConfirmServlet() {
        super();
    }

	/**
	 *  直接アクセスがあった場合は index.jsp  に処理を転送
	 *  s
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * updateuserConfirm.jspの更新確定ボタンからの呼び出し
	 */
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
		udto = new UserDTO(loginID, password, userName,  icon,  profile,0);

		//DBに接続しuserテーブルを更新
		DBManager dbm = new DBManager();
		dbm.uppdateUser(udto,originalLoginId);
		dbm.uppdateShouts(udto);

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
