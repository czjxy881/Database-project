USE [学籍管理系统]
GO

/****** Object:  View [dbo].[全部成绩]    Script Date: 12/09/2013 01:08:56 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[全部成绩]
AS
SELECT     dbo.学生.班号, dbo.成绩.学号, dbo.学生.姓名, dbo.学生.性别, dbo.成绩.课程号, dbo.课程.课程名, dbo.课程.学分, dbo.成绩.成绩
FROM         dbo.学生 INNER JOIN
                      dbo.成绩 ON dbo.学生.学号 = dbo.成绩.学号 INNER JOIN
                      dbo.课程 ON dbo.成绩.课程号 = dbo.课程.课程号
GROUP BY dbo.成绩.学号, dbo.学生.姓名, dbo.学生.性别, dbo.学生.班号, dbo.课程.课程名, dbo.课程.学分, dbo.成绩.成绩, dbo.成绩.课程号

GO

/****** Object:  View [dbo].[及格成绩]    Script Date: 12/09/2013 01:09:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[及格成绩]
AS
SELECT     班号, 学号, 姓名, 性别, 课程号, 课程名, 学分, 成绩
FROM         dbo.全部成绩
WHERE     (成绩 >= 60)

GO

/****** Object:  View [dbo].[及格成绩]    Script Date: 12/09/2013 01:09:13 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[及格成绩]
AS
SELECT     班号, 学号, 姓名, 性别, 课程号, 课程名, 学分, 成绩
FROM         dbo.全部成绩
WHERE     (成绩 >= 60)

GO

/****** Object:  View [dbo].[总学分]    Script Date: 12/09/2013 01:10:19 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[总学分]
AS
SELECT     dbo.学生.学号, dbo.学生.姓名, dbo.学生.性别, dbo.已完成学分.平均分, dbo.已完成学分.已完成学分, dbo.未完成学分.未完成学分
FROM         dbo.学生 LEFT OUTER JOIN
                      dbo.已完成学分 ON dbo.学生.学号 = dbo.已完成学分.学号 LEFT OUTER JOIN
                      dbo.未完成学分 ON dbo.学生.学号 = dbo.未完成学分.学号

GO

/****** Object:  View [dbo].[已完成学分]    Script Date: 12/09/2013 01:10:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[已完成学分]
AS
SELECT     学号, 姓名, CAST(SUM(成绩 * 学分) / CAST(SUM(学分) AS float) AS numeric(5, 2)) AS 平均分, SUM(学分) AS 已完成学分
FROM         dbo.及格成绩
GROUP BY 姓名, 学号

GO

/****** Object:  View [dbo].[未完成学分]    Script Date: 12/09/2013 01:11:07 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[未完成学分]
AS
SELECT     学号, 姓名, SUM(学分) AS 未完成学分
FROM         dbo.不及格成绩
GROUP BY 姓名, 学号

GO