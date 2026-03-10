public class GameEngine {

    private Board board;

    public GameEngine(Board board) {
        this.board = board;
    }

    public String bestMove(int[][] boxes, int[][] hEdges, int[][] vEdges) {
        
        // V1: JUST FIND A MOVE THAT CAN COMPLETE A BOX, IF THERE IS ONE, OTHERWISE RETURN NO BEST MOVE
        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c < board.getSize(); c++) {
                boolean almostDone = (hEdges[r][c] + hEdges[r+1][c] + vEdges[r][c] + vEdges[r][c+1] >= 3);

                // Give edge if completes box 
                if (boxes[r][c] == 0 && almostDone) {
                    if (hEdges[r][c] == 0) {
                        return ("h " + r + " " + c);
                    } else if (hEdges[r+1][c] == 0) {
                        return ("h " + (r+1) + " " + c);
                    } else if (vEdges[r][c] == 0) {
                        return ("v " + r + " " + c);
                    } else if (vEdges[r][c+1] == 0) {
                        return ("v " + r + " " + (c+1));
                    }
                }
            }
        }

        return "no best move! ask later after more moves have been played...";

        // V2: IF THERE IS NO MOVE THAT CAN COMPLETE A BOX, THEN FIND A MOVE THAT DOESN'T GIVE THE OPPONENT AN EASY BOX
    }
}
