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
}
