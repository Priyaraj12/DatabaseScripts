/*
-- Query: select * from ref_credit_score where  lenderid in (10,11)
LIMIT 0, 2000

-- Date: 2023-04-05 14:54
*/
INSERT INTO `ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (62,300,550,8,10,-100,100,3,'2023-04-05 16:04:54','PR','2023-04-05 16:04:54','PR',1);
INSERT INTO `ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (63,651,999,8,10,100,100,1,'2023-04-05 16:09:52','PR','2023-04-05 16:09:52','PR',1);
INSERT INTO `ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (64,551,650,8,10,50,100,2,'2023-04-05 16:09:52','PR','2023-04-05 16:09:52','PR',1);
INSERT INTO `ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (65,0,299,8,10,0,100,4,'2023-04-05 16:09:52','PR','2023-04-05 16:09:52','PR',1);
INSERT INTO `ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (66,300,550,8,11,-100,100,3,'2023-04-05 11:20:10','Vastu','2023-04-05 11:20:10','Vastu',1);
INSERT INTO `ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (67,651,999,8,11,100,100,1,'2023-04-05 11:20:28','Vastu','2023-04-05 11:20:28','Vastu',1);
INSERT INTO `ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (68,551,650,8,11,50,100,2,'2023-04-05 11:20:28','Vastu','2023-04-05 11:20:28','Vastu',1);
INSERT INTO `ref_credit_score` (`refCreditScoreId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (69,0,299,8,11,0,100,4,'2023-04-05 11:20:28','Vastu','2023-04-05 11:20:28','Vastu',1);
select * from ref_credit_score where  lenderid in (10,11) ;