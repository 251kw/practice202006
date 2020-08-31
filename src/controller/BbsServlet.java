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
import dto.ShoutDTO;
import dto.UserDTO;

/**
 * @author d.ito
 *top.jspから遷移
 *叫ぶボタンの制御を行う
 */
@WebServlet("/bbs")
public class BbsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;	// ログインユーザ情報、書き込み内容管理クラス

	// 直接アクセスがあった場合は index.jsp  に処理を転送
	/* (非 Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
	// top.jsp の「叫ぶ」ボタンから呼ばれる
	/*
	 * 掲示板画面の表示項目の制御
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String writing = request.getParameter("shout");
		RequestDispatcher dispatcher;


		String message = null;

		if(writing.equals("")) {

			message = "入力してください";

			//エラーメッセージをオブジェクトへ
			request.setAttribute("alert2", message);
			//top.jspへ転送



		}


		// 書き込み内容があれば、リストに追加
		if (!writing.equals("")) {
			HttpSession session = request.getSession();
			// セッションからログインユーザ情報を取得
			UserDTO user = (UserDTO) session.getAttribute("user");

			// １度だけ DataManager オブジェクトを生成
			if(dbm == null){
				dbm = new DBManager();
			}

			// ログインユーザ情報と書き込み内容を引数に、リストに追加するメソッドを呼び出し
			dbm.setWriting(user, writing);

			// 書き込み内容追加後のリストを取得
			ArrayList<ShoutDTO> list = dbm.getShoutList();

			// リストをセッションに保存
			session.setAttribute("shouts", list);
		}

		// top.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("top.jsp");
		dispatcher.forward(request, response);
	}
}
