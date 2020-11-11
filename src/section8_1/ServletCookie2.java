package section8_1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCookie2
 */
@WebServlet("/sc2")
public class ServletCookie2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCookie2() {
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
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		// 送信情報の取得
		String userName = request.getParameter("abc");	//サイトのユーザー名を取得
		// 文字エンコード
		userName = URLEncoder.encode(userName, "UTF-8");	//文字コード変換
		String cookieCheck = request.getParameter("cookieCheck");

		// クッキーの取得
		Cookie[] cookies = request.getCookies();	//クッキー型のオブジェクトを取得
		Cookie cookie = null;		//クッキーはnull

		// クッキーの存在チェック
		if(cookies != null){	//クッキーオブジェクトがあるかどうか
			for (Cookie data : cookies){	//クッキー全てにループ処理
				if ("abc".equals(data.getName())){		//usernameのクッキーがあれば次へ abcが取れる
					cookie = data;	//
				}
			}
		}

		// 値の保存にチェックが入っていたら
		if("save".equals(cookieCheck)){		//チェックボックスを保存
			if(cookie != null){
				// クッキーの値を更新
				 cookie.setValue(userName);		//値の更新
			}else{
				// 新規にクッキーを作成
				cookie = new Cookie("abc", userName);	//
			}
		//チェックボックスにチェック無し
		}else{
			if (cookie != null){
				 cookie.setValue("");	//
			}else{
				cookie = new Cookie("abc", "");
			}
		}
		// クッキーをクライアントに保存
		response.addCookie(cookie);		//アクセス元にユーザー名保存

		// HTML 出力準備
		PrintWriter out = response.getWriter();

			out.println("<html lang='ja'>");
			out.println("<head>");
			out.println("<title>クッキーの利用</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3>クッキーを設定しました</h3>");
			out.println("<a href='sc1'>元のページに戻る</a>");
			out.println("</body>");
			out.println("</html>");
	}
}
