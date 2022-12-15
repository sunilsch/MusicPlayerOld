import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame implements ActionListener {

    // Buttons
    private JButton pauseButton;
    private JButton reverseSkipButton;
    private JButton skipButton;
    private JButton searchButton;
    private JButton detailsButton;

    // Lists
    private JList<String> listRight;
    private JList<String> listLeft;
    private JList<String> listSongs;

    // Panels
    private JPanel mainPanel;
    private JSplitPane outSplitPane;
    private JSplitPane searchSplitPane;
    private JSplitPane splitPane1;

    // Text input
    private JTextField searchInput;

    // Labels
    private JLabel equalizer;

    // Other
    private JProgressBar progressBar1;

    // reference to Main object
    private final Main main;

    /**
     * Constructor of MainWindow
     * @param title window title
     * @param main reference to object of main class
     */
    public MainWindow(String title, Main main){

        super(title);
        initWindow();
        loadIcons();
        initSplitPanes();
        initButtons();
        this.setVisible(true);
        this.main = main;

        // START DEBUG TEST //
        ArrayList<String> data = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            data.add("Test 1");
        }
        data.add("Test 2");
        this.listRight.setListData(data.toArray(new String[0]));
        this.listLeft.setListData(data.toArray(new String[0]));
        // END DEBUG TEST //



    }

    private void initWindow(){
        this.setPreferredSize(new Dimension(1250,1000));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    private void initSplitPanes(){
        splitPane1.setDividerLocation(0.5);
        splitPane1.setResizeWeight(0.5);
        outSplitPane.setDividerLocation(0.5);
        outSplitPane.setResizeWeight(0.5);
        searchSplitPane.setDividerLocation(0.75);
        searchSplitPane.setResizeWeight(0.75);
    }

    private void initButtons(){
        pauseButton.addActionListener(this);
        pauseButton.setActionCommand("play");
        skipButton.addActionListener(this);
        skipButton.setActionCommand("skip");
        reverseSkipButton.addActionListener(this);
        reverseSkipButton.setActionCommand("skip-reverse");
    }

    private void loadIcons(){
        this.setIconImage(new ImageIcon(System.getProperty("user.dir")+"/img/music.png").getImage());
        pauseButton.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/play.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        reverseSkipButton.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/skip-button-reverse.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        skipButton.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/skip-button.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        equalizer.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/Equalizer.gif").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));

    }

    /*
    * _______________________ *
    *
    *  BUTTON EVENTS
    *
    * _______________________ *
    * */

    public void actionPerformed(ActionEvent ae){
        String action = ae.getActionCommand();
        switch (action) {
            case "pause" -> this.stopMusic();
            case "play" -> this.playMusic();
            case "skip" -> this.skipMusic();
            case "skip-reverse" -> this.reverseSkipMusic();
        }
    }

    private void stopMusic(){
        pauseButton.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/play.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        pauseButton.setActionCommand("play");
        main.stopMusic();
    }

    private void playMusic(){
        System.out.println("Play button pressed!");
        pauseButton.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        pauseButton.setActionCommand("pause");
        main.playMusic();
    }

    private void skipMusic(){
        main.skipMusic();
    }
    private void reverseSkipMusic(){
        main.reverseSkipMusic();
    }

}
