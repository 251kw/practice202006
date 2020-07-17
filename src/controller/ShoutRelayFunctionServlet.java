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

import dto.ShoutDTO;
import util.CheckDB;

/**
 * 叫び内容編集入力画面・削除確認画面に移行するために経由するサーブレット
 */
@WebServlet("/ShoutRelayFunction")
public class ShoutRelayFunctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShoutRelayFunctionServlet() {
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

		ArrayList<ShoutDTO> sdeleteList = new ArrayList<ShoutDTO>();

		ShoutDTO shoutinfo = new ShoutDTO();
		ShoutDTO originalshoutinfo = new ShoutDTO();

		// 該当する叫びを特定するための情報を保持
		String eshoutsId = request.getParameter("eshoutsId");
		String dpush = request.getParameter("dpush");
		String[] select = request.getParameterValues("select");
		String checkall = request.getParameter("checkall");

		session.setAttribute("select", select);
		request.setAttribute("checkall", checkall);

		String notselectedmessage = null;

		// チェックされずに削除ボタンが押された場合
		if(select==null && dpush!=null) {
			notselectedmessage = "書き込みが選択されていません";
			request.setAttribute("notselectedalert", notselectedmessage);
			dispatcher = request.getRequestDispatcher("boardTop.jsp");
		}else if(eshoutsId!=null) {
			// 編集ボタンが押された場合

			originalshoutinfo = CheckDB.SearchShouts(eshoutsId);
			shoutinfo = CheckDB.SearchShouts(eshoutsId);
			request.setAttribute("eshoutsId", eshoutsId);
			request.setAttribute("shoutinfo",shoutinfo);
			session.setAttribute("originalshoutinfo", originalshoutinfo);
			dispatcher = request.getRequestDispatcher("editShoutInput.jsp");
		}else if(select!=null){
			// 削除ボタンが押された場合

			// 削除対象の叫び情報を取得
			for(String shoutsId:select) {
				shoutinfo = CheckDB.SearchShouts(shoutsId);
				sdeleteList.add(shoutinfo);
			}

			session.setAttribute("sdeleteList", sdeleteList);
			dispatcher = request.getRequestDispatcher("deleteShoutConfirm.jsp");

		}
		// 編集入力画面または削除確認画面に移動
		dispatcher.forward(request,response);
	}

}
