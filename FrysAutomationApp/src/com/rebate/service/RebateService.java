package com.rebate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.frys.service.MainConstants;
import com.frys.bean.RebateBean;
import com.frys.connection.CouponDbConnection;

public class RebateService {

	private RebateBean rbBean;
	private RebateService rbService;
	private final String SELECT_FR_PROD_REBATE = "SELECT PLU, STARTDATE, ENDDATE FROM FR_PROD_REBATE WHERE PLU=?";
	private final String UPDATE_REBATE_DATA = "UPDATE FR_PROD_REBATE SET TYPE='V', STARTDATE = TO_DATE(?,'YYYY-MM-DD'),"
			+ " ENDDATE = TO_DATE(?,'YYYY-MM-DD'), AMOUNT=?, QTYLIMIT=2, DEPT=1, CLERK='8300',EXCL='Y',DESCNUMBER=1,"
			+ "ONAD='Y', SHOW='Y',UPCREQUIRED=1, FREEWITHPURCHASE='Y', " 
			+  "DIVISIONREBATEFLAGS='YYYYYYYYYYYNYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY',"
			+ "INABOX=0, PDFNAME='5885063_123113.pdf', REBATE_NUMBER='98462' WHERE PLU=?";   
	private final String INSERT_REBATE_DATA = "INSERT INTO FR_PROD_REBATE"
			+ " (PLU, TYPE,AMOUNT,STARTDATE,ENDDATE,QTYLIMIT,DEPT,CLERK,EXCL,DESCNUMBER, ONAD,SHOW,UPCREQUIRED,FREEWITHPURCHASE,"
			+ "DIVISIONREBATEFLAGS,INABOX,PDFNAME,REBATE_NUMBER) VALUES "
			+ "(?, 'V', ?, TO_DATE(?,'YYYY-MM-DD'), TO_DATE(?,'YYYY-MM-DD'), 2, 1, '8300', 'Y', 1, 'Y', 'Y', 1, 'Y',"
			+ "'YYYYYYYYYYYNYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY', 0, "
			+ "'5885063_123113.pdf', '98462')";
	
	private final String CANCEL_REBATE = "DELETE FROM FR_PROD_REBATE WHERE PLU=?";
			
	public RebateService getRbService() {
		return rbService;
	}

	public void setRbService(RebateService rbService) {
		this.rbService = rbService;
	}

	public RebateBean getRbBean() {
		return rbBean;
	}

	public void setRbBean(RebateBean rbBean) {
		this.rbBean = rbBean;
	}
	
	public boolean checkData() {
		System.out.println("RebateService.checkData(): Checking whether the product entry is already in rebate tables");
		PreparedStatement stmt = null;
		Connection connection = null;
		ResultSet rs = null;
		if(rbBean == null) {
			System.out.println("RebateService.checkData(): USER INPUT IS NULL");
			return false;
		}
		String pluLong = rbBean.getProdId();
		CouponDbConnection couponDbConnection = new CouponDbConnection();
		connection = couponDbConnection.getBv1Connection(MainConstants.ORAC_DRIVER,MainConstants.BV1_URL,MainConstants.BV1_USERNAME,MainConstants.BV1_PASSWORD);
		try {
			if(connection != null && !connection.isClosed()) {
				stmt = connection.prepareStatement(SELECT_FR_PROD_REBATE);
				stmt.setString(1, pluLong);
				rs = stmt.executeQuery();
				if(rs.next()) {
					return true;
				}
			}
		}catch(Exception e) {
			System.out.println("RebateService.checkData(): ****ERROR *****");
			e.printStackTrace();
		} finally {
			CouponDbConnection.releaseResources(connection, rs, stmt);
		}
		return false;
	}
	public boolean updateRebateData() {
		System.out.println("RebateService.updateRebateData(): UPDATING REBATE DATA");
		PreparedStatement stmt = null;
		Connection connection = null;
		if(rbBean == null) {
			System.out.println("RebateService.updateRebateData(): USER INPUT IS NULL");
			return false;
		}
		CouponDbConnection couponDbConnection = new CouponDbConnection();
		connection = couponDbConnection.getBv1Connection(MainConstants.ORAC_DRIVER,MainConstants.BV1_URL,MainConstants.BV1_USERNAME,MainConstants.BV1_PASSWORD);
		if(connection == null) {
			System.out.println("RebateService.updateRebateData(): CONNECTION NULL");
			return false;
		}else {
			try {
				stmt = connection.prepareStatement(UPDATE_REBATE_DATA); //STARTDATE,ENDDATE,AMOUNT,PLU
				stmt.setString(1, rbBean.getStartDate());
				stmt.setString(2, rbBean.getEndDate());
				stmt.setDouble(3, rbBean.getDiscountAmt());
				stmt.setString(4, rbBean.getProdId());
				int numOfRows = stmt.executeUpdate();
				System.out.println("RebateService.updateRebateData(): ROWS UPDATED -> "+numOfRows);
				if(numOfRows > 0) {
					System.out.println("RebateService.updateRebateData(): DATA UPDATED SUCCESSFULLY IN FR_PROD_REBATE");
					return true;
				}
			} catch (SQLException e) {
				System.out.println("RebateService.updateRebateData(): ****ERROR *****");
				e.printStackTrace();
			}finally {
				CouponDbConnection.releaseResources(connection, null, stmt);
			}
		}
		return false;
	}
	public boolean insertRebateData() {
		System.out.println("RebateService.insertRebateData(): INSERTING REBATE DATA");
		PreparedStatement stmt = null;
		Connection connection = null;
		if(rbBean == null) {
			System.out.println("RebateService.updateRebateData(): USER INPUT IS NULL");
			return false;
		}
		CouponDbConnection couponDbConnection = new CouponDbConnection();
		connection = couponDbConnection.getBv1Connection(MainConstants.ORAC_DRIVER,MainConstants.BV1_URL,MainConstants.BV1_USERNAME,MainConstants.BV1_PASSWORD);
		if(connection == null) {
			System.out.println("RebateService.updateRebateData(): CONNECTION NULL");
			return false;
		} else {
			try {
				stmt = connection.prepareStatement(INSERT_REBATE_DATA);
				stmt.setString(1, rbBean.getProdId());
				stmt.setDouble(2, rbBean.getDiscountAmt());
				stmt.setString(3, rbBean.getStartDate());
				stmt.setString(4, rbBean.getEndDate());
				
				int noOfRows = stmt.executeUpdate();
				System.out.println("RebateService.insertRebateData(): ROWS INSERTED: " + noOfRows);
				if(noOfRows > 0) {
					System.out.println("RebateService.insertRebateData(): ROW UPDATED SUCCESSFULLY");
					return true;
				}
			} catch (SQLException e) {
				System.out.println("RebateService.insertRebateData(): ****ERROR *****");
				e.printStackTrace();
			} finally {
				CouponDbConnection.releaseResources(connection, null, stmt);
			}
		}
		return false;
	}
	public boolean cancelRebate() {
		System.out.println("RebateService.cancelRebate(): starts--------- RbBean Object: "+rbBean);
		if(rbBean != null) {
			String prodId = rbBean.getProdId();
			Connection connection = null;
			CouponDbConnection couponDbConnection =new CouponDbConnection();
			connection = couponDbConnection.getBv1Connection(MainConstants.ORAC_DRIVER, MainConstants.BV1_URL, MainConstants.BV1_USERNAME, MainConstants.BV1_PASSWORD);
			PreparedStatement stmt = null;
			try {
				if(connection != null && !connection.isClosed()) {
					stmt = connection.prepareStatement(CANCEL_REBATE);
					stmt.setString(1, prodId);
					int NumOfRows = stmt.executeUpdate();
					System.out.println("RebateService.cancelRebate(): ROWS DELETED: "+NumOfRows); 
					if(NumOfRows > 0) {
						return true;
					}
				}
			}catch(Exception e) {
				System.out.println("RebateService.cancelRebate(): ERROR "+e.getMessage());
				e.printStackTrace();
			} finally {
				CouponDbConnection.releaseResources(connection, null, stmt);
			}
			
		}
		return false;
	}
}
