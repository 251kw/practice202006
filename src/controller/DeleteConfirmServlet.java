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

@WebServlet("/dcs")
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
	 * search_result.jspの「削除」ボタンで呼び出されるdoPostメソッド
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String botton = request.getParameter("btn");
		String[] loginIds = request.getParameterValues("loginIds");
		ArrayList<UserDTO> list;
		RequestDispatcher dispatcher = null;
		String message = null;

		DBUserSearch dbs = new DBUserSearch();

		HttpSession session = request.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");


		if(botton.equals("削除")) {

			list = dbs.userIdSearch(loginIds);
			request.setAttribute("users", list);

			for(String id: loginIds) {
				if(user.getLoginId().equals(id)) {
					//今ログインしているユーザーなら
					message = "現在、ログインしているユーザーを削除するログアウトされます。";

					//メッセージをリクエストオブジェクトに保存
					request.setAttribute("alert", message);
				}
			}

			//処理の転送先をdelete_confirm.jspに指定
			dispatcher = request.getRequestDispatcher("delete_confirm.jsp");
		}

		dispatcher.forward(request, response);
	}

}
