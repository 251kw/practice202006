package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.CheckBoxCheck;

/**
 * Servlet implementation class UserSearchInputBack
 */
@WebServlet("/usib")
public class UserSearchInputBack extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSearchInputBack() {
        super();

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 検索画面へも同る際に経由
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//チェックボックスの値保持
		CheckBoxCheck cbc = new CheckBoxCheck();
		request.setAttribute("cbc", cbc);

		HttpSession session = request.getSession();

		//チェックされたアイコン
		String[] sicon = (String[])session.getAttribute("sicon");
		if(sicon != null) {
			request.setAttribute("sicon", sicon);
		}
		//検索入力画面へ
		RequestDispatcher dispatcher = request.getRequestDispatcher("userSearchInput.jsp");
		dispatcher.forward(request, response);
	}

}
