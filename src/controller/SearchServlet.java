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
import util.CheckUtil;

/**
 *ユーザ検索用のサーブレット
 */
@WebServlet("/searchSrv")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 *search.jspの「検索」ボタンから呼び出される。ユーザ検索操作を行うサーブレット
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");

		//ログインIDの入力制限チェックの変数
		RequestDispatcher dispatcher = null;
		String errId = null;	//ログインIDの入力制限

//ログインIDの入力制限のエラーチェック(あとで)(それかcssで入力制限つける)------------------------------------

		if(loginId != "") {
			errId = CheckUtil.charCheck(loginId,"ログインID");
		}

//エラーがなければユーザ検索してsearchComp.jspへ、あれば入力情報を保持したまま再度search.jsp画面へ遷移---------------------

		if(errId == "" || errId == null) {
			//listで検索結果を取得
			DBManager dbm = new DBManager();
			ArrayList<UserDTO> searchList = dbm.getSearchList(loginId, userName, icon, profile);

			//検索結果があれば完了画面に遷移、なければ検索結果なしのページに遷移
			if(searchList != null) {
				//requestスコープにsearchlistをセット
				request.setAttribute("searchList", searchList);
				//searchComp.jspに処理を転送
				dispatcher = request.getRequestDispatcher("searchComp.jsp");
				dispatcher.forward(request, response);
			}else {
				//検索結果なしのページへ遷移（あとで）
				dispatcher = request.getRequestDispatcher("searchComp.jsp");
				dispatcher.forward(request, response);
			}
		}else{
			//入力情報を保持して、エラーを検索画面へ返す
			//reqestスコープにエラー内容をセット
			request.setAttribute("errId", errId);

			//reqestスコープに入力情報をセット
			request.setAttribute("loginId", loginId);
			request.setAttribute("userName", userName);
			request.setAttribute("icon", icon);
			request.setAttribute("profile", profile);

			//insert.jspに処理を転送
			dispatcher = request.getRequestDispatcher("search.jsp");
			dispatcher.forward(request, response);
		}
	}
}