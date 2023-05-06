-- Create a new database called 'QLKS'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (
    SELECT [name]
        FROM sys.databases
        WHERE [name] = N'QLKS'
)
CREATE DATABASE QLKS
GO

use QLKS
go

-- KhachHang
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKH] [nvarchar](10) NOT NULL,
	[hoTenKH] [nvarchar](30) NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[SDT] [nvarchar](10) NOT NULL,
	[CCCD] [nvarchar](12) NOT NULL,
	[tongTien] [money] NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[KhachHang] ADD PRIMARY KEY CLUSTERED 
(
	[maKH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
CREATE UNIQUE NONCLUSTERED INDEX [Index_KhachHang_1] ON [dbo].[KhachHang]
(
	[CCCD] DESC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO

-- LoaiPhong
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiPhong](
	[maLoai] [nvarchar](10) NOT NULL,
	[tenLoai] [nvarchar](20) NOT NULL,
	[chatLuong] [nvarchar](20) NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[LoaiPhong] ADD PRIMARY KEY CLUSTERED 
(
	[maLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO

-- Phong
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[maPhong] [nvarchar](10) NOT NULL,
	[maLoai] [nvarchar](10) NOT NULL,
	[giaPhong] [money] NOT NULL,
	[ghiChu] [nvarchar](30) NULL,
	[soLanDatPhong] [int] NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[Phong] ADD PRIMARY KEY CLUSTERED 
(
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD FOREIGN KEY([maLoai])
REFERENCES [dbo].[LoaiPhong] ([maLoai])
ON DELETE CASCADE
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE trigger [dbo].[TR_Phong_AddNewPhong]
on [dbo].[Phong]
for insert
as
begin
	declare @maPhong nvarchar(10)
	if exists(select * from inserted)
		begin
			set @maPhong = (select maPhong from inserted)
			
			insert into TinhTrang values (@maPhong, 'KH000', null, null)
		end
end
GO
ALTER TABLE [dbo].[Phong] ENABLE TRIGGER [TR_Phong_AddNewPhong]
GO

-- TinhTrang
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TinhTrang](
	[maPhong] [nvarchar](10) NOT NULL,
	[maKH] [nvarchar](10) NULL,
	[ngayDat] [date] NULL,
	[ngayTra] [date] NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[TinhTrang] ADD  CONSTRAINT [PK_TinhTrang] PRIMARY KEY CLUSTERED 
(
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[TinhTrang]  WITH CHECK ADD  CONSTRAINT [FK_MaKH] FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKH])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TinhTrang] CHECK CONSTRAINT [FK_MaKH]
GO
ALTER TABLE [dbo].[TinhTrang]  WITH CHECK ADD  CONSTRAINT [FK_TinhTrang] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TinhTrang] CHECK CONSTRAINT [FK_TinhTrang]
GO

-- NhanVien
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNV] [nvarchar](10) NOT NULL,
	[matKhau] [nvarchar](20) NOT NULL,
	[hoTenNV] [nvarchar](30) NOT NULL,
	[ngaySinh] [date] NOT NULL,
	[SDT] [nvarchar](10) NOT NULL,
	[CCCD] [nvarchar](12) NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[NhanVien] ADD PRIMARY KEY CLUSTERED 
(
	[maNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO

-- HoaDon
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHD] [nvarchar](10) NOT NULL,
	[maPhong] [nvarchar](10) NOT NULL,
	[maNV] [nvarchar](10) NOT NULL,
	[maKH] [nvarchar](10) NOT NULL,
	[tongTien] [money] NOT NULL,
	[ngayTaoHD] [date] NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
ALTER TABLE [dbo].[HoaDon] ADD PRIMARY KEY CLUSTERED 
(
	[maHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([maKH])
REFERENCES [dbo].[KhachHang] ([maKH])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([maNV])
REFERENCES [dbo].[NhanVien] ([maNV])
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE trigger [dbo].[TR_HoaDon_AddNewHoaDon]
on [dbo].[HoaDon]
for insert
as
begin
	declare @tongTien money, @maKH nvarchar(10), @maPhong nvarchar(10);
	set @tongTien = (select tongTien from inserted);
	set @maKH = (select maKH from inserted);
	set @maPhong = (select maPhong from inserted)

	update KhachHang 
	set tongTien = tongTien + @tongTien
	where maKH = @maKH;

	update Phong 
	set soLanDatPhong = soLanDatPhong + 1
	where maPhong = @maPhong
end
GO
ALTER TABLE [dbo].[HoaDon] ENABLE TRIGGER [TR_HoaDon_AddNewHoaDon]
GO