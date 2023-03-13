-- Reverting back the score value to 0 for lender 10
UPDATE `staging`.`ref_ltv` SET `score` = '0' WHERE (`refLTVId` = '36');
