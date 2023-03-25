use staging;

select distinct(citydivision),state from ref_zip where zip in (380001)

INSERT INTO `staging`.`ref_lender_citydivision` (`cityDivisionlenderId`, `cityDivision`, `state`, `lenderid`, `activestatus`, `createdon`, `updatedon`, `createdby`, `updatedby`) VALUES ('1296', 'Rajkot', 'Gujarat', '11', '1', '2023-03-24', '2023-03-24', 'Vastu', 'Vastu');
INSERT INTO `staging`.`ref_lender_citydivision` (`cityDivisionlenderId`, `cityDivision`, `state`, `lenderid`, `activestatus`, `createdon`, `updatedon`, `createdby`, `updatedby`) VALUES ('1297', 'Ahmedabad', 'Gujarat', '11', '1', '2023-03-24', '2023-03-24', 'Vastu', 'Vastu');

UPDATE `staging`.`ref_residence_category_score` SET `score` = '-100' WHERE (`refResidenceCategoryId` = '55');
UPDATE `staging`.`ref_gender_score` SET `score` = '80' WHERE (`genderId` = '31');

select * from ref_lender_citydivision where lenderid = 11 and state like 'Gujarat%'; -- 12 rows


select max(citydivisionlenderid) from ref_lender_citydivision

