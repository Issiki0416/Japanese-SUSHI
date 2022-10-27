package shop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.bean.CartBean;
import shop.dao.PurchaseDAO;

/**
 * Servlet implementation class PurchaseServlet
 */
@WebServlet("/PurchaseServlet")
public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if(session == null) {
			request.setAttribute("message", "セッションが切れています。もう一度ログインしてください");
			gotoPage(request,response,"/errInternal.jsp");
			return;
		}
		CartBean cart = (CartBean)session.getAttribute("cart");
		if(cart == null) {
			request.setAttribute("message", "正しい操作をしてください");
			gotoPage(request,response,"/errInternal.jsp");
			return;
		}
		
		try {
			String action = request.getParameter("action");
			if(action == null) {
				gotoPage(request,response,"/purchase.jsp");
			}else if(action.equals("purchase")) {
	//				HttpSession session = request.getSession(true);
				String name = request.getParameter("name");
				String address = request.getParameter("address");

	//				CartBean cart = (CartBean)session.getAttribute("cart");
				PurchaseDAO dao = new PurchaseDAO();
				dao.saveInfo(cart, name, address);
				session.removeAttribute("cart");
				gotoPage(request, response, "/purchase.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print(e);
			request.setAttribute("message", "DBの内部エラーが発生しました");
			gotoPage(request,response,"/errInternal.jsp");
		}
	}

	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
