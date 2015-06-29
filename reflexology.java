package quant_methods;

import java.awt.Adjustable;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Reflexology2 extends JFrame{

private static final long serialVersionUID = -1295261024563143679L;
private Ellipse2D ball = new Ellipse2D.Double(0, 0, 25, 25);
private Timer moveBallTimer;
int _ballXpos, _ballYpos;
JButton button1, button2;
JButton movingButton;
int buttonAClicked, buttonDClicked;
private long _openTime = 0;
private long _closeTime = 0;
JPanel thePanel = new JPanel();
JPanel thePlacebo = new TestPanel();
JPanel theSurvey = new JPanel();
final JFrame frame = new JFrame("Reflexology");
final JFrame frame2 = new JFrame("Reflexology");
final JFrame frame3 = new JFrame("Survey");
Font font = new Font("Palatino", Font.PLAIN, 18);
JLabel label1 = new JLabel("Click the moving dot as fast as you can.");
JButton btnPage2 = new JButton("Next");
JButton btnPage3 = new JButton("Next");
JButton btnPage4 = new JButton("Next");
JButton btnDone = new JButton("Finish");
JPanel panelSurv1 = new JPanel();
JPanel panelSurv2 = new JPanel();
JPanel panelSurv3 = new JPanel();
JPanel panelSurv4 = new JPanel();
JTextArea textArea1 = new JTextArea(24, 50);
JScrollPane scrollBar1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);



public static void main(String[] args){
    new Reflexology2();
}

public Reflexology2(){
    frame.setSize(615, 455);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Reflexology 2.0");
    frame.setResizable(false);

    frame2.setSize(600, 475);
    frame2.setLocationRelativeTo(null);
    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame2.setTitle("Reflexology 2.0");
    frame2.setResizable(false);
    
    frame3.setSize(600, 475);
    frame3.setLocationRelativeTo(null);
    frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame3.setTitle("Reflexology Survey");
    frame3.setResizable(false);

    button1 = new JButton("Accept");
    button2 = new JButton("Decline");
    //movingButton = new JButton("Click Me");

    ListenForAcceptButton lForAButton = new ListenForAcceptButton();
    ListenForDeclineButton lForDButton = new ListenForDeclineButton();
    button1.addActionListener(lForAButton);
    button2.addActionListener(lForDButton);
    //movingButton.addActionListener(lForMButton);


    textArea1.setText("Tracking Events\n");
    textArea1.setLineWrap(true);
    textArea1.setWrapStyleWord(true);
    textArea1.setSize(15, 50);
    textArea1.setEditable(false);

    FileReader reader = null;
    try {
        reader = new FileReader("EULA.txt");
        textArea1.read(reader, "EULA.txt");
    } catch (IOException exception) {
        System.err.println("Problem loading file");
        exception.printStackTrace();
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException exception) {
                System.err.println("Error closing reader");
                exception.printStackTrace();
            }
        }

    }

    AdjustmentListener sListener = new MyAdjustmentListener();
    scrollBar1.getVerticalScrollBar().addAdjustmentListener(sListener);
    
    thePanel.add(scrollBar1);
    button1.setEnabled(false);
    thePanel.add(button1);
    thePanel.add(button2);
    
    frame.add(thePanel);
    ListenForMouse lForMouse = new ListenForMouse();
    thePlacebo.addMouseListener(lForMouse);
    label1.setFont(font);
    thePlacebo.add(label1);
    frame2.add(thePlacebo);

    ListenForWindow lForWindow = new ListenForWindow();
    frame.addWindowListener(lForWindow);
    frame2.addKeyListener(new KeyAdapter() {
        public void keyPressed(KeyEvent e){
            if(e.getKeyChar() == 'X' || e.getKeyChar() == 'x') {moveBallTimer.start();}
        }
    });
    frame.setVisible(true);

       moveBallTimer = new Timer(900, new ActionListener() {
           public void actionPerformed(ActionEvent e) {
           moveBall();
         //  System.out.println("Timer started!");
           repaint();
          }
        });

        addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
                if(frame2.isVisible()){ moveBallTimer.start(); }
          }
        });
        
    	setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelSurv1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame3.getContentPane().setLayout(new CardLayout(0, 0));
		frame3.getContentPane().add(panelSurv1, "");
		panelSurv1.setLayout(null);
		
		/*
		frame3.setVisible(true);
		frame3.setSize(600, 475);
		frame3.setResizable(false);
		frame3.setLocationRelativeTo(null);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		
		JLabel lblNewLabel = new JLabel("How old are you?");
		lblNewLabel.setBounds(22, 26, 112, 14);
		panelSurv1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select your gender:");
		lblNewLabel_1.setBounds(366, 37, 115, 14);
		panelSurv1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Current class level:");
		lblNewLabel_2.setBounds(22, 56, 112, 14);
		panelSurv1.add(lblNewLabel_2);
		
		String[] classLevel = {"", "Freshman", "Sophomore", "Junior", "Senior", "Senior+", "Graduate Student", "Other"};
	    cmbClass = new JComboBox(classLevel);
		cmbClass.setName("");
		cmbClass.setBounds(144, 53, 195, 20);
		panelSurv1.add(cmbClass);
		
		JLabel lblWhatIsYour = new JLabel("What is your major or field?");
		lblWhatIsYour.setBounds(22, 102, 161, 14);
		panelSurv1.add(lblWhatIsYour);
		
		txtMajor = new JTextField();
		txtMajor.setName("");
		txtMajor.setColumns(10);
		txtMajor.setBounds(193, 99, 347, 20);
		panelSurv1.add(txtMajor);
		
		JLabel lblDidYouRead = new JLabel("Did you read the license agreement before installing Reflexology?");
		lblDidYouRead.setBounds(22, 143, 392, 14);
		panelSurv1.add(lblDidYouRead);
		
		JLabel lblIfTheLicensing = new JLabel("If the licensing agreement raised any privacy concerns, please type them below:");
		lblIfTheLicensing.setBounds(22, 178, 503, 14);
		panelSurv1.add(lblIfTheLicensing);
		
		JLabel lblToHowMany = new JLabel("Up to how many friends may you gift Reflexology?");
		lblToHowMany.setBounds(22, 247, 303, 14);
		panelSurv1.add(lblToHowMany);
		
		String[] numGifts = {"", "Don't know", "0", "1", "2", "3", "4"};
		cmbNumGifts = new JComboBox(numGifts);
		cmbNumGifts.setBounds(320, 244, 205, 20);
		panelSurv1.add(cmbNumGifts);
		
		JLabel lblReflexologyProvidesThe = new JLabel("Reflexology provides the following warranty:");
		lblReflexologyProvidesThe.setBounds(22, 280, 254, 14);
		panelSurv1.add(lblReflexologyProvidesThe);
		
		String[] theWarranty = {"", "Don't know", "Won't harm my computer", "Doesn't have viruses", "Money back guarantee", "None"};
	    cmbWarranty = new JComboBox(theWarranty);
		cmbWarranty.setBounds(296, 277, 157, 20);
		panelSurv1.add(cmbWarranty);
		
		JLabel lblWhoOwnsIntellectual = new JLabel("Who owns intellectual property created with Reflexology?");
		lblWhoOwnsIntellectual.setBounds(22, 316, 372, 14);
		panelSurv1.add(lblWhoOwnsIntellectual);
		
		String[] IPOwner = {"", "Don't know", "Me", "The researchers", "Penn State", "Reflexology programmers"};
		cmbIPOwner = new JComboBox(IPOwner);
		cmbIPOwner.setBounds(408, 313, 157, 20);
		panelSurv1.add(cmbIPOwner);
		
		btnPage2.setBounds(340, 400, 89, 23);
		panelSurv1.add(btnPage2);
		
		JLabel lblNewLabel_3 = new JLabel("*WARNING* You cannot return to these answers.");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setBounds(35, 404, 277, 14);
		panelSurv1.add(lblNewLabel_3);
		
		String[] gender = {"", "M", "F"};
		cmbGender = new JComboBox(gender);
		cmbGender.setBounds(491, 34, 70, 20);
		panelSurv1.add(cmbGender);
		
		String[] didRead = {"", "Yes", "No"};
		 cmbDidRead = new JComboBox(didRead);
		cmbDidRead.setBounds(424, 140, 98, 20);
		panelSurv1.add(cmbDidRead);
		
		spnAge = new JSpinner();
		spnAge.setModel(new SpinnerNumberModel(1, 1, 111, 1));
		spnAge.setBounds(148, 23, 47, 20);
		panelSurv1.add(spnAge);
		
		txtPrivacyConcerns = new JTextField();
		txtPrivacyConcerns.setName("");
		txtPrivacyConcerns.setColumns(10);
		txtPrivacyConcerns.setBounds(22, 204, 547, 23);
		panelSurv1.add(txtPrivacyConcerns);
		
		frame3.getContentPane().add(panelSurv2, "");
		panelSurv2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Estimate your overall comprehension of the License Agreement:");
		lblNewLabel_4.setBounds(20, 18, 386, 14);
		panelSurv2.add(lblNewLabel_4);
		
		sldComp = new JSlider();
		sldComp.setMajorTickSpacing(1);
		sldComp.setPaintTicks(true);
		sldComp.setPaintLabels(true);
		sldComp.setSnapToTicks(true);
		sldComp.setValue(-1);
		sldComp.setMinorTickSpacing(1);
		sldComp.setMinimum(1);
		sldComp.setMaximum(5);
		sldComp.setBounds(154, 43, 274, 45);
		panelSurv2.add(sldComp);
		
		JLabel lblNewLabel_5 = new JLabel("No comprehension");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(20, 43, 124, 25);
		panelSurv2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Perfect Comprehension");
		lblNewLabel_6.setBounds(438, 43, 138, 25);
		panelSurv2.add(lblNewLabel_6);
		
		JLabel lblIEnjoyUsing = new JLabel("I enjoy using technological applications (apps):");
		lblIEnjoyUsing.setBounds(20, 99, 386, 14);
		panelSurv2.add(lblIEnjoyUsing);
		
		JLabel lblStronglyDisagree = new JLabel("Strongly disagree");
		lblStronglyDisagree.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStronglyDisagree.setBounds(20, 124, 124, 25);
		panelSurv2.add(lblStronglyDisagree);
		
		 sldEnjoyTech = new JSlider();
		sldEnjoyTech.setValue(-1);
		sldEnjoyTech.setSnapToTicks(true);
		sldEnjoyTech.setPaintTicks(true);
		sldEnjoyTech.setPaintLabels(true);
		sldEnjoyTech.setMinorTickSpacing(1);
		sldEnjoyTech.setMinimum(1);
		sldEnjoyTech.setMaximum(5);
		sldEnjoyTech.setMajorTickSpacing(1);
		sldEnjoyTech.setBounds(154, 124, 274, 45);
		panelSurv2.add(sldEnjoyTech);
		
		JLabel lblStronglyAgree = new JLabel("Strongly agree");
		lblStronglyAgree.setBounds(438, 124, 138, 25);
		panelSurv2.add(lblStronglyAgree);
		
		JLabel lblIAmGood = new JLabel("I am good at using technological applications (apps):");
		lblIAmGood.setBounds(20, 180, 386, 14);
		panelSurv2.add(lblIAmGood);
		
		sldUseTech = new JSlider();
		sldUseTech.setValue(-1);
		sldUseTech.setSnapToTicks(true);
		sldUseTech.setPaintTicks(true);
		sldUseTech.setPaintLabels(true);
		sldUseTech.setMinorTickSpacing(1);
		sldUseTech.setMinimum(1);
		sldUseTech.setMaximum(5);
		sldUseTech.setMajorTickSpacing(1);
		sldUseTech.setBounds(154, 205, 274, 45);
		panelSurv2.add(sldUseTech);
		
		JLabel lblHowOftenDo = new JLabel("My friends ask me for computer and technology advice:");
		lblHowOftenDo.setBounds(20, 261, 386, 14);
		panelSurv2.add(lblHowOftenDo);
		
		 sldFriendsTech = new JSlider();
		sldFriendsTech.setValue(-1);
		sldFriendsTech.setSnapToTicks(true);
		sldFriendsTech.setPaintTicks(true);
		sldFriendsTech.setPaintLabels(true);
		sldFriendsTech.setMinorTickSpacing(1);
		sldFriendsTech.setMinimum(1);
		sldFriendsTech.setMaximum(5);
		sldFriendsTech.setMajorTickSpacing(1);
		sldFriendsTech.setBounds(154, 286, 274, 45);
		panelSurv2.add(sldFriendsTech);
		
		JLabel label_1 = new JLabel("Strongly disagree");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(20, 205, 124, 25);
		panelSurv2.add(label_1);
		
		JLabel label_2 = new JLabel("Strongly disagree");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(20, 286, 124, 25);
		panelSurv2.add(label_2);
		
		JLabel label_3 = new JLabel("Strongly agree");
		label_3.setBounds(438, 205, 138, 25);
		panelSurv2.add(label_3);
		
		JLabel label_4 = new JLabel("Strongly agree");
		label_4.setBounds(438, 286, 138, 25);
		panelSurv2.add(label_4);
		
		JLabel label_5 = new JLabel("*WARNING* You cannot return to these answers.");
		label_5.setForeground(Color.RED);
		label_5.setBounds(80, 382, 277, 14);
		panelSurv2.add(label_5);
		
		btnPage3.setBounds(385, 378, 89, 23);
		panelSurv2.add(btnPage3);
		
		frame3.getContentPane().add(panelSurv3, "");
		panelSurv3.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Is there anything you enjoyed about the license agreement?");
		lblNewLabel_7.setBounds(10, 11, 349, 14);
		panelSurv3.add(lblNewLabel_7);
		
		txtEnjoyedLic = new JTextField();
		txtEnjoyedLic.setBounds(364, 8, 220, 20);
		panelSurv3.add(txtEnjoyedLic);
		txtEnjoyedLic.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("When you install reflexology, who are you entering an agreement with?");
		lblNewLabel_8.setBounds(10, 36, 412, 14);
		panelSurv3.add(lblNewLabel_8);
		
		String[] whoAgree = {"", "Don't know", "State of PA", "Penn State", "PA Court of Common Pleas", "PA District Court", "Reflexology Development Team"};
		cmbWhoAgree = new JComboBox(whoAgree);
		cmbWhoAgree.setBounds(432, 33, 152, 20);
		panelSurv3.add(cmbWhoAgree);
		
		JLabel lblTheLicenseAgreement = new JLabel("The license agreement applies to the following types of media:");
		lblTheLicenseAgreement.setBounds(10, 61, 368, 14);
		panelSurv3.add(lblTheLicenseAgreement);
		
		String[] mediaTypes = {"", "Don't know", "Software", "Digital Media Content", "Print Material", "Documentation", "All of the above"};
		cmbMediaTypes = new JComboBox(mediaTypes);
		cmbMediaTypes.setBounds(388, 61, 196, 20);
		panelSurv3.add(cmbMediaTypes);
		
		JLabel lblNewLabel_9 = new JLabel("Is it possible to purchase the Reflexology software? (explain below)");
		lblNewLabel_9.setBounds(10, 86, 566, 14);
		panelSurv3.add(lblNewLabel_9);
		
		txtPurchasing = new JTextField();
		txtPurchasing.setBounds(10, 111, 574, 20);
		panelSurv3.add(txtPurchasing);
		txtPurchasing.setColumns(10);
		
		JLabel lblTheLicenseAgreement_1 = new JLabel("The license agreement was visually appealing");
		lblTheLicenseAgreement_1.setBounds(20, 145, 386, 14);
		panelSurv3.add(lblTheLicenseAgreement_1);
		
		JLabel label_7 = new JLabel("Strongly disagree");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setBounds(20, 170, 124, 25);
		panelSurv3.add(label_7);
		
		 sldAesthetics = new JSlider();
		sldAesthetics.setValue(-1);
		sldAesthetics.setSnapToTicks(true);
		sldAesthetics.setPaintTicks(true);
		sldAesthetics.setPaintLabels(true);
		sldAesthetics.setMinorTickSpacing(1);
		sldAesthetics.setMinimum(1);
		sldAesthetics.setMaximum(5);
		sldAesthetics.setMajorTickSpacing(1);
		sldAesthetics.setBounds(154, 170, 274, 45);
		panelSurv3.add(sldAesthetics);
		
		JLabel label_8 = new JLabel("Strongly agree");
		label_8.setBounds(438, 170, 138, 25);
		panelSurv3.add(label_8);
		
		JLabel lblTheTextOf = new JLabel("This  license agreement was easy to understand");
		lblTheTextOf.setBounds(20, 226, 463, 14);
		panelSurv3.add(lblTheTextOf);
		
		JLabel label_10 = new JLabel("Strongly disagree");
		label_10.setHorizontalAlignment(SwingConstants.RIGHT);
		label_10.setBounds(20, 251, 124, 25);
		panelSurv3.add(label_10);
		
		 sldUnderstandLic = new JSlider();
		sldUnderstandLic.setValue(-1);
		sldUnderstandLic.setSnapToTicks(true);
		sldUnderstandLic.setPaintTicks(true);
		sldUnderstandLic.setPaintLabels(true);
		sldUnderstandLic.setMinorTickSpacing(1);
		sldUnderstandLic.setMinimum(1);
		sldUnderstandLic.setMaximum(5);
		sldUnderstandLic.setMajorTickSpacing(1);
		sldUnderstandLic.setBounds(154, 251, 274, 45);
		panelSurv3.add(sldUnderstandLic);
		
		JLabel label_11 = new JLabel("Strongly agree");
		label_11.setBounds(438, 251, 138, 25);
		panelSurv3.add(label_11);
		
		JLabel label_6 = new JLabel("Strongly disagree");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(20, 332, 124, 25);
		panelSurv3.add(label_6);
		
		 sldAnnoyed = new JSlider();
		sldAnnoyed.setValue(-1);
		sldAnnoyed.setSnapToTicks(true);
		sldAnnoyed.setPaintTicks(true);
		sldAnnoyed.setPaintLabels(true);
		sldAnnoyed.setMinorTickSpacing(1);
		sldAnnoyed.setMinimum(1);
		sldAnnoyed.setMaximum(5);
		sldAnnoyed.setMajorTickSpacing(1);
		sldAnnoyed.setBounds(154, 332, 274, 45);
		panelSurv3.add(sldAnnoyed);
		
		JLabel label_9 = new JLabel("Strongly agree");
		label_9.setBounds(438, 332, 138, 25);
		panelSurv3.add(label_9);
		
		JLabel lblframe3LicenseAgreement = new JLabel("Something about this license agreement annoyed me");
		lblframe3LicenseAgreement.setBounds(10, 304, 463, 14);
		panelSurv3.add(lblframe3LicenseAgreement);
		
		JLabel label_12 = new JLabel("*WARNING* You cannot return to these answers.");
		label_12.setForeground(Color.RED);
		label_12.setBounds(89, 404, 277, 14);
		panelSurv3.add(label_12);
		
		btnPage4.setBounds(394, 400, 89, 23);
		panelSurv3.add(btnPage4);
		
		frame3.getContentPane().add(panelSurv4, "");
		panelSurv4.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("According to the license agreement, what does Penn State have ");
		lblNewLabel_10.setBounds(10, 11, 537, 14);
		panelSurv4.add(lblNewLabel_10);
		
		JLabel lblTheRightTo = new JLabel("the right to download from your computer?");
		lblTheRightTo.setBounds(10, 26, 504, 14);
		panelSurv4.add(lblTheRightTo);
		
		txtPSUDownload = new JTextField();
		txtPSUDownload.setBounds(10, 48, 553, 20);
		panelSurv4.add(txtPSUDownload);
		txtPSUDownload.setColumns(10);
		
		JLabel lblHowManyCopies = new JLabel("How many copies of Reflexology can you make for archival purposes?");
		lblHowManyCopies.setBounds(10, 79, 438, 14);
		panelSurv4.add(lblHowManyCopies);
		
		String[] archCopies = {"", "Don't know", "0", "1", "3", "5", "Unlimited"};
		cmbArchCopies = new JComboBox(archCopies);
		cmbArchCopies.setBounds(458, 79, 105, 20);
		panelSurv4.add(cmbArchCopies);
		
		JLabel lblYouCanLend = new JLabel("What can you do if you want to sue about Reflexology?");
		lblYouCanLend.setBounds(10, 116, 323, 14);
		panelSurv4.add(lblYouCanLend);
		
		JLabel lblUnderWhatCircumstances = new JLabel("Under what circumstances can Penn State terminate your use of the software? ");
		lblUnderWhatCircumstances.setBounds(10, 184, 537, 14);
		panelSurv4.add(lblUnderWhatCircumstances);
		
		txtTerminate = new JTextField();
		txtTerminate.setBounds(10, 209, 553, 20);
		panelSurv4.add(txtTerminate);
		txtTerminate.setColumns(10);
		
		 sldTradKs = new JSlider();
		sldTradKs.setValue(-1);
		sldTradKs.setSnapToTicks(true);
		sldTradKs.setPaintTicks(true);
		sldTradKs.setPaintLabels(true);
		sldTradKs.setMinorTickSpacing(1);
		sldTradKs.setMinimum(1);
		sldTradKs.setMaximum(5);
		sldTradKs.setMajorTickSpacing(1);
		sldTradKs.setBounds(154, 268, 274, 45);
		panelSurv4.add(sldTradKs);
		
		JLabel label_13 = new JLabel("Strongly disagree");
		label_13.setHorizontalAlignment(SwingConstants.RIGHT);
		label_13.setBounds(20, 268, 124, 25);
		panelSurv4.add(label_13);
		
		JLabel label_14 = new JLabel("Strongly agree");
		label_14.setBounds(438, 268, 138, 25);
		panelSurv4.add(label_14);
		
		JLabel lblPeopleAreMore = new JLabel("People are more likely to read traditional contracts than software license agreemenets");
		lblPeopleAreMore.setBounds(10, 240, 553, 14);
		panelSurv4.add(lblPeopleAreMore);
		
		JLabel lblwarningframe3Button = new JLabel("*WARNING* this button will instantly end the experiment ---->");
		lblwarningframe3Button.setForeground(Color.RED);
		lblwarningframe3Button.setBounds(38, 400, 350, 14);
		panelSurv4.add(lblwarningframe3Button);
		
		btnDone.setBounds(398, 391, 89, 23);
		panelSurv4.add(btnDone);
		
		txtArbite = new JTextField();
		txtArbite.setColumns(10);
		txtArbite.setBounds(10, 141, 553, 20);
		panelSurv4.add(txtArbite);
		
	    ListenFor2Button lFor2Button = new ListenFor2Button();
	    ListenFor3Button lFor3Button = new ListenFor3Button();
	    ListenFor4Button lFor4Button = new ListenFor4Button();
	    ListenFor5Button lFor5Button = new ListenFor5Button();
	    btnPage2.addActionListener(lFor2Button);
	    btnPage3.addActionListener(lFor3Button);
	    btnPage4.addActionListener(lFor4Button);
	    btnDone.addActionListener(lFor5Button);

}

private JPanel contentPane;
private JTextField txtMajor, txtPrivacyConcerns;
private JTextField txtEnjoyedLic;
private JTextField txtPurchasing;
private JTextField txtPSUDownload;
private JTextField txtTerminate;
private JTextField txtArbite;
private JSpinner spnAge;
private JSlider sldComp, sldEnjoyTech, sldUseTech, sldFriendsTech, sldAesthetics, sldUnderstandLic, sldAnnoyed, sldTradKs;
private JComboBox cmbWarranty, cmbIPOwner, cmbDidRead, cmbClass, cmbNumGifts, cmdDidRead, cmbGender, cmbWhoAgree, cmbMediaTypes, cmbArchCopies;
String _panel1Str, _panel2Str, _panel3Str;

private class ListenForAcceptButton implements ActionListener{
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button1){
            Calendar ClCDateTime = Calendar.getInstance();
            System.out.println(ClCDateTime.getTimeInMillis() - _openTime);
            _closeTime = ClCDateTime.getTimeInMillis() - _openTime;
            //frame.getContentPane().remove(thePanel);
            //thePlacebo.addKeyListener(lForKeys);
            //frame.getContentPane().add(thePlacebo);

            //frame.repaint();
            moveBallTimer.start();
            frame.setVisible(false);
            frame2.setVisible(true);
            frame2.invalidate();
            frame2.repaint();

            thePlacebo.repaint();
            }
        }
    }


private class ListenForDeclineButton implements ActionListener{
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == button2){
            JOptionPane.showMessageDialog(Reflexology2.this, "You've declined the license agreement. DO NOT RESTART the program. Please go inform a researcher that you have declined the agreement.", "WARNING", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}


private class ListenForWindow implements WindowListener{

    public void windowActivated(WindowEvent e) {
            //textArea1.append("Window is active");

    }

    // if this.dispose() is called, this is called:
    public void windowClosed(WindowEvent arg0) {

    }

    // When a window is closed from a menu, this is called:
    public void windowClosing(WindowEvent arg0) {

    }

    // Called when the window is no longer the active window:
    public void windowDeactivated(WindowEvent arg0) {
        //textArea1.append("Window is NOT active");

    }

    // Window gone from minimized to normal state
    public void windowDeiconified(WindowEvent arg0) {
        //textArea1.append("Window is in normal state");

    }

    // Window has been minimized
    public void windowIconified(WindowEvent arg0) {
        //textArea1.append("Window is minimized");

    }

    // Called when the Window is originally created
    public void windowOpened(WindowEvent arg0) {
        //textArea1.append("Let there be Window!");
        Calendar OlCDateTime = Calendar.getInstance();
         _openTime = OlCDateTime.getTimeInMillis();
        //System.out.println(_openTime);

    }

}

private class MyAdjustmentListener implements AdjustmentListener {

    public void adjustmentValueChanged(AdjustmentEvent e) {
        // testLabel.setText(String.valueOf(scrollBar1.getVerticalScrollBar().getValue()));
        // max = 1296
    	if (scrollBar1.getVerticalScrollBar().getValue() >= 1290) {
    		button1.setEnabled(true);
    	}
    }

}


class TestPanel extends JPanel {
  public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);
        g2d.fill(ball);
       // System.out.println("Calling fill()");
      }
}

Random rand = new Random();


protected void moveBall() {
      //System.out.println("I'm in the moveBall() function!");
      int width = getWidth();
      int height = getHeight();
      int min, max, randomX, randomY;
      min = 0;
      max = 200;
      randomX = min + (int)(Math.random() * ((max - min)+1));
      randomY = min + (int)(Math.random() * ((max - min)+1));
      //System.out.println(randomX + ", " + randomY);

      Rectangle ballBounds = ball.getBounds();
//      //System.out.println(ballBounds.x + ", " + ballBounds.y);
//      if (ballBounds.x + randomX < 0) {
//          randomX = 200;
//      } else if (ballBounds.x + ballBounds.width + randomX > width) {
//          randomX = -200;
//      }
//      if (ballBounds.y + randomY < 0) {
//          randomY = 200;
//      } else if (ballBounds.y + ballBounds.height + randomY > height) {
//          randomY = -200;
//      } 

      ballBounds.x = randomX;
      ballBounds.y = randomY;
      _ballXpos = ballBounds.x;
      _ballYpos = ballBounds.y;
      ball.setFrame(ballBounds);
      thePlacebo.repaint();
    }



      public void start() {
        moveBallTimer.start();
      }

      public void stop() {
        moveBallTimer.stop();
      }

      private class ListenForMouse implements MouseListener{

            // Called when the mouse is clicked
            public void mouseClicked(MouseEvent e) {
                //System.out.println("Mouse Panel pos: " + e.getX() + " " + e.getY() + "\n");
                if (e.getX() >=_ballXpos && e.getX() <= _ballXpos + 25  && e.getY() >=_ballYpos && e.getY() <= _ballYpos + 25 ) {                 
                	moveBallTimer.stop();
                    frame2.setVisible(false);
                    frame3.setVisible(true);
                    frame3.invalidate();
                    frame3.repaint();
                }
           // System.out.println("{e.getX(): " + e.getX() + " / " + "_ballXpos: " + _ballXpos + " | " +  "{e.getY(): " + e.getY() + " / " + "_ballYpos: " + _ballYpos);

            }

            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }
                }
            //    System.out.println("e.getX(): " + e.getX() + " / " + "_ballXpos: " + _ballXpos);



            // Mouse over
            public void mouseEntered(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            // Mouse left the mouseover area:
            public void mouseExited(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            public void mouseReleased(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            private class ListenFor2Button implements ActionListener{
        	    public void actionPerformed(ActionEvent e){
        	        if (e.getSource() == btnPage2){
        	        	_panel1Str = String.valueOf(spnAge.getValue()) + "," + Integer.toString(cmbGender.getSelectedIndex()) + "," + Integer.toString(cmbClass.getSelectedIndex()) + "," + txtMajor.getText() + "," + Integer.toString(cmbDidRead.getSelectedIndex()) + "," + txtPrivacyConcerns.getText() +  "," + Integer.toString(cmbNumGifts.getSelectedIndex()) + "," + Integer.toString(cmbWarranty.getSelectedIndex()) + "," + Integer.toString(cmbIPOwner.getSelectedIndex()) + ",";
        	        	System.out.println(_panel1Str);
        	            panelSurv1.setVisible(false)	;
        	            panelSurv2.setVisible(true);
        	    		validate();
        	    		repaint();
        	        }
        	    }
        	}
        	
        	private class ListenFor3Button implements ActionListener{
        	    public void actionPerformed(ActionEvent e){
        	        if (e.getSource() == btnPage3){
        	        	_panel2Str =String.valueOf(sldComp.getValue()) + "," + String.valueOf(sldEnjoyTech.getValue()) + "," + String.valueOf(sldUseTech.getValue()) + "," +  String.valueOf(sldFriendsTech.getValue()) + ",";
        	        	System.out.println(_panel2Str);
        	            panelSurv2.setVisible(false)	;
        	            panelSurv3.setVisible(true);
        	    		validate();
        	    		repaint();
        	        }
        	    }
        	}
        	
        	private class ListenFor4Button implements ActionListener{
        	    public void actionPerformed(ActionEvent e){
        	        if (e.getSource() == btnPage4){
        	        	_panel3Str = txtEnjoyedLic.getText() + "," + Integer.toString(cmbWhoAgree.getSelectedIndex())  + "," + Integer.toString(cmbMediaTypes.getSelectedIndex()) + "," + txtPurchasing.getText() + "," + String.valueOf(sldAesthetics.getValue()) + "," + String.valueOf(sldUnderstandLic.getValue()) + "," + String.valueOf(sldAnnoyed.getValue()) + ",";
        	        	System.out.println(_panel3Str);
        	            panelSurv3.setVisible(false)	;
        	            panelSurv4.setVisible(true);
        	    		validate();
        	    		repaint();
        	        }
        	    }
        	}
        	
        	private class ListenFor5Button implements ActionListener{
        	    public void actionPerformed(ActionEvent e){
        	        if (e.getSource() == btnDone){
        	        	String theVersion = "2";
        	            Date dNow = new Date( );
        	            SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy  hh:mm:ss");
        	            String theReason = (String)JOptionPane.showInputDialog(null, "Why did you either read or not read the license agreement?", "Type answer here");
        	        	String commaOptions = _panel1Str + _panel2Str + _panel3Str + txtPSUDownload.getText() + "," + Integer.toString(cmbArchCopies.getSelectedIndex()) + "," +  txtArbite.getText() + "," + txtTerminate.getText() + "," + String.valueOf(sldTradKs.getValue()) + "," + ft.format(dNow) + "," + _closeTime + "," + theVersion + "," + theReason;
          	        	System.out.println(commaOptions);
        	        	BufferedWriter bw = null;
        	        	try {
        	        	    bw = new BufferedWriter(new FileWriter("participants.csv", true));
        	        	    bw.write(commaOptions);
        	        	    bw.newLine();
        	        	    bw.flush();
        	        	} catch (IOException ioe) {
        	        	    ioe.printStackTrace();
        	        	} finally { // always close the file
        	        	    if (bw != null) {
        	        	        try {
        	        	            bw.close();
        	        	        } catch (IOException ioe2) {
        	        	            // just ignore it
        	        	        }
        	        	    }
        	        	}
        	            JOptionPane.showMessageDialog(Reflexology2.this, "You have completed the experiment. Please see the researchers for debriefing.", "WARNING", JOptionPane.INFORMATION_MESSAGE);
        	        	System.exit(0);
        	    		validate();
        	    		repaint();
        	        }
        	    }
        	}

    }