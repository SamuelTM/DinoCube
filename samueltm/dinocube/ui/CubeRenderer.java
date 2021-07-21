package samueltm.dinocube.ui;

import samueltm.dinocube.DinoCube;
import samueltm.dinocube.FacePiece;

import java.awt.*;

public class CubeRenderer {

    private final int panelWidth;
    private final int panelHeight;
    private final DinoCube dinoCube;

    public CubeRenderer(int panelWidth, int panelHeight, DinoCube dinoCube) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.dinoCube = dinoCube;
    }

    private void drawTextAtPolyCenter(int facePieceId, int[] xPoly, int[] yPoly, Graphics2D g2, int squareSize) {
        double x = 0, y = 0;
        int pointCount = xPoly.length;
        for (int i = 0; i < pointCount; i++) {
            x += xPoly[i];
            y += yPoly[i];
        }

        x /= pointCount;
        y /= pointCount;

        int offset = facePieceId < 10 ? squareSize / 15 : squareSize / 6;
        g2.drawString(String.valueOf(facePieceId), (int) x - offset, (int) y);
    }

    private void drawFaceFromCenterPoint(int x, int y, Graphics2D g2, FacePiece[] face, int squareSize) {

        int halfSquare = squareSize / 2;

        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setFont(new Font("Arial", Font.PLAIN, halfSquare / 3));

        // Top piece
        g2.setColor(face[0].getPieceColor().getColor());

        int[] xPoly = {x, x - halfSquare, x + halfSquare};
        int[] yPoly = {y, y - halfSquare, y - halfSquare};

        g2.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        g2.setColor(Color.black);
        g2.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        drawTextAtPolyCenter(face[0].getId(), xPoly, yPoly, g2, halfSquare);

        // Right piece
        g2.setColor(face[1].getPieceColor().getColor());
        xPoly = new int[]{x, x + halfSquare, x + halfSquare};
        yPoly = new int[]{y, y - halfSquare, y + halfSquare};

        g2.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        g2.setColor(Color.black);
        g2.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        drawTextAtPolyCenter(face[1].getId(), xPoly, yPoly, g2, halfSquare);

        // Bottom piece
        g2.setColor(face[2].getPieceColor().getColor());
        xPoly = new int[]{x, x + halfSquare, x - halfSquare};
        yPoly = new int[]{y, y + halfSquare, y + halfSquare};

        g2.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        g2.setColor(Color.black);
        g2.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        drawTextAtPolyCenter(face[2].getId(), xPoly, yPoly, g2, halfSquare);

        // Left piece
        g2.setColor(face[3].getPieceColor().getColor());
        xPoly = new int[]{x, x - halfSquare, x - halfSquare};
        yPoly = new int[]{y, y - halfSquare, y + halfSquare};

        g2.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        g2.setColor(Color.black);
        g2.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        drawTextAtPolyCenter(face[3].getId(), xPoly, yPoly, g2, halfSquare);
    }

    public void drawCube(Graphics2D g2) {
        int startX = (int) (panelWidth * 0.5);
        int startY = (int) (panelHeight * 0.2);

        int squareSize = 0;

        while((3 * squareSize) < panelWidth && (4 * squareSize) < panelHeight * 0.8) {
            squareSize++;
        }

        int[] xPoints = {startX, startX - squareSize, startX, startX + squareSize, startX, startX};
        int[] yPoints = {startY, startY + squareSize, startY + squareSize, startY + squareSize,
                startY + (squareSize * 2), startY + (squareSize * 3)};

        for (int i = 0; i < dinoCube.getFaces().length; i++) {
            drawFaceFromCenterPoint(xPoints[i], yPoints[i], g2, dinoCube.getFaces()[i], squareSize);
        }
    }
}
