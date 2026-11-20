package model;

public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col){
        if(row < 0 || row > 2 || col < 0 || col > 2){
            throw new IllegalArgumentException("Invalid position");
        }
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }
}
