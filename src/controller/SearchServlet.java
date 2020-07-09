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

//一度更新完了したあと、再度前回のユーザ検索条件で検索結果を表示する場合の処理--------------------------------

		//sessionスコープの保存領域を確保
		HttpSession session = request.getSession();

		//更新、削除から来た場合は下記を通り、ユーザ検索条件を取得。検索画面から来た場合はここを通らない。
		if(request.getParameter("searchBtn") == null) {
				loginId = (String)session.getAttribute("loginId");
				userName = (String)session.getAttribute("userName");
				icon = (String)session.getAttribute("icon");
				profile = (String)session.getAttribute("profile");
		}

//ログインIDの入力制限のエラーチェック------------------------------------

		if(loginId != "") {
			errId = CheckUtil.charCheck(loginId,"ログインID");
		}

//エラーがなければユーザ検索してsearchComp.jspへ、あれば入力情報を保持したまま再度search.jsp画面へ遷移-----------

		if(errId == "" || errId == null) {
			//searchListで検索結果を取得
			DBManager dbm = new DBManager();
			ArrayList<UserDTO> searchList = dbm.getSearchList(loginId, userName, icon, profile);

			//検索結果を取得し、完了画面に遷移
			if(searchList != null) {
				//requestスコープにsearchlistをセット
				request.setAttribute("searchList", searchList);

				//sessionスコープに今回の検索条件をセット
				session.setAttribute("loginId", loginId);
				session.setAttribute("userName", userName);
				session.setAttribute("icon", icon);
				session.setAttribute("profile", profile);

				//searchComp.jspに処理を転送
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