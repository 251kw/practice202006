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
 * ユーザ情報のDBアップデートを行うサーブレット
 */
@WebServlet("/updateSrv")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;	// ログインユーザ情報、書き込み内容管理クラス

    public UpdateServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    //update.jspの「更新する」ボタンから呼び出される
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		//hiddenで送信した変更前のログインIDの取得
		String logId = request.getParameter("logId");

		//登録結果を代入する用の変数
		boolean result;

		//dbmでDBmanagerをインスタンス化
		dbm = new DBManager();
		//入力された情報をDBに登録
		result = dbm.updateUser(loginId,password,userName,icon,profile,logId);
		request.setAttribute("result", result);

		System.out.println(result);

		//updateComp.jspに処理を転送
		RequestDispatcher dispatcher = request.getRequestDispatcher("updateComp.jsp");
		dispatcher.forward(request, response);
	}
}
