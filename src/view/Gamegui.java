package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

public class Gamegui extends JFrame {

	private JPanel contentPane;
	int zufallsZahl;
	int range;
	int optimum;
	private JPanel panel;
	private JLabel lblZufallszahlZwischen;
	private JTextField txtRange;
	private JButton btnStart;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JLabel lblSteps;
	private JTextField txtguess;
	private JLabel lblhint;
	private JPanel panel_2;
	private JLabel lblRatezahl;
	private JLabel lblError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gamegui frame = new Gamegui();
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
	public Gamegui() {
		setBackground(new Color(144, 238, 144));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 296, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setBackground(new Color(60, 179, 113));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		lblZufallszahlZwischen = new JLabel("Zufallszahl zwischen 0 und ");
		lblZufallszahlZwischen.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel.add(lblZufallszahlZwischen, "cell 0 0,alignx trailing");
		
		txtRange = new JTextField();
		txtRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startGame();
			}
		});
		panel.add(txtRange, "cell 1 0,growx");
		txtRange.setColumns(10);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startGame();
			}
		});
		panel.add(btnStart, "flowx,cell 0 1 2 1");
		
		lblError = new JLabel("");
		lblError.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		panel.add(lblError, "cell 1 1");
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 205, 170));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		lblNewLabel = new JLabel("Optimale verbleibende Rateversuche:");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_1.add(lblNewLabel);
		
		lblSteps = new JLabel("--");
		lblSteps.setBackground(Color.GREEN);
		lblSteps.setFont(new Font("Comic Sans MS", Font.PLAIN, 37));
		lblSteps.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(lblSteps);
		
		lblhint = new JLabel("");
		panel_1.add(lblhint);
		
		txtguess = new JTextField();
		txtguess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkguess();
			}
		});
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(64, 224, 208));
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		lblRatezahl = new JLabel("Ratezahl:");
		lblRatezahl.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		panel_2.add(lblRatezahl);
		panel_2.add(txtguess);
		txtguess.setColumns(10);
	}
	
	public void startGame(){
		lblhint.setText("");
		int range;
		try {
			range = Integer.parseInt(txtRange.getText());
			zufallsZahl =  (int) (Math.random()*range);
			optimum = (int) (Math.log(range) / Math.log(2));
			lblSteps.setText(optimum+"");

		} catch (NumberFormatException e) {
			lblError.setText("Bitte maximum eingeben!");
			e.printStackTrace();
		}

	}
	public void checkguess(){

		if(Integer.parseInt(txtguess.getText())>zufallsZahl){
			lblhint.setText("Zufallszahl Zahl ist kleiner!");
		}
		if(Integer.parseInt(txtguess.getText())<zufallsZahl){
			lblhint.setText("Zufallszahl Zahl ist größer!");
		}
		if(Integer.parseInt(txtguess.getText())==zufallsZahl){
			lblhint.setText("Gratuliere, Sie haben gewonnen!");
		}
		
		lblRatezahl.setText("Ratezahl:"+txtguess.getText());
		txtguess.setText("");
		optimum--;
		lblSteps.setText(optimum+"");
	}

}
