USE [学籍管理系统]
GO

/****** Object:  Table [dbo].[班级]    Script Date: 12/15/2013 02:28:21 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[班级](
	[班号] [nchar](6) NOT NULL,
	[专业号] [int] NOT NULL,
	[班主任] [nchar](10) NOT NULL,
	[入学年份] [int] NOT NULL,
 CONSTRAINT [PK_班级] PRIMARY KEY CLUSTERED 
(
	[班号] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[班级]  WITH CHECK ADD  CONSTRAINT [FK_班级_专业] FOREIGN KEY([专业号])
REFERENCES [dbo].[专业] ([专业号])
GO

ALTER TABLE [dbo].[班级] CHECK CONSTRAINT [FK_班级_专业]
GO

ALTER TABLE [dbo].[班级]  WITH CHECK ADD  CONSTRAINT [CK_班级] CHECK  (([入学年份]>(1800) AND [入学年份]<(10000)))
GO

ALTER TABLE [dbo].[班级] CHECK CONSTRAINT [CK_班级]
GO





USE [学籍管理系统]
GO

/****** Object:  Table [dbo].[成绩]    Script Date: 12/15/2013 02:08:10 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[成绩](
	[学号] [nchar](8) NOT NULL,
	[课程号] [nchar](9) NOT NULL,
	[成绩] [int] NULL,
	[补考成绩] [int] NULL,
 CONSTRAINT [IX_成绩] UNIQUE NONCLUSTERED 
(
	[学号] ASC,
	[课程号] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[成绩]  WITH CHECK ADD  CONSTRAINT [FK_成绩_课程] FOREIGN KEY([课程号])
REFERENCES [dbo].[课程] ([课程号])
GO

ALTER TABLE [dbo].[成绩] CHECK CONSTRAINT [FK_成绩_课程]
GO

ALTER TABLE [dbo].[成绩]  WITH CHECK ADD  CONSTRAINT [FK_成绩_学生] FOREIGN KEY([学号])
REFERENCES [dbo].[学生] ([学号])
GO

ALTER TABLE [dbo].[成绩] CHECK CONSTRAINT [FK_成绩_学生]
GO

ALTER TABLE [dbo].[成绩]  WITH CHECK ADD  CONSTRAINT [CK_成绩] CHECK  (([成绩]>=(0) AND [成绩]<=(100)))
GO

ALTER TABLE [dbo].[成绩] CHECK CONSTRAINT [CK_成绩]
GO

ALTER TABLE [dbo].[成绩]  WITH CHECK ADD  CONSTRAINT [CK_成绩_t] CHECK  (([成绩] IS NOT NULL AND [补考成绩]>=(0) AND [补考成绩]<=(100) OR [成绩] IS NULL AND [补考成绩] IS NULL))
GO

ALTER TABLE [dbo].[成绩] CHECK CONSTRAINT [CK_成绩_t]
GO



USE [学籍管理系统]
GO

/****** Object:  Table [dbo].[课表]    Script Date: 12/09/2013 01:07:22 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[课表](
	[课程号] [nchar](9) NOT NULL,
	[班级号] [nchar](6) NOT NULL,
	[任课教师] [nchar](10) NOT NULL,
 CONSTRAINT [IX_课表] UNIQUE NONCLUSTERED 
(
	[任课教师] ASC,
	[班级号] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[课表]  WITH CHECK ADD  CONSTRAINT [FK_课表_班级] FOREIGN KEY([班级号])
REFERENCES [dbo].[班级] ([班号])
GO

ALTER TABLE [dbo].[课表] CHECK CONSTRAINT [FK_课表_班级]
GO

ALTER TABLE [dbo].[课表]  WITH CHECK ADD  CONSTRAINT [FK_课表_课程] FOREIGN KEY([课程号])
REFERENCES [dbo].[课程] ([课程号])
GO

ALTER TABLE [dbo].[课表] CHECK CONSTRAINT [FK_课表_课程]
GO

USE [学籍管理系统]
GO

USE [学籍管理系统]
GO

/****** Object:  Table [dbo].[课程]    Script Date: 12/15/2013 02:43:48 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[课程](
	[课程号] [nchar](9) NOT NULL,
	[课程名] [nchar](25) NOT NULL,
	[学分] [int] NOT NULL,
	[课程类型] [nchar](2) NOT NULL,
 CONSTRAINT [PK_课程] PRIMARY KEY CLUSTERED 
(
	[课程号] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[课程]  WITH CHECK ADD  CONSTRAINT [CK_课程] CHECK  (([课程类型]='任选' OR [课程类型]='限选' OR [课程类型]='必修'))
GO

ALTER TABLE [dbo].[课程] CHECK CONSTRAINT [CK_课程]
GO

ALTER TABLE [dbo].[课程] ADD  CONSTRAINT [DF_课程_课程类型]  DEFAULT (N'必修') FOR [课程类型]
GO




USE [学籍管理系统]
GO

/****** Object:  Table [dbo].[学生]    Script Date: 12/09/2013 01:07:38 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[学生](
	[班号] [nchar](6) NULL,
	[学号] [nchar](8) NOT NULL,
	[姓名] [nchar](10) NOT NULL,
	[性别] [nchar](1) NOT NULL,
	[出生日期] [smalldatetime] NULL,
 CONSTRAINT [PK_学生] PRIMARY KEY CLUSTERED 
(
	[学号] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[学生]  WITH CHECK ADD  CONSTRAINT [FK_学生_班级] FOREIGN KEY([班号])
REFERENCES [dbo].[班级] ([班号])
GO

ALTER TABLE [dbo].[学生] CHECK CONSTRAINT [FK_学生_班级]
GO

ALTER TABLE [dbo].[学生]  WITH CHECK ADD  CONSTRAINT [CK_学生] CHECK  (([性别]=N'女' OR [性别]=N'男'))
GO

ALTER TABLE [dbo].[学生] CHECK CONSTRAINT [CK_学生]
GO

ALTER TABLE [dbo].[学生] ADD  CONSTRAINT [DF_学生_性别]  DEFAULT (N'男') FOR [性别]
GO

USE [学籍管理系统]
GO

/****** Object:  Table [dbo].[专业]    Script Date: 12/09/2013 01:07:43 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[专业](
	[专业号] [int] NOT NULL,
	[专业名] [nchar](10) NOT NULL,
	[学院名] [nchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[专业号] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

USE [学籍管理系统]
GO

/****** Object:  Table [dbo].[教学计划]    Script Date: 12/15/2013 02:23:17 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[教学计划](
	[年级] [int] NOT NULL,
	[专业号] [int] NOT NULL,
	[课程号] [nchar](9) NOT NULL,
 CONSTRAINT [PK_教学计划] PRIMARY KEY CLUSTERED 
(
	[年级] ASC,
	[专业号] ASC,
	[课程号] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[教学计划]  WITH CHECK ADD  CONSTRAINT [FK_教学计划_课程] FOREIGN KEY([课程号])
REFERENCES [dbo].[课程] ([课程号])
GO

ALTER TABLE [dbo].[教学计划] CHECK CONSTRAINT [FK_教学计划_课程]
GO

ALTER TABLE [dbo].[教学计划]  WITH CHECK ADD  CONSTRAINT [FK_教学计划_专业] FOREIGN KEY([专业号])
REFERENCES [dbo].[专业] ([专业号])
GO

ALTER TABLE [dbo].[教学计划] CHECK CONSTRAINT [FK_教学计划_专业]
GO

ALTER TABLE [dbo].[教学计划]  WITH CHECK ADD  CONSTRAINT [CK_教学计划] CHECK  (([年级]>(0) AND [年级]<(5)))
GO

ALTER TABLE [dbo].[教学计划] CHECK CONSTRAINT [CK_教学计划]
GO


