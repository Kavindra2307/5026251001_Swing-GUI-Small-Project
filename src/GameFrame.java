import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;
    private JButton btnBack;
    private boolean gameOver;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();
        this.gameOver = false;

        setTitle("Tic-Tac-Toe - Game");
        setSize(420, 520);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(GameFrame.this,
                    "Are you sure you want to quit the game?", "Quit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    MainMenuFrame menu = new MainMenuFrame(currentPlayer);
                    menu.setVisible(true);
                    dispose();
                }
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 50));

        JLabel lblTitle = new JLabel("TIC-TAC-TOE", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 200, 255));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 5, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JLabel lblPlayer = new JLabel("Player: " + currentPlayer.getUsername() + "  (X)", SwingConstants.CENTER);
        lblPlayer.setForeground(new Color(180, 255, 180));
        lblPlayer.setFont(new Font("Arial", Font.PLAIN, 13));

        lblStatus = new JLabel("Your turn! Click a cell.", SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 14));
        lblStatus.setForeground(Color.WHITE);

        JPanel topInfo = new JPanel(new GridLayout(2, 1));
        topInfo.setBackground(new Color(30, 30, 50));
        topInfo.add(lblPlayer);
        topInfo.add(lblStatus);
        topInfo.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 6, 6));
        boardPanel.setBackground(new Color(0, 150, 220));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(8, 30, 8, 30));

        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 42));
            buttons[i].setBackground(new Color(50, 50, 80));
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFocusPainted(false);
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(0, 150, 220), 2));
            final int index = i;
            buttons[i].addActionListener(e -> handlePlayerMove(index));
            boardPanel.add(buttons[i]);
        }

        JPanel centerWrap = new JPanel(new BorderLayout());
        centerWrap.setBackground(new Color(30, 30, 50));
        centerWrap.add(topInfo, BorderLayout.NORTH);
        centerWrap.add(boardPanel, BorderLayout.CENTER);

        mainPanel.add(centerWrap, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        bottomPanel.setBackground(new Color(30, 30, 50));

        JButton btnRestart = new JButton("New Game");
        btnRestart.setFont(new Font("Arial", Font.BOLD, 13));
        btnRestart.setBackground(new Color(0, 160, 100));
        btnRestart.setForeground(Color.WHITE);
        btnRestart.setFocusPainted(false);
        btnRestart.addActionListener(e -> resetGame());

        btnBack = new JButton("Main Menu");
        btnBack.setFont(new Font("Arial", Font.BOLD, 13));
        btnBack.setBackground(new Color(150, 80, 0));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(e -> {
            MainMenuFrame menu = new MainMenuFrame(currentPlayer);
            menu.setVisible(true);
            dispose();
        });

        bottomPanel.add(btnRestart);
        bottomPanel.add(btnBack);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void handlePlayerMove(int index) {
        if (gameOver) return;

        boolean moved = gameLogic.makeMove(index, 'X');
        if (!moved) {
            lblStatus.setText("Cell already taken! Choose another.");
            return;
        }

        buttons[index].setText("X");
        buttons[index].setForeground(new Color(0, 220, 255));
        buttons[index].setEnabled(false);

        if (gameLogic.checkWinner('X')) {
            finishGame("WIN");
            return;
        }
        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        lblStatus.setText("Computer is thinking...");
        disableAllButtons();

        Timer timer = new Timer(500, e -> {
            int compIndex = gameLogic.computerMove();
            if (compIndex != -1) {
                gameLogic.makeMove(compIndex, 'O');
                buttons[compIndex].setText("O");
                buttons[compIndex].setForeground(new Color(255, 100, 100));
                buttons[compIndex].setEnabled(false);
            }

            if (gameLogic.checkWinner('O')) {
                finishGame("LOSE");
            } else if (gameLogic.isDraw()) {
                finishGame("DRAW");
            } else {
                enableEmptyButtons();
                lblStatus.setText("Your turn! Click a cell.");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void finishGame(String result) {
        gameOver = true;
        disableAllButtons();

        playerService.updateStatistics(currentPlayer, result);

        String msg;
        if (result.equals("WIN")) {
            msg = "You WIN! +10 points";
            lblStatus.setText("You WIN!");
            lblStatus.setForeground(new Color(0, 255, 150));
        } else if (result.equals("LOSE")) {
            msg = "You LOSE! Computer wins. +0 points";
            lblStatus.setText("You LOSE!");
            lblStatus.setForeground(new Color(255, 80, 80));
        } else {
            msg = "It's a DRAW! +3 points";
            lblStatus.setText("DRAW!");
            lblStatus.setForeground(new Color(255, 200, 0));
        }

        JOptionPane.showMessageDialog(this, msg, "Game Over", JOptionPane.INFORMATION_MESSAGE);

        // Refresh currentPlayer stats
        Player updated = new PlayerService().getPlayerById(currentPlayer.getId());
        if (updated != null) currentPlayer = updated;
    }

    private void resetGame() {
        gameLogic.resetBoard();
        gameOver = false;
        lblStatus.setText("Your turn! Click a cell.");
        lblStatus.setForeground(Color.WHITE);
        for (JButton btn : buttons) {
            btn.setText("");
            btn.setEnabled(true);
            btn.setForeground(Color.WHITE);
        }
    }

    private void disableAllButtons() {
        for (JButton btn : buttons) btn.setEnabled(false);
    }

    private void enableEmptyButtons() {
        char[] board = gameLogic.getBoard();
        for (int i = 0; i < buttons.length; i++) {
            if (board[i] == ' ') buttons[i].setEnabled(true);
        }
    }
}
