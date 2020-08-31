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
 * Servlet implementation class UserUpdateInput
 * 更新入力画面への処理
 */
@WebServlet("/uui")
public class UserUpdateInput extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateInput() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//送信情報の取得
		//ログインIdを取得
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("loginId");
		String uloginId = loginId;

		//複数選択されたログインIDを取得
		String[] sloginId = request.getParameterValues("delloginId");
		//保持用のログインID
		request.setAttribute("hogeId", sloginId);

		RequestDispatcher dispatcher = null;

		UserSearch us = new UserSearch();

		//ユーザーを検索し、リストにセットする
		if(loginId != null) {
			ArrayList<SearchDTO> list = us.SearchloginId(loginId);
			request.setAttribute("update", list);
			request.setAttribute("sloginId", uloginId);
		}
		//更新入力画面へ
		dispatcher = request.getRequestDispatcher("userUpdateInput.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
