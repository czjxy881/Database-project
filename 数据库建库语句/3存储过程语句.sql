USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[班级_Find]    Script Date: 12/09/2013 16:19:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.8
-- Description:	用于班级表查询
-- =============================================
CREATE PROCEDURE [dbo].[班级_Find]
		@班号 nchar(6)=NULL
AS
BEGIN 
	select 班号,专业名,班主任 from 班级,专业 where 班号=@班号 and 班级.专业号=专业.专业号
END
GO


USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[班级_UDA]    Script Date: 12/09/2013 16:19:17 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.8
-- Description:	用于班级表更新
-- =============================================
create PROCEDURE [dbo].[班级_UDA]
		@班号 nchar(6)=NULL,
		@专业号 int=NULL,
		@班主任 nchar(10)=NULL
		
AS
BEGIN transaction
	IF EXISTS (select *  from 班级 where 班号=@班号)
	  update 班级 set 专业号=@专业号,班主任=@班主任 where 班号=@班号
	
	else
	  insert 班级(班号,专业号,班主任) values(@班号,@专业号,@班主任);
	IF @@error<>0
	BEGIN
	  ROLLBACK transaction
	  Return 1
	END
	commit transaction
	return 0


GO

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[成绩_DEL]    Script Date: 12/09/2013 16:19:22 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--insert 课表  values('HA2113001','030511','教师1')
create proc [dbo].[成绩_DEL]
	@学号 nchar(8)=NULL,
	@课程号 nchar(9)=NULL
as
	delete 成绩  where 学号=@学号 and 课程号=@课程号
	
GO
USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[成绩_Find]    Script Date: 12/09/2013 16:19:27 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.8
-- Description:	用于查询成绩详情
-- =============================================
create PROCEDURE [dbo].[成绩_Find]
		@学号 nchar(8)=NULL
AS
BEGIN
	select 课程号,课程名,学分,成绩  from 全部成绩 
	where 学号=@学号
	
end


GO


USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[成绩_INS]    Script Date: 12/09/2013 16:19:31 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.2
-- Description:	用于学生表插入，传入参数 班号，学号，姓名，性别，出生日期
-- =============================================
create PROCEDURE [dbo].[成绩_INS]
		@学号 nchar(8)=NULL,
		@课程号 nchar(25)=NULL,
		@成绩 int=NULL
		
AS
BEGIN transaction
	insert 成绩(学号,课程号,成绩) values(@学号,@课程号,@成绩);
	IF @@error<>0
	BEGIN
	  ROLLBACK transaction
	  Return 1
	END
	commit transaction
	return 0


GO

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[成绩_UDA]    Script Date: 12/09/2013 16:19:35 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--insert 课表  values('HA2113001','030511','教师1')
create proc [dbo].[成绩_UDA]
	@学号 nchar(8)=NULL,
	@课程号 nchar(9)=NULL,
	@成绩 int=0
as
if EXISTS (select * from 成绩 where 学号=@学号 and 课程号=@课程号)
	update 成绩 set 成绩=@成绩  where 学号=@学号 and 课程号=@课程号
else
	insert 成绩 values(@学号,@课程号,@成绩)
	
GO

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[教师_Find]    Script Date: 12/09/2013 16:19:41 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--insert 课表  values('HA2113001','030511','教师1')
CREATE proc [dbo].[教师_Find]
	@学号 nchar(8)=NULL
as
select '班主任',班主任 from 班级,学生 where 学生.班号=班级.班号 and 学生.学号=@学号
union
select 课程名,任课教师 from 课程,课表,学生 where 学生.班号=课表.班级号 and 学生.学号=@学号 and 课表.课程号=课程.课程号
union
select 课程名,任课教师 from 全部成绩,课表 where 学号=@学号 and 课表.课程号=全部成绩.课程号
GO

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[开除]    Script Date: 12/09/2013 16:19:47 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[开除]
	@底线 int=null
as
select 学号,姓名,未完成学分,平均分 from 总学分 where 未完成学分 >=@底线
GO

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[课程_Find]    Script Date: 12/09/2013 16:19:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--insert 课表  values('HA2113001','030511','教师1')
create proc [dbo].[课程_Find]
as
select 课程号,课程名 from 课程
GO

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[课程_INS]    Script Date: 12/09/2013 16:19:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.2
-- Description:	用于学生表插入，传入参数 班号，学号，姓名，性别，出生日期
-- =============================================
create PROCEDURE [dbo].[课程_INS]
		@课程号 nchar(9)=NULL,
		@课程名 nchar(25)=NULL,
		@学分 int=NULL
		
AS
BEGIN transaction
	insert 课程(课程号,课程名,学分) values(@课程号,@课程名,@学分);
	IF @@error<>0
	BEGIN
	  ROLLBACK transaction
	  Return 1
	END
	commit transaction
	return 0


GO



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

