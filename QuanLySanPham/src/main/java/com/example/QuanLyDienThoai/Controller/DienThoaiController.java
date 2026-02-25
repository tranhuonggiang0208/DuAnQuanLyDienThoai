package com.example.QuanLyDienThoai.Controller;

import com.example.QuanLyDienThoai.Entity.DanhMuc;
import com.example.QuanLyDienThoai.Repository.DanhMucRepository;
import com.example.QuanLyDienThoai.Repository.DienThoaiRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "san-pham", value = {
        "/san-pham/list",
        "/san-pham/detail",
        "/san-pham/view-add",
        "/san-pham/add",
        "/san-pham/view-update",
        "/san-pham/update",
        "/san-pham/delete",
        "/san-pham/sort",
        "/san-pham/find",
        "/san-pham/phan-trang",
        "/san-pham/timKiemTheoKhoangSoLuong"
})
public class DienThoaiController extends HttpServlet {
    DanhMucRepository dmRepo = new DanhMucRepository();
    DienThoaiRepository spRepo = new DienThoaiRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("list")) {
            list(req, resp);
        } else if (uri.contains("detail")) {
            detail(req, resp);
        } else if (uri.contains("view-add")) {
            viewAdd(req, resp);
        } else if (uri.contains("view-update")) {
            viewUpdate(req, resp);
        } else if (uri.contains("sort")) {
            sort(req, resp);
        } else if (uri.contains("find")) {
            find(req, resp);
        } else if (uri.contains("phan-trang")) {
            phanTrang(req, resp);
        } else if (uri.contains("timKiemTheoKhoangSoLuong")) {
            timKiemTheoKhoangSoLuong(req, resp);
        }else if (uri.contains("delete")) {
            delete(req, resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Integer id = Integer.valueOf(req.getParameter("id"));

        spRepo.xoaSanPham(id);

        req.getSession().setAttribute("message", "Xóa thành công");

        resp.sendRedirect(req.getContextPath() + "/san-pham/list");
    }

    private void timKiemTheoKhoangSoLuong(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sMin = Integer.valueOf(req.getParameter("sMin"));
        Integer sMax = Integer.valueOf(req.getParameter("sMax"));
        req.setAttribute("sanPhamList", spRepo.timKiemTheoKhoangSoLuongKho(sMin, sMax));
        req.setAttribute("danhMucList", dmRepo.getAll());
        req.getRequestDispatcher("/views/list.jsp").forward(req, resp);
    }

    private void phanTrang(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 0;
        int size = 3;
        if (req.getParameter("page") != null) {
            page = Integer.valueOf(req.getParameter("page"));
        }
        req.setAttribute("page", page);
        int totalSanPham = spRepo.getAll().size();
        double totalPages = Math.ceil((double) totalSanPham / size);
        req.setAttribute("totalPages", (int) totalPages);
        req.setAttribute("sanPhamList", spRepo.phanTrangSanPham(page, size));
        req.setAttribute("danhMucList", dmRepo.getAll());
        req.getRequestDispatcher("/views/list.jsp").forward(req, resp);
    }

    private void find(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer soLuongKho = Integer.valueOf(req.getParameter("soLuongKho"));
        req.setAttribute("sanPhamList", spRepo.timKiemTheoSoLuongKho(soLuongKho));
        req.setAttribute("danhMucList", dmRepo.getAll());
        req.getRequestDispatcher("/views/list.jsp").forward(req, resp);
    }

    private void sort(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sort = req.getParameter("sort");
        req.setAttribute("sanPhamList", spRepo.sapXepTheoSoLuongKho(sort));
        req.setAttribute("danhMucList", dmRepo.getAll());
        req.getRequestDispatcher("/views/list.jsp").forward(req, resp);
    }

    private void viewUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        req.setAttribute("sanPham", spRepo.getById(id));
        req.setAttribute("sanPhamList", spRepo.getAll());
        req.setAttribute("danhMucList", dmRepo.getAll());
        req.getRequestDispatcher("/views/update.jsp").forward(req, resp);
    }

    private void viewAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sanPhamList", spRepo.getAll());
        req.setAttribute("danhMucList", dmRepo.getAll());
        req.getRequestDispatcher("/views/add.jsp").forward(req, resp);
    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        req.setAttribute("sanPham", spRepo.getById(id));
        req.setAttribute("sanPhamList", spRepo.getAll());
        req.getRequestDispatcher("/views/list.jsp").forward(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sanPhamList", spRepo.getAll());
        req.getRequestDispatcher("/views/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("add")) {
            add(req, resp);
        } else if (uri.contains("update")) {
            update(req, resp);
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String idStr = req.getParameter("id");
        String tenSanPham = req.getParameter("tenSanPham");
        String giaBanStr = req.getParameter("giaBan");
        String soLuongKhoStr = req.getParameter("soLuongKho");
        String ngayNhapStr = req.getParameter("ngayNhap");
        String danhMucIdStr = req.getParameter("danhMucId");

        // ===== Validate rỗng =====
        if (tenSanPham == null || tenSanPham.trim().isEmpty() ||
                giaBanStr == null || giaBanStr.trim().isEmpty() ||
                soLuongKhoStr == null || soLuongKhoStr.trim().isEmpty() ||
                ngayNhapStr == null || ngayNhapStr.trim().isEmpty()) {

            req.setAttribute("error", "Không được để trống dữ liệu");
            req.setAttribute("sanPham", spRepo.getById(Integer.valueOf(idStr)));
            req.setAttribute("danhMucList", dmRepo.getAll());
            req.getRequestDispatcher("/views/update.jsp").forward(req, resp);
            return;
        }

        Integer id = Integer.valueOf(idStr);
        Double giaBan = Double.valueOf(giaBanStr);
        Integer soLuongKho = Integer.valueOf(soLuongKhoStr);

        if (soLuongKho <= 0) {
            req.setAttribute("error", "So luong kho khong duoc am");
            req.setAttribute("sanPham", spRepo.getById(id));
            req.setAttribute("danhMucList", dmRepo.getAll());
            req.getRequestDispatcher("/views/update.jsp").forward(req, resp);
            return;
        }

        java.sql.Date ngayNhap = java.sql.Date.valueOf(ngayNhapStr);
        Integer danhMucId = Integer.valueOf(danhMucIdStr);

        DanhMuc danhMuc = dmRepo.getById(danhMucId);
        com.example.QuanLyDienThoai.Entity.DienThoai sanPham = new com.example.QuanLyDienThoai.Entity.DienThoai(id, tenSanPham, giaBan, soLuongKho, ngayNhap, danhMuc);

        spRepo.suaSanPham(sanPham);

        req.getSession().setAttribute("message", "Sua thanh cong");
        resp.sendRedirect(req.getContextPath() + "/san-pham/list");
    }

    private void add(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String tenSanPham = req.getParameter("tenSanPham");
        String giaBanStr = req.getParameter("giaBan");
        String soLuongKhoStr = req.getParameter("soLuongKho");
        String ngayNhapStr = req.getParameter("ngayNhap");
        String danhMucIdStr = req.getParameter("danhMucId");

        // ===== Validate rỗng =====
        if (tenSanPham == null || tenSanPham.trim().isEmpty() ||
                giaBanStr == null || giaBanStr.trim().isEmpty() ||
                soLuongKhoStr == null || soLuongKhoStr.trim().isEmpty() ||
                ngayNhapStr == null || ngayNhapStr.trim().isEmpty()) {

            req.setAttribute("error", "Không được để trống dữ liệu");
            req.setAttribute("danhMucList", dmRepo.getAll());
            req.getRequestDispatcher("/views/add.jsp").forward(req, resp);
            return;
        }

        Double giaBan = Double.valueOf(giaBanStr);
        Integer soLuongKho = Integer.valueOf(soLuongKhoStr);

        if (soLuongKho <= 0) {
            req.setAttribute("error", "So luong kho khong duoc am");
            req.setAttribute("danhMucList", dmRepo.getAll());
            req.getRequestDispatcher("/views/add.jsp").forward(req, resp);
            return;
        }

        java.sql.Date ngayNhap = java.sql.Date.valueOf(ngayNhapStr);
        Integer danhMucId = Integer.valueOf(danhMucIdStr);

        DanhMuc danhMuc = dmRepo.getById(danhMucId);
        com.example.QuanLyDienThoai.Entity.DienThoai sanPham = new com.example.QuanLyDienThoai.Entity.DienThoai(null, tenSanPham, giaBan, soLuongKho, ngayNhap, danhMuc);

        spRepo.themSanPham(sanPham);

        req.getSession().setAttribute("message", "Them thanh cong");
        resp.sendRedirect(req.getContextPath() + "/san-pham/list");
    }
}
