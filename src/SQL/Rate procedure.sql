DROP PROCEDURE IF EXISTS Rate;
delimiter //
CREATE PROCEDURE Rate (in customer_ID int, in product_ID int, grade_ID int, user_comment varchar(140))
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
		BEGIN 
		ROLLBACK;
			SELECT ('SQLEXCEPTION occurred, rollback done');
	END;
		
		DECLARE EXIT HANDLER FOR SQLWARNING 
		BEGIN
          ROLLBACK;
			SELECT ('SQLWARNING occurred, rollback done');
	END;
	
	DECLARE EXIT HANDLER FOR 1062
		BEGIN
			ROLLBACK;
			SELECT ('unique constraint broken, rollback done') as error;
	END;
	
	START TRANSACTION;
		INSERT INTO review (shoeID, customerID, gradeID, comment) values (product_ID, customer_ID, grade_ID, user_comment);
	commit;
end//
delimiter ;
