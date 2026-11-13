import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

// This class displays the simulation and provides the Play/Pause and Reset buttons.
public class SimulationApp {
    private World world;
    private JFrame frame;
    private GridPanel gridPanel;
    private JButton playButton;
    private Timer timer;

    // This constructor creates the window, buttons, grid panel, and animation timer.
    public SimulationApp() {
        world = new World();

        frame = new JFrame("Java Agent Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new java.awt.BorderLayout());

        gridPanel = new GridPanel();
        frame.add(gridPanel, java.awt.BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        playButton = new JButton("Play");
        JButton resetButton = new JButton("Reset");

        buttonPanel.add(playButton);
        buttonPanel.add(resetButton);
        frame.add(buttonPanel, java.awt.BorderLayout.SOUTH);

        timer = new Timer(Settings.TURN_DELAY_MS, event -> {
            world.takeTurn();
            gridPanel.repaint();
        });

        playButton.addActionListener(event -> {
            if (timer.isRunning()) {
                timer.stop();
                playButton.setText("Play");
            } else {
                timer.start();
                playButton.setText("Pause");
            }
        });

        resetButton.addActionListener(event -> {
            timer.stop();
            playButton.setText("Play");
            world = new World();
            gridPanel.repaint();
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // This inner panel draws the grid and the entities currently in the model.
    private class GridPanel extends JPanel {

        // This constructor sets the panel's preferred size.
        public GridPanel() {
            setPreferredSize(new Dimension(Settings.GRID_WIDTH * 30, Settings.GRID_HEIGHT * 30));
            setBackground(Color.WHITE);
        }

        // This method draws the grid lines and uses different colors for food and creatures.
        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);

            Entity[][] grid = world.getGrid();
            int cellWidth = getWidth() / world.getWidth();
            int cellHeight = getHeight() / world.getHeight();

            for (int row = 0; row < world.getHeight(); row++) {
                for (int column = 0; column < world.getWidth(); column++) {
                    int x = column * cellWidth;
                    int y = row * cellHeight;
                    Entity entity = grid[row][column];

                    if (entity == null) {
                        graphics.setColor(Color.WHITE);
                    } else if (entity.getSymbol() == 'F') {
                        graphics.setColor(new Color(80, 180, 90));
                    } else {
                        graphics.setColor(new Color(70, 120, 220));
                    }

                    graphics.fillRect(x, y, cellWidth, cellHeight);
                    graphics.setColor(Color.LIGHT_GRAY);
                    graphics.drawRect(x, y, cellWidth, cellHeight);
                }
            }
        }
    }

    // This main method starts the Swing application.
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new SimulationApp());
    }
}