import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStrategy implements MoveStrategy {

    private final Random random = new Random();

    @Override 
    public Position move(Board board, CellState mark){
        if(board.isFull()){
            throw new IllegalStateException("No empty cells");
        }

        List<Position> availablePositions = new ArrayList<>();

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(board.isEmpty(row, col)){
                    availablePositions.add(new Position(row, col));
                }
            }
        }

        return availablePositions.get(random.nextInt(availablePositions.size()));
    }
}
