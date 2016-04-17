package com.frys.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.frys.connection.CouponDbConnection;

public class ProductService {
	final private String PRODUCT_QUERY = "SELECT bv.PROD_ID, bv.STATUS, bv.DELETED FROM BV_PRODUCT bv, FR_PRODUCT_FLAGS fr "
			+ "WHERE bv.PROD_ID = ? and bv.STATUS=1 and bv.DELETED=0 and bv.PROD_ID = fr.PROD_ID";
	
	public boolean checkValidProduct(String prodId) {
		System.out.println("ProductService.checkValidProduct()");
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		CouponDbConnection dbConnect = new CouponDbConnection();
		con = dbConnect.getBv1Connection(MainConstants.ORAC_DRIVER, MainConstants.BV1_URL, MainConstants.BV1_USERNAME, MainConstants.BV1_PASSWORD);
		try {
			if(con != null && !con.isClosed()) {
				System.out.println("ProductService.checkValidProduct(): QUERY - "+PRODUCT_QUERY);
				stmt = con.prepareStatement(PRODUCT_QUERY);
				stmt.setString(1, prodId);
				rs = stmt.executeQuery();
				if(rs.next()) {
					System.out.println("ProductService.checkValidProduct(): PRODUCT IS VALID");
					return true;
				}
				System.out.println("ProductService.checkValidProduct(): PRODUCT IS NOT VALID");
			}
		} catch (SQLException e) {
			System.out.println("ProductService.checkValidProduct(): "+e);
		}
		return false;
	}
}
