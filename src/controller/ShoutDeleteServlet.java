package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.CheckDB;

/**
 * 指定された書き込み内容を削除する
 */
@WebServlet("/ShoutDelete")
public class ShoutDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShoutDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;

		// 結果画面に出力するために値を保持
		String dshoutsId = request.getParameter("dshoutsId");
		String dsloginId = request.getParameter("dsloginId");
		String dsuserName = request.getParameter("dsuserName");
		String dsicon = request.getParameter("dsicon");
		String dswriting = request.getParameter("dswriting");

		request.setAttribute("dsloginId", dsloginId);
		request.setAttribute("dsuserName", dsuserName);
		request.setAttribute("dsicon", dsicon);
		request.setAttribute("dswriting", dswriting);

		// 該当する書き込みを削除
		CheckDB.DeleteShout(dshoutsId);

		// 削除結果画面に移動
		dispatcher = request.getRequestDispatcher("deleteShoutResult.jsp");
		dispatcher.forward(request,response);
	}

}
