package com.mooka.gamechallenge;

import com.mooka.gamechallenge.logic.CellContainer;
import com.mooka.gamechallenge.logic.GameGenerator;

import javax.swing.*;
import java.awt.*;

public class Main extends Canvas {
    private CellContainer head;
    private final int cellSize = 20;

    public void paint(Graphics graphics) {
        drawBranch(graphics, head);
    }

    private void drawBranch(Graphics graphics, CellContainer cellContainer) {
        graphics.setColor(cellContainer.getColor());
        int x = 400 + cellSize * cellContainer.getX();
        int y = 400 + cellSize * cellContainer.getY();
        graphics.fillRect(x, y, cellSize, cellSize);
        graphics.drawChars(String.valueOf(cellContainer.getGeneration()).toCharArray(), 0, 0, x, y);
        cellContainer.getChildren().forEach(h -> drawBranch(graphics, h));
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.generateGame();
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Here we add it to the frame
        frame.getContentPane().add(main);
        frame.setVisible(true);
    }

    private void generateGame() throws Exception {
        GameGenerator gameGenerator = new GameGenerator(235234242341L);
        head = gameGenerator.generate();
    }
}
