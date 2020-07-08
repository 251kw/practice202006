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
 *
 * @author y.sato
 */

@WebServlet("/dcs")
public class DeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public DeleteConfirmServlet() {
        super();
    }

    /**
	 * 削除ボタンで呼び出されるdoGetメソッド
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String dloginId = request.getParameter("loginId");
		ArrayList<UserDTO> list;
		RequestDispatcher dispatcher = null;
		String message = null;

		DBUserSearch dbs = new DBUserSearch();

		HttpSession session = request.getSession();
		UserDTO user = (UserDTO)session.getAttribute("user");	//ログインユーザーを削除するのか判断するために引き出す

		list = dbs.userIdSearch(dloginId);		//loginIdで検索、情報引き出す
		request.setAttribute("users", list);	//リクエストスコープへ

		if(user.getLoginId().equals(dloginId)) {
			//今ログインしているユーザーなら
			message = "現在、ログインしているユーザーを削除するとログアウトされます。";

			//メッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
		}

		//処理の転送先をdelete_confirm.jspに指定
		dispatcher = request.getRequestDispatcher("delete_confirm.jsp");

		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO 複数削除の際に使う？？
	}
}
