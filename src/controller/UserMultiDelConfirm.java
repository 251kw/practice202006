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
 * Servlet implementation class UserMultiDelConfirm
 */
@WebServlet("/umdc")
public class UserMultiDelConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMultiDelConfirm() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * userMultiDelConfirm.jspから遷移
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//選択されたユーザーのログインIDを配列で取得
		String[] loginId = request.getParameterValues("dloginId");

		//選択されたユーザーを送信
		request.setAttribute("loginId", loginId);

		RequestDispatcher dispatcher = null;

		UserSearch us = new UserSearch();

		//削除するユーザーを探してリストに追加
		if(loginId != null) {
			ArrayList<SearchDTO> list = us.searchMultiloginId(loginId);
			request.setAttribute("delcon", list);
		}

		//shoutsテーブルの削除
		us.multideleteshouts(loginId);
		//ユーザーの削除
		us.multideleteUser(loginId);

		//削除結果画面に遷移
		dispatcher = request.getRequestDispatcher("userMultiDelResult.jsp");
		dispatcher.forward(request, response);
	}

}
