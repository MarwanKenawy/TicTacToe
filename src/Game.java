public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private GameStatus status;

    public Game(String player1Name, String player2Name){
        board = new Board();
        player1 = new Player(player1Name, CellState.X);
        player2 = new Player(player2Name, CellState.O);

        currentPlayer = player1;
        status = GameStatus.IN_PROGRESS;
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
        updateGameStatus();
        if(status == GameStatus.IN_PROGRESS){
            switchTurns();
        }
    }
}
