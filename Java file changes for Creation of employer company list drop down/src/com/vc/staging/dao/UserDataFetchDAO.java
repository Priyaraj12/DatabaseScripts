package com.vc.staging.dao;

import java.util.List;
import java.util.Map;

import com.vc.staging.dto.AgeDTO;
import com.vc.staging.dto.CreditScoreDTO;
import com.vc.staging.dto.EducationDTO;
import com.vc.staging.dto.EducationDetailDTO;
import com.vc.staging.dto.EducationInstitutionDTO;
import com.vc.staging.dto.EmployerCategoryDTO;
import com.vc.staging.dto.EmployerDTO;
import com.vc.staging.dto.FinalCoApplicantLatestDTO;
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
import com.vc.staging.dto.RefLoanTimeFrameDTO;
import com.vc.staging.dto.RefProductTypeDTO;
import com.vc.staging.dto.RefSalaryDeductionDTO;
import com.vc.staging.dto.RefSubscriptionTypeDTO;
import com.vc.staging.dto.ReffrequencyDTO;
import com.vc.staging.dto.RelationShipDTO;
import com.vc.staging.dto.ResidenceDTO;
import com.vc.staging.dto.SessionManagement;
import com.vc.staging.dto.UserDataDTO;
import com.vc.staging.dto.UserEmploymentDTO;
import com.vc.staging.dto.UserFinancialDTO;
import com.vc.staging.dto.UserIncomeDTO;
import com.vc.staging.dto.UserLenderDTO;
import com.vc.staging.dto.UserListDTO;
import com.vc.staging.dto.UserLoanDTO;
import com.vc.staging.dto.UserLoginDTO;
import com.vc.staging.dto.UserSalaryDeductionDTO;
import com.vc.staging.dto.UserSession;
import com.vc.staging.dto.UserTypeDTO;
import com.vc.staging.dto.YodleeCacheDTO;
import com.vc.staging.dto.ZipDTO;
import com.vc.staging.model.BuilderUserDO;
import com.vc.staging.model.EmployerDO;
import com.vc.staging.model.IndustrySectorDO;
import com.vc.staging.model.LenderDO;
import com.vc.staging.model.LenderDisbursementDO;
import com.vc.staging.model.LenderUserDO;
import com.vc.staging.model.OptVerifyDO;
import com.vc.staging.model.RefAgeDO;
import com.vc.staging.model.RefEmployerTypeDO;
import com.vc.staging.model.RefZipDO;
import com.vc.staging.model.SubscriptionDetailDO;
import com.vc.staging.model.UserAddressDO;
import com.vc.staging.model.UserEmploymentDO;
import com.vc.staging.model.UserFinancialDO;
import com.vc.staging.model.UserIncomeDO;
import com.vc.staging.model.UserInfoDO;
import com.vc.staging.model.UserLenderCommentDO;
import com.vc.staging.model.UserLendersStatusDO;
import com.vc.staging.model.UserLoanDO;
import com.vc.staging.model.UserLoginDO;
import com.vc.staging.model.UserLpiLenderDO;
import com.vc.staging.model.UserlenderSanctionDetailsDO;

/**
 * 
 * @author Thanga
 *
 */

public interface UserDataFetchDAO extends AbstractHibernateDAO {
	
	public boolean validateemail(String userEmail);
	
	public String getSalt(String emailId);
	
	public UserLoginDO userLoginDetails(String emailId, String pass);
	
	public List<GenderDTO> getListOfGender();
	
	public List<MaritalStatusDTO> getListOfMaritalStatus();
	
	public List<OccupationTypeDTO> getListOfOccupationType();
	
	public List<OccupationCategoryDTO> getListOfOccupationCategory(int OccupationtypeId);
	
	public List<EducationDTO> getListOfEducation();
	
	public EmployerDO getEmployerDetails(String employerName);
	
	public List<EmployerDTO> getListOfEmployer();
	
	public UserDataDTO getUserInfoByUserId(int userId);

	public List<UserLoanDTO> getUserloanByUserId(int userId);
	
	public UserEmploymentDTO getUserEmploymentByuserId(int userId);
	
	public List<AgeDTO> getListOfAge();
	
	public List<CreditScoreDTO> getListOfCreditScore();
	
	public List<LoanTypeDTO> getListOfLoanType();
	
	public String getEmployerName(int userId);
	
	public UserEmploymentDO getEmploymentDetails(int userId);
	
	public List<UserLoanDO> getLoanDetails(int userId);
	
	public IndustrySectorDO getIndustryDetails(String industrySegmentName);
	
	public List<IndustrySectorDTO> getListOfIndustrySector();

	public List<UserIncomeDO> getListofIncome(int userId);

	public List<UserIncomeDTO> getUserIncomelistById(int userId);
	
	public boolean updateUserIncome(int userId);
	
	public boolean updateUserLoan(int userId);

	public List<IncomeTypeDTO> getListOfIncomeType();

	public List<UserListDTO> getUserListData();

	public List<ResidenceDTO> getListOfResidence();
	
	public Map<String, Object> getLpiFromSP(int userId);
	
	public List<EducationDetailDTO> getEducationDetailByEducationId(int educationId);
	
	public List<EducationInstitutionDTO> getEducationInstitutionByEducationDetailId(int educationDetailId);
	
	public List<EducationDetailDTO> getListOfEducationDetails();
	
	public List<EducationInstitutionDTO> getListOfEducationInstitution();
	
	public UserLoginDO getuserDetails(String emailId);
	
	public String resetPassword(String emailId, int userId, String newpwd, String sessionId);
	
	public String changePassword(int userId, String oldpwd, String newpwd);

	public RefAgeDO getAgeDetails(int refAgeId);
	
	public List<RefZipDO> getListOfZip();
	
	public boolean updateUserFinancials(int userId);	

	public List<ZipDTO> getZipInfo(String zipCode);

	public OptVerifyDO getOtpDetails(String mobileNo);
	
	public UserInfoDO getUserInfoDetails(int userId);

	public List<UserFinancialDO> getUserFinancials();

	public YodleeCacheDTO getYodleeScore(int userId);
	
	public List<UserTypeDTO> getListOfUserType();
	
	public List<LenderDO> getgetLenderNameList();
	
	public List<LoanLenderDTO> getListOfLoanLender();
	
	public OptVerifyDO getUserOtp(String mobileNo);

	public UserLoginDO getUserById(String mobileNo);

	public Map<String, Object> callSubcription(int subscriptionId, String subKey, int count, int subTypeId);

	public Map<String, Object> AssignSubscription(int subscriptorId, String emailId, int mysubtype);
	
	public Map<String, Object> getTotalSubscriptionforSubscriptor(int subscriptorId);
	
	public Map<String, Object> ListOfAssignSubscription(int subscriptorId);

	public boolean validateMobile(String userPhone, String countrycode);
	
	public PerfiosCacheDTO getPerfiosScore(int userId);
	
	public boolean updateUserOtp(String userPhone);
	
	public boolean updateUserAsset(int userId);
	
	public UserFinancialDTO getUserFinancialByUserId(int userId);
	
	public boolean updateUserCoApplicant(int userId);
	
	public boolean updateUserCoApplicantIncome(int userId);
	
	public boolean updateUserCoApplicantLoan(int userId);
	
	public boolean updateUserCoApplicantSelfEmployement(int userId);
	
	public boolean updateUserCoApplicantEmployement(int userId);
	
	public List<UserIncomeDTO> getUserCoApplicantIncome(int userId);
	
	public List<UserLoanDTO> getUserCoApplicantLoan(int userId);
	
	public UserInfoDO getUserName(int userId);

	public SubscriptionDetailDO getSubcriptionKey(String userEmail);
	
	public UserDataDTO getUserInfoDetailsUsingViews(int userId);

	public Map<String, Object> getCoApplicantId(int userId);
	
	public List<UserDataDTO> getCoApplicantDetailsUsingListOfUserId(String listOfUserId);

	public UserLoginDO getUser(int userId);

	public boolean updateUserCoApplicantInfo(int userId);

	public boolean updateUserCoApplicantAddress(int userId);

	public boolean updateUserCoApplicantLogin(int userId);

	public UserInfoDO getUserInfoDT(int userId);

	public UserAddressDO getUserAddress(int userId);

	public List<UserLoginDO> getCoApplicantUserLogin(int userId);

	public List<UserLoginDO> getUserLoginForCoApplicant(int userId);

	public UserEmploymentDO getUserCoApplicantEmployments(int userId);	

	public boolean updateCoApplicantEmployment(int userId);

	public boolean updateCoApplicantIncome(int userId);

	public boolean updateCoApplicantLoan(int userId);

	public List<FinalCoApplicantLatestDTO> getCoApplicantEmploymentAndIncomeLoan(String listOfUserId);

	public List<LenderUserDTO> getLenderUserList(int lenderId);

	public LenderDO getLenderNames(int lenderId);

	public BuilderUserDO getBuilderNameUsingUserId(int userId);

	public LenderUserDO getLenderId(int userId);

	public boolean updateUserCoApplicantFinancials(int userId);

	public List<EmployerDTO> getListOfEmployerNameUsingDistinct();

	public boolean updateCoApplicantuserFinancial(int userId);

	public SessionManagement getSessionManagement(int userId);

	public UserSession getUserSessionManagement(int userId);

	public void updateUserAssetFinancingSession(int userId);

	public void updateUserInfoSession(int userId);

	public void updateUserEmploymentSession(int userId);

	public void updateUserIncomeSession(int userId);

	public void updateUserLoanSession(int userId);

	public String getCoApplicantIncomeCredits(int userId);
	
	public List<LenderLpiStatusDTO> getListOfLpiStatus();
	
	public UserLpiLenderDO getUserLpiLenderId(int userId, int lenderId);
	
	public List<LenderLpiStatusAndCommentsDTO> getLenderLpiStatusAndComments(int userId, int lenderId);
	
	public List<RefEmploymentTypeDTO> getListOfRefEmploymentType();

	public List<RelationShipDTO> getListOfRefRelationShip();

	public boolean updateLoginUserLpiStatus(int userId);

	public List<RefSalaryDeductionDTO> getListOfRefSalaryDeduction();

	public boolean updateUserSalaryDeduction(int userId);

	public List<UserSalaryDeductionDTO> getUserSalaryDeductionByUserId(int userId);

	public void updateUserSalaryDeductionByUserId(int userId);

	public boolean updateCoApplicantSalaryDeduction(int userId);

	public Map<String, Object> getSubscriptionSponsor(String emailId);

	public int firstTimeLoginCheck(String emailId);

	public SubscriptionDetailDO selectedSubscription(int subId);

	public Map<String, Object> selectedSubscriptionSponsor(int userId, int subId);

	public List<RefProductTypeDTO> getAssetProductType();
	
	public String getRefloantimeframe();

	public List<RefSubscriptionTypeDTO> lsitOfAllSubscriptionType();

	public List<RefAssetTypeDTO> getAssetType(int userId);

	public Map<String, Object> getSubRates(int userId);

	public Map<String, Object> getUserInfoDetailsForBuilders(int userId);
	
	public Map<String, Object> insertUserDetailsForCreditScore(String userInfo, int i);

	public String getUserDetailsForCreditScore(int userId);
	
	public String getCreditReport(String userInfo);

	public int insertResponceCrif(String data, int userId);
	
	public int insertQuestions(String Data, int userId);
	
	public String getMyLPICount(int userId, int ans);

	public int userLoginDetails1(String emailId);
	
	public UserLoginDO getUserRecordByEmail(String token);
	
	public boolean activateUserAccount(String email);

	public int selectlogincount(String emailId);

	public int emailverify(String emailId);

	public String selectuserEmail(int userId);

	public boolean updateOtpVerify(String mobileNo);

	public UserLoginDO getUserRecordByPhone(String token, int uid);

	public boolean resetLoginAttempts(UserLoginDTO loginDTO);

	public List<ReffrequencyDTO> getListOffrequency();

	Map<String, Object> deactivateCoApplicants(int userId);

	public String fetchaddress(String userId);

	public Map<String, Object> getLenderApplications(String userId);
	
	public Map<String, Object> getLenderApplicationStatus(String userId, String lenderId);
	
	public Map<String, Object> getLenderApplicationSanctions(String userId, String lenderId);
	
	public Map<String, Object> getLenderApplicationDisbursements(String userId, String lenderId);

	public String selectClassificationId(String classification);

	public String getclassificationid(String employerName);
	
	public UserLenderCommentDO applyForSelectedLenders(UserLenderDTO doList);

	public UserLendersStatusDO updateUserLenderStatus(UserLenderCommentDO userComents);

	public UserlenderSanctionDetailsDO updateUserLenderSanctions(UserLendersStatusDO userStatus);

	public LenderDisbursementDO updateUserLenderDisbursement(UserlenderSanctionDetailsDO sanctions);
	
	public String getcount(String Fromdate, String Todate);
	
	public String getlpicompleted(String Fromdate, String Todate);
	
	public String getappliedloanscount(String Fromdate, String Todate);
	
	public String deleteresponselog();
	
	//Method added by Priyaraj on 23-02-2023 to get companyList using employerName and employerCategory
	public List<EmployerCategoryDTO> getOrganizationList(String employerName,int employerCategoryId);

}
