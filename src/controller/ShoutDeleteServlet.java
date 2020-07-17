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

import dao.DBManager;
import dto.ShoutDTO;
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

		HttpSession session = request.getSession();

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		DBManager dbm = new DBManager();

		 String[] select = (String[]) session.getAttribute("select");

		// 該当する書き込みを削除
		for(String shoutsId:select) {
			CheckDB.DeleteShout(shoutsId);
		}

		// 更新された書き込みを取得してセット
		list = dbm.getShoutList();
		session.setAttribute("shouts", list);

		// 削除結果画面に移動
		dispatcher = request.getRequestDispatcher("deleteShoutResult.jsp");
		dispatcher.forward(request,response);
	}

}
