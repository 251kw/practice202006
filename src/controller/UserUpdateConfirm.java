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
import dto.UserDTO;

/**
 * 更新のsql文を作り実行する
 * update_confirm.jspから送られてくる
 * update_result.jspに返す
 */
@WebServlet("/uuc")
public class UserUpdateConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserUpdateConfirm() {
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

		//変更後の情報
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		//ボタンの確認
		String check = request.getParameter("check");

		RequestDispatcher dispatcher = null;
		DBManager db = new DBManager();

		//戻るボタン押された
		if (check.equals("戻る")) {
			String[] duser = { loginId, password, userName, icon, profile };
			request.setAttribute("duser", duser);
			dispatcher = request.getRequestDispatcher("update_input.jsp");
		//OKボタン押された
		} else if (check.equals("OK")) {
			HttpSession session = request.getSession();
			//変更前のユーザー情報取得
			String[] olduser = (String[]) session.getAttribute("duser");
			String str = "";
			boolean result = false;

			//変更後の情報セット
			request.setAttribute("loginId", loginId);
			request.setAttribute("password", password);
			request.setAttribute("userName", userName);
			request.setAttribute("icon", icon);
			request.setAttribute("profile", profile);

			//sql文にする
			if (!password.equals(olduser[1])) {
				str = str + "password='" + password + "',";
			}
			if (!userName.equals(olduser[2])) {
				str = str + "userName='" + userName + "',";
				db.updateShouts("userName='"+userName+"'",olduser[0]);
			}
			if (!icon.equals(olduser[3])) {
				str = str + "icon='" + icon + "',";
				db.updateShouts("icon='"+icon+"'",olduser[0]);
			}
			if (!profile.equals(olduser[4])) {
				str = str + "profile='" + profile + "',";
			}

			//変更がないときはスキップ
			if(str.length()!=0) {
				str = str.substring(0, (str.length() - 1));
				result = db.updateUser(str, olduser[0]);
				if (result == true) {
					//現在ログインしているユーザーの処理
					UserDTO user = (UserDTO) session.getAttribute("user");
					if (olduser[0].equals(user.getLoginId())) {
						if (!olduser[1].equals(password)) {
							user.setPassword(password);
						}
						if (!olduser[2].equals(userName)) {
							user.setUserName(userName);
						}
						if (!olduser[3].equals(icon)) {
							user.setIcon(icon);
						}
						if (!olduser[4].equals(profile)) {
							user.setProfile(profile);
						}
						session.setAttribute("user", user);
					}
				}
			}

			//更新できたかチェック
			if (result == true || str.length()==0) {
				//叫びの更新反映
				ArrayList<ShoutDTO> list = db.getShoutList();
				session.setAttribute("shouts", list);
				dispatcher = request.getRequestDispatcher("update_result.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("update_confirm.jsp");
				String msg = null;
				msg = "＊データベースで問題が発生するため更新できません。";
				request.setAttribute("alert", msg);
				UserDTO user = null;
				user = new UserDTO();
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setUserName(userName);
				user.setIcon(icon);
				user.setProfile(profile);

				request.setAttribute("user", user);
			}
		}
		dispatcher.forward(request, response);
	}

}
