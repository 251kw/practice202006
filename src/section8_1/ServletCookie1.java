package section8_1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SrevletCookie1
 */
@WebServlet("/sc1")
public class ServletCookie1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCookie1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// クッキーを取得
		Cookie[] cookies = request.getCookies();	//クッキー型のオブジェクトを取得
		String uName = "";		//uName定義

		// クッキーの存在チェック
		if(cookies != null){	//クッキーオブジェクトがあるかどうか
			for (Cookie data : cookies){	//クッキー全てにループ処理
				if ("abc".equals(data.getName())){		//usernameのクッキーがあれば次へ
					uName = data.getValue();	//ユーザー名とってくる
					uName = URLDecoder.decode(uName, "UTF-8");		//文字コード変換
				}
			}
		}

		// HTML 出力準備
		PrintWriter out = response.getWriter();

			out.println("<html lang='ja'>");
			out.println("<head>");
			out.println("<title>クッキーの利用</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action='sc2' method='post'>");
			out.println("<table border='1' class='table'>");
			out.println("<tr>");
			out.println("<th><label for='userName'>ユーザ名</label></th>");
			out.println("<td><input type='text' name='userName' id='userName' value='"+ uName +"'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<th><label for='cookieCheck'>ユーザ名を保存する</label></th>");
			out.println("<td><input type='checkbox' name='cookieCheck' id='cookieCheck' value='save'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td colspan='2' style='text-align:right'><input type='submit' value='送信'></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
