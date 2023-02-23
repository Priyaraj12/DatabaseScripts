use staging;
--   ----
-- deleted the insert scripts of ref_lender_citydivision on Feb 6 as they are all carried out in lender_vasty_citydivision sql file
--  ------------------
SELECT * FROM staging.lender;

select * from loan_type;
INSERT INTO `staging`.`loan_type`(`loantypeid`,`loantype_desc`,`created_on`,`updated_on`,`created_by`,`updated_by`,`activestatus`) 
VALUES (3,'LAP',now(),now(),'Vastu','Vastu',1);
select * from lender_product_loan where lenderid = 10;

select * from ref_producttype_score where lenderid = 10;

SELECT NOW(), SLEEP(5), NOW();
-- As per the sheet, there is no score column here for lender_product_loan
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 1, 1, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 2, 1, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 3, 1, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 4, 1, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 5, 1, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 99, 1, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 1, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 2, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 3, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 4, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 5, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 6, 3, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 7, 3, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');



select * from staging.ref_age where lenderid = 10;
INSERT INTO `staging`.`ref_age`(`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (0,25,1,10,0,15,1,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_age` (`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (26,32,1,10,100,15,2,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_age` (`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (33,39,1,10,100,15,3,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_age` (`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (40,49,1,10,0,15,4,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_age` (`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (50,999,1,10,-200,15,5,now(),'PR',now(),'PR',1);
-- In excel sheet we have additional values like 0-20, 21-35,35-9999 but not inserted yet as we need to know other values.
-- Referred lenderid 7 values from SBI_Syndicate, added these values
select * from ref_credit_score where lenderid = 10;
INSERT INTO `staging`.`ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (62,300,550,8,10,-100,15,4,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (63,651,999,8,10,100,15,1,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (64,550,650,8,10,50,15,2,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (65,0,0,8,10,25,15,3,now(),'PR',now(),'PR',1);

INSERT INTO `staging`.`ref_education_score` (`education`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Graduate',1,10,80,15,1,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_education_score` (`education`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Post Graduate',1,10,100,15,2,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_education_score` (`education`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Non-Graduate',1,10,0,15,3,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_education_score` (`education`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Diploma',1,10,50,15,19,now(),'PR',now(),'PR',1);

select * from ref_emi_config where lenderid = 10;
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) VALUES (1,12000,99999999,50,10,1,0,0,now(),'PR',now(),'PR',1,1);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) VALUES (1,12000,99999999,65,10,2,0,0,now(),'PR',now(),'PR',1,2);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) VALUES (1,12000,99999999,65,10,3,0,0,now(),'PR',now(),'PR',1,3);

INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (2,12000,99999999,50,10,1,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (2,12000,99999999,65,10,2,0,0,now(),'Vastu',now(),'Vastu',1,2);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (2,12000,99999999,65,10,3,0,0,now(),'Vastu',now(),'Vastu',1,3);

INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (3,12000,99999999,50,10,1,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (3,12000,99999999,65,10,2,0,0,now(),'Vastu',now(),'Vastu',1,2);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (3,12000,99999999,65,10,3,0,0,now(),'Vastu',now(),'Vastu',1,3);

INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (4,12000,99999999,50,10,1,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (4,12000,99999999,65,10,2,0,0,now(),'Vastu',now(),'Vastu',1,2);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (4,12000,99999999,65,10,3,0,0,now(),'Vastu',now(),'Vastu',1,3);

INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (5,12000,99999999,50,10,1,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (5,12000,99999999,65,10,2,0,0,now(),'Vastu',now(),'Vastu',1,2);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (5,12000,99999999,65,10,3,0,0,now(),'Vastu',now(),'Vastu',1,3);

INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (6,12000,99999999,50,10,1,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (6,12000,99999999,65,10,2,0,0,now(),'Vastu',now(),'Vastu',1,2);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (6,12000,99999999,65,10,3,0,0,now(),'Vastu',now(),'Vastu',1,3);

INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (7,12000,99999999,50,10,1,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (7,12000,99999999,65,10,2,0,0,now(),'Vastu',now(),'Vastu',1,2);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) 
VALUES (7,12000,99999999,65,10,3,0,0,now(),'Vastu',now(),'Vastu',1,3);

select * from ref_emi_ratio_score where lenderid = 10;
INSERT INTO `staging`.`ref_emi_ratio_score` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (0,40,2,10,100,100,1,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_emi_ratio_score` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (41,60,2,10,75,100,1,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_emi_ratio_score` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (61,100,2,10,0,100,1,now(),'PR',now(),'PR',1);

select * from ref_emi_ratio_score where lenderid in (8,10);

select * from ref_interest_rates where lenderid = 10 and loantypeid = 2;

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,12.5,19.5,17,0,now(),'PR',1,1,now(),'PR',1,1,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,16,19,17,0,now(),'PR',1,1,now(),'PR',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,15,17.5,17,0,now(),'PR',1,2,now(),'PR',1,1,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',1,2,now(),'PR',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',1,3,now(),'PR',1,0,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',1,3,now(),'PR',1,0,1);


INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,12.5,19.5,17,0,now(),'PR',1,1,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,16,19,17,0,now(),'PR',1,1,now(),'PR',1,0,2);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,15,17.5,17,0,now(),'PR',1,2,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',1,2,now(),'PR',1,0,2);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',1,3,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',1,3,now(),'PR',1,0,2);

 -- Continuation by Priya on Jan 27
 -- As per the discussion happened on Jan 27 in the morning call, adding the insert scripts in 'ref_interest_rates'
 -- for other producttypeid too i.e. 2/3/4/5
 select * from ref_interest_rates where lenderid = 10;
-- adding for productypeid = 2 on Jan 27 by Priyaraj
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,12.5,19.5,17,0,now(),'PR',2,1,now(),'PR',1,1,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,16,19,17,0,now(),'PR',2,1,now(),'PR',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,15,17.5,17,0,now(),'PR',2,2,now(),'PR',1,1,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',2,2,now(),'PR',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',2,3,now(),'PR',1,0,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',2,3,now(),'PR',1,0,1);


INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,12.5,19.5,17,0,now(),'PR',2,1,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,16,19,17,0,now(),'PR',2,1,now(),'PR',1,0,2);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,15,17.5,17,0,now(),'PR',2,2,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',2,2,now(),'PR',1,0,2);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',2,3,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',2,3,now(),'PR',1,0,2);

-- adding for productypeid = 3 on Jan 27 by Priyaraj
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,12.5,19.5,17,0,now(),'PR',3,1,now(),'PR',1,1,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,16,19,17,0,now(),'PR',3,1,now(),'PR',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,15,17.5,17,0,now(),'PR',3,2,now(),'PR',1,1,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',3,2,now(),'PR',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',3,3,now(),'PR',1,0,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',3,3,now(),'PR',1,0,1);


INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,12.5,19.5,17,0,now(),'PR',3,1,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,16,19,17,0,now(),'PR',3,1,now(),'PR',1,0,2);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,15,17.5,17,0,now(),'PR',3,2,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',3,2,now(),'PR',1,0,2);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',3,3,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',3,3,now(),'PR',1,0,2);


-- adding for productypeid = 4 on Jan 27 by Priyaraj
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,12.5,19.5,17,0,now(),'PR',4,1,now(),'PR',1,1,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,16,19,17,0,now(),'PR',4,1,now(),'PR',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,15,17.5,17,0,now(),'PR',4,2,now(),'PR',1,1,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',4,2,now(),'PR',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',4,3,now(),'PR',1,0,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',4,3,now(),'PR',1,0,1);


INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,12.5,19.5,17,0,now(),'PR',4,1,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,16,19,17,0,now(),'PR',4,1,now(),'PR',1,0,2);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,15,17.5,17,0,now(),'PR',4,2,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',4,2,now(),'PR',1,0,2);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',4,3,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',4,3,now(),'PR',1,0,2);

-- adding for productypeid = 5 on Jan 27 by Priyaraj
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,12.5,19.5,17,0,now(),'PR',5,1,now(),'PR',1,1,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,16,19,17,0,now(),'PR',5,1,now(),'PR',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,15,17.5,17,0,now(),'PR',5,2,now(),'PR',1,1,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',5,2,now(),'PR',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',5,3,now(),'PR',1,0,1);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',5,3,now(),'PR',1,0,1);


INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,12.5,19.5,17,0,now(),'PR',5,1,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,16,19,17,0,now(),'PR',5,1,now(),'PR',1,0,2);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,15,17.5,17,0,now(),'PR',5,2,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',5,2,now(),'PR',1,0,2);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',5,3,now(),'PR',1,1,2);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,13,18,17,0,now(),'PR',5,3,now(),'PR',1,0,2);

select * from ref_income_verification where lenderid = 10;
INSERT INTO `staging`.`ref_income_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES (80,999,10,10,100,5,now(),'PR',now(),'PR',1,1);
INSERT INTO `staging`.`ref_income_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES (60,79,10,40,100,5,now(),'PR',now(),'PR',1,1);
INSERT INTO `staging`.`ref_income_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES (20,59,10,60,100,5,now(),'PR',now(),'PR',1,1);

select * from `staging`.`ref_builder_ranking_tier`where lenderid = 10;
INSERT INTO `staging`.`ref_builder_ranking_tier` (`builderRank`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`activeStatus`) VALUES ('A-Excellent',9,10,100,5,1,1);
INSERT INTO `staging`.`ref_builder_ranking_tier` (`builderRank`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`activeStatus`) VALUES ('B-Good',9,10,80,5,2,1);
INSERT INTO `staging`.`ref_builder_ranking_tier` (`builderRank`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`activeStatus`) VALUES ('C-Average',9,10,70,5,3,1);
INSERT INTO `staging`.`ref_builder_ranking_tier` (`builderRank`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`activeStatus`) VALUES ('D-New',9,10,30,5,4,1);

select * from `staging`.`ref_employer_type_score` where lenderid in (7,10) order by REFNUM;
-- for 'Public Limited Indian Non-Government Company', score 100 is given but in sheet it is in ?
-- for 'Private Limited Indian Non-Government Company', score 100 is given but in sheet it is in ?
-- Need to check for weightPct , whether given values correct or not
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Public Limited Indian Non-Government Company',1,10,100,33,21,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Private Limited Indian Non-Government Company',1,10,100,33,22,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('One Person Company',1,10,0,33,23,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Private Limited Foreign Company Incorporated in India',1,10,100,33,24,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Public Limited Foreign Company Incorporated in India',1,10,50,33,25,now(),'PR',now(),'PR',1);

INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Union Government Company',1,10,100,33,26,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('State Government Company',1,10,100,33,27,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Guarantee & Association Public',1,10,100,33,28,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Guarantee & Association Private',1,10,100,33,29,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Not For Profit Company',1,10,0,33,30,now(),'PR',now(),'PR',1);

INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Unlimited Liabilities Public',1,10,50,33,31,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Unlimited Liabilities Private',1,10,50,33,32,now(),'PR',now(),'PR',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Undefined',1,10,0,33,33,now(),'PR',now(),'PR',1);

SELECT * FROM staging.ref_project_score where lenderid in (2,10);
INSERT INTO `staging`.`ref_project_score` (`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Excellent',9,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_score` (`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Good',9,10,80,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_score` (`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Fair',9,10,60,100,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_score` (`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Unknown',9,10,20,100,4,now(),'Vastu',now(),'Vastu',1);

SELECT * FROM staging.ref_ltv where lenderid in (10);
INSERT INTO `staging`.`ref_ltv` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (0,30,3,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ltv` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (30,50,3,10,75,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ltv` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (50,70,3,10,50,100,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ltv` (`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (70,999,3,10,0,100,4,now(),'Vastu',now(),'Vastu',1);

select * from ref_project_stage where lenderid in (10);
INSERT INTO `staging`.`ref_project_stage` (`projectStage`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Over 80% Completion',9,10,100,10,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_stage` (`projectStage`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Over 50% Completion',9,10,70,10,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_stage` (`projectStage`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Over 30% Completion',9,10,50,10,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_stage` (`projectStage`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Yet to Commence',9,10,25,10,4,now(),'Vastu',now(),'Vastu',1);

select * from ref_job_tenure where lenderid in (10);
INSERT INTO `staging`.`ref_job_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (0,1,1,10,0,0,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_job_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (1,3,1,10,80,0,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_job_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (3,9999,1,10,100,0,3,now(),'Vastu',now(),'Vastu',1);

select * from ref_prof_tenure where lenderid in (9,10);
INSERT INTO `staging`.`ref_prof_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (0,3,1,10,0,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_prof_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (3,6,1,10,80,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_prof_tenure` (`startTenure`,`endTenure`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (6,999,1,10,100,100,3,now(),'Vastu',now(),'Vastu',1);

select * from ref_project_accessibility where lenderid in (10);
INSERT INTO `staging`.`ref_project_accessibility` (`projectAccessibility`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('Fully Developed & Connected' ,9,10,100,5,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_accessibility` (`projectAccessibility`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('Partially Developed & Connected' ,9,10,75,5,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_accessibility` (`projectAccessibility`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('Upcoming' ,9,10,50,5,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_accessibility` (`projectAccessibility`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('Underdeveloped' ,9,10,0,5,4,now(),'Vastu',now(),'Vastu',1);

select * from ref_project_investment where lenderid in (10);
INSERT INTO `staging`.`ref_project_investment` (`projectInvestmentLevel`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('In Bad Zip' ,9,10,40,5,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_project_investment` (`projectInvestmentLevel`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus` ) 
VALUES ('Not In Bad Zip' ,9,10,100,5,2,now(),'Vastu',now(),'Vastu',1);

select * from ref_income_range where lenderid in (10);
INSERT INTO `staging`.`ref_income_range` (`incomeRangeId`,`startRange`,`endRange`,`lenderId`,`referenceCategoryId`,`OccupationTypeId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (41,12000,9999999,10,10,1,100,100,now(),'Vastu',now(),'Vastu',1);

select * from ref_producttype_score where lenderid in (10);
INSERT INTO `staging`.`ref_producttype_score` (`productTypeId`,`ProductDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (61,'New Property from Builder',1,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_producttype_score` (`productTypeId`,`ProductDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (62,'Fully Constructed Property',1,10,100,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_producttype_score` (`productTypeId`,`ProductDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (63,'Residential Plot',1,10,0,100,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_producttype_score` (`productTypeId`,`ProductDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (64,'Construction on Own Plot',1,10,80,100,4,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_producttype_score` (`productTypeId`,`ProductDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (65,'Residential Plot and Construction',1,10,50,100,5,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_producttype_score` (`productTypeId`,`ProductDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (66,'Property Not Identified',1,10,-100,100,99,now(),'Vastu',now(),'Vastu',1);
-- Two rows added on Jan 30
INSERT INTO `staging`.`ref_producttype_score` (`productTypeId`,`ProductDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (67,'Residential',1,10,100,100,6,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_producttype_score` (`productTypeId`,`ProductDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES (68,'Commercial',1,10,100,100,7,now(),'Vastu',now(),'Vastu',1);

select * from ref_ownership_score where lenderid in (10);

INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (1,1,7,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (1,2,7,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (1,3,7,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (1,4,7,10,65,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (1,5,2,10,80,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (2,1,7,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (2,2,7,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (2,3,7,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (2,4,7,10,65,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (2,5,2,10,80,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (3,1,7,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (3,2,7,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (3,3,7,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (3,4,7,10,65,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_ownership_score` (`primaryOccId`,`CoAppOccId`,`coapprelationship`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
VALUES (3,5,2,10,80,100,1,now(),'Vastu',now(),'Vastu',1);

select * from ref_lender_props_mapping where lender_Id in (10);
INSERT INTO `staging`.`ref_lender_props_mapping` (`lender_id`,`ref_lender_props_id`,`ref_key_value`,`created_on`,`cretaed_by`,`updated_on`,`activeStatus`) 
VALUES (10,1,'0.25% of requested loan amount',now(),'Vastu',now(),1);
INSERT INTO `staging`.`ref_lender_props_mapping` (`lender_id`,`ref_lender_props_id`,`ref_key_value`,`created_on`,`cretaed_by`,`updated_on`,`activeStatus`) 
VALUES (10,2,'INR 3000/- to INR 5000/- plus taxes',now(),'Vastu',now(),1);
INSERT INTO `staging`.`ref_lender_props_mapping` (`lender_id`,`ref_lender_props_id`,`ref_key_value`,`created_on`,`cretaed_by`,`updated_on`,`activeStatus`) 
VALUES (10,3,'NIL',now(),'Vastu',now(),1);
INSERT INTO `staging`.`ref_lender_props_mapping` (`lender_id`,`ref_lender_props_id`,`ref_key_value`,`created_on`,`cretaed_by`,`updated_on`,`activeStatus`) 
VALUES (10,4,'NIL',now(),'Vastu',now(),1);
INSERT INTO `staging`.`ref_lender_props_mapping` (`lender_id`,`ref_lender_props_id`,`ref_key_value`,`created_on`,`cretaed_by`,`updated_on`,`activeStatus`) 
VALUES (10,5,'NIL',now(),'Vastu',now(),1);

Select * from ref_education_score;

select * from ref_gender_score where lenderId in (10);
INSERT INTO `staging`.`ref_gender_score` (`gender`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Male',1,10,80,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_gender_score` (`gender`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Female',1,10,100,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_gender_score` (`gender`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Transgender',1,10,100,100,9,now(),'Vastu',now(),'Vastu',1);

select * from ref_marital_status_score where lenderId in (10);
INSERT INTO `staging`.`ref_marital_status_score` (`maritalStatus`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Single',1,10,50,10,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_marital_status_score` (`maritalStatus`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Married',1,10,100,10,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_marital_status_score` (`maritalStatus`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Widow(er)',1,10,50,10,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_marital_status_score` (`maritalStatus`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Separated',1,10,50,10,4,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_marital_status_score` (`maritalStatus`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) 
VALUES ('Divorced',1,10,50,10,5,now(),'Vastu',now(),'Vastu',1);

select * from ref_occupation_type_score where lenderId in (10);
INSERT INTO `staging`.`ref_occupation_type_score` (`occupationType`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Salaried',1,10,100,30,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_occupation_type_score` (`occupationType`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Self Employed Professional',1,10,100,30,2,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_occupation_type_score` (`occupationType`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Business Owner',1,10,100,30,3,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_occupation_type_score` (`occupationType`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Home Maker',1,10,0,30,4,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_occupation_type_score` (`occupationType`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Retired',1,10,0,0,5,now(),'Vastu',now(),'Vastu',1);
 
 select * from ref_residence_category_score where lenderId in (10);

INSERT INTO `staging`.`ref_residence_category_score` (`residenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Rented',1,10,50,100,1,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_residence_category_score` (`residenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Self Owned',1,10,100,100,2,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_residence_category_score` (`residenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Others',1,10,-100,100,3,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_residence_category_score` (`residenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('PG/Hostel',1,10,-100,100,4,now(),'Vastu',now(),'Vastu',1);
 INSERT INTO `staging`.`ref_residence_category_score` (`residenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`)
 VALUES ('Family Owned',1,10,100,100,5,now(),'Vastu',now(),'Vastu',1);
 

 
 -- continuing reference_category part for lenderid = 10 on Jan 27 by Priyaraj
 select * from reference_category where lenderId in (10);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('User Profile',1,10,0,30,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('FOIR Score',2,10,0,15,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('LTV Score',3,10,0,15,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Identity Score',4,10,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Income Verification Score',5,10,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Loan Verification Score',6,10,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Product Type Score',7,10,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Credit Score',8,10,0,15,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Asset Score',9,10,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Interest Rates',90,10,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Income Range Score',10,10,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
INSERT INTO `staging`.`reference_category` (`referenceCategory`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`referenceWeight`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`REFNUM`) VALUES ('Ownership Score',11,10,0,0,0,now(),'Vastu',now(),'Vastu',1,1);
 
 select * from ref_income_type_score where  lenderid = 10 ; -- 100 rows retrieved initially and hence
 -- so manipulated few things, given SBI_Syndicate file for lender id = 7 having some issues
 -- after manipulating again added 6 rows to match with lenderid 7
 -- but anyhow there will be some rework will be there 
 INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Gross Monthly Salary',1,10,1,100,1,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,3,999);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,999);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,999);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,100,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,999);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,0,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,999);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Bonus',1,10,1,50,7,now(),'Vastu',now(),'Vastu',1,0,0,0,0,1,3,999);
 -- These queries need to be checked, with respect to lenderid 7, now we have 51 records in place, but the insert scripts need to be checcked properly as some manipulations done
 INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Gross Monthly Salary',1,10,1,100,1,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,1,0);
  INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,100,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,1,0);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,0,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,1,0);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,50,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,1,0);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,0,0,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,1,0);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Incentive',1,10,1,50,6,now(),'Vastu',now(),'Vastu',1,0,0,0,0,1,1,0);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Bonus',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,0,0,1,1,0);

INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Gross Monthly Salary',1,10,1,100,1,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,2,1);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,1);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,1);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,100,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,1);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,0,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,1);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Bonus',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,2,1);

INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Gross Receipts - Latest FY',1,10,2,50,8,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,2,1);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Gross Receipts - Previous FY',1,10,2,50,9,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,2,1);

INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Gross Monthly Salary',1,10,1,100,1,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,2,2);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,2);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,2);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,100,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,2);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,0,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,2);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Bonus',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,2,2);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Gross Receipts - Latest FY',1,10,2,50,8,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,2,2);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Gross Receipts - Previous FY',1,10,2,50,9,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,2,2);

INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Gross Monthly Salary',1,10,1,100,1,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,2,3);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,3);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,3);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,100,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,3);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,0,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,3);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Bonus',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,2,3);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Gross Receipts - Latest FY',1,10,1,50,8,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,2,3);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Gross Receipts - Previous FY',1,10,1,50,9,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,2,3);

INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Gross Monthly Salary',1,10,1,100,1,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,2,4);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,4);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,4);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,100,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,4);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,0,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,4);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Bonus',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,2,4);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Gross Receipts - Latest FY',1,10,1,50,8,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,2,4);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Gross Receipts - Previous FY',1,10,1,50,9,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,2,4);

INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Gross Monthly Salary',1,10,1,100,1,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,2,5);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,5);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,5);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,100,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,5);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,0,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,2,5);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Bonus',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,2,5);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Gross Receipts - Latest FY',1,10,1,50,8,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,2,5);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Gross Receipts - Previous FY',1,10,1,50,9,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,2,5);

INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Gross Monthly Salary',1,10,1,100,1,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,3,6);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,6);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,6);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,100,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,6);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,0,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,6);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Bonus',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,3,6);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Profit -- Previous FY',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,6);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual  Profit -- Latest FY',1,10,1,50,11,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,6);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Depreciation - Latest FY',1,10,1,50,12,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,6);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Depreciation - Previous FY',1,10,1,50,13,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,6);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Tax paid - Latest FY',1,10,-1,0,14,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,6);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Tax paid- Previous FY',1,10,-1,0,15,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,6);

INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Gross Monthly Salary',1,10,1,100,1,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,3,7);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,7);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,7);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,100,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,7);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,0,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,7);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Bonus',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,3,7);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Profit -- Previous FY',1,10,1.5,50,10,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,7);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual  Profit -- Latest FY',1,10,1.5,50,11,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,7);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Depreciation - Latest FY',1,10,1,50,12,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,7);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Depreciation - Previous FY',1,10,1,50,13,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,7);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Tax paid - Latest FY',1,10,-1,0,14,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,7);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Tax paid- Previous FY',1,10,-1,0,15,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,7);

INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Gross Monthly Salary',1,10,1,100,1,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,3,8);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,8);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,8);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,100,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,8);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,0,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,8);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Bonus',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,3,8);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Profit -- Previous FY',1,10,1,100,10,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,8);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual  Profit -- Latest FY',1,10,1,100,11,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,8);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Depreciation - Latest FY',1,10,1,50,12,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,8);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Depreciation - Previous FY',1,10,1,50,13,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,8);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Tax paid - Latest FY',1,10,-1,50,14,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,8);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Tax paid- Previous FY',1,10,-1,50,15,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,8);

INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Gross Monthly Salary',1,10,1,100,1,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,100,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,0,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Bonus',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,1,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual Profit -- Previous FY',1,10,1,50,10,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Annual  Profit -- Latest FY',1,10,1,50,11,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Depreciation - Latest FY',1,10,1,50,12,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Depreciation - Previous FY',1,10,1,50,13,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Tax paid - Latest FY',1,10,-1,50,14,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Tax paid- Previous FY',1,10,-1,50,15,now(),'Vastu',now(),'Vastu',1,0,0,999999999,9999999999,1,3,9);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,4,0);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,4,0);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,50,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,4,0);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,50,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,4,0);

INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Rental',1,10,1,75,2,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,5,0);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Investment',1,10,1,50,3,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,5,0);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Pension/Annuity',1,10,1,50,4,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,5,0);
INSERT INTO `staging`.`ref_income_type_score` (`incomeTypedesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`minAmount2`,`minAmount`,`maxAmount2`,`maxAmount`,`isCoreIncome`,`OccupationTypeId`,`Occupation_CategoryId`) VALUES ('Others',1,10,1,50,5,now(),'Vastu',now(),'Vastu',1,0,0,99999999,99999999,0,5,0);

select * from ref_gender_score where lenderid = 10;
 
 select * from ref_loan_verification where lenderid = 10;

 INSERT INTO `staging`.`ref_loan_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`REFNUM`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (40,999,10,0,100,1,6,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_loan_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`REFNUM`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (30,39,10,20,100,2,6,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_loan_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`REFNUM`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (21,29,10,50,100,3,6,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_loan_verification` (`startRange`,`endRange`,`lenderId`,`score`,`weightPct`,`REFNUM`,`referenceCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (-9999999,20,10,100,100,4,6,now(),'Vastu',now(),'Vastu',1);



select * from ref_product_type;
INSERT INTO `staging`.`ref_product_type` (`productTypeId`, `ProductDesc`, `referenceCategoryId`, `lenderId`, `score`, `weightPct`, `REFNUM`, `createdOn`, `createdBy`, `updatedOn`, `updatedBy`, `activeStatus`) VALUES ('6', 'Residential', '1', '0', '0', '0', '6', '2023-01-23 23:42:00', 'Vastu', '2023-01-23 23:42:00', 'Vastu', '1');
INSERT INTO `staging`.`ref_product_type` (`productTypeId`, `ProductDesc`, `referenceCategoryId`, `lenderId`, `score`, `weightPct`, `REFNUM`, `createdOn`, `createdBy`, `updatedOn`, `updatedBy`, `activeStatus`) VALUES ('7', 'Commercial', '1', '0', '0', '0', '7', '2023-01-23 23:44:00', 'Vastu', '2023-01-23 23:44:00', 'Vastu', '1');

-- These are done as we no need to have producttypeid = 99 and 3 for lenderid = 10 . These are done in order to remove exception com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: These delete script are done on 31st January, 2023
DELETE FROM `staging`.`lender_product_loan` WHERE (`lenderproductloanid` = '124');
DELETE FROM `staging`.`lender_product_loan` WHERE (`lenderproductloanid` = '130');

DELETE FROM `staging`.`lender_product_loan` WHERE (`lenderproductloanid` = '127');
DELETE FROM `staging`.`lender_product_loan` WHERE (`lenderproductloanid` = '133');

-- Feb 2, 2023 checks by Priya in lender_product_loan for lenderid = 10 and producttypeid = 3 and 99
select * from `staging`.`lender_product_loan` WHERE lenderid = 10 and producttypeid = 3;
-- 2 rows deleted
DELETE FROM `staging`.`lender_product_loan` WHERE lenderid = 10 and producttypeid = 3;

select * from `staging`.`lender_product_loan` WHERE lenderid = 10 and producttypeid = 99;
-- 1 row deleted
DELETE FROM `staging`.`lender_product_loan` WHERE lenderid = 10 and producttypeid = 99;
-- Finally in lender_product_loan, 10 rows are available
select * from `staging`.`lender_product_loan` WHERE lenderid = 10 order by loantypeid;
-- But this is creating problem while calculating LPI, especially with the query
/*
SELECT l.lenderid FROM lender l
				inner join lender_product_loan lp on l.lenderid =lp.lenderid 
				inner join asset_financing a on lp.producttypeid = a.producttypeid and lp.loantypeid = a.loanTypeId
				where l.activeStatus >0 and lp.activestatus>0 and a.activeStatus>0 and a.userid=myuser;
                */
-- Hence inserting the rows back  on Feb 2, 2023 by Priyaraj 
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 3, 1, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 3, 2, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) VALUES (10, 99, 1, now(), now(), 'Vastu', 'Vastu', '1', '1', '1');

-- Finally in lender_product_loan, 13 rows are available
select * from `staging`.`lender_product_loan` WHERE lenderid = 10 order by loantypeid;
-- Now above query provided the lenderid 10 as well

select * from staging.ref_lender_itconfig;
INSERT INTO `staging`.`ref_lender_itconfig` (`ref_lenderITConfigId`, `lenderid`, `lender_isITreqd`, `createdOn`, `createdBy`, `updatedOn`, `updatedBy`, `activestatus`) VALUES ('11', '10', '1', '03/02/2023 5:02:00 PM', 'Vastu', '03/02/2023 5:02:00 PM', 'Vastu', '1');

-- Priya Feb 3
-- genderscore change as per sheet
UPDATE `staging`.`ref_gender_score` SET `score` = '100' WHERE (`genderId` = '31');
-- In order to increase the educationscore, folowing changes done in ref_education_score for "postgraduate" alone first. and then according to sheet, for nongraduate score is increased to 20
UPDATE `staging`.`ref_education_score` SET `score` = '20' WHERE (`educationId` = '49');
UPDATE `staging`.`ref_education_score` SET `weightPct` = '100' WHERE (`educationId` = '48');
-- changing for other educationtype too
UPDATE `staging`.`ref_education_score` SET `weightPct` = '100' WHERE (`educationId` = '47');
UPDATE `staging`.`ref_education_score` SET `weightPct` = '100' WHERE (`educationId` = '49');
UPDATE `staging`.`ref_education_score` SET `weightPct` = '100' WHERE (`educationId` = '50');

select * from ref_employer_type_score where lenderid = 10 and employerTypeDesc like 'Government';
-- PSU Listed, PSU Unlisted also not there
-- no rows found and hence inserting a row on Feb 3 by Priya
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Government',1,10,100,100,1,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('PSU Listed',1,10,100,100,2,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('PSU UnListed',1,10,70,100,3,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Private Listed',1,10,100,100,4,now(),'Vastu',now(),'Vastu',1);
INSERT INTO `staging`.`ref_employer_type_score` (`employerTypeDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES ('Private UnListed',1,10,70,100,5,now(),'Vastu',now(),'Vastu',1);
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
-- compared with other lender weightPct = 100, hence modified it here now
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '151');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '152');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '153');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '154');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '155');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '156');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '157');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '158');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '159');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '160');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '161');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '162');
UPDATE `staging`.`ref_employer_type_score` SET `weightPct` = '100' WHERE (`employerTypeId` = '163');

-- Modified weightPct = 100 for all the age ranges in ref_age and there is some age variation also there, they got applied
UPDATE `staging`.`ref_age` SET `endAge` = '20', `weightPct` = '100.00' WHERE (`refAgeId` = '52');
UPDATE `staging`.`ref_age` SET `startAge` = '21', `weightPct` = '100.00' WHERE (`refAgeId` = '53');
UPDATE `staging`.`ref_age` SET `weightPct` = '100.00' WHERE (`refAgeId` = '54');
UPDATE `staging`.`ref_age` SET `weightPct` = '100.00' WHERE (`refAgeId` = '55');
UPDATE `staging`.`ref_age` SET `weightPct` = '100.00' WHERE (`refAgeId` = '56');

-- Modified weightPct = 100 for all 3 ranges
UPDATE `staging`.`ref_job_tenure` SET `weightPct` = '100' WHERE (`refJobTenureId` = '35');
UPDATE `staging`.`ref_job_tenure` SET `weightPct` = '100' WHERE (`refJobTenureId` = '36');
UPDATE `staging`.`ref_job_tenure` SET `weightPct` = '100' WHERE (`refJobTenureId` = '37');

-- Modified weightPct = 100 for all 3 ranges
UPDATE `staging`.`ref_prof_tenure` SET `weightPct` = '100' WHERE (`refProfTenureId` = '42');
UPDATE `staging`.`ref_prof_tenure` SET `weightPct` = '100' WHERE (`refProfTenureId` = '43');
UPDATE `staging`.`ref_prof_tenure` SET `weightPct` = '100' WHERE (`refProfTenureId` = '44');

-- As seeing 0 - 30 and then 30 - 50 as ranges, modifying them as 31-50, 51-70 and 71-99 like that
-- LTV  changes
UPDATE `staging`.`ref_ltv` SET `startRange` = '31' WHERE (`refLTVId` = '34');
UPDATE `staging`.`ref_ltv` SET `startRange` = '51' WHERE (`refLTVId` = '35');
UPDATE `staging`.`ref_ltv` SET `startRange` = '71' WHERE (`refLTVId` = '36');

-- As there is 0 score for the range between 71 to 999 for lenderid = 10 in ref_ltv table, updating it to 80(similar like lenderid 8)  to get ltvscore
UPDATE `staging`.`ref_ltv` SET `score` = '80' WHERE (`refLTVId` = '36');
select * from ref_ltv where lenderid = 10;
-- creditscore modified startRange to 551 instead of existed 550
UPDATE `staging`.`ref_credit_score` SET `startRange` = '551' WHERE (`refCreditScoreId` = '64');
-- updated weightPct to 100
UPDATE `staging`.`ref_credit_score` SET `weightPct` = '100' WHERE (`refCreditScoreId` = '62');
UPDATE `staging`.`ref_credit_score` SET `weightPct` = '100' WHERE (`refCreditScoreId` = '63');
UPDATE `staging`.`ref_credit_score` SET `weightPct` = '100' WHERE (`refCreditScoreId` = '64');
UPDATE `staging`.`ref_credit_score` SET `weightPct` = '100' WHERE (`refCreditScoreId` = '65');

-- Priyaraj - Feb 7 2023, insert queries for ref_interest_rates for producttypeid = 6 and 7
select * from ref_interest_rates where lenderid = 10 and loantypeid = 3;

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,17.5,21.5,17,0,now(),'Vastu',6,2,now(),'Vastu',1,1,3);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,17.5,21.5,17,0,now(),'Vastu',6,2,now(),'Vastu',1,0,3);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,17.5,21.5,17,0,now(),'Vastu',6,3,now(),'Vastu',1,1,3);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,17.5,21.5,17,0,now(),'Vastu',6,3,now(),'Vastu',1,0,3);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,19.5,21.5,17,0,now(),'Vastu',7,2,now(),'Vastu',1,1,3);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,19.5,23.5,17,0,now(),'Vastu',7,2,now(),'Vastu',1,0,3);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,19.5,21.5,17,0,now(),'Vastu',7,3,now(),'Vastu',1,1,3);
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES (10,90,0,0,500000,7500000,19.5,23.5,17,0,now(),'Vastu',7,3,now(),'Vastu',1,0,3);

-- During the time of getinterestrate stored procedure execution , query in it failed as it is refering 'producttypeforintcal' column
-- Hence updated those columns to accomodate the change
UPDATE `staging`.`lender_product_loan` SET `producttypeforintcal` = '6' WHERE (`lenderproductloanid` = '136');
UPDATE `staging`.`lender_product_loan` SET `producttypeforintcal` = '7' WHERE (`lenderproductloanid` = '137');

select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 1 and loantypeid = 1;
-- Observed the changes in interest rate in the sheet, hence modified on 7th February 2023
UPDATE `staging`.`ref_interest_rates` SET `createdBy` = 'Vastu', `updatedBy` = 'Vastu' WHERE (`refInterestRateId` = '240');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '12.5000', `maxRate` = '19.5000', `createdBy` = 'Vastu', `updatedBy` = 'Vastu' WHERE (`refInterestRateId` = '241');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '12.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '289');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '12.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '277');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '12.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '253');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '12.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '265');

select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 2 and loantypeid = 1 and ITfiled = 1;
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.0000', `maxRate` = '18.5000' WHERE (`refInterestRateId` = '242');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.0000', `maxRate` = '18.5000' WHERE (`refInterestRateId` = '254');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.0000', `maxRate` = '18.5000' WHERE (`refInterestRateId` = '266');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.0000', `maxRate` = '18.5000' WHERE (`refInterestRateId` = '278');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.0000', `maxRate` = '18.5000' WHERE (`refInterestRateId` = '290');

select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 2 and loantypeid = 1 and ITfiled = 0;
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.0000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '243');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.0000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '255');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.0000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '267');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.0000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '279');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.0000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '291');

select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 3 and loantypeid = 1 and ITfiled = 1;

UPDATE `staging`.`ref_interest_rates` SET `maxRate` = '18.5000' WHERE (`refInterestRateId` = '244');
UPDATE `staging`.`ref_interest_rates` SET `ITfiled` = '1' WHERE (`refInterestRateId` = '256');
UPDATE `staging`.`ref_interest_rates` SET `ITfiled` = '1' WHERE (`refInterestRateId` = '268');

UPDATE `staging`.`ref_interest_rates` SET `maxRate` = '18.5000' WHERE (`refInterestRateId` = '256');
UPDATE `staging`.`ref_interest_rates` SET `maxRate` = '18.5000' WHERE (`refInterestRateId` = '268');

select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 3 and loantypeid = 1 and ITfiled = 0;

select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 3 and loantypeid = 1 and ITfiled = 0;
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '245');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '257');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '269');

select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 1  and ITfiled = 1 and loantypeid = 2;
select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 1  and ITfiled = 0 and loantypeid = 2;
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '12.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '247');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '12.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '259');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '12.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '271');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '12.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '283');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '12.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '295');

select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 2  and ITfiled = 1 and loantypeid = 2;


UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.0000', `maxRate` = '18.5000' WHERE (`refInterestRateId` = '248');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.0000', `maxRate` = '18.5000' WHERE (`refInterestRateId` = '260');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.0000', `maxRate` = '18.5000' WHERE (`refInterestRateId` = '272');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.0000', `maxRate` = '18.5000' WHERE (`refInterestRateId` = '284');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.0000', `maxRate` = '18.5000' WHERE (`refInterestRateId` = '296');

select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 2  and ITfiled = 0 and loantypeid = 2;
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.0000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '249');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.0000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '261');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.0000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '273');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.0000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '285');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.0000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '297');

select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 3  and ITfiled = 1 and loantypeid = 2;
UPDATE `staging`.`ref_interest_rates` SET `maxRate` = '18.5000' WHERE (`refInterestRateId` = '250');
UPDATE `staging`.`ref_interest_rates` SET `maxRate` = '18.5000' WHERE (`refInterestRateId` = '262');
UPDATE `staging`.`ref_interest_rates` SET `maxRate` = '18.5000' WHERE (`refInterestRateId` = '274');
UPDATE `staging`.`ref_interest_rates` SET `maxRate` = '18.5000' WHERE (`refInterestRateId` = '286');
UPDATE `staging`.`ref_interest_rates` SET `maxRate` = '18.5000' WHERE (`refInterestRateId` = '298');


select * from ref_interest_rates where lenderid =10  and OccupationTypeId = 3  and ITfiled = 0 and loantypeid = 2;
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '251');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '263');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '275');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '287');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15.5000', `maxRate` = '19.5000' WHERE (`refInterestRateId` = '299');

select * from ref_interest_rates where lenderid =10  and productTypeId = 7;
-- OccupationTypeId = 3  and ITfiled = 0 and loantypeid = 2;

-- As per discussion happened on 18th Feb for not showing other lenders, except 0(VC) and 10(Vastu), making rest of the lenders activeStatus to 0

select * from lender where activestatus = 1;
/* Let us take care of updating activeStatus to 0 for other lenders later.
UPDATE `staging`.`lender` SET `activeStatus` = '0' WHERE (`lenderId` = '1');

UPDATE `staging`.`lender` SET `activeStatus` = '0' WHERE (`lenderId` = '3');
UPDATE `staging`.`lender` SET `activeStatus` = '0' WHERE (`lenderId` = '4');
UPDATE `staging`.`lender` SET `activeStatus` = '0' WHERE (`lenderId` = '5');
UPDATE `staging`.`lender` SET `activeStatus` = '0' WHERE (`lenderId` = '6');
UPDATE `staging`.`lender` SET `activeStatus` = '0' WHERE (`lenderId` = '7');
UPDATE `staging`.`lender` SET `activeStatus` = '0' WHERE (`lenderId` = '8');
UPDATE `staging`.`lender` SET `activeStatus` = '0' WHERE (`lenderId` = '9');
*/
-- As a trial trying to add the logs in getmyLpi_All_Lenders_consolelogs
CREATE TABLE `staging`.`getmyLpi_All_Lenders_consolelogs` (
  `column1` VARCHAR(500) NULL);
  /*
 -- Tables checked for LAP  Producttypeid = 6,7 and loantypeid = 3 
 select * from staging.lender_product_loan where lenderid = 10;
 
 select * from staging.ref_lender_config where lenderid = 10;
 
 select * from staging.ref_interest_rates where lenderid = 10;
 
 select * from staging.loan_type;
 
  UPDATE `staging`.`loan_type` SET `loantype_desc` = 'LAP' WHERE (`loantypeid` = '3');
 
 select * from staging.ref_loan_type;
  select * from staging.ref_producttype_score where lenderid = 10;
 
 select * from staging.user_loans;
 
 select * from staging.ref_emi_config where lenderid = 10;
  
 
 */
 select * from staging.getmyLpi_All_Lenders_consolelogs;
 
 -- Records need to be available under ref_loan_guidelines for producttypeid 6 and 7. Otherwise we will get eligibilityamount as 0
 INSERT INTO `staging`.`ref_loan_guidelines` (`refguidelineId`, `productTypeId`, `startRange`, `endRange`, `referenceCategoryId`, `lenderId`, `score`, `weightPct`, `REFNUM`, `createdBy`, `updatedBy`, `activeStatus`) VALUES ('17', '6', '0', '3000000', '1', '0', '90', '100', '1', 'Vastu', 'Vastu', '1');
INSERT INTO `staging`.`ref_loan_guidelines` (`refguidelineId`, `productTypeId`, `startRange`, `endRange`, `referenceCategoryId`, `lenderId`, `score`, `weightPct`, `REFNUM`, `createdBy`, `updatedBy`, `activeStatus`) VALUES ('18', '6', '3000001', '7500000', '1', '0', '80', '100', '1', 'Vastu', 'Vastu', '1');
INSERT INTO `staging`.`ref_loan_guidelines` (`refguidelineId`, `productTypeId`, `startRange`, `endRange`, `referenceCategoryId`, `lenderId`, `score`, `weightPct`, `REFNUM`, `createdBy`, `updatedBy`, `activeStatus`) VALUES ('19', '6', '7500001', '999900000', '1', '0', '75', '100', '1', 'Vastu', 'Vastu', '1');

INSERT INTO `staging`.`ref_loan_guidelines` (`refguidelineId`, `productTypeId`, `startRange`, `endRange`, `referenceCategoryId`, `lenderId`, `score`, `weightPct`, `REFNUM`, `createdBy`, `updatedBy`, `activeStatus`) VALUES ('20', '7', '0', '3000000', '1', '0', '90', '100', '1', 'Vastu', 'Vastu', '1');
INSERT INTO `staging`.`ref_loan_guidelines` (`refguidelineId`, `productTypeId`, `startRange`, `endRange`, `referenceCategoryId`, `lenderId`, `score`, `weightPct`, `REFNUM`, `createdBy`, `updatedBy`, `activeStatus`) VALUES ('21', '7', '3000001', '7500000', '1', '0', '80', '100', '1', 'Vastu', 'Vastu', '1');
INSERT INTO `staging`.`ref_loan_guidelines` (`refguidelineId`, `productTypeId`, `startRange`, `endRange`, `referenceCategoryId`, `lenderId`, `score`, `weightPct`, `REFNUM`, `createdBy`, `updatedBy`, `activeStatus`) VALUES ('22', '7', '7500001', '999900000', '1', '0', '75', '100', '1', 'Vastu', 'Vastu', '1');

select * from ref_loan_guidelines where producttypeid in (6,7)
INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('310', '0', '90', '0', '0', '0.00', '999999999.99', '11.1500', '11.1500', '9', '0', 'Vastu', '6', '2', 'Vastu', '1', '1', '3');
INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('311', '0', '90', '0', '0', '0.00', '999999999.99', '11.1500', '11.1500', '9', '0', 'Vastu', '6', '2', 'Vastu', '1', '0', '3');

UPDATE `staging`.`ref_interest_rates` SET `minRate` = '8.3000', `maxRate` = '8.3000' WHERE (`refInterestRateId` = '310');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '8.3000', `maxRate` = '8.3000' WHERE (`refInterestRateId` = '311');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) 
VALUES (0,90,0,0,0.00,999999999.99,8.3000,8.3000,9,0,now(),'Vastu',6,3,now(),'Vastu',1,1,3);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) 
VALUES (0,90,0,0,0.00,999999999.99,8.3000,8.3000,9,0,now(),'Vastu',6,3,now(),'Vastu',1,0,3);

-- For producttypeid = 7
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) 
VALUES (0,90,0,0,0.00,999999999.99,8.3000,8.3000,9,0,now(),'Vastu',7,2,now(),'Vastu',1,1,3);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) 
VALUES (0,90,0,0,0.00,999999999.99,8.3000,8.3000,9,0,now(),'Vastu',7,2,now(),'Vastu',1,0,3);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) 
VALUES (0,90,0,0,0.00,999999999.99,8.3000,8.3000,9,0,now(),'Vastu',7,3,now(),'Vastu',1,1,3);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) 
VALUES (0,90,0,0,0.00,999999999.99,8.3000,8.3000,9,0,now(),'Vastu',7,3,now(),'Vastu',1,0,3);

select * from staging.ref_interest_rates where lenderid = 0 and producttypeid in (6,7);

-- Feb 22, 2023 Priyaraj adding entries in lender_product_loan as there are no entries for producttypeid = 6 and 7 and lenderid = 0
INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) 
VALUES (0, 6, 3, now(), now(), 'Vastu', 'Vastu', '1', '1', '6');

INSERT INTO `staging`.`lender_product_loan` (`lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`) 
VALUES (0, 7, 3, now(), now(), 'Vastu', 'Vastu', '1', '1', '7');

-- As we need entries in ref_emi_config for lender = 0 for producttypeid = 6 and 7 adding insert statements here
Select * from ref_emi_config g where lenderId = 0 and productTypeId in (6,7);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) VALUES 
(6,100001,99999999,60,0,1,0,0,now(),'Vastu',now(),'Vastu',1,2);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) VALUES 
(6,100001,99999999,60,0,1,0,0,now(),'Vastu',now(),'Vastu',1,3);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) VALUES 
(7,100001,99999999,60,0,1,0,0,now(),'Vastu',now(),'Vastu',1,2);
INSERT INTO `staging`.`ref_emi_config` (`productTypeId`,`startRange`,`endRange`,`emiPercent`,`lenderId`,`referenceCategoryId`,`weightPct`,`score`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`,`OccupationTypeId`) VALUES 
(7,100001,99999999,60,0,1,0,0,now(),'Vastu',now(),'Vastu',1,3);

-- After modified values like this got income output from calculateuserincome. But the values if required we need to modify
UPDATE `staging`.`ref_emi_config` SET `startRange` = '12000', `referenceCategoryId` = '2' WHERE (`emiConfigId` = '639');
UPDATE `staging`.`ref_emi_config` SET `startRange` = '12000', `referenceCategoryId` = '3' WHERE (`emiConfigId` = '641');

UPDATE `staging`.`ref_emi_config` SET `startRange` = '12000', `referenceCategoryId` = '2' WHERE (`emiConfigId` = '640');
UPDATE `staging`.`ref_emi_config` SET `startRange` = '12000', `referenceCategoryId` = '3' WHERE (`emiConfigId` = '642');

-- Sayali has shared the ref_interest_rate on Feb 22 for lenderid = 0. Few rows are updated and since more ranges new inserts also added by Priyaraj

-- producttype = 1 --------------
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 1 and ITfiled = 1 and loantypeid = 1;
UPDATE `staging`.`ref_interest_rates` SET `endRange` = '3000000.00', `minRate` = '10.5', `maxRate` = '12.5', `updatedOn` = '2/22/2023', `updatedBy` = 'Vastu' WHERE (`refInterestRateId` = '40');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,10.75,12.5,17,0,now(),'Vastu',1,1,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,11,12.5,17,0,now(),'Vastu',1,1,now(),'Vastu',1,1,1);
 
-- For ITFiled = 0
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 1 and ITfiled = 0 and loantypeid = 1;
-- select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 1 and ITfiled = 0 and loantypeid = 1;

UPDATE `staging`.`ref_interest_rates` SET `endRange` = '3000000', `minRate` = '11', `maxRate` = '13', `updatedOn` = '2/22/2023', `updatedBy` = 'Vastu' WHERE (`refInterestRateId` = '101');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.25,13,17,0,now(),'Vastu',1,1,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,11.5,13,17,0,now(),'Vastu',1,1,now(),'Vastu',1,0,1);

--  occupationtypeid = 2 and itfiled = 1
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 2 and ITfiled = 1 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 2 and ITfiled = 1 and loantypeid = 1;
UPDATE `staging`.`ref_interest_rates` SET `endRange` = '3000000', `minRate` = '11', `maxRate` = '13', `updatedOn` = '2/22/2023', `updatedBy` = 'Vastu' WHERE (`refInterestRateId` = '62');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.25,13,17,0,now(),'Vastu',1,2,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,11.5,13,17,0,now(),'Vastu',1,2,now(),'Vastu',1,1,1);

--  occupationtypeid = 2 and itfiled = 0
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 2 and ITfiled = 0 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 2 and ITfiled = 0 and loantypeid = 1;
UPDATE `staging`.`ref_interest_rates` SET `endRange` = '3000000', `minRate` = '11.5', `maxRate` = '13.5', `updatedOn` = '2/22/2023', `updatedBy` = 'Vastu' WHERE (`refInterestRateId` = '120');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.75,13.5,17,0,now(),'Vastu',1,2,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,12,13.5,17,0,now(),'Vastu',1,2,now(),'Vastu',1,0,1);

-- occupationtypeid = 3 and itfiled = 1
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 3 and ITfiled = 1 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 3 and ITfiled = 1 and loantypeid = 1;
UPDATE `staging`.`ref_interest_rates` SET `endRange` = '3000000', `minRate` = '11', `maxRate` = '13', `updatedOn` = '2/22/2023 ', `updatedBy` = 'Vastu' WHERE (`refInterestRateId` = '82');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.25,13,17,0,now(),'Vastu',1,3,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,11.5,13,17,0,now(),'Vastu',1,3,now(),'Vastu',1,1,1);

-- occupationtypeid = 3 and itfiled = 0
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 3 and ITfiled = 0 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 3 and ITfiled = 0 and loantypeid = 1;

UPDATE `staging`.`ref_interest_rates` SET `endRange` = '3000000', `minRate` = '11.500', `maxRate` = '13.5', `updatedOn` = '2/22/2023 ', `updatedBy` = 'Vastu' WHERE (`refInterestRateId` = '139');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.75,13.5,17,0,now(),'Vastu',1,3,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,12,13.5,17,0,now(),'Vastu',1,3,now(),'Vastu',1,0,1);

-- producttype = 2 --------------
-- occupationtype = 1 and itfiled = 1
select  productTypeId, lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 2 and occupationtypeid = 1 and ITfiled = 1 and loantypeid = 1;
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,10.5,12.5,17,0,now(),'Vastu',2,1,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,10.75,12.5,17,0,now(),'Vastu',2,1,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,11,12.5,17,0,now(),'Vastu',2,1,now(),'Vastu',1,1,1);
 
-- For ITFiled = 0
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 2 and occupationtypeid = 1 and ITfiled = 0 and loantypeid = 1;
-- select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 1 and ITfiled = 0 and loantypeid = 1;
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,11,13,17,0,now(),'Vastu',2,1,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.25,13,17,0,now(),'Vastu',2,1,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,11.5,13,17,0,now(),'Vastu',2,1,now(),'Vastu',1,0,1);

--  occupationtypeid = 2 and itfiled = 1
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 2 and occupationtypeid = 2 and ITfiled = 1 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId =  and occupationtypeid = 2 and ITfiled = 1 and loantypeid = 1;

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,11,13,17,0,now(),'Vastu',2,2,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.25,13,17,0,now(),'Vastu',2,2,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,11.5,13,17,0,now(),'Vastu',2,2,now(),'Vastu',1,1,1);

--  occupationtypeid = 2 and itfiled = 0
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 2 and occupationtypeid = 2 and ITfiled = 0 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 2 and occupationtypeid = 2 and ITfiled = 0 and loantypeid = 1;

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,11.5,13.5,17,0,now(),'Vastu',2,2,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.75,13.5,17,0,now(),'Vastu',2,2,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,12,13.5,17,0,now(),'Vastu',2,2,now(),'Vastu',1,0,1);

-- occupationtypeid = 3 and itfiled = 1
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 2 and occupationtypeid = 3 and ITfiled = 1 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 2 and occupationtypeid = 3 and ITfiled = 1 and loantypeid = 1;
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,11,13,17,0,now(),'Vastu',2,3,now(),'Vastu',1,1,1);


INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.25,13,17,0,now(),'Vastu',2,3,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,11.5,13,17,0,now(),'Vastu',2,3,now(),'Vastu',1,1,1);

-- occupationtypeid = 3 and itfiled = 0
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 2 and occupationtypeid = 3 and ITfiled = 0 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 2 and occupationtypeid = 3 and ITfiled = 0 and loantypeid = 1;

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,11.5,13.5,17,0,now(),'Vastu',2,3,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.75,13.5,17,0,now(),'Vastu',2,3,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,12,13.5,17,0,now(),'Vastu',2,3,now(),'Vastu',1,0,1);

-- producttype = 3 --------------
-- occupationtype = 1 and itfiled = 1
select  productTypeId, lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 3 and occupationtypeid = 1 and ITfiled = 1 and loantypeid = 1;
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,11.5,13.5,17,0,now(),'Vastu',3,1,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.75,13.5,17,0,now(),'Vastu',3,1,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,12,13.5,17,0,now(),'Vastu',3,1,now(),'Vastu',1,1,1);

-- For ITFiled = 0
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 3 and occupationtypeid = 1 and ITfiled = 0 and loantypeid = 1;
-- select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 1 and occupationtypeid = 1 and ITfiled = 0 and loantypeid = 1;
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,11.5,13.5,17,0,now(),'Vastu',3,1,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.75,13.5,17,0,now(),'Vastu',3,1,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,12,13.5,17,0,now(),'Vastu',3,1,now(),'Vastu',1,0,1);

--  occupationtypeid = 2 and itfiled = 1
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 3 and occupationtypeid = 2 and ITfiled = 1 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 3 and occupationtypeid = 2 and ITfiled = 1 and loantypeid = 1;

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,11.5,13.5,17,0,now(),'Vastu',3,2,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.75,13.5,17,0,now(),'Vastu',3,2,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,12,13.5,17,0,now(),'Vastu',3,2,now(),'Vastu',1,1,1);

--  occupationtypeid = 2 and itfiled = 0
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 3 and occupationtypeid = 2 and ITfiled = 0 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 3 and occupationtypeid = 2 and ITfiled = 0 and loantypeid = 1;

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,11.5,13.5,17,0,now(),'Vastu',3,2,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.75,13.5,17,0,now(),'Vastu',3,2,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,12,13.5,17,0,now(),'Vastu',3,2,now(),'Vastu',1,0,1);

-- occupationtypeid = 3 and itfiled = 1
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 3 and occupationtypeid = 3 and ITfiled = 1 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 3 and occupationtypeid = 3 and ITfiled = 1 and loantypeid = 1;
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,11.5,13.5,17,0,now(),'Vastu',3,3,now(),'Vastu',1,1,1);


INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.75,13.5,17,0,now(),'Vastu',3,3,now(),'Vastu',1,1,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,12,13,17,0,now(),'Vastu',3,3,now(),'Vastu',1,1,1);

-- occupationtypeid = 3 and itfiled = 0
select * from staging.ref_interest_rates where lenderid = 0 and productTypeId = 3 and occupationtypeid = 3 and ITfiled = 0 and loantypeid = 1;
select lenderid,startrange,endrange,minRate,maxRate from staging.ref_interest_rates where lenderid = 0 and productTypeId = 3 and occupationtypeid = 3 and ITfiled = 0 and loantypeid = 1;

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,0,3000000,11.5,13.5,17,0,now(),'Vastu',3,3,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,3000001,7500000,11.75,13.5,17,0,now(),'Vastu',3,3,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`,`referenceCategoryId`,`candidateScore`,`periodInMonths`,`startRange`,`endRange`,`minRate`,`maxRate`,`REFNUM`,`score`,`createdOn`,`createdBy`,`productTypeId`,`OccupationTypeId`,`updatedOn`,`updatedBy`,`activeStatus`,`ITfiled`,`loantypeid`) VALUES 
(0,90,0,0,7500001,999999999,12,13.5,17,0,now(),'Vastu',3,3,now(),'Vastu',1,0,1);

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '10.5', '12.5', '17', '0', '2023-02-22 19:24:99', 'Vastu', '4', '1', '2023-02-22 19:24:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '10.75', '12.5', '17', '0', '2023-02-22 19:24:99', 'Vastu', '4', '1', '2023-02-22 19:24:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11', '12.5', '17', '0', '2023-02-22 19:24:99', 'Vastu', '4', '1', '2023-02-22 19:24:99', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-22 19:24:99', 'Vastu', '4', '1', '2023-02-22 19:24:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-22 19:24:99', 'Vastu', '4', '1', '2023-02-22 19:24:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-22 19:24:99', 'Vastu', '4', '1', '2023-02-22 19:24:99', 'Vastu', '1', '0', '1');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '10.5', '12.5', '17', '0', '2023-02-22 19:24:99', 'Vastu', '5', '1', '2023-02-23 10:04:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '10.75', '12.5', '17', '0', '2023-02-22 19:24:99', 'Vastu', '5', '1', '2023-02-23 10:04:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11', '12.5', '17', '0', '2023-02-22 19:24:99', 'Vastu', '5', '1', '2023-02-23 10:04:99', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-22 10:04:99', 'Vastu', '5', '1', '2023-02-23 10:04:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-22 10:04:99', 'Vastu', '5', '1', '2023-02-23 10:04:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-22 10:04:99', 'Vastu', '5', '1', '2023-02-23 10:04:99', 'Vastu', '1', '0', '1');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '2', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '4', '3', '2023-02-23 19:24:99', 'Vastu', '1', '0', '1');


INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '10.5', '12.5', '17', '0', '2023-02-22 19:24:99', 'Vastu', '1', '1', '2023-02-22 19:24:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '10.75', '12.5', '17', '0', '2023-02-22 19:24:99', 'Vastu', '1', '1', '2023-02-22 19:24:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11', '12.5', '17', '0', '2023-02-22 19:24:99', 'Vastu', '1', '1', '2023-02-22 19:24:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-22 19:24:99', 'Vastu', '1', '1', '2023-02-22 19:24:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-22 19:24:99', 'Vastu', '1', '1', '2023-02-22 19:24:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-22 19:24:99', 'Vastu', '1', '1', '2023-02-22 19:24:99', 'Vastu', '1', '0', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '1', '2', '2023-02-23 19:24:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '1', '2', '2023-02-23 19:24:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '1', '2', '2023-02-23 19:24:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '1', '2', '2023-02-23 19:24:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '1', '2', '2023-02-23 19:24:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '1', '2', '2023-02-23 19:24:99', 'Vastu', '1', '0', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '1', '3', '2023-02-23 19:24:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '1', '3', '2023-02-23 19:24:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 9:24:99', 'Vastu', '1', '3', '2023-02-23 19:24:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '1', '3', '2023-02-23 19:24:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '1', '3', '2023-02-23 19:24:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 19:24:99', 'Vastu', '1', '3', '2023-02-23 19:24:99', 'Vastu', '1', '0', '2');

select * from ref_interest_rates where lenderId=0 and productTypeId=2  and loantypeid=2;

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '10.5', '12.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '1', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '10.75', '12.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '1', '2023-02-22 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11', '12.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '1', '2023-02-22 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '1', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '1', '2023-02-22 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '1', '2023-02-22 12:37:99', 'Vastu', '1', '0', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 19:12:37', 'Vastu', '2', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 12:37:99:', 'Vastu', '2', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '2', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');

select * from ref_interest_rates where lenderId=0 and productTypeId=3  and loantypeid=2;
								-- loantypeid-2 , productypeid-3, occupationtypeid=1
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '1', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '1', '2023-02-22 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '1', '2023-02-22 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '1', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '1', '2023-02-22 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '1', '2023-02-22 12:37:99', 'Vastu', '1', '0', '2');
							-- loantypeid-2 , productypeid-3, occupationtypeid=2
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 19:12:37', 'Vastu', '3', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
									-- loantypeid-2 , productypeid-3, occupationtypeid=3
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99:', 'Vastu', '3', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '3', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');

select * from ref_interest_rates where lenderId=0 and productTypeId=4  and loantypeid=2;

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '10.5', '12.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '1', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '10.75', '12.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '1', '2023-02-22 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11', '12.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '1', '2023-02-22 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '1', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '1', '2023-02-22 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '1', '2023-02-22 12:37:99', 'Vastu', '1', '0', '2');
-- loantypeid-2 , productypeid-4, occupationtypeid=2
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 19:12:37', 'Vastu', '4', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
																																																																																-- loantypeid-2 , productypeid-4, occupationtypeid=3
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 12:37:99:', 'Vastu', '4', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '4', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');

select * from ref_interest_rates where lenderId=0 and productTypeId=5  and loantypeid=2;

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '10.5', '12.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '1', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '10.75', '12.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '1', '2023-02-22 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11', '12.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '1', '2023-02-22 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '1', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '1', '2023-02-22 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '1', '2023-02-22 12:37:99', 'Vastu', '1', '0', '2');
-- loantypeid-2 , productypeid-5, occupationtypeid=2
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '2', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 19:12:37', 'Vastu', '5', '2', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
																																																																																-- loantypeid-2 , productypeid-5, occupationtypeid=3
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.25', '13', '17', '0', '2023-02-23 12:37:99:', 'Vastu', '5', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '11.5', '13', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '3', '2023-02-23 12:37:99', 'Vastu', '1', '1', '2');

INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '3000000.00', '11.5', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '3000001.00', '7500000.00', '11.75', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '7500001.00', '999999999', '12', '13.5', '17', '0', '2023-02-23 12:37:99', 'Vastu', '5', '3', '2023-02-23 12:37:99', 'Vastu', '1', '0', '2');

select * from ref_interest_rates where lenderId=0 and productTypeId=6  and loantypeid=3;

UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.5', `maxRate` = '16', `updatedOn` = '2023-02-23  16:03:99' WHERE (`refInterestRateId` = '310');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '14.5', `maxRate` = '16', `updatedOn` = '2023-02-23  16:03:99' WHERE (`refInterestRateId` = '311');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '13.5', `maxRate` = '16', `updatedOn` = '2023-02-23 16:05:31' WHERE (`refInterestRateId` = '312');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '14.5', `maxRate` = '16', `updatedOn` = '2023-02-23  16:03:99' WHERE (`refInterestRateId` = '313');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '999999999.99', '13.5', '16', '9', '0', '2023-02-23  16:03:99', 'Vastu', '6', '1', '2023-02-23  16:03:99', 'Vastu', '1', '1', '3');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '999999999.99', '14.5', '16', '9', '0', '2023-02-23  16:03:99', 'Vastu', '6', '1', '2023-02-23  16:03:99', 'Vastu', '1', '0', '3');

select * from ref_interest_rates where lenderId=0 and productTypeId=7  and loantypeid=3;
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '14', `maxRate` = '16.5', `updatedOn` = '2023-02-23 16:08:28' WHERE (`refInterestRateId` = '314');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15', `maxRate` = '16.5', `updatedOn` = '2023-02-23 16:08:28' WHERE (`refInterestRateId` = '315');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '14', `maxRate` = '16.5', `updatedOn` = '2023-02-23 16:08:28' WHERE (`refInterestRateId` = '316');
UPDATE `staging`.`ref_interest_rates` SET `minRate` = '15', `maxRate` = '16.5', `updatedOn` = '2023-02-23 16:08:28' WHERE (`refInterestRateId` = '317');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.0', '999999999.99', '14', '16.5', '9', '0', '2023-02-23 16:08:28', 'Vastu', '7', '1', 'Vastu', '1', '1', '3');
INSERT INTO `staging`.`ref_interest_rates` (`lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('0', '90', '0', '0', '0.00', '999999999.99', '15', '16.5', '9', '0', '2023-02-23 16:08:28', 'Vastu', '7', '1', 'Vastu', '1', '0', '3');








