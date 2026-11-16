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
