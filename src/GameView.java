import javax.swing.*;

import java.awt.*;

public class GameView extends JFrame {
    private final GameController controller;
    private final JButton[][] buttons;
    private JLabel statusLabel;

    public GameView(GameController controller){
        this.controller = controller;
        buttons = new JButton[3][3];

        setTitle("Tic Tac Toe");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 247, 250));

        statusLabel = new JLabel("Player X's turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 22));
        statusLabel.setForeground(new Color(44, 62, 80));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(statusLabel, BorderLayout.NORTH);
        
        initializeBoard();
        initializeControls();
        initializeHistoryControls();
        controller.startGame();
        refreshBoard();
        refreshStatus();

    }

    private void initializeBoard(){
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 8, 8));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        boardPanel.setBackground(new Color(245, 247, 250));

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                JButton button = new JButton("");
                button.setFont(new Font("Serif", Font.BOLD, 60));

                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
                button.setFocusPainted(false);
                button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                button.setOpaque(true);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));


                final int r = row;
                final int c = col;

                button.addActionListener(e -> {
                    if(!controller.isGameOver() && controller.getCellState(r, c) == CellState.EMPTY){
                        controller.handleCellClick(r, c);
                        refreshBoard();
                        refreshStatus();
                    }
                });

                buttons[row][col] = button;
                boardPanel.add(button);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    private void refreshBoard(){
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                CellState state = controller.getCellState(row, col);

                if(state == CellState.EMPTY){
                    buttons[row][col].setText("");
                    buttons[row][col].setForeground(Color.BLACK);
                }else if(state == CellState.X){
                    buttons[row][col].setText("X");
                    buttons[row][col].setForeground(new Color(41, 128, 185));
                }
                else{
                    buttons[row][col].setText("O");
                    buttons[row][col].setForeground(new Color(231, 76, 60));;
                }
            }
        }
    }

    private void refreshStatus(){
        GameStatus status = controller.getGameStatus();

        switch (status) {
            case X_WON:
                statusLabel.setText("Player X Wins!");
                break;
            case O_WON:
                statusLabel.setText("Player O Wins!");
                break;
            case DRAW:
                statusLabel.setText("It's a Draw!");
                break;
            case IN_PROGRESS:
                statusLabel.setText("Player " + controller.getCurrentPlayerMark() + "'s turn");
                break;
        }
    }

    private void initializeControls(){
        JButton restartButton = new JButton("Restart Game");
        restartButton.setFont(new Font("Arial", Font.BOLD, 18));
        restartButton.setBackground(new Color(52, 152, 219));
        restartButton.setForeground(Color.WHITE);
        restartButton.setFocusPainted(false);
        restartButton.setBorderPainted(false);
        restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        restartButton.setPreferredSize(new Dimension(160, 40));


        restartButton.addActionListener(e -> {
            controller.restartGame();
            refreshBoard();
            refreshStatus();
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(restartButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private void initializeHistoryControls(){
        JButton undoButton = new JButton("Undo");
        JButton redoButton = new JButton("Redo");

        undoButton.addActionListener(e -> {
            controller.undoMove();
            refreshBoard();
            refreshStatus();
        });

        redoButton.addActionListener(e -> {
            controller.redoMove();
            refreshBoard();
            refreshStatus();
        });

        JPanel historyPanel = new JPanel();
        historyPanel.add(undoButton);
        historyPanel.add(redoButton);

        add(historyPanel, BorderLayout.WEST);
    }
}
