import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DefensiveStrategy implements MoveStrategy {

    private final Random random = new Random();

    @Override 
    public Position move(Board board, CellState mark){

        if(board.isFull()){
            throw new IllegalStateException("No empty cells");
        }

        List<Position> availablePositions = new ArrayList<>();
        CellState opponentMark = mark == CellState.X ? CellState.O : CellState.X; 

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(board.isEmpty(row, col)){
                    board.simulateMove(row, col, opponentMark);
                    boolean winningMove = board.hasWinningLine(opponentMark);
                    board.simulateMove(row, col, CellState.EMPTY);

                    if(winningMove){
                        return new Position(row, col);
                    }
                    availablePositions.add(new Position(row, col));
                }
            }
        }

        return availablePositions.get(random.nextInt(availablePositions.size()));
    }
}
