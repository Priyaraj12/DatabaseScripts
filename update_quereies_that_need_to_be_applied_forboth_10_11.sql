-- As 2 rows are seen from lenderid 11 for 'investment' incometypedesc
DELETE FROM `staging`.`ref_income_type_score` WHERE (`refIncomeTypeId` = '1150');
-- As weightPct values need to be updated for these entries, it is updated
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '100' WHERE (`refIncomeTypeId` = '1092');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '100' WHERE (`refIncomeTypeId` = '1179');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '100' WHERE (`refIncomeTypeId` = '1093');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '100' WHERE (`refIncomeTypeId` = '1180');

update ref_lender_config set maximumlendingAmount=10000000 where ITfiled=1 and lenderId=11;

UPDATE `staging`.`ref_income_type_score` SET `score` = '1' WHERE (`refIncomeTypeId` = '1060');
UPDATE `staging`.`ref_income_type_score` SET `score` = '1' WHERE (`refIncomeTypeId` = '1169');

UPDATE `staging`.`ref_income_type_score` SET `score` = '1' WHERE (`refIncomeTypeId` = '1061');
UPDATE `staging`.`ref_income_type_score` SET `score` = '1' WHERE (`refIncomeTypeId` = '1170');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '100' WHERE (`refIncomeTypeId` = '1053');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '100' WHERE (`refIncomeTypeId` = '1061');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '100' WHERE (`refIncomeTypeId` = '1060');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '100' WHERE (`refIncomeTypeId` = '1068');


UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1060');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1068');

UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1061');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1053');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1047');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1156');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1055');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1164');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1063');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1172');

UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1143');
UPDATE `staging`.`ref_income_type_score` SET `weightPct` = '50' WHERE (`refIncomeTypeId` = '1194');

UPDATE `staging`.`ref_income_type_score` SET `REFNUM` = '5' WHERE (`refIncomeTypeId` = '1043');
UPDATE `staging`.`ref_income_type_score` SET `REFNUM` = '5' WHERE (`refIncomeTypeId` = '1152');

-- As these were missing from ref_interest_rates adding them
INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('572', '11', '90', '0', '0', '500000.00', '10000000', '11', '18.5000', '17', '0', '2023-03-10 13:28:53', 'Vastu', '3', '3', '2023-03-10 13:28:53', 'Vastu', '1', '1', '2');
INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('573', '11', '90', '0', '0', '500000.00', '10000000', '11', '18.5000', '17', '0', '2023-03-10 13:28:53', 'Vastu', '3', '3', '2023-03-10 13:28:53', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('574', '10', '90', '0', '0', '500000.00', '7500000.00', '13.0000', '18.5000', '17', '0', '2023-01-27 13:32:15', 'Vastu', '4', '3', '2023-03-10 13:32:15', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('575', '10', '90', '0', '0', '500000.00', '7500000.00', '13.0000', '18.5000', '17', '0', '2023-03-10 13:36:34', 'Vastu', '5', '3', '2023-03-10 13:36:34', 'Vastu', '1', '1', '1');

INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('576', '11', '90', '0', '0', '500000.00', '10000000.00', '11.0000', '18.5000', '17', '0', '2023-03-10 11:08:22', 'Vastu', '2', '3', '2023-03-10 11:08:22', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('577', '11', '90', '0', '0', '500000.00', '10000000.00', '17.5000', '21.5000', '17', '0', '2023-03-06 19:13:30', 'Vastu', '3', '2', '2023-03-06 19:13:30', 'Vastu', '1', '1', '1');
INSERT INTO `staging`.`ref_interest_rates` (`refInterestRateId`, `lenderId`, `referenceCategoryId`, `candidateScore`, `periodInMonths`, `startRange`, `endRange`, `minRate`, `maxRate`, `REFNUM`, `score`, `createdOn`, `createdBy`, `productTypeId`, `OccupationTypeId`, `updatedOn`, `updatedBy`, `activeStatus`, `ITfiled`, `loantypeid`) VALUES ('578', '11', '90', '0', '0', '500000.00', '10000000.00', '17.5000', '21.5000', '17', '0', '2023-03-06 19:13:30', 'Vastu', '3', '2', '2023-03-06 19:13:30', 'Vastu', '1', '1', '2');






