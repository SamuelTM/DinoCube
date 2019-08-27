package br.stm.dinoCube;

public class DinoCube {

    // Index count clockwise, top to bottom and left to right
    private FacePiece[][] faces;

    public DinoCube() {
        faces = new FacePiece[][]{
                // White
                new FacePiece[]{
                        new FacePiece(FaceColor.WHITE, new int[]{0, 0}, 0),
                        new FacePiece(FaceColor.WHITE, new int[]{0, 1}, 1),
                        new FacePiece(FaceColor.WHITE, new int[]{0, 2}, 2),
                        new FacePiece(FaceColor.WHITE, new int[]{0, 3}, 3),
                },
                // Orange, green, red
                new FacePiece[]{
                        new FacePiece(FaceColor.ORANGE, new int[]{1, 0}, 4),
                        new FacePiece(FaceColor.ORANGE, new int[]{1, 1}, 5),
                        new FacePiece(FaceColor.ORANGE, new int[]{1, 2}, 6),
                        new FacePiece(FaceColor.ORANGE, new int[]{1, 3}, 7),
                },
                new FacePiece[]{
                        new FacePiece(FaceColor.GREEN, new int[]{2, 0}, 8),
                        new FacePiece(FaceColor.GREEN, new int[]{2, 1}, 9),
                        new FacePiece(FaceColor.GREEN, new int[]{2, 2}, 10),
                        new FacePiece(FaceColor.GREEN, new int[]{2, 3}, 11),
                },
                new FacePiece[]{
                        new FacePiece(FaceColor.RED, new int[]{3, 0}, 12),
                        new FacePiece(FaceColor.RED, new int[]{3, 1}, 13),
                        new FacePiece(FaceColor.RED, new int[]{3, 2}, 14),
                        new FacePiece(FaceColor.RED, new int[]{3, 3}, 15),
                },

                // Yellow
                new FacePiece[]{
                        new FacePiece(FaceColor.YELLOW, new int[]{4, 0}, 16),
                        new FacePiece(FaceColor.YELLOW, new int[]{4, 1}, 17),
                        new FacePiece(FaceColor.YELLOW, new int[]{4, 2}, 18),
                        new FacePiece(FaceColor.YELLOW, new int[]{4, 3}, 19),
                },
                // Blue
                new FacePiece[]{
                        new FacePiece(FaceColor.BLUE, new int[]{5, 0}, 20),
                        new FacePiece(FaceColor.BLUE, new int[]{5, 1}, 21),
                        new FacePiece(FaceColor.BLUE, new int[]{5, 2}, 22),
                        new FacePiece(FaceColor.BLUE, new int[]{5, 3}, 23),
                },
        };
    }

    public FacePiece[][] getFaces() {
        return faces;
    }

    public void movePiece(int pieceNumber) {
        switch (pieceNumber) {
            case 1:
                FacePiece auxA = faces[0][0].clonePiece();
                FacePiece auxB = faces[0][3].clonePiece();

                faces[0][0] = faces[5][2];
                faces[0][3] = faces[5][3];

                faces[5][2] = faces[1][0];
                faces[5][3] = faces[1][3];

                faces[1][0] = auxA;
                faces[1][3] = auxB;

                break;

            case 2:
                auxB = faces[0][1].clonePiece();

                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                break;
        }
    }
}
