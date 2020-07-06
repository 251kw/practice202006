package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;
import util.CheckStr;

/**
 * Servlet implementation class UserSearchInput
 */
@WebServlet("/usi")
public class UserSearchInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchInput() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	/**
	 * 検索画面から送られてきた情報を取得し、結果画面に情報を送るためのクラス
	 * リクエストスコープに情報を送り、情報の保持も行う
	 * @param loginId ログインID
	 * @param userName ユーザー名
	 * @param icon アイコン
	 * @param profile プロフィール
	 * @param cmd 検索か全件表示か判断
	 * @param str sql文
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon = null;	//iconをStringで返すための変数
		String[] iconList =request.getParameterValues("icon");
		String profile = request.getParameter("profile");
		String cmd = request.getParameter("cmd");

		//検索条件を保持するため
		request.setAttribute("log", loginId);
		request.setAttribute("Name", userName);
		request.setAttribute("Pr", profile);

		DBManager db = new DBManager();

		RequestDispatcher dispatcher;

		String str = "";

		//検索条件チェック+sql文に変換、空なら”no”で返す
		loginId = CheckStr.Check(loginId);
		userName = CheckStr.Check(userName);
		icon = CheckStr.CheckList(iconList);
		profile = CheckStr.Check(profile);

		request.setAttribute("Ic", icon);

		//検索が押された場合
		if (cmd.equals("検索")) {
			//すべて空の場合
			if (loginId.equals("no") && userName.equals("no") && icon.equals("no") && profile.equals("no")) {
				String msg = null;
				str = "no";
				msg = "何かキーワードをいれてね";
				request.setAttribute("alert", msg);
				dispatcher = request.getRequestDispatcher("UserSearchInput.jsp");
				dispatcher.forward(request, response);

			}else {
				str = "WHERE ";
			}

			//それぞれの項目名付けたし
			if (!loginId.equals("no")) {
				loginId = "loginId" + loginId;
			}
			if (!userName.equals("no")) {
				userName = "userName" + userName;
			}
			if (!icon.equals("no")) {
				icon = "(icon" + icon;
			}
			if (!profile.equals("no")) {
				profile = "profile" + profile;
			}

			//sql文にする
			if (!loginId.equals("no")) {
				str = str + loginId + " AND ";
			}
			if (!userName.equals("no")) {
				str = str + userName + " AND ";
			}
			if (!icon.equals("no")) {
				str = str + icon + " AND ";
			}
			if (!profile.equals("no")) {
				str = str + profile + " AND ";
			}

			//AND削る
			if(!str.equals("no")){
				str = str.substring(0, (str.length() - 4));
			}
		}

		//全件表示はここから
		if(!str.equals("no")){
			ArrayList<UserDTO> list = db.searchUser(str);
			request.setAttribute("users", list);

			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			dispatcher.forward(request, response);
		}

	}

}
