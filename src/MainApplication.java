import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainApplication extends JFrame implements KeyListener {
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
        HeroLabel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        drawpane.add(HeroLabel);
        BossLabel = new JLabel(BossImg);
        BossLabel.setBounds(BossCurX, BossCurY, BossWidth, BossHeight);
        drawpane.add(BossLabel);

        JPanel control = new JPanel();
        control.setBounds(0,0,1000,50);
        control.setBackground(new Color(102, 204, 0));
        contentpane.add(control, BorderLayout.NORTH);
        contentpane.add(drawpane, BorderLayout.CENTER);
        addKeyListener(this);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W)
        {
            if (HeroCurY > 0) HeroCurY -= 20;
            else HeroCurY = 0;
            setLocation(HeroCurX, HeroCurY);
        }
        if (e.getKeyCode() == KeyEvent.VK_S)
        {
            if (HeroCurY < 510 - frameHeight) HeroCurY += 20;
            else HeroCurY = 510 - frameHeight;
            setLocation(HeroCurX, HeroCurY);
        }
        if (e.getKeyCode() == KeyEvent.VK_A)
        {
            if (HeroCurX < 20) HeroCurX = 635;
            else HeroCurX -= 20;
            setLocation(HeroCurX, HeroCurY);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            if (HeroCurX > 630) HeroCurX = 5;
            else HeroCurX += 20;
            setLocation(HeroCurX, HeroCurY);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

//class MyLabel extends JLabel implements MouseListener
//{
//    private int width = 150, height = 200;      // adjust label size as you want
//    private MyImageIcon   icon1, icon2;
//    private int           curX, curY;
//    private boolean       horizontalMove, verticalMove;
//
//    public MyLabel(String file1, String file2)
//    {
//        icon1 = new MyImageIcon(file1).resize(width, height);
//        icon2 = new MyImageIcon(file2).resize(width, height);
//        setHorizontalAlignment(JLabel.CENTER);
//        setIcon(icon1);
//        addMouseListener(this);
//    }
//
//    public void setMoveConditions(int x, int y, boolean h, boolean v)
//    {
//        curX = x; curY = y;
//        setBounds(curX, curY, width, height);
//        horizontalMove = h; verticalMove = v;
//    }
//
//    // add your own code
//    public void moveUp()    {
//        if(verticalMove) {
//            if (curY > 0) curY -= 20;
//            else curY = 0;
//            setLocation(curX, curY);
//        }
//    }
//    public void moveDown()  {
//        if(verticalMove) {
//            if (curY < 510 - height) curY += 20;
//            else curY = 510 - height;
//            setLocation(curX, curY);
//        }
//    }
//    public void moveLeft()  {
//        if(horizontalMove) {
//            if (curX < 20) curX = 635;
//            else curX -= 20;
//            setLocation(curX, curY);
//        }
//    }
//    public void moveRight() {
//        if(horizontalMove) {
//            if (curX > 630) curX = 5;
//            else curX += 20;
//            setLocation(curX, curY);
//        }
//    }
//
//
//    @Override
//    public void mouseClicked(MouseEvent e) {
//        if(e.getButton() == MouseEvent.BUTTON1) {
//            setIcon(icon2);
//        }
//        if(e.getButton() == MouseEvent.BUTTON3) {
//            setIcon(icon1);
//        }
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }
//}

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
