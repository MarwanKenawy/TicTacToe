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


        Game game = new Game("Alice", "Bob");

        game.makeMove(0, 0);
        game.makeMove(0, 1);
        game.makeMove(0, 2);
        game.makeMove(1, 1);
        game.makeMove(1, 0);
        game.makeMove(1, 2);
        game.makeMove(2, 1);
        game.makeMove(2, 0);
        game.makeMove(2, 2);

        System.out.println(game.getStatus());
    }
    
}
