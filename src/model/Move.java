package model;

import model.enums.CellState;

public class Move {
    private final int row;
    private final int col;
    private final CellState mark;

    public Move(int row, int col, CellState mark){
        this.row = row;
        this.col = col;
        this.mark = mark;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public CellState getMark(){
        return mark;
    }
}
