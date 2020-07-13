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
	 *userSearch.jspの「検索」ボタンから呼び出される。ユーザ検索操作を行うサーブレット
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
		//セッションに入っているuserの中にある自分のloginIdを取得
		UserDTO user = (UserDTO)session.getAttribute("user");
		String seUserLoginId = user.getLoginId();

		//更新、削除から来た場合のみ、前回のユーザ検索条件を取得
		if(request.getParameter("searchBtn") == null) {
			//セッション内のログインIDと新しいパスワードでログイン認証を行い、変数reuserにユーザ情報を取得
			UserDTO reuser = CheckUtil.nowLoginCheck(seUserLoginId);

			//ログインユーザを削除した場合は、強制的にログアウト処理
			if(reuser == null) {
				//すべてのセッションを破棄
				session.invalidate();
				//index.jsp(ログイン画面)に移動
				response.sendRedirect("index.jsp");
		        return;

			}else {
				//ログインユーザがDBに残っている場合は、セッションに入れた前回の検索条件を取得
				loginId = (String)session.getAttribute("loginId");
				userName = (String)session.getAttribute("userName");
				icon = (String)session.getAttribute("icon");
				profile = (String)session.getAttribute("profile");
			}
		}

//ログインIDの入力制限のエラーチェック------------------------------------

		if(loginId != "") {
			errId = CheckUtil.charCheck(loginId,"ログインID");
		}else {

		}

//エラーがなければユーザ検索してuserSearchComp.jspへ、あれば入力情報を保持したまま再度userSearch.jsp画面へ遷移-----------

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

				//更新・削除から戻ってきた時に、top.jspに戻るまでセッション内の自分のログインIDと新しくしたパスワード情報を保持しておく
				if(request.getParameter("searchBtn") == null) {
					//現在ログインしている情報をDBから再取得
					UserDTO reuser = CheckUtil.nowLoginCheck(seUserLoginId);
					String reLoginId = reuser.getPassword();	//再取得した現在のログインID
					String rePassword = reuser.getPassword();	//再取得した現在のパスワード
					request.setAttribute("reLoginId", reLoginId);
					request.setAttribute("rePassword", rePassword);
				}

				//userSearchComp.jspに処理を転送
				dispatcher = request.getRequestDispatcher("userSearchComp.jsp");
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

			//userSearch.jspに処理を転送
			dispatcher = request.getRequestDispatcher("userSearch.jsp");
			dispatcher.forward(request, response);
		}
	}
}