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

import dto.UserDTO;
import util.CheckDB;

/**
 * 削除機能、編集機能、複数件削除機能の中継地点となるサーブレット
 */
@WebServlet("/RelayFunction")
public class RelayFunctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RelayFunctionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;

		HttpSession session = request.getSession();

		ArrayList<UserDTO> deleteList = new ArrayList<UserDTO>();

		// 送信情報の取得
		String eloginId = request.getParameter("eloginId");
		String dloginId = request.getParameter("dloginId");
		String[] select = request.getParameterValues("select");
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");

		// 値の保持用
		request.setAttribute("eloginId", eloginId);
		request.setAttribute("dloginId", dloginId);
		request.setAttribute("select", select);
		request.setAttribute("sloginId", sloginId);
		request.setAttribute("suserName", suserName);
		request.setAttribute("sicon", sicon);
		request.setAttribute("sprofile", sprofile);

		UserDTO user1 = new UserDTO();
		UserDTO user2 = new UserDTO();

		String logoutmessage = null;
		String notselectedmessage = null;

		ArrayList<UserDTO> resultList = new ArrayList<UserDTO>();


		if(eloginId==null && dloginId==null && select==null) {
			// ユーザーが未チェックの状態で複数削除ボタンを押下したとき

			notselectedmessage = "ユーザーが選択されていません";

			// 条件に合ったユーザーを取得
			resultList = CheckDB.joinsql(sloginId, suserName, sicon, sprofile);

			// アラートメッセージと検索結果をセット
			request.setAttribute("notselectedalert", notselectedmessage);
			request.setAttribute("resultList", resultList);

			// 検索結果画面へ戻る
			dispatcher = request.getRequestDispatcher("userSearchResult.jsp");
			dispatcher.forward(request,response);

		}else {
			// 変更機能
			if(eloginId!=null ) {

				UserDTO originaluser = new UserDTO();
				UserDTO euser = new UserDTO();

				// 初期情報を保持するためのオブジェクト
				originaluser = CheckDB.SearchUser(eloginId);
				// 入力欄に表示するためのオブジェクト
				euser = CheckDB.SearchUser(eloginId);

				session.setAttribute("originaluser", originaluser);
				session.setAttribute("euser", euser);

				dispatcher = request.getRequestDispatcher("editUserInput.jsp");
				dispatcher.forward(request, response);
			}

			// 削除機能
			if(dloginId!=null) {

				// ログイン中のアカウントがuser1,削除しようとしているアカウントがuser2
				user1 = (UserDTO)session.getAttribute("user");
				user2 = CheckDB.SearchUser(dloginId);

				// 確認画面表示用に保持
				request.setAttribute("duser", user2);

				// ログイン中のアカウントが削除リストに含まれている場合
				if(user1.getLoginId().equals(user2.getLoginId())) {
					logoutmessage = "現在使用中のアカウントです";
					request.setAttribute("logoutalert", logoutmessage);
				}

				dispatcher = request.getRequestDispatcher("deleteConfirm.jsp");
				dispatcher.forward(request,response);
			}

			// 複数件削除
			if(select!=null && eloginId==null && dloginId==null) {

				// ログイン中のアカウントがuser1
				user1 = (UserDTO)session.getAttribute("user");

				deleteList = CheckDB.MakedeleteList(select);

				// 削除対象の全情報を持つdeleteListと削除対象のIDだけを持つselectを格納
				session.setAttribute("deleteList", deleteList);
				session.setAttribute("select", select);

				for(String selectId:select) {

					// ログイン中のアカウントが削除リストに含まれている場合
					if(user1.getLoginId().equals(selectId)) {
						logoutmessage = "現在使用中のアカウントが含まれています";
						request.setAttribute("logoutalert", logoutmessage);
					}
				}

				dispatcher = request.getRequestDispatcher("multiDeleteConfirm.jsp");
				dispatcher.forward(request,response);
			}
		}
	}

}
