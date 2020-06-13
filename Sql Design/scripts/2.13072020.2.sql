/****** 
Script showing types of lookup calls/ Inserts/ Updates/ Deactivations required of the Noted-DB

Author: 5Gears0Chill
Date: 13062020
******/


-- Notebook Count Lookup
SELECT COUNT(*) FROM NoteBook;

-- Notebook Paged View 
/***
Selects from the table NoteBook a paginated result set for 20 items and orders the notebooks by title. 
Shows the table might require a createdOn/ UpdatedOn to allow for better viewing experience
***/
SELECT * 
FROM (SELECT ROW_NUMBER() 
		OVER(ORDER BY Title) AS RowNumber, * 
		FROM NoteBook ) AS RowConstrainedResult
Where RowNumber >= 1
AND RowNumber < 20
ORDER BY RowNumber;

-- Paginated Search Result
SELECT * 
FROM (SELECT ROW_NUMBER() 
		OVER(ORDER BY Title) AS RowNumber, * 
		FROM NoteBook 
		WHERE Title like '%e%') AS RowConstrainedResult
Where RowNumber >= 1
AND RowNumber < 20
ORDER BY RowNumber;

-- The same above queries exist for notes but with more complexity

-- Paginated Note Search for a specific Notebook (id=1) ordered by the createdOn date
SELECT * 
FROM (SELECT ROW_NUMBER()
		OVER (ORDER BY na.CreatedOn) AS 
		RowNumber, n.NoteId,n.NoteBookId ,Title, Content,
		CreatedOn, UpdatedOn,
		nc.Description as Category,
		FileSize
		FROM Note as n
		INNER JOIN  NoteAttribute AS na 
		ON na.NoteId = n.NoteId 
		INNER JOIN NoteCategory AS nc 
		ON nc.CategoryId = na.CategoryId 
		WHERE n.NoteBookId = 2) AS RowConstrainedResult
WHERE RowNumber >= 1
AND RowNumber < 20
ORDER BY RowNumber

--Detailed Note Selection Query for a specific note

SELECT n.*, na.CreatedOn, na.UpdatedOn, na.FileSize, nc.Description as Category
FROM Note AS n
INNER JOIN NoteAttribute AS na
ON na.NoteId = n.NoteId
INNER JOIN NoteCategory AS nc
ON nc.CategoryId = na.CategoryId
where n.NoteId = 2

-- Get Note Categories

SELECT nt.NoteId, t.Description AS Tag, t.IsActive
FROM NoteTags AS nt
INNER JOIN Tags AS t
ON t.TagId = nt.TagId
WHERE nt.NoteId = 1

--Get Note Resources
SELECT r.Title, r.Content, rt.Description as Type, r.IsActive
FROM Resource AS r
INNER JOIN ResourceType AS rt
ON rt.TypeId = r.TypeId
WHERE r.NoteId = 1

--Get All Categories
SELECT * 
FROM NoteCategory AS nc
WHERE nc.IsActive = 1

--Get All Types
SELECT * 
FROM ResourceType AS nc
WHERE nc.IsActive = 1

