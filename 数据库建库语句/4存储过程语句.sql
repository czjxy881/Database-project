USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[班级_DEL]    Script Date: 12/18/2013 00:57:01 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[班级_DEL] 
  @班号 nchar(6)=NULL
as
update 学生 set 班号=NULL where 班号=@班号
delete  班级 where 班号=@班号
GO


USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[班级_Find]    Script Date: 12/18/2013 00:57:05 ******/
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
	select 班号,专业名,班主任,入学年份 from 班级,专业 where 班号=@班号 and 班级.专业号=专业.专业号
END
GO
USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[班级_UDA]    Script Date: 12/18/2013 00:57:10 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.8
-- Description:	用于班级表更新
-- =============================================
CREATE PROCEDURE [dbo].[班级_UDA]
		@班号 nchar(6)=NULL,
		@专业号 int=NULL,
		@班主任 nchar(10)=NULL,
		@入学年份 int=NULL
		
AS
BEGIN transaction
	IF EXISTS (select *  from 班级 where 班号=@班号)
	  update 班级 set 专业号=@专业号,班主任=@班主任,入学年份=@入学年份 where 班号=@班号
	
	else
	  insert 班级(班号,专业号,班主任,入学年份) values(@班号,@专业号,@班主任,@入学年份);
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

/****** Object:  StoredProcedure [dbo].[成绩_DEL]    Script Date: 12/18/2013 00:57:15 ******/
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

/****** Object:  StoredProcedure [dbo].[成绩_Find]    Script Date: 12/18/2013 00:57:19 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.8
-- Description:	用于查询成绩详情
-- =============================================
CREATE PROCEDURE [dbo].[成绩_Find]
		@学号 nchar(8)=NULL
AS
BEGIN
	select 课程号,课程名,课程类型,学分,成绩,补考成绩  from 全部成绩 
	where 学号=@学号
	
end


GO
USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[成绩_Find2]    Script Date: 12/18/2013 00:57:23 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.8
-- Description:	用于查询成绩详情
-- =============================================
create PROCEDURE [dbo].[成绩_Find2]
		@学号 nchar(8)=NULL
AS
BEGIN
	select 专业名,学期,全部成绩.课程号,课程名,课程类型,学分,成绩,补考成绩  from 全部成绩 left join 教学计划  on (教学计划.课程号=全部成绩.课程号 and  全部成绩.专业号=教学计划.专业号)
	where 学号=@学号
	
end


GO
USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[成绩_INS]    Script Date: 12/18/2013 00:57:27 ******/
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

/****** Object:  StoredProcedure [dbo].[成绩_UDA]    Script Date: 12/18/2013 00:57:31 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--insert 课表  values('HA2113001','030511','教师1')
CREATE proc [dbo].[成绩_UDA]
	@学号 nchar(8)=NULL,
	@课程号 nchar(9)=NULL,
	@成绩 int=0,
	@补考成绩 int=NULL
as
if EXISTS (select * from 成绩 where 学号=@学号 and 课程号=@课程号)
	update 成绩 set 成绩=@成绩,补考成绩=@补考成绩  where 学号=@学号 and 课程号=@课程号
else
	insert 成绩 values(@学号,@课程号,@成绩,@补考成绩,NULL)
	
GO

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[成绩课程_FIND]    Script Date: 12/18/2013 00:57:36 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[成绩课程_FIND]
	@课程号 nchar(9)=NULL,
	@学号 nchar(8)=NULL
as
select 学号,姓名,成绩,补考成绩 from 全部成绩 where 课程号=@课程号 and (学号=@学号 or @学号 is null) 
union
select 学号,姓名,NULL,NULL from 学生 where (学号=@学号 or @学号 is null)  and  not  exists 
(select * from 全部成绩 where 课程号=@课程号 and (全部成绩.学号=学生.学号))
GO
USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[教师_Find]    Script Date: 12/18/2013 00:57:39 ******/
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

/****** Object:  StoredProcedure [dbo].[教学计划_DEL]    Script Date: 12/18/2013 00:57:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[教学计划_DEL] 
    @学期 int=null,
    @专业号 int=null,
    @课程号 nchar(9)=null
as
delete 教学计划 where (学期=@学期 and 专业号=@专业号 and 课程号=@课程号)
GO
USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[教学计划_Find]    Script Date: 12/18/2013 00:57:49 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[教学计划_Find] 
    @学期 int=null,
    @专业号 int=null
as
select 课程号,课程名,学分,课程类型 from  教学计划总图
   where 学期=@学期 and 专业号=@专业号
GO
USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[教学计划_Find2]    Script Date: 12/18/2013 00:57:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[教学计划_Find2] 
    @学期 int=null,
    @专业号 int=null
as
select 课程号,课程名,学分,课程类型 from  教学计划总图
   where  专业号=@专业号
GO
USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[教学计划_INS]    Script Date: 12/18/2013 00:57:56 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[教学计划_INS] 
    @学期 int=null,
    @专业号 int=null,
    @课程号 nchar(9)=null
as
insert 教学计划 values(@学期,@专业号,@课程号)
GO

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[教学计划学分_Find]    Script Date: 12/18/2013 00:57:59 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[教学计划学分_Find] 
    @学期 int=null,
    @专业号 int=null
as
select 必修学分,限选学分,任选学分  from  教学计划学分
   where 学期=@学期 and 专业号=@专业号
GO

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[开除]    Script Date: 12/18/2013 00:58:08 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[开除]
	@必修底线 int=28,
	@任选底线 int=18,
	@学期底线 int=8
as
select t.学号,姓名,必修未完成学分,平均分 from 总学分 right join(
select 学号 from 总未完成学分 where 必修未完成学分>=@必修底线 or 任选未完成学分 >=@任选底线
union
select 学号 from 学期学分详细 where 必修未完成学分>=@学期底线) as t on 总学分.学号=t.学号

GO
USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[课程_Find]    Script Date: 12/18/2013 00:58:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

--insert 课表  values('HA2113001','030511','教师1')
CREATE proc [dbo].[课程_Find]
	@课程号 nchar(9)=NULL
as
select 课程号,课程名 from 课程 where (课程号=@课程号 or @课程号 is NULL)
GO

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[课程_INS]    Script Date: 12/18/2013 00:58:18 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.2
-- Description:	用于学生表插入，传入参数 班号，学号，姓名，性别，出生日期
-- =============================================
CREATE PROCEDURE [dbo].[课程_INS]
		@课程号 nchar(9)=NULL,
		@课程名 nchar(25)=NULL,
		@学分 int=NULL,
		@课程类型 nchar(2)=NULL
		
AS
BEGIN transaction
	insert 课程 values(@课程号,@课程名,@学分,@课程类型);
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

/****** Object:  StoredProcedure [dbo].[学分_Find]    Script Date: 12/18/2013 00:58:23 ******/
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
	select distinct 学号,姓名,性别,ISNULL(平均分, 0.00)as 平均分,ISNULL(必修平均分, 0.00)as 必修平均分,ISNULL(已完成学分, 0) as 总学分,ISNULL(必修学分, 0) as 必修学分 ,ISNULL(选修学分, 0)as 选修学分,ISNULL( 必修未完成学分, 0) as 必修未完成学分  from 总学分 
	where 学号=@学号
	
end


GO
USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[学生_DEL]    Script Date: 12/18/2013 00:58:29 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		jxy
-- Create date: 2013.12.8
-- Description:	用于修改学生信息
-- =============================================
create PROCEDURE [dbo].[学生_DEL]
		@学号 nchar(9)=NULL
		
AS
delete from 学生 where 学号=@学号
GO

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[学生_Find]    Script Date: 12/18/2013 00:58:34 ******/
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

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[学生_INS]    Script Date: 12/18/2013 00:58:37 ******/
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

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[学生_UDA]    Script Date: 12/18/2013 00:58:40 ******/
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

USE [学籍管理系统]
GO

/****** Object:  StoredProcedure [dbo].[专业_Find]    Script Date: 12/18/2013 00:58:44 ******/
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












