package Chess;

import java.util.Scanner;

public class Play {
    public static void main(String[] args) {

        Board b = new Board();
        b.printBoard();
        Scanner input = new Scanner(System.in);
        int[] moves = new int[4];

        System.out.println("Enter the position from which you'll start moving(piece's position) and to which position will it move: ");
        while (Board.isGameGoing()) {
            System.out.print("Enter your move: ");
            try {
                moves[0] = input.nextInt();
                moves[1] = input.nextInt();
                moves[2] = input.nextInt();
                moves[3] = input.nextInt();
                b.play(moves[0], moves[1], moves[2], moves[3]);
            } catch (Exception e) {
                System.out.println("Wrong input, try again!");
            }
        }
    }
}
