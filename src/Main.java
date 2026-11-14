public class Main {
    public static void main(String[] args){
        System.out.println("Tic Tac Toe Game");

        Board board = new Board();

        board.setCell(0, 0, CellState.X);
        board.setCell(1, 1, CellState.O);

        System.out.println(board.getCell(0, 0));
        System.out.println(board.getCell(1, 1));
        System.out.println(board.isEmpty(0, 1));

        board.reset();

        System.out.println(board.getCell(0, 0));
    }
    
}
