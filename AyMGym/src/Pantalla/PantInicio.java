package Pantalla;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;

public class PantInicio {

	private JFrame frmAymgym;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantInicio window = new PantInicio();
					window.frmAymgym.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public PantInicio() {
		initialize();
	}


	private void initialize() {
		frmAymgym = new JFrame();
		frmAymgym.setResizable(false);
		frmAymgym.getContentPane().setBackground(new Color(192, 192, 192));
		frmAymgym.getContentPane().setLayout(null);
		frmAymgym.setTitle("AyMGym");
		frmAymgym.setBounds(100, 100, 689, 386);
		frmAymgym.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmAymgym.setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		mnSistema.setFont(new Font("Arial", Font.PLAIN, 17));
		menuBar.add(mnSistema);
		
		JMenuItem mntmAltaAlumno = new JMenuItem("Alta de Alumno");
		mnSistema.add(mntmAltaAlumno);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnSistema.add(mntmSalir);
	}
}
