DROP PROCEDURE IF EXISTS AddToCart;
DELIMITER //
CREATE PROCEDURE AddToCart(IN customer_ID int, IN order_ID int, IN shoe_ID int)
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
	IF (select stock from shoe where shoe.id = shoe_id) != 0 THEN 
		IF order_ID is null THEN
			INSERT INTO placed_order (customerID) values (customer_ID);
			INSERT INTO order_content (placed_orderID, shoeID) VALUES (LAST_INSERT_ID(), shoe_id);
		
		ELSE 
			IF (SELECT COUNT(placed_order.id) FROM placed_order WHERE placed_order.id = order_ID) = 0 THEN
				INSERT INTO placed_order (customerID) VALUES (customer_ID);
				INSERT INTO order_content (shoeID, placed_orderID) VALUES (shoe_ID, LAST_INSERT_ID());
		
			ELSE 
				INSERT INTO order_content (shoeID, placed_orderID) VALUES (shoe_ID, order_ID);
			END IF;
			
		END IF;
	
	
	UPDATE shoe
	SET stock = stock -1 where id = shoe_ID;
    SELECT 'DONE' AS message;
    
	ELSE 
		SELECT 'ERROR' AS message;
	END IF;
    
	commit;
end//
DELIMITER ;