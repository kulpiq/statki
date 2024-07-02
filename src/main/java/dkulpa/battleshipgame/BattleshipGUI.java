package dkulpa.battleshipgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BattleshipGUI {
    public static final int SIZE = 5;
    public static final int SHIPS = 5;
    private char[][] ocean = new char[SIZE][SIZE];
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private int remainingShips = SHIPS;

    public BattleshipGUI() {
        initializeBoard();
        placeShips();
        createAndShowGUI();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                ocean[i][j] = '-';
            }
        }
    }

    private void placeShips() {
        Random rand = new Random();
        int placedShips = 0;
        while (placedShips < SHIPS) {
            int x = rand.nextInt(SIZE);
            int y = rand.nextInt(SIZE);
            if (ocean[x][y] == '-') {
                ocean[x][y] = 'S';
                placedShips++;
            }
        }
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(SIZE, SIZE));

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                frame.add(buttons[i][j]);
            }
        }

        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (ocean[row][col] == 'S') {
                buttons[row][col].setText("Hit");
                buttons[row][col].setBackground(Color.BLACK);
                remainingShips--;
                if (remainingShips == 0) {
                    JOptionPane.showMessageDialog(null, "Congratulations! You found all ships!");
                }
            } else {
                buttons[row][col].setBackground(Color.BLUE);
            }
            buttons[row][col].setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BattleshipGUI::new);
    }
}