package Service.Admin;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Admin.Machine.MachineDAO;
import Model.Admin.Machine.MachineVO;
import Service.CommandHandler;

public class Machine_Handler implements CommandHandler {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		MachineDAO dao = MachineDAO.getInstance();
		String s_query = "", addtag = "", query = "", key = "";

		int totcount = 0;
		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			query = request.getParameter("search");
			s_query = query + " like '%" + key + "%'";
			totcount = dao.machine_count(s_query);
		} else {
			totcount = dao.machine_count();
		}
		
		List<MachineVO> list = null;
		if (key.equals("")) {
			list = dao.machine_list();
		} else {
			list = dao.machine_list(s_query);
		}
		request.setAttribute("totcount", totcount);
		request.setAttribute("list", list);
		request.setAttribute("search", query);
		request.setAttribute("key", key);
		
		return "/Admin/machine/machine_list.jsp";
	}

}
