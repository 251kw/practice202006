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

import dao.DBUserAddInput;
import dto.SearchDTO;
import dto.UserDTO;
import util.CheckBoxCheck;
import util.UserSearch;

/**
 * Servlet implementation class UserSearchInput
 */
/**
 * @author d.ito
 * ユーザー検索の制御
 *
 */
@WebServlet("/usi")
public class UserSearchInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchInput() {
		super();
	}

	/**
	 ユーザーを検索してリストに追加
	 userSearchInput.jspから検索で呼出し
	 */
	/* (非 Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		HttpSession session2 = request.getSession();

		// 送信データの取得
		String userName = request.getParameter("userName");
		String loginId = request.getParameter("loginId");
		String[] sicon = request.getParameterValues("sicon");
		String profile = request.getParameter("profile");

		//検索条件をsessionにセット
		session2.setAttribute("userName", userName);
		session2.setAttribute("seloginId", loginId);
		session2.setAttribute("sicon", sicon);
		session2.setAttribute("profile", profile);


		//データ保持
		request.setAttribute("userName", userName);
		request.setAttribute("loginId", loginId);
		//request.setAttribute("icon", icon);
		request.setAttribute("profile", profile);

		int lengths = 0;

		if(sicon != null) {
			lengths = sicon.length;
		}
		request.setAttribute("length", lengths);
		request.setAttribute("icon", sicon);

		String message = null;

		RequestDispatcher dispatcher = null;

		UserSearch us = new UserSearch();

		DBUserAddInput dbui = new DBUserAddInput();

		//全件検索の場合
		 if(loginId.equals("") &&  userName.equals("") && sicon == null && profile.equals("")){
			 ArrayList<SearchDTO> list = us.SearchAllUser();
			 ArrayList<UserDTO> dlist = dbui.SearchDelAllUser();
			 	//検索したユーザー情報を、ユーザリストとしてセッションに追加
				request.setAttribute("users", list);
				//フラグをon
				request.setAttribute("flag", "on");
				request.setAttribute("dusers", dlist);
				if(dlist == null || dlist.size() == 0) {
					request.setAttribute("dflag", "off");
				}else {
					request.setAttribute("dflag", "on");
				}

				if (list == null || list.size() == 0) {
					//エラーメッセージをリクエストオブジェクトに保存
					message = "検索結果がありません";
					CheckBoxCheck cbc = new CheckBoxCheck();
					request.setAttribute("cbc", cbc);
					request.setAttribute("sicon", sicon);
					request.setAttribute("alert", message);
					//検索画面へ
					dispatcher = request.getRequestDispatcher("userSearchInput.jsp");
				}else {
				//検索結果画面へ
				dispatcher = request.getRequestDispatcher("userSearchResult.jsp");
				}
		 }else{
			 //ユーザーを検索するメソッド
			 ArrayList<SearchDTO> list = us.SearchloginIDlUser(loginId,userName,profile,sicon);
			 ArrayList<UserDTO> dlist = dbui.SearchDelloginIDlUser(loginId,userName,profile,sicon);
				//検索したユーザー情報を、ユーザリストとしてセッションに追加
				request.setAttribute("users", list);
				request.setAttribute("dusers", dlist);

				//検索ユーザーがいないなら
				if(list == null || list.size() == 0) {
					request.setAttribute("flag", "off");
				}else {
					request.setAttribute("flag", "on");
				}

				//削除済みユーザーがいないなら
				if(dlist == null || dlist.size() == 0) {
					request.setAttribute("dflag", "off");
				}else {
					request.setAttribute("dflag", "on");
				}

				if ((list == null || list.size() == 0) && (dlist == null || dlist.size() == 0)) {
					//エラーメッセージをリクエストオブジェクトに保存
					CheckBoxCheck cbc = new CheckBoxCheck();
					request.setAttribute("cbc", cbc);
					message = "検索結果がありません";
					request.setAttribute("sicon", sicon);
					request.setAttribute("alert", message);
					//検索画面へ
					dispatcher = request.getRequestDispatcher("userSearchInput.jsp");
				}else {
					//検索結果画面へ
					dispatcher = request.getRequestDispatcher("userSearchResult.jsp");
				}
		 }
		dispatcher.forward(request, response);
	}

}
