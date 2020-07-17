package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.ShoutDTO;

/**
 * 書き込み内容があるかどうかチェックするサーブレット
 */
@WebServlet("/ShoutEditCheckChar")
public class ShoutEditCheckCharServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShoutEditCheckCharServlet() {
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

		ShoutDTO originalshoutinfo = new ShoutDTO();

		// 値を受け取る
		String eshoutsId = request.getParameter("eshoutsId");
		String esloginId = request.getParameter("esloginId");
		String esuserName = request.getParameter("esuserName");
		String esicon = request.getParameter("esicon");
		String esdate = request.getParameter("esdate");
		String eswriting = request.getParameter("eswriting");
		String checkall = request.getParameter("checkall");

		// 一旦全部保持
		request.setAttribute("eshoutsId", eshoutsId);
		request.setAttribute("esloginId", esloginId);
		request.setAttribute("esuserName", esuserName);
		request.setAttribute("esicon", esicon);
		request.setAttribute("esdate", esdate);
		request.setAttribute("eswriting", eswriting);
		request.setAttribute("checkall", checkall);

		String alertmessage = null;
		String allsame = null;

		// 値保持用の処理
		ShoutDTO shoutinfo = new ShoutDTO();
		shoutinfo.setShoutsId(eshoutsId);
		shoutinfo.setLoginId(esloginId);
		shoutinfo.setUserName(esuserName);
		shoutinfo.setIcon(esicon);
		shoutinfo.setDate(esdate);
		shoutinfo.setWriting(eswriting);

		request.setAttribute("shoutinfo", shoutinfo);

		if(eswriting.equals("")) {
			// 書き込みが空の場合

			alertmessage = "書き込みを入力してください";
			request.setAttribute("alertmessage", alertmessage);
			dispatcher = request.getRequestDispatcher("editShoutInput.jsp");
		}else {
			// 書き込みがある場合
			originalshoutinfo = (ShoutDTO)session.getAttribute("originalshoutinfo");

			if(originalshoutinfo.getWriting().equals(eswriting)) {
				// 書き込み内容が変更されていない場合

				allsame = "内容が変更されていません";
				request.setAttribute("allsame", allsame);
				// editUserInput.jsp に処理を転送
				dispatcher = request.getRequestDispatcher("editShoutInput.jsp");
			}else {
				// 書き込み内容が変更されている場合

				dispatcher = request.getRequestDispatcher("editShoutConfirm.jsp");
			}
		}
		dispatcher.forward(request,response);
	}

}
