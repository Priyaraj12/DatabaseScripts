use staging;
-- Business requirement of 'Addition of productApplicability  column to lender_product_loan' table -- Done by Priyaraj on March 15,2023
ALTER TABLE lender_product_loan ADD productApplicability  TINYINT;
-- With regards to lender 10, producttypeid = 99 and 3 are not supported
UPDATE `staging`.`lender_product_loan` SET `productApplicability` = '0' WHERE (`lenderproductloanid` = '142');
UPDATE `staging`.`lender_product_loan` SET `productApplicability` = '0' WHERE (`lenderproductloanid` = '140');
UPDATE `staging`.`lender_product_loan` SET `productApplicability` = '0' WHERE (`lenderproductloanid` = '141');

ALTER TABLE lender_product_loan CHANGE productApplicability applicability TINYINT;

UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '149');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '150');

UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '151');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '152');

INSERT INTO `staging`.`lender_product_loan` (`lenderproductloanid`, `lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`, `applicability`) VALUES ('159', '11', '3', '1', '2023-03-15 15:53:52', '2023-03-15 15:53:52', 'Vastu', 'Vastu', '1', '1', '1', '0');
INSERT INTO `staging`.`lender_product_loan` (`lenderproductloanid`, `lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`, `applicability`) VALUES ('160', '11', '3', '2', '2023-03-15 15:53:52', '2023-03-15 15:53:52', 'Vastu', 'Vastu', '1', '1', '1', '0');

UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '153');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '154');

UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '157');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '158');

UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '121');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '128');

UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '123');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '129');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '125');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '131');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '126');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '132');

UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '136');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '145');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '147');
INSERT INTO `staging`.`lender_product_loan` (`lenderproductloanid`, `lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`, `applicability`) VALUES ('161', '10', '6', '2', '2023-03-15 10:32:53', '2023-03-15 10:32:53', 'Vastu', 'Vastu', '1', '1', '6', '1');

INSERT INTO `staging`.`lender_product_loan` (`lenderproductloanid`, `lenderid`, `producttypeid`, `loantypeid`, `created_on`, `updated_on`, `created_by`, `updated_by`, `activeStatus`, `loantypeforintcal`, `producttypeforintcal`, `applicability`) VALUES ('162', '10', '7', '2', '2023-03-15 00:50:58', '2023-03-15 00:50:58', 'Vastu', 'Vastu', '1', '1', '7', '1');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '137');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '146');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '148');

UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '1');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '2');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '3');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '4');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '5');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '6');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '43');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '44');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '45');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '46');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '47');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '48');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '143');
UPDATE `staging`.`lender_product_loan` SET `applicability` = '1' WHERE (`lenderproductloanid` = '144');

