<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
            padding: 30px;
        }

        form {
            background: #ffffff;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.05);
        }

        input, button {
            padding: 6px 10px;
            margin: 5px 5px 10px 0;
            border-radius: 6px;
            border: 1px solid #ccc;
        }

        button {
            background: #000;
            color: white;
            border: none;
        }

        button:hover {
            opacity: 0.85;
        }

        table {
            background: white;
        }

        th {
            background: #000;
            color: white;
            text-align: center;
        }

        td {
            vertical-align: middle;
        }

        a {
            text-decoration: none;
            font-weight: 500;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>QUẢN LÝ ĐIỆN THOẠI</h1>

<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>

<c:if test="${not empty sessionScope.message}">
    <p style="color: green">${sessionScope.message}</p>
    <c:remove var="message" scope="session"></c:remove>
</c:if>

<form method="get" action="/san-pham/find">
    So luong kho: <input type="number" name="soLuongKho">
    <button type="submit">Tim kiem</button>
</form>

<form method="get" action="/san-pham/timKiemTheoKhoangSoLuong">
    <label>So luong kho theo khoang</label>
    <input type="number" name="sMin">
    <input type="number" name="sMax">
    <button type="submit">Tim kiem</button>
</form>

<form method="get" action="/san-pham/sort">
    <button name="sort" value="asc">Sap xep tang dan so luong kho</button>
    <button name="sort" value="desc">Sap xep giam dan so luong kho</button>
</form>

Id: <input type="text" name="id" value="${sanPham.id}" readonly><br>
Ten san pham: <input type="text" name="tenSanPham" value="${sanPham.tenSanPham}"><br>
Gia ban: <input type="number" name="giaBan" value="${sanPham.giaBan}"><br>
So luong kho: <input type="number" name="soLuongKho" value="${sanPham.soLuongKho}"><br>
Ngay nhap: <input type="date" name="ngayNhap" value="${sanPham.ngayNhap}"><br>
Ten danh muc: <input type="text" name="tenDanhMuc" value="${sanPham.danhMuc.tenDanhMuc}"><br>
Mo ta: <input type="text" name="mota" value="${sanPham.danhMuc.moTa}"><br>

Trang thai:
Da thanh toan:
<input type="radio" name="trangThai" value="true"
${sanPham.danhMuc.trangThai == true ? "checked" : ""}>
Chua thanh toan:
<input type="radio" name="trangThai" value="false"
${sanPham.danhMuc.trangThai == false ? "checked" : ""}>

<br><br>

<table class="table table-bordered table-hover">
    <tr>
        <th>Id</th>
        <th>Ten san pham</th>
        <th>Gia ban</th>
        <th>So luong kho</th>
        <th>Ngay nhap</th>
        <th>Ten danh muc</th>
        <th>Mo ta</th>
        <th>Trang thai</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${sanPhamList}" var="sp">
        <tr>
            <td>${sp.id}</td>
            <td>${sp.tenSanPham}</td>
            <td>${sp.giaBan}</td>
            <td>${sp.soLuongKho}</td>
            <td>${sp.ngayNhap}</td>
            <td>${sp.danhMuc.tenDanhMuc}</td>
            <td>${sp.danhMuc.moTa}</td>
            <td>${sp.danhMuc.trangThai == true ? "Da thanh toan" : "Chua thanh toan"}</td>
            <td>
                <a href="${pageContext.request.contextPath}/san-pham/detail?id=${sp.id}">
                    Detail
                </a> |

                <a href="${pageContext.request.contextPath}/san-pham/view-update?id=${sp.id}">
                    Update
                </a> |

                <a href="${pageContext.request.contextPath}/san-pham/delete?id=${sp.id}"
                   onclick="return confirm('Bạn có chắc muốn xoá không?')">
                    Delete
                </a>

            </td>
        </tr>
    </c:forEach>
</table>

<a href=${pageContext.request.contextPath}/san-pham/view-add>➕ Add</a>
<br><br>

<a href="${pageContext.request.contextPath}/san-pham/phan-trang?page=0">First</a> |

<a href="${pageContext.request.contextPath}/san-pham/phan-trang?page=${page == 0 ? 0 : page - 1}">
    Prev
</a> |

<a href="${pageContext.request.contextPath}/san-pham/phan-trang?page=${page == totalPages - 1 ? page : page + 1}">
    Next
</a> |

<a href="${pageContext.request.contextPath}/san-pham/phan-trang?page=${totalPages - 1}">
    Last
</a>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>