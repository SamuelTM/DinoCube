package com.samueltm.dinocube;

public class DinoCube {

    // Index count clockwise, top to bottom and left to right
    private FacePiece[][] faces;

    public DinoCube() {
        faces = getDefaultState();
    }

    public FacePiece[][] getFaces() {
        return faces;
    }

    public void rotateAxis(int axis) {
        switch (axis) {
            case -1:
                FacePiece auxA = faces[0][0].clonePiece();

                faces[0][0] = faces[1][0];
                faces[1][0] = faces[5][3];
                faces[5][3] = auxA;

                FacePiece auxB = faces[0][3].clonePiece();

                faces[0][3] = faces[1][3];
                faces[1][3] = faces[5][2];
                faces[5][2] = auxB;

                break;

            case -2:

                auxA = faces[0][0].clonePiece();

                faces[0][0] = faces[5][1];
                faces[5][1] = faces[3][0];
                faces[3][0] = auxA;

                auxB = faces[0][1].clonePiece();

                faces[0][1] = faces[5][2];
                faces[5][2] = faces[3][1];
                faces[3][1] = auxB;

                break;
            case -3:

                auxA = faces[0][1].clonePiece();

                faces[0][1] = faces[3][3];
                faces[3][3] = faces[2][0];
                faces[2][0] = auxA;

                auxB = faces[0][2].clonePiece();

                faces[0][2] = faces[3][0];
                faces[3][0] = faces[2][1];
                faces[2][1] = auxB;

                break;
            case -4:

                auxA = faces[0][2].clonePiece();

                faces[0][2] = faces[2][3];
                faces[2][3] = faces[1][0];
                faces[1][0] = auxA;

                auxB = faces[0][3].clonePiece();

                faces[0][3] = faces[2][0];
                faces[2][0] = faces[1][1];
                faces[1][1] = auxB;

                break;
            case -5:

                auxA = faces[1][2].clonePiece();

                faces[1][2] = faces[4][2];
                faces[4][2] = faces[5][3];
                faces[5][3] = auxA;

                auxB = faces[1][3].clonePiece();

                faces[1][3] = faces[4][3];
                faces[4][3] = faces[5][0];
                faces[5][0] = auxB;

                break;
            case -6:

                auxA = faces[3][1].clonePiece();

                faces[3][1] = faces[5][0];
                faces[5][0] = faces[4][1];
                faces[4][1] = auxA;

                auxB = faces[3][2].clonePiece();

                faces[3][2] = faces[5][1];
                faces[5][1] = faces[4][2];
                faces[4][2] = auxB;

                break;
            case -7:

                auxA = faces[2][1].clonePiece();

                faces[2][1] = faces[3][2];
                faces[3][2] = faces[4][0];
                faces[4][0] = auxA;

                auxB = faces[2][2].clonePiece();

                faces[2][2] = faces[3][3];
                faces[3][3] = faces[4][1];
                faces[4][1] = auxB;

                break;
            case -8:

                auxA = faces[2][2].clonePiece();

                faces[2][2] = faces[4][3];
                faces[4][3] = faces[1][1];
                faces[1][1] = auxA;

                auxB = faces[2][3].clonePiece();

                faces[2][3] = faces[4][0];
                faces[4][0] = faces[1][2];
                faces[1][2] = auxB;

                break;
            // Default for any positive number, which means it's just
            // clockwise motion, so we rotate the axis twice
            default:
                if (axis >= 1 && axis <= 8) {
                    for (int i = 0; i < 2; i++)
                        rotateAxis(axis * -1);
                }
                break;
        }
    }

    public FacePiece[][] getDefaultState() {
        return new FacePiece[][]{
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

    public void reset() {
        faces = getDefaultState();
    }

    public DinoCube cloneCube() {
        final DinoCube clone = new DinoCube();
        for (int i = 0; i < faces.length; i++) {
            for (int j = 0; j < faces[i].length; j++) {
                clone.getFaces()[i][j] = faces[i][j].clonePiece();
            }
        }

        return clone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < faces.length; i++) {
            FacePiece[] face = faces[i];
            for (int j = 0; j < face.length; j++) {
                FacePiece piece = face[j];
                sb.append(piece.getId());
                if (!(i == faces.length - 1 && j == face.length - 1))
                    sb.append(",");
            }
        }

        return sb.append("]").toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DinoCube) {
            DinoCube dc = (DinoCube) obj;
            for (int i = 0; i < dc.getFaces().length; i++) {
                FacePiece[] currentFace = dc.getFaces()[i];
                for (int j = 0; j < currentFace.length; j++) {
                    FacePiece currentPiece = currentFace[j];
                    if (!currentPiece.equals(faces[i][j])) {
                        return false;
                    }
                }
            }

            return true;
        }

        return false;
    }
}
