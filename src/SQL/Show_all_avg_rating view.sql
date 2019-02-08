DROP VIEW IF EXISTS Show_All_Avg_Rating;
CREATE VIEW Show_All_Avg_Rating AS
	SELECT	 
		shoe.name AS Shoe, 
		Avg_Review_Rating(shoe.id) AS 'Avg. Rating', 
		grade.rating
	FROM shoe
	LEFT JOIN review ON shoe.ID = review.shoeID
	LEFT JOIN grade on review.gradeID = grade.ID
	GROUP BY shoe.name
    ORDER BY 1;
