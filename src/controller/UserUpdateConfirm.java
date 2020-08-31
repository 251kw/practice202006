package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserUpdateConfirm
 * 更新確認画面への処理
 */
@WebServlet("/uuc")
public class UserUpdateConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateConfirm() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * userUpdateInput.jspから遷移
	 * 送信された情報のエラーチェックをして確認画面へ送る
	 */
	/* (非 Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		// 送信データの取得
		String userName = request.getParameter("userName");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String sicon = request.getParameter("sicon");
		String profile = request.getParameter("profile");
		//ログインユーザーのログインID
		String sloginId = request.getParameter("sloginId");

		request.setAttribute("userName", userName);
		request.setAttribute("loginId", loginId);
		request.setAttribute("password", password);
		request.setAttribute("sicon", sicon);
		request.setAttribute("profile", profile);
		//ログインユーザーのログインID
		request.setAttribute("sloginId", sloginId);

		RequestDispatcher dispatcher = null;

		//更新確認画面へ
		dispatcher = request.getRequestDispatcher("userUpdateConfirm.jsp");
		dispatcher.forward(request, response);

	}

}
