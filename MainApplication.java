import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame
{
    private JPanel           contentpane;
    private JLabel           drawpane;
    private JButton          offButton, rideButton;
    private JToggleButton [] speedToggles;
    private JTextField       greetText;
    private JComboBox        boyCombo, dragonCombo;
    private JLabel           boyLabel, dragonLabel;
    //private MyImageIcon      backgroundImg, boyImg, dragonImg, rideImg;
    private ButtonGroup      bgroup;
    //private MySoundEffect    themeSound, greetSound;

    // working variables - adjust the values as you want
    private int frameWidth  = 1000, frameHeight = 600;
    private int boyCurX     = 400, boyCurY      = 300;
    private int dragonCurX  = 700, dragonCurY   = 200, rideCurY = 0;
    private int boyWidth    = 100, boyHeight    = 200;
    private int dragonWidth = 250, dragonHeight = 200;
    private int rideWidth   = 400, rideHeight   = 300;
    private Thread dragonThread, boyThread;
    private boolean dragonMove = true, boyMove = true, dragonLeft = true, boyLeft = true, s = false;
    private int speed = 1000;
    private int greetCount;

    public static void main(String[] args)
    {
        new MainApplication();
    }

    public MainApplication()
    {
        setTitle("Boss Hunter 2D");
        setBounds(50, 50, frameWidth, frameHeight);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        contentpane = (JPanel)getContentPane();
        AddComponents();
    }

    public void AddComponents()
    {
        JPanel control = new JPanel();
        control.setBounds(0,0,1000,50);
        control.setBackground(new Color(102, 204, 0));
        contentpane.add(control, BorderLayout.NORTH);
        contentpane.add(drawpane, BorderLayout.CENTER);
        validate();
    }

}
