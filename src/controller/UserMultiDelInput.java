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
		// セッションに接続
		HttpSession session2 = request.getSession();
		String userName = (String) session2.getAttribute("userName");
		String loginId = (String) session2.getAttribute("loginId");
		String[] sicon = (String[]) session2.getAttribute("sicon");
		String profile = (String) session2.getAttribute("profile");

		UserSearch us = new UserSearch();

		RequestDispatcher dispatcher = null;

		//ログインユーザーの検索
		ArrayList<SearchDTO> list = us.SearchloginIDlUser(loginId, userName, profile, sicon);

		//チェックをつけていない場合
		if(sloginId == null) {
			request.setAttribute("users", list);
			//検索結果画面に遷移
			dispatcher = request.getRequestDispatcher("userSearchResult.jsp");
			dispatcher.forward(request, response);
		}else {
			//配列からユーザーをリストに
			for (int i = 0; i <= sloginId.length - 1; i++) {
				String dloginId = sloginId[i];
				ArrayList<SearchDTO> dlist = us.SearchloginId(dloginId);
				request.setAttribute("muldel", dlist);
			}

			//削除する複数のユーザーログインIDをもった配列
			request.setAttribute("delloginId", loginId);

			//削除確認画面へ
			dispatcher = request.getRequestDispatcher("userMultiDelConfirm.jsp");
			dispatcher.forward(request, response);
		}

	}

}
