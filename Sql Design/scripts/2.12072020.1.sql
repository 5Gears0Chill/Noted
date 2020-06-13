USE [master]
GO
/****** Object:  Database [Noted-DB]    Script Date: 2020/06/13 02:22:48 ******/
CREATE DATABASE [Noted-DB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Noted-DB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Noted-DB.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Noted-DB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\Noted-DB_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [Noted-DB] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Noted-DB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Noted-DB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Noted-DB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Noted-DB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Noted-DB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Noted-DB] SET ARITHABORT OFF 
GO
ALTER DATABASE [Noted-DB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Noted-DB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Noted-DB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Noted-DB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Noted-DB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Noted-DB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Noted-DB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Noted-DB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Noted-DB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Noted-DB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Noted-DB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Noted-DB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Noted-DB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Noted-DB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Noted-DB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Noted-DB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Noted-DB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Noted-DB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Noted-DB] SET  MULTI_USER 
GO
ALTER DATABASE [Noted-DB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Noted-DB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Noted-DB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Noted-DB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Noted-DB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Noted-DB] SET QUERY_STORE = OFF
GO
USE [Noted-DB]
GO
/****** Object:  Table [dbo].[Note]    Script Date: 2020/06/13 02:22:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Note](
	[NoteId] [int] NOT NULL,
	[NoteBookId] [int] NOT NULL,
	[Title] [varchar](50) NOT NULL,
	[Content] [varchar](max) NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Note] PRIMARY KEY CLUSTERED 
(
	[NoteId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NoteAttribute]    Script Date: 2020/06/13 02:22:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NoteAttribute](
	[NoteId] [int] NOT NULL,
	[CreatedOn] [datetime] NOT NULL,
	[UpdatedOn] [datetime] NOT NULL,
	[CategoryId] [int] NOT NULL,
	[FileSize] [decimal](18, 4) NOT NULL,
 CONSTRAINT [PK_NoteAttribute] PRIMARY KEY CLUSTERED 
(
	[NoteId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NoteBook]    Script Date: 2020/06/13 02:22:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NoteBook](
	[NoteBookId] [int] NOT NULL,
	[Title] [varchar](50) NOT NULL,
	[Description] [varchar](250) NULL,
 CONSTRAINT [PK_NoteBook] PRIMARY KEY CLUSTERED 
(
	[NoteBookId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NoteCategory]    Script Date: 2020/06/13 02:22:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NoteCategory](
	[CategoryId] [int] NOT NULL,
	[Description] [varchar](50) NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_NoteCategory] PRIMARY KEY CLUSTERED 
(
	[CategoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NoteTags]    Script Date: 2020/06/13 02:22:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NoteTags](
	[TagId] [int] NOT NULL,
	[NoteId] [int] NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_NoteTags] PRIMARY KEY CLUSTERED 
(
	[TagId] ASC,
	[NoteId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Resource]    Script Date: 2020/06/13 02:22:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Resource](
	[ResourceId] [int] NOT NULL,
	[NoteId] [int] NOT NULL,
	[Title] [varchar](max) NOT NULL,
	[Content] [varchar](max) NOT NULL,
	[TypeId] [int] NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Resource] PRIMARY KEY CLUSTERED 
(
	[ResourceId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ResourceType]    Script Date: 2020/06/13 02:22:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ResourceType](
	[TypeId] [int] NOT NULL,
	[Description] [varchar](50) NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_ResourceType] PRIMARY KEY CLUSTERED 
(
	[TypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tags]    Script Date: 2020/06/13 02:22:48 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tags](
	[TagId] [int] NOT NULL,
	[Description] [varchar](50) NOT NULL,
	[IsActive] [bit] NOT NULL,
 CONSTRAINT [PK_Tags] PRIMARY KEY CLUSTERED 
(
	[TagId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Note]  WITH CHECK ADD  CONSTRAINT [FK_Note_NoteBook] FOREIGN KEY([NoteBookId])
REFERENCES [dbo].[NoteBook] ([NoteBookId])
GO
ALTER TABLE [dbo].[Note] CHECK CONSTRAINT [FK_Note_NoteBook]
GO
ALTER TABLE [dbo].[NoteAttribute]  WITH CHECK ADD  CONSTRAINT [FK_NoteAttribute_Note] FOREIGN KEY([NoteId])
REFERENCES [dbo].[Note] ([NoteId])
GO
ALTER TABLE [dbo].[NoteAttribute] CHECK CONSTRAINT [FK_NoteAttribute_Note]
GO
ALTER TABLE [dbo].[NoteAttribute]  WITH CHECK ADD  CONSTRAINT [FK_NoteAttribute_NoteCategory] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[NoteCategory] ([CategoryId])
GO
ALTER TABLE [dbo].[NoteAttribute] CHECK CONSTRAINT [FK_NoteAttribute_NoteCategory]
GO
ALTER TABLE [dbo].[NoteTags]  WITH CHECK ADD  CONSTRAINT [FK_NoteTags_Note] FOREIGN KEY([NoteId])
REFERENCES [dbo].[Note] ([NoteId])
GO
ALTER TABLE [dbo].[NoteTags] CHECK CONSTRAINT [FK_NoteTags_Note]
GO
ALTER TABLE [dbo].[NoteTags]  WITH CHECK ADD  CONSTRAINT [FK_NoteTags_Tags] FOREIGN KEY([TagId])
REFERENCES [dbo].[Tags] ([TagId])
GO
ALTER TABLE [dbo].[NoteTags] CHECK CONSTRAINT [FK_NoteTags_Tags]
GO
ALTER TABLE [dbo].[Resource]  WITH CHECK ADD  CONSTRAINT [FK_Resource_Note] FOREIGN KEY([NoteId])
REFERENCES [dbo].[Note] ([NoteId])
GO
ALTER TABLE [dbo].[Resource] CHECK CONSTRAINT [FK_Resource_Note]
GO
ALTER TABLE [dbo].[Resource]  WITH CHECK ADD  CONSTRAINT [FK_Resource_ResourceType] FOREIGN KEY([TypeId])
REFERENCES [dbo].[ResourceType] ([TypeId])
GO
ALTER TABLE [dbo].[Resource] CHECK CONSTRAINT [FK_Resource_ResourceType]
GO
USE [master]
GO
ALTER DATABASE [Noted-DB] SET  READ_WRITE 
GO
