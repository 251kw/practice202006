package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DBManager;
import dto.ShoutDTO;

/**
 *  ユーザー検索中のTOP移動時に呼び出され
 *  セッションスコープに空文字を詰めて
 *  top.jspに処理を転送する
 */
@WebServlet("/returnTop")
public class ReturnTopConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReturnTopConfirmServlet() {
        super();
    }

	/**
	 * 直接アクセスがあった場合は index.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * ユーザー検索中のTOP移動時に呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();
		HttpSession session = request.getSession();

		//空文字をリクエストスコープにset
		session.setAttribute("select_loginId", "");
		session.setAttribute("icon_car","");
		session.setAttribute("icon_paperclip","");
		session.setAttribute("icon_radio","");
		session.setAttribute("userName","");
		session.setAttribute("profile","");

		session.setAttribute("sql", "");

		ArrayList<ShoutDTO> list = dbm.getShoutList();  //DBM内にあるメソッド
		session.setAttribute("shouts", list); //叫びのリスト

		// input.jsp に処理を転送(必要な情報をリクエストスコープへ)
		String ck = "on";
		request.setAttribute("ck", ck);
		ArrayList<String> checkShouts = new ArrayList<String>();
		checkShouts.add("");
		request.setAttribute("checkShouts", checkShouts);
		dispatcher = request.getRequestDispatcher("topInput.jsp");
		dispatcher.forward(request, response);
	}

}
