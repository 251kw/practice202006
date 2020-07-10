package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.UserDTO;
import util.CheckDB;

/**
 * 変更された情報をデータベースに登録するアカウント
 */
@WebServlet("/EditUserRegist")
public class EditUserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EditUserRegistServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		RequestDispatcher dispatcher = null;

		// 入力値の保持用
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");

		request.setAttribute("sloginId", sloginId);
		request.setAttribute("suserName", suserName);
		request.setAttribute("sicon", sicon);
		request.setAttribute("sprofile", sprofile);

		// 元々のユーザー情報と変更内容を持ったオブジェクト
		UserDTO originaluser = new UserDTO();
		UserDTO euser = new UserDTO();

		originaluser = (UserDTO)session.getAttribute("originaluser");
		euser = (UserDTO)session.getAttribute("euser");

		// updateを実行
		CheckDB.EditUser(originaluser, euser);

		// 変更結果に移動
		dispatcher = request.getRequestDispatcher("editUserResult.jsp");
		dispatcher.forward(request, response);
	}

}
