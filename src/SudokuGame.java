import java.util.Scanner;

public class SudokuGame {

    private SudokuBoard board;

    public SudokuGame() {
        board = new SudokuBoard();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao jogo de Sudoku!");
        System.out.println("Insira as jogadas no formato: linha coluna número");
        System.out.println("Linhas e colunas vão de 0 a 8; números de 1 a 9");

        while (true) {
            board.printBoard();

            if (board.isComplete()) {
                System.out.println("Parabéns! Você completou o Sudoku!");
                break;
            }

            System.out.print("Digite sua jogada (linha coluna número): ");
            int row, col, num;
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();
                num = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida! Por favor, digite três números separados por espaço.");
                scanner.nextLine(); // limpar buffer
                continue;
            }

            boolean success = board.insertNumber(row, col, num);
            if (!success) {
                System.out.println("Tente outra jogada.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new SudokuGame().start();
    }
}
