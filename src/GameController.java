public class GameController {
    private final Game game;

    public GameController(Game game){
        this.game = game;
    }

    public void startGame(){
        game.start();
    }

    public void handleCellClick(int row, int col){
        game.playTurn(row, col);
    }

    public GameStatus getGameStatus(){
        return game.getStatus();
    }

    public CellState getCellState(int row, int col){
        return game.getBoard().getCell(row, col);
    }

    public void restartGame(){
        game.restart();
    }

    public CellState getCurrentPlayerMark(){
        return game.getCurrentPlayer().getMark();
    }

    public void undoMove(){
        game.undoTurn();
    }

    public void redoMove(){
        game.redoTurn();
    }

    public boolean isGameOver(){
        return game.getStatus() != GameStatus.IN_PROGRESS;
    }
}
