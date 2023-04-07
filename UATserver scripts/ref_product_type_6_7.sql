/*
-- Query: select * from ref_product_type where productTypeId in (6,7)
LIMIT 0, 1000

-- Date: 2023-04-05 15:40
*/
use staging;
INSERT INTO `ref_product_type` (`productTypeId`,`ProductDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (6,'Residential',1,0,0,0,6,'2023-04-05 23:42:00','Vastu','2023-04-05 23:42:00','Vastu',1);
INSERT INTO `ref_product_type` (`productTypeId`,`ProductDesc`,`referenceCategoryId`,`lenderId`,`score`,`weightPct`,`REFNUM`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`activeStatus`) VALUES (7,'Commercial',1,0,0,0,7,'2023-04-05 23:44:00','Vastu','2023-04-05 23:44:00','Vastu',1);

select * from ref_product_type;
