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
import dto.UserDTO;

/**
 * ログインIDを参照してユーザーを削除する
 */
@WebServlet("/UserDelete")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		RequestDispatcher dispatcher = null;

		// 送信情報の取得
		String dloginId = request.getParameter("dloginId");
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");
		String logoutalert = request.getParameter("logoutalert");

		// 検索画面入力値の保持用
		request.setAttribute("sloginId", sloginId);
		request.setAttribute("suserName", suserName);
		request.setAttribute("sicon", sicon);
		request.setAttribute("sprofile", sprofile);

		// 削除するユーザー情報を一旦保持
		UserDTO duser = new UserDTO();
		duser = CheckDB.SearchUser(dloginId);
		request.setAttribute("duser", duser);

		// 指定のユーザーを削除して新しい書き込みリストを取得
		ArrayList<ShoutDTO> list = CheckDB.DeleteUser(dloginId);

		// リストをセッションに保存
		session.setAttribute("shouts", list);

		// ログイン中のアカウントを削除する場合
		if(logoutalert!=null) {
			request.setAttribute("logoutalert",logoutalert);
		}

		// 削除結果画面に移動
		dispatcher = request.getRequestDispatcher("deleteResult.jsp");
		dispatcher.forward(request,response);
	}

}
