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
import javax.servlet.http.HttpSession;

import Util.CheckInput;
import dto.UserDTO;

/**
 * Servlet implementation class EditUserRegistServlet
 */
@WebServlet("/EditUserCheckChar")
public class EditUserCheckCharServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditUserCheckCharServlet() {
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

		HttpSession session = request.getSession();

		final String DSN = "jdbc:mysql://localhost:3306/sns?useSSL=false";
		final String USER = "root";
		final String PASSWORD = "root";

		Connection conn = null;
		// ID被りチェック用
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		RequestDispatcher dispatcher = null;

		String checkid = null;
		String checkpass = null;
		String checkblank = null;
		String regex_AlphaNum = "^[A-Za-z0-9]+$";

		// 送信情報の取得
		String eloginId = request.getParameter("eloginId");
		String epassword = request.getParameter("epassword");
		String euserName = request.getParameter("euserName");
		String eicon = request.getParameter("eicon");
		String eprofile = request.getParameter("eprofile");

		UserDTO user = new UserDTO();
		user.setLoginId(eloginId);
		user.setPassword(epassword);
		user.setUserName(euserName);
		user.setIcon(eicon);
		user.setProfile(eprofile);

		// sessionを上書き
		session.setAttribute("user", user);

		// 入力値の保持用
		String sloginId = request.getParameter("sloginId");
		String suserName = request.getParameter("suserName");
		String sicon = request.getParameter("sicon");
		String sprofile = request.getParameter("sprofile");

		request.setAttribute("sloginId", sloginId);
		request.setAttribute("suserName", suserName);
		request.setAttribute("sicon", sicon);
		request.setAttribute("sprofile", sprofile);

		// 文字判定
		if (CheckInput.checkLogic(regex_AlphaNum, eloginId) == false
				&& CheckInput.checkLogic(regex_AlphaNum, epassword) == false) {
			checkid = "ログインIDは半角英数字で記入してください";
			checkpass = "パスワードは半角英数字で記入してください";
			request.setAttribute("alertid", checkid);
			request.setAttribute("alertpass", checkpass);

			dispatcher = request.getRequestDispatcher("./TurnEditUserInput");
			dispatcher.forward(request, response);
		} else if (CheckInput.checkLogic(regex_AlphaNum, eloginId) == false) {
			checkid = "ログインIDは半角英数字で記入してください";
			request.setAttribute("alertid", checkid);
			dispatcher = request.getRequestDispatcher("./TurnEditUserInput");
			dispatcher.forward(request, response);
		} else if (CheckInput.checkLogic(regex_AlphaNum, epassword) == false) {
			checkpass = "パスワードは半角英数字で記入してください";
			request.setAttribute("alertpass", checkpass);
			dispatcher = request.getRequestDispatcher("./TurnEditUserInput");
			dispatcher.forward(request, response);
		} else { // 文字が正常な場合

			// 文字数チェック
			if (eloginId.length() <= 4 && epassword.length() <= 7) {
				checkid = "IDは５文字以上で入力してください";
				checkpass = "パスワードは８文字以上で入力してください";
				request.setAttribute("alertid", checkid);
				request.setAttribute("alertpass", checkpass);

				dispatcher = request.getRequestDispatcher("./TurnEditUserInput");
				dispatcher.forward(request, response);
			} else if (eloginId.length() <= 4) {
				checkid = "IDは５文字以上で入力してください";
				request.setAttribute("alertid", checkid);

				dispatcher = request.getRequestDispatcher("./TurnEditUserInput");
				dispatcher.forward(request, response);
			} else if (epassword.length() <= 7) {
				checkpass = "パスワードは８文字以上で入力してください";
				request.setAttribute("alertpass", checkpass);

				dispatcher = request.getRequestDispatcher("./TurnEditUserInput");
				dispatcher.forward(request, response);
			} else { // 文字が正常かつID・パスワードの両方が正常な時

				if (false == CheckInput.excludeBlank(eloginId, epassword, euserName,
						eicon, eprofile)) {

					checkblank = "全ての入力欄を埋めてください";

					// エラーメッセージをリクエストオブジェクトに保存
					request.setAttribute("alertblank", checkblank);

					// register.jsp に処理を転送
					dispatcher = request.getRequestDispatcher("./TurnEditUserInput");
					dispatcher.forward(request, response);
				} else {
					// 	全入力欄が埋まっている場合

					try {
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection(DSN, USER, PASSWORD);

						// ID被りをチェック
						String sql = "SELECT * FROM users WHERE LOGINID=?";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, eloginId);
						rset = pstmt.executeQuery();

						if (rset.next()) {
							String checksame = "入力されたログインIDは既に使用されています";
							// エラーメッセージをリクエストオブジェクトに保存
							request.setAttribute("alertsame", checksame);
							dispatcher = request.getRequestDispatcher("./TurnEditUserInput");
						} else {
							dispatcher = request.getRequestDispatcher("EditUserConfirm.jsp");
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						try {
							pstmt.close();
						} catch (SQLException e) {
						}
					}
					dispatcher.forward(request, response);
				}
			}
		}
	}
}
