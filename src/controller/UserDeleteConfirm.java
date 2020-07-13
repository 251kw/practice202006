package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;

/**
 * @author t.kurihara
 * 実際にデリート文を実行する
 *delete_confirm.jspから送られてくる
 *delete_result.jspに返す
 */
@WebServlet("/udc")
public class UserDeleteConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserDeleteConfirm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//情報取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		//結果画面に送る
		request.setAttribute("loginId", loginId);
		request.setAttribute("password", password);
		request.setAttribute("userName", userName);
		request.setAttribute("icon", icon);
		request.setAttribute("profile", profile);
		boolean result = false;

		DBManager db = new DBManager();
		RequestDispatcher dispatcher = null;

		String str = "'"+loginId+"'";

		//shoutsテーブルで一致したものを消す
		result = db.checkShout(str);
		if(result == true) {
			db.deleteShouts(str);
		}
		//デリート文実行
		result =db.deleteUser(str);

		//成功したかどうか
		if(result==true) {
			dispatcher = request.getRequestDispatcher("delete_result.jsp");
		}else {
			//失敗したときもう一度delete_confirm.jspに情報を送る
			String[] duser = {loginId,password,userName,icon,profile};
			request.setAttribute("duser", duser);
			String msg = null;
			msg = "＊データベースで問題が発生するため削除できません。";
			request.setAttribute("alert", msg);
			dispatcher = request.getRequestDispatcher("delete_confirm.jsp");
		}

		dispatcher.forward(request, response);

	}

}
