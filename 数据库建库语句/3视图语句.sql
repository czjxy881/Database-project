USE [学籍管理系统]
GO

/****** Object:  View [dbo].[全部成绩]    Script Date: 12/18/2013 00:48:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[全部成绩]
AS
SELECT     dbo.专业.专业名, dbo.专业.专业号, dbo.学生.班号, dbo.成绩.学号, dbo.学生.姓名, dbo.学生.性别, dbo.成绩.课程号, dbo.课程.课程名, dbo.课程.课程类型, dbo.课程.学分, dbo.成绩.成绩, 
                      dbo.成绩.补考成绩, dbo.成绩.真实成绩
FROM         dbo.学生 INNER JOIN
                      dbo.成绩 ON dbo.学生.学号 = dbo.成绩.学号 INNER JOIN
                      dbo.课程 ON dbo.成绩.课程号 = dbo.课程.课程号 INNER JOIN
                      dbo.班级 ON dbo.学生.班号 = dbo.班级.班号 INNER JOIN
                      dbo.专业 ON dbo.班级.专业号 = dbo.专业.专业号
GROUP BY dbo.成绩.学号, dbo.学生.姓名, dbo.学生.性别, dbo.学生.班号, dbo.课程.课程名, dbo.课程.学分, dbo.成绩.成绩, dbo.成绩.课程号, dbo.课程.课程类型, dbo.成绩.补考成绩, dbo.专业.专业号, 
                      dbo.专业.专业名, dbo.成绩.真实成绩

GO

USE [学籍管理系统]
GO

/****** Object:  View [dbo].[及格成绩]    Script Date: 12/18/2013 00:50:08 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[及格成绩]
AS
SELECT     班号, 学号, 姓名, 性别, 课程号, 课程名, 学分, 真实成绩 AS 成绩, 课程类型
FROM         dbo.全部成绩
WHERE     (真实成绩 >= 60)

GO


USE [学籍管理系统]
GO

/****** Object:  View [dbo].[不及格成绩]    Script Date: 12/18/2013 00:50:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[不及格成绩]
AS
SELECT     班号, 学号, 姓名, 性别, 课程号, 课程名, 学分, 真实成绩 AS 成绩, 课程类型
FROM         dbo.全部成绩
WHERE     (真实成绩 < 60)

GO


USE [学籍管理系统]
GO

/****** Object:  View [dbo].[必修完成学分]    Script Date: 12/18/2013 00:52:11 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[必修完成学分]
AS
SELECT     学号, 姓名, CAST(SUM(成绩 * 学分) / CAST(SUM(学分) AS float) AS numeric(5, 2)) AS 平均分, SUM(学分) AS 已完成学分
FROM         dbo.及格成绩
WHERE     (课程类型 <> '任选')
GROUP BY 姓名, 学号

GO

USE [学籍管理系统]
GO

/****** Object:  View [dbo].[必修未完成学分]    Script Date: 12/18/2013 00:53:27 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[必修未完成学分]
AS
SELECT     学号, 姓名, SUM(学分) AS 必修未完成学分
FROM         dbo.不及格成绩
WHERE     (课程类型 <> '任选')
GROUP BY 学号, 姓名

GO


USE [学籍管理系统]
GO

/****** Object:  View [dbo].[已完成学分]    Script Date: 12/18/2013 00:54:03 ******/
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


USE [学籍管理系统]
GO

/****** Object:  View [dbo].[总学分]    Script Date: 12/18/2013 00:54:20 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

/*update 总学分
set 已完成学分=0 where 已完成学分=null*/
CREATE VIEW [dbo].[总学分]
AS
SELECT     dbo.学生.学号, dbo.学生.姓名, dbo.学生.性别, dbo.已完成学分.平均分, dbo.必修完成学分.平均分 AS 必修平均分, dbo.已完成学分.已完成学分, dbo.必修完成学分.已完成学分 AS 必修学分, 
                      dbo.已完成学分.已完成学分 - dbo.必修完成学分.已完成学分 AS 选修学分, dbo.必修未完成学分.必修未完成学分
FROM         dbo.学生 INNER JOIN
                      dbo.必修完成学分 ON dbo.学生.学号 = dbo.必修完成学分.学号 INNER JOIN
                      dbo.必修未完成学分 ON dbo.学生.学号 = dbo.必修未完成学分.学号 LEFT OUTER JOIN
                      dbo.已完成学分 ON dbo.学生.学号 = dbo.已完成学分.学号

GO

USE [学籍管理系统]
GO

/****** Object:  View [dbo].[教学计划总图]    Script Date: 12/18/2013 00:54:57 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[教学计划总图]
AS
SELECT     dbo.教学计划.学期, dbo.教学计划.专业号, dbo.教学计划.课程号, dbo.课程.课程名, dbo.课程.学分, dbo.课程.课程类型
FROM         dbo.教学计划 INNER JOIN
                      dbo.课程 ON dbo.教学计划.课程号 = dbo.课程.课程号

GO


USE [学籍管理系统]
GO

/****** Object:  View [dbo].[教学计划学分]    Script Date: 12/18/2013 00:55:09 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[教学计划学分]
AS
SELECT     bi.学期, bi.专业号, ISNULL(bi.必修学分, 0) AS 必修学分, ISNULL(xuan.任选学分, 0) AS 任选学分, ISNULL(xian.限选学分, 0) AS 限选学分
FROM         (SELECT     学期, 专业号, SUM(学分) AS 必修学分
                       FROM          dbo.教学计划总图
                       WHERE      (课程类型 = '必修')
                       GROUP BY 学期, 专业号) AS bi FULL OUTER JOIN
                          (SELECT     学期, 专业号, SUM(学分) AS 任选学分
                            FROM          dbo.教学计划总图 AS 教学计划总图_2
                            WHERE      (课程类型 = '任选')
                            GROUP BY 学期, 专业号) AS xuan ON xuan.学期 = bi.学期 AND xuan.专业号 = bi.专业号 FULL OUTER JOIN
                          (SELECT     学期, 专业号, SUM(学分) AS 限选学分
                            FROM          dbo.教学计划总图 AS 教学计划总图_1
                            WHERE      (课程类型 = '限选')
                            GROUP BY 学期, 专业号) AS xian ON xian.学期 = bi.学期 AND xian.专业号 = bi.专业号

GO

USE [学籍管理系统]
GO

/****** Object:  View [dbo].[学期学分]    Script Date: 12/18/2013 00:55:33 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[学期学分]
AS
SELECT     dbo.全部成绩.学号, dbo.全部成绩.姓名, dbo.教学计划.学期, dbo.全部成绩.课程类型, SUM(dbo.全部成绩.学分) AS 未完成学分
FROM         dbo.全部成绩 LEFT OUTER JOIN
                      dbo.教学计划 ON dbo.教学计划.课程号 = dbo.全部成绩.课程号 AND dbo.全部成绩.专业号 = dbo.教学计划.专业号
WHERE     (dbo.全部成绩.真实成绩 < 60)
GROUP BY dbo.全部成绩.学号, dbo.全部成绩.姓名, dbo.教学计划.学期, dbo.全部成绩.课程类型

GO

USE [学籍管理系统]
GO

/****** Object:  View [dbo].[学期学分详细]    Script Date: 12/18/2013 00:55:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[学期学分详细]
AS
SELECT     org.学号, org.姓名, org.学期, ISNULL(bi.必修未完成学分, 0) AS 必修未完成学分, ISNULL(xian.限选未完成学分, 0) AS 限选未完成学分, ISNULL(xuan.任选未完成学分, 0) AS 任选未完成学分
FROM         (SELECT DISTINCT 学号, 姓名, 学期
                       FROM          dbo.学期学分) AS org LEFT OUTER JOIN
                          (SELECT     学号, 学期, 未完成学分 AS 必修未完成学分
                            FROM          dbo.学期学分 AS 学期学分_3
                            WHERE      (课程类型 = '必修')) AS bi ON org.学号 = bi.学号 AND (org.学期 = bi.学期 OR
                      org.学期 IS NULL AND bi.学期 IS NULL) LEFT OUTER JOIN
                          (SELECT     学号, 学期, 未完成学分 AS 限选未完成学分
                            FROM          dbo.学期学分 AS 学期学分_2
                            WHERE      (课程类型 = '限选')) AS xian ON xian.学号 = org.学号 AND (org.学期 = xian.学期 OR
                      org.学期 IS NULL AND xian.学期 IS NULL) LEFT OUTER JOIN
                          (SELECT     学号, 学期, 未完成学分 AS 任选未完成学分
                            FROM          dbo.学期学分 AS 学期学分_1
                            WHERE      (课程类型 = '任选')) AS xuan ON xuan.学号 = org.学号 AND (org.学期 = xuan.学期 OR
                      org.学期 IS NULL AND xuan.学期 IS NULL)

GO


USE [学籍管理系统]
GO

/****** Object:  View [dbo].[总未完成学分]    Script Date: 12/18/2013 00:56:04 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[总未完成学分]
AS
SELECT     学号, 姓名, SUM(必修未完成学分) AS 必修未完成学分, SUM(限选未完成学分) AS 限选未完成学分, SUM(任选未完成学分) AS 任选未完成学分
FROM         dbo.学期学分详细
GROUP BY 学号, 姓名

GO

