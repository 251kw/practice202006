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
import util.CheckStr;

/**
 * 検索情報入力画面からの情報をsqlにして実行する
 * serch_inputjspから送られてくる
 * serch_result.jspに返す
 */
@WebServlet("/usi")
public class UserSearchInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserSearchInput() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		//iconをStringで返すための変数
		String icon = null;
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
				dispatcher = request.getRequestDispatcher("search_input.jsp");
				dispatcher.forward(request, response);
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
			HttpSession session = request.getSession();
			session.setAttribute("str", str);
			ArrayList<UserDTO> list = db.searchUser(str);
			request.setAttribute("users", list);
			//削除済みユーザー
			list = db.getDeleteUser();
			request.setAttribute("d_users", list);

			dispatcher = request.getRequestDispatcher("search_result.jsp");
			dispatcher.forward(request, response);
		}

	}

}
