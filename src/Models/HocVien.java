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
public class HocVien {

    private UUID idHocVien;
    private String maHocVien;
    private String hoTen;
    private Date ngaySinh;
    private String soDienThoai;
    private String diaChi;

    public HocVien(UUID idHocVien, String maHocVien, String hoTen, Date ngaySinh, String soDienThoai, String diaChi) {
        this.idHocVien = idHocVien;
        this.maHocVien = maHocVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public UUID getIdHocVien() {
        return idHocVien;
    }

    public void setIdHocVien(UUID idHocVien) {
        this.idHocVien = idHocVien;
    }

    public String getMaHocVien() {
        return maHocVien;
    }

    public void setMaHocVien(String maHocVien) {
        this.maHocVien = maHocVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

}
