package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ErrorDTO;
import util.CheckUtil;

/**
 * userInsert.jspの「OK」ボタンから呼び出される。入力チェックのサーブレット
 */
@WebServlet("/inCheck")
public class InsertCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InsertCheckServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * userInsert.jspの「OK」ボタンから呼び出される。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		//入力・重複チェックの変数
		RequestDispatcher dispatcher = null;
		String errMsId = null;
		String errMsPass = null;
		String errMsUname = null;
		String errMsIcon = null;
		String errMsProf = null;
		String errDepMs = null;
		String errId = null;	//ログインIDの入力制限
		String errPass = null;	//パスワードの入力制限

		//ifに入ったらbooがfalseになる
		boolean boo = true;

//入力項目 未入力のエラーチェック--------------------------------------------------

		errMsId = CheckUtil.loginCheck(loginId,"ログインID");
		errMsPass = CheckUtil.loginCheck(password,"パスワード");
		errMsUname = CheckUtil.loginCheck(userName,"名前");
		errMsIcon = CheckUtil.loginCheck(icon,"アイコン");
		errMsProf = CheckUtil.loginCheck(profile,"プロフィール");

//ログインID、パスワードの入力制限のエラーチェック------------------------------------

		errId = CheckUtil.charCheck(loginId,"ログインID");
		errPass = CheckUtil.charCheck(password,"パスワード");

//ID重複のエラーチェック------------------------------------------------------

		errDepMs = CheckUtil.dbCheck(loginId);

//エラーがなければuserInsertConf.jspへ、あればuserInsert.jspへ遷移---------------------

		//エラーがなければtrue、ひとつでもエラーがあればfalseで返ってくる
		boo = CheckUtil.inCheck(errMsId,errMsPass,errMsUname,errMsIcon,errMsProf,errDepMs,errId,errPass);

		if(boo == true) {
			//reqestスコープにひとつずつセット
			request.setAttribute("loginId", loginId);
			request.setAttribute("password", password);
			request.setAttribute("userName", userName);
			request.setAttribute("icon", icon);
			request.setAttribute("profile", profile);

			//userInsertConf.jspに処理を転送
			dispatcher = request.getRequestDispatcher("userInsertConf.jsp");
			dispatcher.forward(request, response);

		}else{
			//errorDTOをreqestへセットしてinsertに渡す
			ErrorDTO errorDTO = new ErrorDTO(errMsId,errMsPass,errMsUname,errMsIcon,errMsProf,errDepMs,errId,errPass);
			request.setAttribute("errorDTO", errorDTO);

			//reqestスコープにひとつずつセット
			request.setAttribute("loginId", loginId);
			request.setAttribute("password", password);
			request.setAttribute("userName", userName);
			request.setAttribute("icon", icon);
			request.setAttribute("profile", profile);

			//userInsert.jspに処理を転送
			dispatcher = request.getRequestDispatcher("userInsert.jsp");
			dispatcher.forward(request, response);
		}
	}
}