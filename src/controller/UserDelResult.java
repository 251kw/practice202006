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
 * Servlet implementation class UserDellResult
 * 削除結果画面への処理
 */
@WebServlet("/udr")
public class UserDelResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDelResult() {
        super();
    }
	/**
	 * ユーザーの削除を行う
	 * userDelconfirmから遷移
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインユーザーのログインID
		String sloginId = request.getParameter("sloginId");
		request.setAttribute("sloginId", sloginId);

		RequestDispatcher dispatcher = null;

		UserSearch us = new UserSearch();

		//削除するユーザーを探してリストに追加
		if(sloginId != null) {
			ArrayList<SearchDTO> list = us.SearchloginId(sloginId);
			request.setAttribute("delcon", list);
		}

		//ユーザーを削除するメソッド
		us.deleteUser(sloginId);

		dispatcher = request.getRequestDispatcher("userDelResult.jsp");
		dispatcher.forward(request, response);
	}

}
