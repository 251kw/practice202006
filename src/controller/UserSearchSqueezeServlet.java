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
import util.CheckInput;
import util.Constant;

/**
 * 検索入力欄の値を元に条件に該当するユーザーを絞り込む
 */
@WebServlet("/UserSearchSqueeze")
public class UserSearchSqueezeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserSearchSqueezeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		// 値を保持するための処理
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");
		String notselectedmessage = request.getParameter("notselectedalert");

		if(sicon.equals("all")) {
			sicon = "";
		}

		// チェックボックスの値保持用
		String[] select = (String[]) session.getAttribute("select");
		session.setAttribute("select", select);

		// 全選択切り替え保持
		String checkall = request.getParameter("checkall");
		// 初期処理
		if(checkall == null) {
			checkall = "";
		}

		request.setAttribute("sloginId", sloginId);
		request.setAttribute("suserName", suserName);
		request.setAttribute("sicon", sicon);
		request.setAttribute("sprofile", sprofile);
		request.setAttribute("checkall", checkall);

		if(notselectedmessage!=null) {
			request.setAttribute("notselectedalert", notselectedmessage);
		}

		RequestDispatcher dispatcher = null;

		String regex_AlphaNum = Constant.AlphaNum;

		ArrayList<UserDTO> resultList = new ArrayList<UserDTO>();

		String notfoundmessage = null;
				;
		// 文字判定
		if (CheckInput.checkLogic(regex_AlphaNum, sloginId) == false && sloginId != "") {
			String checkid = "ログインIDは半角英数字で記入してください";
			request.setAttribute("alertid", checkid);

			dispatcher = request.getRequestDispatcher("userSearchInput.jsp");
			dispatcher.forward(request, response);
		}else {
			// 文字が正常な場合
			// 検索項目に一致するユーザーをリストで受け取る
			resultList = CheckDB.joinsql(sloginId, suserName, sicon, sprofile);

			if(resultList.isEmpty()) {
				notfoundmessage = "該当するユーザーが見つかりませんでした";
				request.setAttribute("notfound", notfoundmessage);

				dispatcher = request.getRequestDispatcher("userSearchInput.jsp");
				dispatcher.forward(request,response);
			} else {
				request.setAttribute("resultList", resultList);

				dispatcher = request.getRequestDispatcher("userSearchResult.jsp");
				dispatcher.forward(request,response);
			}
		}
	}
}
