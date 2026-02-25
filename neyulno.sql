IF DB_ID('neyulno') IS NOT NULL
    DROP DATABASE neyulno;
GO

CREATE DATABASE QuanLyDienThoai;
GO

USE QuanLyDienThoai;
GO

-- ==============================
-- 1. BẢNG DANH MỤC (HÃNG)
-- ==============================
CREATE TABLE danh_muc (
    id INT IDENTITY(1,1) PRIMARY KEY,
    ten_danh_muc NVARCHAR(100) NOT NULL,
    mo_ta NVARCHAR(255),
    trang_thai BIT DEFAULT 1
);
GO

-- Thêm hãng điện thoại
INSERT INTO danh_muc (ten_danh_muc, mo_ta, trang_thai)
VALUES 
    (N'Apple', N'Hãng điện thoại iPhone', 1),
    (N'Samsung', N'Hãng điện thoại Android cao cấp', 1),
    (N'Xiaomi', N'Hãng điện thoại cấu hình cao giá tốt', 1),
    (N'Oppo', N'Hãng điện thoại phổ thông', 1),
    (N'Nokia', N'Hãng điện thoại truyền thống', 1);
GO

-- ==============================
-- 2. BẢNG SẢN PHẨM (ĐIỆN THOẠI)
-- ==============================
CREATE TABLE san_pham (
    id INT IDENTITY(1,1) PRIMARY KEY,
    ten_san_pham NVARCHAR(150) NOT NULL,
    gia_ban DECIMAL(18,2) NOT NULL CHECK (gia_ban > 0),
    so_luong_kho INT DEFAULT 0 CHECK (so_luong_kho >= 0),
    ngay_nhap DATE,
    danh_muc_id INT NOT NULL,
    FOREIGN KEY (danh_muc_id) REFERENCES danh_muc(id)
);
GO

-- ==============================
-- 3. THÊM 15 ĐIỆN THOẠI
-- ==============================
INSERT INTO san_pham (ten_san_pham, gia_ban, so_luong_kho, ngay_nhap, danh_muc_id)
VALUES 
-- Apple
(N'iPhone 15 Pro Max 256GB', 29990000, 40, '2024-01-10', 1),
(N'iPhone 15 128GB', 21990000, 55, '2024-01-12', 1),
(N'iPhone 14 128GB', 18990000, 35, '2024-02-05', 1),

-- Samsung
(N'Samsung Galaxy S24 Ultra', 26990000, 25, '2024-02-15', 2),
(N'Samsung Galaxy S24', 20990000, 30, '2024-02-18', 2),
(N'Samsung Galaxy A55', 9990000, 60, '2024-03-01', 2),

-- Xiaomi
(N'Xiaomi 14 Pro', 17990000, 45, '2024-03-10', 3),
(N'Xiaomi Redmi Note 13', 6990000, 80, '2024-03-12', 3),
(N'Xiaomi 13T', 12990000, 50, '2024-03-15', 3),

-- Oppo
(N'Oppo Reno 11', 10990000, 50, '2024-03-18', 4),
(N'Oppo Find X5', 15990000, 20, '2024-03-20', 4),
(N'Oppo A78', 6490000, 70, '2024-03-22', 4),

-- Nokia
(N'Nokia G22', 4990000, 40, '2024-04-01', 5),
(N'Nokia C32', 3490000, 65, '2024-04-03', 5),
(N'Nokia X30', 8990000, 30, '2024-04-05', 5);
GO