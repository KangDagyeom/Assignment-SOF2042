/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.UUID;

/**
 *
 * @author Hyun
 */
public class KhoaHoc {

    private UUID idKhoaHoc;
    private String maKhoaHoc;
    private String tenKhoaHoc;
    private String moTa;
    private String hinhKhoaHoc;
    private double hocPhi;
    private String trangThai;

    public KhoaHoc(UUID idKhoaHoc, String maKhoaHoc, String tenKhoaHoc, String moTa, String hinhKhoaHoc, double hocPhi, String trangThai) {
        this.idKhoaHoc = idKhoaHoc;
        this.maKhoaHoc = maKhoaHoc;
        this.tenKhoaHoc = tenKhoaHoc;
        this.moTa = moTa;
        this.hinhKhoaHoc = hinhKhoaHoc;
        this.hocPhi = hocPhi;
        this.trangThai = trangThai;
    }

    public UUID getIdKhoaHoc() {
        return idKhoaHoc;
    }

    public void setIdKhoaHoc(UUID idKhoaHoc) {
        this.idKhoaHoc = idKhoaHoc;
    }

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhKhoaHoc() {
        return hinhKhoaHoc;
    }

    public void setHinhKhoaHoc(String hinhKhoaHoc) {
        this.hinhKhoaHoc = hinhKhoaHoc;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
