select * from ref_lender_config where lenderid = 0 and reflenderconfigid <= 2096;
update ref_lender_config set itfiled = 1 where lenderid = 0 and reflenderconfigid <= 2096;

select * from ref_interest_rates where lenderid = 0 and refInterestRateId <= 139;