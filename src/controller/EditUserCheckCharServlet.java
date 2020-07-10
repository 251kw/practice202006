package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserDTO;
import util.CheckInput;
import util.Constant;

/**
 * 変更内容に入力された文字を判定する
 */
@WebServlet("/EditUserCheckChar")
public class EditUserCheckCharServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditUserCheckCharServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		RequestDispatcher dispatcher = null;

		String checkpass = null;
		String checkblank = null;
		String regex_AlphaNum = Constant.AlphaNum;

		// 送信情報の取得
		String epassword = request.getParameter("epassword");
		String euserName = request.getParameter("euserName");
		String eicon = request.getParameter("eicon");
		String eprofile = request.getParameter("eprofile");

		UserDTO euser = new UserDTO();
		euser.setPassword(epassword);
		euser.setUserName(euserName);
		euser.setIcon(eicon);
		euser.setProfile(eprofile);

		// sessionを上書き
		session.setAttribute("euser", euser);

		// 入力値の保持用
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");

		request.setAttribute("sloginId", sloginId);
		request.setAttribute("suserName", suserName);
		request.setAttribute("sicon", sicon);
		request.setAttribute("sprofile", sprofile);


		if (CheckInput.checkLogic(regex_AlphaNum, epassword) == false) {
			// 文字チェック
			checkpass = "パスワードは半角英数字で記入してください";
			request.setAttribute("alertpass", checkpass);
			dispatcher = request.getRequestDispatcher("edituserinput.jsp");
			dispatcher.forward(request, response);
		} else {
			// 文字が正常な場合
			// 文字数チェック
			if (epassword.length() <= 7) {
				checkpass = "パスワードは８文字以上で入力してください";
				request.setAttribute("alertpass", checkpass);

				dispatcher = request.getRequestDispatcher("editUserInput.jsp");
				dispatcher.forward(request, response);
			}else {
				// パスワードが正常な時
				// その他の入力欄が入力されているか確認
				if (false == CheckInput.excludeBlank3(epassword, euserName, eicon, eprofile)) {
					// 未入力項目があるとき
					checkblank = "全ての入力欄を埋めてください";

					// エラーメッセージをリクエストオブジェクトに保存
					request.setAttribute("alertblank", checkblank);

					// editUserInput.jsp に処理を転送
					dispatcher = request.getRequestDispatcher("editUserInput.jsp");
					dispatcher.forward(request, response);
				}else {
					// 未入力項目がないとき
					// editUserConfirm.jsp に処理を転送
					dispatcher = request.getRequestDispatcher("editUserConfirm.jsp");
					dispatcher.forward(request, response);
				}
			}
		}
	}
}
