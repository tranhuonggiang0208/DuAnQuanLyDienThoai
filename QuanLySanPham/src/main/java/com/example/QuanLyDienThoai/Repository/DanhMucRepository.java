package com.example.QuanLyDienThoai.Repository;

import com.example.QuanLyDienThoai.Entity.DanhMuc;
import com.example.QuanLyDienThoai.Util.HibernateConfig;
import org.hibernate.Session;

import java.util.List;


public class DanhMucRepository {
    private Session session = null;

    public DanhMucRepository() {
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<DanhMuc> getAll() {
        return session.createQuery("select dm from DanhMuc dm", DanhMuc.class).list();
    }

    public DanhMuc getById(Integer id) {
        return session.find(DanhMuc.class, id);
    }
}
