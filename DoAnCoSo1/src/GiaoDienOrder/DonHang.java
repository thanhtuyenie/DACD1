package GiaoDienOrder;

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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;

public class DonHang extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private int stt = 0;
	
	static Connection con;
	static Statement stmt;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DonHang frame = new DonHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
//	public DonHang(int soBan, double tongTien, Timestamp thoiGian) {
//        // Khởi tạo frame DonHang
//        initialize();
//        // Thêm dữ liệu vào bảng
//        AddDataTable(soBan, tongTien, thoiGian);
//    }
//	
//	
//	
//	private void initialize() {
//		// TODO Auto-generated method stub
//		
//	}

	public DonHang() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 813, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 10, 789, 508);
		contentPane.add(layeredPane);
		
		JButton btnThem = new JButton("Thêm ");
		btnThem.setFont(new Font("Segoe UI Black", Font.PLAIN, 23));
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GiaoDienChinh main = new GiaoDienChinh();
				main.setVisible(true);
			}
		});
		btnThem.setForeground(new Color(255, 128, 0));
		btnThem.setBounds(173, 424, 145, 44);
		btnThem.setBorderPainted(false);
		btnThem.setContentAreaFilled(false);
		layeredPane.add(btnThem);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = table.getSelectedRow();
		        if (selectedRow >= 0) {
		            DefaultTableModel model = (DefaultTableModel) table.getModel();
		            int stt =  (int) model.getValueAt(selectedRow, 1);

					// Xóa dữ liệu khỏi CSDL
		            xoaDuLieuTrenCSDL(stt);
		            
		            // Xóa hàng khỏi bảng
		            model.removeRow(selectedRow);
	
		        } else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
		        }
				
			}
		});
		btnXoa.setForeground(new Color(255, 128, 0));
		btnXoa.setFont(new Font("Segoe UI Black", Font.PLAIN, 23));
		btnXoa.setContentAreaFilled(false);
		btnXoa.setBorderPainted(false);
		btnXoa.setBounds(468, 424, 145, 44);
		layeredPane.add(btnXoa);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(139, 139, 528, 173);
		layeredPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				 "STT", "S\u1ED1 B\u00E0n", "T\u1ED5ng Ti\u1EC1n", "Th\u1EDDi Gian", "Mã Nhân Viên"
			}
		));
		table.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		
		JButton btnThoatton = new JButton("");
		btnThoatton.setContentAreaFilled(false);
		btnThoatton.setBorderPainted(false);
		btnThoatton.setForeground(new Color(255, 255, 255));
		btnThoatton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GiaoDienChinh main = new GiaoDienChinh();
				main.setVisible(true);
			}
		});
		btnThoatton.setBounds(10, 0, 70, 41);
		layeredPane.add(btnThoatton);
		
		JLabel lblNewLabel_1 = new JLabel("Đơn hàng");
		lblNewLabel_1.setForeground(new Color(255, 128, 0));
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(324, 25, 190, 45);
		layeredPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 789, 508);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon("D:\\ĐACS1\\donhangNV.png"));
		
		napDuLieuTuCSDL();
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
            	int stt = rs.getInt("stt");
                int soban = rs.getInt("soban");
                double tongtien = rs.getDouble("tongtien");
                Timestamp thoigian = rs.getTimestamp("thoigian");
                String manhanvien = rs.getString("manhanvien");
              
                model.addRow(new Object[]{stt++, soban, tongtien, thoigian, manhanvien});
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	public void xoaDuLieuTrenCSDL(int stt) {
        try {
            // Kết nối CSDL
            Connection con = ketNoiCSDL();
            
            // Tạo truy vấn xóa dữ liệu
            String sql = "DELETE FROM donhang WHERE stt=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, stt);
            
            // Thực thi truy vấn
            ps.executeUpdate();
            
            // Đóng kết nối
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
	
	
	
	 }


	
