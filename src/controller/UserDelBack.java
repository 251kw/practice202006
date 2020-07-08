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
 */
@WebServlet("/udb")
public class UserDelBack extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDelBack() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session2 = request.getSession();
		String userName = (String) session2.getAttribute("userName");
		String loginId = (String) session2.getAttribute("loginId");
		String[] sicon = (String[]) session2.getAttribute("sicon");
		String profile = (String) session2.getAttribute("profile");

		String loginUserId = (String) session2.getAttribute("loginUserId");
		String sloginId = (String) request.getParameter("sloginId");

		RequestDispatcher dispatcher = null;

		UserSearch us = new UserSearch();

		ArrayList<SearchDTO> list = us.SearchloginIDlUser(loginId, userName, profile, sicon);
		if (loginUserId.equals(sloginId)) {
			dispatcher = request.getRequestDispatcher("index.jsp");
		}else {
			if (list == null || list.size() == 0) {
				dispatcher = request.getRequestDispatcher("userSearchInput.jsp");
			} else {
				//検索したユーザー情報を、ユーザリストとしてリクエストスコープに追加
				request.setAttribute("users", list);
				//session2.invalidate();
				dispatcher = request.getRequestDispatcher("userSearchResult.jsp");
			}
		}
		dispatcher.forward(request, response);
	}

}
