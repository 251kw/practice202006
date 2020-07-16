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
 * 変更された書き込み内容をデータベースに登録するサーブレット
 */
@WebServlet("/ShoutEditRegist")
public class ShoutEditRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShoutEditRegistServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;

		// 値の受け取り
		String eshoutsId = request.getParameter("eshoutsId");
		String eswriting = request.getParameter("eswriting");

		// 書き込み内容を変更するメソッドを呼び出す
		ShoutDTO reshout = new ShoutDTO();
		CheckDB.EditShouts(eshoutsId, eswriting);

		// 結果画面に出力するために該当箇所の情報を渡す
		reshout = CheckDB.SearchShouts(eshoutsId);
		request.setAttribute("reshout", reshout);

		// 結果画面に移動
		dispatcher = request.getRequestDispatcher("editShoutResult.jsp");
		dispatcher.forward(request,response);
	}

}
