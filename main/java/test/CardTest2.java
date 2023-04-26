package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CardTest2
 */
@WebServlet("/CardTest2")
public class CardTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CardTest2() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); // 受信内容の文字コード
		response.setContentType("text/html; charset=UTF-8"); // 送信内容の文字コード
		// 受け取った内容を変数に入れる
		String suit = request.getParameter("suit");
		String number[] = request.getParameterValues("number");
		String color = request.getParameter("color");
		String query = request.getParameter("query");
		// コンソールへの出力
		System.out.println("--------");
		List<String> numberlist = null;
		if (number == null) {
			numberlist = new ArrayList<>();
		} else {
			numberlist = Arrays.asList(number);
		}
		System.out.println(numberlist);
		System.out.println("color=" + color);
		System.out.println("query=" + query);
		// ブラウザへの出力
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println(suit);
		out.println("<hr>");
		out.println(numberlist);
		out.println("<hr>");
		out.println(color);
		out.println("<hr>");
		out.println(query);

		out.println("<hr>");
		boolean suitFlag[] = new boolean[53];
		boolean numberFlag[] = new boolean[53];

		// 記号による選別
		if (suit.equals("all")) {
			for (int i=1; i<=52; i++) {
				if(i<=13) {
					if(numberlist.contains(""+i)==true) {
						suitFlag[i] = true;
						if(color.contains("red")) {
							suitFlag[i] = false;
						}
					}
				}else if(i<=26) {
					if(numberlist.contains(""+(i-13))==true) {
						suitFlag[i] = true;
						if(color.contains("black")) {
							suitFlag[i] = false;
						}
					}
				}else if(i<=39) {
					if(numberlist.contains(""+(i-26))==true) {
						suitFlag[i] = true;
						if(color.contains("black")) {
							suitFlag[i] = false;
						}
					}
				}else if(i<=52) {
					if(numberlist.contains(""+(i-39))==true) {
						suitFlag[i] = true;
						if(color.contains("red")) {
							suitFlag[i] = false;
						}
					}
				}
			}
		} else if (suit.equals("spade")) {
			for (int i=1; i<=13; i++) {
				if(numberlist.contains(""+i)==true) {
					suitFlag[i] = true;
					if(color.contains("red")) {
						suitFlag[i] = false;
					}
				}
			}
		} else if (suit.equals("heart")) {
			for (int i=14; i<=26; i++) {
				if(numberlist.contains(""+(i-13))==true) {
					suitFlag[i] = true;
					if(color.contains("black")) {
						suitFlag[i] = false;
					}
				}
			}
		}else if (suit.equals("diamond")) {
			for (int i=27; i<=39; i++) {
				if(numberlist.contains(""+(i-26))==true) {
					suitFlag[i] = true;
					if(color.contains("black")) {
						suitFlag[i] = false;
					}
				}
			}
		}else if (suit.equals("club")){
			for (int i=40; i<=52; i++) {
				if(numberlist.contains(""+(i-39))==true) {
					suitFlag[i] = true;
					if(color.contains("red")) {
						suitFlag[i] = false;
					}
				}
			}
		}
		// 数字による選別
		out.println(numberlist.contains("1"));
		out.println("<br>");

		// 色による選別
		// カードの表示
		for (int i=1; i<=52; i++) {
			if (suitFlag[i]==true) {
				String filename = "cards/" + i + ".png";
				out.printf("<img src=\"%s\" width=\"100\" height=\"150\">\n", filename);
			}
		}
		out.println("</html>");
	}

}
