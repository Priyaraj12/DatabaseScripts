-- cross checks done between lender 10 and lender 11 and corrected these issues
UPDATE `staging`.`ref_interest_rates` SET `ITfiled` = '1' WHERE (`refInterestRateId` = '512');
DELETE FROM `staging`.`ref_interest_rates` WHERE (`refInterestRateId` = '576');

INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('579', '11', '90', '0', '0', '500000.00', '7500000.00', '13.0000', '19.5000', '17', '0', '2023-03-23 11:08:22', 'Vastu', '3', '2', '2023-03-23 11:08:22', 'Vastu', '1', '0', '2');
INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('580', '11', '90', '0', '0', '500000.00', '7500000.00', '13.0000', '19.5000', '17', '0', '2023-03-23 11:08:21', 'Vastu', '3', '2', '2023-03-23 11:08:22', 'Vastu', '1', '0', '1');

INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('581', '11', '90', '0', '0', '500000.00', '7500000.00', '13.5000', '19.5000', '17', '0', '2023-03-23 11:08:21', 'Vastu', '3', '3', '2023-03-23 11:08:21', 'Vastu', '1', '0', '1');
INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('582', '11', '90', '0', '0', '500000.00', '7500000.00', '13.5000', '19.5000', '17', '0', '2023-03-23 11:08:21', 'Vastu', '3', '3', '2023-03-23 11:08:21', 'Vastu', '1', '0', '2');

-- for lender 10 also few changes required
UPDATE `staging`.`ref_interest_rates` SET `ITfiled` = '1' WHERE (`refInterestRateId` = '280');
DELETE FROM `staging`.`ref_interest_rates` WHERE (`refInterestRateId` = '574');

UPDATE `staging`.`ref_interest_rates` SET `ITfiled` = '1' WHERE (`refInterestRateId` = '292');
DELETE FROM `staging`.`ref_interest_rates` WHERE (`refInterestRateId` = '575');

select max(refinterestrateid) from ref_interest_rates;

UPDATE `staging`.`ref_credit_score` SET `score` = '0' WHERE (`refCreditScoreId` = '69');
UPDATE `staging`.`ref_credit_score` SET `score` = '0' WHERE (`refCreditScoreId` = '65');

UPDATE `staging`.`ref_credit_score` SET `REFNUM` = '3' WHERE (`refCreditScoreId` = '62');
UPDATE `staging`.`ref_credit_score` SET `REFNUM` = '3' WHERE (`refCreditScoreId` = '66');
UPDATE `staging`.`ref_credit_score` SET `REFNUM` = '4' WHERE (`refCreditScoreId` = '65');
UPDATE `staging`.`ref_credit_score` SET `REFNUM` = '4' WHERE (`refCreditScoreId` = '69');


