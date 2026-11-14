public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public Game(String player1Name, String player2Name){
        board = new Board();
        player1 = new Player(player1Name, CellState.X);
        player2 = new Player(player2Name, CellState.O);

        currentPlayer = player1;
    }

    public Board getBoard(){
        return board;
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    private void switchTurns(){
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }

    public void makeMove(int row, int col){
        if(row < 0 || row > 2 || col < 0 || col > 2){
            throw new IllegalArgumentException("Invalid position");
        }

        if(!board.isEmpty(row, col)){
            throw new IllegalArgumentException("Occupied position");
        }

        board.setCell(row, col, currentPlayer.getMark());
        switchTurns();
    }
}
