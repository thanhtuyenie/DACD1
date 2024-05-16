package GiaoDienQuanLi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;

public class QuanLiDonHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	static Connection con;
	static Statement stmt;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiDonHang frame = new QuanLiDonHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public QuanLiDonHang() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 10, 787, 471);
		contentPane.add(layeredPane);
		
		JLabel lblQuanLI = new JLabel("Quản Lí Đơn Hàng");
		lblQuanLI.setForeground(new Color(255, 128, 0));
		lblQuanLI.setFont(new Font("Segoe UI Black", Font.PLAIN, 28));
		lblQuanLI.setBounds(301, 25, 283, 45);
		layeredPane.add(lblQuanLI);
		
		JLabel lblAnhNen = new JLabel("");
		lblAnhNen.setBounds(0, 0, 802, 496);
		contentPane.add(lblAnhNen);
		lblAnhNen.setIcon(new ImageIcon("D:\\ĐACS1\\donhangQL.png"));
		
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            Timestamp thoigian =  (Timestamp) model.getValueAt(selectedRow, 2);
		            String manhanvien = (String) model.getValueAt(selectedRow, 3);

					// Xóa dữ liệu khỏi CSDL
		            xoaDuLieuTrenCSDL(thoigian, manhanvien);
		            
		            // Xóa hàng khỏi bảng
		            model.removeRow(selectedRow);
	
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
				
			}
		});
		btnXoa.setForeground(new Color(255, 128, 0));
		btnXoa.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnXoa.setContentAreaFilled(false);
		btnXoa.setBorderPainted(false);
		btnXoa.setBounds(244, 423, 141, 44);
		layeredPane.add(btnXoa);
		
		
		
		
		JButton btnThanhToan = new JButton("Doanh Thu");
		btnThanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				tinhVaLuuDoanhThuTheoNgay();
				DoanhThu doanhthu = new DoanhThu();
				doanhthu.napDuLieuTuCSDL();
				doanhthu.setVisible(true);
			}
		});
		btnThanhToan.setForeground(new Color(255, 128, 0));
		btnThanhToan.setFont(new Font("Segoe UI Black", Font.PLAIN, 20));
		btnThanhToan.setContentAreaFilled(false);
		btnThanhToan.setBorderPainted(false);
		btnThanhToan.setBounds(468, 420, 141, 51);
		layeredPane.add(btnThanhToan);
		
		
		JButton btnThoat = new JButton(".");
		btnThoat.setContentAreaFilled(false);
		btnThoat.setBorderPainted(false);
		btnThoat.setForeground(new Color(255, 255, 255));
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GiaoDienChinh1 main = new GiaoDienChinh1();
				main.setVisible(true);
			}
		});
		btnThoat.setBounds(10, 0, 53, 24);
		layeredPane.add(btnThoat);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(151, 132, 521, 213);
		layeredPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			 "S\u1ED1 B\u00E0n", "T\u1ED5ng Ti\u1EC1n", "Th\u1EDDi Gian", "Nhân viên"
			}
		));
		table.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		
		
		
	
	}
	
	
	public static Connection ketNoiCSDL() {
  	  
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/app_order?useUnicode=yes&characterEncoding=UTF-8", "root", "");
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
        
    }
	
	
	public void napDuLieuTuCSDL() {

        con = ketNoiCSDL(); 
        
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM donhang");
            DefaultTableModel model = (DefaultTableModel) table.getModel();   
            model.setRowCount(0);

            while (rs.next()) {
                int soban = rs.getInt("soban");
                double tongtien = rs.getDouble("tongtien");
                Timestamp thoigian = rs.getTimestamp("thoigian");
                String manhanvien = rs.getString("manhanvien");
              
                model.addRow(new Object[]{soban, tongtien, thoigian, manhanvien});
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	public void xoaDuLieuTrenCSDL(Timestamp thoigian, String manhanvien) {
	    try {
	       
	        Connection con = ketNoiCSDL();
	        
	        String sql = "DELETE FROM donhang WHERE thoigian=? AND manhanvien=?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setTimestamp(1, thoigian);
	        ps.setString(2, manhanvien);
	        
	        ps.executeUpdate();
	        
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void tinhVaLuuDoanhThuTheoNgay() {
	    try {
	        con = ketNoiCSDL();
	        String sql = "INSERT INTO doanhthu (ngay, doanhthu) " +
	                     "SELECT DATE(thoigian), SUM(tongtien) " +
	                     "FROM donhang " +
	                     "GROUP BY DATE(thoigian)"+
	                     "ON DUPLICATE KEY UPDATE doanhthu = VALUES(doanhthu)";

	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.executeUpdate();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	
	
	

}


	
