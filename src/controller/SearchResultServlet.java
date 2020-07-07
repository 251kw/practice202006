package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.SearchUserDTO;


/**
 * 検索結果サーブレット
 * doGet
 * doPost
 * @author y.sato
 *
 */
@WebServlet("/srs")
public class SearchResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SearchResultServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 後で
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * search_input.jspから呼び出される
	 * doPostメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher = null;
		String botton = request.getParameter("btn");

		//送信情報の取得
		String loginId = request.getParameter("loginId");
		String userName = request.getParameter("userName");
		String icon[] = request.getParameterValues("icon");		//アイコンは配列で
		String profile = request.getParameter("profile");

		SearchUserDTO searchuser = new SearchUserDTO(loginId, userName, icon, profile);	//データ保持
		request.setAttribute("user", searchuser);


		if(botton.equals("戻る")) {
			//response.setContentType("text/html;charset=UTF-8");

			//処理の転送先をsearch_input.jspに指定
			dispatcher = request.getRequestDispatcher("search_input.jsp");
		} else if(botton.equals("削除")) {
			String dloginId = request.getParameter("loginIds");
			request.setAttribute("loginId", dloginId);

		}
		dispatcher.forward(request, response);
	}

}
