/*
-- Query: select * from ref_lender_itconfig where lenderid in (10,11)
LIMIT 0, 2000

-- Date: 2023-04-05 11:55
*/
INSERT INTO `ref_lender_itconfig` (`ref_lenderITConfigId`,`lenderid`,`lender_isITreqd`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activestatus`) VALUES (11,10,0,'2023-04-05 5:02:00 PM','Vastu','2023-04-05 5:02:00 PM','Vastu','1');
INSERT INTO `ref_lender_itconfig` (`ref_lenderITConfigId`,`lenderid`,`lender_isITreqd`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activestatus`) VALUES (12,11,0,'2023-04-05 14:37:13','Vastu','2023-04-05 14:37:13','Vastu','1');
select * from ref_lender_itconfig where lenderid in (10,11);