/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Hyun
 */
public class DangKyKhoaHoc {

    private UUID idHocVien;
    private UUID idKhoaHoc;
    private UUID idLopHoc;
    private Date ngayDangKy;
    private Double hocPhi;
    private String trangThai;
    private Double diem;

    public DangKyKhoaHoc(UUID idHocVien, UUID idKhoaHoc, UUID idLopHoc, Date ngayDangKy, Double hocPhi, String trangThai, Double diem) {
        this.idHocVien = idHocVien;
        this.idKhoaHoc = idKhoaHoc;
        this.idLopHoc = idLopHoc;
        this.ngayDangKy = ngayDangKy;
        this.hocPhi = hocPhi;
        this.trangThai = trangThai;
        this.diem = diem;
    }

    public UUID getIdHocVien() {
        return idHocVien;
    }

    public void setIdHocVien(UUID idHocVien) {
        this.idHocVien = idHocVien;
    }

    public UUID getIdKhoaHoc() {
        return idKhoaHoc;
    }

    public void setIdKhoaHoc(UUID idKhoaHoc) {
        this.idKhoaHoc = idKhoaHoc;
    }

    public UUID getIdLopHoc() {
        return idLopHoc;
    }

    public void setIdLopHoc(UUID idLopHoc) {
        this.idLopHoc = idLopHoc;
    }

    public Date getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public Double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(Double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Double getDiem() {
        return diem;
    }

    public void setDiem(Double diem) {
        this.diem = diem;
    }

}
