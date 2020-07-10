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
 * Servlet implementation class UserDelBack
 * データの更新後、検索結果がそれに準じたものになるように制御する
 */
@WebServlet("/udb")
public class UserDelBack extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDelBack() {
		super();
	}

	/**
	 * 削除完了画面からどこに戻るかの管理
	 * 検索結果の整合性をとる
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションに接続
		HttpSession session2 = request.getSession();
		String userName = (String) session2.getAttribute("userName");
		String loginId = (String) session2.getAttribute("loginId");
		String[] sicon = (String[]) session2.getAttribute("sicon");
		String profile = (String) session2.getAttribute("profile");

		//ログインユーザーと削除するユーザーのloginIdを取得
		String loginUserId = (String) session2.getAttribute("loginUserId");
		String sloginId = (String) request.getParameter("sloginId");

		//複数削除のユーザー情報の配列
		String[] dloginId = request.getParameterValues("dloginId");

		RequestDispatcher dispatcher = null;

		UserSearch us = new UserSearch();

		//ログインユーザーの検索
		ArrayList<SearchDTO> list = us.SearchloginIDlUser(loginId, userName, profile, sicon);

		int flag = 0;
		//削除情報が配列で送られてきたら
		if(dloginId != null) {
			//配列の要素数だけ繰り返す
			for (int i = 0; i < dloginId.length; i++) {
				//ログイン中のログインIDと同じものがないか判定
				if(dloginId[i].equals(loginUserId)) {
					//ログイン中のユーザーならログアウト
					dispatcher = request.getRequestDispatcher("index.jsp");
					flag = 1;
				}
			}
		}

		if(flag == 0) {
			//検索結果が空になるかどうかを判別
			if (list == null || list.size() == 0) {
				dispatcher = request.getRequestDispatcher("userSearchInput.jsp");
			} else {
				//検索したユーザー情報を、ユーザリストとしてリクエストスコープに追加
				request.setAttribute("users", list);
				dispatcher = request.getRequestDispatcher("userSearchResult.jsp");
			}
		}

		//削除するユーザーがログインユーザーか判別
		if(sloginId != null) {
			if (loginUserId.equals(sloginId)) {
				dispatcher = request.getRequestDispatcher("index.jsp");
			}else {
				//検索結果が空になるかどうかを判別
				if (list == null || list.size() == 0) {
					dispatcher = request.getRequestDispatcher("userSearchInput.jsp");
				} else {
					//検索したユーザー情報を、ユーザリストとしてリクエストスコープに追加
					request.setAttribute("users", list);
					dispatcher = request.getRequestDispatcher("userSearchResult.jsp");
				}
			}
		}
		dispatcher.forward(request, response);
	}

}
