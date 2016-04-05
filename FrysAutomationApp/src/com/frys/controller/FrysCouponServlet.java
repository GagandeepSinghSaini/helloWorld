package com.frys.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.frys.service.ProductService;
import com.coupon.service.ServiceCoupon;
import com.frys.bean.CouponBean;
import com.frys.factory.CouponFactory;
import com.frys.interfaces.FrysCouponProcess;

/**
 * Servlet implementation class FrysCouponServlet
 */
public class FrysCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrysCouponServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServiceCoupon serviceCoupon = new ServiceCoupon();
		String redirectTo = null;
		ProductService serv = new ProductService();
		String pluLong = request.getParameter("prodId");
		String operation = request.getParameter("couponOperation");
		if(("generate_coupon").equalsIgnoreCase(operation)) {
			redirectTo = "generate_coupon.jsp";
		} else if(("delete_coupon").equalsIgnoreCase(operation)) {
			redirectTo = "delete_coupon.jsp";
		}
		if(!serv.checkValidProduct(pluLong) || operation == null) {
			request.setAttribute("msg", "SOME ERROR HAS OCCURED (MIGHT BE YOUR PROD_ID IS NOT CORRECT)");
			RequestDispatcher rd = request.getRequestDispatcher(redirectTo);
			rd.forward(request, response);
			
		}else {
			CouponBean uInfo = new CouponBean();
			String couponRequired = null;
			FrysCouponProcess cprocess = null;
			String promotionCode = null;
			String discType = null;
			String discount = null;
			String startDate = null;
			String endDate = null;
			String msg="";
			
			boolean processSuccess = false;
			System.out.println("FrysCouponProcess Operation: "+operation);
			if(("generate_coupon").equalsIgnoreCase(operation)) {
				promotionCode = request.getParameter("promotionCode");
				couponRequired = request.getParameter("requiredFlag");
				discType = request.getParameter("optradio");
				discount = request.getParameter("discValue");
				startDate = request.getParameter("startDate");
				endDate = request.getParameter("endDate");
				
				System.out.println("VALUES: "+pluLong+", "+promotionCode+", "+couponRequired+", "+discType+", "+discount+", "+
							startDate +", "+endDate+", "+operation);
				
				uInfo.setPluLong(pluLong);
				uInfo.setPromotionCode(promotionCode);
				uInfo.setCouponRequired(couponRequired);
				uInfo.setTypeOfDis(discType);
				uInfo.setDisValue(discount);
				uInfo.setStartDate(startDate);
				uInfo.setEndDate(endDate);
				msg = "COUPON DATA MADE SUCCESSFULLY FOR PRODUCT: "+pluLong;
				redirectTo = "generate_coupon.jsp";
			} else if(("delete_coupon").equalsIgnoreCase(operation)) {
				uInfo.setPluLong(pluLong);
				msg = "COUPON DATA EXPIRED SUCCESSFULLY FOR PRODUCT: "+pluLong;
				redirectTo = "delete_coupon.jsp";
			}
			serviceCoupon.setuInfo(uInfo);
			serviceCoupon.setServiceCoupon(serviceCoupon);
			cprocess = CouponFactory.getInstance(operation);
			processSuccess = cprocess.service(serviceCoupon);
			if(cprocess!=null && processSuccess) {
				System.out.println("FrysCouponServlet.doGet(): "+msg);
				request.setAttribute("msg", msg);
				if(couponRequired != null && couponRequired.equalsIgnoreCase("Y")) {
					if(serviceCoupon.getCoupBean() != null) {
						request.setAttribute("promoCode", serviceCoupon.getCoupBean().getPromoCode());
					}
				}
			}else {
				System.out.println("FrysCouponServlet.doGet(): SOME ERROR HAS OCUURED While executing the service");
				request.setAttribute("msg", "Sorry! Coupon not applied successfully");
			}
			if(redirectTo == null) {
				redirectTo = "index.jsp";
			}
			RequestDispatcher rd = request.getRequestDispatcher(redirectTo);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrysCouponServlet.doPost()");
		doGet(request, response);
	}
}
