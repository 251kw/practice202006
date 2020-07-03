package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBUserSearch;
import dto.UserDTO;

@WebServlet("/dcs")
public class DeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public DeleteConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = null;
		String botton = request.getParameter("btn");
		String loginId = request.getParameter("loginid");
		ArrayList<UserDTO> list;
		DBUserSearch dbs = new DBUserSearch();


		if(botton.equals("削除")) {

			list = dbs.userSearch(loginId, null, null, null);
			request.setAttribute("users", list);

			//処理の転送先をdelete_confirm.jspに指定
			dispatcher = request.getRequestDispatcher("delete_confirm.jsp");
		}



		dispatcher.forward(request, response);
	}

}
