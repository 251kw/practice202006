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
 * Servlet implementation class UserUpdateResult
 * 更新結果画面への処理
 */
@WebServlet("/uur")
public class UserUpdateResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateResult() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * userUpdateComfirmから遷移
	 * 更新データの処理を行い結果画面に渡す
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");
		// 送信データの取得
		String userName = request.getParameter("userName");
	//	String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String icon = request.getParameter("icon");
		String profile = request.getParameter("profile");
		String sloginId = request.getParameter("sloginId");

		//結果画面へ送信
		request.setAttribute("userName", userName);
	//	request.setAttribute("loginId", loginId);
		request.setAttribute("password", password);
		request.setAttribute("icon", icon);
		request.setAttribute("profile", profile);
		request.setAttribute("sloginId", sloginId);

		UserSearch us = new UserSearch();

		//更新情報が入力されていれば更新を行う

		if(!userName.equals("") && userName != null) {
			us.updateUserName(userName,sloginId);
		}

		if(!password.equals("") && password != null) {
			us.updatepassword(password,sloginId);
		}

		if(!profile.equals("") && profile != null) {
			us.updateprofile(profile,sloginId);
		}
		if(!icon.equals("") &&  icon != null) {
			us.updateicon(icon,sloginId);
		}

		//変更後の情報を検索しリストにセット
		ArrayList<SearchDTO> list = us.SearchloginId(sloginId);
		request.setAttribute("update", list);

		RequestDispatcher dispatcher = request.getRequestDispatcher("userUpdateResult.jsp");
		dispatcher.forward(request, response);

	}

}
