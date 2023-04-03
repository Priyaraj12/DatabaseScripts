CREATE DEFINER=`root`@`localhost` PROCEDURE `getmyLPi_ALL_Lenders`(in myuser int, in yesNo int,  in debug int)
BEGIN
		declare banker, id1, id2, status1, count1, temp, temp1, BTLender,rejecttemp,myloanid,MappedLender,userCrifScore,coappid int; 
        declare done  boolean;
        declare myLPi decimal (10,4);
        declare myuseremail,errmsg varchar(100);
        declare occupationtypeId int; -- added by Priyaraj on Feb 8, 2023 for rejecting if occupationtypeid = 1 and producttypeid = 6 or 7 (for lender = vastu) when user selects it from frontend
        declare productOrLoanApplicable tinyint; -- added by Priyaraj on Mar 5,2023 to get productApplicability column      			
		-- We choose to calculate LPi for the banks that support specific product and specific loantype    
		-- Cursor to be used when no valid creditscore is available
		-- DECLARE curs1 CURSOR FOR SELECT l.lenderid FROM lender l
        -- Added the column applicability in cursor1 and cursor2 to check its value in the loop by Priyaraj on 15-03-2023
        -- Adding addition condition to support occupationtypeid matching by Priyaraj on March 17, 2023
        DECLARE curs1 CURSOR FOR SELECT l.lenderid,lp.applicability FROM lender l
		inner join lender_product_loan lp on l.lenderid =lp.lenderid 
		inner join asset_financing a on lp.producttypeid = a.producttypeid and lp.loantypeid = a.loanTypeId
		where l.activeStatus >0 and lp.activestatus>0 and a.activeStatus>0 and a.userid=myuser
        and lp.occupationtypeid in (select ue.occupationtypeId from user_employment ue where ue.userId = myuser and activeStatus > 0);
		-- DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
	
		-- We choose to calculate LPi for the banks that support specific product and specific loantype    
		-- Cursor to be used when valid creditscore is available
		DECLARE curs2 CURSOR FOR SELECT l.lenderid,lp.applicability FROM lender l
		inner join lender_product_loan lp on l.lenderid =lp.lenderid 
		inner join asset_financing a on lp.producttypeid = a.producttypeid and lp.loantypeid = a.loanTypeId
		where l.activeStatus >0 and lp.activestatus>0 and a.activeStatus>0 and a.userid=myuser and userCrifScore between l.creditStartRange and l.creditEndRange 
        and lp.occupationtypeid in (select ue.occupationtypeId from user_employment ue where ue.userId = myuser and activeStatus > 0);
		DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
        
		set id2=0;
		set BTLender=0;
		set rejecttemp=0;
		set MappedLender=100;
		set coappid=-1;
					
		select primaryUserId, userLPiStatus, LPiCount, userEmail, subscriptionId into id1,  status1, count1, myuseremail, id2 
		from user_login where userId=myuser and activeStatus > 0;
-- Geetha commented March 31st to consider BT lender mismatch when same loanlender exists for more than one lpilenders 
/*        select f.loanTypeId, f.currentLender into myloanid, BTLender from asset_financing f where f.userid=myuser and activeStatus>0;
-- 	if myloanid = 2 then 			
--  select l.lpilenderid into MappedLender from ref_lender_mapping l where l.loanlenderid=BTLender;
-- 			if (debug) then select 'Lender Mapping', BTLender, MappedLender, done ; end if;   
 	end if; */
		
		set temp=0;
		select count(*) into temp from user_lpi_lenders where activeStatus>0 and userid=myuser;

		set temp1=0;
		select count(*) into temp1 from user_lender_reject where activeStatus>0 and userid=myuser;

	   
		if ifnull(id2,0) = 0 then 
			select max(subscriptionDetailId) into id2 from subscription_details s 
			where s.AssignedUsereMail = myuseremail  and activeStatus > 0;
		end if;
	
		if ifnull(id2,0) = 0 then set yesNo = 0; end if;
		
		if (debug) then select 'before curssor', id1, id2,  status1, count1 , temp, myuseremail, done ; end if;
		if ifnull(id2,0) > 0 then 
			if ifnull(temp,0) = 0 then set yesNo=1; end if;  -- if there are no LPi records then force LPi calculation by setting yesno=1			
		end if;

		if (id1 = 0) and (status1 < 2)  and (count1 < 5) and (yesNo > 0) and (ifnull(id2,0) > 0) then
			begin
				if (debug) then select 'in begin before updating active status'; end if;
					-- New code to set activeStatus to 0 for existing user lpi lenders
				if ifnull(temp,0) > 0 then 
					begin
						update user_lpi_lenders set activeStatus = 0 where userid=myuser and activeStatus>0; 
						if (debug) then select 'setting user_lpi_lenders activeStatus to 0'; end if;
						UPDATE user_lpi_lender_category_scores u SET u.activeStatus=0 where u.primaryUserId=myuser and activeStatus>0 and user_lpi_lender_category_score_id>0;

					end;
				end if;  
				if ifnull(temp1,0) > 0 then 
					update user_lender_reject set activeStatus = 0 where userid=myuser and activeStatus>0; 
				-- 	delete from user_lender_reject where activeStatus>0 and userid=myuser;
					if (debug) then select 'setting user_lender_reject activeStatus to 0'; end if;
				end if;  
					
				
				-- end of new code
				
				set done = FALSE;
				call getUserCBScore(myuser,0,userCrifScore);
				-- Checking for userCreditscore and if it is not done then skipping creditrange check else doing it
				if (userCrifScore<300) then
					begin
						OPEN curs1;
						if (debug) then select 'opening cursor1', done ; end if;
							read_loop: LOOP
								FETCH curs1 INTO banker,productOrLoanApplicable;
								IF done THEN LEAVE read_loop; END IF;
					
                                CALL goNoGoChecks_And_Calculations(myuser,banker,productOrLoanApplicable,count1,debug);                                    
					/*				if(myloanid=2) and (banker=MappedLender) then											
											if (debug) then select 'inside loan id = 2 and banker = mapped lender', MappedLender, banker, myLPi; end if;
											set errmsg = 'BT Lender match';
											if (debug) then select 'inserting lender reject', MappedLender, banker; end if;
											call insert_lender_reject(myuser,banker,errmsg,rejecttemp);											   
										end if;		
					*/
							END LOOP;
						CLOSE curs1;
					end;				
				else -- if >= 300
					begin
						OPEN curs2;
						if (debug) then select 'opening cursor2', done ; end if;
						read_loop: LOOP
							FETCH curs2 INTO banker,productOrLoanApplicable;
				            IF done THEN LEAVE read_loop; END IF;
                    		CALL goNoGoChecks_And_Calculations(myuser,banker,productOrLoanApplicable,count1,debug);
					/*			if(myloanid=2) and (banker=MappedLender) then										
										if (debug) then select 'inside loan id = 2 and banker = mapped lender', MappedLender, banker, myLPi; end if;
										set errmsg = 'BT Lender match';
										if (debug) then select 'inserting lender reject', MappedLender, banker; end if;
										call insert_lender_reject(myuser,banker,errmsg,rejecttemp);										   
								end if;
					*/
						END LOOP;
						CLOSE curs2;
					end;
				end if; -- if userCrifScore 
				update user_login set LPiCount = LpiCount+1, updatedOn = now() where userId=myuser and activeStatus >0; 	
			end;
		end if;
		
		call get_All_Lender_Prospects (myuser); 
		if id1 > 0 then 
			call get_All_Lender_Prospects (id1); 
		end if;
                  

            
END