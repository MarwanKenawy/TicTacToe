public class BoardSnapshot {
    private final CellState[][] savedBoard;

    public BoardSnapshot(CellState[][] board){
        savedBoard = new CellState[3][3];

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                savedBoard[row][col] = board[row][col];
            }
        }
    }

    public CellState[][] getSavedBoard(){
        CellState[][] copy = new CellState[3][3];

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                copy[row][col] = savedBoard[row][col];
            }
        }
        return copy;
    }
}
