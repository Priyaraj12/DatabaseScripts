/*
-- Query: select * from ref_ltv where lenderid in (10,11)
LIMIT 0, 2000

-- Date: 2023-04-05 14:52
*/
INSERT INTO `ref_ltv` (`refLTVId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (33,0,30,3,10,100,100,1,'2023-04-05 21:37:03','Vastu','2023-04-05 21:37:03','Vastu',1);
INSERT INTO `ref_ltv` (`refLTVId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (34,31,50,3,10,75,100,2,'2023-04-05 21:39:54','Vastu','2023-04-05 21:39:54','Vastu',1);
INSERT INTO `ref_ltv` (`refLTVId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (35,51,70,3,10,50,100,3,'2023-04-05 21:39:54','Vastu','2023-04-05 21:39:54','Vastu',1);
INSERT INTO `ref_ltv` (`refLTVId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (36,71,999,3,10,0,100,4,'2023-04-05 21:39:54','Vastu','2023-04-05 21:39:54','Vastu',1);
INSERT INTO `ref_ltv` (`refLTVId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (37,0,30,3,11,100,100,1,'2023-04-05 12:57:28','Vastu','2023-04-05 12:57:28','Vastu',1);
INSERT INTO `ref_ltv` (`refLTVId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (38,31,50,3,11,75,100,2,'2023-04-05 12:57:28','Vastu','2023-04-05 12:57:28','Vastu',1);
INSERT INTO `ref_ltv` (`refLTVId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (39,51,70,3,11,50,100,3,'2023-04-05 12:57:28','Vastu','2023-04-05 12:57:28','Vastu',1);
INSERT INTO `ref_ltv` (`refLTVId`,`startRange`,`endRange`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (40,71,999,3,11,0,100,4,'2023-04-05 12:57:28','Vastu','2023-04-05 12:57:28','Vastu',1);
select * from ref_ltv where lenderid in (10,11)