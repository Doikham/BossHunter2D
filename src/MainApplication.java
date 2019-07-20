import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainApplication extends JFrame{
    private JPanel           contentpane;
    private JLabel           drawpane;
    private MyLabel          HeroLabel;
    private JLabel           BossLabel;

    private int BossCurX    = 900, BossCurY     = 300;
    private int BossWidth   = 450, BossHeight   = 400;
    private int speed = 1000;
    private double width, height;

    public static void main(String[] args)
    {
        new MainApplication();
    }

    public MainApplication()
    {
        setTitle("Boss Hunter 2D");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);
        setResizable(false);
        setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        validate();
        contentpane = (JPanel)getContentPane();
        contentpane.setLayout(new BorderLayout());
        AddComponents();
        JOptionPane.showMessageDialog(new JFrame(), "Fight!" , "Hello!",
                JOptionPane.INFORMATION_MESSAGE );
        setGameThread();
    }

    public void AddComponents()
    {
        MyImageIcon backgroundImg = new MyImageIcon("Resources/Background_sample_1.jpg").resize((int)width, (int)height);
        MyImageIcon BossImg       = new MyImageIcon("Resources/Boss_sample_1.png").resize(BossWidth, BossHeight);

        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);

        HeroLabel = new MyLabel();
        drawpane.add(HeroLabel);
        HeroLabel.setFocusable(true);
        HeroLabel.requestFocus();
        addKeyListener(HeroLabel);
        repaint();

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

    public void setGameThread(){
        Thread BossThread = new Thread() {
            public void run()
            {
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

class MyLabel extends JLabel implements KeyListener
{
    private int width = 150, height = 200;
    private MyImageIcon   HeroImage;
    private int HeroWidth = 250, HeroHeight = 200;
    private int HerocurX  = 90, HerocurY = 400;

    public MyLabel()
    {
        HeroImage = new MyImageIcon("Resources/Hero_sample_1.png").resize(HeroWidth, HeroHeight);
        setHorizontalAlignment(JLabel.CENTER);
        setIcon(HeroImage);
        setHeroLocation();
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                if (HerocurY > 0) HerocurY -= 20;
                else HerocurY = 0;
                break;
            case KeyEvent.VK_DOWN:
                if (HerocurY < height-200) HerocurY += 20;
                else HerocurY = 510 - height;
                break;
            case KeyEvent.VK_LEFT:
                if (HerocurX < 20) HerocurX = 635;
                else HerocurX -= 20;
                break;
            case KeyEvent.VK_RIGHT:
                if (HerocurX > 630) HerocurX = 5;
                else HerocurX += 20;
                break;
            default: break;
        }
        setHeroLocation();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setHeroLocation(){
        setBounds(HerocurX,HerocurY,width,height);
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
