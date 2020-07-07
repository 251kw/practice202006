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

import dao.DBUserDelete;
import dao.DBUserSearch;
import dto.SearchUserDTO;
import dto.UserDTO;

/**
 * 削除機能サーブレット
 * doGet
 * doPost
 *
 */
@WebServlet("/drs")
public class DeleteResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteResultServlet() {
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
	 * delete_confirm.jsp, delete_result.jspから
	 * 呼ばれるdoPostメソッド
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = null;
		String botton = request.getParameter("btn");
		String[] loginIds = request.getParameterValues("loginIds");
		ArrayList<UserDTO> list;
		DBUserSearch dbs = new DBUserSearch();

		HttpSession session = request.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");

		//delete_confirm.jspの削除ボタン
		if(botton.equals("削除")) {
			list = dbs.userIdSearch(loginIds);
			request.setAttribute("users", list);

			DBUserDelete dbd = new DBUserDelete();
			dbd.shoutsDelete(loginIds);
			dbd.usersDelete(loginIds);

			for(String id: loginIds) {

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
			dispatcher = request.getRequestDispatcher("search_result.jsp");

		} else if(botton.equals("検索結果画面へ")) {		//delete_result.jspで検索結果画面に戻る時
			//削除後、検索しなおして結果表示
			SearchUserDTO search = (SearchUserDTO)session.getAttribute("search");
			String loginId = search.getLoginId();
			String userName = search.getuserName();
			String profile = search.getProfile();
			String[] icon = search.getIcon();

			ArrayList<UserDTO> users = dbs.userSearch(loginId, userName, profile, icon);

			if(users.size()==0) {	//arraylistサイズ測る
				 //検索結果無し、一件削除
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