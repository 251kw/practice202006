package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBManager;
import dto.UserDTO;
import util.CheckAddUserInfo;

/**
 * Servlet implementation class UpdateUserComfirmServlet
 */
@WebServlet("/updateUserComfirm")
public class UpdateUserComfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserComfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher;

		DBManager dbm = new DBManager();

		//入力情報の取得
		String loginID = request.getParameter("loginID");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String icon = request.getParameter("icon");
		String userName = request.getParameter("userName");
		String profile = request.getParameter("profile");

		//UserDTOに取得した情報をset
		UserDTO udto;
		udto = new UserDTO(loginID, password1, userName,  icon,  profile);
		request.setAttribute("user", udto);//リクエストスコープへ入れる

		//エラーがなければ入賞
		String checker = CheckAddUserInfo.checkinfo(loginID,password1,password2,userName);
		if (checker == null) {

			//ログインＩＤの使用の可否を確かめる
			if(dbm.checkID(loginID)) {
				//使用可能ならaddUserConfirmに転送先を指定
				dispatcher = request.getRequestDispatcher("addUserConfirm.jsp");
			}else {

				String message = null;
				message = "そのログインＩＤは既に使用されています";

				// エラーメッセージをリクエストオブジェクトに保存
				request.setAttribute("alert", message);
				// addUserInput.jsp に転送先を指定
				dispatcher = request.getRequestDispatcher("addUserInput.jsp");
			}
		}else {
			//エラーが見つかった場合
			String message = null;
			message = checker;  //checkerにはエラー文が入っている

			// エラーメッセージをリクエストオブジェクトに保存
			request.setAttribute("alert", message);
			// addUserInput.jsp に処理を転送を指定
			dispatcher = request.getRequestDispatcher("addUserInput.jsp");

		}

		//入力情報の受け渡し
		request.setAttribute("loginID",loginID);
		request.setAttribute("icon", icon);
		request.setAttribute("userName", userName);
		request.setAttribute("profile", profile);

		dispatcher.forward(request, response);
	}

}
