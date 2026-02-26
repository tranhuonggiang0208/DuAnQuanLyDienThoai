<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
        }

        form {
            background: white;
            padding: 30px;
            width: 450px;
            margin: 60px auto;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.08);
        }

        input, select {
            width: 100%;
            padding: 8px;
            margin: 5px 0 15px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        input:focus, select:focus {
            outline: none;
            border-color: black;
        }

        button {
            background: black;
            color: white;
            padding: 8px 20px;
            border: none;
            border-radius: 6px;
        }

        button:hover {
            opacity: 0.85;
        }

        p {
            text-align: center;
            font-weight: 500;
        }
    </style>
</head>

<body>
<form method="post" action="/san-pham/add">
    Id: <input type="text" name="id" value="${sanPham.id}" readonly><br>
    Ten san pham: <input type="text" name="tenSanPham" value="${sanPham.tenSanPham}"><br>
    Gia ban: <input type="number" name="giaBan" value="${sanPham.giaBan}"><br>
    So luong kho: <input type="number" name="soLuongKho" value="${sanPham.soLuongKho}"><br>
    Ngay nhap: <input type="date" name="ngayNhap" value="${sanPham.ngayNhap}"><br>
    Ten danh muc:
    <select name="danhMucId">
        <c:forEach items="${danhMucList}" var="dm">
            <option value="${dm.id}" label="${dm.tenDanhMuc}" ${sanPham.danhMuc.id == dm.id ? "selected" : ""}></option>
        </c:forEach>
    </select>
    <button type="submit">Add</button>
</form>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>