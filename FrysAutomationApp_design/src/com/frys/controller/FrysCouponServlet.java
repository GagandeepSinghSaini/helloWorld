package com.frys.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import com.frys.service.ProductService;
import com.frys.bean.CouponBean;
import com.frys.factory.CouponFactory;
import com.frys.factory.CouponTypeFactory;
import com.frys.interfaces.FrysCouponProcess;
import com.frys.interfaces.FrysCouponType;

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
			CouponBean couponInfo = new CouponBean();
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
				
				couponInfo.setPluLong(pluLong);
				couponInfo.setPromotionCode(promotionCode);
				couponInfo.setCouponRequired(couponRequired);
				couponInfo.setTypeOfDis(discType);
				couponInfo.setDisValue(discount);
				couponInfo.setStartDate(startDate);
				couponInfo.setEndDate(endDate);
				msg = "COUPON DATA MADE SUCCESSFULLY FOR PRODUCT: "+pluLong;
				redirectTo = "generate_coupon.jsp";
			} else if(("delete_coupon").equalsIgnoreCase(operation)) {
				couponInfo.setPluLong(pluLong);
				msg = "COUPON DATA EXPIRED SUCCESSFULLY FOR PRODUCT: "+pluLong;
				redirectTo = "delete_coupon.jsp";
			}
			cprocess = CouponFactory.getOperationInstance(operation);
			FrysCouponType couponType = CouponTypeFactory.getCouponType(promotionCode);
			if(cprocess == null || couponType == null) {
				System.out.println("FrysCouponServlet.doGet(): CouponFactory.getOperationInstance()/CouponTypeFactory.getCouponType returning null");
				request.setAttribute("msg", "Sorry! Some error has occured");
				RequestDispatcher rd = request.getRequestDispatcher(redirectTo);
				rd.forward(request, response);
			}else {
				couponType.setUserCouponInfo(couponInfo);
				couponType.setCouponServ(couponType);
				processSuccess = couponType.CoupService(cprocess);
				
				if(cprocess!=null && processSuccess) {
					System.out.println("FrysCouponServlet.doGet(): "+msg);
					request.setAttribute("msg", msg);
					if(couponRequired != null && couponRequired.equalsIgnoreCase("Y")) {
						if(couponType.getCouponPromoBean() != null) {
							request.setAttribute("promoCode", couponType.getCouponPromoBean().getPromoCode());
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FrysCouponServlet.doPost()");
		doGet(request, response);
	}
}
