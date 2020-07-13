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

import dao.DBUserSearch;
import dto.SearchUserDTO;
import dto.UserDTO;


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

    /**
	 * 直接アクセスがないように、index.jspに飛ぶ
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * search_result.jspから呼び出される
	 * doPostメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dispatcher = null;
		String botton = request.getParameter("btn");

		request.setCharacterEncoding("UTF-8");
		String[] loginIds = request.getParameterValues("checkbox");
		String button = request.getParameter("btn");
		String message = null;
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		DBUserSearch dbs = new DBUserSearch();


		if(button.equals("選択項目を削除")) {
			//☑が一つもないとき
			if(loginIds==null) {
				message = "☑チェックボックスが選択されていません。";
				request.setAttribute("alert", message);
				dispatcher = request.getRequestDispatcher("search_result.jsp");
				dispatcher.forward(request, response);
				return;
			}
			HttpSession session = request.getSession();
			UserDTO user = (UserDTO)session.getAttribute("user");

			for(String id : loginIds) {
				if(id.equals(user.getLoginId())){
					//今ログインしているユーザーなら
					message = "現在、ログインしているユーザーを削除するとログアウトされます。";

					//メッセージをリクエストオブジェクトに保存
					request.setAttribute("alert", message);
				}
				list.add(dbs.userIdSearch(id));
			}
			request.setAttribute("users", list);

			//処理の転送先をdelete_confirm.jspに指定
			dispatcher = request.getRequestDispatcher("delete_confirm.jsp");

		} else	if(botton.equals("選択項目を編集")) {
			//☑が一つもないとき
			if(loginIds==null) {
				message = "☑チェックボックスが選択されていません。";
				request.setAttribute("alert", message);
				dispatcher = request.getRequestDispatcher("search_result.jsp");
				dispatcher.forward(request, response);
				return;
			}
			//☑が複数ある時
			if(loginIds.length>1) {
				message = "☑編集項目は1つしか選択できません。";
				request.setAttribute("alert", message);
				request.setAttribute("loginIds", loginIds);
				dispatcher = request.getRequestDispatcher("search_result.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//loginIdで検索して、情報を取り出し
			UserDTO upuser = dbs.userIdSearch(loginIds[0]);

			String loginId = upuser.getLoginId();
			String pass = upuser.getPassword();
			String userName = upuser.getUserName();
			String icon = upuser.getIcon();
			String profile = upuser.getProfile();

			//beanに入れて
			UserDTO user = new UserDTO(loginId, pass, userName, icon, profile);

			//リクエストスコープに置く
			request.setAttribute("user", user);
			//編集画面へ
			dispatcher = request.getRequestDispatcher("update_input.jsp");
		}	else if(botton.equals("戻る")) {
			//送信情報の取得
			String loginId = request.getParameter("loginId");
			String userName = request.getParameter("userName");
			String icon[] = request.getParameterValues("icon");		//アイコンは配列で
			String profile = request.getParameter("profile");

			SearchUserDTO searchuser = new SearchUserDTO(loginId, userName, icon, profile);	//データ保持
			request.setAttribute("user", searchuser);

			//処理の転送先をsearch_input.jspに指定
			dispatcher = request.getRequestDispatcher("search_input.jsp");
		} else {
			//全選択ボタンで
			if(button.equals("off")) {
				//offならonにかえて、
				request.setAttribute("flg", "on");
			} else if(button.equals("on") || button.equals("")) {
				//onまたは初回はoffに、全チェックのためにすべてのログインIdをセット
				request.setAttribute("flg", "off");
				String[] allid = dbs.getAllId();
				request.setAttribute("loginIds", allid);
			}
			dispatcher = request.getRequestDispatcher("search_result.jsp");
		}
		dispatcher.forward(request, response);
	}

}
