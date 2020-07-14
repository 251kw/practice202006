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

import bean.SearchUserBean;
import dao.DBManager;
import dto.UserDTO;
import util.MakeSelectSQL;

/**
 * 検索結果に戻るときに呼び出される
 * セッションスコープのsql文を再利用するため
 * ユーザーを削除・更新した場合でも正しく表示可能
 */
@WebServlet("/returnSearchResult")
public class ReturnSearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReturnSearchResultServlet() {
        super();
    }

	/**
	 *  直接アクセスがあった場合は index.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 検索結果に戻るときに呼び出される
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();

		//検索時に作成したＳＱＬ文を再利用し、現在のデータを取得
		SearchUserBean sUser = (SearchUserBean)session.getAttribute("sUser");
		String sql = MakeSelectSQL.makeSelect(sUser.getLoginId(), sUser.getUserName(), sUser.getProfile(), sUser.getCar(), sUser.getClip(), sUser.getRadio());
		DBManager dbm = new DBManager();
		ArrayList<UserDTO> searchUser = dbm.getSearchUserList(sql);

		//検索結果が0件なら処理をsearchEmptyResult.jspに転送
		if(searchUser.isEmpty()) {
			dispatcher = request.getRequestDispatcher("searchEmptyResult.jsp");
		}else {
			dispatcher = request.getRequestDispatcher("searchResult.jsp");
		}

		//チェックボックスの有無の確認と処理
		String[] cks = request.getParameterValues("checkUsers");
		if(cks == null) {
			ArrayList<String> checkUsers = new ArrayList<String>();
			checkUsers.add("");
			request.setAttribute("checkUsers", checkUsers);
		}else {
			request.setAttribute("checkUsers", cks);
		}

		request.setAttribute("searchUser", searchUser);

		dispatcher.forward(request, response);
	}

}