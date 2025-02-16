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
public class LopHoc {

    private UUID idLopHoc;
    private String tenLop;
    private Integer siSo;
    private Date thoiGianBatDau;
    private Date thoiGianKetThuc;
    private String caHoc;
    private UUID idGiangVien;

    public LopHoc(UUID idLopHoc, String tenLop, Integer siSo, Date thoiGianBatDau, Date thoiGianKetThuc, String caHoc, UUID idGiangVien) {
        this.idLopHoc = idLopHoc;
        this.tenLop = tenLop;
        this.siSo = siSo;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.caHoc = caHoc;
        this.idGiangVien = idGiangVien;
    }

    public UUID getIdLopHoc() {
        return idLopHoc;
    }

    public void setIdLopHoc(UUID idLopHoc) {
        this.idLopHoc = idLopHoc;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public Integer getSiSo() {
        return siSo;
    }

    public void setSiSo(Integer siSo) {
        this.siSo = siSo;
    }

    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getCaHoc() {
        return caHoc;
    }

    public void setCaHoc(String caHoc) {
        this.caHoc = caHoc;
    }

    public UUID getIdGiangVien() {
        return idGiangVien;
    }

    public void setIdGiangVien(UUID idGiangVien) {
        this.idGiangVien = idGiangVien;
    }

}
