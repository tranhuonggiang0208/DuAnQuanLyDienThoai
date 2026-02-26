---
Phần Mềm Quản Lý Điện Thoại
## Giới thiệu
Đây là dự án xây dựng hệ thống quản lý điện thoại theo mô hình MVC. Ứng dụng cho phép quản lý danh mục và thông tin điện thoại, 
sử dụng Spring Boot và Hibernate để kết nối cơ sở dữ liệu.
## Công nghệ sử dụng
* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* SQL Server
* Maven
## Chức năng chính
* Quản lý điện thoại (thêm, sửa, xóa, hiển thị)
* Quản lý danh mục
* Tìm kiếm điện thoại theo tên
* Phân loại điện thoại theo danh mục
* Kết nối cơ sở dữ liệu bằng Hibernate
## Cấu trúc chính
* Controller: Xử lý request
* Entity: Ánh xạ bảng dữ liệu (DienThoai, DanhMuc)
* Repository: Thao tác với database
* Config: Cấu hình Hibernate
## Hướng dẫn chạy dự án
1. Import project vào IDE (IntelliJ / NetBeans)
2. Tạo database trong SQL Server
3. Cấu hình thông tin kết nối trong `application.properties` hoặc `HibernateConfig`
4. Chạy project bằng Spring Boot
## Tác giả
Trần Hương Giang
Trường: Cao đẳng FPT Polytechnic
GitHub: https://github.com/tranhuonggiang0208
Email: giangthth005801@gmail.com
---
