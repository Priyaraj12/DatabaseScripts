package com.vc.staging.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.vc.staging.dto.ReqResLogDTO;
import com.vc.staging.dto.UserLoginDTO;
import com.vc.staging.model.NavigationPageDO;
import com.vc.staging.model.OptVerifyDO;
import com.vc.staging.model.UserAddressDO;

/**
 * 
 * @author Thanga
 *
 */

public interface UserDataFetchService {
	
	public boolean verifynewemailreg(String userDetails);
	
	public int insertSignUpDetails(String userDetails);

	public int insertPageDetails(int response);

	public UserLoginDTO userLogin(String emailId, String password);
	
	public String getListOfGender();
	
	public String getListOfMaritalStatus();
	
	public int addUserActivity(int userId, String activity);
	
	public boolean insertGender(String genderDetails);

	public boolean insertMaritalStatus(String maritalStatusDetails);
	
	public boolean deleteGender(int genderId);
	
	public boolean deleteMaritalStatus(int maritalStatusId);

	public boolean insertOccupationType(String occupationDetails);

	public boolean deleteOccupationType(int occupationTypeId);

	public String getListOfOccupationType();
	
	public String getListOfOccupationCategory(int OccupationtypeId);
	
	public boolean insertUserData(int userId, String userDetails);

	public String getListOfEducation();

	public String getListOfEmployer();

	public String getSummaryDetails(int userId);

	public String getListOfAge();

	public String getListOfCreditScore();

	public String getListOfLoanType();

	public String getListOfIndustrySector();

	public String getListOfIncomeType();

	public boolean insertFinancialData(int userId, String financialDetails, String loanDetails, String incomeDetails,
			String salaryDeduction);

	public String getUserListData();

	public String getListOfResidence();
	
	public String getLpiFromSP(int userId);

	public String getEducationDetailByEducationId(int educationId);
	
	public String getEducationInstitutionByEducationDetailId(int educationDetailId);

	public String getListOfEducationDetails();

	public String getListOfEducationInstitution();

	public String forgotPassword(String emailId, String siteName);

	public String resetPassword(String emailId, int userId, String newpwd, String sessionId);

	public String changePassword(int userId, String oldpwd, String newpwd);

	public String getListOfZip();

	public boolean updateUserFinancials(int userId);
	
	public String getZipInfo(String zipCode);

	public int otpverify(String mobileNo, String otp, String ipAddress);

	public OptVerifyDO getOtpVerifyDetails(String mobileNo);

	public int getLoanTerm(int userId);

	public String getYodleeScore(int userId);

	public String getListOfUserType();

	public UserLoginDTO insertfbDetails(UserLoginDTO userLoginDTO);

	public UserLoginDTO getCustomerFromEmail(String userEmail);

	public UserLoginDTO insertGmailUser(UserLoginDTO userLoginDTO);

	public String getLenderNameList();

	public String getListOfLoanLender();

	public boolean resendOtp(String mobileNo,String emailId, String type);
	
	public boolean isEmailRegistered(String userEmail);

	public String callSubcription(int subscriptionId, String subKey, int count, int subTypeId);
	
	public String AssignSubscription(int subscriptorId, String count, int mysubtype);
	
	public String getTotalSubscriptionforSubscriptor(int subscriptorId);

	public String ListOfAssignSubscription(int subscriptorId);

	public String getListOfAllGetMethodPersonal(int userId);

	public String getListOfAllGetMethodFinancial(int userId);

	public String getSubscriptionCountAndListOfAssignSubscription(int subscriptorId);

	public String getLpiPageListApi(int userId, String sessionId);

	public boolean verifynewMobileReg(String userSignUp);

	public String getPerfiosScore(int userId);

	public String getSubcriptionKey(int userId);

	public boolean insertUserSubcription(int userId, int paymentId);

	public String getUserInfoDetailsUsingViews(int userId);
	
	public String getCoApplicantId(int userId);

	public String getCoApplicantDetailsUsingListOfUserId(String listOfUserId);

	public boolean insertMultipleUserCoApplicantwithLogin(int userId, String mulUserCoAppDetails);

	public boolean insertMultipleUserCoApplicantFinancials(int userId, String mulUserCoAppFinancialDetails);

	public String getCoApplicantEmploymentAndIncomeLoan(String listOfUserId);

	public String getLenderUserList(int lenderId);

	public boolean updateUserCoApplicantFinancials(int userId);

	public JSONObject getListOfAllGetMethodFinancialTest(int userId);
	
	public String getListOfEmployerNameUsingDistinct();

	public boolean updateUserCoApplicant(int userId, int coApplicantId);

	public JSONObject sessionManagement(int userId);

	public String getUserSession(int userId);

	public String updateIndustryAndEmployerFinancialData(int userId);

	public String getCoApplicantIncomeCredits(int userId);

	public int otpverify1(String mobileNo, String otp);

	public String getListOfLpiStatus(int userId);

	public boolean insertLenderLpiStatusAndComments(int userId, int lenderId, String lenderStatus);
	
	public String getLenderLpiStatusAndComments(int userId, int lenderId);
	
	public String getListOfRefEmploymentType(int userId);
	
	public String getListOfRefRelationShip(int userId);

	public boolean updateLoginUserLpiStatus(int userId);

	public String getListOfRefSalaryDeduction(int userId);

	public Map<String, Object> getSubscriptionSponsor(String emailId);

	public String firstTimeLoginCheck(String emailId);

	public Boolean selectedSubscription(int userId, int subId);

	public String getAssetProductType(int userId);
	
	public String getRefloantimeframe();

	public String getAllSubscriptionType();

	public String getAssetType(int userId);

	public boolean verifynewSubscriptionemail(String subscriptionMail);

	public String getSubRates(int userId);

	public String getUserInfoDetailsForBuilders(int userId);
	
	public String insertUserDetailsForCreditScore(String userInfo);

	public String getUserDetailsForCreditScore(int userId);

	public int insertResponceCrif(String data, int userId);

	public int insertQuestions(String Data, int userId);

	String insertUserDetailsForCreditScore(String userInfo, String processStr, String ans, int userId);

	public String getMyLPICount(int userId, int ans);

	public String emailVerfication(String token, int uid);

	public int selectlogincount(String emailId);

	public int emailverify(String emailId);

	boolean insertRequestLog(ReqResLogDTO logDTO);

	boolean insertResponseLog(ReqResLogDTO logDTO);

	public String getListOffrequency();

	public int updatePageDetails(int userId, int pages);

	public int updatecoapplicantPageDetails(int userId, int pages);

	public String fetchaddress(String userId);

	public String selectpages(int userId);

	public int selectPageDetails(int userId);

	public int selectcoapplicant(int userId);

	public int selectuserId(String userSignUp) throws JsonParseException, JsonMappingException, IOException;

	boolean updatelenderAppInitiated(int userId, int lenderId);

	public String getLenderApplications(String userId);

	public String getLenderApplicationStatus(String userId, String lenderId);

	public String getLenderApplicationSanctions(String userId, String lenderId);

	public String getLenderApplicationDisbursements(String userId, String lenderId);

	public boolean applyForSelectedLenders(String lenders);
	
	public String getcount(String Fromdate,String Todate);
	
	public String deleteresponselog();
	
	public String getlpicompleted(String Fromdate,String Todate);
	
	public String getappliedloanscount(String Fromdate,String Todate);
	
	//New method added by Priyaraj on 23-02-2023 to get the employer names from ref_employer_category_mapping
	public String getOrganizationNamesFromCategoryMapping(String employerName,int categoryId);


}
