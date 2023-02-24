package com.vc.staging.dao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vc.staging.dto.AgeDTO;
import com.vc.staging.dto.AssetDTO;
import com.vc.staging.dto.AssetFinancingDTO;
import com.vc.staging.dto.CreditScoreDTO;
import com.vc.staging.dto.CreditScoreDataDTO;
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
import com.vc.staging.dto.UserAddressDTO;
import com.vc.staging.dto.UserDTO;
import com.vc.staging.dto.UserDataDTO;
import com.vc.staging.dto.UserDetailForLenderDTO;
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
import com.vc.staging.model.NavigationPageDO;
import com.vc.staging.model.OptVerifyDO;
import com.vc.staging.model.RefAgeDO;
import com.vc.staging.model.RefApplicantRelationshipDO;
import com.vc.staging.model.RefAssetTypeDO;
import com.vc.staging.model.RefCreditScoreDO;
import com.vc.staging.model.RefEducationDO;
import com.vc.staging.model.RefEducationDetailDO;
import com.vc.staging.model.RefEducationInstitutionDO;
import com.vc.staging.model.RefEmployerTypeDO;
import com.vc.staging.model.RefGenderDO;
import com.vc.staging.model.RefIncomeTypeDO;
import com.vc.staging.model.RefLenderLpiStatusDO;
import com.vc.staging.model.RefLoanLenderDO;
import com.vc.staging.model.RefLoanTimeFrameDO;
import com.vc.staging.model.RefLoanTypeDO;
import com.vc.staging.model.RefMaritalStatusDO;
import com.vc.staging.model.RefOccupationCategoryDO;
import com.vc.staging.model.RefOccupationTypeDO;
import com.vc.staging.model.RefProductTypeDO;
import com.vc.staging.model.RefResidenceCategoryDO;
import com.vc.staging.model.RefSalaryDeductionDO;
import com.vc.staging.model.RefSubscriptionDO;
import com.vc.staging.model.RefUserTypeDO;
import com.vc.staging.model.RefZipDO;
import com.vc.staging.model.ReffrequencyDO;
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
import com.vc.staging.model.UserSalaryDeductionDO;
import com.vc.staging.model.UserlenderSanctionDetailsDO;
import com.vc.staging.util.UtilityMethods;
import com.vc.staging.util.ValuCirclesConstants;

/**
 * 
 * *
 * 
 * @author Thanga
 * 
 */
@Repository
public class UserDataFetchDAOImpl extends AbstractHibernateDAOImpl implements UserDataFetchDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	BuilderDataFetchDAO builderDataFetchDAO;

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#validateemail(java.lang.String)
	 */
	@Override
	public boolean validateemail(String userEmail) {
		boolean newUser = true;
		Object obj = getCurrentSession().getNamedQuery("UserLoginDO.getnewuserotnot")
				.setParameter("userEmail", userEmail).uniqueResult();
		if (obj != null) {
			newUser = false;
		}
		return newUser;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.AbstractHibernateDAOImpl#validatesubscriptionemail(java.lang.String)
	 */
	@Override
	public boolean validatesubscriptionemail(String userEmail) {
		boolean newUser = true;
		Object obj = getCurrentSession().getNamedQuery("SubscriptionDetailDO.getnewuserotnot")
				.setParameter("userEmail", userEmail).uniqueResult();
		if (obj != null) {
			newUser = false;
		}
		return newUser;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getSalt(java.lang.String)
	 */
	@Override
	public String getSalt(String emailId) {
		String salt = null;
		try {
			Object obj = getCurrentSession().getNamedQuery("UserLoginDO.getSalt").setParameter("emailId", emailId)
					.uniqueResult();
			if (obj != null) {
				salt = obj + "";
			} else {
				salt = "Fail";
			}
		} catch (Exception e) {
			salt = "Fail";
		}

		return salt;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#selectlogincount(java.lang.String)
	 */
	public int selectlogincount(String emailId) {
		int loginattempts = 0;
		Object obj3 = getCurrentSession().getNamedQuery("UserLoginDO.selectloginattempt")
				.setParameter("emailId", emailId).uniqueResult();
		if (obj3 != null) {
			loginattempts = (int) obj3;
		}
		return loginattempts;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#selectuserEmail(int)
	 */
	public String selectuserEmail(int userId) {
		String mail = null;
		String query = "SELECT ue.userEmail FROM user_login ue where userId='" + userId + "'";
		if (query != null) {
			mail = query;
		}
		return mail;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#emailverify(java.lang.String)
	 */
	public int emailverify(String emailId) {
		int email_verify = 0;
		Object obj3 = getCurrentSession().getNamedQuery("UserLoginDO.selectemailverify")
				.setParameter("emailId", emailId).uniqueResult();
		if (obj3 != null) {
			email_verify = (int) obj3;
		} else {
			email_verify = 2;
		}
		return email_verify;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#userLoginDetails(java.lang.String, java.lang.String)
	 */
	@Override
	public UserLoginDO userLoginDetails(String emailId, String pass) {
		UserLoginDO user = null;
		Object object = getCurrentSession().getNamedQuery("UserLoginDO.userLogin").setParameter("emailId", emailId)
				.setParameter("pass", pass).uniqueResult();
		if (object != null) {
			user = (UserLoginDO) object;
			user.setLoginattempts(0);
			getCurrentSession().update(user);

		} else {

			Object obj2 = getCurrentSession().getNamedQuery("UserLoginDO.selectemailverify")
					.setParameter("emailId", emailId).uniqueResult();
			if (obj2 == null) {
				user = null;
			} else {
				int email_verify = (int) obj2;
				if (email_verify == 0) {
					Object obj1 = getCurrentSession().getNamedQuery("UserLoginDO.updateloginattempt")
							.setParameter("emailId", emailId).setParameter("loginattempts", 0).executeUpdate();
				} else {
					Object obj = getCurrentSession().getNamedQuery("UserLoginDO.selectloginattempt")
							.setParameter("emailId", emailId).uniqueResult();
					if (obj != null) {
						int loginattempts = (int) obj;
						/* 11/07 :Geeth -Account lockout fix changes
						if (loginattempts == 0 || loginattempts <= 3) {
						Object obj1 = getCurrentSession().getNamedQuery("UserLoginDO.updateloginattempt")
						.setParameter("emailId", emailId).setParameter("loginattempts", loginattempts + 1)
						.executeUpdate();
						} else if (loginattempts > 3) {
						Object obj1 = getCurrentSession().getNamedQuery("UserLoginDO.updateactivestatus")
						.setParameter("emailId", emailId).executeUpdate();
						}
						*/	

						if ( loginattempts <= 2) {
							loginattempts= loginattempts + 1;
							Object obj1 = getCurrentSession().getNamedQuery("UserLoginDO.updateloginattempt")
									.setParameter("emailId", emailId).setParameter("loginattempts", loginattempts)
									.executeUpdate();
						} 

						if (loginattempts ==3 ) {
							Object obj1 = getCurrentSession().getNamedQuery("UserLoginDO.updateactivestatus")
									.setParameter("emailId", emailId).executeUpdate();
						}


					}
				}
			}
		}
		return user;
	}

	
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfGender()
	 */
	@Override
	public List<GenderDTO> getListOfGender() {
		List<RefGenderDO> list = null;
		if (ValuCirclesConstants.genderCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.genderCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefGenderDO.findAll").list();
		}
		List<GenderDTO> finalList = new ArrayList<GenderDTO>();
		if (list != null) {
			for (RefGenderDO do1 : list) {
				GenderDTO dto = new GenderDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setGender(do1.getGender());
				dto.setGenderId(do1.getGenderId());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				// dto.setWeightPct(do1.getWeightPct());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOffrequency()
	 */
	@Override
	public List<ReffrequencyDTO> getListOffrequency() {
		List<ReffrequencyDO> list = null;
		if (ValuCirclesConstants.frequencyCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.frequencyCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("ReffrequencyDO.findAll").list();
		}
		List<ReffrequencyDTO> finalList = new ArrayList<ReffrequencyDTO>();
		if (list != null) {
			for (ReffrequencyDO do1 : list) {
				ReffrequencyDTO dto = new ReffrequencyDTO();
				dto.setFid(do1.getFid());
				dto.setFname(do1.getFname());
				dto.setActiveStatus(do1.getActiveStatus());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfMaritalStatus()
	 */
	@Override
	public List<MaritalStatusDTO> getListOfMaritalStatus() {
		List<RefMaritalStatusDO> list = null;
		if (ValuCirclesConstants.maritalstatusCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.maritalstatusCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefMaritalStatusDO.findAll").list();
		}
		List<MaritalStatusDTO> finalList = new ArrayList<MaritalStatusDTO>();
		if (list != null) {
			for (RefMaritalStatusDO do1 : list) {
				MaritalStatusDTO dto = new MaritalStatusDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setMaritalStatus(do1.getMaritalStatus());
				dto.setMaritalStatusId(do1.getMaritalStatusId());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				// dto.setWeightPct(do1.getWeightPct());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfOccupationType()
	 */
	@Override
	public List<OccupationTypeDTO> getListOfOccupationType() {
		List<RefOccupationTypeDO> list = null;
		if (ValuCirclesConstants.occupationCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.occupationCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefOccupationTypeDO.findAll").list();
		}
		List<OccupationTypeDTO> finalList = new ArrayList<OccupationTypeDTO>();
		if (list != null) {
			for (RefOccupationTypeDO do1 : list) {
				OccupationTypeDTO dto = new OccupationTypeDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setOccupationType(do1.getOccupationType());
				dto.setOccupationTypeId(do1.getOccupationTypeId());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setLenderId(do1.getLender().getLenderId());
				dto.setReferenceCategoryId(do1.getReferenceCategory().getReferenceCategoryId());
				dto.setScore(do1.getScore());
				// dto.setWeightPct(do1.getWeightPct());
				finalList.add(dto);
			}
		}
		return finalList;
	}
	
	
	

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfOccupationCategory(int)
	 */
	@Override
	public List<OccupationCategoryDTO> getListOfOccupationCategory(int occupationtypeid) {
		List<RefOccupationCategoryDO> list = getCurrentSession().getNamedQuery("RefOccupationCategoryDO.getdetail")
				.setParameter("occupationtypeid", occupationtypeid).list();
		List<OccupationCategoryDTO> finalList = new ArrayList<OccupationCategoryDTO>();
		if (list != null) {
			for (RefOccupationCategoryDO do1 : list) {
				OccupationCategoryDTO dto = new OccupationCategoryDTO();
				dto.setCategoryid(do1.getCategoryid());
				dto.setCategoryname(do1.getCategoryname());
				dto.setCategorytext(do1.getCategorytext());
				dto.setOccupationtypeid(do1.getOccupationtypeid());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfEducation()
	 */
	@Override
	public List<EducationDTO> getListOfEducation() {
		List<RefEducationDO> list = null;
		if (ValuCirclesConstants.educationCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.educationCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefEducationDO.findAll").list();
		}
		List<EducationDTO> finalList = new ArrayList<EducationDTO>();
		if (list != null) {
			for (RefEducationDO do1 : list) {
				EducationDTO dto = new EducationDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setEducation(do1.getEducation());
				dto.setEducationId(do1.getEducationId());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				// dto.setWeightPct(do1.getWeightPct());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getEmployerDetails(java.lang.String)
	 */
	@Override
	public EmployerDO getEmployerDetails(String employerName) {
		EmployerDO employerDO = null;
		Object object = getCurrentSession().getNamedQuery("EmployerDO.getEmployerDetailsByEmployerName")
				.setParameter("employerName", employerName).uniqueResult();
		if (object != null) {
			employerDO = (EmployerDO) object;
		}
		return employerDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getclassificationid(java.lang.String)
	 */
	@Override
	public String getclassificationid(String employerName) {
		Object employertypeDO = null;
		if (employerName == "" || employerName.isEmpty() == true) {
			employertypeDO = "";
		}

		else {
			Object object = getCurrentSession().getNamedQuery("RefEmployerTypeDO.getclassificationId")
					.setParameter("employerTypeDesc", employerName).uniqueResult();
			if (object != null) {
				employertypeDO = object;
			}
		}

		return employertypeDO.toString();
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfEmployer()
	 */
	@Override
	public List<EmployerDTO> getListOfEmployer() {
		List<EmployerDO> list = null;
		if (ValuCirclesConstants.employerCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.employerCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("EmployerDO.findAll").list();
		}
		List<EmployerDTO> finalList = new ArrayList<EmployerDTO>();
		if (list != null) {
			for (EmployerDO do1 : list) {
				EmployerDTO dto = new EmployerDTO();

				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setEmployerName(do1.getEmployerName());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setScore(do1.getScore());
				dto.setReferenceCategoryId(do1.getReferenceCategory().getReferenceCategoryId());
				dto.setEmployerId(do1.getEmployerId());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserInfoByUserId(int)
	 */
	@Override
	public UserDataDTO getUserInfoByUserId(int userId) {
		UserDataDTO dataDTO = new UserDataDTO();
		UserInfoDO userInfoDO = ValuCirclesConstants.userInfoCache.get(userId);
		if (userInfoDO == null) {
			userInfoDO = (UserInfoDO) getCurrentSession().getNamedQuery("UserInfoDO.getUserInfoByUserId")
					.setParameter("userId", userId).uniqueResult();
		}
		if (userInfoDO != null) {
			dataDTO.setActiveStatus(userInfoDO.getActiveStatus());			
			dataDTO.setAddress1(userInfoDO.getUserAddress().getAddress1());
			dataDTO.setAddress2(userInfoDO.getUserAddress().getAddress2());			
			dataDTO.setAddress3(userInfoDO.getUserAddress().getAddress3());														   
			RefZipDO refZipDO = (RefZipDO) findById(RefZipDO.class, userInfoDO.getUserAddress().getZipid());
			if (refZipDO != null) {
				dataDTO.setCity(refZipDO.getCity());
				dataDTO.setPostalZip(refZipDO.getZip());
				dataDTO.setState(refZipDO.getState());
				dataDTO.setZipId(refZipDO.getZipId());
			}
			dataDTO.setCountry(userInfoDO.getUserAddress().getCountry());
			dataDTO.setCreatedBy(userInfoDO.getCreatedBy());
			dataDTO.setCreatedOn(userInfoDO.getCreatedOn());
			dataDTO.setDateofBirth(userInfoDO.getDateOfBirth());
			dataDTO.setFirstName(userInfoDO.getFirstName());
			dataDTO.setLastName(userInfoDO.getLastName());
			dataDTO.setUpdatedBy(userInfoDO.getUpdatedBy());
			dataDTO.setUserInfoId(userInfoDO.getUserInfoId());
			dataDTO.setMaritalStatus(userInfoDO.getRefMaritalStatus().getMaritalStatus());
			dataDTO.setMaritalStatusId(userInfoDO.getRefMaritalStatus().getMaritalStatusId());
			dataDTO.setGenderId(userInfoDO.getRefGender().getGenderId());
			dataDTO.setRefAgeId(userInfoDO.getRefAge().getRefAgeId());
			dataDTO.setEducationId(userInfoDO.getRefEducation().getEducationId());
			dataDTO.setGender(userInfoDO.getRefGender().getGender());
			dataDTO.setEducation(userInfoDO.getRefEducation().getEducation());
			dataDTO.setRefResidenceCategoryId(userInfoDO.getRefResidenceCategory().getRefResidenceCategoryId());
			dataDTO.setResidenceCategory(userInfoDO.getRefResidenceCategory().getResidenceCategory());
			dataDTO.setEducationDetailsId(userInfoDO.getEducationDetailsId());
			dataDTO.setEducationInstitutionId(userInfoDO.getEducationInstitutionId());
			dataDTO.setCoApplicantId(userInfoDO.getCoApplicantId());
			dataDTO.setPropertyIdentifierId(userInfoDO.getPropertyIdentifierId());
			dataDTO.setCompareaddrId(userInfoDO.getCompareaddrId());
			dataDTO.setHelpWithProperty(userInfoDO.gethelpWithProperty());
			RefEducationDetailDO detailDO = (RefEducationDetailDO) findById(RefEducationDetailDO.class,
					userInfoDO.getEducationDetailsId());
			if (detailDO != null) {
				dataDTO.setEducationDetail(detailDO.getEducationDetails());
			}
			RefEducationInstitutionDO institutionDO = (RefEducationInstitutionDO) findById(
					RefEducationInstitutionDO.class, userInfoDO.getEducationInstitutionId());
			if (institutionDO != null) {
				dataDTO.setEducationInstitution(institutionDO.getInstitution());
			}
			int dob = UtilityMethods.getAgeUsingDateOfBirth(userInfoDO.getDateOfBirth());
			dataDTO.setAge(dob);
		}
		return dataDTO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserloanByUserId(int)
	 */
	@Override
	public List<UserLoanDTO> getUserloanByUserId(int userId) {
		List<UserLoanDO> list = getCurrentSession().getNamedQuery("UserLoanDO.getUserLoanByUserId")
				.setParameter("userId", userId).list();
		
		List<UserLoanDTO> finalList = new ArrayList<>();
		if (list != null) {
			for (UserLoanDO loanDO : list) {
				UserLoanDTO loanDTO = new UserLoanDTO();
				loanDTO.setActiveStatus(loanDO.getActiveStatus());
				loanDTO.setCreatedBy(loanDO.getCreatedBy());
				loanDTO.setCreatedOn(loanDO.getCreatedOn());
				loanDTO.setManualEntry(loanDO.getManualEntry());
				loanDTO.setUpdatedBy(loanDO.getUpdatedBy());
				loanDTO.setUpdatedOn(loanDO.getUpdatedOn());
				loanDTO.setUserId(loanDO.getUserLogin().getUserId());
				loanDTO.setUserLenderName(loanDO.getUserLenderName());
				RefLoanLenderDO loanLenderDO = (RefLoanLenderDO) findById(RefLoanLenderDO.class,
						loanDO.getUserLenderName());
				if (loanLenderDO != null) {
					loanDTO.setLoanLender(loanLenderDO.getLoanLenderName());
				}
				loanDTO.setUserLoanOutstandingPrincipal(loanDO.getUserLoanOutstandingPrincipal());
				loanDTO.setUserLoanPaymentAmount(loanDO.getUserLoanPaymentAmount());
				loanDTO.setUserLoanRemainingTenure(loanDO.getUserLoanRemainingTenure());
				loanDTO.setUserLoansId(loanDO.getUserLoansId());
				loanDTO.setLoanTypeId(loanDO.getRefLoanType().getLoanTypeId());
				loanDTO.setLoanTypeDesc(loanDO.getRefLoanType().getLoanTypeDesc());
				finalList.add(loanDTO);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserEmploymentByuserId(int)
	 */
	@Override
	public UserEmploymentDTO getUserEmploymentByuserId(int userId) {
		UserEmploymentDTO employmentDTO = new UserEmploymentDTO();
		UserEmploymentDO employmentDO = ValuCirclesConstants.userEmploymentCache.get(userId);
		if (employmentDO == null) {
			employmentDO = (UserEmploymentDO) getCurrentSession()
					.getNamedQuery("UserEmploymentDO.getUseremploymentByUserId").setParameter("userId", userId)
					.uniqueResult();
			if (employmentDO != null) {
				ValuCirclesConstants.userEmploymentCache.put(userId, employmentDO);
			}
		}
		if (employmentDO != null) {
			employmentDTO.setActiveStatus(employmentDO.getActiveStatus());
			employmentDTO.setCreatedBy(employmentDO.getCreatedBy());
			employmentDTO.setCreatedOn(employmentDO.getCreatedOn());
			employmentDTO.setEmployerId(employmentDO.getEmployer().getEmployerId());
			employmentDTO.setOccupationTypeId(employmentDO.getOccupationType().getOccupationTypeId());
			employmentDTO.setUpdatedBy(employmentDO.getUpdatedBy());
			employmentDTO.setUpdatedOn(employmentDO.getUpdatedOn());
			employmentDTO.setUserEmploymentId(employmentDO.getUserEmploymentId());
			employmentDTO.setPensionEligibility(employmentDO.getPensionEligibility());
			employmentDTO.setYearsofServiceinIndustry(employmentDO.getYearsOfServiceInIndustry());
			employmentDTO.setYearsofServicewithEmployer(employmentDO.getYearsOfServicewithEmployer());
			employmentDTO.setOccupationDesc(employmentDO.getOccupationType().getOccupationType());
			employmentDTO.setEmployerName(employmentDO.getEmployerName());
			employmentDTO.setRefCreditScoreId(employmentDO.getRefCreditScore().getRefCreditScoreId());			
			employmentDTO.setStartRangeCreditScore(employmentDO.getRefCreditScore().getStartRange());
			employmentDTO.setEndRangeCreditScore(employmentDO.getRefCreditScore().getEndRange());
			employmentDTO.setCin(employmentDO.getCin());	
			employmentDTO.setOccupationcategoryid(employmentDO.getOccupationcategoryid());
			employmentDTO.setCategorydetail(employmentDO.getCategorydetail());

			/*
			 * 16-08-2019: Geetha- Code changes for adding new ITfiled attribute on Financial page
			 */
			employmentDTO.setITfiled(employmentDO.getITfiled());
			//
			employmentDTO.setemployeeCount(employmentDO.getemployeeCount());
			employmentDTO.setOwnershipShare(employmentDO.getOwnershipShare());
			employmentDTO.setOfficeType(employmentDO.getOfficeType());
			employmentDTO.setannualProfit(employmentDO.getannualProfit());
			employmentDTO.setannualSales(employmentDO.getannualSales());
			employmentDTO.setBusinessConstitution(employmentDO.getBusinessConstitution());
			
			employmentDTO.setprimaryBank(employmentDO.getprimaryBank());
			employmentDTO.setannualProfitPY(employmentDO.getannualProfit());
			employmentDTO.setAnnualSalesPY(employmentDO.getannualSalesPY());
		}
		return employmentDTO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfAge()
	 */
	@Override
	public List<AgeDTO> getListOfAge() {
		List<RefAgeDO> list = null;
		if (ValuCirclesConstants.ageCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.ageCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefAgeDO.findAll").list();
		}
		List<AgeDTO> finalList = new ArrayList<AgeDTO>();
		if (list != null) {
			for (RefAgeDO ageDO : list) {
				AgeDTO dto = new AgeDTO();
				dto.setActiveStatus(ageDO.getActiveStatus());
				dto.setCreatedOn(ageDO.getCreatedOn());
				dto.setCreatedBy(ageDO.getUpdatedBy());
				dto.setEndAge(ageDO.getEndAge());
				dto.setStartAge(ageDO.getStartAge());
				dto.setUpdatedBy(ageDO.getUpdatedBy());
				dto.setUpdatedOn(ageDO.getUpdatedOn());
				dto.setWeightPct(ageDO.getWeightPct());
				dto.setRefAgeId(ageDO.getRefAgeId());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfCreditScore()
	 */
	@Override
	public List<CreditScoreDTO> getListOfCreditScore() {
		List<RefCreditScoreDO> list = null;
		if (ValuCirclesConstants.creditScoreCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.creditScoreCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefCreditScoreDO.findAll").list();
		}
		List<CreditScoreDTO> finalList = new ArrayList<CreditScoreDTO>();
		if (list != null) {
			for (RefCreditScoreDO do1 : list) {
				CreditScoreDTO dto = new CreditScoreDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setEndRange(do1.getEndRange());
				dto.setStartRange(do1.getStartRange());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setUpdatedBy(do1.getUpdatedBy());
				// dto.setWeightPct(do1.getWeightPct());
				dto.setRefCreditScoreId(do1.getRefCreditScoreId());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfLoanType()
	 */
	@Override
	public List<LoanTypeDTO> getListOfLoanType() {
		List<RefLoanTypeDO> list = null;
		if (ValuCirclesConstants.loanTypeCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.loanTypeCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefLoanTypeDO.findAll").list();
		}
		List<LoanTypeDTO> finalList = new ArrayList<LoanTypeDTO>();
		if (list != null) {
			for (RefLoanTypeDO do1 : list) {
				LoanTypeDTO dto = new LoanTypeDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setLoanTypeDesc(do1.getLoanTypeDesc());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setLoanTypeId(do1.getLoanTypeId());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getEmployerName(int)
	 */
	@Override
	public String getEmployerName(int userId) {
		try {
			String query = "SELECT ue.employerName FROM valucircles.user_employment uem,valucircles.user_login ul,valucircles.employer ue where uem.userId=ul.userId and uem.employerId=ue.employerId and ul.userId= "
					+ userId + " ";			
			return jdbcTemplate.queryForObject(query, String.class);
		} catch (EmptyResultDataAccessException e) {
			return "";
		}

	}
	
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getEmploymentDetails(int)
	 */
	@Override
	public UserEmploymentDO getEmploymentDetails(int userId) {
		UserEmploymentDO employmentDO = null;
		Object object = getCurrentSession().getNamedQuery("UserEmploymentDO.getEmploymentDetails")
				.setParameter("userId", userId).uniqueResult();
		if (object != null) {
			employmentDO = (UserEmploymentDO) object;
		}
		return employmentDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getLoanDetails(int)
	 */
	@Override
	public List<UserLoanDO> getLoanDetails(int userId) {
		return getCurrentSession().getNamedQuery("UserLoanDO.getLoanDetails").setParameter("userId", userId).list();
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getIndustryDetails(java.lang.String)
	 */
	@Override
	public IndustrySectorDO getIndustryDetails(String industrySegmentName) {
		IndustrySectorDO industrySectorDO = null;
		Object object = getCurrentSession().getNamedQuery("IndustrySectorDO.getIndustry")
				.setParameter("industrySegmentName", industrySegmentName).uniqueResult();
		if (object != null) {
			industrySectorDO = (IndustrySectorDO) object;
		}
		return industrySectorDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfIndustrySector()
	 */
	@Override
	public List<IndustrySectorDTO> getListOfIndustrySector() {
		List<IndustrySectorDO> list = null;
		if (ValuCirclesConstants.sectorCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.sectorCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("IndustrySectorDO.findAll").list();
		}
		List<IndustrySectorDTO> finalList = new ArrayList<IndustrySectorDTO>();
		if (list != null) {
			for (IndustrySectorDO do1 : list) {
				IndustrySectorDTO dto = new IndustrySectorDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setIndustrySegmentId(do1.getIndustrySegmentId());
				dto.setIndustrySegmentName(do1.getIndustrySegmentName());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListofIncome(int)
	 */
	@Override
	public List<UserIncomeDO> getListofIncome(int userId) {
		return getCurrentSession().getNamedQuery("UserIncomeDO.getIncomeDetails").setParameter("userId", userId).list();
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserIncomelistById(int)
	 */
	@Override
	public List<UserIncomeDTO> getUserIncomelistById(int userId) {
		List<UserIncomeDO> list = null;
		if (ValuCirclesConstants.userIncomeCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.userIncomeCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("UserIncomeDO.getUserIncomeByUserId")
					.setParameter("userId", userId).list();
		}
		List<UserIncomeDTO> finalList = new ArrayList<UserIncomeDTO>();
		if (list != null) {
			for (UserIncomeDO do1 : list) {
				UserIncomeDTO dto = new UserIncomeDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setManualEntry(do1.getManualEntry());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setUserEmpoymentInfo(do1.getUserEmploymentInfo());
				dto.setUserGrossIncome(do1.getUserGrossIncome());
				dto.setUserId(do1.getUserLogin().getUserId());
				dto.setFrequencyType(do1.getFrequencyType());
				dto.setUserIncomeId(do1.getUserIncomeId());
				dto.setUserNetIncome(do1.getUserNetIncome());
				dto.setRefIncomeTypeId(do1.getRefIncomeType().getRefIncomeTypeId());
				dto.setIncomeTypedesc(do1.getRefIncomeType().getIncomeTypedesc());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserIncome(int)
	 */
	@Override
	public boolean updateUserIncome(int userId) {
		int isUpdated = getCurrentSession().getNamedQuery("UserIncomeDO.updateUserIncome")
				.setParameter("userId", userId).executeUpdate();
		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserLoan(int)
	 */
	@Override
	public boolean updateUserLoan(int userId) {
		int isUpdated = getCurrentSession().getNamedQuery("UserLoanDO.updateUserLoan").setParameter("userId", userId)
				.executeUpdate();
		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfIncomeType()
	 */
	@Override
	public List<IncomeTypeDTO> getListOfIncomeType() {
		List<RefIncomeTypeDO> list = getCurrentSession().getNamedQuery("RefIncomeTypeDO.findAll").list();		
		List<IncomeTypeDTO> finalList = new ArrayList<>();
		if (list != null) {
			for (RefIncomeTypeDO do1 : list) {
				IncomeTypeDTO dto = new IncomeTypeDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setIncomeTypedesc(do1.getIncomeTypedesc());
				dto.setRefIncomeTypeId(do1.getRefIncomeTypeId());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setOccupationTypeId(do1.getOccupationTypeId());
				dto.setOccupation_CategoryId(do1.getOccupation_CategoryId());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserListData()
	 */
	@Override
	public List<UserListDTO> getUserListData() {
		List<UserInfoDO> list = getCurrentSession().getNamedQuery("UserInfoDO.findAllUsers").list();
		List<UserListDTO> userList = new ArrayList<UserListDTO>();
		for (UserInfoDO infoDO : list) {
			UserListDTO dto = new UserListDTO();
			dto.setActiveStatus(infoDO.getActiveStatus());
			dto.setEducation(infoDO.getRefEducation().getEducation());
			dto.setGender(infoDO.getRefGender().getGender());
			dto.setMaritalStatus(infoDO.getRefMaritalStatus().getMaritalStatus());
			dto.setFirstName(infoDO.getFirstName());
			dto.setUserInfoId(infoDO.getUserInfoId());
			dto.setLastName(infoDO.getLastName());
			dto.setCoApplicantId(infoDO.getCoApplicantId());
			dto.setPropertyIdentifierId(infoDO.getPropertyIdentifierId());
			dto.setCompareaddrId(infoDO.getCompareaddrId());
			dto.setUserId(infoDO.getUserLogin().getUserId());			
			List<UserIncomeDTO> incomeDTO = new ArrayList<UserIncomeDTO>();
			for (UserIncomeDO incomeDO : infoDO.getUserLogin().getUserIncomes()) {
				UserIncomeDTO userIncomeDTO = new UserIncomeDTO();
				userIncomeDTO.setActiveStatus(incomeDO.getActiveStatus());
				userIncomeDTO.setIncomeTypedesc(incomeDO.getRefIncomeType().getIncomeTypedesc());
				userIncomeDTO.setUserGrossIncome(incomeDO.getUserGrossIncome());
				userIncomeDTO.setUserId(incomeDO.getUserLogin().getUserId());
				userIncomeDTO.setUserNetIncome(incomeDO.getUserNetIncome());
				userIncomeDTO.setUserIncomeId(incomeDO.getUserIncomeId());
				incomeDTO.add(userIncomeDTO);
			}
			dto.setUserIncomeDTO(incomeDTO);
			List<UserLoanDTO> loanDTO = new ArrayList<UserLoanDTO>();
			for (UserLoanDO loanDO : infoDO.getUserLogin().getUserLoans()) {
				UserLoanDTO userLoanDTO = new UserLoanDTO();
				userLoanDTO.setActiveStatus(loanDO.getActiveStatus());
				userLoanDTO.setLoanTypeDesc(loanDO.getRefLoanType().getLoanTypeDesc());
				userLoanDTO.setUserId(loanDO.getUserLogin().getUserId());
				userLoanDTO.setUserLenderName(loanDO.getUserLenderName());
				userLoanDTO.setUserLoanOutstandingPrincipal(loanDO.getUserLoanOutstandingPrincipal());
				userLoanDTO.setUserLoanPaymentAmount(loanDO.getUserLoanPaymentAmount());
				userLoanDTO.setUserLoanRemainingTenure(loanDO.getUserLoanRemainingTenure());
				userLoanDTO.setUserLoansId(loanDO.getUserLoansId());
				RefLoanLenderDO loanLenderDO = (RefLoanLenderDO) findById(RefLoanLenderDO.class,
						loanDO.getUserLenderName());
				if (loanLenderDO != null) {
					userLoanDTO.setLoanLender(loanLenderDO.getLoanLenderName());
				}
				loanDTO.add(userLoanDTO);
			}
			dto.setUserLoanDTO(loanDTO);
			List<UserEmploymentDTO> employmentDTO = new ArrayList<UserEmploymentDTO>();
			for (UserEmploymentDO employmentDO : infoDO.getUserLogin().getUserEmployments()) {
				UserEmploymentDTO userEmploymentDTO = new UserEmploymentDTO();
				userEmploymentDTO.setOccupationDesc(employmentDO.getOccupationType().getOccupationType());
				employmentDTO.add(userEmploymentDTO);
			}
			dto.setUserEmploymentDTO(employmentDTO);
			userList.add(dto);
		}
		return userList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfResidence()
	 */
	@Override
	public List<ResidenceDTO> getListOfResidence() {
		List<RefResidenceCategoryDO> list = getCurrentSession().getNamedQuery("RefResidenceCategoryDO.findAll").list();
		List<ResidenceDTO> finalList = new ArrayList<>();
		if (list != null) {
			for (RefResidenceCategoryDO do1 : list) {
				ResidenceDTO dto = new ResidenceDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setReferenceCategoryId(do1.getReferenceCategoryId());
				dto.setRefResidenceCategoryId(do1.getRefResidenceCategoryId());
				dto.setResidenceCategory(do1.getResidenceCategory());
				dto.setScore(do1.getScore());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getLpiFromSP(int)
	 */
	@Override
	public Map<String, Object> getLpiFromSP(final int userId) {

		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter(Types.VARCHAR));
		paramList.add(new SqlOutParameter("myminscore", Types.VARCHAR));
		paramList.add(new SqlOutParameter("mymaxscore", Types.VARCHAR));

		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {

				CallableStatement callableStatement = connection.prepareCall("{call IncomeScore(?, ?, ?)}");
				callableStatement.setLong(1, userId);
				callableStatement.registerOutParameter(2, Types.VARCHAR);
				callableStatement.registerOutParameter(3, Types.VARCHAR);
				return callableStatement;
			}
		}, paramList);
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getEducationDetailByEducationId(int)
	 */
	@Override
	public List<EducationDetailDTO> getEducationDetailByEducationId(int educationId) {
		List<RefEducationDetailDO> list = getCurrentSession()
				.getNamedQuery("RefEducationDetailDO.getEducationDetailByEducationId")
				.setParameter("educationId", educationId).list();
		List<EducationDetailDTO> finalList = new ArrayList<>();
		if (list != null) {
			for (RefEducationDetailDO do1 : list) {
				EducationDetailDTO dto = new EducationDetailDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setEducationDetails(do1.getEducationDetails());
				dto.setEducationDetailsId(do1.getEducationDetailsId());
				dto.setEducationId(do1.getEducationid());
				dto.setReferenceCategoryId(do1.getReferenceCategoryId());
				dto.setScore(do1.getScore());
				dto.setWeightPct(do1.getWeightPct());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getEducationInstitutionByEducationDetailId(int)
	 */
	@Override
	public List<EducationInstitutionDTO> getEducationInstitutionByEducationDetailId(int educationDetailId) {
		List<RefEducationInstitutionDO> list = getCurrentSession()
				.getNamedQuery("RefEducationInstitutionDO.getEducationInstitutionByEdcationDetailId")
				.setParameter("educationDetailId", educationDetailId).list();
		List<EducationInstitutionDTO> finalList = new ArrayList<>();
		if (list != null) {
			for (RefEducationInstitutionDO do1 : list) {
				EducationInstitutionDTO dto = new EducationInstitutionDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setEducationDetailId(do1.getRefEducationDetail().getEducationDetailsId());
				dto.setEducationInstitutionId(do1.getEducationInstitutionId());
				dto.setInstitution(do1.getInstitution());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setWeightPct(do1.getWeightPct());
				dto.setReferenceCategoryId(do1.getReferenceCategory().getReferenceCategoryId());
				dto.setScore(do1.getScore());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfEducationDetails()
	 */
	@Override
	public List<EducationDetailDTO> getListOfEducationDetails() {
		List<RefEducationDetailDO> list = getCurrentSession().getNamedQuery("RefEducationDetailDO.findAll").list();
		List<EducationDetailDTO> finalList = new ArrayList<>();
		if (list != null) {
			for (RefEducationDetailDO do1 : list) {
				EducationDetailDTO dto = new EducationDetailDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setEducationDetails(do1.getEducationDetails());
				dto.setEducationDetailsId(do1.getEducationDetailsId());
				dto.setEducationId(do1.getEducationid());
				dto.setReferenceCategoryId(do1.getReferenceCategoryId());
				dto.setScore(do1.getScore());
				dto.setWeightPct(do1.getWeightPct());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfEducationInstitution()
	 */
	@Override
	public List<EducationInstitutionDTO> getListOfEducationInstitution() {
		List<RefEducationInstitutionDO> list = getCurrentSession().getNamedQuery("RefEducationInstitutionDO.findAll")
				.list();
		List<EducationInstitutionDTO> finalList = new ArrayList<>();
		if (list != null) {
			for (RefEducationInstitutionDO do1 : list) {
				EducationInstitutionDTO dto = new EducationInstitutionDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setEducationDetailId(do1.getRefEducationDetail().getEducationDetailsId());
				dto.setEducationInstitutionId(do1.getEducationInstitutionId());
				dto.setInstitution(do1.getInstitution());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setWeightPct(do1.getWeightPct());
				dto.setReferenceCategoryId(do1.getReferenceCategory().getReferenceCategoryId());
				dto.setScore(do1.getScore());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getuserDetails(java.lang.String)
	 */
	@Override
	public UserLoginDO getuserDetails(String emailId) {
		UserLoginDO userLoginDO = null;
		Object object = getCurrentSession().getNamedQuery("UserLoginDO.getUserByEmailId")
				.setParameter("emailId", emailId).uniqueResult();
		if (object != null) {
			userLoginDO = (UserLoginDO) object;
		}
		return userLoginDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#resetPassword(java.lang.String, int, java.lang.String, java.lang.String)
	 */
	@Override
	public String resetPassword(String emailId, int userId, String newpwd, String sessionId) {
		JSONObject jsonObject = new JSONObject();
		
		UserLoginDO userLoginDO = (UserLoginDO) findById(UserLoginDO.class, userId);
		String newpass = UtilityMethods.passwordEncrypt(newpwd, userLoginDO.getSalt());
		Date ss = new Date();
		ss.setTime(Long.parseLong(sessionId));
		
		long diffMinutes=0; long diffHours=0;
		System.out.println("Session id from email"+sessionId);
		System.out.println("output of ss is"+ss);
		
		long resetdate = new Date().getTime();
		long maildate = Long.parseLong(sessionId);
		long diff =resetdate-maildate;
		diffMinutes = diff / (60 * 1000) ;
		diffHours =diffMinutes/60;
		System.out.println("difference of time from mail date"+diffMinutes);
		
		try {
			if(diffHours > 24) {
				jsonObject.put(ValuCirclesConstants.RESULT, "Your password link has exoired. Please resubmit password request");
				
			}
			else if (diffHours <=24) {
			if (userLoginDO != null) {
				userLoginDO.setUserCreds(newpass);
				userLoginDO.setActiveStatus(1);
				userLoginDO.setLoginattempts(0);
				try {
					jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.FAIL);

					e.printStackTrace();
				}
			}
		}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject.toString();
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#changePassword(int, java.lang.String, java.lang.String)
	 */
	@Override
	public String changePassword(int userId, String oldpwd, String newpwd) {
		JSONObject jsonObject = new JSONObject();
		UserLoginDO userLoginDO = (UserLoginDO) findById(UserLoginDO.class, userId);
		String pass = UtilityMethods.passwordEncrypt(oldpwd, userLoginDO.getSalt());
		String newpass = UtilityMethods.passwordEncrypt(newpwd, userLoginDO.getSalt());
		if (userLoginDO != null) {
			try {
				if (pass.equalsIgnoreCase(userLoginDO.getUserCreds())) {
					userLoginDO.setUserCreds(newpass);
					jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.SUCCESS);
				} else {
					jsonObject.put(ValuCirclesConstants.RESULT, ValuCirclesConstants.OLDPWD);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonObject.toString();
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getAgeDetails(int)
	 */
	@Override
	public RefAgeDO getAgeDetails(int refAgeId) {
		RefAgeDO ageDO = (RefAgeDO) getCurrentSession().getNamedQuery("RefAgeDO.getAgeDetails")
				.setParameter("refAgeId", refAgeId).uniqueResult();
		return ageDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfZip()
	 */
	@Override
	public List<RefZipDO> getListOfZip() {
		List<RefZipDO> list = null;		
		list = getCurrentSession().getNamedQuery("RefZipDO.findAll").list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserFinancials(int)
	 */
	@Override
	public boolean updateUserFinancials(int userId) {
		boolean isUpdated = false;
		if (userId > 0) {
			UserLoginDO loginDO = (UserLoginDO) findById(UserLoginDO.class, userId);
			if (loginDO != null) {
				final UserFinancialDTO financialDTO = new UserFinancialDTO();
				String query = "select (select sum(userNetIncome) from user_income where userId=" + userId
						+ " and activeStatus=1) as netIncome, (select sum(userLoanPaymentAmount)  "
						+ " from user_loans where userId=" + userId + " and activeStatus=1) as emi,"
						+ " (select sum(userLoanOutstandingPrincipal) from user_loans where userId=" + userId
						+ " and activeStatus=1) as loanObl,(select assetValue from asset_financing ast "
						+ " where userId=" + userId
						+ " and activeStatus=1) as assetValue,(select  loanValue from asset_financing ast "
						+ " where userId=" + userId
						+ " and activeStatus=1) as loanValue, (select  loanTermId from asset_financing ast "
						+ " where userId=" + userId
						+ " and activeStatus=1) as loanTerm, (select preferredInterestRate from asset_financing ast where userId="
						+ userId
						+ " and activeStatus=1) as preferredInterestRate,(SELECT creditScoreId FROM user_employment "
						+ " where userId=" + userId + " and activeStatus=1) as creditScore,(select  loanTypeId from asset_financing ast"
						+ " where userId=" + userId + " and activeStatus=1) as loantypeid,(select currentLoanOutstandingAmount from asset_financing ast"
					    + " where userId=" + userId + " and activeStatus=1) as currentLoanOutstandingAmount,(select currentEMI from asset_financing ast"  
					    + " where userId=" + userId + " and activeStatus=1) as currentEMI,(select currentLender from asset_financing ast"  
					    + " where userId=" + userId + " and activeStatus=1) as currentLender" ;
				jdbcTemplate.query(query, new ResultSetExtractor<String>() {

					@Override
					public String extractData(ResultSet rs) throws SQLException, DataAccessException {
						while (rs.next()) {
							financialDTO.setIncomeCredits(rs.getBigDecimal("netIncome"));
							financialDTO.setEmiPermissible(rs.getBigDecimal("emi"));
							financialDTO.setLoanObligations(rs.getBigDecimal("loanObl"));

							financialDTO.setAssetValue(
									(rs.getBigDecimal("assetValue") != null) ? rs.getBigDecimal("assetValue")
											: BigDecimal.ZERO);
							financialDTO.setLoanValue(
									(rs.getBigDecimal("loanValue") != null) ? rs.getBigDecimal("loanValue")
											: BigDecimal.ZERO);
							financialDTO.setLoanTerm(rs.getInt("loanTerm"));
							financialDTO.setPreferredInterestRate((rs.getBigDecimal("preferredInterestRate") != null)
									? rs.getBigDecimal("preferredInterestRate")
									: BigDecimal.ZERO);
							financialDTO.setCreditScoreId(rs.getInt("creditScore"));

							/*
							 * 25/08/2019:Geetha - Balance transfer requirement
							 */
							financialDTO.setLoanTypeId(rs.getInt("loantypeid"));
							financialDTO.setCurrentLoanOutstandingAmount(rs.getBigDecimal("currentLoanOutstandingAmount"));
							financialDTO.setCurrentLender(rs.getInt("currentLender"));
							financialDTO.setCurrentEmi(rs.getBigDecimal("currentEMI"));
							/*
							 * End of changes
							 */
						
						}
						return null;
					}
				});
				UserFinancialDO financialDO = null;
				Object obj = getCurrentSession().getNamedQuery("UserFinancialDO.getFinancialUsingUserId")
						.setParameter("userId", userId).uniqueResult();
				if (obj != null) {
					financialDO = (UserFinancialDO) obj;
				} else {
					financialDO = new UserFinancialDO();
					financialDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
					financialDO.setCreatedBy(loginDO.getUserEmail());
					financialDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
					financialDO.setUpdatedBy(loginDO.getUserEmail());
					financialDO.setActiveStatus(1);
				}

				financialDO.setCreditScoreId(financialDTO.getCreditScoreId());
				if (financialDTO.getEmiPermissible() != null) {
					financialDO.setEmiPermissible(financialDTO.getEmiPermissible());
				} else {
					financialDO.setEmiPermissible(BigDecimal.ZERO);
				}

				if (financialDTO.getIncomeCredits() != null) {
					financialDO.setIncomeCredits(financialDTO.getIncomeCredits());
				} else {
					financialDO.setIncomeCredits(BigDecimal.ZERO);
				}
				if (financialDTO.getLoanObligations() != null) {
					financialDO.setLoanObligations(financialDTO.getLoanObligations());
				} else {
					financialDO.setLoanObligations(BigDecimal.ZERO);
				}

				if (financialDTO.getLoanTerm() > 0) {
					financialDO.setLoanTerm(financialDTO.getLoanTerm());
				}
				if (financialDTO.getAssetValue() != null) {
					financialDO.setAssetValue(financialDTO.getAssetValue());
				} else {
					financialDO.setAssetValue(BigDecimal.ZERO);
				}
				if (financialDTO.getLoanValue() != null) {
					financialDO.setLoanValue(financialDTO.getLoanValue());
				} else {
					financialDO.setLoanValue(BigDecimal.ZERO);
				}
				if (financialDTO.getPreferredInterestRate().compareTo(BigDecimal.ZERO) > 0) {
					financialDO.setPreferredInterestRate(
							(financialDTO.getPreferredInterestRate() != null) ? financialDTO.getPreferredInterestRate()
									: BigDecimal.ZERO);
				}
				financialDO.setUserLogin(loginDO);
				if (financialDTO.getLpi() == null) {
					financialDO.setLpi(BigDecimal.ZERO);
				}
				save(financialDO);
				if (financialDO.getUserFinancialsId() > 0) {
					isUpdated = true;
				}
			}
		}
		return isUpdated;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getZipInfo(java.lang.String)
	 */
	@Override
	public List<ZipDTO> getZipInfo(String zipCode) {
		List<RefZipDO> list = getCurrentSession().getNamedQuery("RefZipDO.getZipInfo").setParameter("zipCode", zipCode)
				.list();
		List<ZipDTO> finalList = new ArrayList<ZipDTO>();
		if (list != null) {
			for (RefZipDO do1 : list) {
				ZipDTO dto = new ZipDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCity(do1.getCity());
				dto.setState(do1.getState());
				dto.setZip(do1.getZip());
				dto.setZipId(do1.getZipId());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				finalList.add(dto);
			}
		}

		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getOtpDetails(java.lang.String)
	 */
	@Override
	public OptVerifyDO getOtpDetails(String mobileNo) {
		OptVerifyDO otps = null;
		int id = (int) getCurrentSession().getNamedQuery("OptVerifyDO.getOtpDetailsId")
				.setParameter("mobileNo", mobileNo).uniqueResult();
		Object object = getCurrentSession().getNamedQuery("OptVerifyDO.getOtpDetails").setParameter("id", id)
				.uniqueResult();
		if (object != null) {
			otps = (OptVerifyDO) object;
		}
		return otps;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserInfoDetails(int)
	 */
	@Override
	public UserInfoDO getUserInfoDetails(int userId) {
		UserInfoDO infoDO = null;
		Object object = getCurrentSession().getNamedQuery("UserInfoDO.getUserInfoByUserId")
				.setParameter("userId", userId).uniqueResult();
		if (object != null) {
			infoDO = (UserInfoDO) object;
		}
		return infoDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserFinancials()
	 */
	@Override
	public List<UserFinancialDO> getUserFinancials() {
		List<UserFinancialDO> list = getCurrentSession().getNamedQuery("UserFinancialDO.findAll").list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getYodleeScore(int)
	 */
	@Override
	public YodleeCacheDTO getYodleeScore(int userId) {
		YodleeCacheDTO yodleeCacheDTO = new YodleeCacheDTO();
		BigDecimal incomeloanScore = BigDecimal.ZERO;
		final List<Entry<Integer, BigDecimal>> yodleeScore = new ArrayList<>();
		HashMap<Integer, BigDecimal> emptyArray2 = new HashMap<>();
		emptyArray2.put(0, BigDecimal.ZERO);
		List<Entry<Integer, BigDecimal>> emptyArray = new ArrayList<>(emptyArray2.entrySet());
		if (userId > 0) {
			try {
				String yodleeIncomeLoan = "SELECT SUM(amount)/60 AS incomeLoan FROM valucircles.yodlee_user_transaction ut join valucircles.yodlee_userinfo ui on ui.yodleeUserinfoId=ut.yodleeUserinfoId where categoryid in (17,18,27,29,32,92,94,96,98) and date <current_date() and date >=DATE_ADD(current_date(),interval -60 DAY) and ui.userId= "
						+ userId + " ";
				incomeloanScore = jdbcTemplate.queryForObject(yodleeIncomeLoan, BigDecimal.class);				

				String query = "SELECT T2.lenderId as lender, (T2.weight*T2.score)/100 as yodleeWeightPct FROM "
						+ " (SELECT rg.`lenderId`,rg.`startRange`,rg.`endRange`,rg.`weight`,rg.`score` From `lender` lr JOIN  `yodlee_verification_config` rg ON lr.`lenderId` = rg.`lenderId` "
						+ " UNION ALL SELECT T1.`lenderId`, "
						+ " (SELECT rg.`startRange` FROM  `yodlee_verification_config` as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ incomeloanScore + " and rg.endRange>= " + incomeloanScore + " ), "
						+ " (SELECT rg.`endRange` FROM  `yodlee_verification_config` as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ incomeloanScore + " and rg.`endRange`>= " + incomeloanScore + " ), "
						+ " (SELECT rg.`weight` FROM  `yodlee_verification_config` as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ incomeloanScore + " and rg.endRange>= " + incomeloanScore + " ), "
						+ " (SELECT rg.`score` FROM  yodlee_verification_config as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ incomeloanScore + "  and rg.endRange >= " + incomeloanScore + " ) "
						+ " FROM (SELECT lr.lenderId FROM lender AS lr WHERE lr.lenderId NOT IN (SELECT rg.lenderId FROM yodlee_verification_config rg )) AS T1)  AS T2 "
						+ " where T2.startRange <= " + incomeloanScore + "  and T2.endRange >= " + incomeloanScore
						+ " order by T2.lenderId";

				jdbcTemplate.query(query, new ResultSetExtractor<HashMap<Integer, BigDecimal>>() {

					@Override
					public HashMap<Integer, BigDecimal> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						HashMap<Integer, BigDecimal> yodleeIncomeLoanVal = new HashMap<>();
						if (rs != null) {
							while (rs.next()) {
								yodleeIncomeLoanVal.put(rs.getInt("lender"), rs.getBigDecimal("yodleeWeightPct"));
							}
						}
						yodleeScore.addAll(yodleeIncomeLoanVal.entrySet());
						return yodleeIncomeLoanVal;
					}

				});
				yodleeCacheDTO.setYodleeVerificationScore(yodleeScore);
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
			if (yodleeCacheDTO.getYodleeVerificationScore() == null) {
				yodleeCacheDTO.setYodleeVerificationScore(emptyArray);
			}
			if (yodleeCacheDTO.getYodleeVerificationScore().size() == 0) {
				yodleeCacheDTO.setYodleeVerificationScore(emptyArray);
			}
		}
		return yodleeCacheDTO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfUserType()
	 */
	@Override
	public List<UserTypeDTO> getListOfUserType() {
		List<RefUserTypeDO> list = null;
		if (ValuCirclesConstants.genderCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.userTypeCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefUserTypeDO.findAll").list();
		}
		List<UserTypeDTO> finalList = new ArrayList<UserTypeDTO>();
		if (list != null) {
			for (RefUserTypeDO do1 : list) {
				UserTypeDTO dto = new UserTypeDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setRefUserTypeId(do1.getRefUserTypeId());
				dto.setUserType(do1.getUserType());
				finalList.add(dto);
			}
		}
		return finalList;
	}


	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getgetLenderNameList()
	 */
	@Override
	public List<LenderDO> getgetLenderNameList() {
		List<LenderDO> list = getCurrentSession().getNamedQuery("LenderDO.getLenderNameList").list();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfLoanLender()
	 */
	@Override
	public List<LoanLenderDTO> getListOfLoanLender() {
		List<RefLoanLenderDO> list = getCurrentSession().getNamedQuery("RefLoanLenderDO.findAll").list();
		List<LoanLenderDTO> finalList = new ArrayList<LoanLenderDTO>();
		if (list != null) {
			for (RefLoanLenderDO do1 : list) {
				LoanLenderDTO dto = new LoanLenderDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setLoanLenderId(do1.getLoanLenderId());
				dto.setLoanLenderName(do1.getLoanLenderName());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserOtp(java.lang.String)
	 */
	@Override
	public OptVerifyDO getUserOtp(String mobileNo) {
		OptVerifyDO optVerifyDO = null;
		System.out.println("mobileNo" + mobileNo);
		int id = (int) getCurrentSession().getNamedQuery("OptVerifyDO.getOtpDetailsId")
				.setParameter("mobileNo", mobileNo).uniqueResult();
		System.out.println("id" + id);
		Object object = getCurrentSession().getNamedQuery("OptVerifyDO.getOtpDetails").setParameter("id", id)
				.uniqueResult();
		if (object != null) {
			optVerifyDO = (OptVerifyDO) object;
		}
		return optVerifyDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserById(java.lang.String)
	 */
	@Override
	public UserLoginDO getUserById(String mobileNo) {
		UserLoginDO loginDO = null;
		Object object = getCurrentSession().getNamedQuery("UserLoginDO.getUserByUserId")
				.setParameter("mobileNo", mobileNo).uniqueResult();
		if (object != null) {
			loginDO = (UserLoginDO) object;
		}
		return loginDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#callSubcription(int, java.lang.String, int, int)
	 */
	@Override
	public Map<String, Object> callSubcription(final int subscriptionId, final String subKey, final int count,
			final int subTypeId) {
		final int debug = 0;
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter("count", Types.INTEGER));
		paramList.add(new SqlParameter("subuserId", Types.VARCHAR));
		paramList.add(new SqlParameter("subtypeid", Types.INTEGER));
		paramList.add(new SqlParameter("subkey", Types.INTEGER));
		paramList.add(new SqlParameter("debug", Types.INTEGER));
		paramList.add(new SqlOutParameter("retval", Types.INTEGER));

		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {

				CallableStatement callableStatement = connection
						.prepareCall("{call create_Subscription_Summary(?, ?, ?, ?, ?, ?)}");
				callableStatement.setLong(1, count);
				callableStatement.setInt(2, subscriptionId);
				callableStatement.setInt(3, subTypeId);
				callableStatement.setObject(4, null);
				callableStatement.setInt(5, debug);
				callableStatement.registerOutParameter(6, Types.INTEGER);
				return callableStatement;
			}

		}, paramList);
		return resultMap;
	}

	/**
	 * 06/10/2017
	 * 
	 * @author sathish The assign the subscription to the user
	 */
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#AssignSubscription(int, java.lang.String, int)
	 */
	@Override
	public Map<String, Object> AssignSubscription(final int subscriptorId, final String emailId, final int mysubtype) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter("ownerId", Types.INTEGER));
		paramList.add(new SqlParameter("mysubtype", Types.INTEGER));
		paramList.add(new SqlParameter("usereMail", Types.VARCHAR));
		paramList.add(new SqlOutParameter("retval", Types.INTEGER));
		Map<String, Object> resultMap = null;
		try {
			resultMap = jdbcTemplate.call(new CallableStatementCreator() {

				@Override
				public CallableStatement createCallableStatement(Connection connection) throws SQLException {

					CallableStatement callableStatement = connection
							.prepareCall("{call assign_subscription_to_user(?, ?, ?, ?)}");
					callableStatement.setInt(1, subscriptorId);
					callableStatement.setInt(2, mysubtype);
					callableStatement.setString(3, emailId);
					callableStatement.registerOutParameter(4, Types.INTEGER);
					return callableStatement;
				}

			}, paramList);
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return resultMap;
	}

	/**
	 * 6/10/2017 changes for the assign Subscription to the user
	 */
	
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getTotalSubscriptionforSubscriptor(int)
	 */
	@Override
	public Map<String, Object> getTotalSubscriptionforSubscriptor(final int subscriptorId) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter("myuserId", Types.INTEGER));

		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {

				CallableStatement callableStatement = connection.prepareCall("{call getSubscriptionRates(?,?)}");
				callableStatement.setInt(1, subscriptorId);
				callableStatement.setInt(2, 0); // debug set to False
				return callableStatement;
			}

		}, paramList);
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#ListOfAssignSubscription(int)
	 */
	@Override
	public Map<String, Object> ListOfAssignSubscription(final int subscriptorId) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter("myuserId", Types.INTEGER));

		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {

				CallableStatement callableStatement = connection.prepareCall("{call get_builder_subscribed_users(?)}");
				callableStatement.setInt(1, subscriptorId);
				return callableStatement;
			}

		}, paramList);
		return resultMap;
	}                                                            
	
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#validateMobile(java.lang.String)
	 */
	@Override
	public boolean 	validateMobile(String userPhone, String countrycode) {
		boolean newMobile = true;
		Object obj = getCurrentSession().getNamedQuery("UserLoginDO.getnewMobileOrNot")
				.setParameter("userPhone", userPhone)
				.setParameter("userCountryCode", countrycode).uniqueResult();
		if (obj != null) {
			newMobile = false;
		}
		return newMobile;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getPerfiosScore(int)
	 */
	@Override
	public PerfiosCacheDTO getPerfiosScore(int userId) {
		PerfiosCacheDTO perfiosCacheDTO = new PerfiosCacheDTO();
		BigDecimal incomeScore = BigDecimal.ZERO;
		BigDecimal loanScore = BigDecimal.ZERO;
		final List<Entry<Integer, BigDecimal>> perfiosScore = new ArrayList<>();
		final List<Entry<Integer, BigDecimal>> perfiosLoanScore = new ArrayList<>();

		HashMap<Integer, BigDecimal> emptyArray2 = new HashMap<>();
		emptyArray2.put(0, BigDecimal.ZERO);
		List<Entry<Integer, BigDecimal>> emptyArray = new ArrayList<>(emptyArray2.entrySet());
		String netVerify = null;
		String loanVerify = null;
		if (userId > 0) {
			try {
				UserFinancialDTO dto = getUserFinancialByUserId(userId);
				BigDecimal netIncome = dto.getIncomeCredits();
				BigDecimal loanObl = dto.getLoanObligations();
				String perfiosIncome = "select sum(amount) from perfios_user_transaction where category in ('Salary','Interest','Investment Income','Pension','Dividend') and date <current_date() and date >=DATE_ADD(current_date(),interval -60 DAY) and bankId in (select bankId from perfios_user_bank_account where userId in ( "
						+ userId + " ))";
				incomeScore = jdbcTemplate.queryForObject(perfiosIncome, BigDecimal.class);
				if (netIncome != null) {
					if (incomeScore != null) {
						BigDecimal net = incomeScore.subtract(netIncome);
						int incomeScoreVerify = Integer.valueOf(incomeScore.intValue());
						int netReport = Integer.valueOf(net.intValue());
						int netDiff = netReport / incomeScoreVerify;
						if (netReport > 0) {
							netVerify = String.valueOf(100);
						} else {
							netVerify = String.valueOf(netDiff);
						}
					}

				}
				String perfiosLoan = "select ABS(sum(amount)) from perfios_user_transaction where category in ('Insurance','Personal Loan','Interest Charges','House Loan','Gold Loan') and date <current_date() and date >=DATE_ADD(current_date(),interval -60 DAY) and bankId in (select bankId from perfios_user_bank_account where userId in ( "
						+ userId + " ))";
				loanScore = jdbcTemplate.queryForObject(perfiosLoan, BigDecimal.class);
				if (loanObl != null) {
					if (loanScore != null) {
						BigDecimal loan = loanScore.subtract(loanObl);
						int loanScoreVerify = Integer.valueOf(loanScore.intValue());
						int loanReport = Integer.valueOf(loan.intValue());
						int loanDiff = loanReport / loanScoreVerify;
						if (loanReport < 0) {
							loanVerify = String.valueOf(100);
						} else {
							loanVerify = String.valueOf(loanDiff);
						}
					}

				}
				
				String query = "SELECT T2.lenderId as lender, (T2.weight*T2.score)/100 as yodleeWeightPct FROM "
						+ " (SELECT rg.`lenderId`,rg.`startRange`,rg.`endRange`,rg.`weight`,rg.`score` From `lender` lr JOIN  `yodlee_verification_config` rg ON lr.`lenderId` = rg.`lenderId` "
						+ " UNION ALL SELECT T1.`lenderId`, "
						+ " (SELECT rg.`startRange` FROM  `yodlee_verification_config` as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ netVerify + " and rg.endRange>= " + netVerify + " ), "
						+ " (SELECT rg.`endRange` FROM  `yodlee_verification_config` as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ netVerify + " and rg.`endRange`>= " + netVerify + " ), "
						+ " (SELECT rg.`weight` FROM  `yodlee_verification_config` as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ netVerify + " and rg.endRange>= " + netVerify + " ), "
						+ " (SELECT rg.`score` FROM  yodlee_verification_config as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ netVerify + "  and rg.endRange >= " + netVerify + " ) "
						+ " FROM (SELECT lr.lenderId FROM lender AS lr WHERE lr.lenderId NOT IN (SELECT rg.lenderId FROM yodlee_verification_config rg )) AS T1)  AS T2 "
						+ " where T2.startRange <= " + netVerify + "  and T2.endRange >= " + netVerify
						+ " order by T2.lenderId";

				jdbcTemplate.query(query, new ResultSetExtractor<HashMap<Integer, BigDecimal>>() {

					@Override
					public HashMap<Integer, BigDecimal> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						HashMap<Integer, BigDecimal> perfiosIncomeLoanVal = new HashMap<>();
						if (rs != null) {
							while (rs.next()) {
								perfiosIncomeLoanVal.put(rs.getInt("lender"), rs.getBigDecimal("yodleeWeightPct"));
							}
						}
						perfiosScore.addAll(perfiosIncomeLoanVal.entrySet());
						return perfiosIncomeLoanVal;
					}

				});
				perfiosCacheDTO.setPerfiosVerificationScore(perfiosScore);

				String query2 = "SELECT T2.lenderId as lender, (T2.weight*T2.score)/100 as yodleeWeightPct FROM "
						+ " (SELECT rg.`lenderId`,rg.`startRange`,rg.`endRange`,rg.`weight`,rg.`score` From `lender` lr JOIN  `yodlee_verification_config` rg ON lr.`lenderId` = rg.`lenderId` "
						+ " UNION ALL SELECT T1.`lenderId`, "
						+ " (SELECT rg.`startRange` FROM  `yodlee_verification_config` as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ loanVerify + " and rg.endRange>= " + loanVerify + " ), "
						+ " (SELECT rg.`endRange` FROM  `yodlee_verification_config` as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ loanVerify + " and rg.`endRange`>= " + loanVerify + " ), "
						+ " (SELECT rg.`weight` FROM  `yodlee_verification_config` as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ loanVerify + " and rg.endRange>= " + loanVerify + " ), "
						+ " (SELECT rg.`score` FROM  yodlee_verification_config as rg WHERE rg.`lenderId`=0 and rg.`startRange`<= "
						+ loanVerify + "  and rg.endRange >= " + loanVerify + " ) "
						+ " FROM (SELECT lr.lenderId FROM lender AS lr WHERE lr.lenderId NOT IN (SELECT rg.lenderId FROM yodlee_verification_config rg )) AS T1)  AS T2 "
						+ " where T2.startRange <= " + loanVerify + "  and T2.endRange >= " + loanVerify
						+ " order by T2.lenderId";

				jdbcTemplate.query(query2, new ResultSetExtractor<HashMap<Integer, BigDecimal>>() {

					@Override
					public HashMap<Integer, BigDecimal> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						HashMap<Integer, BigDecimal> perfiosLoanVal = new HashMap<>();
						if (rs != null) {
							while (rs.next()) {
								perfiosLoanVal.put(rs.getInt("lender"), rs.getBigDecimal("yodleeWeightPct"));
							}
						}
						perfiosLoanScore.addAll(perfiosLoanVal.entrySet());
						return perfiosLoanVal;
					}

				});
				perfiosCacheDTO.setPerfiosLoanVerificationScore(perfiosLoanScore);
			} catch (EmptyResultDataAccessException e) {
				return null;
			}
			if (perfiosCacheDTO.getPerfiosVerificationScore() == null) {
				perfiosCacheDTO.setPerfiosVerificationScore(emptyArray);
			}
			if (perfiosCacheDTO.getPerfiosVerificationScore().size() == 0) {
				perfiosCacheDTO.setPerfiosVerificationScore(emptyArray);
			}
			if (perfiosCacheDTO.getPerfiosLoanVerificationScore() == null) {
				perfiosCacheDTO.setPerfiosLoanVerificationScore(emptyArray);
			}
			if (perfiosCacheDTO.getPerfiosLoanVerificationScore().size() == 0) {
				perfiosCacheDTO.setPerfiosLoanVerificationScore(emptyArray);
			}
		}
		return perfiosCacheDTO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserOtp(java.lang.String)
	 */
	@Override
	public boolean updateUserOtp(String userPhone) {
		int isUpdated = getCurrentSession().getNamedQuery("OptVerifyDO.updateUserOtp")
				.setParameter("userPhone", userPhone).executeUpdate();
		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserAsset(int)
	 */
	@Override
	public boolean updateUserAsset(int userId) {
		int isUpdated = getCurrentSession().getNamedQuery("AssetDO.updateUserAsset").setParameter("userId", userId)
				.executeUpdate();
		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserFinancialByUserId(int)
	 */
	@Override
	public UserFinancialDTO getUserFinancialByUserId(int userId) {
		UserFinancialDTO dto = new UserFinancialDTO();
		UserFinancialDO financialDO = (UserFinancialDO) getCurrentSession()
				.getNamedQuery("UserFinancialDO.getFinancialUsingUserId").setParameter("userId", userId).uniqueResult();
		if (financialDO != null) {
			dto.setActiveStatus(financialDO.getActiveStatus());
			dto.setAssetValue(financialDO.getAssetValue());
			dto.setEmiPermissible(financialDO.getEmiPermissible());
			dto.setIncomeCredits(financialDO.getIncomeCredits());
			dto.setLoanObligations(financialDO.getLoanObligations());
			dto.setLoanTerm(financialDO.getLoanTerm());
			dto.setLoanValue(financialDO.getLoanValue());
			dto.setLpi(financialDO.getLpi());
			dto.setPreferredInterestRate(financialDO.getPreferredInterestRate());
			dto.setUserId(financialDO.getUserLogin().getUserId());
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserCoApplicant(int)
	 */
	@Override
	public boolean updateUserCoApplicant(int userId) {
		int isUpdated = getCurrentSession().getNamedQuery("UserCoApplicantDO.updateUserCoApplicant")
				.setParameter("userId", userId).executeUpdate();
		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}
	}

	
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserCoApplicantIncome(int)
	 */
	@Override
	public boolean updateUserCoApplicantIncome(int userId) {
		int isUpdated = getCurrentSession().getNamedQuery("UserIncomeDO.updateUserCoApplicantIncome")
				.setParameter("userId", userId).executeUpdate();
		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserCoApplicantLoan(int)
	 */
	@Override
	public boolean updateUserCoApplicantLoan(int userId) {
		int isUpdated = getCurrentSession().getNamedQuery("UserLoanDO.updateUserCoAppicantLoan")
				.setParameter("userId", userId).executeUpdate();
		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserCoApplicantSelfEmployement(int)
	 */
	@Override
	public boolean updateUserCoApplicantSelfEmployement(int userId) {
		int isUpdated = getCurrentSession()
				.getNamedQuery("UserCoApplicantSelfEmploymentBusinessDO.updateUserCoApplicantSelfEmployment")
				.setParameter("userId", userId).executeUpdate();
		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserCoApplicantEmployement(int)
	 */
	@Override
	public boolean updateUserCoApplicantEmployement(int userId) {
		int isUpdated = getCurrentSession().getNamedQuery("UserCoApplicantEmploymentDO.updateUserCoApplicantEmployment")
				.setParameter("userId", userId).executeUpdate();
		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserCoApplicantIncome(int)
	 */
	@Override
	public List<UserIncomeDTO> getUserCoApplicantIncome(int userId) {
		List<UserIncomeDO> list = getCurrentSession().getNamedQuery("UserIncomeDO.getUserCoApplicantIncome")
				.setParameter("userId", userId).list();
		List<UserIncomeDTO> finalList = new ArrayList<UserIncomeDTO>();
		if (list != null) {
			for (UserIncomeDO do1 : list) {
				UserIncomeDTO dto = new UserIncomeDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setManualEntry(do1.getManualEntry());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setUserEmpoymentInfo(do1.getUserEmploymentInfo());
				dto.setUserGrossIncome(do1.getUserGrossIncome());
				dto.setUserId(do1.getUserLogin().getUserId());
				dto.setUserIncomeId(do1.getUserIncomeId());
				dto.setUserNetIncome(do1.getUserNetIncome());
				dto.setRefIncomeTypeId(do1.getRefIncomeType().getRefIncomeTypeId());
				dto.setIncomeTypedesc(do1.getRefIncomeType().getIncomeTypedesc());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserCoApplicantLoan(int)
	 */
	@Override
	public List<UserLoanDTO> getUserCoApplicantLoan(int userId) {
		List<UserLoanDO> list = getCurrentSession().getNamedQuery("UserLoanDO.getUserCoApplicantLoan")
				.setParameter("userId", userId).list();
		List<UserLoanDTO> finalList = new ArrayList<>();
		if (list != null) {
			for (UserLoanDO loanDO : list) {
				UserLoanDTO loanDTO = new UserLoanDTO();
				loanDTO.setActiveStatus(loanDO.getActiveStatus());
				loanDTO.setCreatedBy(loanDO.getCreatedBy());
				loanDTO.setCreatedOn(loanDO.getCreatedOn());
				loanDTO.setManualEntry(loanDO.getManualEntry());
				loanDTO.setUpdatedBy(loanDO.getUpdatedBy());
				loanDTO.setUpdatedOn(loanDO.getUpdatedOn());
				loanDTO.setUserId(loanDO.getUserLogin().getUserId());
				loanDTO.setUserLenderName(loanDO.getUserLenderName());
				RefLoanLenderDO loanLenderDO = (RefLoanLenderDO) findById(RefLoanLenderDO.class,
						loanDO.getUserLenderName());
				if (loanLenderDO != null) {
					loanDTO.setLoanLender(loanLenderDO.getLoanLenderName());
				}
				loanDTO.setUserLoanOutstandingPrincipal(loanDO.getUserLoanOutstandingPrincipal());
				loanDTO.setUserLoanPaymentAmount(loanDO.getUserLoanPaymentAmount());
				loanDTO.setUserLoanRemainingTenure(loanDO.getUserLoanRemainingTenure());
				loanDTO.setUserLoansId(loanDO.getUserLoansId());
				loanDTO.setLoanTypeId(loanDO.getRefLoanType().getLoanTypeId());
				loanDTO.setLoanTypeDesc(loanDO.getRefLoanType().getLoanTypeDesc());
				finalList.add(loanDTO);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserName(int)
	 */
	@Override
	public UserInfoDO getUserName(int userId) {
		UserInfoDO userInfoDO = null;
		Object object = getCurrentSession().getNamedQuery("UserInfoDO.getUserInfoByUserId")
				.setParameter("userId", userId).uniqueResult();
		if (object != null) {
			userInfoDO = (UserInfoDO) object;
		}
		return userInfoDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getSubcriptionKey(java.lang.String)
	 */
	@Override
	public SubscriptionDetailDO getSubcriptionKey(String userEmail) {
		SubscriptionDetailDO detailDO = null;
		Object object = getCurrentSession().getNamedQuery("SubscriptionDetailDO.getSubcriptionKey")
				.setParameter("userEmail", userEmail).uniqueResult();
		if (object != null) {
			detailDO = (SubscriptionDetailDO) object;
		}
		return detailDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserInfoDetailsUsingViews(int)
	 */
	@Override
	public UserDataDTO getUserInfoDetailsUsingViews(int userId) {
		UserLoginDO loginDO = (UserLoginDO) findById(UserLoginDO.class, userId);
		final UserDataDTO dto = new UserDataDTO();
		if (loginDO != null) {
			String userInfo = "select * from getuserinfo where userId= " + userId + " ";
			jdbcTemplate.query(userInfo, new ResultSetExtractor<String>() {

				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					while (rs.next()) {
						dto.setAddress1(rs.getString("address1"));
						dto.setAddress2(rs.getString("address2"));
						dto.setFirstName(rs.getString("firstName"));
					}
					return dto.toString();
				}
			});
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getCoApplicantId(int)
	 */
	@Override
	public Map<String, Object> getCoApplicantId(final int userId) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter("myuser", Types.INTEGER));

		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {

				CallableStatement callableStatement = connection.prepareCall("{call getCoApplicantId(?)}");
				callableStatement.setLong(1, userId);
				return callableStatement;
			}

		}, paramList);
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#deactivateCoApplicants(int)
	 */
	@Override
	public Map<String, Object> deactivateCoApplicants(final int userId) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter("myuser", Types.INTEGER));

		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {

				CallableStatement callableStatement = connection.prepareCall("{call  deactivateCoApplicants(?,?)}");
				callableStatement.setLong(1, userId);
				callableStatement.setLong(2, 0);
				return callableStatement;
			}

		}, paramList);
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getCoApplicantDetailsUsingListOfUserId(java.lang.String)
	 */
	@Override
	public List<UserDataDTO> getCoApplicantDetailsUsingListOfUserId(String listOfUserId) {
		List<UserDTO> userDTO = UtilityMethods.parseJsonToObject(listOfUserId, ArrayList.class, UserDTO.class);
		List<UserDataDTO> applicantDO1 = new ArrayList<UserDataDTO>();

		if (userDTO != null) {
			for (UserDTO dto : userDTO) {
				int userId = dto.getUserid();
				UserInfoDO userInfoDO = (UserInfoDO) getCurrentSession()
						.getNamedQuery("UserInfoDO.getUserInfoByUsingUserId").setParameter("userId", userId)
						.uniqueResult();

				if (userInfoDO != null) {
					UserDataDTO dataDTO = new UserDataDTO();
					dataDTO.setUserId(userInfoDO.getUserLogin().getUserId());
					dataDTO.setActiveStatus(userInfoDO.getActiveStatus());					
					dataDTO.setAddress1(userInfoDO.getUserAddress().getAddress1());
					dataDTO.setAddress2(userInfoDO.getUserAddress().getAddress2());
					dataDTO.setAddress3(userInfoDO.getUserAddress().getAddress3());						
					RefZipDO refZipDO = (RefZipDO) findById(RefZipDO.class, userInfoDO.getUserAddress().getZipid());
					if (refZipDO != null) {
						dataDTO.setCity(refZipDO.getCity());
						dataDTO.setPostalZip(refZipDO.getZip());
						dataDTO.setState(refZipDO.getState());
						dataDTO.setZipId(refZipDO.getZipId());
					}
					dataDTO.setCountry(userInfoDO.getUserAddress().getCountry());
					dataDTO.setCreatedBy(userInfoDO.getCreatedBy());
					dataDTO.setCreatedOn(userInfoDO.getCreatedOn());
					dataDTO.setDateofBirth(userInfoDO.getDateOfBirth());
					dataDTO.setFirstName(userInfoDO.getFirstName());
					dataDTO.setLastName(userInfoDO.getLastName());
					dataDTO.setUpdatedBy(userInfoDO.getUpdatedBy());
					dataDTO.setUserInfoId(userInfoDO.getUserInfoId());
					dataDTO.setMaritalStatus(userInfoDO.getRefMaritalStatus().getMaritalStatus());
					dataDTO.setMaritalStatusId(userInfoDO.getRefMaritalStatus().getMaritalStatusId());
					dataDTO.setGenderId(userInfoDO.getRefGender().getGenderId());
					dataDTO.setRefAgeId(userInfoDO.getRefAge().getRefAgeId());
					dataDTO.setEducationId(userInfoDO.getRefEducation().getEducationId());
					dataDTO.setGender(userInfoDO.getRefGender().getGender());
					dataDTO.setEducation(userInfoDO.getRefEducation().getEducation());
					dataDTO.setRefResidenceCategoryId(userInfoDO.getRefResidenceCategory().getRefResidenceCategoryId());
					dataDTO.setResidenceCategory(userInfoDO.getRefResidenceCategory().getResidenceCategory());
					dataDTO.setEducationDetailsId(userInfoDO.getEducationDetailsId());
					dataDTO.setEducationInstitutionId(userInfoDO.getEducationInstitutionId());
					dataDTO.setRelationshipId(userInfoDO.getUserLogin().getRelationshipId());
					dataDTO.setCoApplicantId(userInfoDO.getCoApplicantId());
					dataDTO.setPropertyIdentifierId(userInfoDO.getPropertyIdentifierId());
					dataDTO.setCompareaddrId(userInfoDO.getCompareaddrId());
					RefEducationDetailDO detailDO = (RefEducationDetailDO) findById(RefEducationDetailDO.class,
							userInfoDO.getEducationDetailsId());
					if (detailDO != null) {
						dataDTO.setEducationDetail(detailDO.getEducationDetails());
					}
					RefEducationInstitutionDO institutionDO = (RefEducationInstitutionDO) findById(
							RefEducationInstitutionDO.class, userInfoDO.getEducationInstitutionId());
					if (institutionDO != null) {
						dataDTO.setEducationInstitution(institutionDO.getInstitution());
					}
					int dob = UtilityMethods.getAgeUsingDateOfBirth(userInfoDO.getDateOfBirth());
					dataDTO.setAge(dob);
					applicantDO1.add(dataDTO);
				}

			}

		}
		return applicantDO1;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUser(int)
	 */
	@Override
	public UserLoginDO getUser(int userId) {
		UserLoginDO loginDO = null;
		Object object = getCurrentSession().getNamedQuery("UserLoginDO.getUser").setParameter("userId", userId)
				.uniqueResult();
		if (object != null) {
			loginDO = (UserLoginDO) object;
		}
		return loginDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserCoApplicantInfo(int)
	 */
	@Override
	public boolean updateUserCoApplicantInfo(int userId) {
		String updateUserInfo = "update user_info set activeStatus=0 where userId in (select userId from user_login where activeStatus=1 and primaryUserId="
				+ userId + ") and activeStatus=1";
		int updateInfo = jdbcTemplate.update(updateUserInfo);
		if (updateInfo > 0) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserCoApplicantAddress(int)
	 */
	@Override
	public boolean updateUserCoApplicantAddress(int userId) {
		String updateUserAddress = "update user_address set activeStatus=0 where userId in (select userId from user_login where activeStatus=1 and primaryUserId="
				+ userId + ") and activeStatus=1";
		int updateAddress = jdbcTemplate.update(updateUserAddress);
		if (updateAddress > 0) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserCoApplicantLogin(int)
	 */
	@Override
	public boolean updateUserCoApplicantLogin(int userId) {
		String updateUserLogin = "update user_login set activeStatus=0 where primaryUserId=" + userId
				+ " and activeStatus=1";
		int updateLogin = jdbcTemplate.update(updateUserLogin);
		if (updateLogin > 0) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserInfoDT(int)
	 */
	@Override
	public UserInfoDO getUserInfoDT(int userId) {
		UserInfoDO do1 = null;
		Object object = getCurrentSession().getNamedQuery("UserInfoDO.getUserInfoByUserId")
				.setParameter("userId", userId).uniqueResult();
		if (object != null) {
			do1 = (UserInfoDO) object;
		}
		return do1;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserAddress(int)
	 */
	@Override
	public UserAddressDO getUserAddress(int userId) {
		UserAddressDO do1 = null;
		Object object = getCurrentSession().getNamedQuery("UserAddressDO.getUserAddress").setParameter("userId", userId)
				.uniqueResult();
		if (object != null) {
			do1 = (UserAddressDO) object;
		}
		return do1;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getCoApplicantUserLogin(int)
	 */
	@Override
	public List<UserLoginDO> getCoApplicantUserLogin(int userId) {
		List<UserLoginDO> list = getCurrentSession().getNamedQuery("UserLoginDO.getCoApplicantDetails")
				.setParameter("userId", userId).list();
		if (list != null) {
			return list;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserLoginForCoApplicant(int)
	 */
	@Override
	public List<UserLoginDO> getUserLoginForCoApplicant(int userId) {
		List<UserLoginDO> list = getCurrentSession().getNamedQuery("UserLoginDO.getCoApplicantDetails")
				.setParameter("userId", userId).list();
		if (list != null) {
			return list;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserCoApplicantEmployments(int)
	 */
	@Override
	public UserEmploymentDO getUserCoApplicantEmployments(int userId) {
		UserEmploymentDO do1 = null;
		Object object = getCurrentSession().getNamedQuery("UserEmploymentDO.getEmploymentDetails")
				.setParameter("userId", userId).uniqueResult();
		if (object != null) {
			do1 = (UserEmploymentDO) object;
		}
		return do1;
	}


	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateCoApplicantEmployment(int)
	 */
	@Override
	public boolean updateCoApplicantEmployment(int userId) {
		String CoApplicantEmployment = "update user_employment set activeStatus=0 where userId in (select userId from user_login where activeStatus=1 and primaryUserId="
				+ userId + ") and activeStatus=1";		
		int updateEmployment = jdbcTemplate.update(CoApplicantEmployment);
		if (updateEmployment > 0) {
			return true;
		} else {
			return false;
		}
	}


	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateCoApplicantIncome(int)
	 */
	@Override
	public boolean updateCoApplicantIncome(int userId) {
		String CoApplicantIncome = "update user_income set activeStatus=0 where userId in (select userId from user_login where activeStatus=1 and primaryUserId="
				+ userId + ") and activeStatus=1";
		int updateIncome = jdbcTemplate.update(CoApplicantIncome);
		if (updateIncome > 0) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateCoApplicantLoan(int)
	 */
	@Override
	public boolean updateCoApplicantLoan(int userId) {
		String CoApplicantLoan = "update user_loans set activeStatus=0 where userId in (select userId from user_login where activeStatus=1 and primaryUserId="
				+ userId + ") and activeStatus=1";
		int updateLoan = jdbcTemplate.update(CoApplicantLoan);
		if (updateLoan > 0) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getCoApplicantEmploymentAndIncomeLoan(java.lang.String)
	 */
	@Override
	public List<FinalCoApplicantLatestDTO> getCoApplicantEmploymentAndIncomeLoan(String listOfUserId) {
		List<UserDTO> userDTO = UtilityMethods.parseJsonToObject(listOfUserId, ArrayList.class, UserDTO.class);
		List<FinalCoApplicantLatestDTO> applicantDO1 = new ArrayList<FinalCoApplicantLatestDTO>();
		if (userDTO != null) {
			for (UserDTO dto : userDTO) {
				int userId = dto.getUserid();
				FinalCoApplicantLatestDTO applicantLatestDTO = new FinalCoApplicantLatestDTO();
				new ArrayList<UserEmploymentDTO>();
				UserEmploymentDO employmentDO = (UserEmploymentDO) getCurrentSession()
						.getNamedQuery("UserEmploymentDO.getEmploymentIdDetails").setParameter("userId", userId)
						.uniqueResult();
				if (employmentDO != null) {					
					applicantLatestDTO.setActiveStatus(employmentDO.getActiveStatus());
					applicantLatestDTO.setCreatedBy(employmentDO.getCreatedBy());
					applicantLatestDTO.setCreatedOn(employmentDO.getCreatedOn());
					applicantLatestDTO.setEmployerId(employmentDO.getEmployer().getEmployerId());
					applicantLatestDTO.setOccupationTypeId(employmentDO.getOccupationType().getOccupationTypeId());
					applicantLatestDTO.setUpdatedBy(employmentDO.getUpdatedBy());
					applicantLatestDTO.setUpdatedOn(employmentDO.getUpdatedOn());
					applicantLatestDTO.setUserEmploymentId(employmentDO.getUserEmploymentId());
					applicantLatestDTO.setYearsofServiceinIndustry(employmentDO.getYearsOfServiceInIndustry());
					applicantLatestDTO.setYearsofServicewithEmployer(employmentDO.getYearsOfServicewithEmployer());
					applicantLatestDTO.setOccupationDesc(employmentDO.getOccupationType().getOccupationType());
					applicantLatestDTO.setEmployerName(employmentDO.getEmployer().getEmployerName());
					applicantLatestDTO.setRefCreditScoreId(employmentDO.getRefCreditScore().getRefCreditScoreId());
					applicantLatestDTO.setPensionEligibility(employmentDO.getPensionEligibility());					
					applicantLatestDTO.setStartRangeCreditScore(employmentDO.getRefCreditScore().getStartRange());
					applicantLatestDTO.setEndRangeCreditScore(employmentDO.getRefCreditScore().getEndRange());					
					applicantLatestDTO.setUserId(employmentDO.getUserLogin().getUserId());
					applicantLatestDTO.setCin(employmentDO.getCin());
					applicantLatestDTO.setOccupationcategoryid(employmentDO.getOccupationcategoryid());
					applicantLatestDTO.setCategorydetail(employmentDO.getCategorydetail());
					/*
					 * 18/08/2019-Geetha: Including ITfiled attribute co-applicant page
					 */
					applicantLatestDTO.setITfiled(employmentDO.getITfiled());
					applicantLatestDTO.setOwnershipShare(employmentDO.getOwnershipShare());
					applicantLatestDTO.setOfficeType(employmentDO.getOfficeType());
					applicantLatestDTO.setannualProfit(employmentDO.getannualProfit());
					applicantLatestDTO.setannualSales(employmentDO.getannualSales());
					applicantLatestDTO.setBusinessConstitution(employmentDO.getBusinessConstitution());
					applicantLatestDTO.setemployeeCount(employmentDO.getemployeeCount());
					applicantLatestDTO.setprimaryBank(employmentDO.getprimaryBank());
					applicantLatestDTO.setannualProfitPY(employmentDO.getannualProfit());
					applicantLatestDTO.setAnnualSalesPY(employmentDO.getannualSalesPY());

				}
				
				// income
				List<UserIncomeDTO> incomeDTO = new ArrayList<UserIncomeDTO>();
				List<UserIncomeDO> incomeDO1 = getCurrentSession()
						.getNamedQuery("UserIncomeDO.getUserCoApplicantIncome").setParameter("userId", userId).list();
				for (UserIncomeDO incomeDO : incomeDO1) {
					UserIncomeDTO incomeDTO1 = new UserIncomeDTO();
					incomeDTO1.setActiveStatus(incomeDO.getActiveStatus());
					incomeDTO1.setCreatedBy(incomeDO.getCreatedBy());
					incomeDTO1.setCreatedOn(incomeDO.getCreatedOn());
					incomeDTO1.setManualEntry(incomeDO.getManualEntry());
					incomeDTO1.setUpdatedBy(incomeDO.getUpdatedBy());
					incomeDTO1.setUpdatedOn(incomeDO.getUpdatedOn());
					incomeDTO1.setUserEmpoymentInfo(incomeDO.getUserEmploymentInfo());
					incomeDTO1.setUserGrossIncome(incomeDO.getUserGrossIncome());
					incomeDTO1.setUserId(incomeDO.getUserLogin().getUserId());
					incomeDTO1.setUserIncomeId(incomeDO.getUserIncomeId());
					incomeDTO1.setUserNetIncome(incomeDO.getUserNetIncome());
					incomeDTO1.setRefIncomeTypeId(incomeDO.getRefIncomeType().getRefIncomeTypeId());
					incomeDTO1.setIncomeTypedesc(incomeDO.getRefIncomeType().getIncomeTypedesc());
					incomeDTO1.setFrequencyType(incomeDO.getFrequencyType());
					incomeDTO.add(incomeDTO1);
				}
				applicantLatestDTO.setUserIncome(incomeDTO);
				// loan
				List<UserLoanDTO> loanDTO = new ArrayList<UserLoanDTO>();
				List<UserLoanDO> loanDO = getCurrentSession().getNamedQuery("UserLoanDO.getUserCoApplicantLoan")
						.setParameter("userId", userId).list();
				for (UserLoanDO do4 : loanDO) {
					UserLoanDTO dtoloan = new UserLoanDTO();
					dtoloan.setActiveStatus(do4.getActiveStatus());
					dtoloan.setCreatedBy(do4.getCreatedBy());
					dtoloan.setCreatedOn(do4.getCreatedOn());
					dtoloan.setManualEntry(do4.getManualEntry());
					dtoloan.setUpdatedBy(do4.getUpdatedBy());
					dtoloan.setUpdatedOn(do4.getUpdatedOn());
					dtoloan.setUserId(do4.getUserLogin().getUserId());
					dtoloan.setUserLenderName(do4.getUserLenderName());
					RefLoanLenderDO loanLenderDO = (RefLoanLenderDO) findById(RefLoanLenderDO.class,
							do4.getUserLenderName());
					if (loanLenderDO != null) {
						dtoloan.setLoanLender(loanLenderDO.getLoanLenderName());
					}
					dtoloan.setUserLoanOutstandingPrincipal(do4.getUserLoanOutstandingPrincipal());
					dtoloan.setUserLoanPaymentAmount(do4.getUserLoanPaymentAmount());
					dtoloan.setUserLoanRemainingTenure(do4.getUserLoanRemainingTenure());
					dtoloan.setUserLoansId(do4.getUserLoansId());
					dtoloan.setLoanTypeId(do4.getRefLoanType().getLoanTypeId());
					dtoloan.setLoanTypeDesc(do4.getRefLoanType().getLoanTypeDesc());
					loanDTO.add(dtoloan);
				}
				applicantLatestDTO.setUserLoan(loanDTO);
				// salary deduction
				List<UserSalaryDeductionDTO> finalSalaryDTO = new ArrayList<>();
				List<UserSalaryDeductionDO> salaryList = getCurrentSession()
						.getNamedQuery("UserSalaryDeductionDO.getUserCoApplicantSalaryDeduction")
						.setParameter("userId", userId).list();
				for (UserSalaryDeductionDO salaryDO : salaryList) {
					UserSalaryDeductionDTO salaryDTO = new UserSalaryDeductionDTO();
					salaryDTO.setActiveStatus(salaryDO.getActiveStatus());
					salaryDTO.setCreatedBy(salaryDO.getCreatedBy());
					salaryDTO.setCreatedOn(salaryDO.getCreatedOn());
					salaryDTO.setUpdatedBy(salaryDO.getUpdatedBy());
					salaryDTO.setUpdatedOn(salaryDO.getUpdatedOn());
					salaryDTO.setUserId(salaryDO.getUserLogin().getUserId());
					salaryDTO.setAmount(salaryDO.getAmount());
					salaryDTO.setUsersaldednsId(salaryDO.getUsersaldednsId());
					salaryDTO.setSalaryDednTypeId(salaryDO.getRefSalaryDeduction().getSalaryDednTypeId());
					salaryDTO.setSalaryDednType(salaryDO.getRefSalaryDeduction().getDednTypeDesc());

					finalSalaryDTO.add(salaryDTO);
				}
				applicantLatestDTO.setUserSalaryDeduction(finalSalaryDTO);

				applicantDO1.add(applicantLatestDTO);
			}

		}
		return applicantDO1;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getLenderUserList(int)
	 */
	@Override
	public List<LenderUserDTO> getLenderUserList(int lenderId) {
		List<UserLpiLenderDO> list = getCurrentSession().getNamedQuery("UserLpiLenderDO.getUserLenderList")
				.setParameter("lenderId", lenderId).list();
		List<LenderUserDTO> finalList = new ArrayList<LenderUserDTO>();
		for (UserLpiLenderDO do1 : list) {
			LenderUserDTO dto = new LenderUserDTO();
			dto.setActiveStatus(do1.getActiveStatus());
			dto.setLpi(do1.getLpi());
			dto.setUserId(do1.getUserLogin().getUserId());
			dto.setLenderId(do1.getLender().getLenderId());			
			List<UserDataDTO> infoDTO = new ArrayList<UserDataDTO>();
			for (UserInfoDO infoDO : do1.getUserLogin().getUserInfos()) {
				UserDataDTO userInfoDTO = new UserDataDTO();
				if (infoDO.getActiveStatus() > 0) {
					userInfoDTO.setActiveStatus(infoDO.getActiveStatus());
					userInfoDTO.setEducation(infoDO.getRefEducation().getEducation());
					userInfoDTO.setGender(infoDO.getRefGender().getGender());
					userInfoDTO.setMaritalStatus(infoDO.getRefMaritalStatus().getMaritalStatus());
					userInfoDTO.setFirstName(infoDO.getFirstName());
					userInfoDTO.setUserInfoId(infoDO.getUserInfoId());
					userInfoDTO.setLastName(infoDO.getLastName());
					userInfoDTO.setCoApplicantId(infoDO.getCoApplicantId());
					userInfoDTO.setPropertyIdentifierId(infoDO.getPropertyIdentifierId());
					userInfoDTO.setCompareaddrId(infoDO.getCompareaddrId());
					userInfoDTO.setUserId(infoDO.getUserLogin().getUserId());
					infoDTO.add(userInfoDTO);
				}
			}
			dto.setUserDataDTO(infoDTO);
			List<UserIncomeDTO> incomeDTO = new ArrayList<UserIncomeDTO>();
			for (UserIncomeDO incomeDO : do1.getUserLogin().getUserIncomes()) {
				UserIncomeDTO userIncomeDTO = new UserIncomeDTO();
				if (incomeDO.getActiveStatus() > 0) {
					userIncomeDTO.setActiveStatus(incomeDO.getActiveStatus());
					userIncomeDTO.setIncomeTypedesc(incomeDO.getRefIncomeType().getIncomeTypedesc());
					userIncomeDTO.setUserGrossIncome(incomeDO.getUserGrossIncome());
					userIncomeDTO.setUserId(incomeDO.getUserLogin().getUserId());
					userIncomeDTO.setUserNetIncome(incomeDO.getUserNetIncome());
					userIncomeDTO.setUserIncomeId(incomeDO.getUserIncomeId());
					incomeDTO.add(userIncomeDTO);
				}
			}
			dto.setUserIncomeDTO(incomeDTO);
			List<UserLoanDTO> loanDTO = new ArrayList<UserLoanDTO>();
			for (UserLoanDO loanDO : do1.getUserLogin().getUserLoans()) {
				UserLoanDTO userLoanDTO = new UserLoanDTO();
				if (loanDO.getActiveStatus() > 0) {
					userLoanDTO.setActiveStatus(loanDO.getActiveStatus());
					userLoanDTO.setLoanTypeDesc(loanDO.getRefLoanType().getLoanTypeDesc());
					userLoanDTO.setUserId(loanDO.getUserLogin().getUserId());
					userLoanDTO.setUserLenderName(loanDO.getUserLenderName());
					userLoanDTO.setUserLoanOutstandingPrincipal(loanDO.getUserLoanOutstandingPrincipal());
					userLoanDTO.setUserLoanPaymentAmount(loanDO.getUserLoanPaymentAmount());
					userLoanDTO.setUserLoanRemainingTenure(loanDO.getUserLoanRemainingTenure());
					userLoanDTO.setUserLoansId(loanDO.getUserLoansId());
					RefLoanLenderDO loanLenderDO = (RefLoanLenderDO) findById(RefLoanLenderDO.class,
							loanDO.getUserLenderName());
					if (loanLenderDO != null) {
						userLoanDTO.setLoanLender(loanLenderDO.getLoanLenderName());
					}
					loanDTO.add(userLoanDTO);
				}
			}
			dto.setUserLoanDTO(loanDTO);
			List<UserEmploymentDTO> employmentDTO = new ArrayList<UserEmploymentDTO>();
			for (UserEmploymentDO employmentDO : do1.getUserLogin().getUserEmployments()) {
				UserEmploymentDTO userEmploymentDTO = new UserEmploymentDTO();
				if (employmentDO.getActiveStatus() > 0) {
					userEmploymentDTO.setOccupationDesc(employmentDO.getOccupationType().getOccupationType());
					employmentDTO.add(userEmploymentDTO);
				}
			}
			dto.setUserEmploymentDTO(employmentDTO);
			LenderDO lenderDO = null;
			Object object = getCurrentSession().getNamedQuery("LenderDO.getLenderName")
					.setParameter("lenderId", lenderId).uniqueResult();
			if (object != null) {
				lenderDO = (LenderDO) object;
			}
			dto.setLenderName(lenderDO.getLenderName());
			finalList.add(dto);
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getLenderNames(int)
	 */
	@Override
	public LenderDO getLenderNames(int lenderId) {
		LenderDO lenderDO = null;
		Object object = getCurrentSession().getNamedQuery("LenderDO.getLenderName").setParameter("lenderId", lenderId)
				.uniqueResult();
		if (object != null) {
			lenderDO = (LenderDO) object;
		}
		return lenderDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getBuilderNameUsingUserId(int)
	 */
	@Override
	public BuilderUserDO getBuilderNameUsingUserId(int userId) {
		BuilderUserDO builderUserDO = null;
		Object object = getCurrentSession().getNamedQuery("BuilderUserDO.getBuilderNameAndId")
				.setParameter("userId", userId).uniqueResult();
		if (object != null) {
			builderUserDO = (BuilderUserDO) object;
		}
		return builderUserDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getLenderId(int)
	 */
	@Override
	public LenderUserDO getLenderId(int userId) {
		LenderUserDO lenderUserDO = null;
		Object object = getCurrentSession().getNamedQuery("LenderUserDO.getLenderUserDetails")
				.setParameter("userId", userId).uniqueResult();
		if (object != null) {
			lenderUserDO = (LenderUserDO) object;
		}
		return lenderUserDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserCoApplicantFinancials(int)
	 */
	@Override
	public boolean updateUserCoApplicantFinancials(int userId) {
		boolean isUpdated = false;
		List<UserLoginDO> list = getUserLoginForCoApplicant(userId);
		if (list != null) {
			for (UserLoginDO loginDO : list) {
				int user = loginDO.getUserId();
				final UserFinancialDTO financialDTO = new UserFinancialDTO();
				String query = "select (select sum(userNetIncome) from user_income where userId=" + user
						+ " and activeStatus=1) as netIncome, (select sum(userLoanPaymentAmount)  "
						+ " from user_loans where userId=" + user + " and activeStatus=1) as emi,"
						+ " (select sum(userLoanOutstandingPrincipal) from user_loans where userId=" + user
						+ " and activeStatus=1) as loanObl,(select assetValue from asset_financing ast "
						+ " where userId=" + user
						+ " and activeStatus=1) as assetValue,(select  loanValue from asset_financing ast "
						+ " where userId=" + user
						+ " and activeStatus=1) as loanValue, (select  loanTermId from asset_financing ast "
						+ " where userId=" + user
						+ " and activeStatus=1) as loanTerm, (select preferredInterestRate from asset_financing ast where userId="
						+ user
						+ " and activeStatus=1) as preferredInterestRate,(SELECT creditScoreId FROM user_employment "
						+ " where userId=" + user + " and activeStatus=1) as creditScore ";
				jdbcTemplate.query(query, new ResultSetExtractor<String>() {

					@Override
					public String extractData(ResultSet rs) throws SQLException, DataAccessException {
						while (rs.next()) {
							financialDTO.setIncomeCredits(rs.getBigDecimal("netIncome"));
							financialDTO.setEmiPermissible(rs.getBigDecimal("emi"));
							financialDTO.setLoanObligations(rs.getBigDecimal("loanObl"));
							financialDTO.setAssetValue(
									(rs.getBigDecimal("assetValue") != null) ? rs.getBigDecimal("assetValue")
											: BigDecimal.ZERO);
							financialDTO.setLoanValue(
									(rs.getBigDecimal("loanValue") != null) ? rs.getBigDecimal("loanValue")
											: BigDecimal.ZERO);
							financialDTO.setLoanTerm(rs.getInt("loanTerm"));
							financialDTO.setPreferredInterestRate((rs.getBigDecimal("preferredInterestRate") != null)
									? rs.getBigDecimal("preferredInterestRate")
									: BigDecimal.ZERO);
							financialDTO.setCreditScoreId(rs.getInt("creditScore"));
						}
						return null;
					}
				});
				UserFinancialDO financialDO = null;
				Object obj = getCurrentSession().getNamedQuery("UserFinancialDO.getFinancialUsingUserId1")
						.setParameter("user", user).uniqueResult();
				if (obj != null) {
					financialDO = (UserFinancialDO) obj;
				} else {
					financialDO = new UserFinancialDO();
					financialDO.setCreatedOn(UtilityMethods.getCurrentDateTime());
					financialDO.setCreatedBy(loginDO.getUserEmail());
					financialDO.setUpdatedOn(UtilityMethods.getCurrentDateTime());
					financialDO.setUpdatedBy(loginDO.getUserEmail());
				}
				financialDO.setActiveStatus(1);
				financialDO.setCreditScoreId(financialDTO.getCreditScoreId());
				if (financialDTO.getEmiPermissible() != null) {
					financialDO.setEmiPermissible(financialDTO.getEmiPermissible());
				} else {
					financialDO.setEmiPermissible(BigDecimal.ZERO);
				}

				if (financialDTO.getIncomeCredits() != null) {
					financialDO.setIncomeCredits(financialDTO.getIncomeCredits());
				} else {
					financialDO.setIncomeCredits(BigDecimal.ZERO);
				}
				if (financialDTO.getLoanObligations() != null) {
					financialDO.setLoanObligations(financialDTO.getLoanObligations());
				} else {
					financialDO.setLoanObligations(BigDecimal.ZERO);
				}

				if (financialDTO.getLoanTerm() > 0) {
					financialDO.setLoanTerm(financialDTO.getLoanTerm());
				}
				if (financialDTO.getAssetValue() != null) {
					financialDO.setAssetValue(financialDTO.getAssetValue());
				} else {
					financialDO.setAssetValue(BigDecimal.ZERO);
				}
				if (financialDTO.getLoanValue() != null) {
					financialDO.setLoanValue(financialDTO.getLoanValue());
				} else {
					financialDO.setLoanValue(BigDecimal.ZERO);
				}
				if (financialDTO.getPreferredInterestRate().compareTo(BigDecimal.ZERO) > 0) {
					financialDO.setPreferredInterestRate(
							(financialDTO.getPreferredInterestRate() != null) ? financialDTO.getPreferredInterestRate()
									: BigDecimal.ZERO);
				}
				financialDO.setUserLogin(loginDO);
				if (financialDTO.getLpi() == null) {
					financialDO.setLpi(BigDecimal.ZERO);
				}
				save(financialDO);
				if (financialDO.getUserFinancialsId() > 0) {
					isUpdated = true;
				}
			}
			return isUpdated;
		}
		return isUpdated;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfEmployerNameUsingDistinct()
	 */
	@Override
	public List<EmployerDTO> getListOfEmployerNameUsingDistinct() {
		List<EmployerDO> list = getCurrentSession().getNamedQuery("EmployerDO.getEmployerDetailsUsingDistinct").list();
		List<EmployerDTO> finalList = new ArrayList<EmployerDTO>();
		if (list != null) {
			for (EmployerDO do1 : list) {
				EmployerDTO dto = new EmployerDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setEmployerName(do1.getEmployerName());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setIndustrySegmentId(do1.getIndustrySector().getIndustrySegmentId());
				dto.setIndustrySegment(do1.getIndustrySector().getIndustrySegmentName());
				dto.setScore(do1.getScore());
				dto.setReferenceCategoryId(do1.getReferenceCategory().getReferenceCategoryId());
				dto.setEmployerId(do1.getEmployerId());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateCoApplicantuserFinancial(int)
	 */
	@Override
	public boolean updateCoApplicantuserFinancial(int userId) {
		String CoApplicantUserFinancial = "update user_financials set activeStatus=0 where userId in (select userId from user_login where activeStatus=1 and primaryUserId="
				+ userId + ") and activeStatus=1";
		int updateCoApplicantUserFinancial = jdbcTemplate.update(CoApplicantUserFinancial);
		if (updateCoApplicantUserFinancial > 0) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getSessionManagement(int)
	 */
	@Override
	public SessionManagement getSessionManagement(int userId) {
		SessionManagement management = new SessionManagement();
		if (userId > 0) {
			UserDataDTO dataDTO = getUserInfoByUserId(userId);
			if (dataDTO != null) {
				management.setUserDataDTO(dataDTO);
			}
			UserEmploymentDTO employmentDTO = getUserEmploymentByuserId(userId);
			if (employmentDTO != null) {
				management.setUserEmploymentDTO(employmentDTO);
			}
			List<UserIncomeDTO> userIncomeDTO = getUserIncomelistById(userId);
			if (userIncomeDTO != null) {
				management.setUserIncomeDTO(userIncomeDTO);
			}
			List<UserLoanDTO> userLoanDTO = getUserloanByUserId(userId);
			if (userLoanDTO != null) {
				management.setUserLoanDTO(userLoanDTO);
			}
			List<UserSalaryDeductionDTO> userSalaryDeductionDTO = getUserSalaryDeductionByUserId(userId);
			if (userSalaryDeductionDTO != null) {
				management.setUserSalaryDeductionDTO(userSalaryDeductionDTO);
			}
			AssetDTO assetDTO = builderDataFetchDAO.getListOfAssetUsingByUserId(userId);
			if (assetDTO != null) {
				management.setAssetDTO(assetDTO);
			}
			AssetFinancingDTO assetFinancingDTO = builderDataFetchDAO.getAssetFinancingDetailsUsingUserId(userId);
			if (assetFinancingDTO != null) {
				management.setAssetFinancingDTO(assetFinancingDTO);
			}

		}
		return management;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserAssetFinancingSession(int)
	 */
	public void updateUserAssetFinancingSession(int userId) {
		SessionManagement sm = ValuCirclesConstants.userSession.get(userId);
		if (sm != null) {
			AssetFinancingDTO assetFinancingDTO = builderDataFetchDAO.getAssetFinancingDetailsUsingUserId(userId);
			if (assetFinancingDTO != null) {
				sm.setAssetFinancingDTO(assetFinancingDTO);
			}
			ValuCirclesConstants.userSession.put(userId, sm);
		}
	}

	/**
	 * @param userId
	 */
	public void updateUserAssetSession(int userId) {
		SessionManagement sm = ValuCirclesConstants.userSession.get(userId);
		if (sm != null) {
			AssetDTO assetDTO = builderDataFetchDAO.getListOfAssetUsingByUserId(userId);
			if (assetDTO != null) {
				sm.setAssetDTO(assetDTO);
			}
			ValuCirclesConstants.userSession.put(userId, sm);
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserLoanSession(int)
	 */
	public void updateUserLoanSession(int userId) {
		SessionManagement sm = ValuCirclesConstants.userSession.get(userId);
		if (sm != null) {
			List<UserLoanDTO> userLoanDTO = getUserloanByUserId(userId);
			if (userLoanDTO != null) {
				sm.setUserLoanDTO(userLoanDTO);
			}
			ValuCirclesConstants.userSession.put(userId, sm);
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserIncomeSession(int)
	 */
	public void updateUserIncomeSession(int userId) {
		SessionManagement sm = ValuCirclesConstants.userSession.get(userId);
		if (sm != null) {
			List<UserIncomeDTO> userIncomeDTO = getUserIncomelistById(userId);
			if (userIncomeDTO != null) {
				sm.setUserIncomeDTO(userIncomeDTO);
			}
			ValuCirclesConstants.userSession.put(userId, sm);
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserEmploymentSession(int)
	 */
	public void updateUserEmploymentSession(int userId) {
		SessionManagement sm = ValuCirclesConstants.userSession.get(userId);
		if (sm != null) {
			UserEmploymentDTO employmentDTO = getUserEmploymentByuserId(userId);
			if (employmentDTO != null) {
				sm.setUserEmploymentDTO(employmentDTO);
			}
			ValuCirclesConstants.userSession.put(userId, sm);
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserInfoSession(int)
	 */
	public void updateUserInfoSession(int userId) {
		SessionManagement sm = ValuCirclesConstants.userSession.get(userId);
		if (sm != null) {
			UserDataDTO dataDTO = getUserInfoByUserId(userId);
			if (dataDTO != null) {
				sm.setUserDataDTO(dataDTO);
			}
			ValuCirclesConstants.userSession.put(userId, sm);
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserSessionManagement(int)
	 */
	@Override
	public UserSession getUserSessionManagement(int userId) {
		UserSession session = new UserSession();
		SessionManagement management = getSessionManagement(userId);
		if (management != null) {
			session.setSessionManagement(ValuCirclesConstants.userSession.put(userId, management));
		}
		return session;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getCoApplicantIncomeCredits(int)
	 */
	@Override
	public String getCoApplicantIncomeCredits(int userId) {
		try {
			String query = "select sum(incomeCredits) from user_financials where userId in (select userId from user_login where primaryUserId="
					+ userId + " and activeStatus=1) and activeStatus=1";
			return jdbcTemplate.queryForObject(query, String.class);
		} catch (EmptyResultDataAccessException e) {
			return "";
		}

	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfLpiStatus()
	 */
	@Override
	public List<LenderLpiStatusDTO> getListOfLpiStatus() {
		List<RefLenderLpiStatusDO> list = getCurrentSession().getNamedQuery("RefLenderLpiStatusDO.findAll").list();
		List<LenderLpiStatusDTO> finalList = new ArrayList<LenderLpiStatusDTO>();
		if (list != null) {
			for (RefLenderLpiStatusDO do1 : list) {
				LenderLpiStatusDTO dto = new LenderLpiStatusDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setLenderLpiStatusId(do1.getLenderLpiStatusId());
				dto.setLenderLpiStatus(do1.getLenderLpiStatus());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserLpiLenderId(int, int)
	 */
	@Override
	public UserLpiLenderDO getUserLpiLenderId(int userId, int lenderId) {
		UserLpiLenderDO userLpiLenderDO = null;
		Object object = getCurrentSession().getNamedQuery("UserLpiLenderDO.getUserLpiLenderId")
				.setParameter("userId", userId).setParameter("lenderId", lenderId).uniqueResult();
		if (object != null) {
			userLpiLenderDO = (UserLpiLenderDO) object;
		}
		return userLpiLenderDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getLenderLpiStatusAndComments(int, int)
	 */
	@Override
	public List<LenderLpiStatusAndCommentsDTO> getLenderLpiStatusAndComments(int userId, int lenderId) {
		UserLpiLenderDO userLpiLenderDO = (UserLpiLenderDO) getCurrentSession()
				.getNamedQuery("UserLpiLenderDO.getUserLpiLenderId").setParameter("userId", userId)
				.setParameter("lenderId", lenderId).uniqueResult();
		List<LenderLpiStatusAndCommentsDTO> finalList = new ArrayList<LenderLpiStatusAndCommentsDTO>();
		if (userLpiLenderDO != null) {
			int userLpiLenderId = userLpiLenderDO.getUserLPiLenderId();
			List<UserLendersStatusDO> list = getCurrentSession()
					.getNamedQuery("UserLendersStatusDO.getStatusAndComments")
					.setParameter("userLpiLenderId", userLpiLenderId).list();

			if (list != null) {
				for (UserLendersStatusDO do1 : list) {
					LenderLpiStatusAndCommentsDTO dto = new LenderLpiStatusAndCommentsDTO();
					dto.setActiveStatus(do1.getActiveStatus());
					dto.setCreatedBy(do1.getCreatedBy());
					dto.setCreatedOn(do1.getCreatedOn());
					dto.setUpdatedBy(do1.getUpdatedBy());
					dto.setUpdatedOn(do1.getUpdatedOn());
					dto.setStatus(do1.getRefLenderLpiStatus().getLenderLpiStatus());
					UserLenderCommentDO userLenderCommentDO = (UserLenderCommentDO) findById(UserLenderCommentDO.class,
							do1.getLenderCommentId());
					if (userLenderCommentDO != null) {
						dto.setLenderCommentId(userLenderCommentDO.getLenderLpiCommentsId());
						dto.setComments(userLenderCommentDO.getComments());
					}
					dto.setUserLPiLenderId(do1.getUserLpiLender().getUserLPiLenderId());
					dto.setUserLenderStatusId(do1.getUserLenderStatusId());
					finalList.add(dto);
				}
				return finalList;
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfRefEmploymentType()
	 */
	@Override
	public List<RefEmploymentTypeDTO> getListOfRefEmploymentType() {
		List<RefEmployerTypeDO> list = null;
		if (ValuCirclesConstants.refEmployerTypeCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.refEmployerTypeCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefEmployerTypeDO.findAll").list();
		}
		List<RefEmploymentTypeDTO> finalList = new ArrayList<RefEmploymentTypeDTO>();
		if (list != null) {
			for (RefEmployerTypeDO do1 : list) {
				RefEmploymentTypeDTO dto = new RefEmploymentTypeDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setUpdatedBy(do1.getUpdatedBy());
				dto.setUpdatedOn(do1.getUpdatedOn());
				dto.setEmployerTypeId(do1.getEmployerTypeId());
				dto.setEmployerTypeDesc(do1.getEmployerTypeDesc());
				dto.setLenderId(do1.getLender().getLenderId());
				dto.setReferenceCategoryId(do1.getReferenceCategory().getReferenceCategoryId());
				dto.setRefnum(do1.getRefnum());
				dto.setScore(do1.getScore());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfRefRelationShip()
	 */
	@Override
	public List<RelationShipDTO> getListOfRefRelationShip() {
		List<RefApplicantRelationshipDO> list = null;
		if (ValuCirclesConstants.relationShipCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.relationShipCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefApplicantRelationshipDO.findAll").list();
		}
		List<RelationShipDTO> finalList = new ArrayList<RelationShipDTO>();
		if (list != null) {
			for (RefApplicantRelationshipDO do1 : list) {
				RelationShipDTO dto = new RelationShipDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setRelationship(do1.getRelationship());
				dto.setRelationshipID(do1.getRelationshipID());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateLoginUserLpiStatus(int)
	 */
	@Override
	public boolean updateLoginUserLpiStatus(int userId) {
		int isUpdated = getCurrentSession().getNamedQuery("UserLoginDO.updateLoginUserLpiStatus")
				.setParameter("userId", userId).executeUpdate();
		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}

	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getListOfRefSalaryDeduction()
	 */
	@Override
	public List<RefSalaryDeductionDTO> getListOfRefSalaryDeduction() {
		List<RefSalaryDeductionDO> list = null;
		if (ValuCirclesConstants.salaryDeductionCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.salaryDeductionCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefSalaryDeductionDO.findAll").list();
		}
		List<RefSalaryDeductionDTO> finalList = new ArrayList<RefSalaryDeductionDTO>();
		if (list != null) {
			for (RefSalaryDeductionDO do1 : list) {
				RefSalaryDeductionDTO dto = new RefSalaryDeductionDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setDednTypeDesc(do1.getDednTypeDesc());
				dto.setRefnum(do1.getRefnum());
				dto.setSalaryDednTypeId(do1.getSalaryDednTypeId());
				dto.setScore(do1.getScore());
				dto.setWeightPct(do1.getWeightPct());
				finalList.add(dto);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserSalaryDeduction(int)
	 */
	@Override
	public boolean updateUserSalaryDeduction(int userId) {
		int isUpdated = getCurrentSession().getNamedQuery("UserSalaryDeductionDO.updateUserSalaryDeduction")
				.setParameter("userId", userId).executeUpdate();
		if (isUpdated > 0) {
			return true;

		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserSalaryDeductionByUserId(int)
	 */
	@Override
	public List<UserSalaryDeductionDTO> getUserSalaryDeductionByUserId(int userId) {
		List<UserSalaryDeductionDO> list = getCurrentSession()
				.getNamedQuery("UserSalaryDeductionDO.getUserSalaryDeductionByUserId").setParameter("userId", userId)
				.list();
		List<UserSalaryDeductionDTO> finalList = new ArrayList<>();
		if (list != null) {
			for (UserSalaryDeductionDO salaryDO : list) {
				UserSalaryDeductionDTO salaryDTO = new UserSalaryDeductionDTO();
				salaryDTO.setActiveStatus(salaryDO.getActiveStatus());
				salaryDTO.setCreatedBy(salaryDO.getCreatedBy());
				salaryDTO.setCreatedOn(salaryDO.getCreatedOn());
				salaryDTO.setUpdatedBy(salaryDO.getUpdatedBy());
				salaryDTO.setUpdatedOn(salaryDO.getUpdatedOn());
				salaryDTO.setUserId(salaryDO.getUserLogin().getUserId());
				salaryDTO.setAmount(salaryDO.getAmount());
				salaryDTO.setUsersaldednsId(salaryDO.getUsersaldednsId());
				salaryDTO.setSalaryDednTypeId(salaryDO.getRefSalaryDeduction().getSalaryDednTypeId());
				salaryDTO.setSalaryDednType(salaryDO.getRefSalaryDeduction().getDednTypeDesc());
				finalList.add(salaryDTO);
			}
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserSalaryDeductionByUserId(int)
	 */
	public void updateUserSalaryDeductionByUserId(int userId) {
		SessionManagement sm = ValuCirclesConstants.userSession.get(userId);
		if (sm != null) {
			List<UserSalaryDeductionDTO> userSalaryDeductionDTO = getUserSalaryDeductionByUserId(userId);
			if (userSalaryDeductionDTO != null) {
				sm.setUserSalaryDeductionDTO(userSalaryDeductionDTO);
			}
			ValuCirclesConstants.userSession.put(userId, sm);
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateCoApplicantSalaryDeduction(int)
	 */
	@Override
	public boolean updateCoApplicantSalaryDeduction(int userId) {
		String CoApplicantsalary = "update user_salary_deductions set activeStatus=0 where userId in (select userId from user_login where activeStatus=1 and primaryUserId="
				+ userId + ") and activeStatus=1";
		int updatesalary = jdbcTemplate.update(CoApplicantsalary);
		if (updatesalary > 0) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getSubscriptionSponsor(java.lang.String)
	 */
	@Override
	public Map<String, Object> getSubscriptionSponsor(final String email) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter("email", Types.VARCHAR));

		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{call getSubscriptionSponsors(?)}");
				callableStatement.setString(1, email);
				return callableStatement;
			}

		}, paramList);
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#firstTimeLoginCheck(java.lang.String)
	 */
	@Override
	public int firstTimeLoginCheck(String emailId) {
		long count = (long) getCurrentSession().getNamedQuery("UserActivityDO.getCountLogin")
				.setParameter("emailId", emailId).uniqueResult();
		return (int) count;		
	}
	
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#selectedSubscription(int)
	 */
	@Override
	public SubscriptionDetailDO selectedSubscription(int subId) {
		SubscriptionDetailDO subDetailDO = (SubscriptionDetailDO) getCurrentSession()
				.getNamedQuery("SubscriptionDetailDO.getSubDetailById").setParameter("subId", subId).uniqueResult();

		return subDetailDO;
	}
	
	

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getAssetProductType()
	 */
	@Override
	public List<RefProductTypeDTO> getAssetProductType() {
		List<RefProductTypeDO> list = null;
		if (ValuCirclesConstants.productTypeCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.productTypeCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefProductTypeDO.findAll").list();
		}
		List<RefProductTypeDTO> finalList = new ArrayList<RefProductTypeDTO>();
		if (list != null) {
			for (RefProductTypeDO do1 : list) {
				RefProductTypeDTO dto = new RefProductTypeDTO();
				dto.setActiveStatus(do1.getActiveStatus());
				dto.setCreatedBy(do1.getCreatedBy());
				dto.setCreatedOn(do1.getCreatedOn());
				dto.setLenderId(do1.getLenderId());
				dto.setProductDesc(do1.getProductDesc());
				dto.setProductTypeId(do1.getProductTypeId());
				dto.setReferenceCategoryId(do1.getReferenceCategoryId());
				dto.setRefnum(do1.getRefnum());
				dto.setScore(do1.getScore());
				dto.setWeightPct(do1.getWeightPct());
				finalList.add(dto);
			}
		}
		return finalList;
	}
	
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getRefloantimeframe()
	 */
	@Override
	public String getRefloantimeframe() {
		List<RefLoanTimeFrameDO> list = null;		
		list = getCurrentSession().getNamedQuery("RefLoanTimeFrameDO.findAll").list();
	
		List<RefLoanTimeFrameDTO> finalList = new ArrayList<RefLoanTimeFrameDTO>();
		if (list != null) {
			for (RefLoanTimeFrameDO do1 : list) {
				RefLoanTimeFrameDTO dto = new RefLoanTimeFrameDTO();
				dto.setId(do1.getId());
				dto.setTimeframe_desc(do1.getTimeframe_desc());
				finalList.add(dto);
			}
		}
		return finalList.toString();
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#lsitOfAllSubscriptionType()
	 */
	@Override
	public List<RefSubscriptionTypeDTO> lsitOfAllSubscriptionType() {
		List<RefSubscriptionDO> list = null;
		if (ValuCirclesConstants.subscriptionTypeCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.subscriptionTypeCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefSubscriptionDO.getAllSubscriptionType").list();
		}
		List<RefSubscriptionTypeDTO> finalList = new ArrayList<RefSubscriptionTypeDTO>();
		if (list != null) {
			for (RefSubscriptionDO subDo : list) {
				RefSubscriptionTypeDTO dto = new RefSubscriptionTypeDTO();
				dto.setSubscriptionId(subDo.getRefsubscriptionId());
				dto.setSubscriptionTypeId(subDo.getSubscriptionTypeId());
				dto.setSubscriptionType(subDo.getSubscriptionType());
				finalList.add(dto);
			}
		} else {
			return null;
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getAssetType(int)
	 */
	@Override
	public List<RefAssetTypeDTO> getAssetType(int userId) {
		List<RefAssetTypeDO> list = null;
		if (ValuCirclesConstants.assetTypeCache.size() > 0) {
			list = new ArrayList<>(ValuCirclesConstants.assetTypeCache.values());
		} else {
			list = getCurrentSession().getNamedQuery("RefAssetTypeDO.getAssetType").list();
		}
		List<RefAssetTypeDTO> finalList = new ArrayList<RefAssetTypeDTO>();
		if (list != null) {
			for (RefAssetTypeDO subDo : list) {
				RefAssetTypeDTO dto = new RefAssetTypeDTO();
				dto.setActiveStatus(subDo.getActiveStatus());
				dto.setAssetType(subDo.getAssetType());
				dto.setAssetTypeId(subDo.getAssetTypeId());
				dto.setCreatedBy(subDo.getCreatedBy());
				dto.setCreatedOn(subDo.getCreatedOn());
				dto.setUpdatedBy(subDo.getUpdatedBy());
				dto.setUpdatedOn(subDo.getUpdatedOn());
				finalList.add(dto);
			}
		} else {
			return null;
		}
		return finalList;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#selectedSubscriptionSponsor(int, int)
	 */
	@Override
	public Map<String, Object> selectedSubscriptionSponsor(final int userId, final int subId) {
		List paramList = new ArrayList();
		paramList.add(new SqlParameter("userid", Types.INTEGER));
		paramList.add(new SqlParameter("subdetaildId", Types.INTEGER));
		paramList.add(new SqlParameter("debug", Types.INTEGER));
		paramList.add(new SqlOutParameter("retval", Types.INTEGER));
		final int debug = 0;
		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{call choose_subscription_for_user(?,?,?,?)}");
				callableStatement.setInt(1, userId);
				callableStatement.setInt(2, subId);
				callableStatement.setInt(3, debug);
				callableStatement.registerOutParameter(4, Types.INTEGER);
				return callableStatement;
			}

		}, paramList);
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getSubRates(int)
	 */
	@Override
	public Map<String, Object> getSubRates(final int userId) {
		List paramList = new ArrayList();
		paramList.add(new SqlParameter("myuserId", Types.INTEGER));
		paramList.add(new SqlParameter("debug", Types.INTEGER));
		final int debug = 0;
		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{call getSubscriptionRates(?,?)}");
				callableStatement.setInt(1, userId);
				callableStatement.setInt(2, debug);
				return callableStatement;
			}

		}, paramList);
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserInfoDetailsForBuilders(int)
	 */
	@Override
	public Map<String, Object> getUserInfoDetailsForBuilders(final int userId) {
		List paramList = new ArrayList();
		paramList.add(new SqlParameter("myuserId", Types.INTEGER));
		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection.prepareCall("{call getuserInfoForBuilders(?)}");
				callableStatement.setInt(1, userId);
				return callableStatement;
			}

		}, paramList);
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#insertUserDetailsForCreditScore(java.lang.String, int)
	 */
	@Override
	public Map<String, Object> insertUserDetailsForCreditScore(String userInfo, final int score) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resultMap = null;
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		try {
			final CreditScoreDataDTO dto = mapper.readValue(userInfo, CreditScoreDataDTO.class);
			paramList.add(new SqlParameter("myuserId", Types.VARCHAR));
			paramList.add(new SqlParameter("myaadharCard", Types.VARCHAR));
			paramList.add(new SqlParameter("myPAN", Types.VARCHAR));
			paramList.add(new SqlParameter("myfatherFirstName", Types.VARCHAR));
			paramList.add(new SqlParameter("myfatherLastName", Types.VARCHAR));
			paramList.add(new SqlParameter("myspouseFirstName", Types.VARCHAR));
			paramList.add(new SqlParameter("myspouseLastName", Types.VARCHAR));
			paramList.add(new SqlParameter("myDL", Types.VARCHAR));
			paramList.add(new SqlParameter("myvoterId", Types.VARCHAR));
			paramList.add(new SqlParameter("myUID", Types.VARCHAR));
			paramList.add(new SqlParameter("myrationCard", Types.VARCHAR));
			paramList.add(new SqlParameter("mypassport", Types.VARCHAR));
			paramList.add(new SqlParameter("myotherIdName1", Types.VARCHAR));
			paramList.add(new SqlParameter("otherIdValue1", Types.VARCHAR));
			paramList.add(new SqlParameter("myotherIDName2", Types.VARCHAR));
			paramList.add(new SqlParameter("myotherIdValue2", Types.VARCHAR));
			paramList.add(new SqlParameter("mycreditScore", Types.INTEGER));
			paramList.add(new SqlOutParameter("retval", Types.INTEGER));
			int userId = Integer.parseInt(dto.getMyuserId());
			String mail = selectuserEmail(userId);
			final String a = mail + dto.getMyuserId();
			resultMap = jdbcTemplate.call(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection
							.prepareCall("{call insert_user_creditinfo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
					try {
						callableStatement.setString(1, dto.getMyuserId());

						callableStatement.setString(2,
								UtilityMethods.encrypt(dto.getMyaadharCard(), a.hashCode() + ""));
						callableStatement.setString(3, UtilityMethods.encrypt(dto.getMyPAN(), a.hashCode() + ""));
						callableStatement.setString(4,
								UtilityMethods.encrypt(dto.getMyfatherFirstName(), a.hashCode() + ""));
						callableStatement.setString(5,
								UtilityMethods.encrypt(dto.getMyfatherLastName(), a.hashCode() + ""));
						callableStatement.setString(6,
								UtilityMethods.encrypt(dto.getMyspouseFirstName(), a.hashCode() + ""));
						callableStatement.setString(7,
								UtilityMethods.encrypt(dto.getMyspouseLastName(), a.hashCode() + ""));
						callableStatement.setString(8, "");
						callableStatement.setString(9, UtilityMethods.encrypt(dto.getMyvoterId(), a.hashCode() + ""));
						callableStatement.setString(10, "");
						callableStatement.setString(11, "");
						callableStatement.setString(12, UtilityMethods.encrypt(dto.getMypassport(), a.hashCode() + ""));
						callableStatement.setString(13,
								UtilityMethods.encrypt(dto.getMyotherIdName1(), a.hashCode() + ""));
						callableStatement.setString(14,
								UtilityMethods.encrypt(dto.getOtherIdValue1(), a.hashCode() + ""));
						callableStatement.setString(15,
								UtilityMethods.encrypt(dto.getMyotherIDName2(), a.hashCode() + ""));
						callableStatement.setString(16,
								UtilityMethods.encrypt(dto.getMyotherIdValue2(), a.hashCode() + ""));
						callableStatement.setInt(17, score);
						callableStatement.registerOutParameter(18, Types.INTEGER);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					updatecoappmobileandemail(dto.getMyuserId(), dto.getMobiNo(), dto.getUserMail());
					return callableStatement;
				}

			}, paramList);
			return resultMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * @param userId
	 * @param mobileNo
	 * @param Email
	 * @return isUpdted
	 */
	public boolean updatecoappmobileandemail(String userId, String mobileNo, String Email) {
		boolean isUpdated = false;
		int cpUps = jdbcTemplate.update("UPDATE user_login ul SET ul.userPhone='" + mobileNo + "',ul.userEmail ='"
				+ Email + "' WHERE ul.userId ='" + userId + "'");
		if (cpUps > 0) {
			isUpdated = true;
		}

		return isUpdated;

	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserDetailsForCreditScore(int)
	 */
	@Override
	public String getUserDetailsForCreditScore(final int userId) {
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		final String uId = String.valueOf(userId);
		paramList.add(new SqlParameter("myuserId", Types.VARCHAR));
		paramList.add(new SqlOutParameter("myaadharCard", Types.VARCHAR));
		paramList.add(new SqlOutParameter("myPAN", Types.VARCHAR));
		paramList.add(new SqlOutParameter("myfatherFirstName", Types.VARCHAR));
		paramList.add(new SqlOutParameter("myfatherLastName", Types.VARCHAR));
		paramList.add(new SqlOutParameter("myspouseFirstName", Types.VARCHAR));
		paramList.add(new SqlOutParameter("myspouseLastName", Types.VARCHAR));
		paramList.add(new SqlOutParameter("myvoterId", Types.VARCHAR));
		paramList.add(new SqlOutParameter("mypassport", Types.VARCHAR));
		paramList.add(new SqlOutParameter("myotherIdName1", Types.VARCHAR));
		paramList.add(new SqlOutParameter("otherIdValue1", Types.VARCHAR));
		paramList.add(new SqlOutParameter("myotherIDName2", Types.VARCHAR));
		paramList.add(new SqlOutParameter("myotherIdValue2", Types.VARCHAR));
		paramList.add(new SqlOutParameter("mycreditScore", Types.INTEGER));

		String mail = selectuserEmail(userId);
		final String a = mail + userId;		
		final List<CreditScoreDataDTO> finalList = new ArrayList<>();
		String query = "SELECT * FROM user_credit_info cr "
				+ " inner join user_login u on u.userId=cr.userId  where cr.userId='" + uId + "'";

		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					CreditScoreDataDTO dto = new CreditScoreDataDTO();
					try {

						dto.setMyuserId(uId);

						if (rs.getString("aadharCard") == "") {
							dto.setMyaadharCard(rs.getString("aadharCard"));
						} else {
							dto.setMyaadharCard(UtilityMethods.decrypt(rs.getString("aadharCard"), a.hashCode() + ""));
						}
						if (rs.getString("PAN") == "") {
							dto.setMyPAN(rs.getString("PAN"));
						} else {
							dto.setMyPAN(UtilityMethods.decrypt(rs.getString("PAN"), a.hashCode() + ""));
						}
						if (rs.getString("fatherFirstName") == "") {
							dto.setMyfatherFirstName(rs.getString("fatherFirstName"));
						} else {
							dto.setMyfatherFirstName(
									UtilityMethods.decrypt(rs.getString("fatherFirstName"), a.hashCode() + ""));
						}
						if (rs.getString("fatherLastName") == "") {
							dto.setMyfatherLastName(rs.getString("fatherLastName"));
						} else {
							dto.setMyfatherLastName(
									UtilityMethods.decrypt(rs.getString("fatherLastName"), a.hashCode() + ""));
						}
						if (rs.getString("spouseFirstName") == "") {
							dto.setMyspouseFirstName(rs.getString("spouseFirstName"));
						} else {
							dto.setMyspouseFirstName(
									UtilityMethods.decrypt(rs.getString("spouseFirstName"), a.hashCode() + ""));
						}
						if (rs.getString("spouseLastName") == "") {
							dto.setMyspouseLastName(rs.getString("spouseLastName"));
						} else {
							dto.setMyspouseLastName(
									UtilityMethods.decrypt(rs.getString("spouseLastName"), a.hashCode() + ""));
						}
						if (rs.getString("voterId") == "") {
							dto.setMyvoterId(rs.getString("voterId"));
						} else {
							dto.setMyvoterId(UtilityMethods.decrypt(rs.getString("voterId"), a.hashCode() + ""));
						}						
						if (rs.getString("passport") == "") {
							dto.setMypassport(rs.getString("passport"));
						} else {
							dto.setMypassport(UtilityMethods.decrypt(rs.getString("passport"), a.hashCode() + ""));
						}
						if (rs.getString("otherIdName1") == "") {
							dto.setMyotherIdName1(rs.getString("otherIdName1"));
						} else {
							dto.setMyotherIdName1(
									UtilityMethods.decrypt(rs.getString("otherIdName1"), a.hashCode() + ""));
						}
						if (rs.getString("otherIdValue1") == "") {
							dto.setOtherIdValue1(rs.getString("otherIdValue1"));
						} else {
							dto.setOtherIdValue1(
									UtilityMethods.decrypt(rs.getString("otherIdValue1"), a.hashCode() + ""));
						}
						if (rs.getString("otherIdName2") == "") {
							dto.setMyotherIDName2(rs.getString("otherIdName2"));
						} else {
							dto.setMyotherIDName2(
									UtilityMethods.decrypt(rs.getString("otherIdName2"), a.hashCode() + ""));
						}
						if (rs.getString("otherIdValue2") == "") {
							dto.setMyotherIdValue2(rs.getString("otherIdValue2"));
						} else {
							dto.setMyotherIdValue2(
									UtilityMethods.decrypt(rs.getString("otherIdValue2"), a.hashCode() + ""));
						}
						dto.setUserMail(rs.getString("userEmail"));
						dto.setMobiNo(rs.getString("userPhone"));
						dto.setMycreditScore(rs.getInt("creditScore"));
						finalList.add(dto);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				return null;
			}
		});

		return finalList.toString();
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getCreditReport(java.lang.String)
	 */
	@Override
	public String getCreditReport(String userInfo) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			CreditScoreDataDTO dto = mapper.readValue(userInfo, CreditScoreDataDTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#insertResponceCrif(java.lang.String, int)
	 */
	@Override
	public int insertResponceCrif(final String data, final int userId) {

		String mail = selectuserEmail(userId);
		final String a = mail + userId;

		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter("myuserId", Types.INTEGER));
		paramList.add(new SqlParameter("myresponse", Types.VARCHAR));
		paramList.add(new SqlOutParameter("retval", Types.INTEGER));
		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {

				CallableStatement callableStatement = connection
						.prepareCall("{call insert_user_creditinfo_response(?,?,?)}");
				callableStatement.setInt(1, userId);
				try {
					callableStatement.setString(2, UtilityMethods.encrypt(data, a.hashCode() + ""));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				callableStatement.registerOutParameter(3, Types.INTEGER);
				return callableStatement;
			}

		}, paramList);
		int val = (int) resultMap.get("retval");
		return val;
	}
	


	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#insertQuestions(java.lang.String, int)
	 */
	@Override
	public int insertQuestions(String Data, final int userId) {
		//Priya 27-12-2022, we need to get the question and answer from splitStringsOfStage2ForQuestions
		/*String[] str2 = null;
		if (StringUtils.contains(Data, "|")) {
			str2 = StringUtils.splitPreserveAllTokens(Data, "|");
		}
		final String question = str2[3];
		String ans[] = null;
		String ansData = str2[4];
		if (StringUtils.contains(ansData, "~")) {
			ans = StringUtils.splitPreserveAllTokens(ansData, "~");
		}
		
		final String ans1 = ans[0];
		final String ans2 = ans[1];
		final String ans3 = ans[2];
		final String ans4 = ans[3];*/
		//Get the questions and answers from UtilityMethods
		final String questions = UtilityMethods.retrieveQuestionsOfStage2WhenStatusS11(Data);
		ArrayList answersList = UtilityMethods.retrieveAnswersOfStage2WhenStatusS11(Data);
		final String ans1 = answersList.get(0).toString();
		final String ans2 = answersList.get(1).toString();
		final String ans3 = answersList.get(2).toString();
		final String ans4 = answersList.get(3).toString();

		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter("myuserId", Types.INTEGER));
		paramList.add(new SqlParameter("myQuestion", Types.VARCHAR));
		paramList.add(new SqlParameter("myAnswer1", Types.VARCHAR));
		paramList.add(new SqlParameter("myAnswer2", Types.VARCHAR));
		paramList.add(new SqlParameter("myAnswer3", Types.VARCHAR));
		paramList.add(new SqlParameter("myAnswer4", Types.VARCHAR));
		paramList.add(new SqlParameter("mycorrectAnswer", Types.VARCHAR));
		paramList.add(new SqlParameter("myuserAnswer", Types.VARCHAR));
		paramList.add(new SqlOutParameter("retval", Types.INTEGER));
		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{call insert_user_creditinfo_question(?,?,?,?,?,?,?,?,?)}");
				callableStatement.setInt(1, userId);
				//callableStatement.setString(2, question);
				callableStatement.setString(2, questions);//Modified by Priya on 27-12-2022 with questionForStage2
				callableStatement.setString(3, ans1);
				callableStatement.setString(4, ans2);
				callableStatement.setString(5, ans3);
				callableStatement.setString(6, ans4);
				callableStatement.setString(7, "");
				callableStatement.setString(8, "");
				callableStatement.registerOutParameter(9, Types.INTEGER);
				return callableStatement;
			}

		}, paramList);
		int val = (int) resultMap.get("retval");
		return val;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getMyLPICount(int, int)
	 */
	@Override
	public String getMyLPICount(final int userId, final int ans) {
		final int debug = 0;
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		paramList.add(new SqlParameter("myuser", Types.INTEGER));
		paramList.add(new SqlParameter("yesNo", Types.VARCHAR));
		paramList.add(new SqlParameter("debug", Types.VARCHAR));
		paramList.add(new SqlOutParameter("retval", Types.INTEGER));
		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection connection) throws SQLException {
				CallableStatement callableStatement = connection
						.prepareCall("{call insert_user_creditinfo_question(?,?,?,?)}");
				callableStatement.setInt(1, userId);
				callableStatement.setInt(2, ans);
				callableStatement.setInt(3, debug);
				callableStatement.registerOutParameter(4, Types.INTEGER);
				return callableStatement;
			}

		}, paramList);
		int val = (int) resultMap.get("retval");
		return "";
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserRecordByEmail(java.lang.String)
	 */
	@Override
	public UserLoginDO getUserRecordByEmail(String token) {
		UserLoginDO userLoginDO = null;
		Object object = getCurrentSession().getNamedQuery("UserLoginDO.getUnActUserByEmail")
				.setParameter("emailId", token).uniqueResult();
		if (object != null) {
			userLoginDO = (UserLoginDO) object;
		}
		return userLoginDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#activateUserAccount(java.lang.String)
	 */
	@Override
	public boolean activateUserAccount(String email) {
		boolean isUpdated = false;
		try {
			if (email != null && email.length() > 0) {
				int cpUps = jdbcTemplate
						.update("UPDATE user_login ul SET ul.activeStatus=1,ul.email_verified =1 WHERE ul.userEmail ='"
								+ email + "'");
				if (cpUps > 0) {
					isUpdated = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;

	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateOtpVerify(java.lang.String)
	 */
	@Override
	public boolean updateOtpVerify(String mobileNo) {
		boolean isUpdated = false;
		try {
			if (mobileNo != null && mobileNo.length() > 0) {
				int cpUps = jdbcTemplate
						.update("UPDATE user_login ul SET ul.otp_verified=1 WHERE ul.userPhone ='" + mobileNo + "'");
				if (cpUps > 0) {
					isUpdated = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}
//not used
	@Override
	public int userLoginDetails1(String emailId) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getUserRecordByPhone(java.lang.String, int)
	 */
	@Override
	public UserLoginDO getUserRecordByPhone(String token, int uid) {
		UserLoginDO userLoginDO = null;
		Object object = getCurrentSession().getNamedQuery("UserLoginDO.getUserByMobile").setParameter("mobileNo", token)
				.setParameter("uid", uid).uniqueResult();
		if (object != null) {
			userLoginDO = (UserLoginDO) object;
		}
		return userLoginDO;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#resetLoginAttempts(com.vc.staging.dto.UserLoginDTO)
	 */
	@Override
	public boolean resetLoginAttempts(UserLoginDTO loginDTO) {
		boolean isUpdated = false;
		try {
			int cpUps = jdbcTemplate.update("UPDATE user_login ul SET ul.loginattempts=0 WHERE ul.userEmail ='"
					+ loginDTO.getUserEmail() + "'");
			if (cpUps > 0) {
				isUpdated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;

	}
	
	
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#deleteresponselog()
	 */
	@Override
	public String deleteresponselog() {
		String isUpdated=null;
		try {
			int cpUps = jdbcTemplate.update("DELETE FROM staging.tbl_response_log where id >0 ORDER BY id ASC limit 1500");
			if (cpUps > 0) {
				 isUpdated = "success";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return  isUpdated;

	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#fetchaddress(java.lang.String)
	 */
	@Override
	public String fetchaddress(String userId) {
		// TODO Auto-generated method stub
		final List<UserAddressDTO> finalList = new ArrayList<>();
		int userId1 = Integer.parseInt(userId);
		String query = "select rf.refResidenceCategoryId,rf.residenceCategory,rz.city,rz.zip,rz.state, ua.*from user_address ua "
				+ "left join ref_zip rz on rz.zipId=ua.zipId  " + "left join user_info uf on uf.userId=ua.userId "
				+ "left join ref_residence_category rf on rf.refResidenceCategoryId=uf.residenceCategoryId where ua.userId='"
				+ userId1 + "'";
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					UserAddressDTO dto = new UserAddressDTO();
					dto.setUserAddressId(rs.getInt("userAddressId"));
					dto.setAddress1(rs.getString("address1"));
					dto.setAddress2(rs.getString("address2"));
					dto.setAddress3(rs.getString("address3"));
					dto.setCountry(rs.getString("country"));
					dto.setZipid(rs.getInt("zipid"));
					dto.setCity(rs.getString("city"));
					dto.setZip(rs.getString("zip"));
					dto.setRefResidenceCategoryId(rs.getInt("refResidenceCategoryId"));
					dto.setResidenceCategory(rs.getString("residenceCategory"));
					dto.setState(rs.getString("state"));
					finalList.add(dto);
				}
				return null;
			}
		});

		return finalList.toString();
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#selectClassificationId(java.lang.String)
	 */
	public String selectClassificationId(String name) {
		String classificationID = null;
		Object object = getCurrentSession().getNamedQuery("RefEmployerTypeDO.getclassificationId")
				.setParameter("employerTypeDesc", name).uniqueResult();
		if (object != null) {
			classificationID = (String) object;
		}
		return classificationID;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getLenderApplications(java.lang.String)
	 */
	@Override
	public Map<String, Object> getLenderApplications(final String uid) {
		Map<String, Object> resultMap = null;
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		try {
			paramList.add(new SqlParameter("myuser", Types.INTEGER));
			resultMap = jdbcTemplate.call(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection
							.prepareCall("{call get_All_Lender_Applications(?)}");
					try {
						callableStatement.setString(1, uid);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return callableStatement;
				}

			}, paramList);
			return resultMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getLenderApplicationStatus(java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> getLenderApplicationStatus(final String userId, final String lenderId) {
		Map<String, Object> resultMap = null;
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		try {
			paramList.add(new SqlParameter("myuser", Types.INTEGER));
			paramList.add(new SqlParameter("myuserLenderID", Types.INTEGER));

			resultMap = jdbcTemplate.call(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection
							.prepareCall("{call get_User_LenderApplicationStatus(?,?)}");
					try {
						callableStatement.setString(1, userId);
						callableStatement.setString(2, lenderId);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return callableStatement;
				}

			}, paramList);
			return resultMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getLenderApplicationSanctions(java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> getLenderApplicationSanctions(final String userId, final String lenderId) {
		Map<String, Object> resultMap = null;
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		try {
			paramList.add(new SqlParameter("myuser", Types.INTEGER));
			paramList.add(new SqlParameter("myuserLenderID", Types.INTEGER));

			resultMap = jdbcTemplate.call(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection
							.prepareCall("{call get_User_LenderSanctionDetails(?,?)}");
					try {
						callableStatement.setString(1, userId);
						callableStatement.setString(2, lenderId);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return callableStatement;
				}

			}, paramList);
			return resultMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getLenderApplicationDisbursements(java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, Object> getLenderApplicationDisbursements(final String userId, final String lenderId) {
		Map<String, Object> resultMap = null;
		List<SqlParameter> paramList = new ArrayList<SqlParameter>();
		try {
			paramList.add(new SqlParameter("myuser", Types.INTEGER));
			paramList.add(new SqlParameter("myuserLenderID", Types.INTEGER));

			resultMap = jdbcTemplate.call(new CallableStatementCreator() {
				@Override
				public CallableStatement createCallableStatement(Connection connection) throws SQLException {
					CallableStatement callableStatement = connection
							.prepareCall("{call get_User_LenderDisbursementDetails(?,?)}");
					try {
						callableStatement.setString(1, userId);
						callableStatement.setString(2, lenderId);

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return callableStatement;
				}

			}, paramList);
			return resultMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultMap;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#applyForSelectedLenders(com.vc.staging.dto.UserLenderDTO)
	 */
	@Override
	public UserLenderCommentDO applyForSelectedLenders(UserLenderDTO lenderDto) {
		UserLenderCommentDO do1 = new UserLenderCommentDO();

		try {
			int isUpdated = getCurrentSession().getNamedQuery("UserLpiLenderDO.updatelenderAppInitiated")
					.setParameter("userId", lenderDto.getUserId()).setParameter("lenderId", lenderDto.getLenderId())
					.executeUpdate();

			UserLpiLenderDO userLPiLenderId = getUserLpiLenderId(Integer.valueOf(lenderDto.getUserId()),
					Integer.valueOf(lenderDto.getLenderId()));

			if (userLPiLenderId != null) {

				do1.setUserLpiLender(userLPiLenderId);
				do1.setComments("Loan Request Submitted");
				do1.setActiveStatus(1);
				do1.setCreatedOn(UtilityMethods.getCurrentDateTime());
				do1.setCreatedBy("");
				do1.setUpdatedOn(UtilityMethods.getCurrentDateTime());
				do1.setUpdatedBy("");
				save(do1);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return do1;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserLenderStatus(com.vc.staging.model.UserLenderCommentDO)
	 */
	@Override
	public UserLendersStatusDO updateUserLenderStatus(UserLenderCommentDO userComents) {

		UserLendersStatusDO do2 = new UserLendersStatusDO();
		try {
			do2.setUserLpiLender(userComents.getUserLpiLender());
			do2.setLenderCommentId(userComents.getLenderLpiCommentsId());
			do2.setCreatedOn(UtilityMethods.getCurrentDateTime());
			do2.setCreatedBy("");
			RefLenderLpiStatusDO refLenderLpiStatus = new RefLenderLpiStatusDO();
			refLenderLpiStatus.setLenderLpiStatusId(4);
			do2.setRefLenderLpiStatus(refLenderLpiStatus);
			do2.setActiveStatus(1);
			do2.setUpdatedOn(UtilityMethods.getCurrentDateTime());
			do2.setUpdatedBy("");
			save(do2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return do2;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserLenderSanctions(com.vc.staging.model.UserLendersStatusDO)
	 */
	@Override
	public UserlenderSanctionDetailsDO updateUserLenderSanctions(UserLendersStatusDO userStatus) {

		UserlenderSanctionDetailsDO do3 = new UserlenderSanctionDetailsDO();
		try {
			do3.setUserLpiLendeId(userStatus.getUserLpiLender().getUserLPiLenderId());
			do3.setLenderSanctioned(0);
			do3.setSanctionDate("");
			do3.setSanctionAmount("0");
			do3.setSanctionTenure(0);
			do3.setActiveStatus(1);
			do3.setCreatedOn(UtilityMethods.getCurrentDateTime());
			do3.setUpdatedOn(UtilityMethods.getCurrentDateTime());
			save(do3);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return do3;
	}

	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#updateUserLenderDisbursement(com.vc.staging.model.UserlenderSanctionDetailsDO)
	 */
	@Override
	public LenderDisbursementDO updateUserLenderDisbursement(UserlenderSanctionDetailsDO sanctions) {

		LenderDisbursementDO do4 = new LenderDisbursementDO();
		try {
			do4.setUserLpiLenderId(sanctions.getUserLpiLenderId());
			do4.setLenderDisbursementBegun(0);
			do4.setActiveStatus("1");
			do4.setDisbursementStatus("Pending Disburshment");
			do4.setCreatedOn(UtilityMethods.getCurrentDateTime());
			do4.setUpdatedOn(UtilityMethods.getCurrentDateTime());
			save(do4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return do4;
	}
	
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getcount(java.lang.String, java.lang.String)
	 */
	public String getcount(String Fromdate,String Todate)
	{
		final List<UserDetailForLenderDTO> finalList = new ArrayList<>();
		
	
		if((Fromdate==null && Todate==null) || (Fromdate=="" && Todate==""))			
		{
		String query = "select ul.userId,ui.firstName,ui.lastName,ul.userPhone,ul.userEmail,ul.primaryUserId, " + 
				" ul.createdOn,ul.activeStatus,ul.userLPiStatus,ul.LPiCount,ul.lastlogin,ul.loginattempts,ul.email_verified,ul.otp_verified" + 
				" from staging.user_login ul left outer join staging.user_info ui on ul.userId=ui.userId " + 
				" WHERE ul.primaryUserId=0 " + 
				" AND  (STR_TO_DATE(ul.lastlogin, '%Y:%m:%d'))" + 
				"  > last_day(now()) + interval 1 day - interval 3 month;";
		 System.out.println("get count query for last login is"+query);
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					UserDetailForLenderDTO dto = new UserDetailForLenderDTO();
					dto.setUserId(rs.getInt("userId"));
					dto.setFirstName(rs.getString("firstName"));
					dto.setLastName(rs.getString("lastName"));
					dto.setMobileNo(rs.getString("userPhone"));
					dto.setEmail(rs.getString("userEmail"));
					dto.setPrimaryUserId(rs.getInt("primaryUserId"));
					dto.setCreatedOn(rs.getString("createdOn"));
					dto.setActiveStatus(rs.getInt("activeStatus"));
					dto.setUserLPiStatus(rs.getInt("userLPiStatus"));
					dto.setLPiCount(rs.getInt("LPiCount"));
					dto.setLastlogin(rs.getString("lastlogin"));
					dto.setLoginattempts(rs.getInt("loginattempts"));
					dto.setEmail_verified(rs.getInt("email_verified"));
					dto.setOtp_verified(rs.getInt("otp_verified"));
					finalList.add(dto);
				}
				
				return null;
			}
		});
			return finalList.toString();
		}
		else
		{
			String query = "select ul.userId,ui.firstName,ui.lastName,ul.userPhone,ul.userEmail,ul.primaryUserId, " + 
					"  ul.createdOn,ul.activeStatus,ul.userLPiStatus,ul.LPiCount,ul.lastlogin,ul.loginattempts,ul.email_verified,ul.otp_verified " + 
					"  from staging.user_login ul left outer join staging.user_info ui on ul.userId=ui.userId" + 
					"  WHERE ul.primaryUserId=0 AND STR_TO_DATE(ul.createdOn, '%Y:%m:%d') >= '"+Fromdate+"'" + 
					"  AND STR_TO_DATE(ul.createdOn, '%Y:%m:%d') < '"+Todate+"' ";
		//	 System.out.println("get count query for one day is"+query);
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
           
			public List<String>  extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					UserDetailForLenderDTO dto = new UserDetailForLenderDTO();
					dto.setUserId(rs.getInt("userId"));
					dto.setFirstName(rs.getString("firstName"));
					dto.setLastName(rs.getString("lastName"));
					dto.setMobileNo(rs.getString("userPhone"));
					dto.setEmail(rs.getString("userEmail"));
					dto.setPrimaryUserId(rs.getInt("primaryUserId"));
					dto.setCreatedOn(rs.getString("createdOn"));
					dto.setActiveStatus(rs.getInt("activeStatus"));
					dto.setUserLPiStatus(rs.getInt("userLPiStatus"));
					dto.setLPiCount(rs.getInt("LPiCount"));
					dto.setLastlogin(rs.getString("lastlogin"));
					dto.setLoginattempts(rs.getInt("loginattempts"));
					dto.setEmail_verified(rs.getInt("email_verified"));
					dto.setOtp_verified(rs.getInt("otp_verified"));			
					finalList.add(dto);
					
				}
				return null;
			}
		});

	}
		return finalList.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getlpicompleted(java.lang.String, java.lang.String)
	 */
	public String getlpicompleted(String Fromdate,String Todate)
	{
		final List<UserDetailForLenderDTO> finalList = new ArrayList<>();
		
	
		if((Fromdate==null && Todate==null) || (Fromdate=="" && Todate==""))			
		{
		String query = " select ui.LPiCount,ll.userId,ll.lpi,ui.userEmail,ui.userPhone,ll.lenderId,l.lenderName,ll.requiredLoanAmount,ll.eligilbleLoanAmount,ll.updatedOn,ll.createdOn" + 
				" from staging.user_login ui inner join staging.user_lpi_lenders ll on ll.userId=ui.userId" + 
				" inner join staging.lender l on l.lenderId=ll.lenderId\n" + 
				" WHERE ui.primaryUserId=0 AND STR_TO_DATE(ll.updatedOn, '%Y-%m-%d')   = subdate(current_date, 1) ";
		 System.out.println("get count query for last login is"+query);
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					UserDetailForLenderDTO dto = new UserDetailForLenderDTO();
					dto.setUserId(rs.getInt("userId"));					
					dto.setMobileNo(rs.getString("userPhone"));
					dto.setEmail(rs.getString("userEmail"));
					dto.setLpi(rs.getBigDecimal("lpi"));
					dto.setRrquiredloan(rs.getBigDecimal("requiredLoanAmount"));
					dto.setLenderId(rs.getInt("lenderId"));
					dto.setLenderName(rs.getString("lenderName"));
					dto.setEligibleamount(rs.getBigDecimal("eligilbleLoanAmount"));		
					dto.setLPiCount(rs.getInt("LPiCount"));
					dto.setCreatedOn(rs.getString("createdOn"));
					dto.setUpdatedOn(rs.getString("updatedOn"));
					finalList.add(dto);
				}
				
				return null;
			}
		});
			return finalList.toString();
		}
		else
		{
		String query = "select ui.LPiCount,ll.userId,ll.lpi,ui.userEmail,ui.userPhone,ll.lenderId,l.lenderName,ll.requiredLoanAmount,ll.eligilbleLoanAmount,ll.updatedOn,ll.createdOn" + 
				" from staging.user_login ui inner join staging.user_lpi_lenders ll on ll.userId=ui.userId" + 
				" inner join staging.lender l on l.lenderId=ll.lenderId" + 
				" WHERE ui.primaryUserId=0 AND (STR_TO_DATE(ll.updatedOn, '%Y-%m-%d') >= '"+Fromdate+"'  AND STR_TO_DATE(ll.updatedOn, '%Y-%m-%d')   < '"+Todate+"') ";
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			public List<String>  extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					UserDetailForLenderDTO dto = new UserDetailForLenderDTO();
					dto.setUserId(rs.getInt("userId"));					
					dto.setMobileNo(rs.getString("userPhone"));
					dto.setEmail(rs.getString("userEmail"));
					dto.setLpi(rs.getBigDecimal("lpi"));
					dto.setRrquiredloan(rs.getBigDecimal("requiredLoanAmount"));
					dto.setLenderId(rs.getInt("lenderId"));
					dto.setLenderName(rs.getString("lenderName"));
					dto.setEligibleamount(rs.getBigDecimal("eligilbleLoanAmount"));		
					dto.setLPiCount(rs.getInt("LPiCount"));
					dto.setCreatedOn(rs.getString("createdOn"));
					dto.setUpdatedOn(rs.getString("updatedOn"));
					finalList.add(dto);
					
				}
				return null;
			}
		});

	}
		return finalList.toString();
	}
	
	/* (non-Javadoc)
	 * @see com.vc.staging.dao.UserDataFetchDAO#getappliedloanscount(java.lang.String, java.lang.String)
	 */
	public String getappliedloanscount(String Fromdate,String Todate)
	{
		final List<UserDetailForLenderDTO> finalList = new ArrayList<>();
		
	
		if((Fromdate==null && Todate==null) || (Fromdate=="" && Todate==""))			
		{
		String query = "select ll.userId,ll.lpi,ui.userEmail,ui.userPhone,ll.lenderId,l.lenderName,ll.requiredLoanAmount,ll.eligilbleLoanAmount " + 
				" from staging.user_login ui   " + 
				" inner join staging.user_lpi_lenders ll on ll.userId=ui.userId  " + 
				" inner join staging.lender l on l.lenderId=ll.lenderId " + 
				" WHERE ui.primaryUserId=0 AND ll.lenderAppInitiated=1 and STR_TO_DATE(ll.createdOn, '%Y-%m-%d') = subdate(current_date, 1) ";
		 System.out.println("get count query for last login is"+query);
				
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					UserDetailForLenderDTO dto = new UserDetailForLenderDTO();
					dto.setUserId(rs.getInt("userId"));					
					dto.setMobileNo(rs.getString("userPhone"));
					dto.setEmail(rs.getString("userEmail"));
					dto.setLpi(rs.getBigDecimal("lpi"));
					dto.setRrquiredloan(rs.getBigDecimal("requiredLoanAmount"));
					dto.setLenderId(rs.getInt("lenderId"));
					dto.setLenderName(rs.getString("lenderName"));
					dto.setEligibleamount(rs.getBigDecimal("eligilbleLoanAmount"));					
					finalList.add(dto);
				}
				
				return null;
			}
		});
			return finalList.toString();
		}
		else
		{
			String query ="select ll.userId,ll.lpi,ui.userEmail,ui.userPhone,ll.lenderId,l.lenderName,ll.requiredLoanAmount,ll.eligilbleLoanAmount " + 
					" from staging.user_login ui " + 
					" inner join staging.user_lpi_lenders ll on ll.userId=ui.userId " + 
					" inner join staging.lender l on l.lenderId=ll.lenderId " + 
					" WHERE  ui.primaryUserId=0 AND ll.lenderAppInitiated=1 and ( STR_TO_DATE(ll.createdOn, '%Y-%m-%d') >= '"+Fromdate+"' " + 
					" AND  STR_TO_DATE(ll.createdOn, '%Y-%m-%d')  < '"+Todate+"' )";
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			public List<String>  extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					UserDetailForLenderDTO dto = new UserDetailForLenderDTO();
					dto.setUserId(rs.getInt("userId"));					
					dto.setMobileNo(rs.getString("userPhone"));
					dto.setEmail(rs.getString("userEmail"));
					dto.setLpi(rs.getBigDecimal("lpi"));
					dto.setRrquiredloan(rs.getBigDecimal("requiredLoanAmount"));
					dto.setLenderId(rs.getInt("lenderId"));
					dto.setLenderName(rs.getString("lenderName"));
					dto.setEligibleamount(rs.getBigDecimal("eligilbleLoanAmount"));

				
					finalList.add(dto);
					
				}
				return null;
			}
		});

	}
		return finalList.toString();
	}

	//Method added by Priyaraj on 23-02-2023 to get companyList using employerName and employerCategory
	@Override
	public List<EmployerCategoryDTO> getOrganizationList(String employerName, int employerCategoryId) {
		final List<EmployerCategoryDTO> organizationList = new ArrayList<>();
		String query = "SELECT distinct employer_name,employer_cin FROM staging.ref_employer_category_mapping  where employer_name like '%"+employerName+"%' and employer_category =  " + employerCategoryId;
		System.out.println("query.." + query);
		jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {

			@Override
			public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {	
					EmployerCategoryDTO employerCategoryDTO = new EmployerCategoryDTO();
					employerCategoryDTO.setEmployerName(rs.getString("employer_name"));
					employerCategoryDTO.setEmployerCin(rs.getString("employer_cin"));
					organizationList.add(employerCategoryDTO);
				}
				return null;
			}
		});
		return organizationList;
	}
}
