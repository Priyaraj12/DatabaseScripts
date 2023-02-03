use staging;



                              

-- By taking the userid = 976 proceeding to delete the rows from ref_lender_config for additional rows
select * from ref_lender_config where lenderid = 10 and productTypeId = 1 and OccupationTypeId = 1 and cityTierId = 3


DELETE FROM `staging`.`ref_lender_config` WHERE (`reflenderconfigid` = '2547');
-- 7 rows
select * from ref_lender_config where lenderid = 10 and productTypeId = 1 and OccupationTypeId = 3 and cityTierId = 3;
-- As in the sheet on 2nd feb, minimumJobExperience is 3, so changed
UPDATE `staging`.`ref_lender_config` SET `minimumJobExperience` = '3' WHERE (`reflenderconfigid` = '2675');
-- Then since 1 row need to be there, deleting the rest 6 rows by keeping 1st row alone
select * from ref_lender_config where lenderid = 10 and productTypeId = 1 and OccupationTypeId = 3 and cityTierId = 3 and minimumJobExperience <> 3;
-- These 6 rows need to be deleted
DELETE FROM `staging`.`ref_lender_config` WHERE (`reflenderconfigid` = '2691');
DELETE FROM `staging`.`ref_lender_config` WHERE (`reflenderconfigid` = '2707');
DELETE FROM `staging`.`ref_lender_config` WHERE (`reflenderconfigid` = '2723');
DELETE FROM `staging`.`ref_lender_config` WHERE (`reflenderconfigid` = '2739');
DELETE FROM `staging`.`ref_lender_config` WHERE (`reflenderconfigid` = '2755');
DELETE FROM `staging`.`ref_lender_config` WHERE (`reflenderconfigid` = '2771');

-- After doing all the above operations,
select i.userId, z.zipId, c.cityTierId, p.productTypeId, c.lenderId, c.minimumEarningsAmount, c.maximumAgeAtMaturity, c.maximumTenure, c.maximumAgeAtMaturity*12
 --           into temp, tzip, tcityTierId, myproductid, tlenderId, minimumEarnings, maximumAgeAtMaturity, maximumTenure, maximumAgeAtMaturityinMonths
            from user_info i
                                inner join user_address a on a.userAddressId=i.addressid
                                inner join ref_zip z on z.zipid = a.zipid 
                inner join asset_financing p on p.userId = i.userId and p.activeStatus > 0
                                inner join ref_lender_config c on c.cityTierId = z.cityTierId and 
                                lenderId = 10 and c.productTypeId=p.productTypeId
                                inner join user_employment e on e.userid= i.userid  -- Added by Vidhya Jan 7th2019
                                where i.userid=976 and e.occupationtypeId= c.OccupationTypeId;
                                
-- Giving 1 row                                

select count(1) from `staging`.`ref_lender_config` where lenderid = 10 and OccupationTypeId = 1;

delete from `staging`.`ref_lender_config` where lenderid = 10

