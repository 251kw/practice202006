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
 * Servlet implementation class MultiDeleteUSerComfirmServlet
 */
@WebServlet("/multiDeleteUSerComfirmServlet")
public class MultiDeleteUserComfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MultiDeleteUserComfirmServlet() {
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
	 * SearchResultの選択削除からの呼び出し
	 * 受け取ったloginIdをUserDTOのリストに詰める
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("user"); //ログインユーザー
		request.setCharacterEncoding("UTF-8");
		DBManager dbm = new DBManager();

		String[] loginIds = request.getParameterValues("checked");
		request.setAttribute("checkUsers", loginIds);

		//ログインIDのチェックボックスが１つ以上選択されているか
		if(loginIds != null) {
			//選択されたユーザーの情報を取得
			String sql = MakeSelectSQL.makeSelects(loginIds);
			ArrayList<UserDTO> deleteUser = dbm.getSearchUserList(sql);

			//取得したユーザー情報をリクエストスコープに入れる
			request.setAttribute("deleteUser",deleteUser);

			//現在ログインしているユーザーが選択されていないか調べる
			for (String log : loginIds) {
				if(log.equals(loginUser.getLoginId())) {
					String message = "現在ログイン中のユーザーが含まれるため削除を実行すると、自動的にログアウトします";
					request.setAttribute("alert", message);
				}
			}
			//multiDeleteUserConfirm.jspに転送先を指定
			dispatcher = request.getRequestDispatcher("multiDeleteUserConfirm.jsp");
		}else {
			//１つも選択されていなければリクエストスコープに検索ユーザーを再セットしてsearchResult.jspに処理を転送
			SearchUserBean sUser = (SearchUserBean)session.getAttribute("sUser");
			String sql = MakeSelectSQL.makeSelect(sUser.getLoginId(), sUser.getUserName(), sUser.getProfile(), sUser.getCar(), sUser.getClip(), sUser.getRadio());
			ArrayList<UserDTO> searchUser = dbm.getSearchUserList(sql);
			request.setAttribute("searchUser", searchUser);

			String alert = "ユーザーが選択されていません";
			request.setAttribute("alert", alert);

			dispatcher = request.getRequestDispatcher("searchResult.jsp");
		}

		dispatcher.forward(request, response);
	}

}
