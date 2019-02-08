DROP FUNCTION IF EXISTS Avg_Review_Rating;
delimiter //
CREATE FUNCTION Avg_Review_Rating (Shoe_ID int) returns double
	DETERMINISTIC
BEGIN
	DECLARE NrOfReviews int default 0;
	DECLARE	avgRating double default 0.0;
	SELECT COUNT(review.shoeID) from review where review.shoeID = Shoe_ID into NrOfReviews;
	SELECT ROUND(SUM(review.gradeID)/NrOfReviews, 1) from review where review.shoeID = Shoe_ID INTO avgRating;
	RETURN avgRating;
END//

delimiter ;