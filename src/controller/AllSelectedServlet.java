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
 * Servlet implementation class AllSelectedServlet
 */
@WebServlet("/AllSelected")
public class AllSelectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AllSelectedServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;

		// 値を保持するための処理
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");
		String checkall = request.getParameter("checkall");

		request.setAttribute("sloginId", sloginId);
		request.setAttribute("suserName", suserName);
		request.setAttribute("sicon", sicon);
		request.setAttribute("sprofile", sprofile);

		ArrayList<UserDTO> resultList = new ArrayList<UserDTO>();

		// 全選択
		if(checkall.equals("checkall")) {
			// 既に全選択されている場合
			resultList = CheckDB.joinsql(sloginId, suserName, sicon, sprofile);

			checkall = "";
			request.setAttribute("checkall", checkall);
			request.setAttribute("resultList", resultList);

			dispatcher = request.getRequestDispatcher("usersearchresult.jsp");
			dispatcher.forward(request,response);
		}else if(checkall.equals("")){
			// 全選択されていない場合
			resultList = CheckDB.joinsql(sloginId, suserName, sicon, sprofile);

			checkall = "checkall";
			request.setAttribute("checkall", checkall);
			request.setAttribute("resultList", resultList);

			dispatcher = request.getRequestDispatcher("usersearchresult.jsp");
			dispatcher.forward(request,response);
		}

	}

}
