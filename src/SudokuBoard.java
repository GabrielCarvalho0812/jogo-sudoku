public class SudokuBoard {

    private int[][] board;
    private final int SIZE = 9;

    // Tabuleiro inicial com alguns números pré-definidos
    private int[][] preset = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    public SudokuBoard() {
        board = new int[SIZE][SIZE];
        // Copiar o preset para o board
        for (int i = 0; i < SIZE; i++) {
            System.arraycopy(preset[i], 0, board[i], 0, SIZE);
        }
    }

    // Imprime o tabuleiro no terminal
    public void printBoard() {
        System.out.println("  0 1 2   3 4 5   6 7 8"); // cabeçalho colunas
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("------+-------+------");
            }
            System.out.print(i + " "); // número da linha
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                if (board[i][j] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Verifica se a jogada é válida no tabuleiro
    public boolean isValid(int row, int col, int num) {
        // Checar linha
        for (int j = 0; j < SIZE; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }

        // Checar coluna
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Checar subgrade 3x3
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;

        for (int i = boxRowStart; i < boxRowStart + 3; i++) {
            for (int j = boxColStart; j < boxColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Insere número no tabuleiro se a posição estiver vazia e o número for válido
    public boolean insertNumber(int row, int col, int num) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || num < 1 || num > 9) {
            System.out.println("Coordenadas ou número fora do intervalo válido.");
            return false;
        }

        if (board[row][col] != 0) {
            System.out.println("Essa posição já está preenchida!");
            return false;
        }

        if (!isValid(row, col, num)) {
            System.out.println("Número inválido para essa posição!");
            return false;
        }

        board[row][col] = num;
        return true;
    }

    // Verifica se o tabuleiro está completo e correto
    public boolean isComplete() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int val = board[i][j];
                if (val == 0) {
                    return false;
                }
                // Temporariamente zera a posição para validar
                board[i][j] = 0;
                if (!isValid(i, j, val)) {
                    board[i][j] = val; // volta ao original
                    return false;
                }
                board[i][j] = val;
            }
        }
        return true;
    }
}
