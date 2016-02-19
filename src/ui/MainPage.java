package ui;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainPage {

	public static void main(String[] args) {
		JFrame window = new JFrame("24");
		JButton btn = new JButton("ºÙºÙ");
		btn.setSize(20, 20);
		window.add(btn);
		window.pack();
	}

}
