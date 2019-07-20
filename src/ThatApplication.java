import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ThatApplication extends JFrame{
    private JPanel              contentpane;
    private JLabel              drawpane;
    private MyLabel             JetpackLabel;
    private JLabel              BossLabel;
    private JButton             startButton, closeButton, highscoreButton;
    private JTextField          scoreText;
    //private MySoundEffectThat   themeSoundThat;

    private double width, height;

    public ThatApplication()
    {
        setTitle("EZ Coin Collector: Beware your step!");
        setBounds(50, 30, 1200, 700);
        setResizable(false);
        setVisible(false);

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
        MyImageIcon backgroundImg = new MyImageIcon("Resources/jetpack/bg2.jpg").resize((int)width, (int)height);

        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        drawpane.setLayout(null);

        shareInt send = new shareInt();
        send.shareDouble(width,height);

        JetpackLabel = new MyLabel();
        drawpane.add(JetpackLabel);
        JetpackLabel.setFocusable(true);
        JetpackLabel.requestFocus();
        addKeyListener(JetpackLabel);
        repaint();

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
                        Thread.sleep(100);
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
    private shareInt receive;

    public MyLabel()
    {
        HeroImage = new MyImageIcon("Resources/jetpack/jetpackboy.png").resize(HeroWidth, HeroHeight);
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
                if (HerocurY < (int)receive.height-HeroHeight-30) HerocurY += 20;
                else HerocurY = (int)receive.height-HeroHeight-30;
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
}

class shareInt{
    protected static double width,height;
    public void shareDouble(double w,double h){width = w;height = h;}

}