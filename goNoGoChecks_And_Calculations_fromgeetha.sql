CREATE DEFINER=`root`@`localhost` PROCEDURE `goNoGoChecks_And_Calculations`(in myuser int,in banker int,in productOrLoanApplicable tinyInt,in count1 int,in debug int)
BEGIN
	declare errmsg varchar(100);
    declare rejecttemp int;
   -- Geetha on 03/31/2023
	declare myloanid, BTLender, MappedLender int; 
    declare myLPi decimal (10,4);
	declare producttypeid int; -- added  by Priyaraj on Feb 1, 2023 for rejecting if producttypeid = 99 and 3 (for lender = vastu) when user selects it from frontend
    declare occupationtypeId int; -- added by Priyaraj on Feb 8, 2023 for rejecting if occupationtypeid = 1 and producttypeid = 6 or 7 (for lender = vastu) when user selects it from frontend
    declare ITfiled tinyint; -- added by Priyaraj on Mar 10, 2023 to get ITfiled value from user_employment
    declare lender_isITreqd int; -- added by Priyaraj on Mar 10, 2023 to get lender_isITreqd value from ref_lender_itconfig
    -- Added bY Geetha 03/31/2023
    set BTLender=0;
	set MappedLender=100;
	-- End 
    select it.lender_isITreqd into lender_isITreqd from ref_lender_itconfig it where lenderid = banker;
    select f.productTypeId into producttypeid from asset_financing f where f.userid=myuser and activeStatus>0;
	-- Added the select query by Priyaraj on Feb 8, 2023 to get occupationtypeId from user_employment 
    -- Added additionally to get ITfiled value by Priyaraj on Mar 10,2023  from user_employment
	select ue.occupationtypeId,ue.ITfiled into occupationtypeId,ITfiled from user_employment ue where ue.userId = myuser and activeStatus > 0;       
    -- select ue.ITfiled into ITfiled from user_employment ue where ue.userId = myuser and activeStatus > 0;       
	if (debug) then select 'ITfiled,lender_isITreqd,banker', ITfiled,lender_isITreqd,banker; end if;
	 if (ITfiled = 0 and lender_isITreqd = 1) then
		set errmsg = 'Lender requires documented income in the form of ITR/payslips';									
		if (debug) then select 'errmsg', errmsg; end if;
	 end if;	
	if (productOrLoanApplicable = 0) then
		if (debug) then select 'product not Applicable for  ', banker, producttypeid; end if;
		if (producttypeid = 99) then
			set errmsg = 'Lender does not provide offers for property not identified cases properties';
		end if;    
		if (producttypeid = 3) then
			set errmsg = 'Lender does not provide offers for purchase of residential plots properties';
		end if;
		if (producttypeid = 6) then
			set errmsg = 'Lender does not provide offers for residential properties in LAP for Salaried people';
		end if;
		if (producttypeid = 7) then
			set errmsg = 'Lender does not provide offers for commerical properties in LAP for Salaried people';
		end if;
		if (errmsg != ' ') then
			if (debug) then select 'inserting lender reject', banker,producttypeid,errmsg,rejecttemp; end if;
			call insert_lender_reject(myuser,banker,errmsg,rejecttemp);				
		end if;
	else -- if productOrLoanApplicable = 1	
	    -- Commented lines not needed as we have split up into 2 parts , but still it is in testing phase
        -- for other product types also if this works out, we can remove the commented part -- priyaraj 20-03-2023
		/*if (banker = 0) then -- As lender 0 applicability column value is 1, we have to do this as VC supports everything
			set errmsg = ' ';
		end if;
		if (errmsg != ' ') then
			if (debug) then select 'inserting lender reject', banker,producttypeid,errmsg,rejecttemp; end if;
			call insert_lender_reject(myuser,banker,errmsg,rejecttemp);
		else -- As we need to calculate LPI wherever there is no rejection occured, we need to calculate now. Priyaraj 21 - 02 -2023
			if (debug) then select 'banker', banker,producttypeid,errmsg; end if; */
		-- Added by Geetha on 31/03/2023 to move the BT lender match checks from getmyLPi_ALL_Lenders to this SP. The below logic has been commented in the original SP
		select f.loanTypeId, f.currentLender into myloanid, BTLender from asset_financing f where f.userid=myuser and activeStatus>0;
		if (debug) then select 'Loan type Id and BT lender',BTLender, myloanid ; end if;  
		if myloanid = 2 then 			
			select l.lpilenderid into MappedLender from ref_lender_mapping l where l.loanlenderid=BTLender and l.lpilenderid=banker;
 			if (debug) then select ' After Lender Mapping', BTLender, MappedLender ; end if;   
 		end if;
		if(myloanid=2) and (banker=MappedLender) then											
			if (debug) then select 'inside loan id = 2 and banker = mapped lender', MappedLender, banker, myLPi; end if;
			set errmsg = 'BT Lender match';
			if (debug) then select 'inserting lender reject', MappedLender, banker; end if;
			call insert_lender_reject(myuser,banker,errmsg,rejecttemp);											   
		else					
			call getmyLPi_new(myuser, banker,count1, 0, myLPi);
		end if;
		-- end if;					

	end if;	-- end of if productOrLoanApplicable

END