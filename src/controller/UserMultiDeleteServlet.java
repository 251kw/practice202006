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

import Util.CheckDB;
import dto.ShoutDTO;

/**
 * ユーザーの複数件削除を実行する
 */
@WebServlet("/UserMultiDelete")
public class UserMultiDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserMultiDeleteServlet() {
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

		// 送信情報の取得
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");
		String logoutmessage = request.getParameter("logoutalert");
		String[] select = (String[]) session.getAttribute("select");


		// 値の保持用
		request.setAttribute("sloginId", sloginId);
		request.setAttribute("suserName", suserName);
		request.setAttribute("sicon", sicon);
		request.setAttribute("sprofile", sprofile);
		request.setAttribute("logoutalert", logoutmessage);

		ArrayList<ShoutDTO> list = new ArrayList<ShoutDTO>();

		// 削除対象のユーザーを全て削除し更新された書き込みリストを取得
		for(String selectId:select) {

			list = CheckDB.DeleteUser(selectId);
		}
		// 書き込みリストをセット
		session.setAttribute("shouts", list);

		// 削除結果画面に移動
		dispatcher = request.getRequestDispatcher("multiDeleteResult.jsp");
		dispatcher.forward(request,response);
	}

}
