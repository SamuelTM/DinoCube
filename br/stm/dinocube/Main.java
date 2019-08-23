package br.stm.dinocube;

public class Main {

    public static void main(String[] args) {
        DinoCube cube = new DinoCube();
        cube.movePiece(1);
        RenderWindow window = new RenderWindow(cube);
    }
}
