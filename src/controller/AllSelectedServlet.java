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

import dto.UserDTO;
import util.CheckDB;

/**
 * 全選択ボタンの機能を切り替える
 */
@WebServlet("/AllSelected")
public class AllSelectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AllSelectedServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = null;

		// 値を保持するための処理
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");
		String checkall = request.getParameter("checkall");

		// 全選択ボタンが押された時はチェックボックスの保持を初期化
		String[] select = null;
		session.setAttribute("select", select);

		if(!(sloginId==null && suserName==null && sicon==null && sprofile==null)) {
			// 検索結果画面の全選択ボタン
			request.setAttribute("sloginId", sloginId);
			request.setAttribute("suserName", suserName);
			request.setAttribute("sicon", sicon);
			request.setAttribute("sprofile", sprofile);

			ArrayList<UserDTO> resultList = new ArrayList<UserDTO>();

			// 全選択
			if(checkall.equals("checkall")) {
				// 既に全選択されている場合
				resultList = CheckDB.joinsql(sloginId, suserName, sicon, sprofile);

				checkall = "";
				request.setAttribute("checkall", checkall);
				request.setAttribute("resultList", resultList);

				dispatcher = request.getRequestDispatcher("userSearchResult.jsp");
				dispatcher.forward(request,response);
			}else if(checkall.equals("")){
				// 全選択されていない場合
				resultList = CheckDB.joinsql(sloginId, suserName, sicon, sprofile);

				checkall = "checkall";
				request.setAttribute("checkall", checkall);
				request.setAttribute("resultList", resultList);

				dispatcher = request.getRequestDispatcher("userSearchResult.jsp");
				dispatcher.forward(request,response);
			}
		}else {
			// 掲示板の全選択ボタン
			// 全選択
			if(checkall.equals("checkall")) {
				// 既に全選択されている場合

				checkall = "";
				request.setAttribute("checkall", checkall);

				dispatcher = request.getRequestDispatcher("boardTop.jsp");
				dispatcher.forward(request,response);
			}else if(checkall.equals("")){
				// 全選択されていない場合

				checkall = "checkall";
				request.setAttribute("checkall", checkall);

				dispatcher = request.getRequestDispatcher("boardTop.jsp");
				dispatcher.forward(request,response);
			}
		}

	}

}
