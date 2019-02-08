Delimiter //
CREATE TRIGGER out_of_stock 
	after update on shoe
	for each row
BEGIN	
	if (new.stock) = 0 then
		insert into OutOfStock (shoeID) values (old.ID);
	end if;
	
end//

Delimiter ;
