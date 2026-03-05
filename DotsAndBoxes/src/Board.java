
public class Board {

    private int[][] hEdges;
    private int[][] vEdges;
    private int[][] boxes;
    private int size;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public Board(int size) {
        this.size = size;

        hEdges = new int[size + 1][size];
        vEdges = new int[size][size + 1];
        boxes = new int[size][size];
    }

    public void printBoard() {

        for (int r = 0; r < size; r++) {

            // print dot row
            for (int c = 0; c < size; c++) {
                System.out.print(".");
                if (hEdges[r][c] != 0) {
                    System.out.print(printRow(hEdges[r][c]));
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println(".");

            // print verticals + boxes
            for (int c = 0; c < size; c++) {

                if (vEdges[r][c] != 0) {
                    System.out.print(printCol(vEdges[r][c]));
                } else {
                    System.out.print(" ");
                }

                if (boxes[r][c] == 0) {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + boxes[r][c] + " ");
                }
            }

            if (vEdges[r][size] != 0) {
                System.out.println(printCol(vEdges[r][size]));
            } else {
                System.out.println(" ");
            }
        }

        // final bottom horizontal row
        for (int c = 0; c < size; c++) {
            System.out.print(".");
            if (hEdges[size][c] != 0) {
                System.out.print(printRow(hEdges[size][c]));
            } else {
                System.out.print("   ");
            }
        }
        System.out.println(".");
    }

    public boolean makeMove(String type, int r, int c, int player) {

        if (type.equals("h")) {

            if (r < 0 || r > size || c < 0 || c >= size) {
                return false;
            }

            if (hEdges[r][c] != 0) {
                return false;
            }

            hEdges[r][c] = player;

        } else if (type.equals("v")) {

            if (r < 0 || r >= size || c < 0 || c > size) {
                return false;
            }

            if (vEdges[r][c] != 0) {
                return false;
            }

            vEdges[r][c] = player;

        } else {
            return false;
        }

        return checkBoxes(player);
    }

    private boolean checkBoxes(int player) {

        boolean scored = false;

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {

                if (boxes[r][c] == 0
                        && hEdges[r][c] != 0
                        && hEdges[r + 1][c] != 0
                        && vEdges[r][c] != 0
                        && vEdges[r][c + 1] != 0) {

                    boxes[r][c] = player;
                    scored = true;
                }
            }
        }

        return scored;
    }

    public boolean isGameOver() {

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (boxes[r][c] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public String[] validMoves() {
        String[] moves = new String[(size + 1) * size * 2];
        int index = 0;

        for (int r = 0; r <= size; r++) {
            for (int c = 0; c < size; c++) {
                if (hEdges[r][c] == 0) {
                    moves[index++] = "h " + r + " " + c;
                }
            }
        }

        for (int r = 0; r < size; r++) {
            for (int c = 0; c <= size; c++) {
                if (vEdges[r][c] == 0) {
                    moves[index++] = "v " + r + " " + c;
                }
            }
        }

        return moves;
    }

    private String printRow(int player) {
        if (player == 1) {
            return ANSI_RED + "---" + ANSI_RESET;
        } else if (player == 2) {
            return ANSI_BLUE + "---" + ANSI_RESET;
        } else {
            return "---";
        }
    }

    private String printCol(int player) {
        if (player == 1) {
            return ANSI_RED + "|" + ANSI_RESET;
        } else if (player == 2) {
            return ANSI_BLUE + "|" + ANSI_RESET;
        } else {
            return "|";
        }
    }
}
