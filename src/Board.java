public class Board {
    private final CellState[][] board;

    public Board(){
        board = new CellState[3][3];
        initializeBoard();
    }

    public CellState getCell(int row, int col){
        return board[row][col];
    }

    public void setCell(int row, int col, CellState state){
        board[row][col] = state;
    }

    public boolean isEmpty(int row, int col){
        return board[row][col] == CellState.EMPTY;
    }

    public void reset(){
        initializeBoard();
    }

    private void initializeBoard(){
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                board[row][col] = CellState.EMPTY;
            }
        }
    }

    public boolean hasWinningLine(CellState mark){
        for(int i = 0; i < 3; i++){
            if((board[i][0] == board[i][1]) && (board[i][0] == board[i][2]) && (board[i][0] == mark)){
                return true;
            }
            if((board[0][i] == board[1][i]) && (board[0][i] == board[2][i]) && (board[0][i] == mark)){
                return true;
            }
        }
        if((board[0][0] == board[1][1]) && (board[0][0] == board[2][2]) && (board[0][0] == mark))
            return true;
        
        if((board[0][2] == board[1][1]) && (board[0][2] == board[2][0]) && (board[0][2] == mark))
            return true;

        return false;


    }

    public boolean isFull(){
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(isEmpty(row, col)){
                    return false;
                }
            }
        }
        return true;
    }
}
