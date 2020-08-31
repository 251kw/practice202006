package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBUserAddInput;

/**
 * Servlet implementation class UserAdd
 * 結果画面への処理
 */
@WebServlet("/uar")
public class UserAddResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddResult() {
        super();
    }

	/**
	 * userAddConfirm.jspから"はい"で呼出し
	 * 登録情報を受け取り、usrAddResualt,jspへ遷移
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		//送信データの取得
		DBUserAddInput dnu = new DBUserAddInput();
		String icon = (String) request.getParameter("icon");
		String loginId = (String) request.getParameter("sloginId");
		String userName = (String) request.getParameter("suserName");
		String password = (String) request.getParameter("spassword");
		String profile = (String) request.getParameter("sprofile");

		//新規ユーザー情報のセット
		dnu.setNewUser(loginId, password, userName, icon, profile);

		//結果画面へ遷移
		request.setAttribute("userName", userName);
		request.setAttribute("loginId", loginId);
		request.setAttribute("password", password);
		request.setAttribute("icon", icon);
		request.setAttribute("profile", profile);
		RequestDispatcher dispatcher = request.getRequestDispatcher("userAddResult.jsp");
		dispatcher.forward(request, response);
	}

}
