package com.vc.staging.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vc.staging.dao.BuilderDataFetchDAO;
import com.vc.staging.dao.LenderDataFetchDAO;
import com.vc.staging.dao.UserDataFetchDAO;
import com.vc.staging.dto.CreditScoreDataDTO;
import com.vc.staging.dto.LenderUserDTO;
import com.vc.staging.dto.UserDTO;
import com.vc.staging.dto.UserLoginDTO;
import com.vc.staging.model.NavigationPageDO;
import com.vc.staging.model.UserAddressDO;
import com.vc.staging.model.UserLoginDO;
import com.vc.staging.service.ErrorLogService;
import com.vc.staging.service.LpiService;
import com.vc.staging.service.UserDataFetchService;
import com.vc.staging.util.ProbeHelper;
import com.vc.staging.util.UtilityMethods;
import com.vc.staging.util.ValuCirclesConstants;

/**
 * 
 * @author Thanga
 * 
 * 
 * 
 * 
 * 
 */

/**
 * @author Venkat
 *
 */
@Path("/user")
@Transactional
@Component
public class UserDataFetchController {
	@Autowired
	UserDataFetchService dataFetchService;

	@Autowired
	LenderDataFetchDAO lenderDataFetchDAO;

	@Autowired
	ErrorLogService errorLog;

	@Autowired
	LpiService lpiService;

	@Autowired
	UserDataFetchDAO dataFetchDAO;

	@Autowired
	BuilderDataFetchDAO builderDataFetchDAO;

	// Logger instance
	Logger LOGGER = LoggerFactory.getLogger(UserDataFetchController.class);

	/**
	 * @param userSignUp
	 * @return jsonobject
	 */
	@Path("/signUp")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String CustomerSignUp(@FormParam("userSignUp") String userSignUp) {

		JSONObject jsonObject = new JSONObject();
		boolean newUser = dataFetchService.verifynewemailreg(userSignUp);
		try {
			if (newUser == true) {
		try {		
				boolean uniqueMobile = dataFetchService.verifynewMobileReg(userSignUp);
				if(uniqueMobile == true) {
				int response = dataFetchService.insertSignUpDetails(userSignUp);
			try {
					if (response > 0) {
						
						jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
						jsonObject.put(ValuCirclesConstants.USERID, response);
					

					} else {
						jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
						jsonObject.put(ValuCirclesConstants.USERID, 0);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				}else {
					jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.EXISTING_MOBILENO);
				}
		}catch (Exception e) {
			 
			e.printStackTrace();
		}
				
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.EXISTING_EMAILID);
			}

		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "signUp", e.toString(), "userSignUp:" + userSignUp);
			e.printStackTrace();
			try {
				jsonObject.put(ValuCirclesConstants.ERROR, e.toString());
			} catch (Exception e1) {

				e1.printStackTrace();
			}
			return jsonObject.toString();
		}
		return jsonObject.toString();
	}

	/**
	 * @param emailId
	 * @param password
	 * @return jsonobject
	 */
	@Path("/userLogin")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String userLogin(@FormParam("emailId") String emailId, @FormParam("password") String password) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		UserLoginDTO loginDTO = dataFetchService.userLogin(emailId, password);
		try {

			if (loginDTO == null) {
				int email_verify = dataFetchService.emailverify(emailId);

				if (email_verify == 0) {
					jsonObject.put(ValuCirclesConstants.RESULT, "");
					jsonObject.put(ValuCirclesConstants.MSG, ValuCirclesConstants.EMAIL_VERIFY_MSG);
					jsonObject.put(ValuCirclesConstants.ERROR, ValuCirclesConstants.INVALID_USER);
				} else if (email_verify == 2) {
					jsonObject.put(ValuCirclesConstants.RESULT, "");
					jsonObject.put(ValuCirclesConstants.MSG, ValuCirclesConstants.Unknown);
					jsonObject.put(ValuCirclesConstants.ERROR, ValuCirclesConstants.INVALID_USER);

				} else {
					int response = dataFetchService.selectlogincount(emailId);
					if (response == 3) {
						jsonObject.put(ValuCirclesConstants.RESULT, "");
						jsonObject.put(ValuCirclesConstants.MSG, ValuCirclesConstants.LOCKED_USER);
						jsonObject.put(ValuCirclesConstants.ERROR, ValuCirclesConstants.INVALID_USER);
					} else {
						jsonObject.put(ValuCirclesConstants.RESULT, "");
						jsonObject.put(ValuCirclesConstants.MSG, ValuCirclesConstants.INVALID_USER_MSG);
						jsonObject.put(ValuCirclesConstants.ERROR, ValuCirclesConstants.INVALID_USER);
					}
				}
			} else if (loginDTO != null) {
				int email_verify = dataFetchService.emailverify(emailId);
				
				if (email_verify == 0) {
					jsonObject.put(ValuCirclesConstants.RESULT, "");
					jsonObject.put(ValuCirclesConstants.MSG, ValuCirclesConstants.EMAIL_VERIFY_MSG);
					jsonObject.put(ValuCirclesConstants.ERROR, ValuCirclesConstants.INVALID_USER);
				}else{
{
					userSession(loginDTO.getUserId());
					jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(loginDTO));
					jsonObject.put(ValuCirclesConstants.SESSION_ID, UtilityMethods.generateNewSessionForUser());
					jsonObject.put(ValuCirclesConstants.ERROR, "");
					if (loginDTO.getRefUserTypeId() == 1) {
						jsonObject.put("UserSession",
								mapper.writeValueAsString(dataFetchDAO.getUserSessionManagement(loginDTO.getUserId())));
					} else if (loginDTO.getRefUserTypeId() == 2) {

						jsonObject.put("LenderUserList",
								mapper.writeValueAsString(lenderDataFetchDAO.getLenderProspects(loginDTO.getUserId())));

					} else if (loginDTO.getRefUserTypeId() == 3) {
						List<UserDTO> list = builderDataFetchDAO.getBuilderSubscribedUser(loginDTO.getBuilderId());
						if (list != null) {
							List<LenderUserDTO> lenderUserDTO = builderDataFetchDAO.getBuilderSubscribedUserList(list);
							if (lenderUserDTO != null) {
								jsonObject.put("BuilderUserList", mapper.writeValueAsString(lenderUserDTO));
							}
						}
					}
					/*17/07/2019 Geetha - OTP Login page removal changes
					 * 01/24/2019 Geetha -OTP Mail for NRI customers 
					 */
				     
				     if(loginDTO.getOtpVerified()==0) {
				    	 if(loginDTO.getCountryCode().equals("+91")) {
				    		 dataFetchService.resendOtp(loginDTO.getUserPhone(),loginDTO.getUserEmail(), "sms");
				    	 }else {
				    		 dataFetchService.resendOtp(loginDTO.getUserPhone(),loginDTO.getUserEmail(), "email");
				    	 }
				     
				     }
				}

			}
			}

		} catch (Exception e) {

			errorLog.insertErrorLog("UserDataFetchController", "userLogin", e.toString(),
					"emailId:" + emailId + ",password:" + password);
			e.printStackTrace();
			try {
				jsonObject.put(ValuCirclesConstants.ERROR, e.toString());
				jsonObject.put(ValuCirclesConstants.RESULT, e.toString());
				jsonObject.put(ValuCirclesConstants.MSG, ValuCirclesConstants.EXCEPTION);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return jsonObject.toString();
		}
		return jsonObject.toString();
	}

	/**
	 * @return response
	 */
	@Path("/getListOfGender")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfGender() {
		String response = dataFetchService.getListOfGender();
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOfMaritalStatus")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListMaritalStatus() {
		String response = dataFetchService.getListOfMaritalStatus();
		return response;
	}

	/**
	 * @param genderDetails
	 * @return jsonobject
	 */
	@Path("/insertGender")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insertGender(@FormParam("genderDetails") String genderDetails) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (dataFetchService.insertGender(genderDetails)) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertGender", e.toString(),
					"genderDetails:" + genderDetails);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param maritalStatusDetails
	 * @return jsonobject
	 */
	@Path("/insertMaritalStatus")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insertMaritalStatus(@FormParam("maritalStatusDetails") String maritalStatusDetails) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (dataFetchService.insertMaritalStatus(maritalStatusDetails)) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertMaritalStatus", e.toString(),
					"maritalStatusDetails:" + maritalStatusDetails);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param genderId
	 * @return jsonobject
	 */
	@Path("/deleteGenderById")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteGender(@FormParam("genderId") int genderId) {
		JSONObject jsonObject = new JSONObject();
		boolean response = dataFetchService.deleteGender(genderId);
		try {
			if (response == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "deleteGender", e.toString(), "genderId:" + genderId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param maritalStatusId
	 * @return jsonobject
	 */
	@Path("/deleteMaritalStatus")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteMaritalStatus(@FormParam("maritalStatusId") int maritalStatusId) {
		JSONObject jsonObject = new JSONObject();
		boolean response = dataFetchService.deleteMaritalStatus(maritalStatusId);
		try {
			if (response == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "deleteMaritalStatus", e.toString(),
					"maritalStatusId:" + maritalStatusId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param occupationDetails
	 * @return jsonobject
	 */
	@Path("/insertOccupationType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insertOccupationType(@FormParam("occupationDetails") String occupationDetails) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (dataFetchService.insertOccupationType(occupationDetails)) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertOccupationType", e.toString(),
					"occupationDetails:" + occupationDetails);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param occupationTypeId
	 * @return jsonobject
	 */
	@Path("/deleteOccupationType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteOccupationType(@FormParam("occupationTypeId") int occupationTypeId) {
		JSONObject jsonObject = new JSONObject();
		boolean response = dataFetchService.deleteOccupationType(occupationTypeId);
		try {
			if (response == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "deleteOccupationType", e.toString(),
					"occupationTypeId:" + occupationTypeId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @return response
	 */
	@Path("/getListOfOccupationType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfOccupationType() {
		String response = dataFetchService.getListOfOccupationType();
		return response;
	}

	/**
	 * @param OccupationtypeId
	 * @return response
	 */
	@Path("/getListOfOccupationCategory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfOccupationCategory(@FormParam("OccupationtypeId") int OccupationtypeId) {
		String response = dataFetchService.getListOfOccupationCategory(OccupationtypeId);
		return response;
	}
	
	/**
	 * @param userId
	 * @param userDetails
	 * @return jsonobject
	 */
	@Path("/insertUserData")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insertUserData(@FormParam("userId") int userId, @FormParam("userDetails") String userDetails) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean response = dataFetchService.insertUserData(userId, userDetails);
		try {
			if (response == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
				dataFetchDAO.updateUserInfoSession(userId);
				int response1 = dataFetchService.selectPageDetails(userId);
				if (response1 > 2) {
					jsonObject.put(ValuCirclesConstants.PAGE, response1);
				} else {
					int pages = 2;
					int page = dataFetchService.updatePageDetails(userId, pages);
					jsonObject.put(ValuCirclesConstants.PAGE, page);
				}
				ValuCirclesConstants.userInfoCache.clear();
				jsonObject.put(ValuCirclesConstants.USERINFO,
						mapper.writeValueAsString(dataFetchDAO.getUserInfoByUserId(userId)));
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertUserData", e.toString(),
					"userId:" + userId + ",userDetails:" + userDetails);
			e.printStackTrace();
			try {
				jsonObject.put(ValuCirclesConstants.ERROR, e.toString());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return jsonObject.toString();
		}	
		return jsonObject.toString();
	}

	/**
	 * @return response
	 */
	@Path("/getListOfEducation")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfEducation() {
		String response = dataFetchService.getListOfEducation();
		return response;
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/getListOfEmployer")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfEmployer(@FormParam("userId") int userId) {
		String response = dataFetchService.getListOfEmployer();
		return response;
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/getSummaryDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getSummaryDetails(@FormParam("userId") int userId) {
		String response = dataFetchService.getSummaryDetails(userId);
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOfAge")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfAge() {
		String response = dataFetchService.getListOfAge();
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOfCreditScore")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfCreditScore() {
		String response = dataFetchService.getListOfCreditScore();
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOfLoanType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfLoanType() {
		String response = dataFetchService.getListOfLoanType();
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOfIndustrySector")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfIndustrySector() {
		String response = dataFetchService.getListOfIndustrySector();
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOfIncomeType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfIncomeType() {
		String response = dataFetchService.getListOfIncomeType();
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOffrequency")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOffrequency() {
		String response = dataFetchService.getListOffrequency();
		return response;
	}

	/**
	 * @param userId
	 * @param financialDetails
	 * @param loanDetails
	 * @param incomeDetails
	 * @param salaryDeduction
	 * @return jsonobject
	 */
	@Path("/insertFinancialData")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insertFinancialData(@FormParam("userId") int userId,
			@FormParam("financialDetails") String financialDetails, @FormParam("loanDetails") String loanDetails,
			@FormParam("incomeDetails") String incomeDetails, @FormParam("salaryDeduction") String salaryDeduction) {
		LOGGER.info(
				"Insert financial Data method invoked!!! financialDetails: {}, Loan Details:{}, income details:{}, salarydeduction:{}",
				financialDetails, loanDetails, incomeDetails, salaryDeduction);
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean response = dataFetchService.insertFinancialData(userId, financialDetails, loanDetails, incomeDetails,
				salaryDeduction);
		try {
			if (response == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
				dataFetchDAO.updateUserEmploymentSession(userId);
				dataFetchDAO.updateUserIncomeSession(userId);
				dataFetchDAO.updateUserLoanSession(userId);
				dataFetchDAO.updateUserSalaryDeductionByUserId(userId);
				ValuCirclesConstants.userEmploymentCache.clear();
				jsonObject.put(ValuCirclesConstants.USEREMPLOYMENT,
						mapper.writeValueAsString(dataFetchDAO.getUserEmploymentByuserId(userId)));				
				jsonObject.put(ValuCirclesConstants.USERINCOME,
						mapper.writeValueAsString(dataFetchDAO.getUserIncomelistById(userId)));
				jsonObject.put(ValuCirclesConstants.USERLOAN,
						mapper.writeValueAsString(dataFetchDAO.getUserloanByUserId(userId)));
				jsonObject.put(ValuCirclesConstants.USERSALARYDEDUCTION,
						mapper.writeValueAsString(dataFetchDAO.getUserSalaryDeductionByUserId(userId)));

				int response1 = dataFetchService.selectPageDetails(userId);
				if (response1 > 4) {
					jsonObject.put(ValuCirclesConstants.PAGE, response1);
				} else {
					int response2 = dataFetchService.selectcoapplicant(userId);
					if (response2 == 0) {
						int page_number = 5;
						page_number = dataFetchService.updatePageDetails(userId, page_number);
						jsonObject.put(ValuCirclesConstants.PAGE, page_number);
					} else {
						int pages = 4;
						int page = dataFetchService.updatePageDetails(userId, pages);
						jsonObject.put(ValuCirclesConstants.PAGE, page);
					}
				}

			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertFinancialData", e.toString(), "userId:" + userId);
			e.printStackTrace();
			try {
				jsonObject.put(ValuCirclesConstants.ERROR, e.toString());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
			return jsonObject.toString();
	}

	/**
	 * @return response
	 */
	@Path("/getUserListData")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserListData() {
		String response = dataFetchService.getUserListData();
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOfResidence")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfResidence() {
		String response = dataFetchService.getListOfResidence();
		return response;
	}

	/**
	 * @param userId
	 * @return lpi
	 */
	@Path("/getMyLpiFromSP")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getLpiFromSP(@FormParam("userId") int userId) {
		return dataFetchService.getLpiFromSP(userId);
	}

	/**
	 * @param educationId
	 * @return response
	 */
	@Path("/getEducationDetailByEducationId")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getEducationDetailByEducationId(@FormParam("educationId") int educationId) {
		String response = dataFetchService.getEducationDetailByEducationId(educationId);
		return response;
	}

	/**
	 * @param educationDetailId
	 * @return response
	 */
	@Path("/getEducationInstitutionByEducationDetailId")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getEducationInstitutionByEducationDetailId(@FormParam("educationDetailId") int educationDetailId) {
		String response = dataFetchService.getEducationInstitutionByEducationDetailId(educationDetailId);
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOfEducationDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfEducationDetails() {
		String response = dataFetchService.getListOfEducationDetails();
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOfEducationInstitution")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfEducationInstitution() {
		String response = dataFetchService.getListOfEducationInstitution();
		return response;
	}

	/**
	 * @param emailId
	 * @param siteName
	 * @return response
	 */
	@Path("/resetPwd")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String forgotPassword(@FormParam("emailId") String emailId, @FormParam("siteName") String siteName) {
		String response = dataFetchService.forgotPassword(emailId, siteName);
		return response;
	}

	/**
	 * @param emailId
	 * @param userId
	 * @param newpwd
	 * @param sessionId
	 * @return response
	 */
	@Path("/resetPassword")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String resetPassword(@FormParam("emailId") String emailId, @FormParam("userId") int userId,
			@FormParam("newpwd") String newpwd, @FormParam("sessionId") String sessionId) {
		String response = dataFetchService.resetPassword(emailId, userId, newpwd, sessionId);
		return response;

	}

	/**
	 * @param userId
	 * @param oldpwd
	 * @param newpwd
	 * @return response
	 */
	@Path("/changepassword")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String changePassword(@FormParam("userId") int userId, @FormParam("oldpwd") String oldpwd,
			@FormParam("newpwd") String newpwd) {
		String response = dataFetchService.changePassword(userId, oldpwd, newpwd);
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOfZip")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfZip() {
		String response = dataFetchService.getListOfZip();
		return response;
	}

	/**
	 * @param zipCode
	 * @return response
	 */
	@Path("/getZipInfo")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getZipInfo(@FormParam("zipCode") String zipCode) {
		String response = dataFetchService.getZipInfo(zipCode);
		return response;
	}

	/**
	 * @param userId
	 * @return  updateUserFinancials
	 */
	@Path("/insertUserFinancial")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String test(@FormParam("userId") int userId) {
		return dataFetchService.updateUserFinancials(userId) + "";
	}

	/**
	 * @param mobileNo
	 * @param otp
	 * @param ipAddress
	 * @return jsonobject
	 */
	@Path("/otpverify")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String otpverify(@FormParam("mobileNo") String mobileNo, @FormParam("otp") String otp,
			@FormParam("ipAddress") String ipAddress,@FormParam("userId") String uId) {
		JSONObject jsonObject = new JSONObject();
		int response = dataFetchService.otpverify(mobileNo, otp, ipAddress);
		int userId= Integer.parseInt(uId);
		 
		try {
			if (response > 0) {
				/*11/12/2019: Geetha- Removal of code for sending verification email*/
					dataFetchService.insertPageDetails(userId);
			
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);				
			}else if(response ==-300){
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.EXPIRED_OTP_MSG);
			} 
			else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.INVALIDPIN);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "otpverify", e.toString(),
					"mobileNo:" + mobileNo + ",userDetails:" + otp);
			e.printStackTrace();
			try {
				jsonObject.put(ValuCirclesConstants.ERROR, e.toString());
			} catch (Exception e1) {
				errorLog.insertErrorLog("UserDataFetchController", "otpverify", e.toString(),
						"mobileNo:" + mobileNo + ",userDetails:" + otp);

				e1.printStackTrace();
			}
			return jsonObject.toString();
		}
		return jsonObject.toString();
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/loanTerm")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getLoanTerm(@FormParam("userId") int userId) {
		JSONObject jsonObject = new JSONObject();
		int response = dataFetchService.getLoanTerm(userId);
		try {
			if (response > 0) {
				jsonObject.put(ValuCirclesConstants.LOANTERM, response);
			} else {
				jsonObject.put(ValuCirclesConstants.LOANTERM, 0);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getLoanTerm", e.toString(), "userId:" + userId);

			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param userId
	 * @return score
	 */
	@Path("/getYodleeScore")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getYodleeScore(@FormParam("userId") int userId) {
		return dataFetchService.getYodleeScore(userId);
	}

	/**
	 * @return response
	 */
	@Path("/getListOfUserType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfUserType() {
		String response = dataFetchService.getListOfUserType();
		return response;
	}

	/**
	 * @param accessToken
	 * @return jsonobject
	 */
	@Path("/getfbdetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getfbdetails(@FormParam("accessToken") String accessToken) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String fbDetails = UtilityMethods.getFaceBookDetails(accessToken);
			if (fbDetails != null && !fbDetails.isEmpty()) {
				JSONObject jsonObject2 = new JSONObject(fbDetails);
				UserLoginDTO userLoginDTO = new UserLoginDTO();
				userLoginDTO.setUserCreds(jsonObject2.get("id").toString());
				userLoginDTO.setUserEmail(jsonObject2.get("email").toString());
				boolean fbnewuser = dataFetchService.isEmailRegistered(userLoginDTO.getUserEmail());
				if (fbnewuser == true) {
					UserLoginDTO uDto = dataFetchService.insertfbDetails(userLoginDTO);
					if (uDto.getUserId() > 0) {
						jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(uDto));
						jsonObject.put(ValuCirclesConstants.SESSION_ID, UtilityMethods.generateNewSessionForUser());
					} else {
						jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
					}
				} else {
					UserLoginDTO dto = dataFetchService.getCustomerFromEmail(userLoginDTO.getUserEmail());
					jsonObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(dto));
					jsonObject.put(ValuCirclesConstants.SESSION_ID, UtilityMethods.generateNewSessionForUser());
				}
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getfbdetails", e.toString(),
					"accessToken:" + accessToken);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param token
	 * @return jsonobject

	 */
	@Path("/getGmailDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getGmailDetails(@FormParam("token") String token) {
		JSONObject jObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String details = UtilityMethods.getGmailDetails(token);
			if (details != null && !details.isEmpty()) {
				JSONObject jsonObject = new JSONObject(details);
				UserLoginDTO userLoginDTO = new UserLoginDTO();
				userLoginDTO.setUserEmail(jsonObject.get("email").toString());
				userLoginDTO.setUserCreds(jsonObject.get("sub").toString());
				boolean newuser = dataFetchService.isEmailRegistered(userLoginDTO.getUserEmail());
				if (newuser == true) {
					UserLoginDTO user = dataFetchService.insertGmailUser(userLoginDTO);
					if (user.getUserId() > 0) {
						jObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(user));
						jsonObject.put(ValuCirclesConstants.SESSION_ID, UtilityMethods.generateNewSessionForUser());
					} else {
						jObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
					}
				} else {
					UserLoginDTO cu = dataFetchService.getCustomerFromEmail(userLoginDTO.getUserEmail());
					jsonObject.put(ValuCirclesConstants.SESSION_ID, UtilityMethods.generateNewSessionForUser());
					jObject.put(ValuCirclesConstants.RESULT, mapper.writeValueAsString(cu));
				}
			} else {
				jObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}

		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getGmailDetails", e.toString(), "token:" + token);
			e.printStackTrace();
		}
		return jObject.toString();
	}

	/**
	 * @return response
	 */ 
	@Path("/getLenderName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getLenderNameList() {
		String response = dataFetchService.getLenderNameList();
		return response;
	}

	/**
	 * @return response
	 */
	@Path("/getListOfLoanLender")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfLoanLender() {
		String response = dataFetchService.getListOfLoanLender();
		return response;
	}

	/**
	 * @param mobileNo
	 * @param type
	 * @return jsonobject
	 */
	@Path("/resendOtp")
	@POST 
	@Produces(MediaType.APPLICATION_JSON)
	public String resendOtp(@FormParam("mobileNo") String mobileNo,@FormParam("emailId") String emailId, @FormParam("type") String type) {
		JSONObject jsonObject = new JSONObject();
		boolean isUpdated = dataFetchService.resendOtp(mobileNo,emailId, type);
		try {
			if (isUpdated == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "resendOtp", e.toString(), "type:" + type);
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	/**
	 * @param subscriptionId
	 * @param subKey
	 * @param count
	 * @param subTypeId
	 * @return jsonobject
	 */
	@Path("/callSubscription")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String callSubcription(@FormParam("subscriptionId") int subscriptionId, @FormParam("subKey") String subKey,
			@FormParam("count") int count, @FormParam("subTypeId") int subTypeId) {
		return dataFetchService.callSubcription(subscriptionId, subKey, count, subTypeId);

	}

	/**
	 * @param subscriptorId
	 * @param emailId
	 * @param mysubtype
	 * @return jsonobject
	 */
	@Path("/assignSubscription")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String AssignSubscription(@FormParam("subscriptorId") int subscriptorId,
			@FormParam("emailId") String emailId, @FormParam("mysubtype") int mysubtype) {
		return dataFetchService.AssignSubscription(subscriptorId, emailId, mysubtype);
	}

	/**
	 * @param subscriptorId
	 * @return jsonobject
	 */
	@Path("/getTotalSubscriptionForSubscriptor")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getTotalSubscriptionforSubscriptor(@FormParam("subscriptorId") int subscriptorId) {
		return dataFetchService.getTotalSubscriptionforSubscriptor(subscriptorId);

	}

	/**
	 * @param subscriptorId
	 * @return jsonobject
	 */
	@Path("/listOfAssignSubscription")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String ListOfAssignSubscription(@FormParam("subscriptorId") int subscriptorId) {
		return dataFetchService.ListOfAssignSubscription(subscriptorId);
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/getListOfAllGetMethodPersonal")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfAllGetMethodPersonal(@FormParam("userId") int userId) {
		return dataFetchService.getListOfAllGetMethodPersonal(userId);
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/getListOfAllGetMethodFinancial")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfAllGetMethodFinancial(@FormParam("userId") int userId) {
		return dataFetchService.getListOfAllGetMethodFinancial(userId);
	}

	/**
	 * @param subscriptorId
	 * @return jsonobject
	 */
	@Path("/getSubscriptionCountAndListOfAssignSubscription")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getSubscriptionCountAndListOfAssignSubscription(@FormParam("subscriptorId") int subscriptorId) {
		return dataFetchService.getSubscriptionCountAndListOfAssignSubscription(subscriptorId);
	}

	/**
	 * @param userId
	 * @param sessionId
	 * @return jsonobject
	 */
	@Path("/getLpiPageListApi")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getLpiPageListApi(@FormParam("userId") int userId, @FormParam("sessionId") String sessionId) {
		return dataFetchService.getLpiPageListApi(userId, sessionId);
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */ 
	@Path("/getPerfiosScore")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getPerfiosScore(@FormParam("userId") int userId) {
		return dataFetchService.getPerfiosScore(userId);
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/getSubcriptionKey")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getSubcriptionKey(@FormParam("userId") int userId) {
		UserLoginDO loginDO = (UserLoginDO) dataFetchDAO.findById(UserLoginDO.class, userId);
		if (loginDO.getSubscriptionId() > 0)
			return Integer.toString(loginDO.getSubscriptionId());
		else {
			String response = dataFetchService.getSubcriptionKey(userId);
			return response;
		}
	}

	/**
	 * @param userId
	 * @param paymentId
	 * @return jsonobject
	 */
	@Path("/insertUserSubcription")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insertUserSubcription(@FormParam("userId") int userId, @FormParam("paymentId") int paymentId) {
		JSONObject jsonObject = new JSONObject();
		boolean response = dataFetchService.insertUserSubcription(userId, paymentId);
		try {
			if (response == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertUserSubcription", e.toString(),
					"userId:" + userId + "paymentId:" + paymentId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/getUserInfoDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserInfoDetailsUsingViews(@FormParam("userId") int userId) {
		String response = dataFetchService.getUserInfoDetailsUsingViews(userId);
		return response;
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/getCoApplicantId")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getCoApplicantId(@FormParam("userId") int userId) {
		return dataFetchService.getCoApplicantId(userId);
	}

	/**
	 * @param listOfUserId
	 * @return response
	 */
	@Path("/getCoApplicantDetailsUsingListOfUserId")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getCoApplicantDetailsUsingListOfUserId(@FormParam("listOfUserId") String listOfUserId) {
		String response = dataFetchService.getCoApplicantDetailsUsingListOfUserId(listOfUserId);
		return response;
	}

	/**
	 * @param userId
	 * @param mulUserCoAppDetails
	 * @return jsonobject
	 */
	@Path("/insertMultipleUserCoApplicantwithLogin")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insertMultipleUserCoApplicantwithLogin(@FormParam("userId") int userId,
			@FormParam("mulUserCoAppDetails") String mulUserCoAppDetails) {
		JSONObject jsonObject = new JSONObject();
		boolean response = dataFetchService.insertMultipleUserCoApplicantwithLogin(userId, mulUserCoAppDetails);
		try {
			if (response == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
				int response1 = dataFetchService.selectPageDetails(userId);
				if (response1 > 3) {
					jsonObject.put(ValuCirclesConstants.PAGE, response1);
				} else {
					int pages = 3;
					dataFetchService.updatePageDetails(userId, pages);
					jsonObject.put(ValuCirclesConstants.PAGE, pages);
				}			
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertMultipleUserCoApplicantwithLogin", e.toString(),
					"userId:" + userId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param userId
	 * @param debug
	 * @param yesNo
	 * @return jsonobject
	 * @throws JSONException
	 */
	@Path("/getUserLenderLpiScore")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserLenderLpiScore(@FormParam("userId") int userId, @FormParam("debug") int debug,
			@FormParam("yesNo") int yesNo) throws JSONException {
		return lpiService.getUserLenderLpiScore(userId, debug, yesNo);
	}

	/**
	 * @param userId
	 * @param mulUserCoAppFinancialDetails
	 * @return jsonobject
	 */
	@Path("/insertMultipleUserCoApplicantFinancials")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insertMultipleUserCoApplicantFinancials(@FormParam("userId") int userId,
			@FormParam("mulUserCoAppFinancialDetails") String mulUserCoAppFinancialDetails) {
		JSONObject jsonObject = new JSONObject();
		boolean response = dataFetchService.insertMultipleUserCoApplicantFinancials(userId,
				mulUserCoAppFinancialDetails);
		try {
			if (response == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
				int response1 = dataFetchService.selectPageDetails(userId);
				if (response1 > 5) {
					jsonObject.put(ValuCirclesConstants.PAGE, response1);
				} else {
					int pages = 5;
					dataFetchService.updatePageDetails(userId, pages);
					jsonObject.put(ValuCirclesConstants.PAGE, pages);
				}
				
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertMultipleUserCoApplicantFinancials", e.toString(),
					"userId:" + userId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/getVCLpiScore")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getVCLpiScore(@FormParam("userId") int userId) {
		return lpiService.getVCLpiScore(userId);
	}

	/**
	 * @param listOfUserId
	 * @return response
	 */
	@Path("/getCoApplicantEmploymentAndIncomeLoan")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getCoApplicantEmploymentAndIncomeLoan(@FormParam("listOfUserId") String listOfUserId) {
		String response = dataFetchService.getCoApplicantEmploymentAndIncomeLoan(listOfUserId);
		return response;

	}

	/**
	 * @param lenderId
	 * @return response
	 */
	@Path("/getLenderUserList")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getLenderUserList(@FormParam("lenderId") int lenderId) {
		String response = dataFetchService.getLenderUserList(lenderId);
		return response;
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/insertUserCoApplicantFinancial")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insertUserCoApplicantFinancial(@FormParam("userId") int userId) {
		return dataFetchService.updateUserCoApplicantFinancials(userId) + "";
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/getListOfAllGetMethodFinancialTest")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getListOfAllGetMethodFinancialTest(@FormParam("userId") int userId) {
		return dataFetchService.getListOfAllGetMethodFinancialTest(userId);
	}

	/**
	 * @return response
	 */
	@Path("/getListOfEmployerNameUsingDistinct")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfEmployerNameUsingDistinct() {
		String response = dataFetchService.getListOfEmployerNameUsingDistinct();
		return response;

	}

	/**
	 * @param userId
	 * @param coApplicantId
	 * @return jsonobject
	 */
	@Path("/updateUserCoApplicant")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String UpdateUserCoApplicant(@FormParam("userId") int userId,
			@FormParam("coApplicantId") int coApplicantId) {
		return dataFetchService.updateUserCoApplicant(userId, coApplicantId) + "";

	}

	/**
	 * @param userId
	 * @param bankId
	 * @return jsonobject
	 */
	@Path("/getVerifyUserIncomeAndLoan")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getVerifyUserIncomeAndLoan(@FormParam("userId") int userId, @FormParam("bankId") int bankId) {
		return lpiService.getVerifyUserIncomeAndLoan(userId, bankId);
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */ 
	@Path("/sessionManagement")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject sessionManagement(@FormParam("userId") int userId) {
		return dataFetchService.sessionManagement(userId);
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/userSession")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String userSession(@FormParam("userId") int userId) {
		return dataFetchService.getUserSession(userId);
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/updateIndustryAndEmployerFinancialData")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String updateIndustryAndEmployerFinancialData(@FormParam("userId") int userId) {
		String response = dataFetchService.updateIndustryAndEmployerFinancialData(userId);
		return response;
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/getCoApplicantIncomeCredits")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getCoApplicantIncomeCredits(@FormParam("userId") int userId) {
		String response = dataFetchService.getCoApplicantIncomeCredits(userId);
		return response;
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/associateSubscriptionToNewUser")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String associateSubscriptionToNewUser(@FormParam("userId") int userId) {
		return lpiService.associateSubscriptionToNewUser(userId);
	}

	/**
	 * @param mobileNo
	 * @param otp
	 * @return jsonobject
	 */
	@Path("/otpverify1")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String otpverify1(@FormParam("mobileNo") String mobileNo, @FormParam("otp") String otp) {
		JSONObject jsonObject = new JSONObject();
		int response = dataFetchService.otpverify1(mobileNo, otp);
		try {
			if (response > 0) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.INVALIDPIN);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "otpverify1", e.toString(),
					"mobileNo:" + mobileNo + ",userDetails:" + otp);
			e.printStackTrace();
			try {
				jsonObject.put(ValuCirclesConstants.ERROR, e.toString());
			} catch (Exception e1) {

				errorLog.insertErrorLog("UserDataFetchController", "otpverify1", e.toString(),
						"mobileNo:" + mobileNo + "otp:" + otp);
				e1.printStackTrace();
			}
			return jsonObject.toString();
		}
		return jsonObject.toString();
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/getListOfLpiStatus")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfLpiStatus(@FormParam("userId") int userId) {
		String response = dataFetchService.getListOfLpiStatus(userId);
		return response;
	}

	/**
	 * @param userId
	 * @param lenderId
	 * @param lenderStatus
	 * @return jsonobject
	 */
	@Path("/insertLenderLpiStatusAndComments")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insertLenderLpiStatusAndComments(@FormParam("userId") int userId, @FormParam("lenderId") int lenderId,
			@FormParam("lenderStatus") String lenderStatus) {
		JSONObject jsonObject = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		boolean response = dataFetchService.insertLenderLpiStatusAndComments(userId, lenderId, lenderStatus);
		try {
			if (response == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
				jsonObject.put("StatusAndComments",
						mapper.writeValueAsString(dataFetchDAO.getLenderLpiStatusAndComments(userId, lenderId)));
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "insertLenderLpiStatusAndComments", e.toString(),
					"userId:" + userId + "lenderId:" + lenderId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param userId
	 * @param lenderId
	 * @return response
	 */
	@Path("/getLenderLpiStatusAndComments")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getLenderLpiStatusAndComments(@FormParam("userId") int userId, @FormParam("lenderId") int lenderId) {
		String response = dataFetchService.getLenderLpiStatusAndComments(userId, lenderId);
		return response;
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/getListOfRefEmploymentType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfRefEmploymentType(@FormParam("userId") int userId) {
		String response = dataFetchService.getListOfRefEmploymentType(userId);
		return response;
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/getListOfRefRelationShip")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfRefRelationShip(@FormParam("userId") int userId) {
		String response = dataFetchService.getListOfRefRelationShip(userId);
		return response;
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/updateLoginUserLpiStatus")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String updateLoginUserLpiStatus(@FormParam("userId") int userId) {
		JSONObject jsonObject = new JSONObject();
		boolean response = dataFetchService.updateLoginUserLpiStatus(userId);
		try {
			if (response == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "updateLoginUserLpiStatus", e.toString(),
					"userId:" + userId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	
	/**
	 * @param userId
	 * @param lenderId
	 * @return jsonobject
	 */
	@Path("/updatelenderAppInitiated")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String updatelenderAppInitiated(@FormParam("userId") int userId,@FormParam("lenderId") int lenderId) {
		JSONObject jsonObject = new JSONObject();
		boolean response = dataFetchService.updatelenderAppInitiated(userId, lenderId);		
		try {
			if (response == true) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "updateLoginUserLpiStatus", e.toString(),
					"userId:" + userId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/getListOfRefSalaryDeduction")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getListOfRefSalaryDeduction(@FormParam("userId") int userId) {
		String response = dataFetchService.getListOfRefSalaryDeduction(userId);
		return response;
	}

	/**
	 * @param emailId
	 * @return jsonobject
	 */
	@Path("/getSubscriptionSponsor")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getSubscriptionSponsor(@FormParam("emailId") String emailId) {
		JSONObject jsonObject = new JSONObject();
		Gson gson = new Gson();
		Map<String, Object> responce = dataFetchService.getSubscriptionSponsor(emailId);
		try {
			if (responce != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, gson.toJson(responce.get("#result-set-1")));
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, "This user don't have the subcription");
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getSubscriptionSponsor", e.toString(),
					"emailId:" + emailId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param emailId
	 * @return response
	 */
	@Path("/firstTimeLoginCheck")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String firstTimeLoginCheck(@FormParam("emailId") String emailId) {
		JSONObject jsonObject = new JSONObject();
		String responce = dataFetchService.firstTimeLoginCheck(emailId);
		try {
			jsonObject.put(ValuCirclesConstants.RESULT, responce);
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "firstTimeLoginCheck", e.toString(),
					"emailId:" + emailId);
			e.printStackTrace();
		}
		responce = jsonObject.toString();
		return responce;
	}

	/**
	 * @param userId
	 * @param subId
	 * @return response
	 */
	@Path("/selectedSubscription")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String selectedSubscription(@FormParam("userId") int userId, @FormParam("subId") int subId) {
		JSONObject jsonObject = new JSONObject();
		String responce = null;
		boolean result = dataFetchService.selectedSubscription(userId, subId);
		try {
			if (result) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "selectedSubscription", e.toString(),
					"userId:" + userId + "subId:" + subId);
			e.printStackTrace();
		}
		responce = jsonObject.toString();
		return responce;
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/getAssetProductType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getAssetProductType(@FormParam("userId") int userId) {
		String response = dataFetchService.getAssetProductType(userId);
		return response;
	}
	
	/**
	 * @return response
	 */
	@Path("/getRefloantimeframe")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getRefloantimeframe() {
		String response = dataFetchService.getRefloantimeframe();
		return response;
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/getAllSubscriptionType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getSubscriptionType(@FormParam("userId") int userId) {
		String response = dataFetchService.getAllSubscriptionType();
		return response;
	}

	/**
	 * @param userId
	 * @return response
	 */
	@Path("/getAssetType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getAssetType(@FormParam("userId") int userId) {
		String response = dataFetchService.getAssetType(userId);
		return response;
	}

	/**
	 * @param fname
	 * @param email
	 * @param sub
	 * @param msg
	 * @return response
	 */
	@Path("/sendMailToContact")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String sendMailToContact(@FormParam("Fname") String fname, @FormParam("email") String email,
			@FormParam("subject") String sub, @FormParam("msg") String msg) {
		String responce = UtilityMethods.mailToContact(fname, email, sub, msg);
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put(ValuCirclesConstants.RESULT, responce);
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "sendMailToContact", e.toString(),
					"email:" + email + "subject:" + sub + "msg:" + msg);
			e.printStackTrace();
			// TODO: handle exception
		}
		return responce;
	}

	/**
	 * @author sathish get the rate for the subscription for the builder
	 */
	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/getSubRates")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getSubRates(@FormParam("userId") int userId) {
		return dataFetchService.getSubRates(userId);
	}

	/**
	 *
	 * @author Sathish get the userInfo for the builder s
	 * 
	 */
	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/getUserInfoDetailsForBuilders")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserInfoDetailsForBuilders(@FormParam("userId") int userId) {
		return dataFetchService.getUserInfoDetailsForBuilders(userId);
	}

	/**
	 * 
	 * @author sathish insert the user details for the credit score
	 * 
	 */
	/******
	 * commented temporarily to suppress build errors - need to invoke this when
	 * Sathish ready ..MT
	 *****/

	/**
	 * get the user details for credit score
	 */
	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/getUserDetailsForCreditScore")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserDetailsForCreditScore(@FormParam("userId") int userId) {
		return dataFetchService.getUserDetailsForCreditScore(userId);
	}

	/**
	 * @param userInfo
	 * @param processStr
	 * @param ans
	 * @param myuserId
	 * @return dataResult
	 */
	@Path("/insertUserDetailsForCreditScore")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String insertUserDetailsForCreditScore(@FormParam("userInfo") String userInfo,
			@FormParam("questionData") String processStr, @FormParam("userAns") String ans,
			@FormParam("mysUserId") int myuserId) {
		String dataResult = dataFetchService.insertUserDetailsForCreditScore(userInfo, processStr, ans, myuserId);

		return dataResult;
	}

	/**
	 * @param userId
	 * @return ""
	 */
	@Path("/test")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getTest(@FormParam("userId") String userId) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			CreditScoreDataDTO dtoData = objectMapper.readValue(userId, CreditScoreDataDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			errorLog.insertErrorLog("UserDataFetchController", "getTest", e.toString(), "userId:" + userId);
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * @param userId
	 * @param ans
	 * @return jsonobject
	 */
	@Path("/getMyLPICount")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getMyLPICount(int userId, int ans) {
		JSONObject jsonObject = new JSONObject();
		String responce = dataFetchService.getMyLPICount(userId, ans);
		try {
			if (responce != null) {
				jsonObject.put(ValuCirclesConstants.RESULT, responce);
			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, "This user don't have the subcription");
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getMyLPICount", e.toString(),
					"userId:" + userId + "ans:" + ans);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param token
	 * @param uid
	 * @return null
	 */
	@Path("/emailVerfication")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response emailVerfication(@QueryParam("token") String token, @QueryParam("uid") int uid) {
		try {
			String response = dataFetchService.emailVerfication(token, uid);
			if (response.equalsIgnoreCase(ValuCirclesConstants.SUCCESS)) {
				dataFetchService.insertPageDetails(uid);

				java.net.URI location = new java.net.URI(UtilityMethods.getProperty(ValuCirclesConstants.REDIRECT_URL));
				return Response.temporaryRedirect(location).build();
			}
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "emailVerfication", e.toString(),
					"token:" + token + "uid:" + uid);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/refreshsession")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String refreshsession(@FormParam("userId") String userId) {
		JSONObject jsonObject = new JSONObject();
		String result = "";
		try {
			result = "Success";
			jsonObject.put(ValuCirclesConstants.RESULT, result);
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "refreshsession", e.toString(), "userId:" + userId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/fetchPrimaryaddress")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchPrimaryaddress(@FormParam("userId") String userId) {
		JSONObject jsonObject = new JSONObject();

		try {
			String result = dataFetchService.fetchaddress(userId);
			jsonObject.put(ValuCirclesConstants.RESULT, result.toString());

		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "fetchPrimaryaddress", e.toString(), "userId:" + userId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param userId
	 * @return jsonobject
	 */
	@Path("/getpages")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String pagecount(@FormParam("userId") int userId) {
		JSONObject jsonObject = new JSONObject();

		try {
			String result = dataFetchService.selectpages(userId);
			jsonObject.put(ValuCirclesConstants.PAGE, result);
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "pagecount", e.toString(), "userId:" + userId);
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param val
	 * @return jsonobject
	 */
	@Path("/getcompanylistusingprobe")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getcompanylistusingprobe(@FormParam("val") String val) {
		JSONObject jsonObject = new JSONObject();

		try {
			String list = ProbeHelper.getPerfiosCompanyList(val, true);
			jsonObject.put(ValuCirclesConstants.PAGE, list);
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getcompanylistusingprobe", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	/**
	 * @param cin
	 * @return jsonobject
	 */
	/* Modification done by Priyaraj on 23-02-2023  for taking companynames from ref_employer_category_mapping instead from Probe URL */
	//@Path("/getcompanylistusingcin")	
	@Path("/getorganizationlistusingemployernameandcategory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	/*public String getcompanylistusingcin(@FormParam("cin") String cin) {
		
		JSONObject jsonObject = new JSONObject();
		try {
			String list = ProbeHelper.getPerfiosCompanyList(cin, false);
			jsonObject.put(ValuCirclesConstants.PAGE, list);
		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getcompanylistusingprobe", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}*/
	public String getorganizationlistusingemployernameandcategory(@FormParam("employerName") String employerName,@FormParam("categoryId") int categoryId) {
		
		return dataFetchService.getOrganizationNamesFromCategoryMapping(employerName, categoryId);
	}

	/**
	 * @param userId
	 * @return list
	 */
	@Path("/getlenderapplications")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getLenderApplications(@FormParam("userId") String userId) {
		JSONObject jsonObject = new JSONObject();
		String list = "";
		try {
			list = dataFetchService.getLenderApplications(userId);

		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getLenderApplication", e.toString(), "");
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * @param userId
	 * @param lenderId
	 * @return list
	 */
	@Path("/getlenderapplicationstatus")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getLenderApplicationStatus(@FormParam("userId") String userId,@FormParam("lenderId") String lenderId) {
		JSONObject jsonObject = new JSONObject();
		String list = "";
		try {
			
			list = dataFetchService.getLenderApplicationStatus(userId,lenderId);

		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getLenderApplicationStatus", e.toString(), "");
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @param userId
	 * @param lenderId
	 * @return list
	 */
	@Path("/getlenderapplicationsanctions")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getLenderApplicationSanctions(@FormParam("userId") String userId,@FormParam("lenderId") String lenderId) {
		JSONObject jsonObject = new JSONObject();
		String list = "";
		try {
			
			list = dataFetchService.getLenderApplicationSanctions(userId,lenderId);

		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getLenderApplicationSanctions", e.toString(), "");
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * @param userId
	 * @param lenderId
	 * @return list
	 */
	@Path("/getlenderapplicationdisbursements")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getLenderApplicationDisbursements(@FormParam("userId") String userId,@FormParam("lenderId") String lenderId) {
		JSONObject jsonObject = new JSONObject();
		String list = "";
		try {			
			list = dataFetchService.getLenderApplicationDisbursements(userId,lenderId);

		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "getLenderApplicationDisbursements", e.toString(), "");
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @param lenders
	 * @return jsonobject
	 */
	@Path("/applyselectedloans")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String applyForSelectedLenders(@FormParam("lenders") String lenders) {
		JSONObject jsonObject = new JSONObject();
		boolean isInserted = false;
		try {

			isInserted = dataFetchService.applyForSelectedLenders(lenders);
			if (isInserted) {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);

			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);

			}

		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "applyForSelectedLenders", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	//registration count,form submitted count email functionality 
	/**
	 * @param Fromdate
	 * @param Todate
	 * @return jsonobject
	 */
	@Path("/getcount")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String getcount(@FormParam("Fromdate") String Fromdate, @FormParam("Todate") String Todate) {
		JSONObject jsonObject = new JSONObject();
		try {
			String result = dataFetchService.getcount(Fromdate, Todate);
			String result1 = dataFetchService.getlpicompleted(Fromdate, Todate);
		//	String result3 = dataFetchService.getappliedloanscount(Fromdate, Todate);//09/07/2019:Geetha- Commenting out the report generation of Applied loan
		//	if (result != null || result1 != null || result3 != null) { 
			if (result != null || result1 != null ) {
				jsonObject.put(ValuCirclesConstants.RESULT, "Success");

			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);

			}

		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "applyForSelectedLenders", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	
	/**
	 * @return jsonobject
	 */
	@Path("/deleteresponselog")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteresponselog() {
		JSONObject jsonObject = new JSONObject();
		try {
			String result = dataFetchService.deleteresponselog( );
			if (result != null ) {
				jsonObject.put(ValuCirclesConstants.RESULT, "Success");

			} else {
				jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);

			}

		} catch (Exception e) {
			errorLog.insertErrorLog("UserDataFetchController", "applyForSelectedLenders", e.toString(), "");
			e.printStackTrace();
		}
		return jsonObject.toString();
	}
	
	
	
	
	

}