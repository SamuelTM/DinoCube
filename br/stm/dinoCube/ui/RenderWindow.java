package br.stm.dinoCube.ui;

import br.stm.dinoCube.DinoCube;

import javax.swing.*;
import java.awt.*;

public class RenderWindow extends JFrame {

    private DinoCube dinoCube;
    private int pieceNumber;

    public RenderWindow(DinoCube dinoCube) {
        this.dinoCube = dinoCube;

        setTitle("Dino Cube");

        setMinimumSize(new Dimension(400, 700));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel pane = new JPanel();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        final JPanel drawingPanel = new DrawingPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        pane.add(drawingPanel, gbc);

        JComboBox<String> pieceList = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"});
        pieceList.addActionListener(e -> pieceNumber = pieceList.getSelectedIndex() + 1);
        pieceList.setSelectedIndex(0);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        gbc.weighty = 0;
        pane.add(pieceList, gbc);

        JButton turnCounterClockwise = new JButton("CCW");
        turnCounterClockwise.addActionListener(e -> {
            dinoCube.rotateAxis(pieceNumber * -1);
            pane.repaint();
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        gbc.weighty = 0;
        pane.add(turnCounterClockwise, gbc);

        JButton turnClockwise = new JButton("CW");
        turnClockwise.addActionListener(e -> {
            dinoCube.rotateAxis(pieceNumber);
            pane.repaint();
        });
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        gbc.weighty = 0;
        pane.add(turnClockwise, gbc);

        JButton resetCube = new JButton("Reset");
        resetCube.addActionListener(e -> {
            dinoCube.reset();
            pane.repaint();
        });
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        gbc.weighty = 0;
        pane.add(resetCube, gbc);

        setContentPane(pane);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class DrawingPanel extends JPanel {

        private static final int DRAWING_SIZE = 50;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);

            Graphics2D g2 = (Graphics2D) g.create();

            int startX = this.getWidth() / 2;
            int startY = DRAWING_SIZE * 2;

            CubeRenderer cr = new CubeRenderer(DRAWING_SIZE, startX, startY, dinoCube);

            cr.drawCube(g2);

            g2.dispose();
        }
    }
}
