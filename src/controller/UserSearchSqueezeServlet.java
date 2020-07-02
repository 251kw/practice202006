package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.CheckDB;
import dto.UserDTO;

/**
 * Servlet implementation class UserSearchSqueezeServlet
 */
@WebServlet("/UserSearchSqueeze")
public class UserSearchSqueezeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchSqueezeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		// 送信情報の取得
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");

		if(sicon.equals("all")) {
			sicon = "";
		}

		// 値の保持用
		request.setAttribute("sloginId", sloginId);
		request.setAttribute("suserName", suserName);
		request.setAttribute("sicon", sicon);
		request.setAttribute("sprofile", sprofile);

		RequestDispatcher dispatcher = null;

		ArrayList<UserDTO> resultList = new ArrayList<UserDTO>();
		resultList = CheckDB.joinsql(sloginId, suserName, sicon, sprofile);

		String notfoundmessage = null;

		if(resultList.isEmpty()) {
			notfoundmessage = "該当するユーザーが見つかりませんでした";
			request.setAttribute("notfound", notfoundmessage);

			dispatcher = request.getRequestDispatcher("UserSearchInput.jsp");
			dispatcher.forward(request,response);
		} else {
			request.setAttribute("resultList", resultList);

			dispatcher = request.getRequestDispatcher("UserSearchResult.jsp");
			dispatcher.forward(request,response);
		}
	}
}
