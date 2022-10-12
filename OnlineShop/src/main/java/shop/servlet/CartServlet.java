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
import shop.bean.ItemBean;
import shop.dao.DAOException;
import shop.dao.ItemDAO;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			if(action == null) {
				gotoPage(request,response,"/cart.jsp");
			}else if(action.equals("add")) {
				int id = Integer.parseInt(request.getParameter("item_id"));
				HttpSession session = request.getSession(true);
				CartBean cart = (CartBean)session.getAttribute("cart");
				if(cart == null) {
					cart = new CartBean();
					session.setAttribute("cart", cart);
				}
				ItemDAO dao = new ItemDAO();
				ItemBean bean = dao.findById(id);
				
				//カートに追加
				cart.addCart(bean);
				gotoPage(request,response,"/cart.jsp");
			}else if(action.equals("delete")) {
				HttpSession session = request.getSession(false);
				if(session == null) {
					request.setAttribute("message", "セッションが切れています。もう一度トップページより");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}
				CartBean cart = (CartBean)session.getAttribute("cart");
				if(cart == null) {
					request.setAttribute("message","正しく操作してください");
					gotoPage(request,response,"/errInternal.jsp");
					return;
				}
				int id = Integer.parseInt(request.getParameter("item_id"));
				cart.deleteCart(id);
				gotoPage(request,response,"/cart.jsp");
			}else {
				request.setAttribute("message", "正しい操作をしてください");
				gotoPage(request,response,"/errInternal.jsp");
			}
		}catch(DAOException e) {
			e.printStackTrace();
			request.setAttribute("message", "内部エラーが発生しました");
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
