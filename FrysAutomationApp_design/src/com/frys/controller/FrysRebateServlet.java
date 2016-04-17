package com.frys.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.frys.service.ProductService;
import com.frys.bean.RebateBean;
import com.frys.factory.RebateFactory;
import com.frys.interfaces.FrysRebateProcess;
import com.rebate.service.RebateService;

/**
 * Servlet implementation class FrysRebateServlet
 */

public class FrysRebateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public FrysRebateServlet() {
        super();
       // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrysRebateServlet.doGet()");
		RebateService service = new RebateService();
		ProductService serv = new ProductService();
		String redirectTo = "generate_rebate.jsp";
		String msg = "";
		String pluLong = request.getParameter("prodId");
		String rebateOperation = request.getParameter("rebateOperation");
		if(!serv.checkValidProduct(pluLong) || rebateOperation == null) {
			System.out.println("FrysRebateServlet.doGet():  YOUR PROD_ID IS NOT CORRECT OR OPERATION FOR REBATE NOT SET");
			request.setAttribute("msg", "YOUR PROD_ID IS NOT CORRECT OR OPERATION FOR REBATE NOT SET");
			RequestDispatcher rd = request.getRequestDispatcher(redirectTo);
			rd.forward(request, response);
		}else {
			FrysRebateProcess rprocess = null;
			RebateBean rb = new RebateBean();
			if(rebateOperation.equalsIgnoreCase("rebate_generate")) {
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");
				Double discAmount = Double.parseDouble(request.getParameter("rebate_amt"));
				System.out.println("FrysRebateServlet.doGet(): "+pluLong+", "+startDate+", "+endDate+", "+discAmount+", "+rebateOperation);
				rb.setProdId(pluLong);
				rb.setStartDate(startDate);
				rb.setEndDate(endDate);
				rb.setDiscountAmt(discAmount);
				redirectTo = "generate_rebate.jsp";
			} else if(rebateOperation.equalsIgnoreCase("delete_rebate")) {
				rb.setProdId(pluLong);
				msg = "COUPON DATA EXPIRED SUCCESSFULLY FOR PRODUCT: "+pluLong;
				redirectTo = "delete_rebate.jsp";
			}
			service.setRbBean(rb);
			service.setRbService(service);
			rprocess = RebateFactory.getInstance(rebateOperation);
			if(rprocess.service(service)) {
				msg = "REBATE OPEARTION DONE SUCCESSFULLY FOR PRODUCT: "+pluLong;
			} else {
				msg = "SOME ERROR HAS OCCURED WHILE UPDATING "+pluLong;
			}
			
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher(redirectTo);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
