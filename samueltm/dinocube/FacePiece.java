package samueltm.dinocube;

public class FacePiece {

    private final FaceColor color;
    private final int[] originalPlace;
    private final int id;

    FacePiece(FaceColor color, int[] originalPlace, int id) {
        this.color = color;
        this.originalPlace = originalPlace;
        this.id = id;
    }

    public FaceColor getPieceColor() {
        return color;
    }

    public int[] getOriginalPlace() {
        return originalPlace;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FacePiece) {
            FacePiece cp = (FacePiece) obj;
            return cp.id == id;
        }

        return false;
    }

    public FacePiece clonePiece() {
        return new FacePiece(color, originalPlace, id);
    }
}
