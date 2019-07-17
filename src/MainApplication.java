import javax.swing.*;
import java.awt.*;

public class MainApplication extends JFrame
{
    private JPanel           contentpane;
    private JLabel           drawpane;
    //private JButton          offButton, rideButton;
    private JToggleButton [] speedToggles;
    private JTextField       greetText;
    //private JComboBox        boyCombo, dragonCombo;
    private JLabel           HeroLabel, BossLabel;
    private MyImageIcon      backgroundImg, HeroImg, BossImg;
    private ButtonGroup      bgroup;
    //private MySoundEffect    themeSound, greetSound;

    private int frameWidth  = 1000, frameHeight = 600;
    private int HeroCurX    = 400, HeroCurY     = 300;
    private int BossCurX    = 700, BossCurY     = 200;
    private int HeroWidth   = 250, HeroHeight   = 200;
    private int BossWidth   = 250, BossHeight   = 200;
    private Thread HeroThread, BossThread;
    //private boolean dragonMove = true, boyMove = true, dragonLeft = true, boyLeft = true, s = false;
    private int speed = 1000;

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
        contentpane.setLayout(new BorderLayout());
        AddComponents();
        setHeroThread();
        setBossThread();
    }

    public void AddComponents()
    {
        backgroundImg = new MyImageIcon("Resources/Background_sample_1.jpg").resize(frameWidth, frameHeight);
        HeroImg       = new MyImageIcon("Resources/Hero_sample_1.png").resize(HeroWidth, HeroHeight);
        BossImg       = new MyImageIcon("Resources/Boss_sample_1.png").resize(BossWidth, BossHeight);

        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);

        HeroLabel = new JLabel(HeroImg);
        HeroLabel.setBounds(HeroCurX, HeroCurY, HeroWidth, HeroHeight);
        drawpane.add(HeroLabel);

        BossLabel = new JLabel(BossImg);
        BossLabel.setBounds(BossCurX, BossCurY, BossWidth, BossHeight);
        drawpane.add(BossLabel);

        JPanel control = new JPanel();
        control.setBounds(0,0,1000,50);
        control.setBackground(new Color(102, 204, 0));
        contentpane.add(control, BorderLayout.NORTH);
        contentpane.add(drawpane, BorderLayout.CENTER);

        validate();
    }

    public void setHeroThread()
    {
        // end run
        HeroThread = new Thread() {
            public void run() {
                while (true) {

                    repaint();

                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } // end while
            }
        }; // end thread creation
        HeroThread.start();
    }

    public void setBossThread(){
        BossThread = new Thread() {
            public void run(){
                while (true) {

                    repaint();

                    try {
                        Thread.sleep(speed);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } // end while
            }
        };
        BossThread.start();
    }

}

class MyImageIcon extends ImageIcon
{
    public MyImageIcon(String fname)  { super(fname); }
    public MyImageIcon(Image image)   { super(image); }

    public MyImageIcon resize(int width, int height)
    {
        Image oldimg = this.getImage();
        Image newimg = oldimg.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new MyImageIcon(newimg);
    }
};
