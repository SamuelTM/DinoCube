package br.stm.dinoCube;

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

        private void drawTextAtPolyCenter(int facePieceId, int[] xPoly, int[] yPoly, Graphics2D g) {
            double x = 0, y = 0;
            int pointCount = xPoly.length;
            for (int i = 0; i < pointCount; i++) {
                x += xPoly[i];
                y += yPoly[i];
            }

            x /= pointCount;
            y /= pointCount;

            int offset = facePieceId < 10 ? DRAWING_SIZE / 15 : DRAWING_SIZE / 6;
            g.drawString(String.valueOf(facePieceId), (int) x - offset, (int) y);
        }

        private void drawFaceFromCenterPoint(int x, int y, Graphics2D g, FacePiece[] face) {

            g.setStroke(new BasicStroke(2));
            g.setFont(new Font("Arial", Font.PLAIN, DRAWING_SIZE / 3));

            // Top piece
            g.setColor(face[0].getPieceColor().getColor());

            int[] xPoly = {x, x - DRAWING_SIZE, x + DRAWING_SIZE};
            int[] yPoly = {y, y - DRAWING_SIZE, y - DRAWING_SIZE};

            g.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
            g.setColor(Color.black);
            g.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
            drawTextAtPolyCenter(face[0].getId(), xPoly, yPoly, g);

            // Right piece
            g.setColor(face[1].getPieceColor().getColor());
            xPoly = new int[]{x, x + DRAWING_SIZE, x + DRAWING_SIZE};
            yPoly = new int[]{y, y - DRAWING_SIZE, y + DRAWING_SIZE};

            g.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
            g.setColor(Color.black);
            g.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
            drawTextAtPolyCenter(face[1].getId(), xPoly, yPoly, g);

            // Bottom piece
            g.setColor(face[2].getPieceColor().getColor());
            xPoly = new int[]{x, x + DRAWING_SIZE, x - DRAWING_SIZE};
            yPoly = new int[]{y, y + DRAWING_SIZE, y + DRAWING_SIZE};

            g.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
            g.setColor(Color.black);
            g.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
            drawTextAtPolyCenter(face[2].getId(), xPoly, yPoly, g);

            // Left piece
            g.setColor(face[3].getPieceColor().getColor());
            xPoly = new int[]{x, x - DRAWING_SIZE, x - DRAWING_SIZE};
            yPoly = new int[]{y, y - DRAWING_SIZE, y + DRAWING_SIZE};

            g.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
            g.setColor(Color.black);
            g.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
            drawTextAtPolyCenter(face[3].getId(), xPoly, yPoly, g);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);

            Graphics2D g2 = (Graphics2D) g.create();

            int baseX = this.getWidth() / 2;
            int baseY = DRAWING_SIZE * 2;

            int[] xPoints = {baseX, baseX - baseY, baseX, baseX + baseY, baseX, baseX};
            int[] yPoints = {baseY, baseY * 2, baseY * 2, baseY * 2, baseY * 3, baseY * 4};

            for (int i = 0; i < dinoCube.getFaces().length; i++) {
                drawFaceFromCenterPoint(xPoints[i], yPoints[i], g2, dinoCube.getFaces()[i]);
            }

            g2.dispose();
        }
    }
}
