package com.samueltm.dinocube.ui;

import com.samueltm.dinocube.DinoCube;

import javax.swing.*;
import java.awt.*;

public class RenderWindow extends JFrame {

    private final DinoCube dinoCube;
    private int pieceNumber;

    public RenderWindow(DinoCube dinoCube) {
        this.dinoCube = dinoCube;

        setTitle("Dino Cube");

        setMinimumSize(new Dimension(400, 700));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        double drawingPanelVerticalWeight = 0.95;
        double controlButtonsVerticalWeight = (1 - drawingPanelVerticalWeight) / 4;

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        final JPanel drawingPanel = new DrawingPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        gbc.weighty = drawingPanelVerticalWeight;
        contentPane.add(drawingPanel, gbc);

        JComboBox<String> pieceList = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
        pieceList.addActionListener(e -> pieceNumber = pieceList.getSelectedIndex() + 1);
        pieceList.setSelectedIndex(0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        gbc.weighty = controlButtonsVerticalWeight;
        contentPane.add(pieceList, gbc);

        JButton turnCounterClockwise = new JButton("CCW");
        turnCounterClockwise.addActionListener(e -> {
            dinoCube.rotateAxis(pieceNumber * -1);
            contentPane.repaint();
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        gbc.weighty = controlButtonsVerticalWeight;
        contentPane.add(turnCounterClockwise, gbc);

        JButton turnClockwise = new JButton("CW");
        turnClockwise.addActionListener(e -> {
            dinoCube.rotateAxis(pieceNumber);
            contentPane.repaint();
        });
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        gbc.weighty = controlButtonsVerticalWeight;
        contentPane.add(turnClockwise, gbc);

        JButton resetCube = new JButton("Reset");
        resetCube.addActionListener(e -> {
            dinoCube.reset();
            contentPane.repaint();
        });
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        gbc.weighty = controlButtonsVerticalWeight;
        contentPane.add(resetCube, gbc);

        setContentPane(contentPane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class DrawingPanel extends JPanel {

        public DrawingPanel() {
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            CubeRenderer cr = new CubeRenderer(getWidth(), getHeight(), dinoCube);

            cr.drawCube(g2);

            g2.dispose();
        }
    }
}
