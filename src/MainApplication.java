import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainApplication extends JFrame {

    private JPanel              contentpane;
    private JLabel              drawpane;
    private MyImageIcon         backgroundImg;
    private JButton             startButton;
    private JTextField          textView;
    private MySoundEffectIntro  themeSoundMain;

    public static void main(String[] args) { new MainApplication().setVisible(true); }

    public MainApplication()
    {
        setTitle("EZ Coin Collector");
        setBounds(100, 30, 1300, 700);
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        validate();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //themeSoundMain.stop();
            }
        });

        contentpane = (JPanel) getContentPane();
        contentpane.setLayout(new BorderLayout());
        AddComponents();
    }

    public void AddComponents()
    {
        //themeSound = new MySoundEffectIntro("Venus.wav");
        //themeSound.playLoop();
        backgroundImg = new MyImageIcon("Resources/jetpack/bg.jpg").resize(contentpane.getWidth(), contentpane.getHeight());
        drawpane = new JLabel();
        drawpane.setIcon(backgroundImg);
        textView = new JTextField(30);
        textView.setBounds(250, 525, 350, 70);
        textView.setFont(new Font("Arial", Font.PLAIN, 20));
        textView.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                firePropertyChange("textViewKeyReleased", "", textView.getText());
            }

        });

        startButton = new JButton("Press Here");
        startButton.setBounds(655, 525, 250, 70);
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startButtonActionPerformed(evt);
            }

            private void startButtonActionPerformed(ActionEvent evt) {
                ChooseApplication frame2 = new ChooseApplication();
                //addPropertyChangeListener(frame2);
                frame2.setVisible(true);
                firePropertyChange("startButtonActionPerformed", "", textView.getText());
                setVisible(false);
                //themeSound.stop();
            }
        });

        contentpane.add(textView);
        contentpane.add(startButton);
        contentpane.add(drawpane, BorderLayout.CENTER);
        repaint();
        validate();
    }
}

class MySoundEffectIntro {

    private java.applet.AudioClip audio_main;

    public MySoundEffectIntro(String filename) {
        try {
            java.io.File file1 = new java.io.File(filename);
            audio_main = java.applet.Applet.newAudioClip(file1.toURL());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void playOnce() {
        audio_main.play();
    }

    public void playLoop() {
        audio_main.loop();
    }

    public void stop() {
        audio_main.stop();
    }
}