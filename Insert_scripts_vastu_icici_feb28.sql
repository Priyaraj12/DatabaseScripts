use staging;
--   ----
INSERT INTO `staging`.`lender` (`lenderId`, `lenderNumber`, `lenderName`, `zipId`, `activeStatus`, `lenderMinLoanAmount`, `lenderMaxLoanAmount`, `lenderCategoryId`, `createdOn`, `createdBy`, `updatedOn`, `updatedBy`, `minimumPrimaryIncome`, `minimumcoApplicantIncome`, `minimumLPiScore`, `useGrossPay`, `loanFees`, `responseDays`, `lenderlogo`, `creditStartRange`, `creditEndRange`) VALUES ('11', '11', 'Vastu_ICICI', '95558', '1', '0', '0', '1', '28/2/2023', 'Vastu', '28/2/2023', 'Vastu', '0.00', '0.00', '30', '0', '0', '3', 'Vastu_icici', '600', '900');
SELECT * FROM staging.lender;


select * from lender_product_loan where lenderid = 11 order by producttypeid;

-- SELECT NOW(), SLEEP(5), NOW();
-- As per the sheet, there is no score column here for lender_product_loan
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (11, 6, 3, now(), now(), 'Vastu', 'Vastu', '1', '1', '6');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (11, 7, 3, now(), now(), 'Vastu', 'Vastu', '1', '1', '7');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (11, 6, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '6');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (11, 7, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '7');
/* If required we may be adding for other categories. hence they are commented out
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 5, 1, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 99, 1, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 1, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 2, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 3, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 4, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 5, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 6, 3, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 7, 3, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
*/


select * from staging.ref_age where lenderid = 11;
INSERT INTO `staging`.`ref_age`(`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (0,20,1,11,0,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_age` (`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (21,32,1,11,100,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_age` (`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (33,39,1,11,100,100,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_age` (`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (40,49,1,11,0,100,4,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_age` (`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (50,999,1,11,-200,100,5,now(),'Vastu',now(),'Vastu',1);


select * from ref_credit_score where lenderid = 11;
INSERT INTO `staging`.`ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (66,300,550,8,11,-100,100,4,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (67,651,999,8,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (68,551,650,8,11,50,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (69,0,299,8,11,0,100,3,now(),'Vastu',now(),'Vastu',1);

select * from ref_education_score where lenderid in (10,11);
INSERT INTO `staging`.`ref_education_score` (`education`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Graduate',1,11,80,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_education_score` (`education`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Post Graduate',1,11,100,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_education_score` (`education`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Non-Graduate',1,11,20,100,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_education_score` (`education`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Diploma',1,11,50,100,19,now(),'Vastu',now(),'Vastu',1);


select * from ref_emi_ratio_score where lenderid = 11;
INSERT INTO `staging`.`ref_emi_ratio_score` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (0,40,2,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_emi_ratio_score` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (41,60,2,11,75,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_emi_ratio_score` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (61,100,2,11,0,100,1,now(),'Vastu',now(),'Vastu',1);
--
select * from ref_income_verification where lenderid = 11;
INSERT INTO `staging`.`ref_income_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES (80,999,11,10,100,5,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`ref_income_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES (60,79,11,40,100,5,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`ref_income_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES (20,59,11,60,100,5,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`ref_income_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES (-999999999,19,11,100,100,5,now(),'Vastu',now(),'Vastu',1,1);

select * from `staging`.`ref_builder_ranking_tier`where lenderid = 11;
-- Below entries may need a change
INSERT INTO `staging`.`ref_builder_ranking_tier` (`builderRank`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`activeStatus`) VALUES ('A-Excellent',9,11,100,5,1,1);
INSERT INTO `staging`.`ref_builder_ranking_tier` (`builderRank`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`activeStatus`) VALUES ('B-Good',9,11,80,5,2,1);
INSERT INTO `staging`.`ref_builder_ranking_tier` (`builderRank`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`activeStatus`) VALUES ('C-Average',9,11,70,5,3,1);
INSERT INTO `staging`.`ref_builder_ranking_tier` (`builderRank`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`activeStatus`) VALUES ('D-New',9,11,30,5,4,1);


SELECT * FROM staging.ref_project_score where lenderid in (11);
-- If required we may need to change -- feb 28
INSERT INTO `staging`.`ref_project_score` (`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Excellent',9,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_score` (`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Good',9,11,80,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_score` (`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Fair',9,11,60,100,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_score` (`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Unknown',9,11,20,100,4,now(),'Vastu',now(),'Vastu',1);

SELECT * FROM staging.ref_ltv where lenderid in (11);
INSERT INTO `staging`.`ref_ltv` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (0,30,3,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ltv` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (31,50,3,11,75,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ltv` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (51,70,3,11,50,100,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ltv` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (71,999,3,11,0,100,4,now(),'Vastu',now(),'Vastu',1);

select * from ref_project_stage where lenderid in (11);
INSERT INTO `staging`.`ref_project_stage` (`projectStage`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Over 80% Completion',9,11,100,10,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_stage` (`projectStage`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Over 50% Completion',9,11,70,10,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_stage` (`projectStage`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Over 30% Completion',9,11,50,10,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_stage` (`projectStage`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Yet to Commence',9,11,25,10,4,now(),'Vastu',now(),'Vastu',1);

select * from ref_job_tenure where lenderid in (11);
INSERT INTO `staging`.`ref_job_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (0,1,1,11,0,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_job_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (2,3,1,11,80,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_job_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (4,9999,1,11,100,100,3,now(),'Vastu',now(),'Vastu',1);

select * from ref_prof_tenure where lenderid in (11);
INSERT INTO `staging`.`ref_prof_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (0,3,1,11,0,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_prof_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (4,6,1,11,80,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_prof_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (7,999,1,11,100,100,3,now(),'Vastu',now(),'Vastu',1);

select * from ref_project_accessibility where lenderid in (11);
INSERT INTO `staging`.`ref_project_accessibility` (`projectAccessibility`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('Fully Developed & Connected' ,9,11,100,5,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_accessibility` (`projectAccessibility`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('Partially Developed & Connected' ,9,11,75,5,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_accessibility` (`projectAccessibility`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('Upcoming' ,9,11,50,5,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_accessibility` (`projectAccessibility`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('Underdeveloped' ,9,11,0,5,4,now(),'Vastu',now(),'Vastu',1);

select * from ref_project_investment where lenderid in (11);
INSERT INTO `staging`.`ref_project_investment` (`projectInvestmentLevel`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('In Bad Zip' ,9,11,40,5,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_investment` (`projectInvestmentLevel`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('Not In Bad Zip' ,9,11,100,5,2,now(),'Vastu',now(),'Vastu',1);

select * from ref_income_range where lenderid in (11);
INSERT INTO `staging`.`ref_income_range` (`incomeRangeId`,`startRange`,`endRange`,`lenderId`,`referenceCategoryId`,`OccupationTypeId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (42,12000,9999999,11,10,1,100,100,now(),'Vastu',now(),'Vastu',1);

select * from ref_ownership_score where lenderid in (11);
-- These are as it is copied same like lenderid 10 but this will be revisited if required
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (1,1,7,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (1,2,7,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (1,3,7,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (1,4,7,11,65,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (1,5,2,11,80,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (2,1,7,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (2,2,7,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (2,3,7,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (2,4,7,11,65,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (2,5,2,11,80,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (3,1,7,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (3,2,7,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (3,3,7,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (3,4,7,11,65,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (3,5,2,11,80,100,1,now(),'Vastu',now(),'Vastu',1);

select * from ref_lender_props_mapping where lender_Id in (11);
-- These are as it is copied same like lenderid 10 but this will be revisited if required
INSERT INTO `staging`.`ref_lender_props_mapping` (`lender_id`,`ref_lender_props_id`,`ref_key_value`,`created_on`,`cretaed_by`,`updated_on`,`activeStatus`) 
VALUES (11,1,'0.25% of requested loan amount',now(),'Vastu',now(),1);
INSERT INTO `staging`.`ref_lender_props_mapping` (`lender_id`,`ref_lender_props_id`,`ref_key_value`,`created_on`,`cretaed_by`,`updated_on`,`activeStatus`) 
VALUES (11,2,'INR 3000/- to INR 5000/- plus taxes',now(),'Vastu',now(),1);
INSERT INTO `staging`.`ref_lender_props_mapping` (`lender_id`,`ref_lender_props_id`,`ref_key_value`,`created_on`,`cretaed_by`,`updated_on`,`activeStatus`) 
VALUES (11,3,'NIL',now(),'Vastu',now(),1);
INSERT INTO `staging`.`ref_lender_props_mapping` (`lender_id`,`ref_lender_props_id`,`ref_key_value`,`created_on`,`cretaed_by`,`updated_on`,`activeStatus`) 
VALUES (11,4,'NIL',now(),'Vastu',now(),1);
INSERT INTO `staging`.`ref_lender_props_mapping` (`lender_id`,`ref_lender_props_id`,`ref_key_value`,`created_on`,`cretaed_by`,`updated_on`,`activeStatus`) 
VALUES (11,5,'NIL',now(),'Vastu',now(),1);

select * from ref_gender_score where lenderId in (11);
-- These are as it is copied same like lenderid 10 but this will be revisited if required
INSERT INTO `staging`.`ref_gender_score` (`gender`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Male',1,11,80,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_gender_score` (`gender`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Female',1,11,100,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_gender_score` (`gender`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Transgender',1,11,100,100,9,now(),'Vastu',now(),'Vastu',1);

select * from ref_marital_status_score where lenderId in (11);
-- These are as it is copied same like lenderid 10 but this will be revisited if required
INSERT INTO `staging`.`ref_marital_status_score` (`maritalStatus`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Single',1,11,50,10,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_marital_status_score` (`maritalStatus`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Married',1,11,100,10,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_marital_status_score` (`maritalStatus`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Widow(er)',1,11,50,10,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_marital_status_score` (`maritalStatus`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Separated',1,11,50,10,4,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_marital_status_score` (`maritalStatus`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Divorced',1,11,50,10,5,now(),'Vastu',now(),'Vastu',1);

select * from ref_occupation_type_score where lenderId in (11);
-- These are as it is copied same like lenderid 10 but this will be revisited if required
INSERT INTO `staging`.`ref_occupation_type_score` (`occupationType`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Salaried',1,11,100,30,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_occupation_type_score` (`occupationType`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Self Employed Professional',1,11,100,30,2,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_occupation_type_score` (`occupationType`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Business Owner',1,11,100,30,3,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_occupation_type_score` (`occupationType`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Home Maker',1,11,0,30,4,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_occupation_type_score` (`occupationType`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Retired',1,11,0,0,5,now(),'Vastu',now(),'Vastu',1);
 
 select * from ref_residence_category_score where lenderId in (11);
-- These are as it is copied same like lenderid 10 but this will be revisited if required
INSERT INTO `staging`.`ref_residence_category_score` (`residenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Rented',1,11,50,100,1,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_residence_category_score` (`residenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Self Owned',1,11,100,100,2,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_residence_category_score` (`residenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Others',1,11,-100,100,3,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_residence_category_score` (`residenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('PG/Hostel',1,11,-100,100,4,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_residence_category_score` (`residenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Family Owned',1,11,100,100,5,now(),'Vastu',now(),'Vastu',1);
 
 select * from reference_category where lenderId in (11);
 
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('User Profile',1,11,0,30,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('FOIR Score',2,11,0,15,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('LTV Score',3,11,0,15,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Identity Score',4,11,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Income Verification Score',5,11,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Loan Verification Score',6,11,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Product Type Score',7,11,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Credit Score',8,11,0,15,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Asset Score',9,11,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Interest Rates',90,11,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Income Range Score',10,11,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Ownership Score',11,11,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
 
 UPDATE `staging`.`reference_category` SET `weightPct` = '25' WHERE (`categoryNum` = '133');
UPDATE `staging`.`reference_category` SET `weightPct` = '20' WHERE (`categoryNum` = '134');
UPDATE `staging`.`reference_category` SET `weightPct` = '20' WHERE (`categoryNum` = '135');
UPDATE `staging`.`reference_category` SET `weightPct` = '10' WHERE (`categoryNum` = '137');
UPDATE `staging`.`reference_category` SET `weightPct` = '10' WHERE (`categoryNum` = '144');

  select * from ref_loan_verification where lenderid = 11;
-- These are as it is copied same like lenderid 10 but this will be revisited if required
 INSERT INTO `staging`.`ref_loan_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`REFNUM`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (40,999,11,0,100,1,6,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_loan_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`REFNUM`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (30,39,11,20,100,2,6,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_loan_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`REFNUM`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (21,29,11,50,100,3,6,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_loan_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`REFNUM`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (-9999999,20,11,100,100,4,6,now(),'Vastu',now(),'Vastu',1);



select * from staging.ref_lender_itconfig where lenderid = 11;
-- This will have a change . Hence it will be modified
INSERT INTO `staging`.`ref_lender_itconfig` (`ref_lenderITConfigId`, `lenderid`, `lender_isITreqd`, `createdOn`, `createdBy`, `updatedOn`, `updatedBy`, `activestatus`) VALUES ('12', '11', '1', now(), 'Vastu', now(), 'Vastu', '1');


select * from ref_employer_type_score where lenderid = 11 and employerTypeDesc like 'Government';
-- These are as it is copied same like lenderid 10 but this will be revisited if required
-- Few entries need to be checked still
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Government',1,11,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('PSU Listed',1,11,100,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('PSU UnListed',1,11,70,100,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Private Listed',1,11,100,100,4,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Private UnListed',1,11,70,100,5,now(),'Vastu',now(),'Vastu',1);
-- As for Public Limited Indian Non-Government Company,Private Limited Indian Non-Government Company,One Person Company
-- Private Limited Foreign Company Incorporated in India
-- Public Limited Foreign Company Incorporated in India
-- Union Government Company
-- State Government Company
-- Guarantee & Association Public,Guarantee & Association Private
-- Not For Profit Company
-- Unlimited Liabilities Public
-- Unlimited Liabilities Private
-- Undefined








