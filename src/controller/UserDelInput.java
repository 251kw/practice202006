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
 * Servlet implementation class UserDelInput
 * 削除機能
 */
@WebServlet("/udi")
public class UserDelInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDelInput() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * userSearchResultから遷移
	 * 削除されるユーザーのログインIDを取得して検索
	 * 確認画面にユーザー情報を表示させる
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//送信情報の取得
		//ログインIdを取得
		String loginId = request.getParameter("loginId");
		String sloginId = loginId;

		RequestDispatcher dispatcher = null;

		UserSearch us = new UserSearch();

		//ユーザーを検索し、リストにセットする
		if(loginId != null) {
			ArrayList<SearchDTO> list = us.SearchloginId(loginId);
			request.setAttribute("del", list);
			request.setAttribute("sloginId", sloginId);
		}
		//確認画面に遷移
		dispatcher = request.getRequestDispatcher("userDelConfirm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
