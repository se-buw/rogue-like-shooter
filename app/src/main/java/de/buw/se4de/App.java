/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package de.buw.se4de;

import java.awt.*;

import javax.swing.*;

public class App {
	public String displayWindow() {
		JButton b=new JButton("Click Here");
		b.setBounds(50,100,95,30);

		JFrame frame = new JFrame("FrameDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(b, BorderLayout.CENTER);
		frame.pack();
		frame.setSize(400,400);
		frame.setLayout(null);
		frame.setVisible(true);

		return null;
	}

	public static void main(String[] args) {
		System.out.println(new App().displayWindow());
	}
}
