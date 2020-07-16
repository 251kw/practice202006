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
 * indexのログインボタンから呼び出され
 * 入力された情報をもとに存在を確認し
 * 存在すればsesstionスコープ("user")にログインユーザー情報を入れtopに処理を転送する
 * 存在しなければエラーメッセージを出しindexに処理を戻す
 */
@WebServlet("/login")
public class LoginIndexInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginIndexInputServlet() {
        super();

    }

	/**
	 *  直接アクセスがあった場合は index.jsp  に処理を転送
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * indexのログインから呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//送信情報の取得
		String loginId = request.getParameter("loginID"); //indexで送った値
		String password = request.getParameter("password");

		RequestDispatcher dispatcher = null;
		String message = null;

		if(loginId.equals("") || password.equals("")) {
			//ＩＤとパスどちらかでも未入力なら
			message = "ログインIDとパスワードは入力必須です";

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);

			//index.jspに処理を転送
			dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}else {
			//ログイン認証を行い、ユーザー情報を取得
			DBManager dbm = new DBManager();
			UserDTO user = dbm.getloginUser(loginId, password);   //DBM内にあるメソッド 検索結果がなければnullで帰ってくる

			if(user != null) {
				//ユーザー情報を取得できたら、書き込み内容リストを取得
				ArrayList<ShoutDTO> list = dbm.getShoutList();  //DBM内にあるメソッド
				HttpSession session = request.getSession();

				//ログインユーザー情報、書き込み内容リストを取得
				session.setAttribute("user", user); //ログインユーザー
				session.setAttribute("shouts", list); //叫びのリスト

				//処理の転送先をtopInput.jspに指定(必要なリクエストの初期値を渡す)
				String ck = "on";
				request.setAttribute("ck", ck);
				ArrayList<String> checkShouts = new ArrayList<String>();
				checkShouts.add("");
				request.setAttribute("checkShouts", checkShouts);
				dispatcher = request.getRequestDispatcher("topInput.jsp");
			}else {
				//ユーザー情報を取得できない場合
				//エラーメッセージをリクエストオブジェクトに保存
				message = "ログインIDまたはパスワードが違います";
				request.setAttribute("alert", message);

				//処理の転送先をindex.jspに指定
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			//処理を転送
			dispatcher.forward(request, response);
		}
	}

}
