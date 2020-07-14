
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
 * 掲示板書き込みサーブレット
 * doPost
 * doGet
 * @author y.sato
 *
 */
@WebServlet("/top")
public class TopInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;	// ログインユーザ情報、書き込み内容管理クラス

	/**
	 * 直接アクセスがあった場合は
	 * index.jsp  に処理を転送
	 * doGetメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * top.jsp の「叫ぶ」ボタンから呼ばれる
	 * doPostメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String writing = request.getParameter("shout");
		String button = request.getParameter("btn");
		RequestDispatcher dispatcher;
		String message = null;
		// １度だけ DataManager オブジェクトを生成
		if(dbm == null){
			dbm = new DBManager();
		}

		if(button.equals("掲示板に戻る")) {
			// 書き込み内容追加後のリストを取得
			ArrayList<ShoutDTO> list = dbm.getShoutList();
			// リストをセッションに保存
			request.setAttribute("shouts", list);

		} else if(button.equals("叫ぶ")) {

			if(!writing.equals("")) {// 書き込み内容があれば、リストに追加

				HttpSession session = request.getSession();	//セッションオブジェクト取得
				// セッションからログインユーザ情報を取得
				UserDTO user = (UserDTO) session.getAttribute("user");

				// ログインユーザ情報と書き込み内容を引数に、リストに追加するメソッドを呼び出し
				dbm.setWriting(user, writing);

				// 書き込み内容追加後のリストを取得
				ArrayList<ShoutDTO> list = dbm.getShoutList();

				// リストをセッションに保存
				request.setAttribute("shouts", list);

			} else {
				//書き込み未入力なら
				message = "コメントを入力してください";
				//エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);

				// 書き込み内容追加後のリストを取得
				ArrayList<ShoutDTO> list = dbm.getShoutList();
				// リストをセッションに保存
				request.setAttribute("shouts", list);
			}
		}

		// top.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("top.jsp");
		dispatcher.forward(request, response);
	}
}
