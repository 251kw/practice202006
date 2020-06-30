package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.UserDTO;

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
		// TODO Auto-generated method stub
		;	// ログインユーザ情報、書き込み内容管理クラス
		boolean result;

		//DBManagerのgetEndUserメソッドを実行、DBに登録する
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("userDTO");

		//dbmでDBmanagerをインスタンス化
		dbm = new DBManager();
		result = dbm.getEndUser(user);
		request.setAttribute("result", result);

		//cookieをすべて削除(明示的にクッキーの内容を削除する手段は無い→
		//既存のクッキーを有効期間がゼロ秒のクッキーに変更する事で、ブラウザ側で破棄の処理を行うように促す。)
		Cookie cookies[] = ((HttpServletRequest)request).getCookies();
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				// クッキーの有効期間を0秒に設定する
				cookies[i].setMaxAge(0);
	                        ((HttpServletResponse)response).addCookie(cookies[i]);
			}
		}
		//insertConpに処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("insertComp.jsp");
		dispatcher.forward(request, response);
	}

}
