use staging;
INSERT INTO `staging`.`ref_lender_citydivision` (`cityDivisionlenderId`, `cityDivision`, `state`, `lenderid`, `activestatus`, `createdon`, `updatedon`, `createdby`, `updatedby`) VALUES ('1295', 'Surendranagar', 'Gujarat', '11', '1', '2023-03-21', '2023-03-21', 'Vastu', 'Vastu');

select i.userId, c.lenderid, c.cityDivision, c.state, c.cityDivisionlenderId
           --  into tempuserId, tlenderId, cityDivision, state, lenderCityDivisionId
            from user_info i
                                inner join user_address a on a.userAddressId=i.addressid
                                inner join ref_zip z on z.zipid = a.zipid 
                                inner join ref_lender_citydivision c on c.cityDivision = z.cityDivision and c.state = z.state and c.lenderId = 11
                                where i.userid=1043 and c.activestatus>0;