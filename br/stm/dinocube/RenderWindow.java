package br.stm.dinocube;

import javax.swing.*;
import java.awt.*;

public class RenderWindow extends JFrame {

    private DinoCube dinoCube;

    private final int DRAWING_SIZE = 60;

    public RenderWindow(DinoCube dinoCube) {
        this.dinoCube = dinoCube;

        setTitle("Dino Cube");

        setMinimumSize(new Dimension(500, 800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel content = new DrawingPanel();
        setContentPane(content);

        setVisible(true);
    }

    private class DrawingPanel extends JPanel {

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
        public void paintComponent(Graphics graphics) {
            super.paintComponents(graphics);
            Graphics2D g = (Graphics2D) graphics;

            int baseX = this.getWidth() / 2;
            int baseY = DRAWING_SIZE * 2;

            int[] xPoints = {baseX, baseX - baseY, baseX, baseX + baseY, baseX, baseX};
            int[] yPoints = {baseY, baseY * 2, baseY * 2, baseY * 2, baseY * 3, baseY * 4};

            for (int i = 0; i < dinoCube.getFaces().length; i++) {
                drawFaceFromCenterPoint(xPoints[i], yPoints[i], g, dinoCube.getFaces()[i]);
            }
        }
    }
}
