-- checking for equal number of records between lender 10 and lender 11 as per Sayali sheet
use staging;
select count(1) from ref_income_type_score where lenderid = 10; -- 51
select count(1) from ref_income_type_score where lenderid = 11;-- 51

select * from ref_lender_itconfig where lenderid in (10,11);

select count(1) from ref_lender_config where lenderid = 10; -- 112
select count(1) from ref_lender_config where lenderid = 11; -- 112

select * from ref_lender_config where lenderid = 11 and productTypeId = 1 and occupationtypeid = 1; -- 4 rows

select * from ref_lender_config where lenderid = 11 and productTypeId = 1 and occupationtypeid = 2; -- 8 rows

select * from ref_lender_config where lenderid = 11 and productTypeId = 1 and occupationtypeid = 3; -- 8 rows

select * from ref_lender_config where lenderid = 10 and  occupationtypeid = 1 and itfiled = 0; -- 0 row
select * from ref_lender_config where lenderid = 11 and  occupationtypeid = 1 and itfiled = 0; -- 0 row

select * from ref_lender_config where lenderid = 8 and  occupationtypeid = 1 and itfiled = 0; -- here also 0 row

select * from ref_lender_config where lenderid in (10,11) and  occupationtypeid = 2 and itfiled = 0; -- 48 rows

select * from ref_lender_config where lenderid in (10,11) and  occupationtypeid = 3 and itfiled = 0; -- 48 rows

select * from ref_lender_config where lenderid in (10,11) and  occupationtypeid = 1 and itfiled = 1; -- 32 rows

select * from ref_lender_config where lenderid in (10,11) and  occupationtypeid = 2 and itfiled = 1; -- 48 rows

select * from ref_lender_config where lenderid in (10,11) and  occupationtypeid = 3 and itfiled = 1; -- 48 rows




select count(1) from lender_product_loan where lenderid = 10; -- 45
select count(1) from lender_product_loan where lenderid = 11; -- 45
select * from lender_product_loan where lenderid = 10 and producttypeid = 99;
select * from lender_product_loan where lenderid = 11 and producttypeforintcal <> 1 order by producttypeid ;


select count(1) from ref_interest_rates where lenderid = 10; -- 76 (12 + 12 + 12 + 12 + 12 + 8 + 8
select * from ref_interest_rates where lenderid = 10 and itfiled = 0productTypeId = 7 and OccupationTypeId = 3;
select * from ref_interest_rates where lenderid = 11 and itfiled = 0 and  productTypeId = 7 and OccupationTypeId = 3 and itfiled = 0;
select count(1) from ref_interest_rates where lenderid = 11; -- 76

select count(1) from ref_emi_config where lenderid = 10; -- 21
select * from ref_emi_config where lenderid = 10 and OccupationTypeId = 3
select count(1) from ref_emi_config where lenderid = 11; -- 21

select count(1) from reference_category where lenderid = 10; -- 12
select count(1) from reference_category where lenderid = 11; -- 12
select * from reference_category where lenderid = 11;

select count(1) from ref_age where lenderid = 10; -- 5
select count(1) from ref_age where lenderid = 11; -- 5
select * from ref_age where lenderid = 11;

select count(1) from ref_education_score where lenderid = 10; -- 4
select count(1) from ref_education_score where lenderid = 11; -- 4
select * from ref_education_score where lenderid = 11;

select count(1) from ref_residence_category_score where lenderid = 10; -- 5
select count(1) from ref_residence_category_score where lenderid = 11; -- 5
select * from ref_residence_category_score where lenderid = 11;


select count(1) from ref_job_tenure where lenderid = 10; -- 3
select count(1) from ref_job_tenure where lenderid = 11; -- 3
select * from ref_job_tenure where lenderid = 11;


select count(1) from ref_prof_tenure where lenderid = 10; -- 3
select count(1) from ref_prof_tenure where lenderid = 11; -- 3
select * from ref_prof_tenure where lenderid = 11;

select count(1) from ref_prof_tenure where lenderid = 10; -- 3
select count(1) from ref_prof_tenure where lenderid = 11; -- 3
select * from ref_prof_tenure where lenderid = 11;

select count(1) from ref_ltv where lenderid = 10; -- 4
select count(1) from ref_ltv where lenderid = 11; -- 4
select * from ref_ltv where lenderid = 11;

select count(1) from ref_credit_score where lenderid = 10; -- 4
select count(1) from ref_credit_score where lenderid = 11; -- 4
select * from ref_credit_score where  lenderid in (10,11) order by refnum;
select * from ref_credit_score where refnum = 4 and lenderid in (7,8,10,11);

select count(1) from ref_emi_ratio_score where lenderid = 10; -- 3
select count(1) from ref_emi_ratio_score where lenderid = 11; -- 3
select * from ref_emi_ratio_score where lenderid = 11;

select count(1) from ref_project_score where lenderid = 10; -- 3
select count(1) from ref_project_score where lenderid = 11; -- 3
select * from  ref_project_score where lenderid = 11;

select * from  ref_project_stage where lenderid in (10,11);
select * from  ref_project_accessibility where lenderid in (10,11);
select * from  ref_project_investment where lenderid in (10,11);

-- whenever there are errors from this script we can refer and check


