package GiaoDienOrder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.management.loading.PrivateClassLoader;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;



public class GiaoDienChinh extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnBan1;
	private JTextField txtTongDonHang;
	private JLayeredPane layeredPane;
	private JButton btnBan2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDienChinh frame = new GiaoDienChinh();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	
	
	public GiaoDienChinh() {
		
		setTitle("Trang chủ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 785, 494);
		contentPane.add(layeredPane);
		
		JButton btnThoat = new JButton("Quay lại");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThoat.setBackground(new Color(0, 0, 0));
		btnThoat.setIcon(new ImageIcon("D:\\ĐACS1\\ảnh mới\\muiten.png"));
		btnThoat.setForeground(new Color(227, 175, 113));
		btnThoat.setBounds(158, 10, 123, 32);
		btnThoat.setBorderPainted(false);
		btnThoat.setContentAreaFilled(false);
		layeredPane.add(btnThoat);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            dispose();
                            DangNhap dn = new DangNhap();
                            dn.setVisible(true);
			}
		});
	
		
		JButton btnTrangchu = new JButton("Trang Chủ");
		btnTrangchu.setForeground(new Color(255, 255, 255));
		btnTrangchu.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		btnTrangchu.setBounds(37, 187, 217, 59);
		btnTrangchu.setBorderPainted(false);
		btnTrangchu.setContentAreaFilled(false);
		layeredPane.add(btnTrangchu);
		btnTrangchu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GiaoDienChinh main = new GiaoDienChinh();
				main.setVisible(true);
			}
		});
		
		
		JButton btnDonhang = new JButton("  Đơn Hàng");
		btnDonhang.setForeground(Color.WHITE);
		btnDonhang.setFont(new Font("Segoe UI Black", Font.BOLD, 20));
		btnDonhang.setBounds(54, 315, 200, 51);
		btnDonhang.setBorderPainted(false);
		btnDonhang.setContentAreaFilled(false);
		layeredPane.add(btnDonhang);
		btnDonhang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DonHang donhang = new DonHang();
				donhang.setVisible(true);
			}
		});
	
		
		btnBan1 = new JButton(" 1");
		btnBan1.setForeground(new Color(255, 140, 0));
		btnBan1.setFont(new Font("Arial", Font.BOLD, 24));
		btnBan1.setBounds(325, 229, 89, 74);
		btnBan1.setBorderPainted(false);
		btnBan1.setContentAreaFilled(false);
		layeredPane.add(btnBan1);
        btnBan1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {    
                Ban1 b1 = new Ban1();
                b1.napDuLieuTuCSDL(); // Gọi phương thức để chuyển dữ liệu từ bảng danhsach sang bảng của lớp Ban1
                b1.setVisible(true);
                dispose();
			}
		});
		
		
		btnBan2 = new JButton("2");
		btnBan2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Ban2 b2 = new Ban2();
				b2.setVisible(true);
			}
		});
		btnBan2.setForeground(new Color(255, 140, 0));
		btnBan2.setFont(new Font("Arial", Font.BOLD, 24));
		btnBan2.setBounds(451, 229, 78, 74);
		btnBan2.setBorderPainted(false);
		btnBan2.setContentAreaFilled(false);
		layeredPane.add(btnBan2);
		
		JButton btnBan3 = new JButton("3");
		btnBan3.setForeground(new Color(255, 140, 0));
		btnBan3.setFont(new Font("Arial", Font.BOLD, 24));
		btnBan3.setBounds(565, 229, 78, 74);
		btnBan3.setBorderPainted(false);
		btnBan3.setContentAreaFilled(false);
		layeredPane.add(btnBan3);
		
		JButton btnBan4 = new JButton("4");
		btnBan4.setForeground(new Color(255, 140, 0));
		btnBan4.setFont(new Font("Arial", Font.BOLD, 24));
		btnBan4.setBounds(686, 229, 78, 74);
		btnBan4.setBorderPainted(false);
		btnBan4.setContentAreaFilled(false);
		layeredPane.add(btnBan4);
		
		JButton btnBan5 = new JButton(" 5");
		btnBan5.setForeground(new Color(255, 140, 0));
		btnBan5.setFont(new Font("Arial", Font.BOLD, 24));
		btnBan5.setBounds(325, 409, 89, 74);
		btnBan5.setBorderPainted(false);
		btnBan5.setContentAreaFilled(false);		
		layeredPane.add(btnBan5);
		
		JButton btnBan6 = new JButton("6");
		btnBan6.setForeground(new Color(255, 140, 0));
		btnBan6.setFont(new Font("Arial", Font.BOLD, 24));
		btnBan6.setBounds(451, 409, 78, 74);
		btnBan6.setBorderPainted(false);
		btnBan6.setContentAreaFilled(false);
		layeredPane.add(btnBan6);
		
		
		JButton btnBan7 = new JButton("7");
		btnBan7.setForeground(new Color(255, 140, 0));
		btnBan7.setFont(new Font("Arial", Font.BOLD, 24));
		btnBan7.setBounds(565, 409, 78, 74);
		btnBan7.setBorderPainted(false);
		btnBan7.setContentAreaFilled(false);
		layeredPane.add(btnBan7);
		
		JButton btnBan8 = new JButton("8");
		btnBan8.setForeground(new Color(255, 140, 0));
		btnBan8.setFont(new Font("Arial", Font.BOLD, 24));
		btnBan8.setBounds(686, 409, 78, 74);
		btnBan8.setBorderPainted(false);
		btnBan8.setContentAreaFilled(false);
		layeredPane.add(btnBan8);
		
		JLabel lblChonban = new JLabel("Chọn bàn");
		lblChonban.setForeground(new Color(236, 112, 19));
		lblChonban.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		lblChonban.setBounds(466, 26, 227, 71);
		layeredPane.add(lblChonban);
		
		JLabel lblTng = new JLabel("Tổng Đơn Hàng:");
		lblTng.setForeground(new Color(255, 128, 64));
		lblTng.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		lblTng.setBounds(37, 98, 156, 32);
		layeredPane.add(lblTng);
		
		txtTongDonHang = new JTextField();
		txtTongDonHang.setOpaque(false);
		txtTongDonHang.setForeground(new Color(255, 140, 0));
		txtTongDonHang.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		txtTongDonHang.setColumns(10);
		txtTongDonHang.setBorder(null);
		txtTongDonHang.setBounds(203, 98, 78, 33);
		layeredPane.add(txtTongDonHang);
		
		JLabel lblAnhGiaoDienChinh = new JLabel("");
		lblAnhGiaoDienChinh.setIcon(new ImageIcon("D:\\ĐACS1\\giaodienchinhNV.png"));
		lblAnhGiaoDienChinh.setBounds(0, 0, 785, 500);
		contentPane.add(lblAnhGiaoDienChinh);
		
		demSoButtonMauXanh();
	}
	
	public void capNhatMauSauKhiLuu() {
	      btnBan1.setForeground(Color.BLUE);
	
	}
	


	public void capNhatMauSauKhiThanhToan() {
		btnBan1.setForeground(Color.ORANGE);
	}
	
	
	public int demSoButtonMauXanh() {
	    int soButtonMauXanh=0;

	    // Lặp qua tất cả các nút trong giao diện chính
	    Component[] components = layeredPane.getComponents();
	    for (Component component : components) {
	        // Kiểm tra nếu thành phần là JButton
	        if (component instanceof JButton) {
	            JButton button = (JButton) component;
	            // Kiểm tra màu văn bản của nút
	            if (button.getForeground().equals(Color.BLUE)) {
	                soButtonMauXanh++;
	                
	            }
	        }

	    }
	   
	    return soButtonMauXanh;
	}
	
	
	public void capNhatSoButtonMauXanh() {
        txtTongDonHang.setText(Integer.toString(demSoButtonMauXanh()));
    }
	

}