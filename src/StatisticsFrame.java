import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;

    public StatisticsFrame(Player player) {
        this.playerService = new PlayerService();
        // Always refresh from DB to get latest stats
        Player fresh = playerService.getPlayerById(player.getId());
        this.currentPlayer = (fresh != null) ? fresh : player;

        setTitle("My Statistics - " + currentPlayer.getUsername());
        setSize(340, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 50));

        JLabel lblTitle = new JLabel("MY STATISTICS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 200, 255));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(18, 0, 10, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridBagLayout());
        statsPanel.setBackground(new Color(40, 40, 65));
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 30, 10, 30),
            BorderFactory.createLineBorder(new Color(0, 150, 220), 1)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addStatRow(statsPanel, gbc, 0, "Username:", currentPlayer.getUsername(), new Color(180, 255, 180));
        addStatRow(statsPanel, gbc, 1, "Wins:", String.valueOf(currentPlayer.getWins()), new Color(0, 220, 130));
        addStatRow(statsPanel, gbc, 2, "Losses:", String.valueOf(currentPlayer.getLosses()), new Color(255, 100, 100));
        addStatRow(statsPanel, gbc, 3, "Draws:", String.valueOf(currentPlayer.getDraws()), new Color(255, 200, 0));
        addStatRow(statsPanel, gbc, 4, "Total Score:", String.valueOf(currentPlayer.getScore()), new Color(0, 200, 255));

        mainPanel.add(statsPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(30, 30, 50));
        JButton btnClose = new JButton("Close");
        btnClose.setFont(new Font("Arial", Font.BOLD, 13));
        btnClose.setBackground(new Color(0, 120, 200));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.setPreferredSize(new Dimension(100, 35));
        btnClose.addActionListener(e -> dispose());
        btnPanel.add(btnClose);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addStatRow(JPanel panel, GridBagConstraints gbc, int row, String label, String value, Color valueColor) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl.setForeground(Color.LIGHT_GRAY);
        gbc.gridx = 0; gbc.gridy = row;
        panel.add(lbl, gbc);

        JLabel val = new JLabel(value);
        val.setFont(new Font("Arial", Font.BOLD, 14));
        val.setForeground(valueColor);
        gbc.gridx = 1; gbc.gridy = row;
        panel.add(val, gbc);
    }
}
