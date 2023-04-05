/*
-- Query: select * from  ref_project_investment where lenderid in (10,11)
LIMIT 0, 1000

-- Date: 2023-04-05 15:32
*/
INSERT INTO `staging.ref_project_investment` (`refProjectInvestmentId`,`ProjectInvestmentLevel`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (9,'In Bad Zip',9,10,40,5,1,'2023-04-05 01:51:25','Vastu','2023-04-05 01:51:25','Vastu',1);
INSERT INTO `staging.ref_project_investment` (`refProjectInvestmentId`,`ProjectInvestmentLevel`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (10,'Not In Bad Zip',9,10,100,5,2,'2023-04-05 01:51:25','Vastu','2023-04-05 01:51:25','Vastu',1);
INSERT INTO `staging.ref_project_investment` (`refProjectInvestmentId`,`ProjectInvestmentLevel`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (11,'In Bad Zip',9,11,40,5,1,'2023-04-05 13:59:47','Vastu','2023-04-05 13:59:47','Vastu',1);
INSERT INTO `staging.ref_project_investment` (`refProjectInvestmentId`,`ProjectInvestmentLevel`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (12,'Not In Bad Zip',9,11,100,5,2,'2023-04-05 13:59:47','Vastu','2023-04-05 13:59:47','Vastu',1);
