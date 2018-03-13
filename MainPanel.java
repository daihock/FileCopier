import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainPanel extends JPanel {

    public static final int COPY = 0;
    public static final int MOVE = 1;

    public static int copyType = COPY; //Default action is COPY

    //* Default text fields values
    public static String sourcePath = "C:\\test1";
    public static String destPath = "C:\\test2";
    public static String pattern = "*";


    public MainPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //First area with label, text field and button
        //////////////////////////////////////////////////////////////////////////////////////////////////////
        JLabel sourceLbl = new JLabel("Copy from:"); //Label
        JPanel sourcePanel = new JPanel (new BorderLayout()); //Panel with label only
        sourcePanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        sourcePanel.add(sourceLbl);
        JTextField sourceField = new JTextField(30); //Text field
        sourceField.setText(sourcePath);
        sourceField.setName("sourceField");
        sourceField.getDocument().addDocumentListener(new TextUpdateAction(sourceField));
        JButton sourceBtn = new JButton("Browse"); //Button
        sourceBtn.addActionListener(new BrowseAction(sourceField)); //Add Action with parameter JTextField
        sourceBtn.setActionCommand("source");

        //Panel with text field and button
        JPanel sourceDir = new JPanel();
        sourceDir.setLayout(new BoxLayout(sourceDir, BoxLayout.X_AXIS));
        sourceDir.setBorder(BorderFactory.createEmptyBorder(1,10,10,10));
        sourceDir.add(Box.createHorizontalGlue());
        sourceDir.add(sourceField);
        sourceDir.add(Box.createRigidArea(new Dimension(10,10)));
        sourceDir.add(sourceBtn);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //Second panel with destination folder
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel destPanel = new JPanel(new BorderLayout()); //Panel with label only
        JLabel destLbl = new JLabel("Copy to:"); //Label
        destPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        destPanel.add(destLbl);
        JTextField destField = new JTextField(30); //Text field
        destField.setText(destPath);
        destField.setName("destField");
        destField.getDocument().addDocumentListener(new TextUpdateAction(destField));
        JButton destBtn = new JButton("Browse"); //Button
        destBtn.addActionListener(new BrowseAction(destField)); //Add Action with parameter JTextField

        //Panel with text field and button
        JPanel destDir = new JPanel();
        destDir.setLayout(new BoxLayout (destDir, BoxLayout.X_AXIS));
        destDir.setBorder(BorderFactory.createEmptyBorder(1,10,10,10));
        destDir.add(Box.createHorizontalGlue());
        destDir.add(destField);
        destDir.add(Box.createRigidArea(new Dimension(10,10)));
        destDir.add(destBtn);

        //Pattern text field

        JPanel patternPanel = new JPanel();
        patternPanel.setLayout(new BoxLayout(patternPanel,BoxLayout.X_AXIS));
        patternPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        JLabel patternLbl = new JLabel("Pattern:");
        JTextField patternField = new JTextField();
        patternField.setText(pattern);
        patternField.getDocument().addDocumentListener(new PatternAction(patternField));
        patternField.setPreferredSize(new Dimension(500,25));
        patternPanel.add(patternLbl);
        patternPanel.add(Box.createRigidArea(new Dimension(10,10)));
        patternPanel.add(patternField);

        //Radio buttons group

        ButtonGroup radioBtns = new ButtonGroup();
        JRadioButton radioCopy = new JRadioButton("Copy");
        radioCopy.addActionListener(new RadioAction(COPY)); //Action listener for radio button "Copy"
        radioCopy.setMnemonic(KeyEvent.VK_C);
        radioCopy.setHorizontalAlignment(SwingConstants.LEFT);
        JRadioButton radioMove = new JRadioButton("Move");
        radioMove.addActionListener(new RadioAction(MOVE)); //Action listener for radio button "Move"
        radioMove.setMnemonic(KeyEvent.VK_M);

        //Radio buttons

        radioBtns.add(radioCopy);
        radioBtns.add(radioMove);
        radioBtns.setSelected(radioCopy.getModel(), true);

        //File list button

        JButton listBtn = new JButton("File list");
        listBtn.setPreferredSize(new Dimension(120,25));

        //Action listener for main button to retrieve file list

        listBtn.addActionListener(new FileListAction(/*sourcePath, destPath, pattern, copyType*/));

        //Radio panel

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.X_AXIS));
        radioPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        radioPanel.add(radioCopy);
        radioPanel.add(radioMove);
        radioPanel.add(Box.createHorizontalGlue());
        radioPanel.add(listBtn);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Main panel construction
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////

        add(Box.createVerticalGlue());
        add(sourcePanel);
        add(sourceDir);
        add(destPanel);
        add(destDir);
        add(patternPanel);
        add(radioPanel);

    }


}
