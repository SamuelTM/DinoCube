package br.stm.dinoCube.ui;

import br.stm.dinoCube.DinoCube;
import br.stm.dinoCube.FacePiece;

import java.awt.*;

public class CubeRenderer {
    
    private int drawingSize;
    private int startX, startY;
    private DinoCube dinoCube;

    public CubeRenderer(int drawingSize, int startX, int startY, DinoCube dinoCube) {
        this.drawingSize = drawingSize;
        this.startX = startX;
        this.startY = startY;
        this.dinoCube = dinoCube;
    }

    private void drawTextAtPolyCenter(int facePieceId, int[] xPoly, int[] yPoly, Graphics2D g2) {
        double x = 0, y = 0;
        int pointCount = xPoly.length;
        for (int i = 0; i < pointCount; i++) {
            x += xPoly[i];
            y += yPoly[i];
        }

        x /= pointCount;
        y /= pointCount;

        int offset = facePieceId < 10 ? drawingSize / 15 : drawingSize / 6;
        g2.drawString(String.valueOf(facePieceId), (int) x - offset, (int) y);
    }

    private void drawFaceFromCenterPoint(int x, int y, Graphics2D g2, FacePiece[] face) {

        g2.setStroke(new BasicStroke(2));
        g2.setFont(new Font("Arial", Font.PLAIN, drawingSize / 3));

        // Top piece
        g2.setColor(face[0].getPieceColor().getColor());

        int[] xPoly = {x, x - drawingSize, x + drawingSize};
        int[] yPoly = {y, y - drawingSize, y - drawingSize};

        g2.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        g2.setColor(Color.black);
        g2.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        drawTextAtPolyCenter(face[0].getId(), xPoly, yPoly, g2);

        // Right piece
        g2.setColor(face[1].getPieceColor().getColor());
        xPoly = new int[]{x, x + drawingSize, x + drawingSize};
        yPoly = new int[]{y, y - drawingSize, y + drawingSize};

        g2.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        g2.setColor(Color.black);
        g2.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        drawTextAtPolyCenter(face[1].getId(), xPoly, yPoly, g2);

        // Bottom piece
        g2.setColor(face[2].getPieceColor().getColor());
        xPoly = new int[]{x, x + drawingSize, x - drawingSize};
        yPoly = new int[]{y, y + drawingSize, y + drawingSize};

        g2.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        g2.setColor(Color.black);
        g2.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        drawTextAtPolyCenter(face[2].getId(), xPoly, yPoly, g2);

        // Left piece
        g2.setColor(face[3].getPieceColor().getColor());
        xPoly = new int[]{x, x - drawingSize, x - drawingSize};
        yPoly = new int[]{y, y - drawingSize, y + drawingSize};

        g2.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        g2.setColor(Color.black);
        g2.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));
        drawTextAtPolyCenter(face[3].getId(), xPoly, yPoly, g2);
    }
    
    public void drawCube(Graphics2D g2) {
        int[] xPoints = {startX, startX - startY, startX, startX + startY, startX, startX};
        int[] yPoints = {startY, startY * 2, startY * 2, startY * 2, startY * 3, startY * 4};

        for (int i = 0; i < dinoCube.getFaces().length; i++) {
            drawFaceFromCenterPoint(xPoints[i], yPoints[i], g2, dinoCube.getFaces()[i]);
        }
    }
}
