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
 * 掲示板の叫ぶ欄に書き込まれた内容をデータベースに登録し、その内容をセッションに保持して、掲示板に移動
 */
@WebServlet("/bbs")
public class ShoutsRegistDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;	// ログインユーザ情報、書き込み内容管理クラス

	// 直接アクセスがあった場合は index.jsp  に処理を転送
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	// boardtop.jsp の「叫ぶ」ボタンから呼ばれる
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String writing = request.getParameter("shout");
		RequestDispatcher dispatcher;

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
		}else {
			String bmessage = "入力エラー";
			request.setAttribute("alert", bmessage);
		}

		// boardtop.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("boardtop.jsp");
		dispatcher.forward(request, response);
	}
}
