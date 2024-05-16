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

import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GiaoDienChinh1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	static Connection con;
	static Statement stmt;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDienChinh1 frame = new GiaoDienChinh1();
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

	public GiaoDienChinh1() {
		setTitle("GiaoDienChinhQuanLI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 538);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 10, 777, 479);
		contentPane.add(layeredPane);
		
		JButton btnCapNhat = new JButton("Cập nhật sản phẩm");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CapNhatSanPham capnhat = new CapNhatSanPham();
				capnhat.getData();
				capnhat.setVisible(true);
			}
		});
		btnCapNhat.setForeground(new Color(255, 255, 255));
		btnCapNhat.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		btnCapNhat.setContentAreaFilled(false);
		btnCapNhat.setBorderPainted(false);
		btnCapNhat.setBounds(141, 153, 254, 50);
		layeredPane.add(btnCapNhat);
		
		JLabel lblTrangChu = new JLabel("TRANG CHỦ");
		lblTrangChu.setForeground(new Color(255, 128, 0));
		lblTrangChu.setFont(new Font("Segoe UI Black", Font.BOLD, 40));
		lblTrangChu.setBounds(269, 20, 260, 74);
		layeredPane.add(lblTrangChu);
		
		JButton btnQuanLi = new JButton("Quản lí đơn hàng");
		btnQuanLi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				QuanLiDonHang quanli = new QuanLiDonHang();
				quanli.napDuLieuTuCSDL();
				quanli.setVisible(true);
			}
		});
		btnQuanLi.setForeground(new Color(255, 255, 255));
		btnQuanLi.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		btnQuanLi.setContentAreaFilled(false);
		btnQuanLi.setBorderPainted(false);
		btnQuanLi.setBounds(141, 240, 238, 50);
		layeredPane.add(btnQuanLi);
		
		JButton btnDoanhThu = new JButton("Doanh thu");
		btnDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DoanhThu doanhthu = new DoanhThu();
				doanhthu.napDuLieuTuCSDL();
				doanhthu.setVisible(true);
			}
		});
		
		btnDoanhThu.setForeground(new Color(255, 255, 255));
		btnDoanhThu.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		btnDoanhThu.setContentAreaFilled(false);
		btnDoanhThu.setBorderPainted(false);
		btnDoanhThu.setBounds(126, 329, 238, 50);
		layeredPane.add(btnDoanhThu);
		
		JButton btnThoat = new JButton("");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DangNhap1 dn1 = new DangNhap1();
				dn1.setVisible(true);
			}
		});
		btnThoat.setForeground(Color.WHITE);
		btnThoat.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnThoat.setContentAreaFilled(false);
		btnThoat.setBorderPainted(false);
		btnThoat.setBounds(0, 0, 95, 52);
		layeredPane.add(btnThoat);
		
		JLabel lblAnhNen = new JLabel("");
		lblAnhNen.setBounds(0, 0, 792, 510);
		contentPane.add(lblAnhNen);
		lblAnhNen.setIcon(new ImageIcon("D:\\ĐACS1\\giaodienchinhQL.png"));
		
		
		
	}
	 }


	
