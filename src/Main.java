public class Main {
    public static void main(String[] args){
        System.out.println("Tic Tac Toe Game");

        // Board board = new Board();

        // board.setCell(0, 0, CellState.X);
        // board.setCell(1, 1, CellState.O);

        // System.out.println(board.getCell(0, 0));
        // System.out.println(board.getCell(1, 1));
        // System.out.println(board.isEmpty(0, 1));

        // board.reset();

        // System.out.println(board.getCell(0, 0));

        // Game game = new Game("Alice", "Bob");

        // game.makeMove(0, 0);
        // System.out.println(game.getCurrentPlayer().getName());

        // game.makeMove(1, 1);
        // System.out.println(game.getCurrentPlayer().getName());

        // game.makeMove(0, 1);
        // System.out.println(game.getCurrentPlayer().getName());

        // try {
        //     game.makeMove(0, 0);
        // } catch (IllegalArgumentException e) {
        //     System.out.println(e.getMessage());
        // }

        // Game game = new Game("Alice", "Bob");

        // game.makeMove(0, 0);
        // game.makeMove(1, 0);
        // game.makeMove(0, 1);
        // game.makeMove(1, 1);
        // game.makeMove(0, 2);
        
        // System.out.println(game.getStatus());

        // Game game = new Game("Alice", "Bob");

        // game.makeMove(0, 0);
        // game.makeMove(1, 0);
        // game.makeMove(0, 1);
        // game.makeMove(1, 1);
        // game.makeMove(2, 2);
        // game.makeMove(1, 2);

        // System.out.println(game.getStatus());


        // Game game = new Game("Alice", "Bob");
        
        // game.makeMove(0, 0);
        // game.makeMove(0, 1);
        // game.makeMove(0, 2);
        // game.makeMove(1, 1);
        // game.makeMove(1, 0);
        // game.makeMove(1, 2);
        // game.makeMove(2, 1);
        // game.makeMove(2, 0);
        // game.makeMove(2, 2);
        
        // System.out.println(game.getStatus());

        // Board board = new Board();

        // place(board, 0, 0, CellState.X);
        // place(board, 0, 1, CellState.X);

        // DefensiveStrategy strategy = new DefensiveStrategy();
        // Position position = strategy.move(board, CellState.O);

        // System.out.println(position.getRow() + ", " + position.getCol());

        // Board board = new Board();

        // place(board, 0, 0, CellState.X);
        // place(board, 1, 0, CellState.X);

        // Position position = strategy.move(board, CellState.O);

        // System.out.println(position.getRow() + ", " + position.getCol());

        // Board board = new Board();

        // place(board, 0, 0, CellState.X);
        // place(board, 1, 1, CellState.X);

        // Position position = strategy.move(board, CellState.O);

        // System.out.println(position.getRow() + ", " + position.getCol());

        // Board board = new Board();

        // place(board, 0, 0, CellState.X);

        // Position position = strategy.move(board, CellState.O);

        // System.out.println(board.isEmpty(position.getRow(), position.getCol()));

        // Board board = new Board();

        // board.simulateMove(0, 0, CellState.O);
        // board.simulateMove(0, 1, CellState.O);

        // SmartStrategy strategy = new SmartStrategy();
        // Position p = strategy.move(board, CellState.O);

        // System.out.println(p.getRow() + ", " + p.getCol());

        // Board board = new Board();

        // board.simulateMove(0, 0, CellState.X);
        // board.simulateMove(0, 1, CellState.X);

        // Position p = strategy.move(board, CellState.O);

        // System.out.println(p.getRow() + ", " + p.getCol());

        // Board board = new Board();

        // board.simulateMove(0, 0, CellState.X);

        // Position p = strategy.move(board, CellState.O);

        // System.out.println(p.getRow() + ", " + p.getCol());

        // Board board = new Board();

        // board.simulateMove(1, 1, CellState.X);

        // Position p = strategy.move(board, CellState.O);

        // System.out.println(
        //     (p.getRow() == 0 || p.getRow() == 2) &&
        //     (p.getCol() == 0 || p.getCol() == 2)
        // );

        // Board board = new Board();

        // board.simulateMove(1, 1, CellState.X);
        // board.simulateMove(0, 0, CellState.X);
        // board.simulateMove(0, 2, CellState.O);
        // board.simulateMove(2, 0, CellState.O);
        // board.simulateMove(2, 2, CellState.X);

        // Position p = strategy.move(board, CellState.O);

        // System.out.println(board.isEmpty(p.getRow(), p.getCol()));
        
        Game game = new Game("Alice", "Bob", new SmartStrategy(), CellState.O);
        System.out.println(game.getCurrentPlayer());
        game.playTurn(0, 0);
        // game.makeComputerMove();
        game.playTurn(0, 1);
        // game.makeComputerMove();
        game.playTurn(2, 2);
        // game.makeComputerMove();

        // System.out.println(game.getBoard().isEmpty(0, 2));
        System.out.println(game.getBoard().getCell(0, 0));
        System.out.println(game.getBoard().getCell(1, 1));
        System.out.println(game.getStatus());
        game.restart();        
        System.out.println(game.getStatus());
        System.out.println(game.getBoard().getCell(0, 0));
        System.out.println(game.getBoard().getCell(1, 1));
        System.out.println(game.getCurrentPlayer());
    }

    private static void place(Board board, int row, int col, CellState mark) {
        board.simulateMove(row, col, mark);
    }
    
}
