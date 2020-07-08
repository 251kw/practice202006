package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;
import util.CheckAddUserInfo;

/**
 * dogetは初回呼び出し時に初期値をセットする
 * dopostは入力された情報を判定しエラーがあればaddUserInput.jspにエラーメッセージを出し
 * 正常ならaddUserConfirm.jspに処理を転送する
 */
@WebServlet("/newuser")
public class AddUserInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBManager dbm;	// ログインユーザ情報、書き込み内容管理クラス

    public AddUserInputServlet() {
        super();

    }

	/**
	 * index.jspの新規登録ボタンからの呼び出し
	 * 初期値をセットする
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		request.setAttribute("loginID","");
		request.setAttribute("icon","icon-car");
		request.setAttribute("userName","");
		request.setAttribute("profile","よろしくお願いします");
		// input.jsp に処理を転送
		dispatcher = request.getRequestDispatcher("addUserInput.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * addUserInput.jspの登録ボタンからの呼び出し
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher;

		//入力情報の取得
		String loginID = request.getParameter("loginID");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String icon = request.getParameter("icon");
		String userName = request.getParameter("userName");
		String profile = request.getParameter("profile");

		//UserDTOに取得した情報をset
		UserDTO udto;
		udto = new UserDTO(loginID, password1, userName,  icon,  profile,0);
		request.setAttribute("user", udto);//リクエストスコープへ入れる

		//入力に誤りがないか調べ、なければnull,あればString型でエラー文（エラー内容にあわせた）を返す
		String checker = CheckAddUserInfo.checkinfo(loginID,password1,password2,userName);
		if (checker == null) {
			//エラーがなければ入賞
			if(dbm == null){
				dbm = new DBManager();
			}

			//ログインＩＤの使用の可否を確かめる
			if(dbm.checkID(loginID)) {
				//使用可能ならaddUserConfirmに転送先を指定
				dispatcher = request.getRequestDispatcher("addUserConfirm.jsp");
			}else {

				String message = null;
				message = "そのログインＩＤは既に使用されています";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				// addUserInput.jsp に転送先を指定
				dispatcher = request.getRequestDispatcher("addUserInput.jsp");
			}
		}else {
			//エラーが見つかった場合
			String message = null;
			message = checker;  //checkerにはエラー文が入っている

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			// addUserInput.jsp に処理を転送を指定
			dispatcher = request.getRequestDispatcher("addUserInput.jsp");

		}

		//入力情報の受け渡し
		request.setAttribute("loginID",loginID);
		request.setAttribute("icon", icon);
		request.setAttribute("userName", userName);
		request.setAttribute("profile", profile);

		dispatcher.forward(request, response);
	}

}
