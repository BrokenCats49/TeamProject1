package Service.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Admin.Order.OrderDAO;
import Model.Admin.Order.OrderVO;
import Service.CommandHandler;

public class Order_list_proHandler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int page = Integer.parseInt(request.getParameter("page"));
		OrderVO vo = new OrderVO();
		vo.setKinds(request.getParameter("kinds"));
		vo.setCompany_name(request.getParameter("company"));
		vo.setO_option(request.getParameter("option"));
		vo.setColor(request.getParameter("color"));
		vo.setModel_name(request.getParameter("model"));
		vo.setStock(request.getParameter("stock"));
		vo.setAmount(Integer.parseInt(request.getParameter("amount")));
		vo.setUnit_cost(Integer.parseInt(request.getParameter("price")));
		OrderDAO dao = OrderDAO.getInstance();
		int row = dao.orderinsert(vo);
		request.setAttribute("page", page);
		request.setAttribute("row", row);
		return "/Admin/order/order_list.jsp";
	}

}
