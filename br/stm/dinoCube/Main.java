package br.stm.dinoCube;

public class Main {

    private static void permute(int nLevels, int depth, DinoCube baseCube) {
        if (depth < nLevels) {
            System.out.println("---- Level " + depth + " ----");
            for (int i = 1; i < 9; i++) {
                baseCube.rotateAxis(i);
                System.out.println(baseCube);
                baseCube.rotateAxis(i);
                baseCube.rotateAxis(i);
                permute(nLevels, depth + 1, baseCube);
            }
        }
    }

    public static void main(String[] args) {
        DinoCube cube = new DinoCube();
        new RenderWindow(cube);
        //permute(1, 0, cube);
    }
}
