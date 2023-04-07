/*
-- Query: select * from ref_loan_guidelines where refguidelineId > 16
LIMIT 0, 1000

-- Date: 2023-04-05 17:32
*/
INSERT INTO `ref_loan_guidelines` (`refguidelineId`,`productTypeId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (17,6,0,3000000,1,0,90,100,1,NULL,'Vastu',NULL,'Vastu',1);
INSERT INTO `ref_loan_guidelines` (`refguidelineId`,`productTypeId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (18,6,3000001,7500000,1,0,80,100,1,NULL,'Vastu',NULL,'Vastu',1);
INSERT INTO `ref_loan_guidelines` (`refguidelineId`,`productTypeId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (19,6,7500001,999900000,1,0,75,100,1,NULL,'Vastu',NULL,'Vastu',1);
INSERT INTO `ref_loan_guidelines` (`refguidelineId`,`productTypeId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (20,7,0,3000000,1,0,90,100,1,NULL,'Vastu',NULL,'Vastu',1);
INSERT INTO `ref_loan_guidelines` (`refguidelineId`,`productTypeId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (21,7,3000001,7500000,1,0,80,100,1,NULL,'Vastu',NULL,'Vastu',1);
INSERT INTO `ref_loan_guidelines` (`refguidelineId`,`productTypeId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (22,7,7500001,999900000,1,0,75,100,1,NULL,'Vastu',NULL,'Vastu',1);
select * from ref_loan_guidelines where refguidelineId > 16