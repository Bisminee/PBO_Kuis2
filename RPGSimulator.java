import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class RPGSimulator extends JFrame {

    private JTextField txtHeroName, txtHeroLevel;
    private JTextField txtSkillName, txtSkillMultiplier;

    private JTextField txtEnemyName, txtEnemyHP, txtEnemyAP;

    private JTextArea txtOutput;

    private Hero hero;
    private Enemy enemy;
    private Skill skill;

    public RPGSimulator() {
        setTitle("RPG Simulator - Hero vs Enemy Edition");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        hero = new Hero("Denji", 10, 80, 20);
        enemy = new Enemy("Makima", 150, 23);
        skill = new Skill("Transform", 1.0);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelEditHero = createEditHeroPanel();
        tabbedPane.addTab("Edit Hero", panelEditHero);

        JPanel panelEditEnemy = createEditEnemyPanel();
        tabbedPane.addTab("Edit Enemy", panelEditEnemy);

        add(tabbedPane, BorderLayout.CENTER);

        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createEditHeroPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel statusPanel = new JPanel(new GridBagLayout());
        statusPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Hero Status",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        statusPanel.add(new JLabel("Nama Hero"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtHeroName = new JTextField(hero.getName());
        statusPanel.add(txtHeroName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        statusPanel.add(new JLabel("Level"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtHeroLevel = new JTextField(String.valueOf(hero.getLevel()));
        statusPanel.add(txtHeroLevel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        JButton btnSave = new JButton("Save Hero");
        btnSave.setPreferredSize(new Dimension(120, 30));
        btnSave.addActionListener(e -> actionSaveHero());
        statusPanel.add(btnSave, gbc);

        JPanel skillPanel = new JPanel(new GridBagLayout());
        skillPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Edit Skill Hero",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12)));

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        skillPanel.add(new JLabel("Nama Skill"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtSkillName = new JTextField(skill.getSkillName());
        skillPanel.add(txtSkillName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        skillPanel.add(new JLabel("Multiplier"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        txtSkillMultiplier = new JTextField("1.5"); // default input gaya persen
        skillPanel.add(txtSkillMultiplier, gbc);

        JPanel btnGroupPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        btnAdd.setPreferredSize(new Dimension(80, 30));
        btnDelete.setPreferredSize(new Dimension(80, 30));

        btnAdd.addActionListener(e -> actionAddSkill());
        btnDelete.addActionListener(e -> actionDeleteSkill());

        btnGroupPanel.add(btnAdd);
        btnGroupPanel.add(btnDelete);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        skillPanel.add(btnGroupPanel, gbc);

        mainPanel.add(statusPanel);
        mainPanel.add(skillPanel);

        return mainPanel;
    }

    private JPanel createEditEnemyPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel statusPanel = new JPanel(new GridBagLayout());
        statusPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Enemy Status (Boss)",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        statusPanel.add(new JLabel("Nama Enemy:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        txtEnemyName = new JTextField(enemy.getName());
        statusPanel.add(txtEnemyName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        statusPanel.add(new JLabel("Max HP:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        txtEnemyHP = new JTextField(String.valueOf(enemy.getMaxHP()));
        statusPanel.add(txtEnemyHP, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        statusPanel.add(new JLabel("Attack Power (AP):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        txtEnemyAP = new JTextField(String.valueOf(enemy.getAttackPower()));
        statusPanel.add(txtEnemyAP, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton btnSaveEnemy = new JButton("Save Enemy Stats");
        btnSaveEnemy.setPreferredSize(new Dimension(150, 35));
        btnSaveEnemy.addActionListener(e -> actionSaveEnemy());
        statusPanel.add(btnSaveEnemy, gbc);

        GridBagConstraints mainGbc = new GridBagConstraints();
        mainGbc.gridx = 0;
        mainGbc.gridy = 0;
        mainGbc.weightx = 1.0;
        mainGbc.weighty = 1.0;
        mainGbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(statusPanel, mainGbc);

        return mainPanel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel btnContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnSimulasi = new JButton("Jalankan Simulasi!");
        btnSimulasi.setFont(new Font("Arial", Font.BOLD, 14));
        btnSimulasi.setPreferredSize(new Dimension(200, 40));
        btnSimulasi.addActionListener(e -> actionRunSimulation());
        btnContainer.add(btnSimulasi);

        txtOutput = new JTextArea(15, 50);
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output Log"));

        panel.add(btnContainer, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void actionSaveHero() {
        hero.setName(txtHeroName.getText());
        hero.setLevel(Integer.parseInt(txtHeroLevel.getText()));
        skill = new Skill(txtSkillName.getText(),
                Double.parseDouble(txtSkillMultiplier.getText()));

        logSeparator();
        logToOutput("Hero berhasil diganti!");
        logToOutput("Hero sekarang bernama " + hero.getName() + " dengan Level " + hero.getLevel());
        logSeparator();
    }

    private void actionSaveEnemy() {
        enemy.setName(txtEnemyName.getText());
        enemy.setCurrentHP(Integer.parseInt(txtEnemyHP.getText()));
        enemy.setAttackPower(Integer.parseInt(txtEnemyAP.getText()));

        logSeparator();
        logToOutput("> Enemy saved: " + enemy.getName() + " | HP: " + enemy.getMaxHP() + " | AP: "
                + enemy.getAttackPower());
        logSeparator();
    }

    private void actionAddSkill() {
        String skillName = txtSkillName.getText().trim();
        double skillMultiplier = parseMultiplier(txtSkillMultiplier.getText().trim());

        logSeparator();
        logToOutput("Skill dengan nama " + skillName + " dengan "
                + formatMultiplierInput(txtSkillMultiplier.getText().trim())
                + " multiplier berhasil ditambahkan!");
        logSeparator();
    }

    private void actionDeleteSkill() {
        String skillName = txtSkillName.getText().trim();
        double skillMultiplier = parseMultiplier(txtSkillMultiplier.getText().trim());

        logSeparator();
        logToOutput("Skill dengan nama " + skillName + " dengan "
                + formatMultiplierInput(txtSkillMultiplier.getText().trim())
                + " multiplier berhasil dihapus!");
        logSeparator();
    }

    private void actionRunSimulation() {
        logSeparator();
        logToOutput("Simulasi dimulai!");

        String log = BattleSimulator.runBattle(hero, enemy, skill);
        txtOutput.append(log);

        logToOutput(log + "\n");
        logSeparator();
    }

    // === helper ===
    private double parseMultiplier(String raw) {
        try {
            double m = Double.parseDouble(raw);
            // jika input gaya persen (>= 5), ubah ke pecahan
            if (m >= 5)
                m = m / 100.0;
            if (m <= 0)
                m = 1.0;
            return m;
        } catch (NumberFormatException e) {
            return 1.0;
        }
    }

    private String formatMultiplierInput(String raw) {
        // biar outputnya sama kayak input user (contoh: 100 multiplier)
        return raw.isEmpty() ? "1.0" : raw;
    }

    private void logSeparator() {
        logToOutput("---------------------------------------------------------");
    }

    private void logToOutput(String text) {
        txtOutput.append(text + "\n");
        txtOutput.setCaretPosition(txtOutput.getDocument().getLength());
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
            }
            new RPGSimulator().setVisible(true);
        });
    }
}
