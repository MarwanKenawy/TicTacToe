import java.util.ArrayList;
import java.util.List;

public class Board {
    private final CellState[][] board;
    private final List<BoardObserver> observers = new ArrayList<>();

    public Board(){
        board = new CellState[3][3];
        initializeBoard();
    }

    public CellState getCell(int row, int col){
        return board[row][col];
    }

    public void simulateMove(int row, int col, CellState state){
        board[row][col] = state;
    }

    public void setCell(int row, int col, CellState state){
        board[row][col] = state;
        notifyObservers();
    }

    public boolean isEmpty(int row, int col){
        return board[row][col] == CellState.EMPTY;
    }

    public void reset(){
        initializeBoard();
        notifyObservers();
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

    public BoardSnapshot createSnapshot(){
        return new BoardSnapshot(board);
    }

    public void restoreSnapshot(BoardSnapshot snapshot){
        CellState[][] savedBoard = snapshot.getSavedBoard();

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                board[row][col] = savedBoard[row][col];
            }
        }

        notifyObservers();
    }

    public Position[] getWinningLine(CellState mark){
        for(int row = 0; row < 3; row++){
            if(board[row][0] == mark && board[row][1] == mark && board[row][2] == mark){
                return new Position[] {new Position(row, 0), new Position(row, 1), new Position(row, 2)};
            }
        }

        for(int col = 0; col < 3; col++){
            if(board[0][col] == mark && board[1][col] == mark && board[2][col] == mark){
                return new Position[] {new Position(0, col), new Position(1, col), new Position(2, col)};
            }
        }

        if(board[0][0] == mark && board[1][1] == mark && board[2][2] == mark){
            return new Position[] {new Position(0, 0), new Position(1, 1), new Position(2, 2)};
        }

        if(board[0][2] == mark && board[1][1] == mark && board[2][0] == mark){
            return new Position[] {new Position(0, 2), new Position(1, 1), new Position(2, 0)};
        }

        return null;
    }

    public void addObserver(BoardObserver observer){
        if(observer != null && !observers.contains(observer)){
            observers.add(observer);
        }
    }

    public void removeObserver(BoardObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for(BoardObserver observer : observers){
            observer.onBoardChanged();
        }
    }
}
