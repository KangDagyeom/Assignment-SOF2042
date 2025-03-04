/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import DAOClass.ChuyenDeDAO;
import DAOClass.DangKyKhoaHocDAO;
import DAOClass.HocVienDAO;
import DAOClass.KhoaHocDAO;
import DAOClass.LopHocDAO;
import DAOClass.NhanVienDAO;
import DAOClass.UserSession;
import Models.DangKyKhoaHoc;
import Models.HocVien;
import Models.NhanVien;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Hyun
 */
public class RegisterCourse extends javax.swing.JFrame {

    /**
     * Creates new form TopicFrame
     */
    private DangKyKhoaHocDAO dangKyKhoaHocDAO = new DangKyKhoaHocDAO();
    private ArrayList<DangKyKhoaHoc> list = dangKyKhoaHocDAO.getAllDangKy();
    private HocVienDAO hocVienDAO = new HocVienDAO();
    private ArrayList<String> getMaHV = hocVienDAO.getMaHocVien();
    private LopHocDAO lopHocDAO = new LopHocDAO();
    private ArrayList<String> getTenLop = lopHocDAO.getTenLop();
    private KhoaHocDAO khoaHocDAO = new KhoaHocDAO();
    private ArrayList<String> getMaKhoaHoc = khoaHocDAO.getMaKhoaHoc();
    private String imagePath = "";
    private Map<String, Font> fontCache = new HashMap<>();

    public RegisterCourse() {
        initComponents();
        loadFont("Poppins-SemiBold", "/fonts/FZ Poppins-SemiBold.ttf");
        loadFont("Poppins-Regular", "/fonts/FZ Poppins-Regular.ttf");
        txtusername.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 16));
        txtrole.setFont(getCustomFont("Poppins-SemiBold", Font.PLAIN, 14));
        loadUserData();
        loadData(list);
        this.setLocationRelativeTo(null);
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

    private void loadData(ArrayList<DangKyKhoaHoc> list) {
        DefaultTableModel model = (DefaultTableModel) tblDangKy.getModel();
        model.setRowCount(0);

        int minSize = Math.min(getMaHV.size(), Math.min(getTenLop.size(), getMaKhoaHoc.size())); // Đảm bảo không bị lỗi IndexOutOfBoundsException

        for (int i = 0; i < Math.min(list.size(), minSize); i++) {
            DangKyKhoaHoc dangKyKhoaHoc = list.get(i);
            String maHV = getMaHV.get(i);
            String tenLop = getTenLop.get(i);
            String maKH = getMaKhoaHoc.get(i);

            model.addRow(new Object[]{
                maHV,
                tenLop,
                maKH,
                dangKyKhoaHoc.getHocPhi(),
                dangKyKhoaHoc.getTrangThai(),
                dangKyKhoaHoc.getNgayDangKy(),
                dangKyKhoaHoc.getDiem()
            });
        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        test1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbavatar = new javax.swing.JLabel();
        txtrole = new javax.swing.JLabel();
        txtusername = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtstcode = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtclassname = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDangKy = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        cbostatus = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtcoursecode = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtdate = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtpoint = new javax.swing.JTextField();
        btninsert = new javax.swing.JLabel();
        btnupdate = new javax.swing.JLabel();
        btndelete = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtfee = new javax.swing.JTextField();
        lbtopiccontainer = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/App-logo-homeview.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 28, -1, -1));

        test1.setForeground(new java.awt.Color(255, 255, 255));
        test1.setText("Course Activity");
        jPanel1.add(test1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

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

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setOpaque(false);

        txtstcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstcodeActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Class name:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Student code:");

        tblDangKy.setBackground(new java.awt.Color(255, 255, 255));
        tblDangKy.setForeground(new java.awt.Color(51, 51, 51));
        tblDangKy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student code", "Class name", "Course code", "Tuition fee", "Status", "Registered Date", "Point"
            }
        ));
        tblDangKy.setGridColor(new java.awt.Color(0, 102, 255));
        tblDangKy.setOpaque(false);
        tblDangKy.setSelectionBackground(new java.awt.Color(0, 153, 255));
        tblDangKy.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblDangKy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDangKyMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDangKy);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Status:");

        cbostatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chưa thanh toán" }));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Course code:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Registered date:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Course point:");

        btninsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Insert-btn.png"))); // NOI18N
        btninsert.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btninsert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btninsertMouseClicked(evt);
            }
        });

        btnupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Update-btn.png"))); // NOI18N
        btnupdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnupdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnupdateMouseClicked(evt);
            }
        });

        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Delete-btn.png"))); // NOI18N
        btndelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btndelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndeleteMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tuition fee:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(221, 221, 221)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtdate)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbostatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 37, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtclassname, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtcoursecode, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtfee, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(36, 36, 36)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtstcode, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(53, 53, 53)))
                                .addGap(134, 134, 134)
                                .addComponent(txtpoint, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(btninsert, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btndelete)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtstcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbostatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtclassname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtcoursecode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtpoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtfee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btninsert)
                    .addComponent(btnupdate)
                    .addComponent(btndelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 640, 480));

        lbtopiccontainer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Topics-courses-container.png"))); // NOI18N
        jPanel1.add(lbtopiccontainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Performance-unclick-btn.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Courses-clicked-btn.png"))); // NOI18N
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Topic-unclick-btn.png"))); // NOI18N
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Clerks-unclick-btn.png"))); // NOI18N
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 90, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Stu-unclick-btn.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 130, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Logout-unclick-btn.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, -1, -1));

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
    private void handleTopicImg(JLabel jLabel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            imagePath = file.getAbsolutePath();
            System.out.println("Đường dẫn ảnh: " + imagePath);

            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image image = imageIcon.getImage().getScaledInstance(230, 232, Image.SCALE_SMOOTH);
            jLabel.setIcon(new ImageIcon(image));
        } else {
            System.out.println("Không chọn ảnh.");
        }
    }
    private void txtstcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstcodeActionPerformed
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void btninsertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btninsertMouseClicked

        try {
            // Lấy dữ liệu từ giao diện
            String maHocVien = txtstcode.getText().trim();
            String tenLop = txtclassname.getText().trim();
            String maKhoaHoc = txtcoursecode.getText().trim();
            String hocPhiStr = txtfee.getText().trim();
            String status = cbostatus.getSelectedItem().toString();
            String ngayDangKyStr = txtdate.getText().trim();
            String diemStr = txtpoint.getText().trim();

            // 🛑 1. Kiểm tra dữ liệu rỗng
            if (maHocVien.isEmpty()) {
                showMessage("Mã học viên không được để trống!");
                return;
            }
            if (tenLop.isEmpty()) {
                showMessage("Tên lớp không được để trống!");
                return;
            }
            if (maKhoaHoc.isEmpty()) {
                showMessage("Mã khóa học không được để trống!");
                return;
            }
            if (hocPhiStr.isEmpty()) {
                showMessage("Học phí không được để trống!");
                return;
            }
            if (diemStr.isEmpty()) {
                showMessage("Điểm không được để trống!");
                return;
            }

            // 🛑 2. Kiểm tra học phí phải là số dương
            double hocPhi;
            try {
                hocPhi = Double.parseDouble(hocPhiStr);
                if (hocPhi <= 0) {
                    showMessage("Học phí phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException e) {
                showMessage("Học phí phải là số hợp lệ!");
                return;
            }

            // 🛑 3. Kiểm tra điểm hợp lệ (trong khoảng 0 - 10)
            double diem;
            try {
                diem = Double.parseDouble(diemStr);
                if (diem < 0 || diem > 10) {
                    showMessage("Điểm phải nằm trong khoảng từ 0 đến 10!");
                    return;
                }
            } catch (NumberFormatException e) {
                showMessage("Điểm phải là số hợp lệ!");
                return;
            }

            // 🛑 4. Kiểm tra ngày đăng ký (chấp nhận ngày hiện tại nếu rỗng)
            Timestamp ngayDangKy;
            try {
                if (ngayDangKyStr.isEmpty()) {
                    ngayDangKy = new Timestamp(System.currentTimeMillis());
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    ngayDangKy = new Timestamp(sdf.parse(ngayDangKyStr).getTime());
                }
            } catch (ParseException e) {
                showMessage("Ngày đăng ký không hợp lệ! Định dạng đúng: yyyy-MM-dd");
                return;
            }

            // ✅ Dữ liệu hợp lệ -> gọi DAO để thêm vào DB
            int result = dangKyKhoaHocDAO.insertDangKy(maHocVien, maKhoaHoc, tenLop, ngayDangKy, hocPhi, status, diem);
            if (result > 0) {
                showMessage("Đăng ký khóa học thành công!");
                list = dangKyKhoaHocDAO.getAllDangKy();
                loadData(list);
            } else {
                showMessage("Lỗi khi đăng ký khóa học!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_btninsertMouseClicked

    private void btnupdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnupdateMouseClicked
        try {
            // Lấy dữ liệu từ giao diện
            String maHocVien = txtstcode.getText().trim();
            String tenLop = txtclassname.getText().trim();
            String maKhoaHoc = txtcoursecode.getText().trim();
            String hocPhiStr = txtfee.getText().trim();
            String status = cbostatus.getSelectedItem().toString();
            String ngayDangKyStr = txtdate.getText().trim();
            String diemStr = txtpoint.getText().trim();

            // 🛑 1. Kiểm tra dữ liệu rỗng
            if (maHocVien.isEmpty()) {
                showMessage("Mã học viên không được để trống!");
                return;
            }
            if (tenLop.isEmpty()) {
                showMessage("Tên lớp không được để trống!");
                return;
            }
            if (maKhoaHoc.isEmpty()) {
                showMessage("Mã khóa học không được để trống!");
                return;
            }
            if (hocPhiStr.isEmpty()) {
                showMessage("Học phí không được để trống!");
                return;
            }
            if (diemStr.isEmpty()) {
                showMessage("Điểm không được để trống!");
                return;
            }

            // 🛑 2. Kiểm tra học phí phải là số dương
            double hocPhi;
            try {
                hocPhi = Double.parseDouble(hocPhiStr);
                if (hocPhi <= 0) {
                    showMessage("Học phí phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException e) {
                showMessage("Học phí phải là số hợp lệ!");
                return;
            }

            // 🛑 3. Kiểm tra điểm hợp lệ (trong khoảng 0 - 10)
            double diem;
            try {
                diem = Double.parseDouble(diemStr);
                if (diem < 0 || diem > 10) {
                    showMessage("Điểm phải nằm trong khoảng từ 0 đến 10!");
                    return;
                }
            } catch (NumberFormatException e) {
                showMessage("Điểm phải là số hợp lệ!");
                return;
            }

            // 🛑 4. Kiểm tra ngày đăng ký (chấp nhận ngày hiện tại nếu rỗng)
            Timestamp ngayDangKy;
            try {
                if (ngayDangKyStr.isEmpty()) {
                    ngayDangKy = new Timestamp(System.currentTimeMillis());
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    ngayDangKy = new Timestamp(sdf.parse(ngayDangKyStr).getTime());
                }
            } catch (ParseException e) {
                showMessage("Ngày đăng ký không hợp lệ! Định dạng đúng: yyyy-MM-dd");
                return;
            }

            // ✅ Dữ liệu hợp lệ -> gọi DAO để thêm vào DB
            int result = dangKyKhoaHocDAO.updateDangKy(maHocVien, maKhoaHoc, tenLop, ngayDangKy, hocPhi, status, diem);
            if (result > 0) {
                showMessage("Chỉnh sửa đăng ký khóa học thành công!");
                list = dangKyKhoaHocDAO.getAllDangKy();
                loadData(list);
            } else {
                showMessage("Lỗi sửa đăng ký khóa học!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnupdateMouseClicked

    private void btndeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndeleteMouseClicked
        // TODO add your handling code here:
        try {
            String maHocVien = txtstcode.getText().trim();
            String tenLop = txtclassname.getText().trim();
            String maKhoaHoc = txtcoursecode.getText().trim();
            if (maHocVien.isEmpty()) {
                showMessage("Mã học viên không được để trống!");
                return;
            }
            if (tenLop.isEmpty()) {
                showMessage("Tên lớp không được để trống!");
                return;
            }
            if (maKhoaHoc.isEmpty()) {
                showMessage("Mã khóa học không được để trống!");
                return;
            }
            int result = dangKyKhoaHocDAO.deleteDangKy(maHocVien, maKhoaHoc, tenLop);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Xóa đăng ký thành công !");
                list = dangKyKhoaHocDAO.getAllDangKy();
                loadData(list);
            } else {
                JOptionPane.showMessageDialog(this, "Lỗi xóa đăng ký !");
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_btndeleteMouseClicked

    private void tblDangKyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDangKyMouseClicked
        // TODO add your handling code here:
        loadNhanVienFromTable();
    }//GEN-LAST:event_tblDangKyMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        HomeFrame.getInstance().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ClerkFrame().setVisible(true);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new StudentFrame().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new GoodbyeFrame().setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new CRUDTopic().setVisible(true);
    }//GEN-LAST:event_jLabel21MouseClicked
    public void loadNhanVienFromTable() {
        int selectedRow = tblDangKy.getSelectedRow(); // Lấy dòng được chọn
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên!");
            return;
        }

        // Lấy dữ liệu từ JTable
        String maHocVien = tblDangKy.getValueAt(selectedRow, 0).toString();
        String tenLop = tblDangKy.getValueAt(selectedRow, 1).toString();
        String maKhoaHoc = tblDangKy.getValueAt(selectedRow, 2).toString();
        String hocPhi = tblDangKy.getValueAt(selectedRow, 3).toString();
        String TrangThai = tblDangKy.getValueAt(selectedRow, 4).toString();
        String ngayDangKy = tblDangKy.getValueAt(selectedRow, 5).toString();
        String diem = tblDangKy.getValueAt(selectedRow, 6).toString();

        // Hiển thị dữ liệu lên form
        txtstcode.setText(maHocVien);
        txtclassname.setText(tenLop);
        txtcoursecode.setText(maKhoaHoc);
        cbostatus.setSelectedItem(TrangThai);
        txtfee.setText(hocPhi);
        txtdate.setText(ngayDangKy);
        txtpoint.setText(diem);

    }

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
            java.util.logging.Logger.getLogger(InsertTopicFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertTopicFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertTopicFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertTopicFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertTopicFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btndelete;
    private javax.swing.JLabel btninsert;
    private javax.swing.JLabel btnupdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbostatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbavatar;
    private javax.swing.JLabel lbtopiccontainer;
    private javax.swing.JTable tblDangKy;
    private javax.swing.JLabel test1;
    private javax.swing.JTextField txtclassname;
    private javax.swing.JTextField txtcoursecode;
    private javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtfee;
    private javax.swing.JTextField txtpoint;
    private javax.swing.JLabel txtrole;
    private javax.swing.JTextField txtstcode;
    private javax.swing.JLabel txtusername;
    // End of variables declaration//GEN-END:variables
}
