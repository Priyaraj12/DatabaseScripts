package com.vc.staging.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.itextpdf.text.pdf.codec.Base64;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ParseException;
import com.vc.staging.dao.AbstractHibernateDAOImpl;
import com.vc.staging.dao.BuilderDataFetchDAO;
import com.vc.staging.dao.LpiDAO;
import com.vc.staging.dao.UserDataFetchDAO;
import com.vc.staging.dto.AgeDTO;
import com.vc.staging.dto.AssetDTO;
import com.vc.staging.dto.AssetFinancingDTO;
import com.vc.staging.dto.CoApplicantFinancialDTO;
import com.vc.staging.dto.CreditScoreDTO;
import com.vc.staging.dto.EducationDTO;
import com.vc.staging.dto.EducationDetailDTO;
import com.vc.staging.dto.EducationInstitutionDTO;
import com.vc.staging.dto.EmployerDTO;
import com.vc.staging.dto.FinalCoApplicantLatestDTO;
import com.vc.staging.dto.FinancialDTO;
import com.vc.staging.dto.GenderDTO;
import com.vc.staging.dto.IncomeTypeDTO;
import com.vc.staging.dto.IndustrySectorDTO;
import com.vc.staging.dto.LenderLpiStatusAndCommentsDTO;
import com.vc.staging.dto.LenderLpiStatusDTO;
import com.vc.staging.dto.LenderUserDTO;
import com.vc.staging.dto.LoanLenderDTO;
import com.vc.staging.dto.LoanTypeDTO;
import com.vc.staging.dto.MaritalStatusDTO;
import com.vc.staging.dto.NavigationPageDTO;
import com.vc.staging.dto.OccupationCategoryDTO;
import com.vc.staging.dto.OccupationTypeDTO;
import com.vc.staging.dto.PerfiosCacheDTO;
import com.vc.staging.dto.RefAssetTypeDTO;
import com.vc.staging.dto.RefEmploymentTypeDTO;
import com.vc.staging.dto.RefProductTypeDTO;
import com.vc.staging.dto.RefSalaryDeductionDTO;
import com.vc.staging.dto.RefSubscriptionTypeDTO;
import com.vc.staging.dto.ReffrequencyDTO;
import com.vc.staging.dto.RelationShipDTO;
import com.vc.staging.dto.ReqResLogDTO;
import com.vc.staging.dto.ResidenceDTO;
import com.vc.staging.dto.SessionManagement;
import com.vc.staging.dto.UserAddressDTO;
import com.vc.staging.dto.UserDataDTO;
import com.vc.staging.dto.UserEmploymentDTO;
import com.vc.staging.dto.UserFinancialDTO;
import com.vc.staging.dto.UserIncomeDTO;
import com.vc.staging.dto.UserInfoDTO;
import com.vc.staging.dto.UserLenderDTO;
import com.vc.staging.dto.UserListDTO;
import com.vc.staging.dto.UserLoanDTO;
import com.vc.staging.dto.UserLoginDTO;

/*
 * 07/11/2019
 * Modified by Geetha - Insert UserCreditSummary and details 
 */
import com.vc.staging.dto.UserCreditInfoDTO;
import com.vc.staging.dto.UserSalaryDeductionDTO;
import com.vc.staging.dto.UserSession;
import com.vc.staging.dto.UserTypeDTO;
import com.vc.staging.dto.YodleeCacheDTO;
import com.vc.staging.dto.ZipDTO;

import com.vc.staging.model.BuilderUserDO;
import com.vc.staging.model.CreditReportInquiryDO;
import com.vc.staging.model.EmployerDO;
import com.vc.staging.model.IndustrySectorDO;
import com.vc.staging.model.LenderDO;
import com.vc.staging.model.LenderDisbursementDO;
import com.vc.staging.model.LenderUserDO;
import com.vc.staging.model.NavigationPageDO;
import com.vc.staging.model.OptVerifyDO;
import com.vc.staging.model.RefAgeDO;
import com.vc.staging.model.RefCreditScoreDO;
import com.vc.staging.model.RefEducationDO;
import com.vc.staging.model.RefEmployerTypeDO;
import com.vc.staging.model.RefGenderDO;
import com.vc.staging.model.RefIncomeTypeDO;
import com.vc.staging.model.RefLenderLpiStatusDO;
import com.vc.staging.model.RefLoanTypeDO;
import com.vc.staging.model.RefMaritalStatusDO;
import com.vc.staging.model.RefOccupationTypeDO;
import com.vc.staging.model.RefPagesDO;
import com.vc.staging.model.RefResidenceCategoryDO;
import com.vc.staging.model.RefSalaryDeductionDO;
import com.vc.staging.model.RefUserTypeDO;
import com.vc.staging.model.RefZipDO;
import com.vc.staging.model.ReferenceCategoryDO;
import com.vc.staging.model.SmsLogDO;
import com.vc.staging.model.SubscriptionDetailDO;
import com.vc.staging.model.SubscriptionSummaryDO;
import com.vc.staging.model.TblRequestLogDO;
import com.vc.staging.model.TblResponseLogDO;
import com.vc.staging.model.UserActivityDO;
import com.vc.staging.model.UserAddressDO;
import com.vc.staging.model.UserCreditReportSummaryDO;
import com.vc.staging.model.UserEmploymentDO;
import com.vc.staging.model.UserIncomeDO;
import com.vc.staging.model.UserInfoDO;
import com.vc.staging.model.UserLenderCommentDO;
import com.vc.staging.model.UserLendersStatusDO;
import com.vc.staging.model.UserLoanDO;
import com.vc.staging.model.UserLoginDO;
import com.vc.staging.model.UserLpiLenderDO;
import com.vc.staging.model.UserSalaryDeductionDO;
import com.vc.staging.model.UserSubscriptionSummaryDO;
import com.vc.staging.model.UserlenderSanctionDetailsDO;
import com.vc.staging.model.LoanAccDetailDO;
import com.vc.staging.model.UserAccountPaymentDO;
import com.vc.staging.util.Crif;

import com.vc.staging.util.Mailsending;
import com.vc.staging.util.UtilityMethods;
import com.vc.staging.util.ValuCirclesConstants;

import net.sf.ehcache.hibernate.HibernateUtil;

/**
 * 
 * @author Thanga
 * 
 */
/**
 * @author Venkat
 *
 */
/**
 * @author Venkat
 *
 */
/**
 * @author Venkat
 *
 */
@Service
public class UserDataFetchServiceImpl extends AbstractHibernateDAOImpl implements UserDataFetchService {

	@Autowired
	UserDataFetchDAO dataFetchDAO;

	@Autowired
	LpiDAO lpiDAO;
	@Autowired
	BuilderDataFetchDAO builderDataFetchDAO;
	@Autowired
	ErrorLogService errorLog;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	SessionFactory sessionFactory;

	// Logger instance
	Logger LOGGER = LoggerFactory.getLogger(UserDataFetchServiceImpl.class);

	/**
	 * Method to verify new email reg
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#verifynewemailreg(java.lang.
	 * String)
	 */
	@Override
	public boolean verifynewemailreg(String userSignUp) {
		boolean newUser = false;
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserLoginDTO dto = mapper.readValue(userSignUp, UserLoginDTO.class);
			if (dto != null) {
				newUser = dataFetchDAO.validateemail(dto.getUserEmail());
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "verifynewemailreg", e.toString(),
					"userSignUp:" + userSignUp);
			e.printStackTrace();
		}
		return newUser;
	}

	/**
	 * Method to verify new email reg
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#verifynewSubscriptionemail(java.
	 * lang.String)
	 */
	@Override
	public boolean verifynewSubscriptionemail(String subscriptionMail) {
		boolean newUser = false;
		ObjectMapper mapper = new ObjectMapper();
		try {
			newUser = dataFetchDAO.validatesubscriptionemail(subscriptionMail);
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "verifynewSubscriptionemail", e.toString(),
					"subscriptionMail:" + subscriptionMail);
			e.printStackTrace();
		}
		return newUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#selectuserId(java.lang.String)
	 */
	public int selectuserId(String userDetails) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		final List<UserLoginDTO> finalList = new ArrayList<>();

		final UserLoginDTO dto = mapper.readValue(userDetails, UserLoginDTO.class);

		String email = dto.getUserEmail();
		String query = "select userId from user_login where userEmail='" + email + "'";
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					dto.setUserId(rs.getInt("userId"));
					finalList.add(dto);
				}
				return null;
			}
		});
		return dto.getUserId();

	}

	/**
	 * Method to insert signUp details
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertSignUpDetails(java.lang.
	 * String)
	 */
	@Override
	public int insertSignUpDetails(String userDetails) {
		int response = 0;
		int respose_uid = 0;
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserLoginDTO dto = mapper.readValue(userDetails, UserLoginDTO.class);
			if (dto != null) {
				UserLoginDO loginDO = new UserLoginDO();
				String salt = UtilityMethods.randomAlphaNumeric();
				String password = UtilityMethods.passwordEncrypt(dto.getUserCreds(), salt);
				loginDO.setActiveStatus(1);
				loginDO.setCreatedBy(dto.getCreatedBy());
				loginDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
				loginDO.setSalt(salt);
				loginDO.setUpdatedBy(dto.getUpdatedBy());
				loginDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				loginDO.setUserCreds(password);
				loginDO.setUserEmail(dto.getUserEmail());
				loginDO.setUserPhone(dto.getUserPhone());
				loginDO.setLastlogin(UtilityMethods.getCurrentDateTime());
				loginDO.setUserLPiStatus(1);
				loginDO.setEmail_verified(1);
				loginDO.setPrimaryUserId(0);
				loginDO.setOtp_verified(0);
				loginDO.setLoginattempts(0);
				loginDO.setSubscriptionId(9999);
				loginDO.setAccountStatus(dto.getaccount_status());
				// loginDO.setWhatsappConsent(Integer.parseInt(dto.getwhatsApp_consent()));
				loginDO.setWhatsappConsent(1);
				/*
				 * 01/24/2020- Geetha Changes for introducing country codes during Signup
				 */
				loginDO.setCountryCode(dto.getCountryCode());
				RefUserTypeDO refUserTypeDO = (RefUserTypeDO) dataFetchDAO.findById(RefUserTypeDO.class,
						dto.getRefUserTypeId());
				if (refUserTypeDO != null) {
					loginDO.setRefUserType(refUserTypeDO);
				}
				dataFetchDAO.save(loginDO);
				respose_uid = loginDO.getUserId();
				OptVerifyDO optVerifyDO = new OptVerifyDO();
				optVerifyDO.setOtp(UtilityMethods.otpGenerate());
				optVerifyDO.setIs_Verified(0);
				optVerifyDO.setMobileNo(dto.getUserPhone());
				optVerifyDO.setActiveStatus(1);
				optVerifyDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				optVerifyDO.setUpdateBy("Valucircles");
				dataFetchDAO.save(optVerifyDO);
				response = optVerifyDO.getId();
				System.out.println("response after optverify.getId, response  " + response);
				if (response > 0) {
					String opt = optVerifyDO.getOtp();
					String msg = System.lineSeparator() + "Your OTP is:" + opt; // included the ':'
					String msg_default = "Thank you for Signing up with ValuCircles - Your Home Loan Advisor.";
					System.out.println("OTP Value for Geetha" + opt);

					SmsLogDO smsLogDO = new SmsLogDO();
					smsLogDO.setMessage(msg);
					String mailId = dto.getUserEmail();
					if (dto.getCountryCode() != null) {
						if ((dto.getCountryCode().equals("+91"))) {
							UtilityMethods.sendSms(dto.getUserPhone(), opt);
						} else {
							msg = msg_default + " " + msg;
							UtilityMethods.otpMail(msg, mailId);
						}

					}
					/* 17/07/2019: Geetha- Removal of code for sending OTP email */
					// UtilityMethods.otpMail(msg, mailId);
					/* 11/12/2019: Geetha- Removal of code for sending verification email */
					/*
					 * Mailsending mailsending = new Mailsending(); //String link =
					 * UtilityMethods.getProperty(ValuCirclesConstants.EMAIL_VERIFICATION_LINK);
					 * /mailsending.sentEmailVerificationLink( link + "token=" +
					 * loginDO.getUserPhone() + "&uid=" + loginDO.getUserId(),
					 * loginDO.getUserEmail());
					 */
					smsLogDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
					smsLogDO.setCreatedBy(dto.getUserEmail());
					smsLogDO.setUpdateOn(UtilityMethods.getCurrentDateTime());
					smsLogDO.setUpdatedBy(dto.getUserEmail());
					smsLogDO.setLastlogin(UtilityMethods.getCurrentDateTime());
					smsLogDO.setMobileNo(dto.getUserPhone());
					dataFetchDAO.save(smsLogDO);
				}
				return respose_uid;
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertSignUpDetails", e.toString(),
					"userDetails:" + userDetails);
			e.printStackTrace();
		}
		return respose_uid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#insertPageDetails(int)
	 */
	@Override
	public int insertPageDetails(int response) {
		NavigationPageDO page = new NavigationPageDO();
		if (response > 0) {
			page.setUserId(response);
			page.setPage_completed(0);
			page.setCoapplicant_page(0);
			dataFetchDAO.save(page);
		}
		return page.getPage_completed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#updatePageDetails(int, int)
	 */
	public int updatePageDetails(int response, int pages) {
		NavigationPageDO page = new NavigationPageDO();
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			page.setPage_completed(pages);
			page.setUserId(response);
			page.setCoapplicant_page(0);
			session.update(page);

		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "updatePageDetails", e.toString(), "userDetails:" + "");
			e.printStackTrace();
		} finally {
			tx.commit();
			session.flush();
			session.clear();
			session.close();
		}

		return page.getPage_completed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#selectPageDetails(int)
	 */
	public int selectPageDetails(int userId) {
		final List<NavigationPageDTO> finalList = new ArrayList<>();
		final NavigationPageDTO dto = new NavigationPageDTO();

		String query = "SELECT page_completed FROM navigate_pages  where userId='" + userId + "'";
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					dto.setPage_completed(rs.getInt("page_completed"));
					finalList.add(dto);
				}
				return null;
			}
		});

		return dto.getPage_completed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#selectcoapplicant(int)
	 */
	public int selectcoapplicant(int userId) {
		final List<UserInfoDTO> finalList = new ArrayList<>();
		final UserInfoDTO dto = new UserInfoDTO();

		String query = "SELECT coApplicantId FROM user_info  where userId='" + userId + "'";
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					dto.setCoApplicantId(rs.getInt("coApplicantId"));
					finalList.add(dto);
				}
				return null;
			}
		});

		return dto.getCoApplicantId();
	}

	/**
	 * @param userId
	 * @return
	 */
	public int selectuserid(int userId) {
		final List<UserLoginDTO> finalList = new ArrayList<>();
		final UserLoginDTO dto = new UserLoginDTO();

		String query = "SELECT primaryUserId FROM user_login  where userId='" + userId + "'";
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					dto.setPrimaryUserId(rs.getInt("primaryUserId"));
					finalList.add(dto);
				}
				return null;
			}
		});

		return dto.getPrimaryUserId();
	}

	/**
	 * @param userId
	 * @return
	 */
	public int selecCreditInfoid(int userId) {
		final ArrayList<UserCreditInfoDTO> finalList = new ArrayList<>();
		final UserCreditInfoDTO dto = new UserCreditInfoDTO();
		String query = "SELECT userCreditInfoId FROM user_credit_info  where userId='" + userId + "'";
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {

					dto.setUserCreditInfoId(rs.getInt("userCreditInfoId"));
					finalList.add(dto);

				}
				return null;
			}
		});

		return dto.getUserCreditInfoId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#selectpages(int)
	 */
	@Override
	public String selectpages(int userId) {
		// TODO Auto-generated method stub
		final List<NavigationPageDTO> finalList = new ArrayList<>();

		String query = "SELECT page_completed FROM navigate_pages  where userId='" + userId + "'";
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {

				while (rs.next()) {
					NavigationPageDTO dto = new NavigationPageDTO();
					dto.setPage_completed(rs.getInt("page_completed"));
					finalList.add(dto);
				}
				return null;
			}
		});

		return finalList.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#updatecoapplicantPageDetails(int,
	 * int)
	 */
	public int updatecoapplicantPageDetails(int response, int pages) {

		NavigationPageDO page = new NavigationPageDO();
		page.setUserId(response);
		page.setCoapplicant_page(pages);
		dataFetchDAO.save(page);
		return page.getCoapplicant_page();
	}

	// fetch primary user address for co applicant
	public String fetchaddress(String userId) {

		String address = dataFetchDAO.fetchaddress(userId);
		return address;
	}
	// Method to get User Login details

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#userLogin(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public UserLoginDTO userLogin(String emailId, String password) {
		String salt = dataFetchDAO.getSalt(emailId);
		String pass = UtilityMethods.passwordEncrypt(password, salt);
		UserLoginDO loginDO = dataFetchDAO.userLoginDetails(emailId, pass);
		UserLoginDTO loginDTO = null;
		if (loginDO != null) {
			loginDTO = new UserLoginDTO();
			loginDTO.setActiveStatus(loginDO.getActiveStatus());
			loginDTO.setCreatedBy(loginDO.getCreatedBy());
			loginDTO.setCreatedOn(loginDO.getCreatedOn());
			loginDO.setLastlogin(UtilityMethods.getCurrentDateTime());
			loginDTO.setSalt(loginDO.getSalt());
			loginDTO.setUpdatedBy(loginDO.getUpdatedBy());
			loginDTO.setUpdatedOn(loginDO.getUpdatedOn());
			loginDTO.setUserCreds(loginDO.getUserCreds());
			loginDTO.setUserEmail(loginDO.getUserEmail());
			loginDTO.setUserId(loginDO.getUserId());
			loginDTO.setUserPhone(loginDO.getUserPhone());
			loginDTO.setRefUserTypeId(loginDO.getRefUserType().getRefUserTypeId());
			loginDTO.setPrimaryUserId(loginDO.getPrimaryUserId());
			loginDTO.setSubscriptionId(loginDO.getSubscriptionId());
			loginDTO.setUserLPiStatus(loginDO.getUserLPiStatus());
			loginDTO.setEmail_verified(loginDO.getEmail_verified());
			loginDTO.setOtpVerified(loginDO.getOtp_verified());
			/*
			 * 01/24/2019 -Geetha: Send OTP Mail for NRI customer
			 * 
			 */

			loginDTO.setCountryCode(loginDO.getCountryCode());
			/*
			 * 09/04/2020 -Geetha: Retrieve promo code entered by User during signup
			 * 
			 */
			loginDTO.setAccount_status(loginDO.getAccountStatus());
			UserInfoDO userInfoDO = dataFetchDAO.getUserName(loginDO.getUserId());
			if (userInfoDO != null) {
				loginDTO.setUserName(userInfoDO.getFirstName());
			} else {
				loginDTO.setUserName("Guest");
			}

			LenderUserDO lenderUserDO = dataFetchDAO.getLenderId(loginDO.getUserId());
			if (lenderUserDO != null) {
				loginDTO.setLenderId(lenderUserDO.getLender().getLenderId());
				loginDTO.setLenderName(lenderUserDO.getLender().getLenderName());
			} else {
				loginDTO.setLenderName("Lender");
			}
			BuilderUserDO builderUserDO = dataFetchDAO.getBuilderNameUsingUserId(loginDO.getUserId());
			if (builderUserDO != null) {
				loginDTO.setBuilderName(builderUserDO.getBuilder().getBuilderName());
				loginDTO.setBuilderId(builderUserDO.getBuilder().getBuilderId());
			} else {
				loginDTO.setBuilderName("Builder");
				loginDTO.setBuilderId(0);
			}

			loginDTO.setLoginattempts(0);
			loginDTO.setActiveStatus(1);
		}
		return loginDTO;
	}

	/**
	 * Method to get List of Gender
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfGender()
	 */
	@Override
	public String getListOfGender() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<GenderDTO> list = dataFetchDAO.getListOfGender();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {

			errorLog.insertErrorLog("UserDataFetchController", "getListOfGender", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * Method to get List of Frequency
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOffrequency()
	 */
	@Override
	public String getListOffrequency() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<ReffrequencyDTO> list = dataFetchDAO.getListOffrequency();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {

			errorLog.insertErrorLog("UserDataFetchController", "getListOffrequency", e.toString(), "");
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	/**
	 * Method to get List of Marital Status
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfMaritalStatus()
	 */
	@Override
	public String getListOfMaritalStatus() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<MaritalStatusDTO> list = dataFetchDAO.getListOfMaritalStatus();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getListOfMaritalStatus", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * Method to insert UserActivity
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#addUserActivity(int,
	 * java.lang.String)
	 */
	@Override
	public int addUserActivity(int userId, String activity) {
		if (userId > 0) {
			UserLoginDO loginDO = (UserLoginDO) dataFetchDAO.findById(UserLoginDO.class, userId);
			if (loginDO != null) {
				UserActivityDO activityDO = new UserActivityDO();
				activityDO.setActiveStatus(1);
				activityDO.setActivity(activity);
				activityDO.setCreatedBy(loginDO.getUserEmail());
				activityDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
				activityDO.setCustomerData(loginDO.getUserEmail());
				activityDO.setUpdatedBy(loginDO.getUserEmail());
				activityDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				activityDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				activityDO.setUserId(userId);
				dataFetchDAO.save(activityDO);
				return activityDO.getId();
			} else {

				UserActivityDO activityDO = new UserActivityDO();
				activityDO.setActiveStatus(0);
				activityDO.setActivity(activity);
				activityDO.setCreatedBy(loginDO.getUserEmail());
				activityDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
				activityDO.setCustomerData(loginDO.getUserEmail());
				activityDO.setUpdatedBy(loginDO.getUserEmail());
				activityDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				activityDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				activityDO.setUserId(userId);
				dataFetchDAO.save(activityDO);
				return activityDO.getId();
			}
		}
		return 0;
	}

	/**
	 * Method to insert gender details
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertGender(java.lang.String)
	 */
	@Override
	public boolean insertGender(String genderDetails) {
		boolean isInserted = false;
		ObjectMapper mapper = new ObjectMapper();
		try {
			GenderDTO genderDTO = mapper.readValue(genderDetails, GenderDTO.class);
			if (genderDTO != null) {
				RefGenderDO genderDO = new RefGenderDO();
				genderDO.setActiveStatus(1);
				genderDO.setCreatedBy(genderDTO.getCreatedBy());
				genderDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
				genderDO.setGender(genderDTO.getGender());
				genderDO.setUpdatedBy(genderDTO.getUpdatedBy());
				genderDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				dataFetchDAO.save(genderDO);
				if (genderDO.getGenderId() > 0) {
					isInserted = true;
				}
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertGender", e.toString(),
					"genderDetails:" + genderDetails);
			e.printStackTrace();
		}
		return isInserted;
	}

	/**
	 * Method to insert marital status
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertMaritalStatus(java.lang.
	 * String)
	 */
	@Override
	public boolean insertMaritalStatus(String maritalStatusDetails) {
		boolean isInserted = false;
		ObjectMapper mapper = new ObjectMapper();
		try {
			MaritalStatusDTO maritalStatusDTO = mapper.readValue(maritalStatusDetails, MaritalStatusDTO.class);
			if (maritalStatusDTO != null) {
				RefMaritalStatusDO maritalStatusDO = new RefMaritalStatusDO();
				maritalStatusDO.setActiveStatus(1);
				maritalStatusDO.setCreatedBy(maritalStatusDTO.getCreatedBy());
				maritalStatusDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
				maritalStatusDO.setMaritalStatus(maritalStatusDTO.getMaritalStatus());
				maritalStatusDO.setUpdatedBy(maritalStatusDTO.getUpdatedBy());
				maritalStatusDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				dataFetchDAO.save(maritalStatusDO);
				if (maritalStatusDO.getMaritalStatusId() > 0) {
					isInserted = true;
				}
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertMaritalStatus", e.toString(),
					"maritalStatusDetails:" + maritalStatusDetails);
			e.printStackTrace();
		}
		return isInserted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#deleteGender(int)
	 */
	@Override
	public boolean deleteGender(int genderId) {
		RefGenderDO genderDO = null;
		Object object = dataFetchDAO.findById(RefGenderDO.class, genderId);
		try {
			if (object != null) {
				genderDO = (RefGenderDO) object;
				genderDO.setActiveStatus(0);
				genderDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				dataFetchDAO.save(genderDO);
				return true;
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "deleteGender", e.toString(), "genderId:" + genderId);
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#deleteMaritalStatus(int)
	 */
	@Override
	public boolean deleteMaritalStatus(int maritalStatusId) {
		RefMaritalStatusDO maritalStatusDO = null;
		Object object = dataFetchDAO.findById(RefMaritalStatusDO.class, maritalStatusId);
		try {
			if (object != null) {
				maritalStatusDO = (RefMaritalStatusDO) object;
				maritalStatusDO.setActiveStatus(0);
				maritalStatusDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				dataFetchDAO.save(maritalStatusDO);
				return true;
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "deleteMaritalStatus", e.toString(),
					"maritalStatusId:" + maritalStatusId);
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertOccupationType(java.lang.
	 * String)
	 */
	@Override
	public boolean insertOccupationType(String occupationDetails) {
		boolean isInserted = false;
		ObjectMapper mapper = new ObjectMapper();
		try {
			OccupationTypeDTO occupationTypeDTO = mapper.readValue(occupationDetails, OccupationTypeDTO.class);
			if (occupationTypeDTO != null) {
				RefOccupationTypeDO occupationTypeDO = new RefOccupationTypeDO();
				occupationTypeDO.setActiveStatus(1);
				occupationTypeDO.setCreatedBy(occupationTypeDO.getCreatedBy());
				occupationTypeDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
				occupationTypeDO.setOccupationType(occupationTypeDO.getOccupationType());
				occupationTypeDO.setUpdatedBy(occupationTypeDO.getUpdatedBy());
				occupationTypeDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				dataFetchDAO.save(occupationTypeDO);
				if (occupationTypeDO.getOccupationTypeId() > 0) {
					isInserted = true;
				}
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertOccupationType", e.toString(),
					"occupationDetails:" + occupationDetails);
			e.printStackTrace();
		}
		return isInserted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#deleteOccupationType(int)
	 */
	@Override
	public boolean deleteOccupationType(int occupationTypeId) {
		RefOccupationTypeDO occupationTypeDO = null;
		Object object = dataFetchDAO.findById(RefOccupationTypeDO.class, occupationTypeId);
		try {
			if (object != null) {
				occupationTypeDO = (RefOccupationTypeDO) object;
				occupationTypeDO.setActiveStatus(0);
				occupationTypeDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				dataFetchDAO.save(occupationTypeDO);
				return true;
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "deleteOccupationType", e.toString(),
					"occupationTypeId:" + occupationTypeId);
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfOccupationType()
	 */
	@Override
	public String getListOfOccupationType() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<OccupationTypeDTO> list = dataFetchDAO.getListOfOccupationType();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getListOfOccupationType", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getListOfOccupationCategory(int)
	 */
	@Override
	public String getListOfOccupationCategory(int OccupationtypeId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<OccupationCategoryDTO> list = dataFetchDAO.getListOfOccupationCategory(OccupationtypeId);
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getListOfOccupationType", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#insertUserData(int,
	 * java.lang.String)
	 */
	@Override
	public boolean insertUserData(int userId, String userDetails) {
		ObjectMapper mapper = new ObjectMapper();
		UserLoginDO loginDO = (UserLoginDO) dataFetchDAO.findById(UserLoginDO.class, userId);
		if (loginDO != null) {
			try {
				UserInfoDTO dto = mapper.readValue(userDetails, UserInfoDTO.class);
				byte[] address = Base64.decode(dto.getAddress1());
				byte[] addressSecond = Base64.decode(dto.getAddress2());
				if (dto != null) {

					// user address insert
					UserAddressDO addressDO = new UserAddressDO();
					if (loginDO.getUserAddresses().size() > 0) {
						addressDO = loginDO.getUserAddresses().get(0);
					}
					addressDO.setActiveStatus(1);
					addressDO.setAddress1(new String(address));
					addressDO.setAddress2(new String(addressSecond));
					addressDO.setAddress3(dto.getAddress3());
					addressDO.setZipid(dto.getPostalZip());
					addressDO.setUserLogin(loginDO);
					addressDO.setCountry(dto.getCountry());
					dataFetchDAO.save(addressDO);
					if (addressDO.getUserAddressId() > 0) {

					}
					// user info insert
					UserInfoDO infoDO = new UserInfoDO();
					if (loginDO.getUserInfos().size() > 0) {
						infoDO = loginDO.getUserInfos().get(0);
					}

					infoDO.setUserAddress(addressDO);
					infoDO.setDateOfBirth(dto.getDateofBirth());
					infoDO.setFirstName(dto.getFirstName());
					infoDO.setLastName(dto.getLastName());
					infoDO.setUserLogin(loginDO);
					infoDO.setActiveStatus(1);
					infoDO.setCreatedBy(dto.getFirstName());
					infoDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
					infoDO.setUpdatedBy(dto.getUpdatedBy());
					infoDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
					if (dto.getCoApplicantId() == 0) {
						dataFetchDAO.deactivateCoApplicants(userId);
						infoDO.setCoApplicantId(dto.getCoApplicantId());
						int response1 = selectPageDetails(userId);
						if (response1 == 1) {
							int pages = 3;
							int page = updatePageDetails(userId, pages);
						}
					} else {
						infoDO.setCoApplicantId(dto.getCoApplicantId());
					}
					infoDO.setPropertyIdentifierId(dto.getPropertyIdentifierId());
					infoDO.setCompareaddrId(dto.getCompareaddrId());
					infoDO.sethelpWithProperty(dto.gethelpWithProperty());
					RefMaritalStatusDO maritalStatusDO = (RefMaritalStatusDO) dataFetchDAO
							.findById(RefMaritalStatusDO.class, dto.getMaritalStatusId());
					if (maritalStatusDO != null) {
						infoDO.setRefMaritalStatus(maritalStatusDO);
					}
					RefEducationDO educationDO = (RefEducationDO) dataFetchDAO.findById(RefEducationDO.class,
							dto.getEducationId());
					if (educationDO != null) {
						infoDO.setRefEducation(educationDO);
					}

					RefGenderDO genderDO = (RefGenderDO) dataFetchDAO.findById(RefGenderDO.class, dto.getGenderId());
					if (genderDO != null) {
						infoDO.setRefGender(genderDO);
					}

					RefAgeDO refAgeDO = dataFetchDAO.getAgeDetails(dto.getRefAgeId());
					if (refAgeDO != null) {
						infoDO.setRefAge(refAgeDO);
					}

					RefResidenceCategoryDO categoryDO = (RefResidenceCategoryDO) dataFetchDAO
							.findById(RefResidenceCategoryDO.class, dto.getRefResidenceCategoryId());
					if (categoryDO != null) {
						infoDO.setRefResidenceCategory(categoryDO);
					}
					infoDO.setEducationDetailsId(dto.getEducationDetailsId());
					infoDO.setEducationInstitutionId(dto.getEducationInstitutionId());
					dataFetchDAO.save(infoDO);
					// 05/14/2019[Geetha N]- Removed the obsolete code that checks for property
					// identification from PersonalInfo page
					// if (infoDO.getPropertyIdentifierId() == 0) {
					// boolean response = dataFetchDAO.updateUserAsset(userId);
					// }
					return true;
				}
			} catch (Exception e) {
				errorLog.insertErrorLog("UserDataFetchController", "insertUserData", e.toString(), "userId:" + userId);
				e.printStackTrace();
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfEducation()
	 */
	@Override
	public String getListOfEducation() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<EducationDTO> list = dataFetchDAO.getListOfEducation();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getListOfEducation", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfEmployer()
	 */
	@Override
	public String getListOfEmployer() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<EmployerDTO> list = dataFetchDAO.getListOfEmployer();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getListOfEmployer", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getSummaryDetails(int)
	 */
	@Override
	public String getSummaryDetails(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		UserDataDTO dataDTO = dataFetchDAO.getUserInfoByUserId(userId);
		List<UserLoanDTO> loanDTO = dataFetchDAO.getUserloanByUserId(userId);
		UserEmploymentDTO employmentDTO = dataFetchDAO.getUserEmploymentByuserId(userId);
		List<UserIncomeDTO> userIncomeDTO = dataFetchDAO.getUserIncomelistById(userId);
		UserFinancialDTO financialDTO = dataFetchDAO.getUserFinancialByUserId(userId);
		List<UserSalaryDeductionDTO> userSalaryDeductionDTO = dataFetchDAO.getUserSalaryDeductionByUserId(userId);
		AssetDTO assetDTO = builderDataFetchDAO.getListOfAssetUsingByUserId(userId);
		AssetFinancingDTO assetFinancingDTO = builderDataFetchDAO.getAssetFinancingDetailsUsingUserId(userId);
		try {
			jsonObject.put(ValuCirclesConstants.USERINFO, mapper.writeValueAsString(dataDTO));
			jsonObject.put(ValuCirclesConstants.USERINCOME, mapper.writeValueAsString(userIncomeDTO));
			jsonObject.put(ValuCirclesConstants.USERLOAN, mapper.writeValueAsString(loanDTO));
			jsonObject.put(ValuCirclesConstants.USERSALARYDEDUCTION, mapper.writeValueAsString(userSalaryDeductionDTO));
			jsonObject.put(ValuCirclesConstants.USEREMPLOYMENT, mapper.writeValueAsString(employmentDTO));
			jsonObject.put(ValuCirclesConstants.USERFINANCIAL, mapper.writeValueAsString(financialDTO));
			jsonObject.put("AssetInfo", mapper.writeValueAsString(assetDTO));
			jsonObject.put("AssetFinancial", mapper.writeValueAsString(assetFinancingDTO));
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getSummaryDetails", e.toString(), "userId:" + userId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfAge()
	 */
	@Override
	public String getListOfAge() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<AgeDTO> list = dataFetchDAO.getListOfAge();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getListOfAge", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfCreditScore()
	 */
	@Override
	public String getListOfCreditScore() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<CreditScoreDTO> list = dataFetchDAO.getListOfCreditScore();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getListOfCreditScore", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfLoanType()
	 */
	@Override
	public String getListOfLoanType() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<LoanTypeDTO> list = dataFetchDAO.getListOfLoanType();
		try {
			jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getListOfLoanType", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfIndustrySector()
	 */
	@Override
	public String getListOfIndustrySector() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<IndustrySectorDTO> list = dataFetchDAO.getListOfIndustrySector();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getListOfIndustrySector", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfIncomeType()
	 */
	@Override
	public String getListOfIncomeType() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<IncomeTypeDTO> list = dataFetchDAO.getListOfIncomeType();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getListOfIncomeType", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#insertFinancialData(int,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean insertFinancialData(int userId, String financialDetails, String loanDetails, String incomeDetails,
			String salaryDeduction) {
		ObjectMapper mapper = new ObjectMapper();
		UserLoginDO loginDO = (UserLoginDO) dataFetchDAO.findById(UserLoginDO.class, userId);
		if (loginDO != null) {
			try {
				FinancialDTO dto = mapper.readValue(financialDetails, FinancialDTO.class);
				byte[] employerName = Base64.decode(dto.getEmployerName());
				if (dto != null) {
					EmployerDO employerDO = dataFetchDAO.getEmployerDetails(new String(employerName));
					if (employerDO == null) {
						employerDO = new EmployerDO();
						ValuCirclesConstants.employerChanged = true;
						employerDO.setActiveStatus(1);
						employerDO.setCreatedBy(dto.getCreatedBy());
						employerDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
						employerDO.setEmployerName(new String(employerName));
						employerDO.setUpdatedBy(dto.getUpdatedBy());
						employerDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
						employerDO.setWeightPct(BigDecimal.ZERO);
						ReferenceCategoryDO referenceCategory1 = (ReferenceCategoryDO) dataFetchDAO
								.findById(ReferenceCategoryDO.class, 1);
						if (referenceCategory1 != null) {
							employerDO.setReferenceCategory(referenceCategory1);
						}
						dataFetchDAO.save(employerDO);
						if (employerDO.getEmployerId() > 0) {

						}
						ValuCirclesConstants.employerCache.clear();
					}
					// user employment insert

					UserEmploymentDO employmentDO = new UserEmploymentDO();
					if (loginDO.getUserEmployments().size() > 0) {
						employmentDO = loginDO.getUserEmployments().get(0);
					}
					employmentDO.setActiveStatus(1);
					employmentDO.setCreatedBy(dto.getCreatedBy());
					employmentDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
					employmentDO.setUpdatedBy(dto.getUpdatedBy());
					employmentDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
					employmentDO.setUserLogin(loginDO);
					employmentDO.setYearsOfServiceInIndustry(dto.getYearsofServiceinIndustry());
					employmentDO.setYearsOfServicewithEmployer(dto.getYearsofServicewithEmployer());
					employmentDO.setPensionEligibility(dto.getPensionEligibility());
					employmentDO.setCin(dto.getCin());
					employmentDO.setEmployerName(new String(employerName));
					employmentDO.setOccupationcategoryid(dto.getOccupationcategoryid());
					employmentDO.setCategorydetail(dto.getCategorydetail());
					/* 16/08/2019: Geetha- Add IT returns field in the financial page */
					employmentDO.setITfiled(dto.getITfiled());
					//
					employmentDO.setprimaryBank(dto.getprimaryBank());
					employmentDO.setBusinessConstitution(dto.getBusinessConstitution());
					employmentDO.setannualSales(dto.getannualSales());
					employmentDO.setannualProfit(dto.getannualProfit());
					employmentDO.setOfficeType(dto.getOfficeType());
					employmentDO.setemployeeCount(dto.getemployeeCount());
					employmentDO.setOwnershipShare(dto.getOwnershipShare());
					employmentDO.setannualProfitPY(dto.getannualProfitPY());
					employmentDO.setAnnualSalesPY(dto.getannualSalesPY());
					//
					int id = 0;

					String classification = dto.getClassification();
					if (!classification.isEmpty() || !classification.equalsIgnoreCase("") || classification != ""
							|| classification != null) {
						String id2 = dataFetchDAO.getclassificationid(classification);
						if (id2 == "") {

						} else {
							id = Integer.parseInt(id2);
							RefEmployerTypeDO id3 = new RefEmployerTypeDO();
							id3.setEmployerTypeId(id);
							employmentDO.setRefEmployerType(id3);
						}

						RefOccupationTypeDO occupationTypeDO = (RefOccupationTypeDO) dataFetchDAO
								.findById(RefOccupationTypeDO.class, dto.getOccupationTypeId());
						if (occupationTypeDO != null) {
							employmentDO.setOccupationType(occupationTypeDO);
						}
						employmentDO.setEmployer(employerDO);
						RefCreditScoreDO creditScoreDO = (RefCreditScoreDO) dataFetchDAO
								.findById(RefCreditScoreDO.class, dto.getCreditScoreId());
						if (creditScoreDO != null) {
							employmentDO.setRefCreditScore(creditScoreDO);
						}

						RefEmployerTypeDO refEmployerTypeDO = (RefEmployerTypeDO) dataFetchDAO
								.findById(RefEmployerTypeDO.class, dto.getEmployerTypeId());
						if (refEmployerTypeDO != null) {
							employmentDO.setRefEmployerType(refEmployerTypeDO);
						}
						dataFetchDAO.save(employmentDO);
						if (employmentDO.getUserEmploymentId() > 0) {
							ValuCirclesConstants.userEmploymentCache.put(loginDO.getUserId(), employmentDO);
						}
						// user income insert
						boolean isUpdateUserIncome = dataFetchDAO.updateUserIncome(userId);
						List<UserIncomeDTO> list1 = UtilityMethods.parseJsonToObject(incomeDetails, ArrayList.class,
								UserIncomeDTO.class);
						LOGGER.info(" Json Income detail: {} Parsed income details : {}", incomeDetails, list1);
						if (list1 != null) {
							for (UserIncomeDTO incomeDTO : list1) {
								UserIncomeDO incomeDO = (UserIncomeDO) dataFetchDAO.findById(UserIncomeDO.class,
										incomeDTO.getUserIncomeId());
								if (incomeDO == null) {
									incomeDO = new UserIncomeDO();
								}
								incomeDO.setActiveStatus(1);
								incomeDO.setCreatedBy(dto.getCreatedBy());
								incomeDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
								incomeDO.setUpdatedBy(incomeDTO.getUpdatedBy());
								incomeDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
								incomeDO.setUserEmploymentInfo(incomeDTO.getUserEmpoymentInfo());
								incomeDO.setUserGrossIncome(incomeDTO.getUserNetIncome());
								incomeDO.setFrequencyType(incomeDTO.getFrequencyType());
								incomeDO.setUserLogin(loginDO);
								incomeDO.setUserNetIncome(incomeDTO.getUserNetIncome());
								incomeDO.setManualEntry(1);
								RefIncomeTypeDO incomeTypeDO = (RefIncomeTypeDO) dataFetchDAO
										.findById(RefIncomeTypeDO.class, incomeDTO.getRefIncomeTypeId());
								if (incomeTypeDO != null) {
									incomeDO.setRefIncomeType(incomeTypeDO);
								}
								dataFetchDAO.save(incomeDO);
								if (incomeDO.getUserIncomeId() > 0) {
								}
							}
						}
						// user loan insert
						boolean isUpdateUserLoan = dataFetchDAO.updateUserLoan(userId);
						List<UserLoanDTO> list = UtilityMethods.parseJsonToObject(loanDetails, ArrayList.class,
								UserLoanDTO.class);
						if (list != null) {
							for (UserLoanDTO loanDTO : list) {
								UserLoanDO loanDO = (UserLoanDO) dataFetchDAO.findById(UserLoanDO.class,
										loanDTO.getUserLoansId());
								if (loanDO == null) {
									loanDO = new UserLoanDO();
								}
								loanDO.setActiveStatus(1);
								loanDO.setCreatedBy(dto.getCreatedBy());
								loanDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
								loanDO.setUpdatedBy(loanDTO.getUpdatedBy());
								loanDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
								loanDO.setUserLogin(loginDO);
								loanDO.setUserLoanOutstandingPrincipal(loanDTO.getUserLoanOutstandingPrincipal());
								loanDO.setUserLoanPaymentAmount(loanDTO.getUserLoanPaymentAmount());
								loanDO.setManualEntry(1);
								loanDO.setUserLenderName(loanDTO.getUserLenderName());
								RefLoanTypeDO loanTypeDO = (RefLoanTypeDO) dataFetchDAO.findById(RefLoanTypeDO.class,
										loanDTO.getLoanTypeId());
								if (loanTypeDO != null) {
									loanDO.setRefLoanType(loanTypeDO);
								}
								loanDO.setUserLoanRemainingTenure(loanDTO.getUserLoanRemainingTenure());
								dataFetchDAO.save(loanDO);
							}

						}

						// user salary deduction
						boolean isUpdateUserSalaryDeduction = dataFetchDAO.updateUserSalaryDeduction(userId);
						List<UserSalaryDeductionDTO> list2 = UtilityMethods.parseJsonToObject(salaryDeduction,
								ArrayList.class, UserSalaryDeductionDTO.class);
						if (list1 != null) {
							for (UserSalaryDeductionDTO salaryDTO : list2) {
								UserSalaryDeductionDO salaryDO = (UserSalaryDeductionDO) dataFetchDAO
										.findById(UserSalaryDeductionDO.class, salaryDTO.getUsersaldednsId());
								if (salaryDO == null) {
									salaryDO = new UserSalaryDeductionDO();
								}
								salaryDO.setActiveStatus(1);
								salaryDO.setCreatedBy(dto.getCreatedBy());
								salaryDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
								salaryDO.setUpdatedBy(salaryDTO.getUpdatedBy());
								salaryDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
								salaryDO.setAmount(salaryDTO.getAmount());
								salaryDO.setUserLogin(loginDO);
								RefSalaryDeductionDO refSalaryDeductionDO = (RefSalaryDeductionDO) dataFetchDAO
										.findById(RefSalaryDeductionDO.class, salaryDTO.getSalaryDednTypeId());
								if (refSalaryDeductionDO != null) {
									salaryDO.setRefSalaryDeduction(refSalaryDeductionDO);
								}
								dataFetchDAO.save(salaryDO);
								if (salaryDO.getUsersaldednsId() > 0) {
								}
							}
						}

						return true;
					}
				}
			} catch (Exception e) {

				errorLog.insertErrorLog("UserDataFetchController", "getListOfIncomeType", e.toString(),
						"userId:" + userId + "financialDetails" + financialDetails + "loanDetails" + loanDetails
								+ "incomeDetails" + incomeDetails + "salaryDeduction" + salaryDeduction);
				e.printStackTrace();
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getUserListData()
	 */
	@Override
	public String getUserListData() {
		ObjectMapper mapper = new ObjectMapper();
		List<UserListDTO> list = dataFetchDAO.getUserListData();
		try {
			if (list != null) {
				return mapper.writeValueAsString(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfResidence()
	 */
	@Override
	public String getListOfResidence() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<ResidenceDTO> list = dataFetchDAO.getListOfResidence();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getLpiFromSP(int)
	 */
	@Override
	public String getLpiFromSP(int userId) {
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> callValue = dataFetchDAO.getLpiFromSP(userId);
		try {
			jsonObject.put("minValue", callValue.get("myminscore"));
			jsonObject.put("maxValue", callValue.get("mymaxscore"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getEducationDetailByEducationId(
	 * int)
	 */
	@Override
	public String getEducationDetailByEducationId(int educationId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<EducationDetailDTO> list = dataFetchDAO.getEducationDetailByEducationId(educationId);
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#
	 * getEducationInstitutionByEducationDetailId(int)
	 */
	@Override
	public String getEducationInstitutionByEducationDetailId(int educationDetailId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<EducationInstitutionDTO> list = dataFetchDAO.getEducationInstitutionByEducationDetailId(educationDetailId);
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfEducationDetails()
	 */
	@Override
	public String getListOfEducationDetails() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<EducationDetailDTO> list = dataFetchDAO.getListOfEducationDetails();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getListOfEducationInstitution()
	 */
	@Override
	public String getListOfEducationInstitution() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<EducationInstitutionDTO> list = dataFetchDAO.getListOfEducationInstitution();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#forgotPassword(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String forgotPassword(String emailId, String siteName) {
		JSONObject jsonObject = new JSONObject();
		UserLoginDO userLoginDO = dataFetchDAO.getuserDetails(emailId);
		if (userLoginDO != null) {
			UtilityMethods.forgotEmail(userLoginDO.getUserId(), userLoginDO.getUserEmail(), siteName);
		}
		try {
			if (userLoginDO != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#resetPassword(java.lang.String,
	 * int, java.lang.String, java.lang.String)
	 */
	@Override
	public String resetPassword(String emailId, int userId, String newpwd, String sessionId) {
		return dataFetchDAO.resetPassword(emailId, userId, newpwd, sessionId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#changePassword(int,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String changePassword(int userId, String oldpwd, String newpwd) {
		return dataFetchDAO.changePassword(userId, oldpwd, newpwd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfZip()
	 */
	@Override
	public String getListOfZip() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		if (ValuCirclesConstants.ZIPCACHE.length() == 0) {
			List<RefZipDO> list = dataFetchDAO.getListOfZip();
			try {
				if (list != null) {
					jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
					ValuCirclesConstants.ZIPCACHE = jsonObject.toString();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ValuCirclesConstants.ZIPCACHE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#updateUserFinancials(int)
	 */
	@Override
	public boolean updateUserFinancials(int userId) {
		return dataFetchDAO.updateUserFinancials(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getZipInfo(java.lang.String)
	 */
	@Override
	public String getZipInfo(String zipCode) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<ZipDTO> list = dataFetchDAO.getZipInfo(zipCode);
		try {
			jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#otpverify(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public int otpverify(String mobileNo, String otp, String ipAddress) {
		int response = 0;
		long diffMinutes = 0;
		OptVerifyDO optVerifyDO = dataFetchDAO.getOtpDetails(mobileNo);
		/*
		 * Geetha -Commented for OTPExpiry functionality
		 * optVerifyDO.setUpdateBy(ipAddress);
		 * 
		 * optVerifyDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
		 */
		if (optVerifyDO != null) {
			String otptbl = optVerifyDO.getOtp();
			String dbotpupdatedatetime = optVerifyDO.getUpdatedOn();
			String otpverifytime = UtilityMethods.getCurrentDateTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss", Locale.getDefault());
			try {
				Date date1 = format.parse(dbotpupdatedatetime);
				Date date2 = format.parse(otpverifytime);

				long diff = date2.getTime() - date1.getTime();
				diffMinutes = diff / (60 * 1000);
				System.out.print(diffMinutes + " Minutes.");
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (diffMinutes >= 5) {
				response = -300;
			} else if (otptbl.equals(otp)) {
				optVerifyDO.setIs_Verified(1);
				optVerifyDO.setIpAddress(ipAddress);
				optVerifyDO.setUpdateBy(ipAddress);
				optVerifyDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				dataFetchDAO.save(optVerifyDO);
				if (optVerifyDO.getId() > 0) {

					boolean isSaved = dataFetchDAO.updateOtpVerify(mobileNo);
					if (isSaved) {
						response = optVerifyDO.getId();
					}
				}
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getOtpVerifyDetails(java.lang.
	 * String)
	 */
	@Override
	public OptVerifyDO getOtpVerifyDetails(String mobileNo) {
		return dataFetchDAO.getOtpDetails(mobileNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getLoanTerm(int)
	 */
	@Override
	public int getLoanTerm(int userId) {
		int response = 0;
		UserInfoDO userInfoDO = dataFetchDAO.getUserInfoDetails(userId);
		if (userInfoDO != null) {
			try {
				int dob = UtilityMethods.getAgeUsingDateOfBirth(userInfoDO.getDateOfBirth());
				/* eligible loan term code */
				int totalMonths = 720;
				int loanTerm = 360;
				int curMonth = UtilityMethods.getCurrentMonth();
				int dobMonth = UtilityMethods.getMonthUsingDOB(userInfoDO.getDateOfBirth());
				int ss = curMonth - dobMonth;
				int months = (dob * 12) + ss;
				int differMonths = totalMonths - months;
				if (loanTerm > differMonths) {
					response = differMonths;

				} else if (differMonths <= 0) {
					response = 0;
				} else {
					response = loanTerm;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getYodleeScore(int)
	 */
	@Override
	public String getYodleeScore(int userId) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (ValuCirclesConstants.yodleeScoreCache.containsKey(userId)) {
				return mapper.writeValueAsString(ValuCirclesConstants.yodleeScoreCache.get(userId));
			} else {
				YodleeCacheDTO yodlee = dataFetchDAO.getYodleeScore(userId);
				return mapper.writeValueAsString(yodlee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfUserType()
	 */
	@Override
	public String getListOfUserType() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<UserTypeDTO> list = dataFetchDAO.getListOfUserType();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertfbDetails(com.vc.staging.
	 * dto.UserLoginDTO)
	 */
	@Override
	public UserLoginDTO insertfbDetails(UserLoginDTO userLoginDTO) {
		int response = 0;
		try {
			UserLoginDO userLoginDO = new UserLoginDO();
			userLoginDO.setActiveStatus(1);
			userLoginDO.setUserEmail(userLoginDTO.getUserEmail());
			userLoginDO.setUserCreds(userLoginDTO.getUserCreds());
			userLoginDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
			userLoginDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
			userLoginDO.setLastlogin(UtilityMethods.getCurrentDateTime());
			dataFetchDAO.save(userLoginDO);
			response = userLoginDO.getUserId();
			userLoginDTO.setUserId(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userLoginDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getCustomerFromEmail(java.lang.
	 * String)
	 */
	@Override
	public UserLoginDTO getCustomerFromEmail(String userEmail) {
		UserLoginDTO userLoginDTO = null;
		UserLoginDO userLoginDO = dataFetchDAO.getuserDetails(userEmail);
		if (userLoginDO != null) {
			userLoginDTO = new UserLoginDTO();
			userLoginDTO.setUserId(userLoginDO.getUserId());
			userLoginDTO.setUserEmail(userLoginDO.getUserEmail());
			userLoginDTO.setUserCreds(userLoginDO.getUserCreds());
		}
		return userLoginDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertGmailUser(com.vc.staging.
	 * dto.UserLoginDTO)
	 */
	@Override
	public UserLoginDTO insertGmailUser(UserLoginDTO userLoginDTO) {
		int response = 0;
		try {
			UserLoginDO userLoginDO = new UserLoginDO();
			userLoginDO.setActiveStatus(1);
			userLoginDO.setUserEmail(userLoginDTO.getUserEmail());
			userLoginDO.setUserCreds(userLoginDTO.getUserCreds());
			userLoginDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
			userLoginDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
			userLoginDO.setLastlogin(UtilityMethods.getCurrentDateTime());
			dataFetchDAO.save(userLoginDO);
			response = userLoginDO.getUserId();
			userLoginDTO.setUserId(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userLoginDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getLenderNameList()
	 */
	@Override
	public String getLenderNameList() {
		ObjectMapper mapper = new ObjectMapper();
		List<LenderDO> list = dataFetchDAO.getgetLenderNameList();
		if (list != null) {
			try {
				return mapper.writeValueAsString(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfLoanLender()
	 */
	@Override
	public String getListOfLoanLender() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<LoanLenderDTO> list = dataFetchDAO.getListOfLoanLender();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#resendOtp(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean resendOtp(String mobileNo, String userEmail, String type) {
		boolean isChanged = false;
		OptVerifyDO optVerifyDO = dataFetchDAO.getUserOtp(mobileNo);
		optVerifyDO.setOtp(UtilityMethods.otpGenerate());
		optVerifyDO.setUpdateBy("Valucircles");
		optVerifyDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
		String msg = System.lineSeparator() + "Your OTP is: "; // included the ':'
		String msg_resendotp = "New OTP from ValuCircles - Your Home Loan Advisor.";

		if (optVerifyDO != null) {
			String otpCode = optVerifyDO.getOtp();
			if (type.equals("sms")) {
				UtilityMethods.sendSms(mobileNo, otpCode);
			} else {
				msg = msg_resendotp + " " + msg + otpCode;
				UtilityMethods.otpMail(msg, userEmail);
			}
			isChanged = true;
		}
		return isChanged;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#isEmailRegistered(java.lang.
	 * String)
	 */
	@Override
	public boolean isEmailRegistered(String userEmail) {
		boolean newUser = false;
		try {
			newUser = dataFetchDAO.validateemail(userEmail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#callSubcription(int,
	 * java.lang.String, int, int)
	 */
	@Override
	public String callSubcription(int subscriptionId, String subKey, int count, int subTypeId) {
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> callValue = dataFetchDAO.callSubcription(subscriptionId, subKey, count, subTypeId);
		try {
			jsonObject.put("result", callValue.get("retval"));
			jsonObject.put("SubscriptionCount", getTotalSubscriptionforSubscriptor(subscriptionId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#AssignSubscription(int,
	 * java.lang.String, int)
	 */
	@Override
	public String AssignSubscription(int subscriptorId, String emailId, int mysubtype) {
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> callValue = dataFetchDAO.AssignSubscription(subscriptorId, emailId, mysubtype);
		try {
			jsonObject.put("result", callValue.get("retval"));
			jsonObject.put("SubscriptionCount", getTotalSubscriptionforSubscriptor(subscriptorId));
			jsonObject.put("AsingnedSubscription", ListOfAssignSubscription(subscriptorId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#
	 * getTotalSubscriptionforSubscriptor(int)
	 */
	@Override
	public String getTotalSubscriptionforSubscriptor(int subscriptorId) {
		JSONObject jsonObject = new JSONObject();
		Gson gsonObj = new Gson();
		Map<String, Object> callValue = dataFetchDAO.getTotalSubscriptionforSubscriptor(subscriptorId);
		try {
			String jsonStr = gsonObj.toJson(callValue);
			jsonObject.put("result", jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#ListOfAssignSubscription(int)
	 */
	@Override
	public String ListOfAssignSubscription(int subscriptorId) {
		JSONObject jsonObject = new JSONObject();
		Gson gsonObj = new Gson();
		Map<String, Object> callValue = dataFetchDAO.ListOfAssignSubscription(subscriptorId);
		try {
			String jsonStr = gsonObj.toJson(callValue);
			jsonObject.put("result", jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getListOfAllGetMethodPersonal(
	 * int)
	 */
	@Override
	public String getListOfAllGetMethodPersonal(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();

		List<GenderDTO> genderDTO = dataFetchDAO.getListOfGender();
		List<MaritalStatusDTO> maritalStatusDTO = dataFetchDAO.getListOfMaritalStatus();
		List<EducationDTO> educationDTO = dataFetchDAO.getListOfEducation();
		List<EducationDetailDTO> educationDetailDTO = dataFetchDAO.getListOfEducationDetails();
		List<EducationInstitutionDTO> educationInstitutionDTO = dataFetchDAO.getListOfEducationInstitution();
		List<ResidenceDTO> residenceDTO = dataFetchDAO.getListOfResidence();
		List<RelationShipDTO> relationShipDTO = dataFetchDAO.getListOfRefRelationShip();
		try {
			jsonObject.put("Gender", mapper.writeValueAsString(genderDTO));
			jsonObject.put("MaritalStatus", mapper.writeValueAsString(maritalStatusDTO));
			jsonObject.put("Education", mapper.writeValueAsString(educationDTO));
			jsonObject.put("EducationDetails", mapper.writeValueAsString(educationDetailDTO));
			jsonObject.put("EducationInstitution", mapper.writeValueAsString(educationInstitutionDTO));
			jsonObject.put("ResidenceCategory", mapper.writeValueAsString(residenceDTO));
			jsonObject.put("RelationShip", mapper.writeValueAsString(relationShipDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getListOfAllGetMethodFinancial(
	 * int)
	 */
	@Override
	public String getListOfAllGetMethodFinancial(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<OccupationTypeDTO> occupationTypeDTO = dataFetchDAO.getListOfOccupationType();
		List<CreditScoreDTO> creditScoreDTO = dataFetchDAO.getListOfCreditScore();
		List<IncomeTypeDTO> incomeTypeDTO = dataFetchDAO.getListOfIncomeType();
		List<ReffrequencyDTO> frequencytypeDTO = dataFetchDAO.getListOffrequency();
		List<LoanLenderDTO> loanLenderDTO = dataFetchDAO.getListOfLoanLender();
		List<LoanTypeDTO> loanTypeDTO = dataFetchDAO.getListOfLoanType();
		List<EmployerDTO> employerDTO = dataFetchDAO.getListOfEmployer();
		List<IndustrySectorDTO> industrySectorDTO = dataFetchDAO.getListOfIndustrySector();
		List<RefEmploymentTypeDTO> refEmploymentTypeDTO = dataFetchDAO.getListOfRefEmploymentType();
		List<RefSalaryDeductionDTO> refSalaryDeductionDTO = dataFetchDAO.getListOfRefSalaryDeduction();
		try {
			jsonObject.put("OccupationType", mapper.writeValueAsString(occupationTypeDTO));
			jsonObject.put("CreditScore", mapper.writeValueAsString(creditScoreDTO));
			jsonObject.put("IncomeType", mapper.writeValueAsString(incomeTypeDTO));
			jsonObject.put("frequencyType", mapper.writeValueAsString(frequencytypeDTO));
			jsonObject.put("LoanLenderType", mapper.writeValueAsString(loanLenderDTO));
			jsonObject.put("LoanType", mapper.writeValueAsString(loanTypeDTO));
			jsonObject.put("EmployerName", mapper.writeValueAsString(employerDTO));
			jsonObject.put("IndustrySector", mapper.writeValueAsString(industrySectorDTO));
			jsonObject.put("EmploymentType", mapper.writeValueAsString(refEmploymentTypeDTO));
			jsonObject.put("SalaryDeduction", mapper.writeValueAsString(refSalaryDeductionDTO));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#
	 * getSubscriptionCountAndListOfAssignSubscription(int)
	 */
	@Override
	public String getSubscriptionCountAndListOfAssignSubscription(int subscriptorId) {
		JSONObject jsonObject = new JSONObject();
		Gson gsonObj = new Gson();
		Map<String, Object> callValue1 = dataFetchDAO.getTotalSubscriptionforSubscriptor(subscriptorId);
		Map<String, Object> callValue2 = dataFetchDAO.ListOfAssignSubscription(subscriptorId);
		try {
			String jsonStr = gsonObj.toJson(callValue1);
			jsonObject.put("result1", jsonStr);
			jsonStr = gsonObj.toJson(callValue2);
			jsonObject.put("result2", jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getLpiPageListApi(int,
	 * java.lang.String)
	 */
	@Override
	public String getLpiPageListApi(int userId, String sessionId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		UserDataDTO dataDTO = dataFetchDAO.getUserInfoByUserId(userId);
		UserEmploymentDTO employmentDTO = dataFetchDAO.getUserEmploymentByuserId(userId);
		UserFinancialDTO financialDTO = dataFetchDAO.getUserFinancialByUserId(userId);
		String coApplicantIncome = dataFetchDAO.getCoApplicantIncomeCredits(userId);
		String incomeCredits;
		if (coApplicantIncome != null) {
			incomeCredits = coApplicantIncome;
		} else {
			incomeCredits = "0";
		}

		try {
			jsonObject.put("userInfo", mapper.writeValueAsString(dataDTO));
			jsonObject.put("userEmployee", mapper.writeValueAsString(employmentDTO));
			jsonObject.put("userFinancial", mapper.writeValueAsString(financialDTO));
			jsonObject.put("coApplicantIncome", incomeCredits);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#verifynewMobileReg(java.lang.
	 * String)
	 */
	@Override
	public boolean verifynewMobileReg(String userSignUp) {
		boolean newMobile = false;
		ObjectMapper mapper = new ObjectMapper();
		try {
			UserLoginDTO dto = mapper.readValue(userSignUp, UserLoginDTO.class);
			if (dto != null) {
				newMobile = dataFetchDAO.validateMobile(dto.getUserPhone(), dto.getCountryCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newMobile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getPerfiosScore(int)
	 */
	@Override
	public String getPerfiosScore(int userId) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (ValuCirclesConstants.perfiosScoreCache.containsKey(userId)) {
				return mapper.writeValueAsString(ValuCirclesConstants.perfiosScoreCache.get(userId));
			} else {
				PerfiosCacheDTO perfios = dataFetchDAO.getPerfiosScore(userId);
				return mapper.writeValueAsString(perfios);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getSubcriptionKey(int)
	 */
	@Override
	public String getSubcriptionKey(int userId) {
		JSONObject jsonObject = new JSONObject();
		UserLoginDO loginDO = (UserLoginDO) dataFetchDAO.findById(UserLoginDO.class, userId);
		if (loginDO != null) {
			try {
				SubscriptionDetailDO detailDO = dataFetchDAO.getSubcriptionKey(loginDO.getUserEmail());
				if (detailDO != null) {
					jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
				} else {
					jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#insertUserSubcription(int,
	 * int)
	 */
	@Override
	public boolean insertUserSubcription(int userId, int paymentId) {
		if (paymentId > 0) {
			UserLoginDO loginDO = (UserLoginDO) dataFetchDAO.findById(UserLoginDO.class, userId);
			if (loginDO != null) {
				SubscriptionSummaryDO summaryDO = new SubscriptionSummaryDO();
				summaryDO.setSubscriptionCount(1);
				summaryDO.setUsedCount(0);
				summaryDO.setUserId(loginDO.getUserId());
				summaryDO.setActiveStatus(1);
				String subKey = UtilityMethods.getSubscriptiondigit();
				String output = subKey.toString();
				String userKey = UtilityMethods.getUserIdKeyWithStar(userId);
				String key = userKey + output;
				summaryDO.setSubscriptionKey(key);
				dataFetchDAO.save(summaryDO);
				if (summaryDO.getSubscriptionSummaryid() > 0) {

				}
				UserSubscriptionSummaryDO userSubscriptionSummaryDO = new UserSubscriptionSummaryDO();
				userSubscriptionSummaryDO.setSubscriptionKey(summaryDO.getSubscriptionKey());
				userSubscriptionSummaryDO.setSubscriptionSummary(summaryDO);
				userSubscriptionSummaryDO.setUserId(loginDO.getUserId());
				userSubscriptionSummaryDO.setActiveStatus(1);
				dataFetchDAO.save(userSubscriptionSummaryDO);
				if (userSubscriptionSummaryDO.getUserSubscriptionSummaryId() > 0) {

				}
				SubscriptionDetailDO detailDO = new SubscriptionDetailDO();
				detailDO.setAssignedUsereMail(loginDO.getUserEmail());
				detailDO.setStatusId(1);
				detailDO.setSubscriptionKey(summaryDO.getSubscriptionKey());
				detailDO.setSubscriptionSummary(summaryDO);
				detailDO.setActiveStatus(1);
				dataFetchDAO.save(detailDO);
				if (detailDO.getSubscriptionDetailId() > 0) {
					summaryDO.setUsedCount(1);
					dataFetchDAO.save(summaryDO);
				}
			}
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getUserInfoDetailsUsingViews(int)
	 */
	@Override
	public String getUserInfoDetailsUsingViews(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		UserDataDTO dataDTO = dataFetchDAO.getUserInfoDetailsUsingViews(userId);
		if (dataDTO != null) {
			try {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(dataDTO));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getCoApplicantId(int)
	 */
	@Override
	public String getCoApplicantId(final int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> callValue = dataFetchDAO.getCoApplicantId(userId);
		try {
			jsonObject.put("result", mapper.writeValueAsString(callValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#
	 * getCoApplicantDetailsUsingListOfUserId(java.lang.String)
	 */
	@Override
	public String getCoApplicantDetailsUsingListOfUserId(String listOfUserId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<UserDataDTO> list = dataFetchDAO.getCoApplicantDetailsUsingListOfUserId(listOfUserId);
		if (list != null) {
			try {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#
	 * insertMultipleUserCoApplicantwithLogin(int, java.lang.String)
	 */
	@Override
	public boolean insertMultipleUserCoApplicantwithLogin(int userId, String mulUserCoAppDetails) {
		boolean isUpdateUserCoApplicantInfo = dataFetchDAO.updateUserCoApplicantInfo(userId);
		boolean isUpdateUserCoApplicantAddress = dataFetchDAO.updateUserCoApplicantAddress(userId);
		boolean isUpdateUserCoApplicantLogin = dataFetchDAO.updateUserCoApplicantLogin(userId);
		UserLoginDO loginDO = (UserLoginDO) dataFetchDAO.findById(UserLoginDO.class, userId);
		if (loginDO != null) {
			try {
				List<UserDataDTO> dto = UtilityMethods.parseJsonToObject(mulUserCoAppDetails, ArrayList.class,
						UserDataDTO.class);
				if (dto != null) {
					for (UserDataDTO applicantDTO : dto) {
						// insert login details for co applicant
						String password = loginDO.getUserCreds();
						String salt = loginDO.getSalt();
						String email = loginDO.getUserEmail();
						String phone = loginDO.getUserPhone();
						int refUserTypeId = loginDO.getRefUserType().getRefUserTypeId();
						UserLoginDO userLoginDO = (UserLoginDO) dataFetchDAO.findById(UserLoginDO.class,
								applicantDTO.getUserId());
						if (userLoginDO == null) {
							userLoginDO = new UserLoginDO();
						}
						userLoginDO.setActiveStatus(1);
						userLoginDO.setCreatedBy("");
						userLoginDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
						userLoginDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
						userLoginDO.setRelationshipId(applicantDTO.getRelationshipId());
						userLoginDO.setUserCreds(password);
						userLoginDO.setUserPhone(phone);
						userLoginDO.setUserEmail(email);
						userLoginDO.setSalt(salt);
						userLoginDO.setLastlogin(UtilityMethods.getCurrentDateTime());
						userLoginDO.setUserLPiStatus(1);
						RefUserTypeDO refUserTypeDO = (RefUserTypeDO) dataFetchDAO.findById(RefUserTypeDO.class,
								refUserTypeId);
						if (refUserTypeDO != null) {
							userLoginDO.setRefUserType(refUserTypeDO);
						}
						userLoginDO.setPrimaryUserId(userId);
						dataFetchDAO.save(userLoginDO);
						if (userLoginDO.getUserId() > 0) {

						}

						byte[] address = Base64.decode(applicantDTO.getAddress1());
						byte[] addressSecond = Base64.decode(applicantDTO.getAddress2());
						// user address insert
						UserAddressDO addressDO = dataFetchDAO.getUserAddress(userLoginDO.getUserId());
						if (addressDO == null) {
							addressDO = new UserAddressDO();
						}
						addressDO.setActiveStatus(1);
						addressDO.setAddress1(applicantDTO.getAddress1());
						addressDO.setAddress2(applicantDTO.getAddress2());
						addressDO.setAddress3(applicantDTO.getAddress3());
						addressDO.setZipid(applicantDTO.getZipId());
						addressDO.setUserLogin(userLoginDO);
						addressDO.setCountry(applicantDTO.getCountry());
						dataFetchDAO.save(addressDO);
						if (addressDO.getUserAddressId() > 0) {

						}
						// user info insert

						byte[] addressONE = Base64.decode(applicantDTO.getAddress1());
						byte[] addressTWO = Base64.decode(applicantDTO.getAddress2());
						UserInfoDO applicantDO = dataFetchDAO.getUserInfoDT(userLoginDO.getUserId());
						if (applicantDO == null) {
							applicantDO = new UserInfoDO();
						}
						applicantDO.setActiveStatus(1);
						applicantDO.setUserAddress(addressDO);
						applicantDO.setCreatedBy(applicantDTO.getCreatedBy());
						applicantDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
						applicantDO.setDateOfBirth(applicantDTO.getDateofBirth());
						applicantDO.setEducationDetailsId(applicantDTO.getEducationDetailsId());
						applicantDO.setEducationInstitutionId(applicantDTO.getEducationInstitutionId());
						applicantDO.setFirstName(applicantDTO.getFirstName());
						applicantDO.setLastName(applicantDTO.getLastName());
						RefMaritalStatusDO maritalStatusDO = (RefMaritalStatusDO) dataFetchDAO
								.findById(RefMaritalStatusDO.class, applicantDTO.getMaritalStatusId());
						if (maritalStatusDO != null) {
							applicantDO.setRefMaritalStatus(maritalStatusDO);
						}
						RefEducationDO educationDO = (RefEducationDO) dataFetchDAO.findById(RefEducationDO.class,
								applicantDTO.getEducationId());
						if (educationDO != null) {
							applicantDO.setRefEducation(educationDO);
						}

						RefGenderDO genderDO = (RefGenderDO) dataFetchDAO.findById(RefGenderDO.class,
								applicantDTO.getGenderId());
						if (genderDO != null) {
							applicantDO.setRefGender(genderDO);
						}
						RefAgeDO refAgeDO = dataFetchDAO.getAgeDetails(applicantDTO.getRefAgeId());
						if (refAgeDO != null) {
							applicantDO.setRefAge(refAgeDO);
						}
						RefResidenceCategoryDO categoryDO = (RefResidenceCategoryDO) dataFetchDAO
								.findById(RefResidenceCategoryDO.class, applicantDTO.getRefResidenceCategoryId());
						if (categoryDO != null) {
							applicantDO.setRefResidenceCategory(categoryDO);
						}
						applicantDO.setUserLogin(userLoginDO);
						applicantDO.setUpdatedBy(applicantDTO.getUpdatedBy());
						applicantDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
						dataFetchDAO.save(applicantDO);
					}

					return true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#
	 * insertMultipleUserCoApplicantFinancials(int, java.lang.String)
	 */
	@Override
	public boolean insertMultipleUserCoApplicantFinancials(int userId, String mulUserCoAppFinancialDetails) {
		List<UserLoginDO> list = dataFetchDAO.getUserLoginForCoApplicant(userId);
		if (list != null) {
			boolean isUpdateCoApplicantUserEmployment = dataFetchDAO.updateCoApplicantEmployment(userId);
			boolean isUpdateCoApplicantIncome = dataFetchDAO.updateCoApplicantIncome(userId);
			boolean isUpdateCoApplicantLoan = dataFetchDAO.updateCoApplicantLoan(userId);
			boolean isUpdateCoApplicantSalaryDeduction = dataFetchDAO.updateCoApplicantSalaryDeduction(userId);
			boolean isUpdateUserFinancial = dataFetchDAO.updateCoApplicantuserFinancial(userId);
			int i = 0;

			List<CoApplicantFinancialDTO> applicantFinancialDTO = UtilityMethods
					.parseJsonToObject(mulUserCoAppFinancialDetails, ArrayList.class, CoApplicantFinancialDTO.class);

			if (applicantFinancialDTO != null) {
				for (UserLoginDO loginDO : list) {
					if (i < applicantFinancialDTO.size()) {
						UserEmploymentDO employmentDO = dataFetchDAO
								.getUserCoApplicantEmployments(applicantFinancialDTO.get(i).getUserId());

						byte[] employerName1 = Base64.decode(applicantFinancialDTO.get(i).getEmployerName());
						// Employer insert if not available
						EmployerDO employerDO = dataFetchDAO.getEmployerDetails(new String(employerName1));
						if (employerDO == null) {
							employerDO = new EmployerDO();
							ValuCirclesConstants.employerChanged = true;
							employerDO.setActiveStatus(1);
							employerDO.setCreatedBy(applicantFinancialDTO.get(i).getCreatedBy());
							employerDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
							employerDO.setEmployerName(new String(employerName1));
							employerDO.setUpdatedBy(applicantFinancialDTO.get(i).getUpdatedBy());
							employerDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
							employerDO.setWeightPct(BigDecimal.ZERO);
							ReferenceCategoryDO referenceCategory1 = (ReferenceCategoryDO) dataFetchDAO
									.findById(ReferenceCategoryDO.class, 1);
							if (referenceCategory1 != null) {
								employerDO.setReferenceCategory(referenceCategory1);
							}

							// employerDO.setIndustrySector(sectorDO);
							dataFetchDAO.save(employerDO);
							if (employerDO.getEmployerId() > 0) {

							}
						}

						// user employment insert

						if (employmentDO == null) {
							employmentDO = new UserEmploymentDO();
						}
						employmentDO.setActiveStatus(1);
						employmentDO.setCreatedBy(applicantFinancialDTO.get(i).getCreatedBy());
						employmentDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
						employmentDO.setUpdatedBy(applicantFinancialDTO.get(i).getUpdatedBy());
						employmentDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
						employmentDO.setUserLogin(loginDO);
						employmentDO.setYearsOfServiceInIndustry(
								applicantFinancialDTO.get(i).getYearsofServiceinIndustry());
						employmentDO.setYearsOfServicewithEmployer(
								applicantFinancialDTO.get(i).getYearsofServicewithEmployer());
						employmentDO.setPensionEligibility(applicantFinancialDTO.get(i).getPensionEligibility());
						employmentDO.setCin(applicantFinancialDTO.get(i).getCin());
						employmentDO.setEmployerName(new String(employerName1));
						employmentDO.setOccupationcategoryid(applicantFinancialDTO.get(i).getOccupationcategoryid());
						employmentDO.setCategorydetail(applicantFinancialDTO.get(i).getCategorydetail());
						String classification = applicantFinancialDTO.get(i).getClassification();
						int id = 0;
						if (!classification.isEmpty() || !classification.equalsIgnoreCase("") || classification != ""
								|| classification != null) {
							String id2 = dataFetchDAO.getclassificationid(classification);
							if (id2 == "") {

							} else {
								id = Integer.parseInt(id2);
								RefEmployerTypeDO id3 = new RefEmployerTypeDO();
								id3.setEmployerTypeId(id);
								employmentDO.setRefEmployerType(id3);
							}
						}
						/* 08-18-2019: Geetha - Including ITfiled option on coapplicant page */
						employmentDO.setITfiled(applicantFinancialDTO.get(i).getITfiled());
						employmentDO.setprimaryBank(applicantFinancialDTO.get(i).getprimaryBank());
						employmentDO.setBusinessConstitution(applicantFinancialDTO.get(i).getBusinessConstitution());
						employmentDO.setannualSales(applicantFinancialDTO.get(i).getannualSales());
						employmentDO.setannualProfit(applicantFinancialDTO.get(i).getannualProfit());
						employmentDO.setOfficeType(applicantFinancialDTO.get(i).getOfficeType());
						employmentDO.setemployeeCount(applicantFinancialDTO.get(i).getemployeeCount());
						employmentDO.setOwnershipShare(applicantFinancialDTO.get(i).getOwnershipShare());
						employmentDO.setannualProfitPY(applicantFinancialDTO.get(i).getannualProfitPY());
						employmentDO.setAnnualSalesPY(applicantFinancialDTO.get(i).getannualSalesPY());

						RefOccupationTypeDO occupationTypeDO = (RefOccupationTypeDO) dataFetchDAO.findById(
								RefOccupationTypeDO.class, applicantFinancialDTO.get(i).getOccupationTypeId());
						if (occupationTypeDO != null) {
							employmentDO.setOccupationType(occupationTypeDO);
						}
						employmentDO.setEmployer(employerDO);
						// employmentDO.setIndustrySector(sectorDO);
						RefCreditScoreDO creditScoreDO = (RefCreditScoreDO) dataFetchDAO
								.findById(RefCreditScoreDO.class, applicantFinancialDTO.get(i).getRefCreditScoreId());
						if (creditScoreDO != null) {
							employmentDO.setRefCreditScore(creditScoreDO);
						}

						dataFetchDAO.save(employmentDO);
						if (employmentDO.getUserEmploymentId() > 0) {

						}
						// user income insert

						for (UserIncomeDTO incomeDTO : applicantFinancialDTO.get(i).getUserIncome()) {
							UserIncomeDO incomeDO = (UserIncomeDO) dataFetchDAO.findById(UserIncomeDO.class,
									incomeDTO.getUserIncomeId());
							if (incomeDO == null) {
								incomeDO = new UserIncomeDO();
							}
							incomeDO.setActiveStatus(1);
							incomeDO.setCreatedBy(incomeDTO.getCreatedBy());
							incomeDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
							incomeDO.setUpdatedBy(incomeDTO.getUpdatedBy());
							incomeDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
							incomeDO.setUserEmploymentInfo(incomeDTO.getUserEmpoymentInfo());
							incomeDO.setUserGrossIncome(incomeDTO.getUserNetIncome());
							incomeDO.setUserLogin(loginDO);
							incomeDO.setUserNetIncome(incomeDTO.getUserNetIncome());
							incomeDO.setManualEntry(1);
							incomeDO.setFrequencyType(incomeDTO.getFrequencyType());
							RefIncomeTypeDO incomeTypeDO = (RefIncomeTypeDO) dataFetchDAO
									.findById(RefIncomeTypeDO.class, incomeDTO.getRefIncomeTypeId());
							if (incomeTypeDO != null) {
								incomeDO.setRefIncomeType(incomeTypeDO);
							}
							dataFetchDAO.save(incomeDO);
							if (incomeDO.getUserIncomeId() > 0) {
							}
						}
						// user loan insert

						for (UserLoanDTO loanDTO : applicantFinancialDTO.get(i).getUserLoan()) {
							UserLoanDO loanDO = (UserLoanDO) dataFetchDAO.findById(UserLoanDO.class,
									loanDTO.getUserLoansId());
							if (loanDO == null) {
								loanDO = new UserLoanDO();
							}
							loanDO.setActiveStatus(1);
							loanDO.setCreatedBy(loanDTO.getCreatedBy());
							loanDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
							loanDO.setUpdatedBy(loanDTO.getUpdatedBy());
							loanDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
							loanDO.setUserLogin(loginDO);
							loanDO.setUserLoanOutstandingPrincipal(loanDTO.getUserLoanOutstandingPrincipal());
							loanDO.setUserLoanPaymentAmount(loanDTO.getUserLoanPaymentAmount());
							loanDO.setManualEntry(1);
							loanDO.setUserLenderName(loanDTO.getUserLenderName());
							RefLoanTypeDO loanTypeDO = (RefLoanTypeDO) dataFetchDAO.findById(RefLoanTypeDO.class,
									loanDTO.getLoanTypeId());
							if (loanTypeDO != null) {
								loanDO.setRefLoanType(loanTypeDO);
							}
							loanDO.setUserLoanRemainingTenure(loanDTO.getUserLoanRemainingTenure());
							dataFetchDAO.save(loanDO);
						}

						// user salary deduction
						for (UserSalaryDeductionDTO salaryDTO : applicantFinancialDTO.get(i).getUserSalaryDeduction()) {
							UserSalaryDeductionDO salaryDO = (UserSalaryDeductionDO) dataFetchDAO
									.findById(UserSalaryDeductionDO.class, salaryDTO.getUsersaldednsId());
							if (salaryDO == null) {
								salaryDO = new UserSalaryDeductionDO();
							}
							salaryDO.setActiveStatus(1);
							salaryDO.setCreatedBy(salaryDTO.getCreatedBy());
							salaryDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
							salaryDO.setUpdatedBy(salaryDTO.getUpdatedBy());
							salaryDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
							salaryDO.setAmount(salaryDTO.getAmount());
							salaryDO.setUserLogin(loginDO);
							RefSalaryDeductionDO refSalaryDeductionDO = (RefSalaryDeductionDO) dataFetchDAO
									.findById(RefSalaryDeductionDO.class, salaryDTO.getSalaryDednTypeId());
							if (refSalaryDeductionDO != null) {
								salaryDO.setRefSalaryDeduction(refSalaryDeductionDO);
							}
							dataFetchDAO.save(salaryDO);
						}
						i++;
					}
				}
			}
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#
	 * getCoApplicantEmploymentAndIncomeLoan(java.lang.String)
	 */
	@Override
	public String getCoApplicantEmploymentAndIncomeLoan(String listOfUserId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<FinalCoApplicantLatestDTO> list = dataFetchDAO.getCoApplicantEmploymentAndIncomeLoan(listOfUserId);
		if (list != null) {
			try {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getLenderUserList(int)
	 */
	@Override
	public String getLenderUserList(int lenderId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<LenderUserDTO> list = dataFetchDAO.getLenderUserList(lenderId);
		if (list != null) {
			try {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#updateUserCoApplicantFinancials(
	 * int)
	 */
	@Override
	public boolean updateUserCoApplicantFinancials(int userId) {
		return dataFetchDAO.updateUserCoApplicantFinancials(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#
	 * getListOfAllGetMethodFinancialTest(int)
	 */
	@Override
	public JSONObject getListOfAllGetMethodFinancialTest(int userId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (ValuCirclesConstants.commonFinancialCache == null) {
			ValuCirclesConstants.commonFinancialCache = new JSONObject();
			List<OccupationTypeDTO> occupationTypeDTO = dataFetchDAO.getListOfOccupationType();
			List<CreditScoreDTO> creditScoreDTO = dataFetchDAO.getListOfCreditScore();
			List<IncomeTypeDTO> incomeTypeDTO = dataFetchDAO.getListOfIncomeType();
			List<ReffrequencyDTO> frequencytypeDTO = dataFetchDAO.getListOffrequency();
			List<LoanLenderDTO> loanLenderDTO = dataFetchDAO.getListOfLoanLender();
			List<LoanTypeDTO> loanTypeDTO = dataFetchDAO.getListOfLoanType();
			List<EmployerDTO> employerDTO = dataFetchDAO.getListOfEmployer();
			List<IndustrySectorDTO> industrySectorDTO = dataFetchDAO.getListOfIndustrySector();
			List<RefEmploymentTypeDTO> employmentTypeDTO = dataFetchDAO.getListOfRefEmploymentType();
			List<RefSalaryDeductionDTO> refSalaryDeductionDTO = dataFetchDAO.getListOfRefSalaryDeduction();
			try {
				ValuCirclesConstants.commonFinancialCache.put("OccupationType", occupationTypeDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("CreditScore", creditScoreDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("IncomeType", incomeTypeDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("frequencyType", frequencytypeDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("LoanLenderType", loanLenderDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("LoanType", loanTypeDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("EmployerName", employerDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("IndustrySector", industrySectorDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("EmploymentType", employmentTypeDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("SalaryDeduction", refSalaryDeductionDTO.toString());
				long time = ValuCirclesConstants.dateTime = timestamp.getTime();
				ValuCirclesConstants.commonFinancialCache.put("TimeStamp", time);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ValuCirclesConstants.commonFinancialCache;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#
	 * getListOfEmployerNameUsingDistinct()
	 */
	@Override
	public String getListOfEmployerNameUsingDistinct() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<EmployerDTO> list = dataFetchDAO.getListOfEmployerNameUsingDistinct();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#updateUserCoApplicant(int,
	 * int)
	 */
	@Override
	public boolean updateUserCoApplicant(int userId, int coApplicantId) {
		boolean isUpdated = false;
		if (coApplicantId == 0) {
			boolean isUpdateCoApplicantUserEmployment = dataFetchDAO.updateCoApplicantEmployment(userId);
			boolean isUpdateCoApplicantIncome = dataFetchDAO.updateCoApplicantIncome(userId);
			boolean isUpdateCoApplicantLoan = dataFetchDAO.updateCoApplicantLoan(userId);
			boolean isUpdateUserCoApplicantInfo = dataFetchDAO.updateUserCoApplicantInfo(userId);
			boolean isUpdateUserCoApplicantAddress = dataFetchDAO.updateUserCoApplicantAddress(userId);
			boolean isUpdateUserFinancial = dataFetchDAO.updateCoApplicantuserFinancial(userId);
			boolean isUpdateUserCoApplicantLogin = dataFetchDAO.updateUserCoApplicantLogin(userId);
			boolean isUpdateCoApplicantSalaryDeduction = dataFetchDAO.updateCoApplicantSalaryDeduction(userId);
			isUpdated = true;
		} else {
			isUpdated = false;
		}
		return isUpdated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#sessionManagement(int)
	 */
	@Override
	public JSONObject sessionManagement(int userId) {
		if (ValuCirclesConstants.sessionManagementUserInfo == null) {
			ValuCirclesConstants.sessionManagementUserInfo = new JSONObject();
			ObjectMapper mapper = new ObjectMapper();
			SessionManagement sessionManagement = dataFetchDAO.getSessionManagement(userId);
			try {
				ValuCirclesConstants.sessionManagementUserInfo.put("SessionManagementDetails",
						sessionManagement.toString());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ValuCirclesConstants.sessionManagementUserInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getUserSession(int)
	 */
	@Override
	public String getUserSession(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		UserSession management = dataFetchDAO.getUserSessionManagement(userId);
		try {
			return mapper.writeValueAsString(management);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#
	 * updateIndustryAndEmployerFinancialData(int)
	 */
	@Override
	public String updateIndustryAndEmployerFinancialData(int userId) {
		JSONObject jsonObject = new JSONObject();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		if (ValuCirclesConstants.commonFinancialCache == null) {
			ValuCirclesConstants.commonFinancialCache = new JSONObject();
			List<IndustrySectorDTO> industrySectorDTO = dataFetchDAO.getListOfIndustrySector();
			List<EmployerDTO> employerDTO = dataFetchDAO.getListOfEmployer();
			// List<EmployerDTO> distinctName =
			// dataFetchDAO.getListOfEmployerNameUsingDistinct();
			List<OccupationTypeDTO> occupationTypeDTO = dataFetchDAO.getListOfOccupationType();
			List<CreditScoreDTO> creditScoreDTO = dataFetchDAO.getListOfCreditScore();
			List<IncomeTypeDTO> incomeTypeDTO = dataFetchDAO.getListOfIncomeType();
			List<ReffrequencyDTO> frequencytypeDTO = dataFetchDAO.getListOffrequency();
			List<LoanLenderDTO> loanLenderDTO = dataFetchDAO.getListOfLoanLender();
			List<LoanTypeDTO> loanTypeDTO = dataFetchDAO.getListOfLoanType();
			List<RefEmploymentTypeDTO> employmentTypeDTO = dataFetchDAO.getListOfRefEmploymentType();
			List<RefSalaryDeductionDTO> refSalaryDeductionDTO = dataFetchDAO.getListOfRefSalaryDeduction();
			try {
				ValuCirclesConstants.commonFinancialCache.put("OccupationType", occupationTypeDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("CreditScore", creditScoreDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("IncomeType", incomeTypeDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("frequencyType", frequencytypeDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("LoanLenderType", loanLenderDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("LoanType", loanTypeDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("EmployerName", employerDTO.toString());
				// ValuCirclesConstants.commonFinancialCache.put("EmployerName1",
				// distinctName.toString());
				ValuCirclesConstants.commonFinancialCache.put("IndustrySector", industrySectorDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("EmploymentType", employmentTypeDTO.toString());
				ValuCirclesConstants.commonFinancialCache.put("SalaryDeduction", refSalaryDeductionDTO.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				jsonObject.put("TimeStamp", ValuCirclesConstants.dateTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (ValuCirclesConstants.industrySectorChanged == true) {
			try {
				ValuCirclesConstants.commonFinancialCache = null;
				ValuCirclesConstants.industrySectorChanged = false;
				jsonObject.put("TimeStamp", timestamp.getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				jsonObject.put("TimeStamp", ValuCirclesConstants.dateTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (ValuCirclesConstants.employerChanged == true) {
			try {
				ValuCirclesConstants.commonFinancialCache = null;
				ValuCirclesConstants.employerChanged = false;
				jsonObject.put("TimeStamp", timestamp.getTime());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				jsonObject.put("TimeStamp", ValuCirclesConstants.dateTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getCoApplicantIncomeCredits(int)
	 */
	@Override
	public String getCoApplicantIncomeCredits(int userId) {
		String coApplicantIncomeCredits = dataFetchDAO.getCoApplicantIncomeCredits(userId);
		return coApplicantIncomeCredits;
	}

	@Override
	public int otpverify1(String mobileNo, String otp) {
		int response = 0;
		OptVerifyDO optVerifyDO = dataFetchDAO.getOtpDetails(mobileNo);
		if (optVerifyDO != null) {
			String otptbl = optVerifyDO.getOtp();
			if (otptbl.equals(otp)) {
				optVerifyDO.setIs_Verified(1);
				dataFetchDAO.save(optVerifyDO);
				response = optVerifyDO.getId();
				if (response > 0) {
					boolean isUpdateOtp = dataFetchDAO.updateUserOtp(mobileNo);
				}
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getListOfLpiStatus(int)
	 */
	@Override
	public String getListOfLpiStatus(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<LenderLpiStatusDTO> list = dataFetchDAO.getListOfLpiStatus();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertLenderLpiStatusAndComments(
	 * int, int, java.lang.String)
	 */
	@Override
	public boolean insertLenderLpiStatusAndComments(int userId, int lenderId, String lenderStatus) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			LenderLpiStatusAndCommentsDTO dto = mapper.readValue(lenderStatus, LenderLpiStatusAndCommentsDTO.class);
			if (dto != null) {
				// lender comment insert
				UserLenderCommentDO commentDO = new UserLenderCommentDO();
				commentDO.setActiveStatus(1);
				commentDO.setComments(dto.getComments());
				commentDO.setCreatedBy(dto.getCreatedBy());
				commentDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
				commentDO.setUpdatedBy(dto.getUpdatedBy());
				commentDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				UserLpiLenderDO userLpiLenderDO = dataFetchDAO.getUserLpiLenderId(userId, lenderId);
				if (userLpiLenderDO != null) {
					commentDO.setUserLpiLender(userLpiLenderDO);
				}
				dataFetchDAO.save(commentDO);
				if (commentDO.getLenderLpiCommentsId() > 0) {

				}
				// lender status insert
				UserLendersStatusDO statusDO = new UserLendersStatusDO();
				statusDO.setActiveStatus(1);
				statusDO.setCreatedBy(dto.getCreatedBy());
				statusDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
				statusDO.setUpdatedBy(dto.getUpdatedBy());
				statusDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				statusDO.setLenderCommentId(commentDO.getLenderLpiCommentsId());
				statusDO.setUserLpiLender(userLpiLenderDO);
				// statusDO.set
				RefLenderLpiStatusDO refLenderLpiStatusDO = (RefLenderLpiStatusDO) dataFetchDAO
						.findById(RefLenderLpiStatusDO.class, dto.getLenderStatusId());
				if (refLenderLpiStatusDO != null) {
					statusDO.setRefLenderLpiStatus(refLenderLpiStatusDO);
				}
				dataFetchDAO.save(statusDO);
				if (statusDO.getUserLenderStatusId() > 0) {

				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getLenderLpiStatusAndComments(
	 * int, int)
	 */
	@Override
	public String getLenderLpiStatusAndComments(int userId, int lenderId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<LenderLpiStatusAndCommentsDTO> list = dataFetchDAO.getLenderLpiStatusAndComments(userId, lenderId);
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getListOfRefEmploymentType(int)
	 */
	@Override
	public String getListOfRefEmploymentType(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<RefEmploymentTypeDTO> list = dataFetchDAO.getListOfRefEmploymentType();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getListOfRefRelationShip(int)
	 */
	@Override
	public String getListOfRefRelationShip(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<RelationShipDTO> list = dataFetchDAO.getListOfRefRelationShip();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#updateLoginUserLpiStatus(int)
	 */
	@Override
	public boolean updateLoginUserLpiStatus(int userId) {
		try {
			boolean response = dataFetchDAO.updateLoginUserLpiStatus(userId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#updatelenderAppInitiated(int,
	 * int)
	 */
	@Override
	public boolean updatelenderAppInitiated(int userId, int lenderId) {
		int isUpdated = getCurrentSession().getNamedQuery("UserLpiLenderDO.updatelenderAppInitiated")
				.setParameter("userId", userId).setParameter("lenderId", lenderId).executeUpdate();

		try {
			Session session = null;
			Transaction tx = null;
			Transaction tx1 = null;
			Transaction tx2 = null;
			session = sessionFactory.openSession();

			Object userLPiLenderId = getCurrentSession().getNamedQuery("UserLpiLenderDO.getUserLpiLenderId")
					.setParameter("userId", userId).setParameter("lenderId", lenderId).uniqueResult();
			if (userLPiLenderId != null) {
				tx = session.beginTransaction();
				UserLpiLenderDO userLPiLenderId1 = (UserLpiLenderDO) userLPiLenderId;
				UserLenderCommentDO do1 = new UserLenderCommentDO();
				do1.setUserLpiLender(userLPiLenderId1);
				do1.setComments("Loan Request Submitted");
				do1.setActiveStatus(1);
				do1.setCreatedOn(UtilityMethods.getCurrentDateTime());
				do1.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				dataFetchDAO.save(do1);
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getListOfRefSalaryDeduction(int)
	 */
	@Override
	public String getListOfRefSalaryDeduction(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<RefSalaryDeductionDTO> list = dataFetchDAO.getListOfRefSalaryDeduction();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getSubscriptionSponsor(java.lang.
	 * String)
	 */
	@Override
	public Map<String, Object> getSubscriptionSponsor(String emailId) {
		Map<String, Object> status = dataFetchDAO.getSubscriptionSponsor(emailId);
		// TODO Auto-generated method stub
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#firstTimeLoginCheck(java.lang.
	 * String)
	 */
	@Override
	public String firstTimeLoginCheck(String emailId) {
		String status = "";
		int count = dataFetchDAO.firstTimeLoginCheck(emailId);
		if (count == 1) {
			status = "1";
		} else {
			status = "0";
		}
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#selectedSubscription(int,
	 * int)
	 */
	@Override
	public Boolean selectedSubscription(int userId, int subId) {
		Map<String, Object> status = dataFetchDAO.selectedSubscriptionSponsor(userId, subId);
		int result = (int) status.get("retval");
		if (result == 1) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getAssetProductType(int)
	 */
	@Override
	public String getAssetProductType(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<RefProductTypeDTO> list = dataFetchDAO.getAssetProductType();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getRefloantimeframe()
	 */
	@Override
	public String getRefloantimeframe() {
		JSONObject jsonObject = new JSONObject();
		String list = dataFetchDAO.getRefloantimeframe();
		try {
			if (list != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getAllSubscriptionType()
	 */
	@Override
	public String getAllSubscriptionType() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<RefSubscriptionTypeDTO> list = dataFetchDAO.lsitOfAllSubscriptionType();
		try {
			jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getAssetType(int)
	 */
	@Override
	public String getAssetType(int userId) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List<RefAssetTypeDTO> list = dataFetchDAO.getAssetType(userId);
		try {
			jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getSubRates(int)
	 */
	@Override
	public String getSubRates(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> callValue = dataFetchDAO.getSubRates(userId);
		try {
			jsonObject.put("SubRates", mapper.writeValueAsString(callValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getUserInfoDetailsForBuilders(
	 * int)
	 */
	@Override
	public String getUserInfoDetailsForBuilders(int userId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> callValue = dataFetchDAO.getUserInfoDetailsForBuilders(userId);
		try {
			jsonObject.put("userInfo", mapper.writeValueAsString(callValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertUserDetailsForCreditScore(
	 * java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public String insertUserDetailsForCreditScore(String userInfo, String processStr, String ans, int myuserId) {
		JSONObject jsonObject = new JSONObject();
		String exData = "";
		String exData1 = "";
		int exData2 = 0;
		Crif crif = new Crif();
		String questions = "";
		Map<String, Object> callValue = null;

		try {
			if (processStr.isEmpty() && ans.isEmpty()) {
				/*
				 * 24/07/2019:Geetha- Credit Report UI of Applicant and Co-application to be
				 * enabled if no credit report was generated callValue =
				 * dataFetchDAO.insertUserDetailsForCreditScore(userInfo, 0);
				 * 
				 */ callValue = dataFetchDAO.insertUserDetailsForCreditScore(userInfo, -99);
				questions = crif.BBCCHMStageOne(userInfo);
			} else { // We need to bring the questionaire case part here i.e. when status is S11, it
						// has to get appended with one of the options and again come with stage 2 --
						// Priya --29/12/2022
				questions = crif.BBCCHMStageTwo_Two(processStr, ans, myuserId);
			}
			// added the null condition by Priya on 22-12-2022
			// Adding the status value S08 as well by Priya on 27-12-2022
			if (questions != null && (questions.matches("S05") || questions.matches("S08"))) {
				try {
					jsonObject.put(ValuCirclesConstants.RESULT, "User Validation Fails");
					return jsonObject.toString();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (UnsupportedOperationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			JSONObject jsonObj = new JSONObject(questions);
			exData = (String) jsonObj.get("Result");
			exData1 = (String) jsonObj.get("Data");
			exData2 = (int) jsonObj.get("id");
			if (exData.equalsIgnoreCase("Success")) {
				int val = insertResponceCrif(exData1, exData2);
				;/* commented for test purposes and need to be undone */
				/*
				 * 07/11/2019 :Changed by Geetha- Insert XML data to User_Credit_Summary and
				 * detail tables
				 */

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder;
				String scoreValue = null;
				String strInq = null;
				String strCryear = null;
				String strCrmon = null;
				String strAvgyear = null;
				String strAvgmon = null;
				String strNewacct = null;
				String strDelAcct = null;

				String strPrnoacc = null;
				String strPractacc = null;
				String strProvracc = null;
				String strPrbal = null;
				String strPrsanacc = null;
				String strPrDsamt = null;
				String strSrnoacc = null;
				String strSractacc = null;
				String strSrovacc = null;
				String strSrbal = null;
				String strSrScamt = null;
				String strSrDsamt = null;
				String strScortyp;
				try {
					builder = factory.newDocumentBuilder();
					InputSource src = new InputSource();
					src.setCharacterStream(new StringReader(exData1));

					// System.out.println("xml data from Crif is" +exData1); Commented to prevent
					// logging xml data- Feb 13 2020
					Document document = builder.parse(src);
					NodeList fstNmElmntLst = document.getElementsByTagName("SCORE");
					if (fstNmElmntLst.getLength() == 0)
						scoreValue = "0";
					else {
						String score = document.getElementsByTagName("SCORE").item(0).getTextContent();
						scoreValue = document.getElementsByTagName("SCORE-VALUE").item(0).getTextContent();
					}

					int userId = selectuserid(myuserId);

					NodeList ndlInq = document.getElementsByTagName("INQURIES-IN-LAST-SIX-MONTHS");
					NodeList ndlCryear = document.getElementsByTagName("LENGTH-OF-CREDIT-HISTORY-YEAR");
					NodeList ndlCrmon = document.getElementsByTagName("LENGTH-OF-CREDIT-HISTORY-MONTH");
					NodeList ndlAvgyear = document.getElementsByTagName("AVERAGE-ACCOUNT-AGE-YEAR");
					NodeList ndlAvgmon = document.getElementsByTagName("AVERAGE-ACCOUNT-AGE-MONTH");
					NodeList ndlNewacct = document.getElementsByTagName("NEW-ACCOUNTS-IN-LAST-SIX-MONTHS");
					NodeList ndlDelAcct = document.getElementsByTagName("NEW-DELINQ-ACCOUNT-IN-LAST-SIX-MONTHS");

					NodeList ndlPrnoacc = document.getElementsByTagName("PRIMARY-NUMBER-OF-ACCOUNTS");
					NodeList ndlPractacc = document.getElementsByTagName("PRIMARY-ACTIVE-NUMBER-OF-ACCOUNTS");
					NodeList ndlProvracc = document.getElementsByTagName("PRIMARY-OVERDUE-NUMBER-OF-ACCOUNTS");
					NodeList ndlPrbal = document.getElementsByTagName("PRIMARY-CURRENT-BALANCE");
					NodeList ndlPrsanacc = document.getElementsByTagName("PRIMARY-SANCTIONED-AMOUNT");
					NodeList ndlPrDsamt = document.getElementsByTagName("PRIMARY-DISBURSED-AMOUNT");
					NodeList ndlSrnoacc = document.getElementsByTagName("SECONDARY-NUMBER-OF-ACCOUNTS");
					NodeList ndlSractacc = document.getElementsByTagName("SECONDARY-ACTIVE-NUMBER-OF-ACCOUNTS");
					NodeList ndlSrovacc = document.getElementsByTagName("SECONDARY-OVERDUE-NUMBER-OF-ACCOUNTS");
					NodeList ndlSrbal = document.getElementsByTagName("SECONDARY-CURRENT-BALANCE");
					NodeList ndlSrScamt = document.getElementsByTagName("SECONDARY-SANCTIONED-AMOUNT");
					NodeList ndlSrDsamt = document.getElementsByTagName("SECONDARY-DISBURSED-AMOUNT");
					NodeList ndlScortyp = document.getElementsByTagName("SCORE-TYPE");
					NodeList ndlScorval = document.getElementsByTagName("SCORE-VALUE");
					String strScorval;
					NodeList ndlScorcom = document.getElementsByTagName("SCORE-COMMENTS");
					// Priya 4-1-2023 Adding Scorefactors code
					NodeList ndlScorFactors = document.getElementsByTagName("SCORE-FACTORS");
					String strScorFactors;
					// Priya 4-1-2023 Adding Scorefactors code
					String strScorcom;
					if (ndlInq.getLength() == 0) {
						strInq = "0";
					} else {
						strInq = document.getElementsByTagName("INQURIES-IN-LAST-SIX-MONTHS").item(0).getTextContent();
						strInq = strInq.replaceAll(",", "");
					}

					if (ndlCryear.getLength() == 0) {
						strCryear = "0";
					} else {
						strCryear = document.getElementsByTagName("LENGTH-OF-CREDIT-HISTORY-YEAR").item(0)
								.getTextContent();
						strCryear = strCryear.replaceAll(",", "");
					}

					if (ndlCrmon.getLength() == 0) {
						strCrmon = "0";
					} else {
						strCrmon = document.getElementsByTagName("LENGTH-OF-CREDIT-HISTORY-MONTH").item(0)
								.getTextContent();
						strCrmon = strCrmon.replaceAll(",", "");
					}

					if (ndlAvgyear.getLength() == 0) {
						strAvgyear = "0";
					} else {
						strAvgyear = document.getElementsByTagName("AVERAGE-ACCOUNT-AGE-YEAR").item(0).getTextContent();
						strAvgyear = strAvgyear.replaceAll(",", "");
					}

					if (ndlAvgmon.getLength() == 0) {
						strAvgmon = "0";
					} else {
						strAvgmon = document.getElementsByTagName("AVERAGE-ACCOUNT-AGE-MONTH").item(0).getTextContent();
						strAvgmon = strAvgmon.replaceAll(",", "");
					}

					if (ndlNewacct.getLength() == 0) {
						strNewacct = "0";
					} else {
						strNewacct = document.getElementsByTagName("NEW-ACCOUNTS-IN-LAST-SIX-MONTHS").item(0)
								.getTextContent();
						strNewacct = strNewacct.replaceAll(",", "");
					}

					if (ndlDelAcct.getLength() == 0) {
						strDelAcct = "0";
					} else {
						strDelAcct = document.getElementsByTagName("NEW-DELINQ-ACCOUNT-IN-LAST-SIX-MONTHS").item(0)
								.getTextContent();
						strDelAcct = strDelAcct.replaceAll(",", "");
					}

					if (ndlPrnoacc.getLength() == 0) {
						strPrnoacc = "0";
					} else {
						strPrnoacc = document.getElementsByTagName("PRIMARY-NUMBER-OF-ACCOUNTS").item(0)
								.getTextContent();
						strPrnoacc = strPrnoacc.replaceAll(",", "");
					}

					if (ndlPractacc.getLength() == 0) {
						strPractacc = "0";
					} else {
						strPractacc = document.getElementsByTagName("PRIMARY-ACTIVE-NUMBER-OF-ACCOUNTS").item(0)
								.getTextContent();
						strPractacc = strPractacc.replaceAll(",", "");
					}

					if (ndlProvracc.getLength() == 0) {
						strProvracc = "0";
					} else {
						strProvracc = document.getElementsByTagName("PRIMARY-OVERDUE-NUMBER-OF-ACCOUNTS").item(0)
								.getTextContent();
						strProvracc = strProvracc.replaceAll(",", "");
					}

					if (ndlPrbal.getLength() == 0) {
						strPrbal = "0";
					} else {
						strPrbal = document.getElementsByTagName("PRIMARY-CURRENT-BALANCE").item(0).getTextContent();
						strPrbal = strPrbal.replaceAll(",", "");
					}

					if (ndlPrsanacc.getLength() == 0) {
						strPrsanacc = "0";
					} else {
						strPrsanacc = document.getElementsByTagName("PRIMARY-SANCTIONED-AMOUNT").item(0)
								.getTextContent();
						strPrsanacc = strPrsanacc.replaceAll(",", "");
					}

					if (ndlPrDsamt.getLength() == 0) {
						strPrDsamt = "0";
					} else {
						strPrDsamt = document.getElementsByTagName("PRIMARY-DISBURSED-AMOUNT").item(0).getTextContent();
						strPrDsamt = strPrDsamt.replaceAll(",", "");
					}
					if (ndlSrnoacc.getLength() == 0) {
						strSrnoacc = "0";
					} else {
						strSrnoacc = document.getElementsByTagName("SECONDARY-NUMBER-OF-ACCOUNTS").item(0)
								.getTextContent();
						strSrnoacc = strSrnoacc.replaceAll(",", "");
					}

					if (ndlSractacc.getLength() == 0) {
						strSractacc = "0";
					} else {
						strSractacc = document.getElementsByTagName("SECONDARY-ACTIVE-NUMBER-OF-ACCOUNTS").item(0)
								.getTextContent();
						strSractacc = strSractacc.replaceAll(",", "");
					}

					if (ndlSrovacc.getLength() == 0) {
						strSrovacc = "0";
					} else {
						strSrovacc = document.getElementsByTagName("SECONDARY-OVERDUE-NUMBER-OF-ACCOUNTS").item(0)
								.getTextContent();
						strSrovacc = strSrovacc.replaceAll(",", "");
					}

					if (ndlSrbal.getLength() == 0) {
						strSrbal = "0";
					} else {
						strSrbal = document.getElementsByTagName("SECONDARY-CURRENT-BALANCE").item(0).getTextContent();
						strSrbal = strSrbal.replaceAll(",", "");
					}

					if (ndlSrScamt.getLength() == 0) {
						strSrScamt = "0";
					} else {
						strSrScamt = document.getElementsByTagName("SECONDARY-SANCTIONED-AMOUNT").item(0)
								.getTextContent();
						strSrScamt = strSrScamt.replaceAll(",", "");
					}

					if (ndlSrDsamt.getLength() == 0) {
						strSrDsamt = "0";
					} else {
						strSrDsamt = document.getElementsByTagName("SECONDARY-DISBURSED-AMOUNT").item(0)
								.getTextContent();
						strSrDsamt = strSrDsamt.replaceAll(",", "");
					}
					if (ndlScortyp.getLength() == 0) {
						strScortyp = "";
					} else {
						strScortyp = document.getElementsByTagName("SCORE-TYPE").item(0).getTextContent();
					}

					if (ndlScorval.getLength() == 0) {
						strScorval = "0";
					} else {
						strScorval = document.getElementsByTagName("SCORE-VALUE").item(0).getTextContent();
						strScorval = strScorval.replaceAll(",", "");
					}
					if (ndlScorcom.getLength() == 0) {
						strScorcom = "";
					} else {
						strScorcom = document.getElementsByTagName("SCORE-COMMENTS").item(0).getTextContent();

					}
					// Priya 4-1-2023 Adding Scorefactors code
					if (ndlScorFactors.getLength() == 0) {
						strScorFactors = "";
					} else {
						strScorFactors = document.getElementsByTagName("SCORE-FACTORS").item(0).getTextContent();

					}
					// Priya 4-1-2023 Adding Scorefactors code
					int creditScore = Integer.parseInt(scoreValue);
					//int creditScore = 890; //hardcoding for testing
					int intInq = Integer.parseInt(strInq);
					int intCryear = Integer.parseInt(strCryear);
					int intCrmon = Integer.parseInt(strCrmon);
					int intAvgyear = Integer.parseInt(strAvgyear);
					int intAvgmon = Integer.parseInt(strAvgmon);
					int intNewacct = Integer.parseInt(strNewacct);
					int intDelAcct = Integer.parseInt(strDelAcct);

					int intPrnoacc = Integer.parseInt(strPrnoacc);
					int intPractacc = Integer.parseInt(strPractacc);
					int intProvracc = Integer.parseInt(strProvracc);
					int intPrbal = Integer.parseInt(strPrbal);
					int intPrsanacc = Integer.parseInt(strPrsanacc);
					int intPrDsamt = Integer.parseInt(strPrDsamt);
					int intSrnoacc = Integer.parseInt(strSrnoacc);
					int intSractacc = Integer.parseInt(strSractacc);
					int intSrovacc = Integer.parseInt(strSrovacc);
					int intSrbal = Integer.parseInt(strSrbal);
					int intSrScamt = Integer.parseInt(strSrScamt);
					int intSrDsamt = Integer.parseInt(strSrDsamt);
					int infoId = selecCreditInfoid(myuserId);

					if (val > 0) {
						Map<String, Object> creditInfo_result = dataFetchDAO.insertUserDetailsForCreditScore(userInfo,
								creditScore);
						int infoval = (int) creditInfo_result.get("retval");

						if (infoval > 0 && creditScore >= 300) { 

							UserCreditReportSummaryDO crdRepsumDO = new UserCreditReportSummaryDO();
							crdRepsumDO.setuserCreditInfoId(infoId);
							crdRepsumDO.setuserId(myuserId);
							crdRepsumDO.setuserCInqLast6Months(intInq);
							crdRepsumDO.setuserLengthCreditHistYear(intCryear);
							crdRepsumDO.setuserLengthCreditHistMonth(intCrmon);
							crdRepsumDO.setuserAvgAcctAgeYear(intAvgyear);
							crdRepsumDO.setuserAvgAcctAgeMonth(intAvgmon);
							crdRepsumDO.setuserNewAcctsLast6Months(intNewacct);
							crdRepsumDO.setuserNewDelinqAcctsLast6Months(intDelAcct);
							crdRepsumDO.setuserPriNoAccounts(intPrnoacc);
							crdRepsumDO.setuserPriActiveNoAccounts(intPractacc);
							crdRepsumDO.setuserPriOverdueNoAccounts(intProvracc);
							crdRepsumDO.setuserPriCurBal(intPrbal);
							crdRepsumDO.setuserNewDelinqAcctsLast6Months(intDelAcct);
							crdRepsumDO.setuserPriDisAmount(intPrDsamt);
							crdRepsumDO.setuserSecNoAccounts(intSrnoacc);
							crdRepsumDO.setuserSecActiveNoAccounts(intSractacc);
							crdRepsumDO.setuserSecOverdueNoAccounts(intSrovacc);
							crdRepsumDO.setuserSecCurBal(intSrbal);
							crdRepsumDO.setuserSecSancAmount(intSrScamt);
							crdRepsumDO.setuseruserSecDisAmountt(intSrDsamt);
							crdRepsumDO.setuserScoreType(strScortyp);
							crdRepsumDO.setuserScoreValue(creditScore);
							// Replacing strScorcom with strScorcom as latest CRIF response is having Score
							// factors instead of Score Comments
							// Priya 4 - 1 -2023
							// crdRepsumDO.setuserScoreDesc(strScorcom);
							crdRepsumDO.setuserScoreDesc(strScorFactors);
							// Priya 4 - 1 -2023
							crdRepsumDO.setActiveStatus("1");
							crdRepsumDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
							crdRepsumDO.setCreatedBy("Valucircles");
							crdRepsumDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
							crdRepsumDO.setUpdatedBy("Valucircles");
							dataFetchDAO.save(crdRepsumDO);
							int crdReportId = crdRepsumDO.getuserCreditReportId();
							if (crdReportId > 0) {
								String loanAcctdetails = parseCreditReportXML(exData1, myuserId, infoId, crdReportId);
								String crdInqdetails = parseCreditXMLInquiry(exData1, myuserId, infoId, crdReportId);

							}
						}

						jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);						
						jsonObject.put("creditScore", creditScore);
						

					} else {

						jsonObject.put(ValuCirclesConstants.RESULT, "Unable to generate credit report.");

					}

				} catch (ParserConfigurationException | SAXException | IOException
						| com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException | DOMException
						| java.text.ParseException e) {

					e.printStackTrace();
				}
				/*
				 * End of Changes
				 */

				int response1 = selectPageDetails(myuserId);
				if (response1 > 8) {

					jsonObject.put(ValuCirclesConstants.PAGE, response1);

				} else {

					int response2 = selectcoapplicant(myuserId);
					int response3 = selectuserid(myuserId);
					if (response2 == 0) {

						int page_number = 9;
						page_number = updatePageDetails(response3, page_number);

						jsonObject.put(ValuCirclesConstants.PAGE, page_number);

					} else {
						int pages = 8;
						int page = updatePageDetails(response3, pages);

						jsonObject.put(ValuCirclesConstants.PAGE, page);

					}

				}
			} else if (exData.equalsIgnoreCase("question")) {
				int val = insertQuestions(exData1, exData2);
				if (val > 0) {
					jsonObject.put(ValuCirclesConstants.RESULT, "question");
					jsonObject.put("questions", exData1);
				} else {
					jsonObject.put(ValuCirclesConstants.RESULT, "Unable to generate credit report.");
				}
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, "Unable to generate credit report.");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	private String parseCreditReportXML(String xmldata, int userId, int infoId, int repId)
			throws ParserConfigurationException, SAXException, IOException,
			com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException, DOMException, java.text.ParseException {
		List<LoanAccDetailDO> loanAccdetails = new ArrayList<LoanAccDetailDO>();
		LoanAccDetailDO loanInfo = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource src = new InputSource();
		src.setCharacterStream(new StringReader(xmldata));
		Document document = builder.parse(src);
		document.getDocumentElement().normalize();

		NodeList nList = document.getElementsByTagName("LOAN-DETAILS");
		Date infoDate = null;
		Date disDate = null;
		Date lastpayDate = null;
		Date closeDate = null;
		String strdisAmount;
		String strwriteOffAmt;
		String strcurrBal;
		String strcreditLimit;
		String strovrdueAmt;
		String strinstamt;
		String strinstfreq;
		String stracctnmbr;
		String strmskacctnr = "";
		String strwriteoffstat;
		String strcmpayhistry = null;
		int indx = 0;
		String strinstval;
		int crdAcctId = 0;
		String strstmtAmount;
		boolean payInd;
		// Priya 5 - 1 -2023
		// adding account number column datalength as a constant value,
		// in order to check whether the retrieved account number is crossing the data
		// length value and
		// if so truncating it to the datalength value first
		final int accountNumberColumnLength = 45; // As "user_credit_report_accounts' AcctNumber is varchar(45), kept
													// like this
		String accountNumber = "";
		try {
			// Priya 5 - 1 -2023
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					// Create new Employee Object
					loanInfo = new LoanAccDetailDO();
					loanInfo.setuserCreditReportId(repId);
					loanInfo.setuserCreditInfoId(infoId);
					loanInfo.setuserId(userId);
					if (eElement.getElementsByTagName("ACCT-NUMBER").getLength() == 0) {
						loanInfo.setAccnumbr("");
					} else {
						stracctnmbr = eElement.getElementsByTagName("ACCT-NUMBER").item(0).getTextContent();
						// Added by Priya on 5 - 1 - 2023
						// Moving the try catch in the beginning to see whether exception occuring in
						// any part

						// try {

						if (stracctnmbr.length() > accountNumberColumnLength) {
							// Take first 45 characters
							accountNumber = stracctnmbr.substring(0, accountNumberColumnLength - 1);
						} else {
							accountNumber = stracctnmbr;
						}
						// strmskacctnr= UtilityMethods.maskString(stracctnmbr, 0,
						// stracctnmbr.length()-4, '*');
						strmskacctnr = UtilityMethods.maskString(accountNumber, 4, accountNumber.length(), '*');
						// Added by Priya on 5 - 1 - 2023

						loanInfo.setAccnumbr(strmskacctnr);
					}
					if (eElement.getElementsByTagName("COMBINED-PAYMENT-HISTORY").getLength() == 0) {

					} else {
						strcmpayhistry = eElement.getElementsByTagName("COMBINED-PAYMENT-HISTORY").item(0)
								.getTextContent();

					}
					if (eElement.getElementsByTagName("CREDIT-GUARANTOR").getLength() == 0) {
						loanInfo.setCreditGuarantor("");
					} else {
						loanInfo.setCreditGuarantor(
								eElement.getElementsByTagName("CREDIT-GUARANTOR").item(0).getTextContent());
					}
					if (eElement.getElementsByTagName("ACCT-TYPE").getLength() == 0) {
						loanInfo.setAccType("");
					} else {
						loanInfo.setAccType(eElement.getElementsByTagName("ACCT-TYPE").item(0).getTextContent());
					}
					if (eElement.getElementsByTagName("OWNERSHIP-IND").getLength() == 0) {
						loanInfo.setOwnership("");
					} else {
						loanInfo.setOwnership(eElement.getElementsByTagName("OWNERSHIP-IND").item(0).getTextContent());
					}
					if (eElement.getElementsByTagName("ACCOUNT-STATUS").getLength() == 0) {
						loanInfo.setAcctStatus("");
					} else {
						loanInfo.setAcctStatus(
								eElement.getElementsByTagName("ACCOUNT-STATUS").item(0).getTextContent());
					}
					if (eElement.getElementsByTagName("DATE-REPORTED").getLength() == 0) {
						loanInfo.setDateRptd(null);
					} else {
						infoDate = new SimpleDateFormat("dd-MM-yyyy")
								.parse(eElement.getElementsByTagName("DATE-REPORTED").item(0).getTextContent());
						loanInfo.setDateRptd(infoDate);

					}
					if (eElement.getElementsByTagName("DISBURSED-AMT").getLength() == 0) {
						// loanInfo.setDisAmount(0);
					} else {
						strdisAmount = eElement.getElementsByTagName("DISBURSED-AMT").item(0).getTextContent();
						strdisAmount = strdisAmount.replaceAll(",", "");
						loanInfo.setDisAmount(Integer.parseInt(strdisAmount));
					}
					if (eElement.getElementsByTagName("DISBURSED-DT").getLength() == 0) {
						// loanInfo.setDisDate(null);
					} else {
						disDate = new SimpleDateFormat("dd-MM-yyyy")
								.parse(eElement.getElementsByTagName("DISBURSED-DT").item(0).getTextContent());
						loanInfo.setDisDate(disDate);
					}
					if (eElement.getElementsByTagName("LAST-PAYMENT-DATE").getLength() == 0) {
						// loanInfo.setLastPaymentdate("");
					} else {
						lastpayDate = new SimpleDateFormat("dd-MM-yyyy")
								.parse(eElement.getElementsByTagName("LAST-PAYMENT-DATE").item(0).getTextContent());
						loanInfo.setLastPaymentdate(lastpayDate);
					}
					if (eElement.getElementsByTagName("CLOSED-DATE").getLength() == 0) {

						// loanInfo.setClosedDate("");
					} else {
						closeDate = new SimpleDateFormat("dd-MM-yyyy")
								.parse(eElement.getElementsByTagName("CLOSED-DATE").item(0).getTextContent());
						loanInfo.setClosedDate(closeDate);
					}
					if (eElement.getElementsByTagName("OVERDUE-AMT").getLength() == 0) {
						// loanInfo.setWriteOffAmount(0);
					} else {
						strovrdueAmt = eElement.getElementsByTagName("OVERDUE-AMT").item(0).getTextContent();

						strovrdueAmt = strovrdueAmt.replaceAll(",", "");

						loanInfo.setOverdueAmount(Integer.parseInt(strovrdueAmt));
					}
					if (eElement.getElementsByTagName("WRITE-OFF-AMT").getLength() == 0) {
						// loanInfo.setWriteOffAmount(0);
					} else {
						strwriteOffAmt = eElement.getElementsByTagName("WRITE-OFF-AMT").item(0).getTextContent();

						strwriteOffAmt = strwriteOffAmt.replaceAll(",", "");

						loanInfo.setWriteOffAmount(Integer.parseInt(strwriteOffAmt));
					}
					if (eElement.getElementsByTagName("CURRENT-BAL").getLength() == 0) {
						// loanInfo.setCurrentBal(0);
					} else {
						strcurrBal = eElement.getElementsByTagName("CURRENT-BAL").item(0).getTextContent();
						strcurrBal = strcurrBal.replaceAll(",", "");
						loanInfo.setCurrentBal(Integer.parseInt(strcurrBal));
					}
					if (eElement.getElementsByTagName("MATCHED-TYPE").getLength() == 0) {
						// loanInfo.setMatchedType("");
					} else {
						loanInfo.setMatchedType(eElement.getElementsByTagName("MATCHED-TYPE").item(0).getTextContent());
					}
					if (eElement.getElementsByTagName("CREDIT-LIMIT").getLength() == 0) {
						// loanInfo.setCreditLimit(0);
					} else {
						strcreditLimit = eElement.getElementsByTagName("CREDIT-LIMIT").item(0).getTextContent();
						strcreditLimit = strcreditLimit.replaceAll(",", "");
						loanInfo.setCreditLimit(Integer.parseInt(strcreditLimit));
					}
					if (eElement.getElementsByTagName("INSTALLMENT-AMT").getLength() == 0) {
						// loanInfo.setInstallAmt(0);
						// loanInfo.setInstallFreq("");
					} else {
						strinstval = eElement.getElementsByTagName("INSTALLMENT-AMT").item(0).getTextContent();
						indx = strinstval.indexOf('/');
						if (indx > 0) {
							strinstamt = strinstval.substring(0, indx);
							strinstfreq = strinstval.substring(indx + 1);

						} else {
							strinstamt = strinstval;
							strinstfreq = "";
						}
						loanInfo.setInstallAmt(Integer.parseInt(strinstamt.replaceAll(",", "")));
						loanInfo.setInstallFreq(strinstfreq);
					}
					if (eElement.getElementsByTagName("WRITTEN-OFF_SETTLED-STATUS").getLength() == 0) {

					} else {

						loanInfo.setWriteOffStatus(
								eElement.getElementsByTagName("WRITTEN-OFF_SETTLED-STATUS").item(0).getTextContent());
					}
					if (eElement.getElementsByTagName("SETTLEMENT-AMT").getLength() == 0) {

					} else {
						strstmtAmount = eElement.getElementsByTagName("SETTLEMENT-AMT").item(0).getTextContent();
						strstmtAmount = strstmtAmount.replaceAll(",", "");
						loanInfo.setSettlementAmount(Integer.parseInt(strstmtAmount));
					}
					if (eElement.getElementsByTagName("SUIT-FILED_WILFUL-DEFAULT").getLength() == 0) {

					} else {
						loanInfo.setSuitFiled(
								eElement.getElementsByTagName("SUIT-FILED_WILFUL-DEFAULT").item(0).getTextContent());
					}
					loanInfo.setActiveStatus("1");
					loanInfo.setCreatedOn(UtilityMethods.getCurrentDateTime());
					loanInfo.setCreatedBy("Valucircles");
					loanInfo.setUpdatedOn(UtilityMethods.getCurrentDateTime());
					loanInfo.setUpdatedBy("Valucircles");
					dataFetchDAO.save(loanInfo);
					loanAccdetails.add(loanInfo);
					crdAcctId = loanInfo.getcreditReportAccId();
					if (crdAcctId > 0 && strcmpayhistry != null && strcmpayhistry.length() > 0) {

						payInd = insertUserCreditPaymentHistory(strcmpayhistry, strmskacctnr, userId, infoId, repId,
								crdAcctId);
						;

					}
				}
			} // for loop
		} catch (Exception e) {
			// Added by Priya on 5 - 1 - 2023
			// e.printStackTrace();
			// Logging the details
			System.out.println("Reason of exception" + e.getMessage());
			LOGGER.info(" Reason of exception", e.getMessage());
		}
		return loanAccdetails.toString();
	}

	private boolean insertUserCreditPaymentHistory(String strcmbpaymentstring, String acnumber, int userId, int infoId,
			int repId, int acctId) throws ParserConfigurationException, SAXException, IOException,
			com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException, DOMException, java.text.ParseException {

		String[] arrStrpymhstry = strcmbpaymentstring.split("\\|");

		int indMonth;
		int indYear;
		int intCode;
		int intStatus;
		for (String strpayment : arrStrpymhstry) {

			indMonth = strpayment.indexOf(':');
			indYear = strpayment.indexOf(',');
			intCode = strpayment.indexOf('/');

			UserAccountPaymentDO paymentDO = new UserAccountPaymentDO();

			paymentDO.setuserCreditReportAcctId(acctId);
			paymentDO.setuserCreditReportId(repId);
			paymentDO.setuserCreditInfoId(infoId);
			;
			paymentDO.setuserId(userId);
			paymentDO.setAcctNumber(acnumber);
			paymentDO.setMonth(strpayment.substring(0, indMonth));
			paymentDO.setYear(strpayment.substring(indMonth + 1, indYear));
			paymentDO.setDaysOverdue(strpayment.substring(indYear + 1, intCode));
			paymentDO.setAcctStatus(strpayment.substring(intCode + 1));
			paymentDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
			paymentDO.setCreatedBy("valucirlces1");
			paymentDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
			paymentDO.setUpdatedBy("Valucircles1");
			paymentDO.setActiveStatus("1");

			dataFetchDAO.save(paymentDO);
			if (paymentDO.getuserCreditReportAcctPymtId() > 0) {
			}

		}

		return true;

	}

	private String parseCreditXMLInquiry(String xmldata, int userId, int infoId, int repId)
			throws ParserConfigurationException, SAXException, IOException,
			com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException, DOMException, java.text.ParseException {
		List<CreditReportInquiryDO> crdRepInqydetails = new ArrayList<CreditReportInquiryDO>();
		CreditReportInquiryDO inqryInfo = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource src = new InputSource();
		src.setCharacterStream(new StringReader(xmldata));
		Document document = builder.parse(src);
		document.getDocumentElement().normalize();

		NodeList nList = document.getElementsByTagName("HISTORY");
		Date inqDate = null;
		String strmembername;
		String strpurpose;
		String strownershiptyp;
		String stramount;
		String remark;

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				inqryInfo = new CreditReportInquiryDO();
				inqryInfo.setuserCreditReportId(repId);
				inqryInfo.setuserCreditInfoId(infoId);
				inqryInfo.setuserId(userId);
				if (eElement.getElementsByTagName("MEMBER-NAME").getLength() == 0) {
					inqryInfo.setMemberName("");
				} else {
					inqryInfo.setMemberName(eElement.getElementsByTagName("MEMBER-NAME").item(0).getTextContent());
				}

				if (eElement.getElementsByTagName("INQUIRY-DATE").getLength() == 0) {
					inqryInfo.setInquiryDate(null);
				} else {
					inqDate = new SimpleDateFormat("dd-MM-yyyy")
							.parse(eElement.getElementsByTagName("INQUIRY-DATE").item(0).getTextContent());
					inqryInfo.setInquiryDate(inqDate);
				}

				if (eElement.getElementsByTagName("PURPOSE").getLength() == 0) {
					// loanInfo.setCurrentBal(0);
				} else {
					strpurpose = eElement.getElementsByTagName("PURPOSE").item(0).getTextContent();

					inqryInfo.setPurpose(strpurpose);
				}
				if (eElement.getElementsByTagName("OWNERSHIP-TYPE").getLength() == 0) {
					// loanInfo.setMatchedType("");
				} else {
					inqryInfo
							.setOwnershipType(eElement.getElementsByTagName("OWNERSHIP-TYPE").item(0).getTextContent());
				}
				if (eElement.getElementsByTagName("AMOUNT").getLength() == 0) {
					// loanInfo.setCreditLimit(0);
				} else {
					inqryInfo.setAmount(eElement.getElementsByTagName("AMOUNT").item(0).getTextContent());

				}
				if (eElement.getElementsByTagName("REMARK").getLength() == 0) {

					// loanInfo.setWriteOffStatus("");
				} else {
					inqryInfo.setRemark(eElement.getElementsByTagName("REMARK").item(0).getTextContent());
				}
				inqryInfo.setActiveStatus("1");
				inqryInfo.setCreatedOn(UtilityMethods.getCurrentDateTime());
				inqryInfo.setCreatedBy("Valucircles");
				inqryInfo.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				inqryInfo.setUpdatedBy("Valucircles");
				dataFetchDAO.save(inqryInfo);
				crdRepInqydetails.add(inqryInfo);
			}
		}
		return crdRepInqydetails.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getUserDetailsForCreditScore(int)
	 */
	@Override
	public String getUserDetailsForCreditScore(int userId) {
		JSONObject jsonObject = new JSONObject();
		String callValue = dataFetchDAO.getUserDetailsForCreditScore(userId);
		try {
			jsonObject.put("userInfo", callValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertResponceCrif(java.lang.
	 * String, int)
	 */
	@Override
	public int insertResponceCrif(String data, int userId) {
		return dataFetchDAO.insertResponceCrif(data, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertQuestions(java.lang.String,
	 * int)
	 */
	@Override
	public int insertQuestions(String Data, int userId) {
		// TODO Auto-generated method stub
		return dataFetchDAO.insertQuestions(Data, userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertUserDetailsForCreditScore(
	 * java.lang.String)
	 */
	@Override
	public String insertUserDetailsForCreditScore(String userInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getMyLPICount(int, int)
	 */
	@Override
	public String getMyLPICount(int userId, int ans) {
		// TODO Auto-generated method stub
		return dataFetchDAO.getMyLPICount(userId, ans);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#emailVerfication(java.lang.
	 * String, int)
	 */
	@Override
	public String emailVerfication(String token, int uid) {
		String response = ValuCirclesConstants.FAIL;
		try {
			UserLoginDO loginDO = dataFetchDAO.getUserRecordByPhone(token, uid);
			if (loginDO != null) {
				if (dataFetchDAO.activateUserAccount(loginDO.getUserEmail())) {
					response = ValuCirclesConstants.SUCCESS;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#selectlogincount(java.lang.
	 * String)
	 */
	@Override
	public int selectlogincount(String emailId) {
		int loginDO = dataFetchDAO.selectlogincount(emailId);
		return loginDO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#emailverify(java.lang.String)
	 */
	public int emailverify(String emailId) {
		// TODO Auto-generated method stub
		int loginDO = dataFetchDAO.emailverify(emailId);
		return loginDO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertRequestLog(com.vc.staging.
	 * dto.ReqResLogDTO)
	 */
	@Override
	public boolean insertRequestLog(ReqResLogDTO logDTO) {

		boolean flag = false;
		Session session = null;

		InetAddress localhost = null;
		try {
			localhost = InetAddress.getLocalHost();

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			session = sessionFactory.openSession();
			TblRequestLogDO logDO = new TblRequestLogDO();
			logDO.setApiPath(logDTO.getApiPath());
			logDO.setClientIp(logDTO.getClientIp());
			logDO.setContentType(logDTO.getContentType());
			logDO.setUserAgent(logDTO.getUserAgent());
			logDO.setSessionId(logDTO.getSessionId());
			logDO.setCreatedBy(localhost.getHostAddress().trim());
			logDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
			logDO.setUpdatedBy(localhost.getHostAddress().trim());
			logDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
			logDO.setActiveStatus(1);
			session.save(logDO);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#insertResponseLog(com.vc.staging.
	 * dto.ReqResLogDTO)
	 */
	@Override
	public boolean insertResponseLog(ReqResLogDTO logDTO) {
		boolean flag = false;
		Session session = null;

		InetAddress localhost = null;
		try {
			localhost = InetAddress.getLocalHost();

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			session = sessionFactory.openSession();
			TblResponseLogDO logDO = new TblResponseLogDO();
			logDO.setApiPath(logDTO.getApiPath());
			logDO.setContentType(logDTO.getContentType());
			logDO.setSessionId(logDTO.getSessionId());
			logDO.setElapsedTime(logDTO.getElapsedTime());
			logDO.setResponseData(logDTO.getResponseData());
			logDO.setCreatedBy(localhost.getHostAddress().trim());
			logDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
			logDO.setUpdatedBy(localhost.getHostAddress().trim());
			logDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
			logDO.setActiveStatus(1);
			session.save(logDO);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			session.close();
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getLenderApplications(java.lang.
	 * String)
	 */
	@Override
	public String getLenderApplications(String userId) {

		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> callValue = dataFetchDAO.getLenderApplications(userId);
		try {
			jsonObject.put("lenderlist", mapper.writeValueAsString(callValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getLenderApplicationStatus(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public String getLenderApplicationStatus(String userId, String lenderId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> callValue = dataFetchDAO.getLenderApplicationStatus(userId, lenderId);
		try {
			jsonObject.put("status", mapper.writeValueAsString(callValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getLenderApplicationSanctions(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String getLenderApplicationSanctions(String userId, String lenderId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> callValue = dataFetchDAO.getLenderApplicationSanctions(userId, lenderId);
		try {
			jsonObject.put("sanctions", mapper.writeValueAsString(callValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getLenderApplicationDisbursements
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public String getLenderApplicationDisbursements(String userId, String lenderId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> callValue = dataFetchDAO.getLenderApplicationDisbursements(userId, lenderId);
		try {
			jsonObject.put("disbursements", mapper.writeValueAsString(callValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#deleteresponselog()
	 */
	@Override
	public String deleteresponselog() {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		String callValue = dataFetchDAO.deleteresponselog();
		try {
			jsonObject.put("deleteresponselog", mapper.writeValueAsString(callValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#applyForSelectedLenders(java.lang
	 * .String)
	 */
	@Override
	public boolean applyForSelectedLenders(String lenders) {
		try {
			int count = 0;
			List<UserLenderDTO> list = UtilityMethods.parseJsonToObject(lenders, ArrayList.class, UserLenderDTO.class);
			if (list != null && list.size() > 0) {
				for (UserLenderDTO doList : list) {
					UserLenderCommentDO userComents = new UserLenderCommentDO();
					userComents = dataFetchDAO.applyForSelectedLenders(doList);
					if (userComents != null) {
						UserLendersStatusDO userStatus = new UserLendersStatusDO();
						userStatus = dataFetchDAO.updateUserLenderStatus(userComents);
						if (userStatus != null) {
							UserlenderSanctionDetailsDO sanctions = new UserlenderSanctionDetailsDO();
							sanctions = dataFetchDAO.updateUserLenderSanctions(userStatus);
							if (sanctions != null) {
								LenderDisbursementDO disb = new LenderDisbursementDO();
								disb = dataFetchDAO.updateUserLenderDisbursement(sanctions);
								if (disb.getUserLenderDisbursementID() > 0) {
									count++;
									if (list.size() == count) {
										return true;
									}
								}

							}

						}

					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getcount(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String getcount(String Fromdate, String Todate) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();

		final HSSFWorkbook workbook = new HSSFWorkbook();
		final HSSFSheet sheet = workbook.createSheet("Registration Count");
		final HSSFRow header = sheet.createRow((short) 0);
		final HSSFCellStyle style = workbook.createCellStyle();
		final HSSFFont font = workbook.createFont();
		int userId = 0;
		int rowCount = 0, primaryUserId = 0, activeStatus = 0, userLPiStatus = 0, LPiCount = 0, loginattempts = 0,
				email_verified = 0, otp_verified = 0;
		String FirstName = null, LastName = null, MobileNo = null, Email = null, createdOn = null, lastlogin = null;
		String test = dataFetchDAO.getcount(Fromdate, Todate);
		try {
			if (test != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, test);

				String hasil = jsonObject.getString("Result");
				JSONArray test1 = new JSONArray(hasil);
				for (int j = 0; j < test1.length(); j++) {
					JSONObject rec = test1.getJSONObject(j);
					userId = rec.getInt("userId");
					FirstName = rec.getString("firstName");
					LastName = rec.getString("lastName");
					MobileNo = rec.getString("mobileNo");
					Email = rec.getString("email");
					primaryUserId = rec.getInt("primaryUserId");
					createdOn = rec.getString("createdOn");
					activeStatus = rec.getInt("activeStatus");
					userLPiStatus = rec.getInt("userLPiStatus");
					LPiCount = rec.getInt("lpiCount");
					lastlogin = rec.getString("lastlogin");
					loginattempts = rec.getInt("loginattempts");
					email_verified = rec.getInt("email_verified");
					otp_verified = rec.getInt("otp_verified");

					font.setFontName(HSSFFont.FONT_ARIAL);
					font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					style.setFont(font);
					header.createCell((short) 0).setCellValue("User ID");
					header.createCell((short) 1).setCellValue("First Name");
					header.createCell((short) 2).setCellValue("Last Name");
					header.createCell((short) 3).setCellValue("Mobile Number");
					header.createCell((short) 4).setCellValue("Email");
					header.createCell((short) 5).setCellValue("Primary UserId");
					header.createCell((short) 6).setCellValue("createdOn Date");
					header.createCell((short) 7).setCellValue("Active");
					header.createCell((short) 8).setCellValue("Loan Applied");
					header.createCell((short) 9).setCellValue("LPiCount");
					header.createCell((short) 10).setCellValue("Lastlogin Date");
					header.createCell((short) 11).setCellValue("Login Attempts");
					header.createCell((short) 12).setCellValue("Email Verified");
					header.createCell((short) 13).setCellValue("OTP Verified");

					Row row = sheet.createRow(++rowCount);
					row.createCell((short) 0).setCellValue(userId);
					row.createCell((short) 1).setCellValue(FirstName);
					row.createCell((short) 2).setCellValue(LastName);
					row.createCell((short) 3).setCellValue(MobileNo);
					row.createCell((short) 4).setCellValue(Email);
					row.createCell((short) 5).setCellValue(primaryUserId);
					row.createCell((short) 6).setCellValue(createdOn);
					row.createCell((short) 7).setCellValue(activeStatus);
					row.createCell((short) 8).setCellValue(userLPiStatus);
					row.createCell((short) 9).setCellValue(LPiCount);
					row.createCell((short) 10).setCellValue(lastlogin);
					row.createCell((short) 11).setCellValue(loginattempts);
					row.createCell((short) 12).setCellValue(email_verified);
					row.createCell((short) 13).setCellValue(otp_verified);

				}

			}

			String timeStamp = new SimpleDateFormat("yyyy-MM-dd.HH-mm-ss").format(new Date());
			String filename = "registrationdone" + timeStamp + ".xls";
			FileOutputStream fileOut;

			fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			String mailId = UtilityMethods.getProperty(ValuCirclesConstants.Emailfordailyreport);
			UtilityMethods.excelSend(filename, mailId);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return test;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getlpicompleted(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String getlpicompleted(String Fromdate, String Todate) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();

		final HSSFWorkbook workbook = new HSSFWorkbook();
		final HSSFSheet sheet = workbook.createSheet("Lpi Completed Count");
		final HSSFRow header = sheet.createRow((short) 0);
		final HSSFCellStyle style = workbook.createCellStyle();
		final HSSFFont font = workbook.createFont();
		int userId = 0;
		int rowCount = 0, lenderId = 0, LPiCount = 0;
		String MobileNo = null, Email = null, lpi = null, requiredLoanAmount = null, lenderName = null,
				eligilbleLoanAmount = null, createdOn = null, updatedOn = null;
		String test = dataFetchDAO.getlpicompleted(Fromdate, Todate);
		try {
			if (test != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, test);

				String hasil = jsonObject.getString("Result");

				JSONArray test11 = new JSONArray(hasil);
				for (int j = 0; j < test11.length(); j++) {
					JSONObject rec = test11.getJSONObject(j);
					userId = rec.getInt("userId");
					MobileNo = rec.getString("mobileNo");
					Email = rec.getString("email");
					lpi = rec.getString("lpi");
					requiredLoanAmount = rec.getString("rrquiredloan");
					lenderId = rec.getInt("lenderId");
					lenderName = rec.getString("lenderName");
					eligilbleLoanAmount = rec.getString("eligibleamount");
					LPiCount = rec.getInt("lpiCount");
					createdOn = rec.getString("createdOn");
					updatedOn = rec.getString("updatedOn");

					font.setFontName(HSSFFont.FONT_ARIAL);
					font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					style.setFont(font);
					header.createCell((short) 0).setCellValue("User ID");
					header.createCell((short) 1).setCellValue("MobileNo");
					header.createCell((short) 2).setCellValue("Email");
					header.createCell((short) 3).setCellValue("Lpi");
					header.createCell((short) 4).setCellValue("Required Loan");
					header.createCell((short) 5).setCellValue("LenderID");
					header.createCell((short) 6).setCellValue("LenderName");
					header.createCell((short) 7).setCellValue("Eligible Amount");
					header.createCell((short) 8).setCellValue("Lpi Count");
					header.createCell((short) 9).setCellValue("Created Date");
					header.createCell((short) 10).setCellValue("Update on");

					Row row = sheet.createRow(++rowCount);
					row.createCell((short) 0).setCellValue(userId);
					row.createCell((short) 1).setCellValue(MobileNo);
					row.createCell((short) 2).setCellValue(Email);
					row.createCell((short) 3).setCellValue(lpi);
					row.createCell((short) 4).setCellValue(requiredLoanAmount);
					row.createCell((short) 5).setCellValue(lenderId);
					row.createCell((short) 6).setCellValue(lenderName);
					row.createCell((short) 7).setCellValue(eligilbleLoanAmount);
					row.createCell((short) 8).setCellValue(LPiCount);
					row.createCell((short) 9).setCellValue(createdOn);
					row.createCell((short) 10).setCellValue(updatedOn);
				}

				String timeStamp = new SimpleDateFormat("yyyy-MM-dd.HH-mm-ss").format(new Date());
				String filename = "lpicompleted" + timeStamp + ".xls";
				FileOutputStream fileOut;

				fileOut = new FileOutputStream(filename);
				workbook.write(fileOut);
				fileOut.close();
				String mailId = UtilityMethods.getProperty(ValuCirclesConstants.Emailfordailyreport);
				UtilityMethods.excelSend(filename, mailId);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return test;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vc.staging.service.UserDataFetchService#getappliedloanscount(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public String getappliedloanscount(String Fromdate, String Todate) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();

		final HSSFWorkbook workbook = new HSSFWorkbook();
		final HSSFSheet sheet = workbook.createSheet("Applied Loan Count");
		final HSSFRow header = sheet.createRow((short) 0);
		final HSSFCellStyle style = workbook.createCellStyle();
		final HSSFFont font = workbook.createFont();
		int userId = 0;
		int rowCount = 0, lenderId = 0;
		String FirstName = null, LastName = null, MobileNo = null, Email = null;
		String requiredLoanAmount = null, lenderName = null, eligilbleLoanAmount = null;
		String test = dataFetchDAO.getappliedloanscount(Fromdate, Todate);
		try {
			if (test != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, test);

				String hasil = jsonObject.getString("Result");
				JSONArray test1 = new JSONArray(hasil);
				for (int j = 0; j < test1.length(); j++) {
					JSONObject rec = test1.getJSONObject(j);
					userId = rec.getInt("userId");
					MobileNo = rec.getString("mobileNo");
					Email = rec.getString("email");
					requiredLoanAmount = rec.getString("rrquiredloan");
					lenderId = rec.getInt("lenderId");
					lenderName = rec.getString("lenderName");
					eligilbleLoanAmount = rec.getString("eligibleamount");

					font.setFontName(HSSFFont.FONT_ARIAL);
					font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					style.setFont(font);
					header.createCell((short) 0).setCellValue("User ID");
					header.createCell((short) 1).setCellValue("Mobile Number");
					header.createCell((short) 2).setCellValue("Email");
					header.createCell((short) 3).setCellValue("Required Loan Amount");
					header.createCell((short) 4).setCellValue("Lender ID");
					header.createCell((short) 5).setCellValue("Lender Name");
					header.createCell((short) 6).setCellValue("Eligible Loan Amount");

					Row row = sheet.createRow(++rowCount);
					row.createCell((short) 0).setCellValue(userId);
					row.createCell((short) 1).setCellValue(MobileNo);
					row.createCell((short) 2).setCellValue(Email);
					row.createCell((short) 3).setCellValue(requiredLoanAmount);
					row.createCell((short) 4).setCellValue(lenderId);
					row.createCell((short) 5).setCellValue(lenderName);
					row.createCell((short) 6).setCellValue(eligilbleLoanAmount);

				}

				String timeStamp = new SimpleDateFormat("yyyy-MM-dd.HH-mm-ss").format(new Date());
				String filename = "appliedloan " + timeStamp + ".xls";
				FileOutputStream fileOut;

				fileOut = new FileOutputStream(filename);
				workbook.write(fileOut);
				fileOut.close();

				String mailId = UtilityMethods.getProperty(ValuCirclesConstants.Emailfordailyreport);
				UtilityMethods.excelSend(filename, mailId);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return test;

	}
	/**
	 * Method to get employer names from ref_employer_category_mapping based on employer_name and employer_category on 23-02-2023 
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vc.staging.service.UserDataFetchService#getEmployerNamesFromCategoryMapping(java.lang.
	 * String,int)
	 */
	@Override
	public String getOrganizationNamesFromCategoryMapping(String employerName, int employerCategoryId) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		List list = dataFetchDAO.getOrganizationList(employerName, employerCategoryId);
		try {
			jsonObject.put("Result", mapper.writeValueAsString(list));
			
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchServiceImpl", "getEmployerNamesFromCategoryMapping", e.toString(),
					"userId:");
			e.printStackTrace();
		}
		//System.out.println("jsonObject output.." + jsonObject.toString());
		return jsonObject.toString();
	}

}
