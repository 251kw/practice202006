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

import dao.DBUserSearch;
import dao.DBUserUpdate;
import dto.SearchUserDTO;
import dto.UserDTO;

/**
 * ユーザー情報
 * 更新記入サーブレット
 *
 */
@WebServlet("/udis")
public class UpDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpDateServlet() {
        super();
    }


	/**
	 * 直接アクセスがないように、index.jspに飛ぶ
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
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
		String botton = request.getParameter("btn");
		DBUserSearch dbs = new DBUserSearch();
		String message = null;

		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO)session.getAttribute("user");
		DBUserUpdate dbu = new DBUserUpdate();
		String oldId = request.getParameter("oldId");
		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String pass = request.getParameter("pass");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		UserDTO user = new UserDTO(loginId, pass, userName, icon, profile);

		if (botton.equals("確認画面へ")) {
			request.setAttribute("user", user);

			if (pass.equals("")) {
				//パスワードどちらか、もしくは双方未入力なら
				message = "パスワードは必須入力です";

				//エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				message = "";

				//update_input.jspに処理を転送
				dispatcher = request.getRequestDispatcher("update_input.jsp");

			} else {
				dispatcher = request.getRequestDispatcher("update_confirm.jsp");
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

		} else if(botton.equals("キャンセル")) {
			String[] loginIds = {oldId};
			request.setAttribute("loginIds", loginIds);
			dispatcher = request.getRequestDispatcher("search_result.jsp");
			dispatcher.forward(request, response);
			return;
		} else if(botton.equals("検索結果画面へ")) {		//検索結果画面に戻る時
			//削除後、検索しなおして結果表示するために
			SearchUserDTO search = (SearchUserDTO)session.getAttribute("search");
			String Id = search.getLoginId();
			String uName = search.getuserName();
			String pfile = search.getProfile();
			String[] icons = search.getIcon();

			ArrayList<UserDTO> users = dbs.userSearch(Id, uName, pfile, icons);

			if(users.size()==0) {	//arraylistサイズ測る
				 //検索結果無し、一件削除のとき
				 dispatcher = request.getRequestDispatcher("search_no_result.jsp");
			} else {
				//複数あれば
				session.setAttribute("users", users);
				String[] loginIds = {request.getParameter("loginId")};
				request.setAttribute("loginIds", loginIds);
				dispatcher = request.getRequestDispatcher("search_result.jsp");
			}

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
