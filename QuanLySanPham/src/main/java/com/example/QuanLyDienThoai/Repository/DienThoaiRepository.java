package com.example.QuanLyDienThoai.Repository;

import com.example.QuanLyDienThoai.Entity.DienThoai;
import com.example.QuanLyDienThoai.Util.HibernateConfig;
import jakarta.persistence.Query;
import org.hibernate.Session;

import java.util.List;

public class DienThoaiRepository {
    private Session session = null;

    public DienThoaiRepository() {
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<DienThoai> getAll() {
        return session.createQuery("select sp from DienThoai sp", DienThoai.class).list();
    }

    public DienThoai getById(Integer id) {
        return session.find(DienThoai.class, id);
    }

    public void themSanPham(DienThoai sanPham) {
        try {
            session.getTransaction().begin();
            session.save(sanPham);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void suaSanPham(DienThoai sanPham) {
        try {
            session.getTransaction().begin();
            session.merge(sanPham);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void xoaSanPham(Integer id) {
        try {
            session.getTransaction().begin();
            session.delete(this.getById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<DienThoai> phanTrangSanPham(int page, int size) {
        Query query = session.createQuery("select sp from DienThoai sp");// nho dung query co javax.presistence
        query.setFirstResult(page * size); // ket qua dau tien se hien thi khi ap dung phan trang
        query.setMaxResults(size); //tra ve so luong trang toi da
        return query.getResultList(); //tra ve mot mang phan tu duoc phan trang khi ap dung 2 cai tren
    }

    public List<DienThoai> sapXepTheoSoLuongKho(String sort) {
        String sql = "select * from san_pham order by so_luong_kho " + sort; // day la cau lenh sql, ten table hoac column phai viet giong sql, nho phai co dau cach doan cuoi
        Query query = session.createNativeQuery(sql, DienThoai.class); //hql dung createQuery, sql thi phai co them Native (createNativeQuery)
        return query.getResultList();
    }

    public List<DienThoai> timKiemTheoSoLuongKho(Integer soLuongKho) {
        Query query = session.createQuery("SELECT sp FROM DienThoai sp WHERE sp.soLuongKho = :soLuongKho"); //Integer, Double se dung = thay vi LIKE
        query.setParameter("soLuongKho", soLuongKho);
        return query.getResultList();
    }

    public List<DienThoai> timKiemTheoKhoangSoLuongKho(Integer sMin, Integer sMax) {
        Query query = session.createQuery("select sp from DienThoai sp where sp.soLuongKho between :sMin and :sMax");
        query.setParameter("sMin", sMin);
        query.setParameter("sMax", sMax);
        return query.getResultList();
    }
}
