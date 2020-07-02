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
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		String cmd = request.getParameter("cmd");

		CheckStr cs = new CheckStr();
		DBManager db = new DBManager();

		RequestDispatcher dispatcher;

		String str = "";

		loginId = cs.Check(loginId);
		userName = cs.Check(userName);
		icon = cs.Check(icon);
		profile = cs.Check(profile);

		if (cmd.equals("検索")) {
			//すべて空の場合
			if (loginId.equals("no") && userName.equals("no") && icon.equals("no") && profile.equals("no")) {
				str = "何かキーワードをいれてね";
				request.setAttribute("alert", str);
				dispatcher = request.getRequestDispatcher("UserSearchInput.jsp");
				dispatcher.forward(request, response);

			} else {
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
				icon = "icon" + icon;
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
			str = str.substring(0, (str.length() - 4));
		}


		ArrayList<UserDTO> list = db.searchUser(str);
		request.setAttribute("users", list);

		dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
		dispatcher.forward(request, response);

	}

}
