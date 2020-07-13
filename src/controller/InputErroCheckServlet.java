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
 * ユーザ情報のDBアップデートを行うサーブレット
 */
@WebServlet("/inErrCheckSrv")
public class InputErroCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InputErroCheckServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 *userUpdate.jspの「更新する」ボタンから呼び出される
	 */
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
		//TODO　logIDあとでけす
		//String logId = request.getParameter("logId");

		//入力・重複チェックの変数
		String errMsId = null;
		String errMsPass = null;
		String errMsUname = null;
		String errMsIcon = null;
		String errMsProf = null;
		String errPass = null;	//パスワードの入力制限
		String errDepMs = "";	//CheckUtil.inCheckを使用するための空の変数
		String errId = "";	//CheckUtil.inCheckを使用するための空の変数

		//未入力エラーチェックの結果
		boolean boo = true;

//入力項目 未入力、入力制限のエラーチェック----------------------
		errMsId = CheckUtil.loginCheck(loginId,"ログインID");
		errMsPass = CheckUtil.loginCheck(password,"パスワード");
		errMsUname = CheckUtil.loginCheck(userName,"名前");
		errMsIcon = CheckUtil.loginCheck(icon,"アイコン");
		errMsProf = CheckUtil.loginCheck(profile,"プロフィール");
		errPass = CheckUtil.charCheck(password,"パスワード");
//--------------------------------------------------
		//入力項目にエラーがないかチェック
		boo = CheckUtil.inCheck(errMsId,errMsPass,errMsUname,errMsIcon,errMsProf,errDepMs,errId,errPass);

		//エラーがなければuserUpdateConf.jspに送信
		if(boo == true) {
			//requestスコープに情報をセット
			request.setAttribute("loginId", loginId);
			request.setAttribute("password", password);
			request.setAttribute("userName", userName);
			request.setAttribute("icon", icon);
			request.setAttribute("profile", profile);

			//userUpdateConf.jspに処理を転送
			RequestDispatcher dispatcher = request.getRequestDispatcher("userUpdateConf.jsp");
			dispatcher.forward(request, response);
		}else {
			//エラーがあれば元のページに戻り、エラー文を表示させる
			ErrorDTO errorDTO = new ErrorDTO(errMsId,errMsPass,errMsUname,errMsIcon,errMsProf,errDepMs,errId,errPass);
			request.setAttribute("errorDTO", errorDTO);

			//userUpdate.jspに処理を転送
			RequestDispatcher dispatcher = request.getRequestDispatcher("userUpdate.jsp");
			dispatcher.forward(request, response);
		}
	}
}
