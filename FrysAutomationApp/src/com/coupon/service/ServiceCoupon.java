package com.coupon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.frys.bean.CouponBean;
import com.frys.bean.FrysCobaBean;
import com.frys.bean.FrysCoupBean;
import com.frys.connection.CouponDbConnection;
import com.frys.service.MainConstants;
/**
 * 
 * @author Sachin Kumar
 * This class contains the logic to check the existing record in the FR_PROM_DETAIL(regarding promotion code) table.
 * If record already exist then,ask the user for update the record.
 * If the record doesn't exist then insert the values automatically on the basis of PLU id.  
 * This class also contains the logic of coupon_required condition flag.
 *    
 */
public class ServiceCoupon {

	private final String SELECT_FR_PDLIST = "SELECT MPID,LONGPLU FROM FR_PDLIST WHERE LONGPLU=?";
	private final String SELECT_FR_PROM_DETAILS = "SELECT MPID,PLULONG FROM FR_PROM_DETAIL WHERE MPID=?";
	private final String SELECT_FRYCOBA = "SELECT MPID, BATCH_NUMBER FROM FRYSCOBA WHERE MPID=?";
	private final String SELECT_FRYSCOBA2 = "SELECT BATCH_NUMBER FROM FRYSCOBA";
	private final String SELECT_FRYSCOUP = "SELECT fp.PROMO_CODE, fb.MPID FROM FRYSCOUP fp, FRYSCOBA fb WHERE fp.BATCH = ? and fb.MPID=? and fp.BATCH = fb.BATCH_NUMBER";
	private final String SELECT_FR_PROM_DETAIL2 = "SELECT mpid FROM FR_PROM_DETAIL";
	private final String UPDATE_FR_PROM_DETAIL = "UPDATE FR_PROM_DETAIL SET PROMOTIONCODE=?,STARTDATE =TO_DATE(?,'YYYY-MM-DD'),ENDDATE=TO_DATE(?,'YYYY-MM-DD'),COUPON_REQUIRED=?, DISCOUNTPERCENT=?, FIXEDDISCOUNT=? WHERE PLULONG=? AND MPID=?";
    private final String UDDATE_FR_PDLIST = "UPDATE FR_PDLIST SET STARTDATE =TO_DATE(?,'YYYY-MM-DD'),ENDDATE=TO_DATE(?,'YYYY-MM-DD') WHERE LONGPLU=? AND MPID=?";
    private final String INSERT_FR_PROM_DETAIL = "INSERT INTO FR_PROM_DETAIL "
    		+ "(PLU,CLASSCODE,PROMOTIONCODE,DISCOUNTTYPE,PURCHASEDQTY,DISCOUNTPLU,DISCOUNTQTY,DISCOUNTPERCENT,"
    		+ "FIXEDDISCOUNT,STARTDATE,ENDDATE,DEPT,DISCOUNTCLASSCODE,VENDORNUMBER,DISCOUNTDEPT,"
    		+ "DISCOUNTVENDORNUMBER,DISCOUNTDETAIL,PLULONG,PLULONGDISC,CLERK,POSITION,UPDATEDATE,"
    		+ "MPID,AMOUNTBACK,ACTIVEFLAG,ADONLY,LIMITQTYFORMP,APPROVEDACC,APPROVERID,TRIGGERAMOUNT,"
    		+ "DISCOUNTSPREAD,UPTODISCITEMS,BACKTOSTOREPERCENT,LAST_MOD_TIME,CREATE_TIME,DESCRIPTION,"
    		+ "MINDISCQTY,SHIPPING_METHOD,COUPON_REQUIRED)"
    		+ "VALUES"
    		+ "(?,0,?,8,1,0,1,?,?,to_date(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),"
    		+ "5,0,0,5,0,1,?,0,45952,352,null,?,0,"
    		+ "'YYYYYYYYYYYYYYYYYYYYYYYYNYYYYYYYYYYYYNYYYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN',"
    		+ "'N',2,'Y',0,0,'N',0,100,null,null,'291867',1,'9',?)";
    private final String INSERT_INTO_FR_PDLIST ="INSERT INTO FR_PDLIST (MPID,LONGPLU,STARTDATE,ENDDATE,PLUTYPE,GROUPNUM,DISCOUNTPERCENT,DISCOUNTAMOUNT,DISCOUNTQTY,LAST_MOD_TIME,CREATE_TIME)VALUES(?,?,TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),0,0,0,0,0,null,null)";
    private final String INSET_INTO_FRYSCOBA ="INSERT INTO FRYSCOBA(BATCH_NUMBER,MPID) VALUES (?,?)";
    private final String INSERT_INTO_FRYSCOUP ="INSERT INTO FRYSCOUP(PROMO_CODE,BATCH,EMAIL,END_TIME,START_TIME,COUPON_TYPE,EXPANSION)VALUES(?,?,'','00:00:00','00:00:00','0','')";
    private final String EXPIRE_COUPON_QUERY = "UPDATE FR_PROM_DETAIL SET ENDDATE=TO_DATE(?,'YYYY-MM-DD') WHERE PLULONG=? AND MPID=?";
    private final String SELECT_FRYSCO = "SELECT MPID, COUPONID FROM FRYSCO WHERE MPID=?";
    private final String DELETE_FRYSCO = "DELETE FROM FRYSCO WHERE MPID=?";
    
	private ResultSet rs;
	private Scanner scanner;
	private CouponBean uInfo;
	private FrysCoupBean coupBean;
	private final String FIXED_DISCOUNT = "FixedDiscount";
	private final String DISCOUNT_PERCENT = "DiscountPercent";
	private String promotionCode;
	private String couponRequired;
	private String startDate;
	private String endDate;
	private String disValue;
	private String typeOfDis;
	private String pluLong;
	private String mpId;
	private String BATCH_NUMBER = "batch_number";
	private String PROMO_CODE = "PROMO_CODE";
	private ServiceCoupon serviceCoupon;
	//final static Logger logger = Logger.getLogger(ServiceCoupon.class);
	
	public ServiceCoupon getServiceCoupon() {
		return serviceCoupon;
	}
	public void setServiceCoupon(ServiceCoupon serviceCoupon) {
		this.serviceCoupon = serviceCoupon;
	}
	public void setuInfo(CouponBean uInfo) {
		this.uInfo = uInfo;
	}
	public FrysCoupBean getCoupBean() {
		return coupBean;
	}
	public void setCoupBean(FrysCoupBean coupBean) {
		this.coupBean = coupBean;
	}

	
	/**
	 * Populate  the values of PLU,PromotionType,Coupon_Required through bean object
	 */
	 
	public void getUserInfo() {
		try {
		
			uInfo = new CouponBean();
			scanner = new Scanner(System.in);
			
			System.out.println("Enter The value for ProdId: ");
			String pluLong =scanner.nextLine();
            uInfo.setPluLong(pluLong);
            
            System.out.println("Please enter the Desired PromotionCode :");
			String promotionCode = scanner.nextLine();
			uInfo.setPromotionCode(promotionCode);
			System.out.println("Enter Coupon_Required flag(Y/N): ");
			String couponRequired = scanner.nextLine();
			uInfo.setCouponRequired(couponRequired.toUpperCase());
			System.out.println("PLEASE FILL THE DISCOUNT PERCENT OR FIXED DISCOUNT: ");
			String discountVal = scanner.nextLine();
			uInfo.setDisValue(discountVal);
			boolean repeat =true;
			do {
				System.out.println("Select the option (1 or 2)");
				System.out.println("1. Fixed Discount");
				System.out.println("2. Discount Percent");
				System.out.println("9. Exit");
				System.out.println("PLease Enter your Option: ");
				int optionSelected = scanner.nextInt();
				if(optionSelected == 1) {
					uInfo.setTypeOfDis(FIXED_DISCOUNT);
					repeat=false;
				} else if(optionSelected == 2) {
					uInfo.setTypeOfDis(DISCOUNT_PERCENT);
					repeat=false;
				}else if (optionSelected == 9) {
					System.out.println("PROGRAM IS EXITING.......");
					System.exit(0);
				}else {
					System.out.println("Fill Correct Option");
					repeat = true;
				}
			}while(repeat);
			
		} catch (Exception exp) {
			System.out.println("ServiceCoupon.getUserInfo()" + exp);
		}
	}
	
	/**
	 * 
	 * @param connection
	 * @return true or false value for Record exist or not,on the basis of PLU,PromotionType,Coupon_Required.
	 */
		public boolean checkData() {
			System.out.println("ServiceCoupon.checkData(): Checking whether the product entry is already in coupon tables ");
			PreparedStatement stmt = null;
			Connection connection = null;
			try {
				if (uInfo == null) {
					System.out.println("ServiceCoupon.checkData(): USER INPUT IS NULL");
					return false;
				}
				pluLong = uInfo.getPluLong();
				startDate = uInfo.getStartDate();
				endDate = uInfo.getEndDate();
				couponRequired = uInfo.getCouponRequired();
				typeOfDis = uInfo.getTypeOfDis();
				disValue = uInfo.getDisValue();
				promotionCode = uInfo.getPromotionCode();
				CouponDbConnection couponDbConnection = new CouponDbConnection();
				connection = couponDbConnection.getBv1Connection(MainConstants.ORAC_DRIVER,MainConstants.BV1_URL,MainConstants.BV1_USERNAME,MainConstants.BV1_PASSWORD);
				
				if(connection != null && !connection.isClosed()) {
					
					stmt = connection.prepareStatement(SELECT_FR_PDLIST);
					stmt.setString(1, pluLong);
					rs = stmt.executeQuery();
					if (rs.next()) {
						uInfo.setMpid(rs.getString("MPID"));
						mpId = uInfo.getMpid();
						if(!checkDataFrPromDetail(connection)) {
							insertFrPromDetail();
						}
						System.out.println("DATA EXISTS FOR mpId: " + rs.getString("MPID")+ " and pluLong: "+pluLong);
					} else {
						System.out.println("NO RESULT SET EXISTS FOR pluLong: "+pluLong);
						return false;
					}
				}
				
			} catch (Exception exp) {
				System.out.println("CoupnDbConnection.main()+++++++++++++++++++++++++++++++++++++++++++++"+ exp);
				return false;
			} finally {
				CouponDbConnection.releaseResources(connection,rs,stmt);
			}
			return true;
		}
	/**
	 * updates the coupon data
	 * @param connection
	 * @return
	 */
	public boolean updateCouponData() {
		System.out.println("ServiceCoupon.updateCouponData(): ENTER FOR UPDATION");
		
		boolean updateFlag = false;
		if(uInfo != null) {
			Integer promotionCode = Integer.parseInt(this.promotionCode);			
			switch(promotionCode) {
			case 36:
				updateFlag = updateFrPromDetail();
				break;
			case 37:
				updateFlag = updateFrPromDetail();
				break;
			default:
				System.out.println("NO SUCH PROMOTION CODE EXISTS IN FRYS");
				break;
			}
		}
		return updateFlag;
	}
/**
 * updates the data in table FrPromDetail
 * 
 */
	public boolean updateFrPromDetail() {
		System.out.println("******************************ServiceCoupon.updateFrPromDetail()");
		PreparedStatement stmt = null;
		Connection connection = null;
		try {
			CouponDbConnection couponDbConnection = new CouponDbConnection();
			connection=couponDbConnection.getBv1Connection(MainConstants.ORAC_DRIVER,MainConstants.BV1_URL,MainConstants.BV1_USERNAME,MainConstants.BV1_PASSWORD);
			if(connection != null && !connection.isClosed()) {
				stmt = connection.prepareStatement(UPDATE_FR_PROM_DETAIL);
				System.out.println("QUERY: "+UPDATE_FR_PROM_DETAIL);
				stmt.setString(1, promotionCode);
				stmt.setString(2, startDate);
				stmt.setString(3, endDate);
				stmt.setString(4, couponRequired);
				if(typeOfDis != null) {
					if(typeOfDis.equals(FIXED_DISCOUNT)) {
						stmt.setString(5, null);
						stmt.setString(6, disValue);
					}else if(typeOfDis.equals(DISCOUNT_PERCENT)) {
						stmt.setString(5, disValue);
						stmt.setString(6, null);
					}
				}else {
					stmt.setString(5, null);
					stmt.setString(6, null);
				}
				stmt.setString(7, pluLong);
				stmt.setString(8, mpId);
				int rowsUpdated = stmt.executeUpdate();
				if(rowsUpdated >0) {
					System.out.println(rowsUpdated + " ROWS Updated Sucessfully in FR_PROM_DETAILS:.....");
					return updateFrPdList(couponDbConnection);
				}else {
					System.out.println("ServiceCoupon.updateFrPromDetail(): No row updated in FR_PROM_DETAILS");
				}
			}else {
				System.out.println("ServiceCoupon.updateFrPromDetail(): Connection not made with BV1TO1");
			}
		} catch (Exception exp) {
			System.out.println("ServiceCoupon.updateFr_Prom_Detail()" + exp);
			exp.printStackTrace();
			return false;
		}
		finally{
			CouponDbConnection.releaseResources(connection,null,stmt);
		}
		return false;
	}
	/**
	 * updates the data in table FrPdList
	 * @param connection
	 * @return
	 */
	public boolean updateFrPdList(CouponDbConnection couponDbConnection) {
		System.out.println("*************ServiceCoupon.updateFrPdList()");
		PreparedStatement stmt =null;
		Connection connection = null;
		try {
			connection=couponDbConnection.getBv1Connection(MainConstants.ORAC_DRIVER,MainConstants.BV1_URL,MainConstants.BV1_USERNAME,MainConstants.BV1_PASSWORD);
			if(connection != null && !connection.isClosed()) {
				stmt= connection.prepareStatement(UDDATE_FR_PDLIST);
				System.out.println("QUERY: "+UDDATE_FR_PDLIST);
	            stmt.setString(1, startDate);
	            stmt.setString(2, endDate);
	            stmt.setString(3, pluLong);
	            stmt.setString(4, mpId);
	            int updatedRows = stmt.executeUpdate();
	            if(updatedRows > 0) {
	            	System.out.println(updatedRows + " ROWS Updated Sucessfully in FR_PDLIST:.....");
	            	if(uInfo.getCouponRequired().equalsIgnoreCase("Y")) {
	            		checkPervasiveData();
	            	}
	            	return true;
	            }else {
					System.out.println("ServiceCoupon.updateFrPromDetail(): No row updated in FR_PDLIST");
				}
			}else {
				System.out.println("ServiceCoupon.updateFrPdList(): Connection not made with BV1TO1");
			}
        } catch (Exception exp) {
			System.out.println("ServiceCoupon.updateFR_PD_LIST()" + exp);
			return false;
		} finally {
			CouponDbConnection.releaseResources(connection,null,stmt);
		}
		return false;
	}
	
	/**
	 * 
	 * @param connection
	 * @return
	 * @throws Exception
	 */
	private boolean checkDataFrPromDetail(Connection connection) throws Exception {
		System.out.println("----------ServiceCoupon.checkDataFrPromDetail()");
		PreparedStatement stmt = null;
		ResultSet rs1 = null;
		if(connection == null || connection.isClosed()) {
			CouponDbConnection couponDbConnection = new CouponDbConnection();
			connection = couponDbConnection.getBv1Connection(MainConstants.ORAC_DRIVER,MainConstants.BV1_URL,MainConstants.BV1_USERNAME,MainConstants.BV1_PASSWORD);
		}
		if(connection != null && !connection.isClosed()) {
			stmt = connection.prepareStatement(SELECT_FR_PROM_DETAILS);
			stmt.setString(1, mpId);
			 rs1=stmt.executeQuery();
			if(rs1.next()) {
				System.out.println("ServiceCoupon.checkDataFrPromDetail(): Mpid: " + mpId+ " exist in fr_prom_details");
				return true;
			}else {
				System.out.println("ServiceCoupon.checkDataFrPromDetail(): Mpid: " + mpId+ " does not exist in fr_prom_details");
			}
		}
		return false;
	}
	/**
	 * insert the values in fr_prom_details table if value doesn't exist for prod_id.
	 */
	public boolean insertFrPromDetail() {
		PreparedStatement stmt =null;
		Connection connection = null;
		boolean fromCheckData = true;
		try{
			CouponDbConnection couponDbConnection = new CouponDbConnection();
			connection = couponDbConnection.getBv1Connection(MainConstants.ORAC_DRIVER, MainConstants.BV1_URL, MainConstants.BV1_USERNAME, MainConstants.BV1_PASSWORD);
			
			if(connection != null && !connection.isClosed()) {
				if(mpId == null || mpId.trim().equals("")) {
					fromCheckData=false;
					List<String> listOfMpIds = new ArrayList<String>();
					stmt = connection.prepareStatement(SELECT_FR_PROM_DETAIL2);
					ResultSet rs = stmt.executeQuery();
					while(rs.next()) {
						listOfMpIds.add(rs.getString("MPID"));
					}
					String mpId = null;
					do {
						mpId = generateRandonNumbers(100000, 999999);
					}while(listOfMpIds.contains(mpId));
					System.out.println("NEW MPID GENERATED: "+mpId);
					uInfo.setMpid(mpId);
					this.mpId = mpId;
				}
				System.out.println("MPID: "+mpId);
				
				String  plu = pluLong!=null ? pluLong.substring(0, pluLong.length()-1):null;
				stmt=connection.prepareStatement(INSERT_FR_PROM_DETAIL);
				stmt.setString(1, plu);
				stmt.setString(2, promotionCode.toString());
				if(typeOfDis != null) {
					if(typeOfDis.equals(DISCOUNT_PERCENT)) {
						stmt.setString(3, uInfo.getDisValue());
						stmt.setString(4, null);
					}else if(typeOfDis.equals(FIXED_DISCOUNT)) {
						stmt.setString(3, null);
						stmt.setString(4, uInfo.getDisValue());
					}
				}else {
					stmt.setString(3, null);
					stmt.setString(4, null);
				}
				stmt.setString(5, startDate);
				stmt.setString(6, endDate);
				stmt.setString(7, pluLong);
				stmt.setString(8, mpId);
				stmt.setString(9, couponRequired);
				int rowInserted = stmt.executeUpdate();
				if(rowInserted > 0) {
					System.out.println(rowInserted+": Rows updated in FR_PROM_DETAILS");
					if(!fromCheckData) {
						return insertFrPdlist(connection,promotionCode.toString(),startDate,endDate,pluLong,mpId,couponRequired);
					}
				}
			}
		}catch(Exception exp) {
			System.out.println("ServiceCoupon.insertFrPromDetail()"+exp);
		}
		
		return false;
	}
	/**
	 * insert the entry into the table fr_pdlist
	 * @param connection
	 * @param prmootionCode
	 * @param startDate
	 * @param endDate
	 * @param pluLong
	 * @param mpId
	 * @param couponRequired
	 * @return
	 */
	public boolean insertFrPdlist(Connection connection,String prmootionCode,String startDate,String endDate,
			String pluLong,String mpId,String couponRequired) {
		try{
			PreparedStatement stmt =null;
		if(connection == null|| connection.isClosed()) {
			CouponDbConnection couponDbConnection =new CouponDbConnection();
			connection = couponDbConnection.getBv1Connection(MainConstants.ORAC_DRIVER, MainConstants.BV1_URL, MainConstants.BV1_USERNAME, MainConstants.BV1_PASSWORD);
		} 
		stmt=connection.prepareStatement(INSERT_INTO_FR_PDLIST);
		stmt.setString(1, mpId);
		stmt.setString(2, pluLong);
		stmt.setString(3, startDate);
		stmt.setString(4, endDate);
		int insertFrPdlist = stmt.executeUpdate();
		if(insertFrPdlist > 0 && couponRequired.equals("N") ) {
			System.out.println(insertFrPdlist+"Record inserted sucessfully in FR_PDLIST");
			return true;
		}else if(couponRequired.equals("Y")) {
			System.out.println(+insertFrPdlist+": ROWS INSERTED IN FR_PDLIST AND COUPON_REQUIRED"+couponRequired);
			return insertFrysCoba();
		}
		   }catch(Exception exp) {
			   System.out.println("ServiceCoupon.insertFrPdlist()"+exp);
			   return false;
		   }
		return false;
	}
	/**
	 * Check the data in pervasive database on the basis of mpid.
	 */
	private boolean checkPervasiveData() {
		System.out.println("********<<<ServiceCoupon.checkPervasiveData()");
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			CouponDbConnection couponDbConnection = new CouponDbConnection();
			connection=couponDbConnection.getPervasiveConnection(MainConstants.PERV_DRIVER, MainConstants.PERV_URL, MainConstants.PERV_USERNAME, MainConstants.PERV_PASSWORD);
			if(connection != null  && !connection.isClosed()) {
				stmt = connection.prepareStatement(SELECT_FRYCOBA);
				stmt.setString(1, mpId);
				rs = stmt.executeQuery();
				if(!rs.next()) {
					System.out.println("ServiceCoupon.checkPervasiveData(): DATA DOES NO EXIST IN PERVASIVE TABLES FOR MPID: "+uInfo.getMpid());
					return insertFrysCoba();
				}else {
					System.out.println("ServiceCoupon.checkPervasiveData(): ID ALREADY EXISTS IN FRYSCOBA");
					String batchNumber = rs.getString(BATCH_NUMBER);
					if(checkFrysCoup(batchNumber)) {
						System.out.println("ServiceCoupon.checkPervasiveData(): ID ALREADY EXISTS IN FRYSCOUP");
						String promoCode = getFrysCoupDetail(batchNumber, mpId);
						coupBean = new FrysCoupBean();
						coupBean.setPromoCode(promoCode);
						serviceCoupon.setCoupBean(coupBean);
						return true;
					}else {
						System.out.println("ServiceCoupon.checkPervasiveData(): ID DOES NOT EXIST IN FRYSCOUP");
						FrysCobaBean coba =new FrysCobaBean();
						coba.setBatchId(batchNumber);
						coba.setMpid(mpId);
						return insertFrysCoup(connection,coba);
					}
				}
			}else {
				System.out.println("ServiceCoupon.updateFrysCoba(): Connection not made with pervasive database");
			}
		}catch(Exception e) {
			System.out.println("ServiceCoupon.checkPervasiveData(): "+e.getMessage());
			System.exit(1);
		}
		return false;
	}
	/**
	 * insert the entry into frysCoba table if the couon_required flag is Y
	 */
	public boolean insertFrysCoba() {
		System.out.println("***********<<<<ServiceCoupon.insertFrysCoba()");
		PreparedStatement stmt=null;
		Connection prvConnection=null;
		List<String> listOfBatchIds = new ArrayList<String>();
		try{
			CouponDbConnection dbConnection = new CouponDbConnection();
		prvConnection=dbConnection.getPervasiveConnection(MainConstants.PERV_DRIVER, MainConstants.PERV_URL,MainConstants.PERV_USERNAME,MainConstants.PERV_PASSWORD);
		FrysCobaBean coba =new FrysCobaBean();
		stmt = prvConnection.prepareStatement(SELECT_FRYSCOBA2);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			listOfBatchIds.add(rs.getString("BATCH_NUMBER"));
		}
		String batchId;
		do {
			batchId = generateRandonNumbers(1,1000);
		}while(listOfBatchIds.contains(batchId)); 
		System.out.println("BATCH ID: "+batchId);
		coba.setBatchId(batchId);
		coba.setMpid(mpId);
	    stmt=prvConnection.prepareStatement(INSET_INTO_FRYSCOBA);
	    stmt.setString(1, batchId);
	    stmt.setString(2, mpId);
	    int insertFryCob = stmt.executeUpdate();
	    if(insertFryCob > 0) {
	    	System.out.println(insertFryCob+": Row updated in Fryscoba Successfully");
	    	return insertFrysCoup(prvConnection,coba);
	    }
		}catch(Exception exp) {
			System.out.println("ServiceCoupon.insertFrysCoba()"+exp);
			return false;
		} finally {
			CouponDbConnection.releaseResources(prvConnection,null,stmt);
		}
		return false;
	}
	/**
	 * insert the entry into table FrysCoup
	 * @param prvConnection
	 * @param cobaBean
	 * @return
	 */
	private boolean insertFrysCoup(Connection prvConnection,FrysCobaBean cobaBean) {
		System.out.println("**********<<ServiceCoupon.insertFrysCoup()");
		PreparedStatement stmt=null;
		try{
			if(prvConnection==null||prvConnection.isClosed()) {
				CouponDbConnection couponDbConnection =new CouponDbConnection();
				prvConnection=couponDbConnection.getPervasiveConnection(MainConstants.PERV_DRIVER, MainConstants.PERV_URL, MainConstants.PERV_USERNAME, MainConstants.PERV_PASSWORD);
			}
			String promo_Code = generateRandonNumbers(1001, 9999);
			System.out.println("******************<<<<<PROMO CODE: "+promo_Code+">>>>>>>>**************");
			String batch=cobaBean.getBatchId();
			stmt=prvConnection.prepareStatement(INSERT_INTO_FRYSCOUP);
			stmt.setString(1, promo_Code);
			stmt.setString(2, batch);
			int insertCoup = stmt.executeUpdate();
			if(insertCoup > 0 ) {
				System.out.println(insertCoup+": Rows inserted in FRYSCOUP table");
				System.out.println("ServiceCoupon.insertFrysCoup(): ALL PERVASIVE TABLES ARE UPDATED>>>> ");
				coupBean = new FrysCoupBean();
				coupBean.setPromoCode(promo_Code);
				serviceCoupon.setCoupBean(coupBean);
				return true;
			}
			else {
				System.out.println("Error While inserting in FRYSCOUP table");
			}
		}catch(Exception exp) {
			System.out.println("ServiceCoupon.insertFrysCoup()"+exp);
			return false;
		}finally {
			CouponDbConnection.releaseResources(prvConnection,null,stmt);
		}
		return false;
	}
	/**
	 * On the basis of the promotion code type call the method. 
	 */
	public boolean insertCouponData() {
		System.out.println("ServiceCoupon.insertCouponData(): INSERTING NEW ENTRY FOR COUPON");
		boolean insertFlag = false;
		if(uInfo != null) {
			Integer promotionCode = Integer.parseInt(uInfo.getPromotionCode());			
			switch(promotionCode) {
			case 36:
				insertFlag = insertFrPromDetail();
				break;
			case 37:
				insertFlag = insertFrPromDetail();
				break;
			case 38:
				insertFlag = insertFrPromDetail();
				break;
			default:
				System.out.println("NO SUCH PROMOTION CODE EXISTS IN FRYS");
				break;
			}
		}
		return insertFlag;
	
	}
	/**
	 * 
	 * @param days
	 * @return current date
	 */
	private String getDate(int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, days);
		return sdf.format(c.getTime());
	}
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @return random number on the basis of parameters at run time
	 */
	private String generateRandonNumbers(int start, int end) {
		 Random rnd = new Random();  
		 long range = (long)end - (long)start + 1;
		 long fraction = (long)(range * rnd.nextDouble());
		 Integer randomNumber =  (int)(fraction + start);    
		 //System.out.println("Generated : " + randomNumber); 
		 System.out.println(randomNumber);
		 return randomNumber.toString();
	}
	
	private String getFrysCoupDetail(String batchNumber, String mpId) {
		System.out.println("ServiceCoupon.getFrysCoupDetail()");
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		CouponDbConnection couponDbConnection = new CouponDbConnection();
		con=couponDbConnection.getPervasiveConnection(MainConstants.PERV_DRIVER, MainConstants.PERV_URL, MainConstants.PERV_USERNAME, MainConstants.PERV_PASSWORD);
		try {
			if(con!=null && !con.isClosed()) {
				stmt = con.prepareStatement(SELECT_FRYSCOUP);
				stmt.setString(1, batchNumber);
				stmt.setString(2, mpId);
				rs = stmt.executeQuery();
				if(rs.next()) {
					return rs.getString(PROMO_CODE);
				}
			}
		} catch (SQLException e) {
			System.out.println("ServiceCoupon.getFrysCoupDetail(): "+e);
		}
		return null;
	}
	
	/**
	 * Make the coupon expired
	 * @return
	 */
	public boolean expireCoupon() {
		System.out.println("ServiceCoupon.expireCoupon()");
		if(uInfo != null) {
			String prodId = uInfo.getPluLong();
			Connection connection = null;
			CouponDbConnection couponDbConnection =new CouponDbConnection();
			connection = couponDbConnection.getBv1Connection(MainConstants.ORAC_DRIVER, MainConstants.BV1_URL, MainConstants.BV1_USERNAME, MainConstants.BV1_PASSWORD);
			PreparedStatement stmt = null;
			try {
				if(connection!=null && !connection.isClosed()) {
					String date = getDate(-10);
					String mpId = uInfo.getMpid();
					System.out.println("ServiceCoupon.expireCoupon(): QUERY -> "+EXPIRE_COUPON_QUERY);
					stmt = connection.prepareStatement(EXPIRE_COUPON_QUERY);
					stmt.setString(1, date);
					stmt.setString(2, prodId);
					stmt.setString(3, mpId);
					if(!stmt.execute()) {
						System.out.println("ServiceCoupon.expireCoupon(): SUCCESSFULLY COUPON HAS EXPIRED");
						return true;
					}else {
						System.out.println("ServiceCoupon.expireCoupon(): EXECUTION OF QUERY NOT PROPER");
					}
					
				}
				System.out.println("ServiceCoupon.expireCoupon(): SOME PROBLEM HAS OCCURED WHILE EXPIRING THE COUPON");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public void deleteFrysCoIfExist() {
		System.out.println("------------ServiceCoupon.deleteFrysCoIfExist(): START ---------------");
		if(uInfo != null) {
			Connection con = null;
			PreparedStatement stmt=null;
			ResultSet rs = null;
			CouponDbConnection couponDbConnection = new CouponDbConnection();
			con=couponDbConnection.getPervasiveConnection(MainConstants.PERV_DRIVER, MainConstants.PERV_URL, MainConstants.PERV_USERNAME, MainConstants.PERV_PASSWORD);
			try {
				if(con!=null && !con.isClosed()) {
					String mpid=uInfo.getMpid();
					stmt = con.prepareStatement(SELECT_FRYSCO);
					stmt.setString(1, mpid);
					rs = stmt.executeQuery();
					if(rs.next()) {
						System.out.println("ServiceCoupon.deleteFrysCoIfExist():  ENTRY EXIST IN FRYSCO FOR MPID: "+mpid);
						stmt = con.prepareStatement(DELETE_FRYSCO);
						stmt.setString(1, mpid);
						if(stmt.execute()) {
						System.out.println("ServiceCoupon.deleteFrysCoIfExist() deleted the entry in frysco of mpId: "+mpid+ " Sucessfully");
						}
					}else {
						System.out.println("ServiceCoupon.deleteFrysCoIfExist()ENTRY DOES NOT EXIST IN FRYSCO FOR MPID: "+mpid);
					}
				}else {
					System.out.println("ServiceCoupon.deleteFrysCoIfExist(): Connection Problem With Pervasive DB");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private boolean checkFrysCoup(String batchNumber) {
		Connection con = null;
		PreparedStatement stmt=null;
		ResultSet rs = null;
		CouponDbConnection couponDbConnection = new CouponDbConnection();
		con=couponDbConnection.getPervasiveConnection(MainConstants.PERV_DRIVER, MainConstants.PERV_URL, MainConstants.PERV_USERNAME, MainConstants.PERV_PASSWORD);
		try {
			if(con!=null && !con.isClosed()) {
				stmt = con.prepareStatement(SELECT_FRYSCOUP);
				stmt.setString(1, batchNumber);
				stmt.setString(2, mpId);
				rs = stmt.executeQuery();
				if(rs.next()) {
					return true;
				}
			}
		} catch (SQLException e) {
			System.out.println("ServiceCoupon.checkFrysCoup()");
			e.printStackTrace();
		}
		
		return false;
	}
}
