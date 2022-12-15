import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainWindow extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JList<String> list1;
    private JList<String> list2;
    private JSplitPane splitPane1;
    private JTextField textField1;
    private JButton suchenButton;
    private JSplitPane outSplitPane;
    private JSplitPane searchSplitPane;
    private JButton pauseButton;
    private JButton reverseSkipButton;
    private JButton skipButton;
    private JLabel equalizer;
    private JProgressBar progressBar1;
    private JButton detailsButton;

    private Main main;
    public MainWindow(String title, Main main){

        super(title);
        this.setPreferredSize(new Dimension(1250,1000));
        this.setIconImage(new ImageIcon(System.getProperty("user.dir")+"/img/music.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        ArrayList<String> data = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            data.add("Test 1");
        }
        data.add("Test 2");
        this.list1.setListData(data.toArray(new String[0]));
        this.list2.setListData(data.toArray(new String[0]));


        splitPane1.setDividerLocation(0.5);
        splitPane1.setResizeWeight(0.5);
        outSplitPane.setDividerLocation(0.5);
        outSplitPane.setResizeWeight(0.5);
        searchSplitPane.setDividerLocation(0.75);
        searchSplitPane.setResizeWeight(0.75);

        pauseButton.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        reverseSkipButton.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/skip-button-reverse.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        skipButton.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/skip-button.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        equalizer.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/Equalizer.gif").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));

        pauseButton.addActionListener(this);
        pauseButton.setActionCommand("pause");

        this.setVisible(true);


    }
    public void actionPerformed(ActionEvent ae){
        String action = ae.getActionCommand();
        if(action.equals("pause")){
            pauseMusic();
        } else if(action.equals("play")){
            playMusic();
        }
    }

    public void pauseMusic(){
        pauseButton.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/play.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        pauseButton.setActionCommand("play");
    }

    public void playMusic(){
        pauseButton.setIcon(new ImageIcon(new ImageIcon(System.getProperty("user.dir")+"/img/pause.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        pauseButton.setActionCommand("pause");

    }
}
