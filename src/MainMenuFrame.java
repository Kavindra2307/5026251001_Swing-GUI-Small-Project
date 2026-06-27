import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;
        setTitle("Tic-Tac-Toe - Main Menu");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 50));

        JLabel lblTitle = new JLabel("MAIN MENU", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 200, 255));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JLabel lblWelcome = new JLabel("Hello, " + currentPlayer.getUsername() + "!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 14));
        lblWelcome.setForeground(new Color(180, 180, 255));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(30, 30, 50));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        centerPanel.add(lblWelcome);
        centerPanel.add(Box.createVerticalStrut(20));

        btnStartGame = createMenuButton("Start Game");
        btnStatistics = createMenuButton("My Statistics");
        btnTopScorers = createMenuButton("Top 5 Scorers");
        btnExit = createMenuButton("Exit");
        btnExit.setBackground(new Color(180, 50, 50));

        centerPanel.add(btnStartGame);
        centerPanel.add(Box.createVerticalStrut(12));
        centerPanel.add(btnStatistics);
        centerPanel.add(Box.createVerticalStrut(12));
        centerPanel.add(btnTopScorers);
        centerPanel.add(Box.createVerticalStrut(12));
        centerPanel.add(btnExit);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            this.dispose();
        });

        btnStatistics.addActionListener(e -> {
            StatisticsFrame statsFrame = new StatisticsFrame(currentPlayer);
            statsFrame.setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });

        btnExit.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private JButton createMenuButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(0, 120, 200));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setMaximumSize(new Dimension(220, 40));
        btn.setPreferredSize(new Dimension(220, 40));
        return btn;
    }
}
