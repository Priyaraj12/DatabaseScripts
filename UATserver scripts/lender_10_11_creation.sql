/*
-- Query: select * from lender where lenderid in (10,11)
LIMIT 0, 1000

-- Date: 2023-04-05 15:35
*/
use staging;
INSERT INTO `lender` (`lenderId`,`lenderNumber`,`lenderName`,`zipId`,`lenderContact`,`lenderPhone`,`activeStatus`,`lenderMinLoanAmount`,`lenderMaxLoanAmount`,`lenderCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`minimumPrimaryIncome`,`minimumcoApplicantIncome`,`minimumLPiScore`,`useGrossPay`,`loanFees`,`responseDays`,`lenderWebsite`,`lenderlogo`,`creditStartRange`,`creditEndRange`) VALUES (10,10,'Vastu',95558,'NULL','NULL',1,0,0,1,'2023-04-05','Vastu','2023-04-05','Vastu',0.00,0.00,30,0,0,3,'NULL','Vastu',500,900);
INSERT INTO `lender` (`lenderId`,`lenderNumber`,`lenderName`,`zipId`,`lenderContact`,`lenderPhone`,`activeStatus`,`lenderMinLoanAmount`,`lenderMaxLoanAmount`,`lenderCategoryId`,`createdOn`,`createdBy`,`updatedOn`,`updatedBy`,`minimumPrimaryIncome`,`minimumcoApplicantIncome`,`minimumLPiScore`,`useGrossPay`,`loanFees`,`responseDays`,`lenderWebsite`,`lenderlogo`,`creditStartRange`,`creditEndRange`) VALUES (11,11,'Vastu_ICICI',95558,NULL,NULL,1,0,0,1,'2023-04-05','Vastu','2023-04-05','Vastu',0.00,0.00,30,0,0,3,NULL,'Vastu_icici',500,900);

select * from lender;
