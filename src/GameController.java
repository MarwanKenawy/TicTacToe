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

    public void makeComputerMove(){
        game.makeComputerMove();
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

    public String getCurrentPlayerName(){
        return game.getCurrentPlayerName();
    }

    public boolean isSinglePlayer(){
        return game.isSinglePlayer();
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

    public void saveSnapshot(){
        game.saveSnapshot();
    }

    public boolean isComputerTurn(){
        return game.isComputerTurn();
    }

    public void restoreSnapshot(){
        game.restoreSnapshot();
    }

    public Position[] getWinningLine(){
        GameStatus status = game.getStatus();

        if(status == GameStatus.X_WON){
            return game.getBoard().getWinningLine(CellState.X);
        }else if(status == GameStatus.O_WON){
            return game.getBoard().getWinningLine(CellState.O);
        }

        return null;
    }
}
