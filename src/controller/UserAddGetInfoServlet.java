package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Util.CheckInput;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/getinfo")
public class UserAddGetInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAddGetInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		// ID被りチェック用
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;

		RequestDispatcher dispatcher = null;
		String checkid = null;
		String checkpass = null;
		String checkblank = null;
		String regex_AlphaNum = "^[A-Za-z0-9]+$";

		// 送信情報の取得
		String newloginId = request.getParameter("newloginId");
		String newpassword = request.getParameter("newpassword");
		String newuserName = request.getParameter("newuserName");
		String newicon = request.getParameter("newicon");
		String newprofile = request.getParameter("newprofile");

		request.setAttribute("newloginId", newloginId);
		request.setAttribute("newpassword", newpassword);
		request.setAttribute("newuserName", newuserName);
		request.setAttribute("newicon", newicon);
		request.setAttribute("newprofile", newprofile);

		// 文字判定
		if (CheckInput.checkLogic(regex_AlphaNum, newloginId) == false
				&& CheckInput.checkLogic(regex_AlphaNum, newpassword) == false) {
			checkid = "ログインIDは半角英数字で記入してください";
			checkpass = "パスワードは半角英数字で記入してください";
			request.setAttribute("alertid", checkid);
			request.setAttribute("alertpass", checkpass);

			dispatcher = request.getRequestDispatcher("UserAddInput.jsp");
			dispatcher.forward(request, response);
		} else if (CheckInput.checkLogic(regex_AlphaNum, newloginId) == false) {
			checkid = "ログインIDは半角英数字で記入してください";
			request.setAttribute("alertid", checkid);
			dispatcher = request.getRequestDispatcher("UserAddInput.jsp");
			dispatcher.forward(request, response);
		} else if (CheckInput.checkLogic(regex_AlphaNum, newpassword) == false) {
			checkpass = "パスワードは半角英数字で記入してください";
			request.setAttribute("alertpass", checkpass);
			dispatcher = request.getRequestDispatcher("UserAddInput.jsp");
			dispatcher.forward(request, response);
		} else { // 文字が正常な場合

			// 文字数チェック
			if (newloginId.length() <= 4 && newpassword.length() <= 7) {
				checkid = "IDは５文字以上で入力してください";
				checkpass = "パスワードは８文字以上で入力してください";
				request.setAttribute("alertid", checkid);
				request.setAttribute("alertpass", checkpass);

				dispatcher = request.getRequestDispatcher("UserAddInput.jsp");
				dispatcher.forward(request, response);
			} else if (newloginId.length() <= 4) {
				checkid = "IDは５文字以上で入力してください";
				request.setAttribute("alertid", checkid);

				dispatcher = request.getRequestDispatcher("UserAddInput.jsp");
				dispatcher.forward(request, response);
			} else if (newpassword.length() <= 7) {
				checkpass = "パスワードは８文字以上で入力してください";
				request.setAttribute("alertpass", checkpass);

				dispatcher = request.getRequestDispatcher("UserAddInput.jsp");
				dispatcher.forward(request, response);
			} else { // 文字が正常かつID・パスワードの両方が正常な時

				if (false == CheckInput.excludeBlank(newloginId, newpassword, newuserName, newicon, newprofile)) {

					checkblank = "全ての入力欄を埋めてください";

					// エラーメッセージをリクエストオブジェクトに保存
					request.setAttribute("alertblank", checkblank);

					// register.jsp に処理を転送
					dispatcher = request.getRequestDispatcher("UserAddInput.jsp");
					dispatcher.forward(request, response);
				} else {
					// 全入力欄が埋まっている場合

					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection(DSN, USER, PASSWORD);

						// ID被りをチェック
						String sql2 = "SELECT * FROM users WHERE LOGINID=?";
						pstmt2 = conn.prepareStatement(sql2);
						pstmt2.setString(1, newloginId);
						rset2 = pstmt2.executeQuery();

						if (rset2.next()) {
							String checksame = "入力されたログインIDは既に使用されています";
							// エラーメッセージをリクエストオブジェクトに保存
							request.setAttribute("alertsame", checksame);
							dispatcher = request.getRequestDispatcher("UserAddInput.jsp");
						} else {
							dispatcher = request.getRequestDispatcher("UserAddConfirm.jsp");
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							pstmt2.close();
						} catch (SQLException e) {
						}
					}
					dispatcher.forward(request, response);
				}
			}
		}
	}

}
