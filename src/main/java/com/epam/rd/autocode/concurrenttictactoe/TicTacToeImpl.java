package com.epam.rd.autocode.concurrenttictactoe;

public class TicTacToeImpl implements TicTacToe{
    private char lastMark = 'O';
    private char[][] board = new char[3][3];
    public TicTacToeImpl() {
        newBoard();
    }

    private void newBoard() {
        board = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '},
        };
    }

    @Override
    public synchronized void setMark(int x, int y, char mark) {
        if (board[x][y] == ' ') {
            board[x][y] = mark;
            lastMark = mark;
        } else {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public char[][] table() {
        char[][] newTable = new char[3][3];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    newTable[i][j] = ' ';
                } else {
                    newTable[i][j] = board[i][j];
                }
            }
        }

        return newTable;
    }

    @Override
    public char lastMark() {
        return lastMark;
    }

}
