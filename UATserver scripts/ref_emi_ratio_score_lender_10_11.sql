/*
-- Query: select * from staging.ref_emi_ratio_score where lenderid in (10,11)
LIMIT 0, 1000

-- Date: 2023-04-05 15:28
*/
INSERT INTO `ref_emi_ratio_score` (`refEmiScoreRatioId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (37,0,40,2,10,100,100,1,'2023-04-05 16:31:21','PR','2023-04-05 16:31:21','PR',1);
INSERT INTO `ref_emi_ratio_score` (`refEmiScoreRatioId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (38,41,60,2,10,75,100,1,'2023-04-05 16:32:54','PR','2023-04-05 16:32:54','PR',1);
INSERT INTO `ref_emi_ratio_score` (`refEmiScoreRatioId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (39,61,100,2,10,0,100,1,'2023-04-05 16:32:54','PR','2023-04-05 16:32:54','PR',1);
INSERT INTO `ref_emi_ratio_score` (`refEmiScoreRatioId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (40,0,40,2,11,100,100,1,'2023-04-05 10:56:59','Vastu','2023-04-05 10:56:59','Vastu',1);
INSERT INTO `ref_emi_ratio_score` (`refEmiScoreRatioId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (41,41,60,2,11,75,100,1,'2023-04-05 10:56:59','Vastu','2023-04-05 10:56:59','Vastu',1);
INSERT INTO `ref_emi_ratio_score` (`refEmiScoreRatioId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (42,61,100,2,11,0,100,1,'2023-04-05 10:56:59','Vastu','2023-04-05 10:56:59','Vastu',1);
select * from ref_emi_ratio_score where lenderid in (10,11);