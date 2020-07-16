package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;

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
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		//送信情報の取得
		//削除対象のログインIDを配列で取得
		String[] checkedUserLogId = (String[]) request.getParameterValues("checkedUserLogId");

		//削除結果を代入する用の変数
		boolean result = false;
		//削除済みユーザ情報用の変数
		UserDTO user = null;
		//削除済みユーザ情報格納用のリスト
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();

		//dbmでDBmanagerをインスタンス化
		dbm = new DBManager();

		//チェックされたユーザで削除済みユーザリスト作成
		for(String loginId : checkedUserLogId) {
			//DBからユーザ情報取得後、リストに追加
			user = dbm.getCheckUser(loginId);
			list.add(user);
		}

		//削除済みユーザリストの受け渡し
		request.setAttribute("list", list);

		//チェックされたユーザのDB情報を削除
		for(String loginId : checkedUserLogId) {
			result = dbm.deleteUser(loginId);
		}

		//削除結果(true,boolean)の受け渡し
		request.setAttribute("result", result);

		//userDeleteComp.jspに処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("userDeleteComp.jsp");
		dispatcher.forward(request, response);
	}

}
