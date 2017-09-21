package calculator;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Button;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class View {

	private JFrame frame;
	private JButton btnSqrt;
	private ArrayList<JButton> buttons;
	private String text1 = "0";//первое число
	private String text2 = "0";//второе число
	private JLabel label;//ввод данных
	private JLabel label_1;//вывод данных
	private double temp;//Результат вычисления

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View() {		
		initialize();
		listen();
	}
	
	//вычисляем корень числа
	private void listen() {
		btnSqrt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Проверяем ввели ли мы знак после первого числа
				//если ввели знак, а потом нажали корень, то убераем знак
				boolean znak1 =text1.contains("=") || text1.contains("+") || text1.contains("/") || text1.contains("*") ||text1.contains("-");
				if(znak1) {
					text1 = text1.substring(0, text1.length()-1);
				}
				temp = Math.sqrt(Double.parseDouble(text1));//вычисляем корень
				//и выводим на экран
				if(temp % 1 == 0) {//убераем дробную часть
					label_1.setText((""+temp).substring(0, (""+temp).length()-2));
				}
				else {
					label_1.setText(""+temp);
				}					
				label.setText("\u221a" + text1 + "=");										
				frame.getContentPane().repaint();					
				text1 = "" + temp;
			}
		});
		
		//обрабатываем нажатие цифр и знаков
		for (JButton btn : buttons) {			
			btn.addActionListener(new ActionListener() {				

				@Override
				public void actionPerformed(ActionEvent e) {
					//Проверяем ввели ли мы знак после первого числа
					boolean znak1 = text1.contains("+") || text1.contains("/") || text1.contains("*") ||text1.contains("-");
					//Проверяем ввели ли мы знак после второго числа
					boolean znak2 =e.getActionCommand().equals("=") || e.getActionCommand().equals("+") || e.getActionCommand().equals("-") || e.getActionCommand().equals("*") || e.getActionCommand().equals("/");
					
					//Если результат прошлого вычисления выдал ошибку делаем сброс
					if (text1.equals("Infinity=")) {
						text1 = "0";
					}
					//убираем = из переменной
					if(text1.contains("=")) {
						text1 = text1.substring(0, text1.length()-1);
					}
					
					if(znak1) {
						if(znak2) {//Если мы ввели оба числа - переходим к вычислению
							switch (text1.substring(text1.length()-1)) {//получаем операцию
								case "+" :
									//сохраняем вычисление
									//первое число без последнего элемента тк хроним в нем знак
									temp = Double.parseDouble(text1.substring(0, text1.length()-1)) + Double.parseDouble(text2);
									finish(e);
									break;
								case "-" :
									temp = Double.parseDouble(text1.substring(0, text1.length()-1)) - Double.parseDouble(text2);
									finish(e);
									break;
								case "*" :
									temp = Double.parseDouble(text1.substring(0, text1.length()-1)) * Double.parseDouble(text2);
									finish(e);
									break;
								case "/" :									
									temp = Double.parseDouble(text1.substring(0, text1.length()-1)) / Double.parseDouble(text2);
									finish(e);
									break;
																
							}
						}
						//вводим второе число
						else {
							if(text2.equals("0")) {
								text2 = "";
							}
							text2 += e.getActionCommand();					
							label.setText(text1 + text2);										
							frame.getContentPane().repaint();
						}
					}
					//вводим первое число
					else {						
						if(text1.equals("0")) {
							text1 = "";
						}
						text1 += e.getActionCommand();					
						label.setText(text1);										
						frame.getContentPane().repaint();
					}
					
						
				}
				
				// функция выводит на экран результаты ввода
				private void finish(ActionEvent e) {	
					if(temp % 1 == 0) {
						label_1.setText((""+temp).substring(0, (""+temp).length()-2));
					}
					else {
						label_1.setText(""+temp);
					}					
					text2 += e.getActionCommand();					
					label.setText(text1 + text2);										
					frame.getContentPane().repaint();					
					text2 = "0";					
					text1 = temp + e.getActionCommand();
					
				}
			});
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 270, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		buttons = new ArrayList<>();
		
		JButton btnNewButton = new JButton("7");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(10, 81, 47, 23);
		buttons.add(btnNewButton);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("8");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(67, 81, 47, 23);
		buttons.add(btnNewButton_1);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("9");
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(124, 81, 47, 23);
		buttons.add(btnNewButton_2);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton button = new JButton("4");
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(10, 115, 47, 23);
		buttons.add(button);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("5");
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(67, 115, 47, 23);
		buttons.add(button_1);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("6");
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setBounds(124, 115, 47, 23);
		buttons.add(button_2);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("1");
		button_3.setBackground(Color.LIGHT_GRAY);
		button_3.setBounds(10, 149, 47, 23);
		buttons.add(button_3);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("2");
		button_4.setBackground(Color.LIGHT_GRAY);
		button_4.setBounds(67, 149, 47, 23);
		buttons.add(button_4);
		frame.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("3");
		button_5.setBackground(Color.LIGHT_GRAY);
		button_5.setBounds(124, 149, 47, 23);
		buttons.add(button_5);
		frame.getContentPane().add(button_5);
		
		JButton button_6 = new JButton("0");
		button_6.setBackground(Color.LIGHT_GRAY);
		button_6.setBounds(67, 183, 47, 23);
		buttons.add(button_6);
		frame.getContentPane().add(button_6);
		
		JButton button_7 = new JButton("+");
		button_7.setBackground(Color.LIGHT_GRAY);
		button_7.setBounds(197, 215, 47, 36);
		buttons.add(button_7);
		frame.getContentPane().add(button_7);
		
		JButton button_8 = new JButton("-");
		button_8.setBackground(Color.LIGHT_GRAY);
		button_8.setBounds(197, 170, 47, 36);
		buttons.add(button_8);
		frame.getContentPane().add(button_8);
		
		JButton button_9 = new JButton("*");
		button_9.setBackground(Color.LIGHT_GRAY);
		button_9.setBounds(197, 123, 47, 36);
		buttons.add(button_9);
		frame.getContentPane().add(button_9);
		
		JButton button_10 = new JButton("/");
		button_10.setBackground(Color.LIGHT_GRAY);
		button_10.setBounds(197, 70, 47, 36);
		buttons.add(button_10);
		frame.getContentPane().add(button_10);		
		
		btnSqrt = new JButton("\u221a");
		btnSqrt.setBackground(Color.LIGHT_GRAY);
		btnSqrt.setBounds(197, 21, 47, 36);
		frame.getContentPane().add(btnSqrt);
		
		JButton button_12 = new JButton(".");
		button_12.setBackground(Color.LIGHT_GRAY);
		button_12.setBounds(124, 183, 47, 23);
		buttons.add(button_12);
		frame.getContentPane().add(button_12);
		
		JButton button_13 = new JButton("=");
		button_13.setBackground(Color.LIGHT_GRAY);
		button_13.setBounds(104, 217, 67, 36);
		buttons.add(button_13);
		frame.getContentPane().add(button_13);
		
		label = new JLabel(text1);
		label.setBackground(Color.WHITE);
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setVerticalAlignment(SwingConstants.NORTH);
		label.setBounds(10, 24, 161, 23);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("0");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBackground(Color.WHITE);
		label_1.setOpaque(true);
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(10, 43, 161, 29);
		frame.getContentPane().add(label_1);
	}
}
