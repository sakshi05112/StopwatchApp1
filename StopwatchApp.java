	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	public class StopwatchApp {
	    private JFrame frame;
	    private JLabel timeLabel;
	    private JButton startButton, pauseButton, resetButton;
	    private Timer timer;
	    private long startTime, elapsedTime;
	    private boolean running = false;

	    public StopwatchApp() {
	        frame = new JFrame("Stopwatch");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(300, 200);
	        frame.setLayout(new BorderLayout());

	        timeLabel = new JLabel("00:00:000", SwingConstants.CENTER);
	        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));
	        frame.add(timeLabel, BorderLayout.CENTER);

	        JPanel buttonPanel = new JPanel();
	        startButton = new JButton("Start");
	        pauseButton = new JButton("Pause");
	        resetButton = new JButton("Reset");
	        
	        buttonPanel.add(startButton);
	        buttonPanel.add(pauseButton);
	        buttonPanel.add(resetButton);
	        frame.add(buttonPanel, BorderLayout.SOUTH);

	        timer = new Timer(10, new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                elapsedTime = System.currentTimeMillis() - startTime;
	                updateTimeLabel();
	            }
	        });

	        startButton.addActionListener(e -> start());
	        pauseButton.addActionListener(e -> pause());
	        resetButton.addActionListener(e -> reset());
	        
	        frame.setVisible(true);
	    }

	    private void start() {
	        if (!running) {
	            startTime = System.currentTimeMillis() - elapsedTime;
	            timer.start();
	            running = true;
	        }
	    }

	    private void pause() {
	        if (running) {
	            timer.stop();
	            running = false;
	        }
	    }

	    private void reset() {
	        timer.stop();
	        running = false;
	        elapsedTime = 0;
	        updateTimeLabel();
	    }

	    private void updateTimeLabel() {
	        long minutes = (elapsedTime / 60000) % 60;
	        long seconds = (elapsedTime / 1000) % 60;
	        long milliseconds = elapsedTime % 1000;
	        timeLabel.setText(String.format("%02d:%02d:%03d", minutes, seconds, milliseconds));
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(StopwatchApp::new);
	    }
	}

