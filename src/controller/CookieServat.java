package controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CookieDTO;

/**
 * Servlet implementation class CookieServal
 */
@WebServlet("/CookieSrv")
public class CookieServat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CookieServat() {
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
		//確認画面から修正ボタンで戻ってきたときにクッキーを取得する
		// クッキーの配列を取得
		Cookie cookies[] = request.getCookies();

		String logId = "";
		String pass = "";
		String uName = "";
		String Icon = "";
		String prof = "";

		RequestDispatcher dispatcher = null;

		// クッキーがあるかを確認
		if (cookies != null) {
			for (Cookie data : cookies) {
				if ("loginId".equals(data.getName())) {
					// 該当するクッキーを取得
					logId = data.getValue();
					//デコードは日本語以外いらない
					logId = URLDecoder.decode(logId, "UTF-8");
				}
			}
		}

		if (cookies != null) {
			for (Cookie data : cookies) {
				if ("password".equals(data.getName())) {
					// 該当するクッキーを取得
					pass = data.getValue();
					//デコードは日本語以外いらない
					pass = URLDecoder.decode(pass, "UTF-8");
				}
			}
		}
		if (cookies != null) {
			for (Cookie data : cookies) {
				if ("userName".equals(data.getName())) {
					// 該当するクッキーを取得
					uName = data.getValue();
					//デコードしておく
					uName = URLDecoder.decode(uName, "UTF-8");
				}
			}
		}
		if (cookies != null) {
			for (Cookie data : cookies) {
				if ("icon".equals(data.getName())) {
					// 該当するクッキーを取得
					Icon = data.getValue();
					//デコードしておく
					Icon = URLDecoder.decode(Icon, "UTF-8");
				}
			}
		}
		if (cookies != null) {
			for (Cookie data : cookies) {
				if ("profile".equals(data.getName())) {
					// 該当するクッキーを取得
					prof = data.getValue();
					//デコードしておく
					prof = URLDecoder.decode(prof, "UTF-8");
				}
			}
		}
		//CookieDTOにセット
		CookieDTO CookieDTO = new CookieDTO(logId,pass,uName,Icon,prof);
		request.setAttribute("CookieDTO", CookieDTO);

		//insert.jspに処理を転送
		dispatcher = request.getRequestDispatcher("insert.jsp");
		dispatcher.forward(request, response);
	}

}
