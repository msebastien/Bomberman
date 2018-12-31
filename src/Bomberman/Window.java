package Bomberman;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window extends JFrame  {

    //pour avoir la bonne taille de JPanel

    private JPanel hud;
    private JButton buttonBeginGame;
    private JLabel currentBombNumber;
    private JLabel currentLevel;
    private JLabel textEndGame;
    //private JPanel baseJPanel;
    private CardLayout menuGestion;

    public Window(Scene scene) throws HeadlessException {


        this.setTitle("Bomberman");

        this.setLocation(0,0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);


        //====
        //creation des layouts
        //====

        /*
        Creation des layouts du jeu
         */
        hud=new JPanel();
        hud.setLayout(new BoxLayout(hud,BoxLayout.PAGE_AXIS));

        JLabel titleNbrBomb=new JLabel("Nombre de Bombes");
        setBasicProperty(titleNbrBomb,Color.BLACK,hud);

        currentBombNumber=new JLabel("...");
        setBasicProperty(currentBombNumber,Color.RED,hud);

        JLabel titleCurrentLevel=new JLabel("Level");
        setBasicProperty(titleCurrentLevel,Color.BLACK,hud);

        currentLevel=new JLabel("...");
        setBasicProperty(currentLevel,Color.RED,hud);

        JPanel printGame=new JPanel();
        printGame.setLayout(new BorderLayout());
        printGame.add(scene, BorderLayout.CENTER);
        printGame.add(hud,BorderLayout.EAST);

        /*
        Creation des layouts du menu Pause
         */
        JPanel menuPause=new JPanel();
        menuPause.setLayout(new BoxLayout(menuPause,BoxLayout.PAGE_AXIS));

        textEndGame=new JLabel("Fin du Jeu");
        setBasicProperty(textEndGame,Color.RED,menuPause);
        textEndGame.setFont(new Font("Arial",Font.BOLD,60));

        buttonBeginGame=new JButton("NextLevel");
        buttonBeginGame.addActionListener(e ->
        {
            menuGestion.show(this.getContentPane(),"printGame");
            Main.game.init();
        });
        setBasicProperty(buttonBeginGame,Color.BLACK,menuPause);

        JButton buttonQuit=new JButton("Quit");
        buttonQuit.addActionListener(e->dispose());
        setBasicProperty(buttonQuit,Color.BLACK,menuPause);

        menuPause.add(buttonBeginGame);
        menuPause.add(buttonQuit);
        menuPause.setBorder(BorderFactory.createEmptyBorder(100,100,100,100));
        menuPause.setBackground(Color.BLACK);

        //on met le tout dans le cardlyout
        menuGestion=new CardLayout();

        this.setLayout(menuGestion);
        this.add(printGame,"printGame");
        this.add(menuPause,"menuPause");

        this.pack();
    }

    public void updateHUD(int nbrBomb)
    {
        currentLevel.setText(Integer.toString(Main.game.getLevel()));
        currentBombNumber.setText(Integer.toString(nbrBomb));
    }

    public void showMenuEndGame(IssueGame issueGame)
    {
        String endgame;
        String playAgain;//text print on the JButton which play a new game

        //fenetre a afficher en cas de victoire
        if(issueGame.equals(IssueGame.VICTORY))
        {
            endgame="Victoire";
            playAgain="NextLevel";
        }else
        {
            endgame="Defaite";
            playAgain="Try again";
        }
        endgame+=" - Level : "+Main.game.getLevel();

        textEndGame.setText(endgame);
        buttonBeginGame.setText(playAgain);
        menuGestion.show(this.getContentPane(),"menuPause");

    }

    /**
     * Create a label and add it to the jpanel
     * @return the Label that contain the information
     */
    private void setBasicProperty(JComponent composant,Color foregroundColor,JPanel jPanel)
    {
        composant.setFont(new Font("Arial",Font.PLAIN,20));
        composant.setForeground(foregroundColor);
        composant.setAlignmentX(Component.CENTER_ALIGNMENT);
        composant.setAlignmentY(Component.CENTER_ALIGNMENT);

        jPanel.add(composant);
    }

}
