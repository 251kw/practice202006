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
import dto.UserDTO;
import util.MakeSelectSQL;

/**
 * searchInput.jspの検索ボタンからの呼び出され
 * 送られてきた情報をもとに
 * SELECTのSQL文を作成して
 * SearchResult.jspに処理を転送する
 */
@WebServlet("/search")
public class SerachInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SerachInputServlet() {
        super();
    }

	/**
	 *  直接アクセスがあった場合は indexInput.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("indexInput.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * searchInput.jspの検索ボタンからの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		request.setCharacterEncoding("UTF-8");
		DBManager dbm = new DBManager();

		//受け取った情報をString型にいれる
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String profile = request.getParameter("profile");
		String car = request.getParameter("icon_car");
		String clip = request.getParameter("icon_paperclip");
		String radio = request.getParameter("icon_radio");

		//nullが送られてきていないか確かめて、nullだったら空文字にする
		if(car == null) {
			car = "";
		}
		if(clip == null) {
			clip = "";
		}
		if(radio == null) {
			radio = "";
		}

		//SQL文の作成メソッド
		String sql = MakeSelectSQL.makeSelect(loginId, userName, profile, car, clip, radio);
		//作成したSQLを渡しserchUserで受け取る
		ArrayList<UserDTO> searchUser = dbm.getSearchUserList(sql);
		HttpSession session = request.getSession();

		if(searchUser.isEmpty()) {
			dispatcher = request.getRequestDispatcher("SearchEmptyResult.jsp");
		}else {
			dispatcher = request.getRequestDispatcher("SearchResult.jsp");
		}

		session.setAttribute("select_loginId", loginId);
		session.setAttribute("userName", userName);
		session.setAttribute("profile",profile);
		session.setAttribute("icon_car",car);
		session.setAttribute("icon_paperclip",clip);
		session.setAttribute("icon_radio",radio);
		session.setAttribute("sql", sql);

		request.setAttribute("searchUser",searchUser );

		dispatcher.forward(request, response);
	}

}