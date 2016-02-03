package asianoptiondissertation;

/**
 * <p>Title: MSc Financial Market with Information Systems</p>
 *
 * <p>Description: Asian Option Pricer</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Orajava Consultancy Ltd</p>
 *
 * @author Plamen Stilyianov
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.BorderFactory;

//import java.awt.BorderLayout;

public class AsianOptionFrame extends JDialog {
    private JPanel topPanel = new JPanel();

    // Import algorithms
    private ArithMonteCarloGeoContVar monteModel;
    private GeometricModels geoModel;
    private GenBlackScholesModels bsModel;
    private ArithmeticModels artModel;


    // Menue bar
    private JMenuBar jMenuBar1 = new JMenuBar();
    private JMenu fileMenu = new JMenu();
    private JMenuItem exitItem = new JMenuItem();
    private JMenu helpMenu = new JMenu();
    private JMenuItem aboutItem = new JMenuItem();

    //Data Input
    TitledBorder inputsTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)), "Data Input");
    private JPanel inputsPanel = new JPanel();
    private JPanel spotPanel = new JPanel();
    private JLabel spotLabel = new JLabel("Spot Price");
    private JTextField spotTextField = new JTextField("4493");
    private JPanel strikePanel = new JPanel();
    private JLabel strikeLabel = new JLabel("Strike Price");
    private JTextField strikeTextField = new JTextField("4493");
    private JPanel yieldPanel = new JPanel();
    private JLabel yieldLabel = new JLabel("Dividend Yield");
    private JTextField yieldTextField = new JTextField("0.87");
    private JPanel ratePanel = new JPanel();
    private JLabel rateLabel = new JLabel("Risk Free Rate (%)");
    private JTextField rateTextField = new JTextField("4.314");
    private JPanel volPanel = new JPanel();
    private JLabel volLabel = new JLabel("Volatility (%)");
    private JTextField volTextField = new JTextField("21.097");
    private JPanel matPanel = new JPanel();
    private JLabel matLabel = new JLabel("Maturity (Yrs)");
    private JTextField matTextField = new JTextField("0.24658");

    Border buttonBorder = new TitledBorder(BorderFactory.createEtchedBorder(Color.
            white, new Color(134, 134, 134)), "");

    // Standard Vanilla Model
    TitledBorder vanillaTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)),
            "Standard Vanilla Model");
    private JPanel vanillaPanel = new JPanel();
    // Black Option Price
    TitledBorder balckTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)), "Option Price");
    private JPanel blackPanel = new JPanel();
    private JPanel blackPanelAll = new JPanel();
    private JLabel blackLabel = new JLabel("Black & Scholes");
    private JTextField blackTextField = new JTextField();
    // Hedge Sensitivities
    TitledBorder bsHedgeTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)),
            "Hedge Sensitivities");
    private JPanel bsHedgePanel = new JPanel();
    private JPanel deltaBsPanel = new JPanel();
    private JLabel deltaBsLabel = new JLabel("Delta");
    private JTextField deltaBsTextField = new JTextField();
    private JPanel gammaBsPanel = new JPanel();
    private JLabel gammaBsLabel = new JLabel("Gamma");
    private JTextField gammaBsTextField = new JTextField();
    private JPanel thetaBsPanel = new JPanel();
    private JLabel thetaBsLabel = new JLabel("Theta");
    private JTextField thetaBsTextField = new JTextField();
    private JPanel vegaBsPanel = new JPanel();
    private JLabel vegaBsLabel = new JLabel("Vega");
    private JTextField vegaBsTextField = new JTextField();
    private JPanel rhoBsPanel = new JPanel();
    private JLabel rhoBsLabel = new JLabel("Rho");
    private JTextField rhoBsTextField = new JTextField();
    private FlowLayout spotFlowLayout = new FlowLayout();
    private FlowLayout strikeflowLayout = new FlowLayout();
    private FlowLayout yieldFlowLayout = new FlowLayout();
    private FlowLayout volFlowLayout = new FlowLayout();
    private FlowLayout rateFlowLayout = new FlowLayout();
    private FlowLayout matFlowLayout = new FlowLayout();
    private FlowLayout deltaFlowLayout = new FlowLayout();
    private FlowLayout gammaFlowLayout = new FlowLayout();
    private FlowLayout thetaFlowLayout = new FlowLayout();
    private FlowLayout vegaFlowLayout = new FlowLayout();
    private FlowLayout rhoFlowLayout = new FlowLayout();
    private FlowLayout bottomFlowLayout = new FlowLayout();
    private FlowLayout blackAllFlowLayout = new FlowLayout();

    FlowLayout inputsFlowLayout = new FlowLayout();
    FlowLayout bsHedgeFlowLayout = new FlowLayout();
    FlowLayout topFlowLayout = new FlowLayout();
    FlowLayout vanillaFlowLayout = new FlowLayout();
    FlowLayout blackFlowLayout = new FlowLayout();

    // Bottom Panel
    TitledBorder arthModelTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)),
            "Arithmetic Model");
    TitledBorder arthOptionPriceTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)), "Option Price");
    TitledBorder arthHedgeTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)),
            "Hedge Sensitivities");

    TitledBorder geoModelTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)),
            "Geometric Model");
    TitledBorder geoOptionPriceTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)), "Option Price");
    TitledBorder geoHedgeTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)),
            "Hedge Sensitivities");

    TitledBorder controlVarTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)),
            "Arithmetic Model with Geometric Variate");
    TitledBorder mcOptionPriceTitledBorder = new TitledBorder(BorderFactory.
            createEtchedBorder(Color.white, new Color(142, 142, 142)), "Option Price");

    private JPanel bottomPanel = new JPanel();
    private JPanel arithModelPanel = new JPanel();
    FlowLayout arithModelFlowLayout = new FlowLayout();
    JPanel arthOptionPricePanel = new JPanel();
    JPanel turnbullPanel = new JPanel();
    JLabel turnbullLabel = new JLabel();
    JTextField turnbullTextField = new JTextField();
    JPanel gammaArthPanel = new JPanel();
    JLabel gammaArthLabel = new JLabel("Delta");
    JTextField gammaArthTextField = new JTextField();
    JPanel arthHedgePanel = new JPanel();
    JPanel deltaArthPanel = new JPanel();
    JLabel deltaArthLabel = new JLabel("Gamma");
    JTextField deltaArthTextField = new JTextField();
    JPanel thetaArthPanel = new JPanel();
    JLabel thetaArthLabel = new JLabel("Theta");
    JTextField thetaArthTextField = new JTextField();
    JPanel vegaArthPanel = new JPanel();
    JLabel vegaArthLabel = new JLabel("Vega");
    JTextField vegaArthTextField = new JTextField();
    JPanel rhoArthPanel = new JPanel();
    JLabel rhoArthLabel = new JLabel("Rho");
    JTextField rhoArthTextField = new JTextField();
    JPanel levyPanel = new JPanel();
    JLabel levyLabel = new JLabel();
    JTextField levyTextField = new JTextField();
    BorderLayout frameBorderLayout = new BorderLayout();
    FlowLayout arthOptionFlowLayout = new FlowLayout();
    FlowLayout levyFlowLayout = new FlowLayout();
    FlowLayout turnbullFlowLayout = new FlowLayout();
    FlowLayout arthHedgeFlowLayout = new FlowLayout();
    FlowLayout deltaArthFlowLayout = new FlowLayout();
    FlowLayout gammaArthFlowLayout = new FlowLayout();
    FlowLayout thetaArthFlowLayout = new FlowLayout();
    FlowLayout vegaArthFlowLayout = new FlowLayout();
    FlowLayout rhoArthFlowLayout = new FlowLayout();
    JPanel geoModelPanel = new JPanel();
    JPanel geoOptionPricePanel = new JPanel();
    JPanel geoKemnaPanel = new JPanel();
    JLabel geoKenmaLabel = new JLabel();
    JTextField geoKemnaTextField = new JTextField();
    JPanel geoCurranPanel = new JPanel();
    JLabel geoCurranLabel = new JLabel();
    JTextField geoCurranTextField = new JTextField();
    JPanel geoHedgePanel = new JPanel();
    JPanel geoDeltaPanel = new JPanel();
    JLabel geodeltaLabel = new JLabel("Delta");
    JTextField geoDeltaTextField = new JTextField();
    JPanel geoGammaPanel = new JPanel();
    JLabel geoGammaLabel = new JLabel("Gamma");
    JTextField geoGammaTextField = new JTextField();
    JPanel geoThetaPanel = new JPanel();
    JLabel geoThetaLabel = new JLabel("Theta");
    JTextField geoThetaTextField = new JTextField();
    JPanel geoVegaPanel = new JPanel();
    JLabel geoVegaLabel = new JLabel("Vega");
    JTextField geoVegaTextField = new JTextField();
    JPanel geoRhoPanel = new JPanel();
    JLabel geoRhoLabel = new JLabel("Rho");
    JTextField geoRhoTextField = new JTextField();
    FlowLayout geoModelFlowLayout = new FlowLayout();
    FlowLayout geoOptionPriceFlowLayout = new FlowLayout();
    FlowLayout geoKemnaFlowLayout = new FlowLayout();
    FlowLayout geoCurranFlowLayout = new FlowLayout();
    FlowLayout geoHedgeFlowLayout = new FlowLayout();
    FlowLayout geoDeltaFlowLayout = new FlowLayout();
    FlowLayout geoGammaFlowLayout = new FlowLayout();
    FlowLayout geoThetaFlowLayout = new FlowLayout();
    FlowLayout geoVegaFlowLayout = new FlowLayout();
    FlowLayout geoRhoFlowLayout = new FlowLayout();
    JPanel controlVarPanel = new JPanel();
    JPanel mcOptionPricePanel = new JPanel();
    JPanel monteCarloPanel = new JPanel();
    JLabel monteCarloLabel = new JLabel();
    JTextField monteCarloTextField = new JTextField();
    FlowLayout controlVarFlowLayout = new FlowLayout();
    FlowLayout mcOptionPriceFlowLayout = new FlowLayout();
    FlowLayout monteCarloFlowLayout = new FlowLayout();
    JPanel buttonPanel = new JPanel();
    JPanel runExitPanel = new JPanel();
    FlowLayout buttonFlowLayout = new FlowLayout();
    JButton runButton = new JButton();
    JButton clearButton = new JButton();
    JButton exitButton = new JButton();
    FlowLayout runFlowLayout = new FlowLayout();

    public AsianOptionFrame() {
        this(null, "", false);
    }

    /**
     * @param modal
     * @param title
     * @param parent
     */
    public AsianOptionFrame(Frame parent, String title, boolean modal) {
        super(parent, title, modal);
        try {
            jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void jbInit() throws Exception {
        this.setSize(new Dimension(950, 630));
        this.getContentPane().setLayout(frameBorderLayout);
        this.setTitle("European Asian Call Option Calculator");
        this.setJMenuBar(jMenuBar1);
        spotLabel.setOpaque(true);
        strikeLabel.setOpaque(true);
        rateLabel.setOpaque(true);
        yieldLabel.setOpaque(true);
        volLabel.setOpaque(true);
        matLabel.setOpaque(true);
        blackLabel.setOpaque(true);
        deltaBsLabel.setOpaque(true);
        gammaBsLabel.setOpaque(true);
        thetaBsLabel.setOpaque(true);
        vegaBsLabel.setOpaque(true);
        rhoBsLabel.setOpaque(true);
        turnbullLabel.setOpaque(true);
        levyLabel.setOpaque(true);
        deltaArthLabel.setOpaque(true);
        gammaArthLabel.setOpaque(true);
        thetaArthLabel.setOpaque(true);
        vegaArthLabel.setOpaque(true);
        rhoArthLabel.setOpaque(true);
        geoKenmaLabel.setOpaque(true);
        geoCurranLabel.setOpaque(true);
        geodeltaLabel.setOpaque(true);
        geoGammaLabel.setOpaque(true);
        geoThetaLabel.setOpaque(true);
        geoVegaLabel.setOpaque(true);
        geoRhoLabel.setOpaque(true);
        monteCarloLabel.setOpaque(true);

        jMenuBar1.add(fileMenu);
        jMenuBar1.add(helpMenu);
        fileMenu.setMnemonic('F');
        fileMenu.setText("File");
        exitItem.setText("Exit");
        exitItem.setMnemonic('X');
        fileMenu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        Action actionAbout = new AbstractAction("About",
                new ImageIcon("About.gif")) {
            public void actionPerformed(ActionEvent e) {
                AboutBox dlg = new AboutBox(AsianOptionFrame.this);
                dlg.setVisible(true);
            }
        };
        helpMenu.setMnemonic('H');
        helpMenu.setText("Help");
        aboutItem.setMnemonic('A');
        //aboutItem.setText("About");
        aboutItem = helpMenu.add(actionAbout);
        helpMenu.add(aboutItem);


        arthOptionFlowLayout.setVgap(0);
        arthHedgeFlowLayout.setVgap(0);
        arithModelFlowLayout.setVgap(0);
        arthOptionPricePanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        arithModelPanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        vanillaFlowLayout.setHgap(20);
        geoModelPanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geoModelPanel.setBorder(geoModelTitledBorder);
        geoModelPanel.setPreferredSize(new Dimension(290, 330));
        geoModelPanel.setLayout(geoModelFlowLayout);
        geoOptionPricePanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geoOptionPricePanel.setBorder(geoOptionPriceTitledBorder);
        geoOptionPricePanel.setPreferredSize(new Dimension(270, 100));
        geoOptionPricePanel.setLayout(geoOptionPriceFlowLayout);
        geoKemnaPanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geoKemnaPanel.setPreferredSize(new Dimension(250, 30));
        geoKemnaPanel.setLayout(geoKemnaFlowLayout);
        geoKenmaLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geoKenmaLabel.setPreferredSize(new Dimension(125, 25));
        geoKenmaLabel.setToolTipText("");
        geoKenmaLabel.setText("Kemna & Vorst");
        geoKemnaTextField.setPreferredSize(new Dimension(120, 25));
        geoKemnaTextField.setEditable(false);
        geoCurranPanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geoCurranPanel.setPreferredSize(new Dimension(250, 30));
        geoCurranPanel.setLayout(geoCurranFlowLayout);
        geoCurranLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geoCurranLabel.setPreferredSize(new Dimension(125, 25));
        geoCurranLabel.setText("Curran");
        geoCurranTextField.setPreferredSize(new Dimension(120, 25));
        geoCurranTextField.setEditable(false);
        geoHedgePanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geoHedgePanel.setBorder(geoHedgeTitledBorder);
        geoHedgePanel.setPreferredSize(new Dimension(270, 190));
        geoHedgePanel.setLayout(geoHedgeFlowLayout);
        geoDeltaPanel.setPreferredSize(new Dimension(250, 30));
        geoDeltaPanel.setLayout(geoDeltaFlowLayout);
        geodeltaLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geodeltaLabel.setPreferredSize(new Dimension(120, 25));
        geodeltaLabel.setText("Delta");
        geoDeltaTextField.setPreferredSize(new Dimension(120, 25));
        geoDeltaTextField.setEditable(false);
        geoGammaPanel.setPreferredSize(new Dimension(250, 30));
        geoGammaPanel.setLayout(geoGammaFlowLayout);
        geoGammaLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geoGammaLabel.setPreferredSize(new Dimension(120, 25));
        geoGammaLabel.setText("Gamma");
        geoGammaTextField.setPreferredSize(new Dimension(120, 25));
        geoGammaTextField.setEditable(false);
        geoThetaPanel.setPreferredSize(new Dimension(250, 30));
        geoThetaPanel.setLayout(geoThetaFlowLayout);
        geoThetaLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geoThetaLabel.setPreferredSize(new Dimension(120, 25));
        geoThetaTextField.setPreferredSize(new Dimension(120, 25));
        geoThetaTextField.setEditable(false);
        geoVegaPanel.setPreferredSize(new Dimension(250, 30));
        geoVegaPanel.setLayout(geoVegaFlowLayout);
        geoVegaLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geoVegaLabel.setPreferredSize(new Dimension(120, 25));
        geoVegaTextField.setPreferredSize(new Dimension(120, 25));
        geoVegaTextField.setEditable(false);
        geoRhoPanel.setPreferredSize(new Dimension(250, 30));
        geoRhoPanel.setLayout(geoRhoFlowLayout);
        geoRhoLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        geoRhoLabel.setPreferredSize(new Dimension(120, 25));
        geoRhoTextField.setPreferredSize(new Dimension(120, 25));
        geoRhoTextField.setEditable(false);
        geoModelFlowLayout.setAlignment(FlowLayout.LEFT);
        geoModelFlowLayout.setVgap(0);
        geoKemnaFlowLayout.setHgap(0);
        geoCurranFlowLayout.setHgap(0);
        geoOptionPriceFlowLayout.setHgap(0);
        geoOptionPriceFlowLayout.setVgap(0);
        geoHedgeFlowLayout.setAlignment(FlowLayout.LEFT);
        geoHedgeFlowLayout.setVgap(0);
        vanillaPanel.setMinimumSize(new Dimension(450, 95));
        controlVarPanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        controlVarPanel.setBorder(controlVarTitledBorder);
        controlVarPanel.setPreferredSize(new Dimension(270, 115));
        controlVarPanel.setLayout(controlVarFlowLayout);
        mcOptionPricePanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        mcOptionPricePanel.setBorder(mcOptionPriceTitledBorder);
        mcOptionPricePanel.setPreferredSize(new Dimension(250, 75));
        mcOptionPricePanel.setLayout(mcOptionPriceFlowLayout);
        monteCarloPanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        monteCarloPanel.setPreferredSize(new Dimension(230, 30));
        monteCarloPanel.setLayout(monteCarloFlowLayout);
        monteCarloLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        monteCarloLabel.setPreferredSize(new Dimension(90, 25));
        monteCarloLabel.setText("Monte Carlo");
        monteCarloTextField.setPreferredSize(new Dimension(120, 25));
        monteCarloTextField.setEditable(false);
        controlVarFlowLayout.setAlignment(FlowLayout.LEFT);
        controlVarFlowLayout.setVgap(0);
        mcOptionPriceFlowLayout.setHgap(0);
        mcOptionPriceFlowLayout.setVgap(0);
        buttonPanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        buttonPanel.setBorder(null);
        buttonPanel.setPreferredSize(new Dimension(290, 330));
        buttonPanel.setLayout(buttonFlowLayout);
        runExitPanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        runExitPanel.setBorder(null);
        runExitPanel.setPreferredSize(new Dimension(250, 210));
        runExitPanel.setLayout(runFlowLayout);
        buttonFlowLayout.setHgap(0);
        buttonFlowLayout.setVgap(0);
        bottomPanel.setBorder(BorderFactory.createEtchedBorder());
        topPanel.setBorder(BorderFactory.createEtchedBorder());
        blackFlowLayout.setAlignment(FlowLayout.LEFT);
        blackAllFlowLayout.setAlignment(FlowLayout.LEFT);
        blackTextField.setEditable(false);
        runButton.setText("Run");
        clearButton.setText("Clear");
        exitButton.setText("Exit");
        runFlowLayout.setVgap(100);
        thetaBsTextField.setEditable(false);
        deltaBsTextField.setEditable(false);
        gammaBsTextField.setEditable(false);
        vegaBsTextField.setEditable(false);
        rhoBsTextField.setEditable(false);
        rhoArthTextField.setEditable(false);
        vegaArthTextField.setEditable(false);
        thetaArthTextField.setEditable(false);
        gammaArthTextField.setEditable(false);
        deltaArthTextField.setEditable(false);
        levyTextField.setEditable(false);
        turnbullTextField.setEditable(false);
        this.getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);
        this.getContentPane().add(bottomPanel, java.awt.BorderLayout.CENTER);

        /* ================= Top Panel ============================  */
        topPanel.setLayout(topFlowLayout);
        topPanel.setPreferredSize(new Dimension(950, 275));

        inputsPanel.setFont(new Font("Verdana", 0, 11));
        inputsPanel.setAlignmentX((float) 0.0);
        inputsPanel.setAlignmentY((float) 0.0);
        inputsPanel.setPreferredSize(new Dimension(290, 260));
        spotPanel.setLayout(spotFlowLayout);
        spotPanel.setPreferredSize(new Dimension(250, 30));
        spotLabel.setPreferredSize(new Dimension(120, 25));
        spotLabel.setFont(new Font("Verdana", 0, 11));
        spotTextField.setPreferredSize(new Dimension(120, 25));
        strikePanel.setLayout(strikeflowLayout);
        strikePanel.setPreferredSize(new Dimension(250, 30));
        strikeLabel.setPreferredSize(new Dimension(120, 25));
        strikeLabel.setFont(new Font("Verdana", 0, 11));
        strikeTextField.setPreferredSize(new Dimension(120, 25));
        yieldPanel.setLayout(yieldFlowLayout);
        yieldPanel.setPreferredSize(new Dimension(250, 30));
        yieldLabel.setPreferredSize(new Dimension(120, 25));
        yieldLabel.setFont(new Font("Verdana", 0, 11));
        yieldTextField.setPreferredSize(new Dimension(120, 25));
        ratePanel.setLayout(rateFlowLayout);
        ratePanel.setPreferredSize(new Dimension(250, 30));
        rateLabel.setPreferredSize(new Dimension(120, 25));
        rateLabel.setFont(new Font("Verdana", 0, 11));
        rateTextField.setPreferredSize(new Dimension(120, 25));
        volPanel.setLayout(volFlowLayout);
        volPanel.setPreferredSize(new Dimension(250, 30));
        volLabel.setPreferredSize(new Dimension(120, 25));
        volLabel.setFont(new Font("Verdana", 0, 11));
        volTextField.setPreferredSize(new Dimension(120, 25));
        matPanel.setLayout(matFlowLayout);
        matPanel.setPreferredSize(new Dimension(250, 30));
        matLabel.setPreferredSize(new Dimension(120, 25));
        matLabel.setFont(new Font("Verdana", 0, 11));
        matTextField.setPreferredSize(new Dimension(120, 25));

        vanillaPanel.setLayout(vanillaFlowLayout);
        vanillaPanel.setFont(new Font("Verdana", 0, 11));
        vanillaPanel.setPreferredSize(new Dimension(590, 260));

        blackPanel.setLayout(blackFlowLayout);
        blackPanel.setPreferredSize(new Dimension(270, 220));
        blackPanelAll.setFont(new Font("Verdana", 0, 11));
        blackPanelAll.setPreferredSize(new Dimension(270, 75));
        blackPanelAll.setLayout(blackAllFlowLayout);
        blackLabel.setPreferredSize(new Dimension(110, 25));
        blackLabel.setFont(new Font("Verdana", 0, 11));
        blackTextField.setPreferredSize(new Dimension(120, 25));
        bsHedgePanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));

        bsHedgePanel.setPreferredSize(new Dimension(260, 220));
        deltaBsPanel.setLayout(deltaFlowLayout);
        deltaBsPanel.setPreferredSize(new Dimension(230, 30));
        deltaBsLabel.setPreferredSize(new Dimension(100, 25));
        deltaBsLabel.setFont(new Font("Verdana", 0, 11));
        deltaBsTextField.setPreferredSize(new Dimension(120, 25));
        gammaBsPanel.setLayout(gammaFlowLayout);
        gammaBsPanel.setPreferredSize(new Dimension(230, 30));
        gammaBsLabel.setPreferredSize(new Dimension(100, 25));
        gammaBsLabel.setFont(new Font("Verdana", 0, 11));
        gammaBsTextField.setPreferredSize(new Dimension(120, 25));
        thetaBsPanel.setLayout(thetaFlowLayout);
        thetaBsPanel.setPreferredSize(new Dimension(230, 30));
        thetaBsLabel.setPreferredSize(new Dimension(100, 25));
        thetaBsLabel.setFont(new Font("Verdana", 0, 11));
        thetaBsTextField.setPreferredSize(new Dimension(120, 25));
        vegaBsPanel.setLayout(vegaFlowLayout);
        vegaBsPanel.setPreferredSize(new Dimension(230, 30));
        vegaBsLabel.setPreferredSize(new Dimension(100, 25));
        vegaBsLabel.setFont(new Font("Verdana", 0, 11));
        vegaBsTextField.setPreferredSize(new Dimension(120, 25));
        rhoBsPanel.setLayout(rhoFlowLayout);
        rhoBsPanel.setPreferredSize(new Dimension(230, 30));
        rhoBsLabel.setPreferredSize(new Dimension(100, 25));
        rhoBsLabel.setFont(new Font("Verdana", 0, 11));
        rhoBsTextField.setPreferredSize(new Dimension(120, 25));

        spotFlowLayout.setAlignment(FlowLayout.LEFT);
        strikeflowLayout.setAlignment(FlowLayout.LEFT);
        yieldFlowLayout.setAlignment(FlowLayout.LEFT);
        rateFlowLayout.setAlignment(FlowLayout.LEFT);
        volFlowLayout.setAlignment(FlowLayout.LEFT);
        matFlowLayout.setAlignment(FlowLayout.LEFT);
        vanillaFlowLayout.setAlignment(FlowLayout.LEFT);
        vanillaFlowLayout.setVgap(0);
        blackFlowLayout.setHgap(0);
        blackFlowLayout.setVgap(0);
        topFlowLayout.setAlignment(FlowLayout.LEFT);
        topFlowLayout.setHgap(20);
        inputsPanel.add(spotPanel);
        spotPanel.add(spotLabel);
        spotPanel.add(spotTextField);
        inputsPanel.add(strikePanel);
        strikePanel.add(strikeLabel);
        strikePanel.add(strikeTextField);
        inputsPanel.add(ratePanel);
        yieldPanel.add(yieldLabel);
        yieldPanel.add(yieldTextField);
        inputsPanel.add(yieldPanel);
        ratePanel.add(rateLabel);
        ratePanel.add(rateTextField);
        inputsPanel.add(volPanel);
        volPanel.add(volLabel);
        volPanel.add(volTextField);
        inputsPanel.add(matPanel);
        matPanel.add(matLabel);
        matPanel.add(matTextField);
        topPanel.add(inputsPanel);
        topPanel.add(vanillaPanel);
        inputsPanel.setLayout(inputsFlowLayout);
        inputsPanel.setBorder(inputsTitledBorder);

        //Standard Vanilla Model
        vanillaPanel.setBorder(vanillaTitledBorder); //Option Price
        blackPanelAll.setBorder(balckTitledBorder);
        blackPanelAll.add(blackLabel);
        blackPanelAll.add(blackTextField);
        vanillaPanel.add(blackPanel);
        blackPanel.add(blackPanelAll);
        vanillaPanel.add(bsHedgePanel);
        blackPanel.add(Box.createVerticalGlue());

        //Hedge Sensitivities
        bsHedgePanel.setBorder(bsHedgeTitledBorder);
        bsHedgePanel.setLayout(bsHedgeFlowLayout);
        deltaBsPanel.add(deltaBsLabel);
        deltaBsPanel.add(deltaBsTextField);
        bsHedgePanel.add(deltaBsPanel);
        bsHedgePanel.add(gammaBsPanel);
        bsHedgePanel.add(thetaBsPanel);
        gammaBsPanel.add(gammaBsLabel);
        gammaBsPanel.add(gammaBsTextField);
        thetaBsPanel.add(thetaBsLabel);
        thetaBsPanel.add(thetaBsTextField);
        bsHedgePanel.add(vegaBsPanel);
        vegaBsPanel.add(vegaBsLabel);
        vegaBsPanel.add(vegaBsTextField);
        bsHedgePanel.add(rhoBsPanel);
        rhoBsPanel.add(rhoBsLabel);
        rhoBsPanel.add(rhoBsTextField);

        /* ================= Bottom Panel ============================  */
        // Bottom Panel
        bottomPanel.setLayout(bottomFlowLayout);
        bottomPanel.setPreferredSize(new Dimension(950, 350));
        bottomFlowLayout.setAlignment(FlowLayout.LEFT);
        bottomFlowLayout.setHgap(20);
        arithModelPanel.setLayout(arithModelFlowLayout);
        arithModelPanel.setBorder(arthModelTitledBorder);
        arithModelPanel.setPreferredSize(new Dimension(290, 330));
        arthOptionPricePanel.setBorder(arthOptionPriceTitledBorder);
        arthOptionPricePanel.setPreferredSize(new Dimension(270, 100));
        arthOptionPricePanel.setLayout(arthOptionFlowLayout);
        turnbullPanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        turnbullPanel.setPreferredSize(new Dimension(250, 30));
        turnbullPanel.setLayout(turnbullFlowLayout);
        turnbullLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        turnbullLabel.setPreferredSize(new Dimension(130, 25));
        turnbullLabel.setToolTipText("");
        turnbullLabel.setText("Turnbull & Wakeman");
        turnbullTextField.setPreferredSize(new Dimension(120, 25));
        arthHedgePanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        arthHedgePanel.setBorder(arthHedgeTitledBorder);
        arthHedgePanel.setPreferredSize(new Dimension(270, 190));
        arthHedgePanel.setLayout(arthHedgeFlowLayout);
        deltaArthPanel.setPreferredSize(new Dimension(250, 30));
        deltaArthPanel.setLayout(deltaArthFlowLayout);
        deltaArthLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        deltaArthLabel.setPreferredSize(new Dimension(120, 25));
        deltaArthLabel.setText("Delta");
        deltaArthTextField.setPreferredSize(new Dimension(120, 25));
        gammaArthPanel.setPreferredSize(new Dimension(250, 30));
        gammaArthPanel.setLayout(gammaArthFlowLayout);
        gammaArthLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        gammaArthLabel.setPreferredSize(new Dimension(120, 25));
        gammaArthLabel.setText("Gamma");
        gammaArthTextField.setPreferredSize(new Dimension(120, 25));
        thetaArthPanel.setPreferredSize(new Dimension(250, 30));
        thetaArthPanel.setLayout(thetaArthFlowLayout);
        thetaArthLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        thetaArthLabel.setPreferredSize(new Dimension(120, 25));
        thetaArthTextField.setPreferredSize(new Dimension(120, 25));
        vegaArthPanel.setPreferredSize(new Dimension(250, 30));
        vegaArthPanel.setLayout(vegaArthFlowLayout);
        vegaArthLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        vegaArthLabel.setPreferredSize(new Dimension(120, 25));
        vegaArthTextField.setPreferredSize(new Dimension(120, 25));
        rhoArthPanel.setPreferredSize(new Dimension(250, 30));
        rhoArthPanel.setLayout(rhoArthFlowLayout);
        rhoArthLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        rhoArthLabel.setPreferredSize(new Dimension(120, 25));
        rhoArthTextField.setPreferredSize(new Dimension(120, 25));
        levyPanel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        levyPanel.setPreferredSize(new Dimension(250, 30));
        levyPanel.setLayout(levyFlowLayout);
        levyLabel.setFont(new java.awt.Font("Verdana", Font.PLAIN, 11));
        levyLabel.setPreferredSize(new Dimension(130, 25));
        levyLabel.setText("Levy");
        levyTextField.setPreferredSize(new Dimension(120, 25));
        arithModelFlowLayout.setAlignment(FlowLayout.LEFT);
        arthHedgeFlowLayout.setAlignment(FlowLayout.LEFT);
        arthOptionFlowLayout.setHgap(0);
        turnbullFlowLayout.setHgap(0);
        levyFlowLayout.setAlignment(FlowLayout.LEFT);
        levyFlowLayout.setHgap(0);
        deltaArthFlowLayout.setAlignment(FlowLayout.LEFT);
        gammaArthFlowLayout.setAlignment(FlowLayout.LEFT);
        thetaArthFlowLayout.setAlignment(FlowLayout.LEFT);
        vegaArthFlowLayout.setAlignment(FlowLayout.LEFT);
        rhoArthFlowLayout.setAlignment(FlowLayout.LEFT);
        bottomPanel.add(arithModelPanel);
        bottomPanel.add(geoModelPanel);
        bottomPanel.add(buttonPanel);
        geoRhoPanel.add(geoRhoLabel);
        geoRhoPanel.add(geoRhoTextField);
        geoVegaPanel.add(geoVegaLabel);
        geoVegaPanel.add(geoVegaTextField);
        geoHedgePanel.add(geoDeltaPanel);
        geoHedgePanel.add(geoGammaPanel);
        geoHedgePanel.add(geoThetaPanel);
        geoHedgePanel.add(geoVegaPanel);
        geoHedgePanel.add(geoRhoPanel);
        geoThetaPanel.add(geoThetaLabel);
        geoThetaPanel.add(geoThetaTextField);
        geoGammaPanel.add(geoGammaLabel);
        geoGammaPanel.add(geoGammaTextField);
        geoDeltaPanel.add(geodeltaLabel);
        geoDeltaPanel.add(geoDeltaTextField);
        geoCurranPanel.add(geoCurranLabel);
        geoCurranPanel.add(geoCurranTextField);
        geoOptionPricePanel.add(geoKemnaPanel);
        geoOptionPricePanel.add(geoCurranPanel);
        geoModelPanel.add(geoOptionPricePanel);
        geoModelPanel.add(geoHedgePanel);
        geoKemnaPanel.add(geoKenmaLabel);
        geoKemnaPanel.add(geoKemnaTextField);
        rhoArthPanel.add(rhoArthLabel);
        rhoArthPanel.add(rhoArthTextField);
        vegaArthPanel.add(vegaArthLabel);
        vegaArthPanel.add(vegaArthTextField);
        arthHedgePanel.add(deltaArthPanel);
        arthHedgePanel.add(gammaArthPanel);
        arthHedgePanel.add(thetaArthPanel);
        arthHedgePanel.add(vegaArthPanel);
        arthHedgePanel.add(rhoArthPanel);
        thetaArthPanel.add(thetaArthLabel);
        thetaArthPanel.add(thetaArthTextField);
        gammaArthPanel.add(gammaArthLabel);
        gammaArthPanel.add(gammaArthTextField);
        deltaArthPanel.add(deltaArthLabel);
        deltaArthPanel.add(deltaArthTextField);
        levyPanel.add(levyLabel);
        levyPanel.add(levyTextField);
        arithModelPanel.add(arthOptionPricePanel);
        arithModelPanel.add(arthHedgePanel);
        turnbullPanel.add(turnbullLabel);
        turnbullPanel.add(turnbullTextField);
        arthOptionPricePanel.add(turnbullPanel);
        arthOptionPricePanel.add(levyPanel);
        monteCarloPanel.add(monteCarloLabel);
        monteCarloPanel.add(monteCarloTextField);
        buttonPanel.add(controlVarPanel);
        controlVarPanel.add(mcOptionPricePanel);
        mcOptionPricePanel.add(monteCarloPanel);
        buttonPanel.add(runExitPanel, null);
        runExitPanel.add(runButton);
        runExitPanel.add(clearButton);
        runExitPanel.add(exitButton);

        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                Double spPrice = new Double(spotTextField.getText());
                Double stPrice = new Double(strikeTextField.getText());
                Double rate = new Double(rateTextField.getText());
                Double div = new Double(yieldTextField.getText());
                Double vol = new Double(volTextField.getText());
                Double yrs = new Double(matTextField.getText());

                monteModel = new ArithMonteCarloGeoContVar(spPrice.doubleValue(), stPrice.doubleValue(), rate.doubleValue() / 100, div.doubleValue() / 100, vol.doubleValue() / 100, yrs.doubleValue());
                geoModel = new GeometricModels( spPrice.doubleValue(), stPrice.doubleValue(),rate.doubleValue() / 100, div.doubleValue() / 100, vol.doubleValue() / 100, yrs.doubleValue());
                bsModel = new GenBlackScholesModels( spPrice.doubleValue(),stPrice.doubleValue(), rate.doubleValue() / 100, div.doubleValue() / 100, vol.doubleValue() / 100, yrs.doubleValue());
                artModel = new ArithmeticModels(spPrice.doubleValue(), stPrice.doubleValue(), rate.doubleValue() / 100, div.doubleValue() / 100, vol.doubleValue() / 100, yrs.doubleValue());

                //Black & Schles
                blackTextField.setText(String.valueOf(bsModel.valueBlackScholes).substring(0, 15));
                blackTextField.setForeground(Color.red);
                deltaBsTextField.setText(String.valueOf(bsModel.delta).substring(0, 15));
                gammaBsTextField.setText(String.valueOf(bsModel.gamma).substring(0, 15));
                thetaBsTextField.setText(String.valueOf(bsModel.theta).substring(0, 15));
                vegaBsTextField.setText(String.valueOf(bsModel.vega).substring(0, 15));
                rhoBsTextField.setText(String.valueOf(bsModel.rho).substring(0, 15));
                //Arithmetic Model
                turnbullTextField.setText(String.valueOf(artModel.valueTurnbullWakeman).substring(0, 15));
                turnbullTextField.setForeground(Color.red);
                levyTextField.setText(String.valueOf(artModel.valueLevy).substring(0, 15));
                levyTextField.setForeground(Color.red);
                deltaArthTextField.setText(String.valueOf(artModel.delta).substring(0, 15));
                gammaArthTextField.setText(String.valueOf(artModel.gamma).substring(0, 15));
                thetaArthTextField.setText(String.valueOf(artModel.theta).substring(0, 15));
                vegaArthTextField.setText(String.valueOf(artModel.vega).substring(0, 15));
                rhoArthTextField.setText(String.valueOf(artModel.rho).substring(0, 15));
                //Geometric Model
                geoKemnaTextField.setText(String.valueOf(geoModel.valueKemnaVorst).substring(0, 15));
                geoKemnaTextField.setForeground(Color.red);
                geoCurranTextField.setText(String.valueOf(geoModel.valueCurran).substring(0, 15));
                geoCurranTextField.setForeground(Color.red);
                geoDeltaTextField.setText(String.valueOf(geoModel.delta).substring(0, 15));
                geoGammaTextField.setText(String.valueOf(geoModel.gamma).substring(0, 15));
                geoThetaTextField.setText(String.valueOf(geoModel.theta).substring(0, 15));
                geoVegaTextField.setText(String.valueOf(geoModel.vega).substring(0, 15));
                geoRhoTextField.setText(String.valueOf(geoModel.rho).substring(0, 15));
                //Monte Carlo
                monteCarloTextField.setText(String.valueOf(monteModel.valueMonteCarlo).substring(0, 15));
                monteCarloTextField.setForeground(Color.red);

            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Data Input
                spotTextField.setText("");
                strikeTextField.setText("");
                yieldTextField.setText("");
                rateTextField.setText("");
                volTextField.setText("");
                matTextField.setText("");
                //Black & Schles
                blackTextField.setText("");
                deltaBsTextField.setText("");
                gammaBsTextField.setText("");
                thetaBsTextField.setText("");
                vegaBsTextField.setText("");
                rhoBsTextField.setText("");
                //Arithmetic Model
                turnbullTextField.setText("");
                levyTextField.setText("");
                deltaArthTextField.setText("");
                gammaArthTextField.setText("");
                thetaArthTextField.setText("");
                vegaArthTextField.setText("");
                rhoArthTextField.setText("");
                //Geometric Model
                geoKemnaTextField.setText("");
                geoCurranTextField.setText("");
                geoDeltaTextField.setText("");
                geoGammaTextField.setText("");
                geoThetaTextField.setText("");
                geoVegaTextField.setText("");
                geoRhoTextField.setText("");
                //Monte Carlo
                monteCarloTextField.setText("");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        AsianOptionFrame dialog = new AsianOptionFrame();
        dialog.pack();
        dialog.setVisible(true);
        // System.exit(0);
    }

    class AboutBox extends JDialog {

        public AboutBox(AsianOptionFrame owner) {
            super(owner, "About", true);
            this.setSize(new Dimension(300, 200));
            this.setBackground(super.getBackground());
            JLabel lbl = new JLabel(new ImageIcon("AsianCalculator.gif"));
            JPanel p = new JPanel();
            Border b1 = new BevelBorder(BevelBorder.LOWERED);
            Border b2 = new EmptyBorder(5, 5, 5, 5);
            lbl.setBorder(new CompoundBorder(b1, b2));
            p.add(lbl);
            getContentPane().add(p, BorderLayout.WEST);

            String message = "Asian Optian Calculator application\n" +
                             "MSc Financial Markets with Information Systems \n" +
                             "(c) P.Stilyianov 2004-2005";
            JTextArea txt = new JTextArea(message);
            txt.setBorder(new EmptyBorder(5, 10, 5, 10));
            txt.setFont(new Font("Helvetica", Font.BOLD, 12));
            txt.setEditable(false);
            txt.setBackground(owner.getBackground());
            p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            p.add(txt);

            message = "JVM version " + System.getProperty("java.version") +
                    " by " + System.getProperty("java.vendor");
            txt = new JTextArea(message);
            txt.setBorder(new EmptyBorder(5, 10, 5, 10));
            txt.setFont(new Font("Arial", Font.PLAIN, 12));
            txt.setEditable(false);
            txt.setLineWrap(true);
            txt.setWrapStyleWord(true);
            txt.setBackground(getBackground());
            p.add(txt);

            getContentPane().add(p, BorderLayout.CENTER);

            final JButton btOK = new JButton("OK");
            ActionListener lst = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            btOK.addActionListener(lst);
            p = new JPanel();
            p.add(btOK);
            getRootPane().setDefaultButton(btOK);
            getRootPane().registerKeyboardAction(lst,
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                    JComponent.WHEN_IN_FOCUSED_WINDOW);
            getContentPane().add(p, BorderLayout.SOUTH);

            // That will transer focus from first component upon dialog's show
            WindowListener wl = new WindowAdapter() {
                public void windowOpened(WindowEvent e) {
                    btOK.requestFocus();
                }
            };
            addWindowListener(wl);

            pack();
            setResizable(false);
            setLocationRelativeTo(owner);
        }
    }
}
