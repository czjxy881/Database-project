USE [学籍管理系统]
GO

/****** Object:  UserDefinedFunction [dbo].[真实成绩]    Script Date: 12/18/2013 00:47:21 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE FUNCTION [dbo].[真实成绩]
(	
	-- Add the parameters for the function here
	@补考成绩 int,
	@成绩 int
)
RETURNS int 
AS
begin

RETURN 
(
	CASE 
WHEN @补考成绩 IS null THEN @成绩 
ELSE 
case 
when @补考成绩>60 then 60 
else @补考成绩 end end
)
end

GO


