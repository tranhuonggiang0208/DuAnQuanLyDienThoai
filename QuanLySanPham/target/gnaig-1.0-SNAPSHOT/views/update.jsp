<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Sản Phẩm</title>

    <!-- Responsive -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }

        form {
            background: #ffffff;
            width: 450px;
            margin: 60px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.08);
        }

        input, select {
            width: 100%;
            padding: 8px;
            margin: 5px 0 15px 0;
            border-radius: 6px;
            border: 1px solid #ccc;
        }

        input:focus, select:focus {
            outline: none;
            border-color: #000;
        }

        button {
            background: #000;
            color: white;
            border: none;
            padding: 8px 20px;
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

<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>

<c:if test="${not empty sessionScope.message}">
    <p style="color: green">${sessionScope.message}</p>
    <c:remove var="message" scope="session"></c:remove>
</c:if>

<form method="post" action="/san-pham/update">
    Id: <input type="text" name="id" value="${sanPham.id}" readonly><br>
    Ten san pham: <input type="text" name="tenSanPham" value="${sanPham.tenSanPham}"><br>
    Gia ban: <input type="number" name="giaBan" value="${sanPham.giaBan}"><br>
    So luong kho: <input type="number" name="soLuongKho" value="${sanPham.soLuongKho}"><br>
    Ngay nhap: <input type="date" name="ngayNhap" value="${sanPham.ngayNhap}"><br>
    Ten danh muc:
    <select name="danhMucId">
        <c:forEach items="${danhMucList}" var="dm">
            <option value="${dm.id}"
                ${sanPham.danhMuc.id == dm.id ? "selected" : ""}>
                    ${dm.tenDanhMuc}
            </option>
        </c:forEach>
    </select>
    <button type="submit">Update</button>
</form>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>