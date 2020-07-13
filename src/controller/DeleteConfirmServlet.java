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
import dto.UserDTO;

/**
 * 削除機能サーブレット
 * doGet
 * doPost
 * @author y.sato
 */

@WebServlet("")
public class DeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public DeleteConfirmServlet() {
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
	 * 複数削除の時に起動する
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = null;
		String[] loginIds = request.getParameterValues("checkbox");
		String button = request.getParameter("btn");
		String message = null;
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		DBUserSearch dbs = new DBUserSearch();

		if(button.equals("選択項目を削除")) {
			if(loginIds==null) {
				message = "☑チェックボックスが選択されていません。";
				request.setAttribute("alert", message);
				dispatcher = request.getRequestDispatcher("search_result.jsp");

			} else {
				HttpSession session = request.getSession();
				UserDTO user = (UserDTO)session.getAttribute("user");

				for(String id : loginIds) {
					if(id.equals(user.getLoginId())){
						//今ログインしているユーザーなら
						message = "現在、ログインしているユーザーを削除するとログアウトされます。";

						//メッセージをリクエストオブジェクトに保存
						request.setAttribute("alert", message);
					}
					list.add(dbs.userIdSearch(id));
				}
				request.setAttribute("users", list);

				//処理の転送先をdelete_confirm.jspに指定
				dispatcher = request.getRequestDispatcher("delete_confirm.jsp");
			}
		} else {
			if(button.equals("off")) {

				request.setAttribute("flg", "on");
			} else if(button.equals("on") || button.equals("")) {

				request.setAttribute("flg", "off");
				String[] allid = dbs.getAllId();
				request.setAttribute("loginIds", allid);
			}
			dispatcher = request.getRequestDispatcher("search_result.jsp");
		}
		dispatcher.forward(request, response);
	}
}
