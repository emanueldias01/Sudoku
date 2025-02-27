package br.code.dio.service;



import br.code.dio.model.Board;
import br.code.dio.model.GameStatusEnum;
import br.code.dio.model.Space;

import java.util.*;

public class BoardService {
    private final static int BOARD_LIMIT = 9;
    private final Board board;

    public List<List<Space>> getSpaces(){
        return board.getSpaces();
    }

    public void reset(){
        board.reset();
    }

    public boolean hasErrors(){
        return board.hasErrors();
    }

    public GameStatusEnum getStatus(){
        return board.getStatus();
    }

    public boolean gameIsFinished(){
        return board.gameIsFinished();
    }

    public BoardService() {
        this.board = new Board(initBoard());
    }

    private List<List<Space>> initBoard() {
        List<List<Space>> board = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            List<Space> row = new ArrayList<>();
            for (int j = 0; j < BOARD_LIMIT; j++) {
                row.add(new Space(0, false)); // Inicializa os espaços sem números fixos
            }
            board.add(row);
        }
        generateValidBoard(board);
        return board;
    }

    private void generateValidBoard(List<List<Space>> board) {
        Random random = new Random();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (int j = 0; j < BOARD_LIMIT; j++) {
                if (random.nextDouble() < 0.4) { // Define uma densidade aleatória inicial
                    int num;
                    do {
                        num = random.nextInt(9) + 1;
                    } while (!isValid(board, i, j, num));
                    board.get(i).set(j, new Space(num, true)); // Número fixo
                }
            }
        }
    }

    private boolean isValid(List<List<Space>> board, int row, int col, int num) {
        for (int i = 0; i < BOARD_LIMIT; i++) {
            if (board.get(row).get(i).getActual() != null && board.get(row).get(i).getActual() == num) {
                return false;
            }
            if (board.get(i).get(col).getActual() != null && board.get(i).get(col).getActual() == num) {
                return false;
            }
        }
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(boxRow + i).get(boxCol + j).getActual() != null && board.get(boxRow + i).get(boxCol + j).getActual() == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
