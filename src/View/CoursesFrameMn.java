/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAOClass.ChuyenDeDAO;
import DAOClass.Course;
import DAOClass.KhoaHocDAO;
import DAOClass.UserSession;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author Hyun
 */
public class CoursesFrameMn extends javax.swing.JFrame {

    /**
     * Creates new form CoursesFrame
     */
    private Map<String, Font> fontCache = new HashMap<>();
    private KhoaHocDAO khoaHocDAO = new KhoaHocDAO();
    private ArrayList<Course> courses = khoaHocDAO.loadCoursesFromDatabase();
    private ArrayList<Course> displayedCourses = new ArrayList<>();
    private String imagePath = "";
    private int currentPage = 0;
    private final int ITEMS_PER_PAGE = 3;
    
    public CoursesFrameMn() {
        initComponents();
        this.setLocationRelativeTo(null);
        btndel1.setName("del1");
        btndel2.setName("del2");
        btndel3.setName("del3");
        
        displayedCourses = courses;
        updateUI(displayedCourses);
        
        loadFont("Poppins-SemiBold", "/fonts/FZ Poppins-SemiBold.ttf");
        loadFont("Poppins-Regular", "/fonts/FZ Poppins-Regular.ttf");
        txtusername.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 16));
        txtrole.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 14));
        loadUserData();
        btnUpdate1.addActionListener(e -> handleUpdateAction(btnUpdate1));
        btnUpdate2.addActionListener(e -> handleUpdateAction(btnUpdate2));
        btnUpdate3.addActionListener(e -> handleUpdateAction(btnUpdate3));
        btndel1.addActionListener(e -> handleDeleteCourse(btndel1));
        btndel2.addActionListener(e -> handleDeleteCourse(btndel2));
        btndel3.addActionListener(e -> handleDeleteCourse(btndel3));
        btndetails1.addActionListener(e -> handleDetails(btndetails1));
        btndetails2.addActionListener(e -> handleDetails(btndetails2));
        btndetails3.addActionListener(e -> handleDetails(btndetails3));
    }
    
    private void loadFont(String fontName, String path) {
        try (InputStream fontStream = getClass().getResourceAsStream(path)) {
            if (fontStream == null) {
                System.err.println("Font file not found: " + path);
                return;
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
            fontCache.put(fontName, font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
    
    private Font getCustomFont(String fontName, int style, float size) {
        Font baseFont = fontCache.get(fontName);
        return (baseFont != null) ? baseFont.deriveFont(style, size) : new Font("SansSerif", style, (int) size);
    }
    
    private void loadUserData() {
        String username = UserSession.getUsername();
        String role = UserSession.getRole();
        String gioiTinh = UserSession.getGioiTinh();
        
        txtusername.setText(username);
        txtrole.setText(role);
        setAvatar(gioiTinh);
        if (username == null || role == null || gioiTinh == null) {
            JOptionPane.showMessageDialog(this, "Lỗi: Chưa có thông tin đăng nhập!");
            return;
        }
    }
    
    private void setAvatar(String gioiTinh) {
        String imagePath = "";
        if (gioiTinh.equals("Nam")) {
            imagePath = "C:\\Users\\Hyun\\Desktop\\Assignment-SOF2042\\src\\Resources\\Male-user-img.png"; // Thay đường dẫn file thật
        } else if (gioiTinh.equals("Nu")) {
            imagePath = "C:\\Users\\Hyun\\Desktop\\Assignment-SOF2042\\src\\Resources\\Female-user-img.png"; // Thay đường dẫn file thật
        } else {
            imagePath = "C:\\Users\\Hyun\\Desktop\\Assignment-SOF2042\\src\\Resources\\Unknow-user-img.png"; // Avatar mặc định
        }

        // Kiểm tra file tồn tại trước khi đặt icon
        File file = new File(imagePath);
        if (file.exists()) {
            lbavatar.setIcon(new ImageIcon(imagePath));
        } else {
            System.out.println("⚠ Lỗi: Không tìm thấy hình ảnh tại " + imagePath);
        }
    }
    
    private void updateUI(ArrayList<Course> allCourses) {
        int start = currentPage * ITEMS_PER_PAGE;
        JTextField[] code = {txtcode1, txtcode2, txtcode3};
        JTextField[] name = {txtname1, txtname2, txtname3};
        JTextField[] price = {txtprice1, txtprice2, txtprice3};
        JTextArea[] des = {txtdes1, txtdes2, txtdes3};
        JTextField[] status = {txtstatus1, txtstatus2, txtstatus3};
        JLabel[] iconLabels = {lbpr1, lbpr2, lbpr3};
        
        for (int i = 0; i < ITEMS_PER_PAGE; i++) {
            int index = start + i;
            if (index < allCourses.size()) {
                Course course = allCourses.get(index);
                code[i].setText(course.getMaKhoaHoc());
                name[i].setText(course.getTenKhoaHoc());
                des[i].setText(course.getMoTa());
                price[i].setText(course.getHocPhi().toString());
                status[i].setText(course.getTrangThai());
                ImageIcon icon = new ImageIcon(course.getHinhKhoaHoc());
                iconLabels[i].setIcon(icon);
                
                code[i].setVisible(true);
                name[i].setVisible(true);
                des[i].setVisible(true);
                price[i].setVisible(true);
                status[i].setVisible(true);
                iconLabels[i].setVisible(true);
            } else {
                code[i].setEditable(false);
                name[i].setEditable(false);
                des[i].setEditable(false);
                price[i].setEditable(false);
                status[i].setEditable(false);
                code[i].setText(null);
                name[i].setText(null);
                des[i].setText(null);
                price[i].setText(null);
                status[i].setText(null);
                ImageIcon icon = new ImageIcon("C:\\Users\\Hyun\\Desktop\\Assignment-SOF2042\\src\\Resources\\Unknow-icon.png");
                iconLabels[i].setIcon(icon);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btndetails3 = new javax.swing.JButton();
        lbpr3 = new javax.swing.JLabel();
        lbpr2 = new javax.swing.JLabel();
        lbpr1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbavatar = new javax.swing.JLabel();
        txtrole = new javax.swing.JLabel();
        txtusername = new javax.swing.JLabel();
        lbcourse1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtname1 = new javax.swing.JTextField();
        btnUpdate1 = new javax.swing.JButton();
        txtstatus1 = new javax.swing.JTextField();
        txtcode1 = new javax.swing.JTextField();
        txtprice1 = new javax.swing.JTextField();
        btndetails2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdes1 = new javax.swing.JTextArea();
        lbcourse3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtcode3 = new javax.swing.JTextField();
        txtname3 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtdes3 = new javax.swing.JTextArea();
        txtprice3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtstatus3 = new javax.swing.JTextField();
        btnUpdate3 = new javax.swing.JButton();
        btndel3 = new javax.swing.JButton();
        btndel1 = new javax.swing.JButton();
        btndetails1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lbpr3container = new javax.swing.JLabel();
        lbpr1container = new javax.swing.JLabel();
        lbcourse2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtcode2 = new javax.swing.JTextField();
        txtname2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtdes2 = new javax.swing.JTextArea();
        txtprice2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtstatus2 = new javax.swing.JTextField();
        btnUpdate2 = new javax.swing.JButton();
        btndel2 = new javax.swing.JButton();
        lbpr2container = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtsearchbar = new javax.swing.JTextField();
        btnsearch = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Coursera");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btndetails3.setBackground(new java.awt.Color(255, 255, 255));
        btndetails3.setForeground(new java.awt.Color(0, 0, 0));
        btndetails3.setText("Details");
        jPanel1.add(btndetails3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 510, -1, -1));

        lbpr3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbpr3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbpr3MouseClicked(evt);
            }
        });
        jPanel1.add(lbpr3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, -1, -1));

        lbpr2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbpr2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbpr2MouseClicked(evt);
            }
        });
        jPanel1.add(lbpr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, -1));

        lbpr1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(lbpr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/App-logo-homeview.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 28, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Stu-unclick-btn.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 130, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Performance-unclick-btn.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Logout-unclick-btn.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Thanks-banner-img.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, -1, -1));

        lbavatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Male-user-img.png"))); // NOI18N
        jPanel1.add(lbavatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, -1, -1));

        txtrole.setForeground(new java.awt.Color(153, 204, 255));
        txtrole.setText("Role");
        jPanel1.add(txtrole, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 40, -1, -1));

        txtusername.setForeground(new java.awt.Color(0, 0, 0));
        txtusername.setText("Username");
        jPanel1.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 20, -1, -1));

        lbcourse1.setForeground(new java.awt.Color(255, 255, 255));
        lbcourse1.setText("Course code:");
        jPanel1.add(lbcourse1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Course name:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, -1, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Description:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, -1, -1));

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Course price:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Status:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, -1, -1));

        txtname1.setBackground(new java.awt.Color(255, 255, 255));
        txtname1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 120, -1));

        btnUpdate1.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate1.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdate1.setText("Update");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, -1, -1));

        txtstatus1.setBackground(new java.awt.Color(255, 255, 255));
        txtstatus1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtstatus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, 120, -1));

        txtcode1.setBackground(new java.awt.Color(255, 255, 255));
        txtcode1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtcode1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 120, -1));

        txtprice1.setBackground(new java.awt.Color(255, 255, 255));
        txtprice1.setForeground(new java.awt.Color(0, 0, 0));
        txtprice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprice1ActionPerformed(evt);
            }
        });
        jPanel1.add(txtprice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 110, 120, -1));

        btndetails2.setBackground(new java.awt.Color(255, 255, 255));
        btndetails2.setForeground(new java.awt.Color(0, 0, 0));
        btndetails2.setText("Details");
        jPanel1.add(btndetails2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 340, -1, -1));

        txtdes1.setBackground(new java.awt.Color(255, 255, 255));
        txtdes1.setColumns(20);
        txtdes1.setForeground(new java.awt.Color(0, 0, 0));
        txtdes1.setLineWrap(true);
        txtdes1.setRows(5);
        txtdes1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtdes1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 290, 40));

        lbcourse3.setForeground(new java.awt.Color(255, 255, 255));
        lbcourse3.setText("Course code:");
        jPanel1.add(lbcourse3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 450, -1, -1));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Course name:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 480, -1, -1));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Description:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 510, -1, -1));

        txtcode3.setBackground(new java.awt.Color(255, 255, 255));
        txtcode3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtcode3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 450, 120, -1));

        txtname3.setBackground(new java.awt.Color(255, 255, 255));
        txtname3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtname3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 480, 120, -1));

        txtdes3.setBackground(new java.awt.Color(255, 255, 255));
        txtdes3.setColumns(20);
        txtdes3.setForeground(new java.awt.Color(0, 0, 0));
        txtdes3.setLineWrap(true);
        txtdes3.setRows(5);
        txtdes3.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtdes3);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 510, 290, 40));

        txtprice3.setBackground(new java.awt.Color(255, 255, 255));
        txtprice3.setForeground(new java.awt.Color(0, 0, 0));
        txtprice3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprice3ActionPerformed(evt);
            }
        });
        jPanel1.add(txtprice3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 450, 120, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Courses-clicked-btn.png"))); // NOI18N
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Course price:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 450, -1, -1));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Status:");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, -1, -1));

        txtstatus3.setBackground(new java.awt.Color(255, 255, 255));
        txtstatus3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtstatus3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 480, 120, -1));

        btnUpdate3.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate3.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdate3.setText("Update");
        btnUpdate3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate3ActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 450, -1, -1));

        btndel3.setBackground(new java.awt.Color(255, 255, 255));
        btndel3.setForeground(new java.awt.Color(0, 0, 0));
        btndel3.setText("Remove");
        jPanel1.add(btndel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 480, -1, -1));

        btndel1.setBackground(new java.awt.Color(255, 255, 255));
        btndel1.setForeground(new java.awt.Color(0, 0, 0));
        btndel1.setText("Remove");
        jPanel1.add(btndel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 140, -1, -1));

        btndetails1.setBackground(new java.awt.Color(255, 255, 255));
        btndetails1.setForeground(new java.awt.Color(0, 0, 0));
        btndetails1.setText("Details");
        jPanel1.add(btndetails1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Clerks-unclick-btn.png"))); // NOI18N
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 90, -1));

        lbpr3container.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Pr-Course3-container.png"))); // NOI18N
        jPanel1.add(lbpr3container, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 430, -1, -1));

        lbpr1container.setForeground(new java.awt.Color(0, 0, 0));
        lbpr1container.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Pr-Course1-container.png"))); // NOI18N
        jPanel1.add(lbpr1container, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        lbcourse2.setForeground(new java.awt.Color(255, 255, 255));
        lbcourse2.setText("Course code:");
        jPanel1.add(lbcourse2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, -1));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Course name:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 310, -1, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Description:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, -1, -1));

        txtcode2.setBackground(new java.awt.Color(255, 255, 255));
        txtcode2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtcode2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 280, 120, -1));

        txtname2.setBackground(new java.awt.Color(255, 255, 255));
        txtname2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, 120, -1));

        txtdes2.setBackground(new java.awt.Color(255, 255, 255));
        txtdes2.setColumns(20);
        txtdes2.setForeground(new java.awt.Color(0, 0, 0));
        txtdes2.setLineWrap(true);
        txtdes2.setRows(5);
        txtdes2.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtdes2);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 340, 290, 40));

        txtprice2.setBackground(new java.awt.Color(255, 255, 255));
        txtprice2.setForeground(new java.awt.Color(0, 0, 0));
        txtprice2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprice2ActionPerformed(evt);
            }
        });
        jPanel1.add(txtprice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 280, 120, -1));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Course price:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, -1, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Status:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 310, -1, -1));

        txtstatus2.setBackground(new java.awt.Color(255, 255, 255));
        txtstatus2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtstatus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 310, 120, -1));

        btnUpdate2.setBackground(new java.awt.Color(255, 255, 255));
        btnUpdate2.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdate2.setText("Update");
        btnUpdate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdate2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 280, -1, -1));

        btndel2.setBackground(new java.awt.Color(255, 255, 255));
        btndel2.setForeground(new java.awt.Color(0, 0, 0));
        btndel2.setText("Remove");
        jPanel1.add(btndel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 310, -1, -1));

        lbpr2container.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Pr-Course2-container.png"))); // NOI18N
        jPanel1.add(lbpr2container, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 260, -1, -1));

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel1.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 60, -1));

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, 60, -1));

        jButton3.setText("Inert course, topic");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        txtsearchbar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsearchbarMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtsearchbarMouseExited(evt);
            }
        });
        jPanel1.add(txtsearchbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 200, -1));

        btnsearch.setText("Search");
        btnsearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsearchMouseClicked(evt);
            }
        });
        jPanel1.add(btnsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 50, -1, -1));

        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("*Empty value will display default");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Topic-unclick-btn.png"))); // NOI18N
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 664, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        HomeFrame.getInstance().setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel3MouseClicked

    private void txtprice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprice1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprice1ActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void txtprice2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprice2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprice2ActionPerformed

    private void btnUpdate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdate2ActionPerformed

    private void txtprice3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprice3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprice3ActionPerformed

    private void btnUpdate3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdate3ActionPerformed

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kButton1ActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if ((currentPage + 1) * ITEMS_PER_PAGE < displayedCourses.size()) {
            currentPage++;
            updateUI(displayedCourses);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        if (currentPage > 0) {
            currentPage--;
            updateUI(displayedCourses);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new GoodbyeFrame().setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btnsearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsearchMouseClicked
        
        String searchContent = txtsearchbar.getText();
        ArrayList<Course> getCourse = khoaHocDAO.loadCourseFromDatabase(searchContent);
        
        if (getCourse.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả!");
        } else {
            JOptionPane.showMessageDialog(this, "Đã tìm thấy!");
            displayedCourses = getCourse;
            currentPage = 0;
            updateUI(displayedCourses);
        }
        

    }//GEN-LAST:event_btnsearchMouseClicked
    private void handleUpdateAction(JButton button) {
        String role = UserSession.getRole();
        if (role.equals("Manager")) {
            int start = currentPage * ITEMS_PER_PAGE;
            JTextField[] code = {txtcode1, txtcode2, txtcode3};
            JTextField[] name = {txtname1, txtname2, txtname3};
            JTextField[] price = {txtprice1, txtprice2, txtprice3};
            JTextArea[] des = {txtdes1, txtdes2, txtdes3};
            JTextField[] status = {txtstatus1, txtstatus2, txtstatus3};
            
            for (int i = 0; i < ITEMS_PER_PAGE; i++) {
                int index = start + i;
                if (index >= courses.size()) {
                    break;
                }
                
                Course course = courses.get(index);
                String maKhoaHoc = code[i].getText().trim();
                String tenKhoaHoc = name[i].getText().trim();
                String moTa = des[i].getText().trim();
                String trangThai = status[i].getText().trim();
                String hinhKhoaHoc = imagePath;
                
                double hocPhi;
                try {
                    hocPhi = Double.parseDouble(price[i].getText().trim());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Học phí không hợp lệ!");
                    continue;
                }
                
                int result = khoaHocDAO.updateKhoaHoc(maKhoaHoc, tenKhoaHoc, moTa, hinhKhoaHoc, hocPhi, trangThai, maKhoaHoc);
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
                }
            }
            
            courses = khoaHocDAO.loadCoursesFromDatabase();
            updateUI(courses);
            revalidate();
            repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Xin loi, ban khong quyen han de thuc hien chuc nang nay !");
        }
    }
    
    private String handleSetCourseImg(JLabel jLabel) {
        String role = UserSession.getRole();
        if (role.equals("Manager")) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                imagePath = file.getAbsolutePath();
                System.out.println("Đường dẫn ảnh: " + imagePath);
                int originalWidth = imageIcon.getIconWidth();
                int originalHeight = imageIcon.getIconHeight();
                Image image = imageIcon.getImage().getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH);
                jLabel.setIcon(new ImageIcon(image));
            } else {
                System.out.println("Surely its dead");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Xin loi, ban khong quyen han de thuc hien chuc nang nay !");
        }
        return imagePath;
        
    }
    
    private void handleDeleteCourse(JButton jButton) {
        String role = UserSession.getRole();
        if (role.equals("Manager")) {
            String maKhoaHoc = "";
            if (jButton.getName().contains("1")) {
                maKhoaHoc = txtcode1.getText();
            } else if (jButton.getName().contains("2")) {
                maKhoaHoc = txtcode2.getText();
            } else {
                maKhoaHoc = txtcode3.getText();
            }
            
            int result = khoaHocDAO.deleteKhoaHoc(maKhoaHoc);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                courses = khoaHocDAO.loadCoursesFromDatabase();
                updateUI(courses);
                revalidate();
                repaint();
                
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Xin loi, ban khong quyen han de thuc hien chuc nang nay !");
        }
    }
    
    private void handleDetails(JButton jButton) {
        this.dispose();
        new RegisterCourse().setVisible(true);
    }
    private void txtsearchbarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsearchbarMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_txtsearchbarMouseClicked

    private void txtsearchbarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsearchbarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchbarMouseExited

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        handleSetCourseImg(lbpr1);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void lbpr2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbpr2MouseClicked
        // TODO add your handling code here:
        handleSetCourseImg(lbpr2);
    }//GEN-LAST:event_lbpr2MouseClicked

    private void lbpr3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbpr3MouseClicked
        // TODO add your handling code here:
        handleSetCourseImg(lbpr3);
    }//GEN-LAST:event_lbpr3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String role = UserSession.getRole();
        if (role.equals("Manager")) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm chuyên đề hay khóa học");
            
            if (confirm == JOptionPane.YES_OPTION) {
                this.dispose();
                new InsertCourseFrame().setVisible(true);
            } else if (confirm == JOptionPane.NO_OPTION) {
                this.dispose();
                new InsertTopicFrame().setVisible(true);
            } else {
                this.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ban khogn du dang cap");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ClerkFrame().setVisible(true);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new StudentFrame().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new CRUDTopic().setVisible(true);
    }//GEN-LAST:event_jLabel21MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                    
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CoursesFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CoursesFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CoursesFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CoursesFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CoursesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JButton btnUpdate2;
    private javax.swing.JButton btnUpdate3;
    private javax.swing.JButton btndel1;
    private javax.swing.JButton btndel2;
    private javax.swing.JButton btndel3;
    private javax.swing.JButton btndetails1;
    private javax.swing.JButton btndetails2;
    private javax.swing.JButton btndetails3;
    private javax.swing.JButton btnsearch;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbavatar;
    private javax.swing.JLabel lbcourse1;
    private javax.swing.JLabel lbcourse2;
    private javax.swing.JLabel lbcourse3;
    private javax.swing.JLabel lbpr1;
    private javax.swing.JLabel lbpr1container;
    private javax.swing.JLabel lbpr2;
    private javax.swing.JLabel lbpr2container;
    private javax.swing.JLabel lbpr3;
    private javax.swing.JLabel lbpr3container;
    private javax.swing.JTextField txtcode1;
    private javax.swing.JTextField txtcode2;
    private javax.swing.JTextField txtcode3;
    private javax.swing.JTextArea txtdes1;
    private javax.swing.JTextArea txtdes2;
    private javax.swing.JTextArea txtdes3;
    private javax.swing.JTextField txtname1;
    private javax.swing.JTextField txtname2;
    private javax.swing.JTextField txtname3;
    private javax.swing.JTextField txtprice1;
    private javax.swing.JTextField txtprice2;
    private javax.swing.JTextField txtprice3;
    private javax.swing.JLabel txtrole;
    private javax.swing.JTextField txtsearchbar;
    private javax.swing.JTextField txtstatus1;
    private javax.swing.JTextField txtstatus2;
    private javax.swing.JTextField txtstatus3;
    private javax.swing.JLabel txtusername;
    // End of variables declaration//GEN-END:variables
}
