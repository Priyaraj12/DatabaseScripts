CREATE TABLE `ref_lender_zip` (
  `ziplenderId` int(11) NOT NULL,
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

select * from ref_lender_zip;

INSERT INTO `staging`.`ref_lender_zip` (`ziplenderId`, `zip`, `district`, `state`, `lenderid`, `activestatus`, `createdon`, `updatedon`, `createdby`, `updatedby`) VALUES ('1', '456006', 'Ujjain', 'Madhya Pradesh', '10', '1', '03-27-2023', '03-27-2023', 'Vastu', 'Vastu');
-- truncate table ref_lender_zip;

select a.userId,z.zip
           from user_address a 
                                inner join ref_zip z on z.zipid = a.zipid                                 
                               inner join ref_lender_zip  lz on lz.zip = z.zip                                
                                where a.userid=1048 and lz.state = z.state and lz.lenderId = 10                                
                                and lz.activestatus>0;
                                
select * from user_info where userid = 1005     
select * from user_address where userid in (1005)                           
select * from ref_lender_citydivision where userid = 1005       

select * from ref_zip where zipid in (32572,65384,43694)   

select * from ref_zip where zipid in (65384) 

select * from ref_zip where zip in (456006)                      
