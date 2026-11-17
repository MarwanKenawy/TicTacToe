import java.util.ArrayDeque;
import java.util.Deque;

public class Game implements BoardObserver {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameStatus status;
    private MoveStrategy computerStrategy;
    private CellState computerMark;
    private final Deque<Move> undoStack = new ArrayDeque<>();
    private final Deque<Move> redoStack = new ArrayDeque<>();

    public Game(String player1Name, String player2Name){
        board = new Board();
        player1 = new Player(player1Name, CellState.X);
        player2 = new Player(player2Name, CellState.O);

        currentPlayer = player1;
        status = GameStatus.IN_PROGRESS;
        board.addObserver(this);
    }

    public Game(String player1Name, String player2Name, MoveStrategy computerStrategy, CellState computerMark){
        this(player1Name, player2Name);

        if(computerStrategy != null && computerMark == CellState.EMPTY)
            throw new IllegalArgumentException("Invalid computer mark");

        this.computerStrategy = computerStrategy;
        this.computerMark = computerMark;
    }

    public Board getBoard(){
        return board;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public GameStatus getStatus(){
        return status;
    }

    public void updateGameStatus(){
        if(board.hasWinningLine(player1.getMark()))
            status = GameStatus.X_WON;
        else if(board.hasWinningLine(player2.getMark()))
            status = GameStatus.O_WON;
        else if(board.isFull())
            status = GameStatus.DRAW;
    }

    private void switchTurns(){
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public void makeMove(int row, int col){
        if(status != GameStatus.IN_PROGRESS){
            throw new IllegalStateException("Game Ended");
        }
        if(row < 0 || row > 2 || col < 0 || col > 2){
            throw new IllegalArgumentException("Invalid position");
        }

        if(!board.isEmpty(row, col)){
            throw new IllegalArgumentException("Occupied position");
        }

        board.setCell(row, col, currentPlayer.getMark());
        undoStack.push(new Move(row, col, currentPlayer.getMark()));
        redoStack.clear();
        // updateGameStatus();
        if(status == GameStatus.IN_PROGRESS){
            switchTurns();
        }
    }

    public void makeComputerMove(){
        if(computerStrategy == null)
            throw new IllegalStateException("This is a two-player game");
        if(status != GameStatus.IN_PROGRESS)
            throw new IllegalStateException("Game Ended");
        if(currentPlayer.getMark() != computerMark)
            throw new IllegalStateException("It is not the computer's turn");

        Position position = computerStrategy.move(board, computerMark);
        makeMove(position.getRow(), position.getCol());
    }

    public void playTurn(int row, int col){
        makeMove(row, col);

        // if(computerStrategy != null && status == GameStatus.IN_PROGRESS && currentPlayer.getMark() == computerMark){
        //     makeComputerMove();
        // }
    }

    public void start(){
        if(computerStrategy != null && currentPlayer.getMark() == computerMark){
            makeComputerMove();
        }
    }

    public void restart(){
        currentPlayer = player1;
        status = GameStatus.IN_PROGRESS;
        undoStack.clear();
        redoStack.clear();
        board.reset();
        start();
    }

    public void undo(){
        if(undoStack.isEmpty()){
            return;
        }
        Move move = undoStack.pop();

        board.simulateMove(move.getRow(), move.getCol(), CellState.EMPTY);
        redoStack.push(move);

        currentPlayer = move.getMark() == player1.getMark() ? player1 : player2;
        status = GameStatus.IN_PROGRESS;
        board.notifyObservers();
    }

    public void undoTurn(){
        if(computerStrategy == null){
            undo();
            return;
        }

        if(undoStack.isEmpty()){
            return;
        }

        Move lastMove = undoStack.peek();

        undo();

        if(!undoStack.isEmpty()){
            if(lastMove.getMark() == computerMark){
                if (undoStack.peek().getMark() != computerMark) {
                    undo();
                }else{
                    if(undoStack.peek().getMark() == computerMark){
                        undo();
                    }
                }
            }
        }

        if(computerStrategy != null && status == GameStatus.IN_PROGRESS && currentPlayer.getMark() == computerMark){
            makeComputerMove();
        }
    }

    public void redo(){
        if(redoStack.isEmpty()){
            return;
        }

        Move move = redoStack.pop();

        board.simulateMove(move.getRow(), move.getCol(), move.getMark());

        undoStack.push(move);

        currentPlayer = move.getMark() == player1.getMark() ? player2 : player1;

        updateGameStatus();
        board.notifyObservers();
    }

    public void redoTurn(){
        if(redoStack.isEmpty()){
            return;
        }

        redo();

        if(computerStrategy != null && status == GameStatus.IN_PROGRESS && currentPlayer.getMark() == computerMark){
            if(!redoStack.isEmpty() && redoStack.peek().getMark() == computerMark){
                redo();
            }else{
                makeComputerMove();
            }
        }
    }

    @Override 
    public void onBoardChanged(){
        updateGameStatus();
    }
}
