package br.stm.dinoCube;

import br.stm.dinoCube.ui.RenderWindow;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static class Node<T> {
        public T data;
        public Node<T> parent;
        public List<Node<T>> children;
    }

    private static final int[] POSSIBLE_MOVES = {1, -1, 2, -2};

    private static void permute(int desiredDepth, int currentDepth, DinoCube currentCube, List<DinoCube> cubeStates) {
        if (!cubeStates.contains(currentCube)) {
            System.out.println("Added cube " + currentCube + " at depth " + currentDepth);
            cubeStates.add(currentCube);
        }

        if (currentDepth < desiredDepth) {
            for (int move : POSSIBLE_MOVES) {
                System.out.println("\nDo move " + move + " from cube " + currentCube);
                DinoCube clone = currentCube.cloneCube();
                clone.rotateAxis(move);
                System.out.println("New cube now is " + clone);
                permute(desiredDepth, currentDepth + 1, clone, cubeStates);
            }
        }
    }

    public static void main(String[] args) {
//        new RenderWindow(new DinoCube());

        List<DinoCube> cubeStates = new ArrayList<>();
        permute(1, 0, new DinoCube(), cubeStates);

        System.out.println(cubeStates.size());
    }
}
