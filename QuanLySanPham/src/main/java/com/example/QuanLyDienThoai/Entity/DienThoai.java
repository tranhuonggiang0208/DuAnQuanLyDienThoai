package com.example.QuanLyDienThoai.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "san_pham")
public class DienThoai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "gia_ban")
    private Double giaBan;

    @Column(name = "so_luong_kho")
    private Integer soLuongKho;

    @Column(name = "ngay_nhap")
    private Date ngayNhap;

    @ManyToOne
    @JoinColumn(name = "danh_muc_id", referencedColumnName = "id")
    private DanhMuc danhMuc;
}
