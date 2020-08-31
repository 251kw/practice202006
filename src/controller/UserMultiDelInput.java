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

import dto.SearchDTO;
import util.UserSearch;

/**
 * Servlet implementation class UserMultiDelInput
 */
@WebServlet("/umdi")
public class UserMultiDelInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMultiDelInput() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//複数選択されたログインIDを取得
		String[] sloginId = request.getParameterValues("delloginId");
		//保持用のログインID
		request.setAttribute("hogeId", sloginId);

		// セッションに接続
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String seloginId = (String) session.getAttribute("seloginId");
		String[] sicon = (String[]) session.getAttribute("sicon");
		String profile = (String) session.getAttribute("profile");

		String message = null;

		UserSearch us = new UserSearch();

		RequestDispatcher dispatcher = null;

		ArrayList<SearchDTO> sdlist = null;

		//ログインユーザーの検索

		//チェックをつけていない場合
		if(sloginId == null) {
			ArrayList<SearchDTO> list = us.SearchloginIDlUser(seloginId, userName, profile, sicon);
			request.setAttribute("users", list);
			//エラーメッセージ
			message = "チェックボックスを入力してください";
			//エラーメッセージをオブジェクトに保存
			request.setAttribute("alert", message);

			//検索結果画面に遷移
			dispatcher = request.getRequestDispatcher("userSearchResult.jsp");
			dispatcher.forward(request, response);
		}else {
			//配列からユーザーをリストに
			for (int i = 0; i <= sloginId.length - 1; i++) {
				String loginId = sloginId[i];
				sdlist = us.SearchloginId(loginId);

			}
			request.setAttribute("muldel", sdlist);

			//削除する複数のユーザーログインIDをもった配列
			request.setAttribute("delloginId", sloginId);

			//削除確認画面へ
			dispatcher = request.getRequestDispatcher("userMultiDelConfirm.jsp");
			dispatcher.forward(request, response);
		}

	}

}
