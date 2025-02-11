/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.UUID;

/**
 *
 * @author Administrator
 */
public class ChuyenDe {

    private UUID idChuyenDe;
    private String maChuyenDe;
    private String tenChuyenDe;
    private String hinhChuyenDe;
    private String moTa;
    private Integer hocPhi;

    public ChuyenDe(UUID idChuyenDe, String maChuyenDe, String tenChuyenDe, String hinhChuyenDe, String moTa, Integer hocPhi) {
        this.idChuyenDe = idChuyenDe;
        this.maChuyenDe = maChuyenDe;
        this.tenChuyenDe = tenChuyenDe;
        this.hinhChuyenDe = hinhChuyenDe;
        this.moTa = moTa;
        this.hocPhi = hocPhi;
    }

    public UUID getIdChuyenDe() {
        return idChuyenDe;
    }

    public void setIdChuyenDe(UUID idChuyenDe) {
        this.idChuyenDe = idChuyenDe;
    }

    public String getMaChuyenDe() {
        return maChuyenDe;
    }

    public void setMaChuyenDe(String maChuyenDe) {
        this.maChuyenDe = maChuyenDe;
    }

    public String getTenChuyenDe() {
        return tenChuyenDe;
    }

    public void setTenChuyenDe(String tenChuyenDe) {
        this.tenChuyenDe = tenChuyenDe;
    }

    public String getHinhChuyenDe() {
        return hinhChuyenDe;
    }

    public void setHinhChuyenDe(String hinhChuyenDe) {
        this.hinhChuyenDe = hinhChuyenDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(Integer hocPhi) {
        this.hocPhi = hocPhi;
    }

}
