/*
-- Query: select * from ref_age where lenderid in (10,11)
LIMIT 0, 2000

-- Date: 2023-04-05 14:38
*/
INSERT INTO `ref_age` (`refAgeId`,`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (52,0,20,1,10,0,100.00,1,'2023-04-05 15:47:30','PR','2023-04-05 15:47:30','PR',1);
INSERT INTO `ref_age` (`refAgeId`,`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (53,21,32,1,10,100,100.00,2,'2023-04-05 15:52:21','PR','2023-04-05 15:52:21','PR',1);
INSERT INTO `ref_age` (`refAgeId`,`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (54,33,39,1,10,100,100.00,3,'2023-04-05 15:53:42','PR','2023-04-05 15:53:42','PR',1);
INSERT INTO `ref_age` (`refAgeId`,`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (55,40,49,1,10,0,100.00,4,'2023-04-05 15:53:42','PR','2023-04-05 15:53:42','PR',1);
INSERT INTO `ref_age` (`refAgeId`,`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (56,50,999,1,10,-200,100.00,5,'2023-04-05 15:53:42','PR','2023-04-05 15:53:42','PR',1);
INSERT INTO `ref_age` (`refAgeId`,`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (57,0,20,1,11,0,100.00,1,'2023-04-05 10:45:09','Vastu','2023-04-05 10:45:09','Vastu',1);
INSERT INTO `ref_age` (`refAgeId`,`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (58,21,32,1,11,100,100.00,2,'2023-04-05 10:45:09','Vastu','2023-04-05 10:45:09','Vastu',1);
INSERT INTO `ref_age` (`refAgeId`,`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (59,33,39,1,11,100,100.00,3,'2023-04-05 10:45:09','Vastu','2023-04-05 10:45:09','Vastu',1);
INSERT INTO `ref_age` (`refAgeId`,`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (60,40,49,1,11,0,100.00,4,'2023-04-05 10:45:09','Vastu','2023-04-05 10:45:09','Vastu',1);
INSERT INTO `ref_age` (`refAgeId`,`startAge`,`endAge`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (61,50,999,1,11,-200,100.00,5,'2023-04-05 10:45:09','Vastu','2023-04-05 10:45:09','Vastu',1);
select * from ref_age where lenderid in (10,11);