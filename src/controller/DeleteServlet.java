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
 * ユーザ情報のDB削除を行うサーブレット
 */
@WebServlet("/deleteSrv")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;	// ログインユーザ情報、書き込み内容管理クラス

    public DeleteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 *userDelete.jspの「削除する」ボタンから呼び出される
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		//送信情報の取得
		String loginId = request.getParameter("loginId");

		//削除結果を代入する用の変数
		boolean result;

		//dbmでDBmanagerをインスタンス化
		dbm = new DBManager();
		//入力された情報でDBを削除
		result = dbm.deleteUser(loginId);
		request.setAttribute("result", result);

		//userDeleteComp.jspに処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("userDeleteComp.jsp");
		dispatcher.forward(request, response);
	}
}
