import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            String[] modes = {"Two Players", "Play vs Computer"};

            int mode = JOptionPane.showOptionDialog(null, "Choose Game Mode", "Tic Tac Toe", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, modes, modes[0]);
            if(mode == -1){
                return;
            }

            Game game;

            if(mode == 0){
                game = new Game("Player X", "Player O");
            }else{
                String[] difficulties = {"Random", "Defensive", "Smart"};
                int difficulty = JOptionPane.showOptionDialog(null, "Choose Computer Difficulty", "Difficulty", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, difficulties, difficulties[2]);
                
                MoveStrategy strategy;

                switch (difficulty) {
                    case 0:
                        strategy = new RandomStrategy();
                        break;
                    case 1:
                        strategy = new DefensiveStrategy();
                        break;    
                    default:
                        strategy = new SmartStrategy();
                        break;
                }

                String[] marks = {"Computer plays X (First)", "Computer plays O (Second)"};

                int markChoice = JOptionPane.showOptionDialog(null, "Choose Computer's Mark", "Player Selection", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, marks, marks[1]);

                if(markChoice == -1){
                    return;
                }

                CellState computerMark = markChoice == 0 ? CellState.X : CellState.O;

                String player1Name = computerMark == CellState.X ? "Computer" : "Player X";
                String player2Name = computerMark == CellState.O ? "Computer" : "Player O";

                game = new Game(player1Name, player2Name, strategy, computerMark);
            }

            GameController controller = new GameController(game);
            GameView view = new GameView(controller);
            view.setVisible(true);

        });
    }
}
