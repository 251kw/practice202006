package section8_1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletInit
 */
public class ServletInit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 初期化パラメータの取得
		message = config.getInitParameter("message");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// HTML 出力準備
		PrintWriter out = response.getWriter();

		out.println("<html lang='ja'>");
		out.println("<head>");
		out.println("<title>セッションの利用</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action='ss2' method='post'>");
		out.println("<table border='1' class='table'>");
		out.println("<tr>");
		out.println("<th><label for='userName'>ユーザ名</label></th>");
		out.println("<td><input type='text' name='userName' id='userName' value='"+ message +"'></td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th><label for='sessionCheck'>ユーザ名を保存する</label></th>");
		out.println("<td><input type='checkbox' name='sessionCheck' id='sessionCheck' value='save'></td>");
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
