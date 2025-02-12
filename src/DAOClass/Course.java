/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOClass;

/**
 *
 * @author Hyun
 */
public class Course {

    private String maKhoaHoc;
    private String tenKhoaHoc;
    private String moTa;
    private String hinhKhoaHoc;
    private Double hocPhi;
    private String trangThai;

    public Course(String maKhoaHoc, String tenKhoaHoc, String moTa, String hinhKhoaHoc, Double hocPhi, String trangThai) {
        this.maKhoaHoc = maKhoaHoc;
        this.tenKhoaHoc = tenKhoaHoc;
        this.moTa = moTa;
        this.hinhKhoaHoc = hinhKhoaHoc;
        this.hocPhi = hocPhi;
        this.trangThai = trangThai;
    }

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public String getHinhKhoaHoc() {
        return hinhKhoaHoc;
    }

    public Double getHocPhi() {
        return hocPhi;
    }

    public String getTrangThai() {
        return trangThai;
    }

}
