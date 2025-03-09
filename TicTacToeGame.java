package com.jbk;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToeGame implements ActionListener{


	
	    private JFrame frame;
	    private JButton[][] buttons = new JButton[3][3];
	    private boolean playerX = true; // true = X, false = O
	    private int moves = 0;

	    public TicTacToeGame() {
	        frame = new JFrame("Tic-Tac-Toe");
	        frame.setSize(400, 400);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(new GridLayout(3, 3));

	        // Create buttons for the 3x3 grid
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                buttons[i][j] = new JButton("");
	                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
	                buttons[i][j].setFocusPainted(false);
	                buttons[i][j].addActionListener(this);
	                frame.add(buttons[i][j]);
	            }
	        }

	        frame.setVisible(true);
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JButton clickedButton = (JButton) e.getSource();

	        // Set the text only if the button is empty
	        if (clickedButton.getText().equals("")) {
	            clickedButton.setText(playerX ? "X" : "O");
	            moves++;
	            if (checkWinner()) {
	                JOptionPane.showMessageDialog(frame, "Player " + (playerX ? "X" : "O") + " wins!");
	                resetGame();
	                return;
	            }
	            if (moves == 9) {
	                JOptionPane.showMessageDialog(frame, "It's a Draw!");
	                resetGame();
	                return;
	            }
	            playerX = !playerX; // Switch turn
	        }
	    }

	    private boolean checkWinner() {
	        // Check rows and columns
	        for (int i = 0; i < 3; i++) {
	            if (checkThree(buttons[i][0], buttons[i][1], buttons[i][2]) ||
	                checkThree(buttons[0][i], buttons[1][i], buttons[2][i])) {
	                return true;
	            }
	        }
	        // Check diagonals
	        return checkThree(buttons[0][0], buttons[1][1], buttons[2][2]) ||
	               checkThree(buttons[0][2], buttons[1][1], buttons[2][0]);
	    }

	    private boolean checkThree(JButton b1, JButton b2, JButton b3) {
	        return (!b1.getText().equals("") && 
	                b1.getText().equals(b2.getText()) && 
	                b2.getText().equals(b3.getText()));
	    }

	    private void resetGame() {
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                buttons[i][j].setText("");
	            }
	        }
	        playerX = true;
	        moves = 0;
	    }

	    public static void main(String[] args) {
	        new TicTacToeGame();
	    }
	}

