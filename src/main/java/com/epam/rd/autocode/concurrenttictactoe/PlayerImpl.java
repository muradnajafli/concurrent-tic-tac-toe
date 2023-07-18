package com.epam.rd.autocode.concurrenttictactoe;

public class PlayerImpl implements Player {
    private static final char WHITE_SPACE = ' ';

    private final TicTacToe ticTacToe;
    private final char mark;
    private final PlayerStrategy strategy;

    public PlayerImpl(final TicTacToe ticTacToe, final char mark, final PlayerStrategy strategy) {
        this.ticTacToe = ticTacToe;
        this.mark = mark;
        this.strategy = strategy;
    }

    @Override
    public void run() {
        while (isNotGameEnded() && isNotFull()) {
            synchronized (ticTacToe) {
                if (isPossibleToMove()) {
                    Move move = strategy.computeMove(mark, ticTacToe);
                    ticTacToe.setMark(move.row, move.column, mark);
                }

            }
        }

    }


    private boolean isNotFull() {
        for (int i = 0; i < ticTacToe.table().length; i++) {
            for (int j = 0; j < ticTacToe.table().length; j++) {
                if (ticTacToe.table()[i][j] == WHITE_SPACE) {
                    return true;

                }
            }
        }
        return false;
    }

    private boolean isGameEnded() {
        if (isMainDiagonalDone() || isSideDiagonalDone()) {
            return true;
        }
        for (int i = 0; i < ticTacToe.table().length; i++) {
            if (isRowDone(i) || isColumnDone(i)) {
                return true;
            }
        }

        return false;

    }

    private boolean isNotGameEnded() {
        return !isGameEnded();
    }


    private boolean isPossibleToMove() {
        return isNotFull() && isNotGameEnded() && ticTacToe.lastMark() != mark;
    }

    private boolean isRowDone(int row) {
        char cellValue = ticTacToe.table()[row][0];
        for (int i = 0; i < ticTacToe.table()[row].length; i++) {
            if (ticTacToe.table()[row][i] != cellValue) {
                return false;

            }
            cellValue = ticTacToe.table()[row][i];
        }
        return cellValue != WHITE_SPACE;
    }

    private boolean isColumnDone(int column) {
        char cellValue = ticTacToe.table()[0][column];
        for (int i = 0; i < ticTacToe.table().length; i++) {
            if (ticTacToe.table()[i][column] != cellValue) {
                return false;
            }
            cellValue = ticTacToe.table()[i][column];
        }
        return cellValue != WHITE_SPACE;
    }

    private boolean isMainDiagonalDone() {
        char cellValue = ticTacToe.table()[0][0];
        for (int i = 0; i < ticTacToe.table().length; i++) {
            if (ticTacToe.table()[i][i] != cellValue) {
                return false;
            }
            cellValue = ticTacToe.table()[i][i];
        }
        return cellValue != WHITE_SPACE;
    }

    private boolean isSideDiagonalDone() {
        int lastRow = 2;
        char cellValue = ticTacToe.table()[lastRow][0];
        for (int i = 0; i < ticTacToe.table().length; i++) {
            if (ticTacToe.table()[lastRow-i][i] != cellValue) {
                return false;
            }
            cellValue = ticTacToe.table()[lastRow - i][i];
        }
        return cellValue != WHITE_SPACE;
    }
}



