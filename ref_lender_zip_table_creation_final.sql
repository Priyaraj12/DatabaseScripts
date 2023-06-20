CREATE TABLE `ref_lender_zip` (
  `ziplenderId` int(11) NOT NULL AUTO_INCREMENT,
  `zip` varchar(50) DEFAULT NULL,
  `district` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `lenderid` varchar(45) DEFAULT NULL,
  `activestatus` int(1) DEFAULT '1',
  `createdon` varchar(45) DEFAULT NULL,
  `updatedon` varchar(45) DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `updatedby` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ziplenderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



select * from ref_lender_zip