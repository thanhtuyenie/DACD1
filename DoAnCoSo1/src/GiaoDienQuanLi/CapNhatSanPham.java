package GiaoDienQuanLi;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;

public class CapNhatSanPham extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField tfDongia1;
    private JTextField tfTenmon1;
    private DefaultTableModel model;

    static Connection con;
    static Statement stmt;
    private JTextField tfMasp;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CapNhatSanPham frame = new CapNhatSanPham();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CapNhatSanPham() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 801, 533);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        tfDongia1 = new JTextField();
        tfDongia1.setBounds(66, 314, 145, 28);
        tfDongia1.setColumns(10);
        contentPane.add(tfDongia1);

        tfMasp = new JTextField();
        tfMasp.setBounds(66, 166, 145, 28);
        tfMasp.setColumns(10);
        contentPane.add(tfMasp);

        tfTenmon1 = new JTextField();
        tfTenmon1.setBounds(66, 231, 145, 33);
        tfTenmon1.setColumns(10);
        contentPane.add(tfTenmon1);
        
        JLabel lblCapnhatSP = new JLabel("Cập nhật sản phẩm");
        lblCapnhatSP.setForeground(new Color(236, 112, 19));
        lblCapnhatSP.setFont(new Font("Segoe UI Black", Font.PLAIN, 23));
        lblCapnhatSP.setBounds(327, 0, 259, 71);
        contentPane.add(lblCapnhatSP);

        JLabel lblDongia1 = new JLabel("Đơn giá/ món");
        lblDongia1.setBounds(66, 287, 130, 28);
        lblDongia1.setForeground(Color.WHITE);
        lblDongia1.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        lblDongia1.setBackground(Color.BLACK);
        contentPane.add(lblDongia1);

        JLabel lblMasp = new JLabel("Mã sản phẩm ");
        lblMasp.setBounds(66, 128, 130, 28);
        lblMasp.setForeground(Color.WHITE);
        lblMasp.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        lblMasp.setBackground(Color.BLACK);
        contentPane.add(lblMasp);

        JLabel lblTenmon1 = new JLabel("Tên món");
        lblTenmon1.setBounds(66, 204, 130, 28);
        lblTenmon1.setForeground(Color.WHITE);
        lblTenmon1.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        lblTenmon1.setBackground(Color.BLACK);
        contentPane.add(lblTenmon1);

        JButton btnThoat = new JButton("");
        btnThoat.setBorder(null);
        btnThoat.setContentAreaFilled(false);
        btnThoat.setBorderPainted(false);
        btnThoat.setBounds(0, 0, 57, 28);
        btnThoat.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        contentPane.add(btnThoat);
        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                GiaoDienChinh1 main = new GiaoDienChinh1();
                main.setVisible(true);
            }
        });

        JButton btnXoa = new JButton("Xóa");
        btnXoa.setContentAreaFilled(false);
        btnXoa.setBorderPainted(false);
        btnXoa.setBounds(590, 443, 81, 37);
        btnXoa.setForeground(new Color(236, 112, 19));
        btnXoa.setFont(new Font("Segoe UI Black", Font.BOLD, 19));
        contentPane.add(btnXoa);
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    String masp = (String) model.getValueAt(selectedRow, 0);
                    model.removeRow(selectedRow);
                    try {
                        con = ketNoiCSDL();
                        String sql = "DELETE FROM machang WHERE masanpham=?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, masp);
                        ps.executeUpdate();
                        con.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton btnThem = new JButton("Thêm");
        btnThem.setContentAreaFilled(false);
        btnThem.setBorderPainted(false);
        btnThem.setBounds(291, 440, 103, 43);
        btnThem.setForeground(new Color(236, 112, 19));
        btnThem.setFont(new Font("Segoe UI Black", Font.BOLD, 19));
        contentPane.add(btnThem);
        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (tfMasp.getText().isEmpty() || tfTenmon1.getText().isEmpty() || tfDongia1.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    String masp = tfMasp.getText();
                    String tenmon = tfTenmon1.getText();
                    double dongia = Double.parseDouble(tfDongia1.getText());

                    try {
                        con = ketNoiCSDL();
                        String sql = "INSERT INTO machang (masanpham, tenmon, dongia) VALUES (?, ?, ?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, masp);
                        ps.setString(2, tenmon);
                        ps.setDouble(3, dongia);
                        ps.executeUpdate();
                        con.close();
                        
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.addRow(new Object[]{masp, tenmon, dongia});
                       
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Bạn đã nhập trùng mã sản phẩm. Mã sản phẩm là duy nhất", "Lỗi", JOptionPane.WARNING_MESSAGE);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL hoặc lỗi khác", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }

                    tfMasp.setText("");
                    tfTenmon1.setText("");
                    tfDongia1.setText("");
                }
            }
        });

        JButton btnCapNhat = new JButton("Cập Nhật");
        btnCapNhat.setContentAreaFilled(false);
        btnCapNhat.setBorderPainted(false);
        btnCapNhat.setBounds(430, 443, 136, 37);
        btnCapNhat.setFont(new Font("Segoe UI Black", Font.BOLD, 19));
        btnCapNhat.setForeground(new Color(236, 112, 19));
        contentPane.add(btnCapNhat);
        btnCapNhat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Edit();
            }
        });

        JButton btnMenu = new JButton("Menu");
        btnMenu.setContentAreaFilled(false);
        btnMenu.setBorderPainted(false);
        btnMenu.setFont(new Font("Segoe UI Black", Font.BOLD, 19));
        btnMenu.setForeground(new Color(236, 112, 19));
        btnMenu.setBounds(138, 443, 103, 37);
        contentPane.add(btnMenu);
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getData();
            }
        });
        
        
        JButton btnresetForm = new JButton("");
        btnresetForm.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
        btnresetForm.setContentAreaFilled(false);
        btnresetForm.setBorderPainted(false);
        btnresetForm.setBounds(0, 0, 57, 37);
        contentPane.add(btnresetForm);
        btnresetForm.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 tfMasp.setText("");
                 tfTenmon1.setText("");
                 tfDongia1.setText("");
        	}
        });
     

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(261, 136, 500, 217);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"Mã sản phẩm", "Tên món", "Đơn giá/ món"}
        ));

        // Add MouseListener to the table
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    String masanpham = (String) table.getValueAt(row, 0);
                    String tenmon = (String) table.getValueAt(row, 1);
                    String dongia = String.valueOf(table.getValueAt(row, 2));

                    tfMasp.setText(masanpham);
                    tfTenmon1.setText(tenmon);
                    tfDongia1.setText(dongia);
                }
            }
        });

        JLabel lblAnh = new JLabel("");
        lblAnh.setIcon(new ImageIcon("D:\\ĐACS1\\capnhatQL.png"));
        lblAnh.setBounds(0, 0, 785, 494);
        contentPane.add(lblAnh);
    }

    public static Connection ketNoiCSDL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/app_order?useUnicode=true&characterEncoding=UTF-8", "root", "");
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

   
    public void Edit() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            if (tfTenmon1.getText().isEmpty() || tfDongia1.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                con = ketNoiCSDL();
                String masp = (String) table.getValueAt(selectedRow, 0);
                String sql = "UPDATE machang SET tenmon=?, dongia=? WHERE masanpham=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, tfTenmon1.getText());
                ps.setDouble(2, Double.parseDouble(tfDongia1.getText()));
                ps.setString(3, masp);
                ps.executeUpdate();
                con.close();

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setValueAt(tfTenmon1.getText(), selectedRow, 1);
                model.setValueAt(Double.parseDouble(tfDongia1.getText()), selectedRow, 2);

                JOptionPane.showMessageDialog(null, "Cập nhật thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
    public void getData() {
    	con = ketNoiCSDL();
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM machang");
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                String masanpham = rs.getString("masanpham");
                String tenmon = rs.getString("tenmon");
                double dongia = rs.getDouble("dongia");
                model.addRow(new Object[]{masanpham, tenmon, dongia});
            }
            con.close();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
}