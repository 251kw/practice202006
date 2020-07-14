package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		RequestDispatcher dispatcher = null;

		ShoutDTO shoutinfo = new ShoutDTO();

		// 該当する叫びを特定するための情報を保持
		String eshoutsId = request.getParameter("eshoutsId");
		String dshoutsId = request.getParameter("dshoutsId");

		// shoutsIdを元に書き込み情報を特定
		if(eshoutsId!=null) {
			// 編集ボタンが押された場合
			shoutinfo = CheckDB.SearchShouts(eshoutsId);
			request.setAttribute("eshoutsId", eshoutsId);
			dispatcher = request.getRequestDispatcher("editShoutInput.jsp");
		}else if(dshoutsId!=null){
			// 削除ボタンが押された場合
			shoutinfo = CheckDB.SearchShouts(dshoutsId);
			request.setAttribute("dshoutsId", dshoutsId);
			dispatcher = request.getRequestDispatcher("deleteShoutConfirm.jsp");
		}

		request.setAttribute("shoutinfo", shoutinfo);

		// 編集入力画面または削除確認画面に移動
		dispatcher.forward(request,response);
	}

}
