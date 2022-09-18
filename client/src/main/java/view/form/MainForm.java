package view.form;

import dto.impl.UserDTO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class MainForm extends javax.swing.JFrame {

    private final Integer FRAME_WIDTH = 600;
    private final Integer FRAME_HEIGHT = 350;
    private final Integer IMG_WIDTH = 300;
    private final Integer IMG_HEIGHT = 150;

    private final UserDTO user;
    private final Map<String, String> selectedQuotes;
    
    public MainForm(UserDTO user) {
        this.user = user;
        selectedQuotes = new HashMap<>();
        initComponents();
        prepareComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenuBar = new javax.swing.JMenuBar();
        menuClients = new javax.swing.JMenu();
        optionCreateFamilyTree = new javax.swing.JMenuItem();
        optionDeleteFamilyTree = new javax.swing.JMenuItem();
        optionShowFamilyTree = new javax.swing.JMenuItem();
        menuCases = new javax.swing.JMenu();
        optionNewMember = new javax.swing.JMenuItem();
        optionChangeMember = new javax.swing.JMenuItem();
        optionDeleteMember = new javax.swing.JMenuItem();
        optionFindRelationship = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        menuClients.setText("Klijenti");

        optionCreateFamilyTree.setText("Kreiraj novi rodoslov");
        menuClients.add(optionCreateFamilyTree);

        optionDeleteFamilyTree.setText("Obriši rodoslov");
        menuClients.add(optionDeleteFamilyTree);

        optionShowFamilyTree.setText("Prikaz rodoslova");
        menuClients.add(optionShowFamilyTree);

        mainMenuBar.add(menuClients);

        menuCases.setText(" Predmeti");

        optionNewMember.setText("Unos novog člana u rodoslov");
        menuCases.add(optionNewMember);

        optionChangeMember.setText("Izmeni člana");
        menuCases.add(optionChangeMember);

        optionDeleteMember.setText("Obriši člana ");
        menuCases.add(optionDeleteMember);

        optionFindRelationship.setText("Odredi rodbinsku vezu dva člana");
        menuCases.add(optionFindRelationship);

        mainMenuBar.add(menuCases);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 574, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 344, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenu menuCases;
    private javax.swing.JMenu menuClients;
    private javax.swing.JMenuItem optionChangeMember;
    private javax.swing.JMenuItem optionCreateFamilyTree;
    private javax.swing.JMenuItem optionDeleteFamilyTree;
    private javax.swing.JMenuItem optionDeleteMember;
    private javax.swing.JMenuItem optionFindRelationship;
    private javax.swing.JMenuItem optionNewMember;
    private javax.swing.JMenuItem optionShowFamilyTree;
    // End of variables declaration//GEN-END:variables

    private void prepareComponents() {
        this.getContentPane().setBackground(Color.WHITE);
        this.setTitle(user.getName() + ", dobrodošli.");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        ImageIcon backgroundImg = new ImageIcon(this.getClass().getResource("/ps-pic.png"));
        JLabel lblImg = new JLabel(backgroundImg);
        lblImg.setSize(IMG_WIDTH, IMG_HEIGHT);
        lblImg.setBounds(IMG_WIDTH/2, IMG_HEIGHT/2, IMG_WIDTH, IMG_HEIGHT);
        this.add(lblImg);
        JLabel lblQuote = new JLabel("Insert Text here");
        Font fntQuote = new Font(lblQuote.getFont().getName(), Font.ITALIC+Font.BOLD, lblQuote.getFont().getSize());
        lblQuote.setSize(FRAME_WIDTH/2, 15);
        lblQuote.setBounds(IMG_WIDTH/2, 50, FRAME_WIDTH/2, 15);
        lblQuote.setForeground(new Color(203, 171, 103, 255)); // color of the logo
        lblQuote.setFont(fntQuote);
        insertLoginQuote();
        lblQuote.setText(selectedQuotes.get("latin"));
        this.add(lblQuote);
        addListeners(lblImg, lblQuote);
    }

    private void insertLoginQuote() {
        List<String> lines = new ArrayList<>();
        InputStream inputStream = this.getClass().getResourceAsStream("/quotes.txt");
        Scanner scanner = new Scanner(inputStream);
        while(scanner.hasNextLine()){
           lines.add(scanner.nextLine());
        }
        Random random = new Random();
        String selectedLine = lines.get(random.nextInt(lines.size()));
        int indxDot = selectedLine.indexOf('.');
        String latinQuote = selectedLine.substring(0, indxDot + 1);
        String serbianQuote = selectedLine.substring(indxDot + 1);
        selectedQuotes.put("latin", latinQuote);
        selectedQuotes.put("serbian",serbianQuote);
    }

    private void addListeners(JLabel lblImg, JLabel lblQuote) {
        lblImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lblQuote.setText(selectedQuotes.get("serbian"));
            }
        });
        lblImg.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lblQuote.setText(selectedQuotes.get("latin"));
            }
        });
    }
}
