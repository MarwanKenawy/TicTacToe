import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SmartStrategy implements MoveStrategy {
    private final Random random = new Random();

    @Override 
    public Position move(Board board, CellState mark){
        if(board.isFull()){
            throw new IllegalStateException("No empty cells");
        }

        Position winningPosition = findWinningMove(board, mark);
        if(winningPosition != null){
            return winningPosition;
        }

        CellState opponentMark = mark == CellState.X ? CellState.O : CellState.X;
        Position blockingPosition = findWinningMove(board, opponentMark);
        if(blockingPosition != null){
            return blockingPosition;
        }

        if (board.isEmpty(1, 1)) {
            return new Position(1, 1);
        }

        List<Position> emptyCorners = new ArrayList<>();

        if(board.isEmpty(0, 0))
            emptyCorners.add(new Position(0, 0));
        if(board.isEmpty(2, 0))
            emptyCorners.add(new Position(2, 0));
        if(board.isEmpty(0, 2))
            emptyCorners.add(new Position(0, 2));
        if(board.isEmpty(2, 2))
            emptyCorners.add(new Position(2, 2));
        
        if(!emptyCorners.isEmpty()){
            return emptyCorners.get(random.nextInt(emptyCorners.size()));
        }
        
        List<Position> emptyPositions = new ArrayList<>();
        
        if(board.isEmpty(0, 1))
            emptyPositions.add(new Position(0, 1));
        if(board.isEmpty(1, 0))
            emptyPositions.add(new Position(1, 0));
        if(board.isEmpty(1, 2))
            emptyPositions.add(new Position(1, 2));
        if(board.isEmpty(2, 1))
            emptyPositions.add(new Position(2, 1));

        return emptyPositions.get(random.nextInt(emptyPositions.size()));
    }

    private Position findWinningMove(Board board, CellState mark){
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(board.isEmpty(row, col)){
                    board.simulateMove(row, col, mark);
                    boolean winningMove = board.hasWinningLine(mark);
                    board.simulateMove(row, col, CellState.EMPTY);

                    if(winningMove){
                        return new Position(row, col);
                    }
                }
            }
        }
        return null;
    }
}
