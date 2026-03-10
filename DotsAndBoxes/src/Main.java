import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Board board = new Board(5);
        GameEngine engine = new GameEngine(board);

        int currentPlayer = 1;

        while (!board.isGameOver()) {
            board.printBoard();

            System.out.println("Player " + currentPlayer + ", enter your move (type h/v row col) or press y to print valid moves and z to print best move:");
            String type = sc.next();
            if (type.equals("y")) {
                System.out.println(board.validMoves());
                continue;
            }
            if (type.equals("z")) {
                // bestMove expects (boxes, hEdges, vEdges)
                System.out.println(engine.bestMove(board.getBoxes(), board.getHEdges(), board.getVEdges()).toString());
                continue;
            }
            int r = sc.nextInt();
            int c = sc.nextInt();
            
            boolean scored = board.makeMove(type, r, c, currentPlayer);

            if (!scored) {
                currentPlayer = (currentPlayer == 1) ? 2 : 1;
            }
        }
        board.printBoard();
        System.out.println("Game Over!");
    }
}
