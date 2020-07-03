package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SearchDTO;
import util.UserSearch;

/**
 * Servlet implementation class UserSearchInput
 */
@WebServlet("/usi")
public class UserSearchInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserSearchInput() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		// 送信データの取得
		String userName = request.getParameter("userName");
		String loginId = request.getParameter("loginId");
		String[] sicon = request.getParameterValues("sicon");
		String profile = request.getParameter("profile");

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

		//全件検索の場合
		 if(loginId.equals("") &&  userName.equals("") && sicon == null && profile.equals("")){
			 ArrayList<SearchDTO> list = us.SearchAllUser();
			 	//検索したユーザー情報を、ユーザリストとしてセッションに追加
				request.setAttribute("users", list);

				if (list == null || list.size() == 0) {
					//エラーメッセージをリクエストオブジェクトに保存
					message = "検索結果がありません";
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
				//検索したユーザー情報を、ユーザリストとしてセッションに追加
				request.setAttribute("users", list);

				if (list == null || list.size() == 0) {
					//エラーメッセージをリクエストオブジェクトに保存
					message = "検索結果がありません";
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
