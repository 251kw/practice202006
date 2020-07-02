package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SearchUserDTO;

@WebServlet("/srs")
public class SearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchResultServlet() {
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

		if(botton.equals("戻る")) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");

			//送信情報の取得
			String loginId = request.getParameter("loginId");
			String userName = request.getParameter("userName");
			String icon[] = request.getParameterValues("icon");		//アイコンは配列で
			String profile = request.getParameter("profile");

			SearchUserDTO searchuser = new SearchUserDTO(loginId, userName, icon, profile);	//データ保持
			request.setAttribute("user", searchuser);
			//処理の転送先をsearch_input.jspに指定
			dispatcher = request.getRequestDispatcher("search_input.jsp");
		}
		dispatcher.forward(request, response);
	}

}
