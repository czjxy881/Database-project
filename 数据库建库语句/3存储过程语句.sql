USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[学分_Find]    Script Date: 12/09/2013 01:13:30 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.2
-- Description:	用于查询学分
-- =============================================
CREATE PROCEDURE [dbo].[学分_Find]
		@学号 nchar(8)=NULL
AS
BEGIN
	select 学号,姓名,性别,ISNULL(平均分, 0.00)as 平均分,ISNULL(已完成学分, 0) as 已完成学分 ,ISNULL(未完成学分, 0)as 未完成学分  from 总学分 
	where 学号=@学号
	
end


GO

/****** Object:  StoredProcedure [dbo].[学生_DEL]    Script Date: 12/09/2013 01:13:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.8
-- Description:	用于删除学生信息
-- =============================================
create PROCEDURE [dbo].[学生_DEL]
		@学号 nchar(9)=NULL
		
AS
delete from 学生 where 学号=@学号
GO


/****** Object:  StoredProcedure [dbo].[学生_Find]    Script Date: 12/09/2013 01:14:15 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.2
-- Description:	用于学生表查询，传入参数 班号，学号，姓名，性别，出生日期
-- =============================================
CREATE PROCEDURE [dbo].[学生_Find]
		@班号 nchar(6)=NULL,
		@学号 nchar(9)=NULL,
		@姓名 nchar(10)=NULL,
		@性别 nchar(1)=NULL,
		@出生日期 smalldatetime=NULL
AS
BEGIN
	select * from 学生
	where (
		(学号=@学号 or @学号 is NULL) and
		(班号=@班号 or @班号 is NULL) and
		(姓名=@姓名 or @姓名 is NULL) and
		(性别=@性别 or @性别 is NULL) and
		(出生日期=@出生日期 or @出生日期 is NULL)
	)
end


GO


/****** Object:  StoredProcedure [dbo].[学生_INS]    Script Date: 12/09/2013 01:14:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.2
-- Description:	用于学生表插入，传入参数 班号，学号，姓名，性别，出生日期
-- =============================================
CREATE PROCEDURE [dbo].[学生_INS]
		@班号 nchar(6)=NULL,
		@学号 nchar(9)=NULL,
		@姓名 nchar(10)=NULL,
		@性别 nchar(1)=NULL,
		@出生日期 smalldatetime=NULL
AS
BEGIN transaction
	insert 学生(班号,学号,姓名,性别,出生日期) values(@班号,@学号,@姓名,@性别,@出生日期);
	IF @@error<>0
	BEGIN
	  ROLLBACK transaction
	  Return 1
	END
	commit transaction
	return 0


GO



/****** Object:  StoredProcedure [dbo].[学生_UDA]    Script Date: 12/09/2013 01:14:45 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.8
-- Description:	用于修改学生信息
-- =============================================
create PROCEDURE [dbo].[学生_UDA]
		@班号 nchar(6)=NULL,
		@学号 nchar(9)=NULL,
		@姓名 nchar(10)=NULL,
		@性别 nchar(1)=NULL,
		@出生日期 smalldatetime=NULL
		
AS
BEGIN transaction
	update 学生
		set 班号=isnull(@班号,班号),姓名=ISNULL(@姓名,姓名),性别=ISNULL(@性别,性别),出生日期=ISNULL(@出生日期,出生日期)
	where 学号=@学号
			
	IF @@error<>0
	BEGIN
	  ROLLBACK transaction
	  Return 1
	END
	commit transaction
	return 0


GO


/****** Object:  StoredProcedure [dbo].[专业_Find]    Script Date: 12/09/2013 01:15:01 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.8
-- Description:	用于专业表查询
-- =============================================
create PROCEDURE [dbo].[专业_Find]
AS
BEGIN 
	select 专业号,专业名 from 专业 
END
GO

