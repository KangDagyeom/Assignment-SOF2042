/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.UUID;

/**
 *
 * @author Hyun
 */
public class NhanVien {

    private UUID idNhanVien;
    private String maNhanVien;
    private String hoTen;
    private String gioiTinh;
    private String soDienThoai;
    private String diaChi;
    private String vaiTro;
    private String matKhau;

    public NhanVien(UUID idNhanVien, String maNhanVien, String hoTen, String gioiTinh, String soDienThoai, String diaChi, String vaiTro, String matKhau) {
        this.idNhanVien = idNhanVien;
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.vaiTro = vaiTro;
        this.matKhau = matKhau;
    }

    public UUID getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(UUID idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
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

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "NhanVien{"
                + "idNhanVien=" + idNhanVien
                + ", maNhanVien='" + maNhanVien + '\''
                + ", hoTen='" + hoTen + '\''
                + ", gioiTinh='" + gioiTinh + '\''
                + ", soDienThoai='" + soDienThoai + '\''
                + ", diaChi='" + diaChi + '\''
                + ", vaiTro='" + vaiTro + '\''
                + ", matKhau='" + matKhau + '\''
                + '}';
    }

}
