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
import dao.DBUserDelete;
import dao.DBUserSearch;
import dto.SearchUserDTO;
import dto.ShoutDTO;
import dto.UserDTO;

/**
 * 削除機能サーブレット
 * doGet
 * doPost
 *
 */
@WebServlet("/drs")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteServlet() {
        super();
    }

	/**
	 *
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = null;
		String botton = request.getParameter("btn");
		String[] shoutIds = request.getParameterValues("shoutId");
		String message = null;
		DBUserSearch dbs = new DBUserSearch();
		DBUserDelete dbd = new DBUserDelete();
		DBManager dbm = new DBManager();

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		if(botton.equals("☑選択項目を削除")) {
			if(shoutIds==null) {
				message = "☑チェックボックスが選択されていません。";
				request.setAttribute("alert", message);

				// 書き込み内容追加後のリストを取得
				list = dbm.getShoutList();
				// リストをセッションに保存
				request.setAttribute("shouts", list);

				dispatcher = request.getRequestDispatcher("top.jsp");
				dispatcher.forward(request, response);
				return;
			}
			for(String s: shoutIds) {
				int shout = Integer.parseInt(s);
				list.add(dbs.shoutIdSearch(shout));
			}
			request.setAttribute("shouts", list);
			dispatcher = request.getRequestDispatcher("s_delete_confirm.jsp");

		} else if(botton.equals("キャンセル")) {
			request.setAttribute("shoutIds", shoutIds);
			dispatcher = request.getRequestDispatcher("top.jsp");

		} else if(botton.equals("削除")) {
			for(String s: shoutIds) {
				int shout = Integer.parseInt(s);
				list.add(dbs.shoutIdSearch(shout));
			}
			request.setAttribute("shouts", list);

			for(String s: shoutIds) {
				int shout = Integer.parseInt(s);
				dbd.shoutDelete(shout);
			}
			dispatcher = request.getRequestDispatcher("s_delete_result.jsp");
		}
		dispatcher.forward(request, response);
	}


	/**
	 * delete_confirm.jsp, delete_result.jsp
	 * （更新の時、検索結果画面に戻るときも）から
	 * 呼ばれるdoPostメソッド
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = null;
		String botton = request.getParameter("btn");
		String[] loginId = request.getParameterValues("loginId");
		String[] checkbox = request.getParameterValues("checkbox");
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		DBUserSearch dbs = new DBUserSearch();

		HttpSession session = request.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");

		//delete_confirm.jspの削除ボタン
		if(botton.equals("削除")) {
			for(String id: loginId) {
				list.add(dbs.userIdSearch(id));
				request.setAttribute("users", list);

				DBUserDelete dbd = new DBUserDelete();
				dbd.shoutsDelete(id);
				dbd.usersDelete(id);
			}

			for(String id: loginId) {
				if(user.getLoginId().equals(id)) {
					//今ログインしているユーザーを削除したら
					//ログイン画面へ戻る削除結果jspへ
					dispatcher = request.getRequestDispatcher("delete_result_index.jsp");
					break;

				} else {
					//検索画面へ戻る削除結果画面へ
					dispatcher = request.getRequestDispatcher("delete_result.jsp");
				}
			}

		} else if(botton.equals("キャンセル")) {
			//削除キャンセルなら、検索結果画面へ
			if(request.getParameter("only").equals("")) {	//単独削除ボタンの時はチェックつけない
				request.setAttribute("loginIds", loginId);	//チェックボックス保持
			} else {
				request.setAttribute("loginIds", checkbox);	//複数☑で単独ボタンの時
			}
			dispatcher = request.getRequestDispatcher("search_result.jsp");

		} else if(botton.equals("検索結果画面へ")) {		//検索結果画面に戻る時
			//削除後、検索しなおして結果表示するために
			SearchUserDTO search = (SearchUserDTO)session.getAttribute("search");
			String Id = search.getLoginId();
			String userName = search.getuserName();
			String profile = search.getProfile();
			String[] icon = search.getIcon();

			ArrayList<UserDTO> users = dbs.userSearch(Id, userName, profile, icon);

			if(users.size()==0) {	//arraylistサイズ測る
				 //検索結果無し、一件削除のとき
				 dispatcher = request.getRequestDispatcher("search_no_result.jsp");
			} else {
				//複数あれば
				session.setAttribute("users", users);

				dispatcher = request.getRequestDispatcher("search_result.jsp");
			}
		}
		dispatcher.forward(request, response);
	}

}
