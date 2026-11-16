public class Game implements BoardObserver {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameStatus status;
    private MoveStrategy computerStrategy;
    private CellState computerMark;

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

        if(computerStrategy != null && status == GameStatus.IN_PROGRESS && currentPlayer.getMark() == computerMark){
            makeComputerMove();
        }
    }

    @Override 
    public void onBoardChanged(){
        updateGameStatus();
    }
}
