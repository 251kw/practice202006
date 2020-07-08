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
import dao.DBUserSearch;
import dao.DBUserUpdate;
import dto.UserDTO;
import util.Check;

/**
 * ユーザー情報
 * 更新記入サーブレット
 *
 */
@WebServlet("/udis")
public class UpDateInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpDateInputServlet() {
        super();
    }

	/**
	 *検索結果の編集ボタンから呼ばれる
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uloginId = request.getParameter("loginId");
		RequestDispatcher dispatcher = null;

		DBUserSearch dbs = new DBUserSearch();

		//loginIdで検索して、情報を取り出し
		ArrayList<UserDTO> upuser = dbs.userIdSearch(uloginId);

		String loginId = upuser.get(0).getLoginId();
		String pass = upuser.get(0).getPassword();
		String userName = upuser.get(0).getUserName();
		String icon = upuser.get(0).getIcon();
		String profile = upuser.get(0).getProfile();

		//beanに入れて
		UserDTO user = new UserDTO(loginId, pass, userName, icon, profile);

		//リクエストスコープに置く
		request.setAttribute("user", user);
		//編集画面へ
		dispatcher = request.getRequestDispatcher("update_input.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 編集画面(update_input.jsp)から呼ばれる
	 * doPostメソッド
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = null;
		DBManager dbm = new DBManager();
		DBUserUpdate dbu = new DBUserUpdate();
		String botton = request.getParameter("btn");
		String oldId = request.getParameter("oldId");
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("user");
		String message = null;

		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String pass = request.getParameter("pass");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		UserDTO user = new UserDTO(loginId, pass, userName, icon, profile);

		if (botton.equals("確認画面へ")) {
			request.setAttribute("user", user);

			if (loginId.equals("") || pass.equals("")) {
				//ログインIDとパスワードどちらか、もしくは双方未入力なら
				message = "ログインIDとパスワードは必須入力です";

				//エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				message = "";

				//update_input.jspに処理を転送
				dispatcher = request.getRequestDispatcher("update_input.jsp");

			} else if(Check.checkLogic(loginId)==false) {
				message = "ログインIDに使用できるのは半角英数字のみです。";

				//エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				message = "";

				//update_input.jspに処理を転送
				dispatcher = request.getRequestDispatcher("update_input.jsp");

			} else if (dbm.SameCheckId(loginId)) {
				//update_confirm.jspに処理を転送
				dispatcher = request.getRequestDispatcher("update_confirm.jsp");

			} else {
				if(loginId.equals(oldId)) {			//ログインユーザーの変更の時
					//update_confirm.jspに処理を転送

					dispatcher = request.getRequestDispatcher("update_confirm.jsp");
				} else {
					message = "このログインIDは既に使われています。";
					request.setAttribute("alert", message);
					message = "";

					//update_input.jspに処理を転送
					dispatcher = request.getRequestDispatcher("update_input.jsp");
				}
			}

		} else if (botton.equals("変更する")) {
			//変更完了へ
			request.setAttribute("user", user);

			dbu.shoutsUpDate(oldId, user);
			dbu.userUpDate(oldId, user);

			if(loginUser.getLoginId().equals(oldId)) { //ログインユーザーの変更の時
				session.setAttribute("user", user);
			}

			//変更完了画面へ
			dispatcher = request.getRequestDispatcher("update_result.jsp");

		} else if(botton.equals("戻る")) {
			//変更画面へ
			request.setAttribute("user", user);
			dispatcher = request.getRequestDispatcher("update_input.jsp");
		}
		//jspに送る
		request.setAttribute("loginId",loginId);
		request.setAttribute("password",pass);
		request.setAttribute("userName",userName);
		request.setAttribute("icon",icon);
		request.setAttribute("profile",profile);
		request.setAttribute("oldId", oldId);

		dispatcher.forward(request, response);

	}
}
