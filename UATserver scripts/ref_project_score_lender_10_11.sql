/*
-- Query: select * from  ref_project_score where lenderid in (10,11)
LIMIT 0, 1000

-- Date: 2023-04-05 15:30
*/
INSERT INTO `ref_project_score` (`refProjectScoreId`,`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (17,'Excellent',9,10,100,100,1,'2023-04-05 20:51:39','Vastu','2023-04-05 20:51:39','Vastu',1);
INSERT INTO `ref_project_score` (`refProjectScoreId`,`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (18,'Good',9,10,80,100,2,'2023-04-05 20:55:13','Vastu','2023-04-05 20:55:13','Vastu',1);
INSERT INTO `ref_project_score` (`refProjectScoreId`,`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (19,'Fair',9,10,60,100,3,'2023-04-05 20:55:13','Vastu','2023-04-05 20:55:13','Vastu',1);
INSERT INTO `ref_project_score` (`refProjectScoreId`,`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (20,'Unknown',9,10,20,100,4,'2023-04-05 20:55:13','Vastu','2023-04-05 20:55:13','Vastu',1);
INSERT INTO `ref_project_score` (`refProjectScoreId`,`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (21,'Excellent',9,11,100,100,1,'2023-04-05 12:54:53','Vastu','2023-04-05 12:54:53','Vastu',1);
INSERT INTO `ref_project_score` (`refProjectScoreId`,`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (22,'Good',9,11,80,100,2,'2023-04-05 12:54:53','Vastu','2023-04-05 12:54:53','Vastu',1);
INSERT INTO `ref_project_score` (`refProjectScoreId`,`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (23,'Fair',9,11,60,100,3,'2023-04-05 12:54:53','Vastu','2023-04-05 12:54:53','Vastu',1);
INSERT INTO `ref_project_score` (`refProjectScoreId`,`ProjectRating`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (24,'Unknown',9,11,20,100,4,'2023-04-05 12:54:53','Vastu','2023-04-05 12:54:53','Vastu',1);
select * from ref_project_score where lenderid in (10,11)