import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
public class DHMain extends javax.swing.JFrame
{    
	JFrame f;
	JFrame f1;
	JTextArea ipaddress;
	JTextArea location;
	JButton connect;
	JFileChooser jfc;
	JButton open;
	JFileChooser fileChooser;
	String fileName,fileName1;
	Socket s;
	Socket s1;
	JButton but;
	public DHMain() 
   {
	   
        initComponents();
       
	   //jDesktopPane1.add( new DHFrame());
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(610,455);
        setResizable(false);
    } 

   
    private void initComponents()
     {
		 
		 Font font = new Font("Verdana", Font.BOLD, 15);
	
	f=new JFrame("Transmit");
	f.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Rima Sasmal\\eclipse-workspace2-VideoSteganography\\Aud\\src\\Project3.png")));
	JLabel l1=new JLabel("-----Share a file-----");
	l1.setBounds(10,10,600,100);
	l1.setFont(new Font("Courier New", Font.BOLD, 20));
    f.add(l1);
	JLabel l2=new JLabel("1. Open a file");
	l2.setBounds(10,100,300,100);
	l2.setFont(new Font("Courier New", Font.BOLD, 20));
	f.add(l2);
	open=new JButton("Select a file to Send");
	open.setBounds(320,120,200,50);
	f.add(open);
	JLabel l4=new JLabel("2.IP address of receiver");
	l4.setBounds(10,150,300,150);
	l4.setFont(new Font("Courier New", Font.BOLD, 20));
	f.add(l4);
	ipaddress=new JTextArea();
	ipaddress.setBounds(320,190,200,50);
	f.add(ipaddress);
	ipaddress.setFont(font);
	connect=new JButton("Connect");
	connect.setBounds(200,270,100,50);
	f.add(connect);
	
	
	f.setSize(600,400);
	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	f.setResizable(false);
	f.setLayout(null);
	
	open.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			fileChooser=new JFileChooser();
			int returnVal = fileChooser.showOpenDialog((Component)e.getSource());
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        try {
		 fileName = file.toString();
			System.out.println(fileName);

        } catch (Exception ex) {
          System.out.println("problem accessing file"+file.getAbsolutePath());
        }
    } 
    else {
        System.out.println("File access cancelled by user.");
    }       
		}
	});
	
	
	f1=new JFrame("Receive");
	f1.setContentPane(new JLabel(new ImageIcon("C:\\Users\\Rima Sasmal\\eclipse-workspace2-VideoSteganography\\Aud\\src\\Project3.png")));
	JLabel lab=new JLabel("-----Receive a file-----");
	lab.setBounds(10,10,600,100);
	lab.setFont(new Font("Courier New", Font.BOLD, 20));
    f1.add(lab);
	JLabel l5=new JLabel("1.Enter the location");
	l5.setBounds(10,70,300,100);
	l5.setFont(new Font("Courier New", Font.BOLD, 20));
	f1.add(l5);
/*	location=new JTextArea();
	location.setBounds(300,100,250,40);
	location.setFont(font);
	f1.add(location);		*/
	JButton button=new JButton("select");
	button.setBounds(330,100,190,50);
	f1.add(button);
	
	
	button.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			fileChooser=new JFileChooser();
			int returnVal = fileChooser.showOpenDialog((Component)e.getSource());
    if (returnVal == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        try {
		 fileName1 = file.toString();
			System.out.println(fileName1);

        } catch (Exception ex) {
          System.out.println("problem accessing file"+file.getAbsolutePath());
        }
    } 
    else {
        System.out.println("File access cancelled by user.");
    }       
		}
	});
	
	
	
	
	
	
	JLabel l3=new JLabel("2.Connect");
	l3.setBounds(10,130,300,100);
	l3.setFont(new Font("Courier New", Font.BOLD, 20));
	f1.add(l3);
	startServer=new JButton("Connect");
	startServer.setBounds(330,160,190,50);
	f1.add(startServer);
	JLabel l8=new JLabel("3.Receive the file");
	l8.setBounds(10,200,300,100);
	l8.setFont(new Font("Courier New", Font.BOLD, 20));
	f1.add(l8);
	but=new JButton("Receive");
	but.setBounds(330,230,190,50);
	f1.add(but);
	
	
	f1.setSize(600,400);
	f1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	f1.setResizable(false);
	f1.setLayout(null);
	
	
	startServer.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				ServerSocket ss=new ServerSocket(1512);
				s=ss.accept();
				System.out.println("Client Connected "+s);
				
				JOptionPane.showMessageDialog(f,"Connection Successful");
				
			}
			catch(Exception e1)
			{
				System.out.println("in catch of start server");
				JOptionPane.showMessageDialog(f,"Error in Connection");
			}
		}
		
	});
	but.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e)
		{
			
			try{
		byte b[]= new byte [10000];
		InputStream is=s.getInputStream();
		FileOutputStream fs=new FileOutputStream(fileName1);
		is.read(b,0,b.length);
		fs.write(b,0,b.length);
		System.out.println("Successful"+fs+" "+is);
		JOptionPane.showMessageDialog(f,"File Received");
			}
			catch(Exception e1)
			{
				System.out.println("inside but.....");
			}
		}	
		
	});
	
	
	
	
	connect.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			//Socket s1;
			try
			{
			s1 = new Socket(ipaddress.getText(),1512);
			JOptionPane.showMessageDialog(f,"Connection successful");
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(f,"Error in Connection");
			}
			try
			{
				
				
				FileInputStream fs=new FileInputStream(fileName);
		byte b[]=new byte[10000];
		fs.read(b,0,b.length);
		OutputStream os=s1.getOutputStream();
		os.write(b,0,b.length);
				
			}
			catch(Exception e1)
			{
				System.out.println("Error");
			}
			
		}
		
	});
	
	
	
	
        
	jdp = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
	fileMenu1 = new javax.swing.JMenu();
        embedMenu = new javax.swing.JMenuItem();
        extractmenu = new javax.swing.JMenuItem();
        exitmenu = new javax.swing.JMenuItem();
	aboutMenu = new javax.swing.JMenuItem();
	trans=new javax.swing.JMenu();
	transmit=new javax.swing.JMenuItem();
	receive=new javax.swing.JMenuItem();
        piclabel=new javax.swing.JLabel(new javax.swing.ImageIcon("C:\\Users\\Rima Sasmal\\eclipse-workspace2-VideoSteganography\\Aud\\src\\Project1.png"));
	piclabel.setBounds(0,0,600,400);
	add(piclabel);
	setTitle("Secured Data Transmission");
        addWindowListener(new java.awt.event.WindowAdapter() 
	{
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        getContentPane().add(jdp, java.awt.BorderLayout.CENTER);

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");
	fileMenu1.setMnemonic('h');
	fileMenu1.setText("Help");
	trans.setMnemonic('S');
	trans.setText("Share");
        embedMenu.setMnemonic('m');
        embedMenu.setText("Embed");
        embedMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                embedMenuActionPerformed(evt);
            }
        });
        fileMenu.add(embedMenu);
        extractmenu.setMnemonic('t');
        extractmenu.setText("Extract");
        extractmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extractmenuActionPerformed(evt);
            }
        });

        fileMenu.add(extractmenu);
        exitmenu.setMnemonic('x');
        exitmenu.setText("Exit");
        exitmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            exitmenuActionPerformed(evt);
            }
        });
	fileMenu1.add(aboutMenu);
        aboutMenu.setMnemonic('a');
        aboutMenu.setText("About");
        aboutMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            aboutMenuActionPerformed(evt);
            }
        });
        fileMenu.add(exitmenu);
		transmit.setText("Transmit");
		transmit.setMnemonic('T');
		transmit.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				handleTransmission(e);
			}
		});
		trans.add(transmit);
		receive.setText("Receive");
		receive.setMnemonic('R');
		receive.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				handleReceiving(e);
			}
		});
		trans.add(receive);
        fileMenu1.add(aboutMenu);
	jMenuBar1.add(fileMenu);
	jMenuBar1.add(trans);
	jMenuBar1.add(fileMenu1);
	
        setJMenuBar(jMenuBar1);
        pack();
    }	
	
   
  private void exitmenuActionPerformed(java.awt.event.ActionEvent evt) 
   {
   	      	
	  System.exit(0);
    }
	
    private void extractmenuActionPerformed(java.awt.event.ActionEvent evt) 
  {    
        piclabel.setVisible(false);
        WizardFrame wf = new ExtractAction(this).getWizardFrame();
       jdp.add(wf);
     wf.moveToFront();
    }
	public void handleTransmission(java.awt.event.ActionEvent e)
	  {
		//  piclabel.setVisible(false);
		 f.setVisible(true);
		}
	public void handleReceiving(java.awt.event.ActionEvent e)
	  {
		  //piclabel.setVisible(false);
		 f1.setVisible(true);
		}
    private void embedMenuActionPerformed(java.awt.event.ActionEvent evt) 
  {
 
     System.out.println("Embed Action Selected..");
     
	piclabel.setVisible(false);
        WizardFrame wf = new EmbedAction(this).getWizardFrame();
        jdp.add(wf);
        wf.moveToFront();
    }

    private void aboutMenuActionPerformed(java.awt.event.ActionEvent evt) 
  {
  javax.swing.JOptionPane.showMessageDialog(this,"Secured Data Transmission\n\n"+"Created by : ADGITMians","About",javax.swing.JOptionPane.PLAIN_MESSAGE);
    }	
    private void exitForm(java.awt.event.WindowEvent evt) {

        System.exit(0);

    }

      public static void main(String args[])
	 {
           new DHMain().show();
     }
	 JInternalFrame jif;
	 JButton startServer;
     private javax.swing.JMenuItem exitmenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu fileMenu1;
    private javax.swing.JDesktopPane jdp;
    private javax.swing.JMenuItem extractmenu;
    private javax.swing.JMenuItem embedMenu;
    private javax.swing.JMenuItem aboutMenu;
    private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenu trans;
	private javax.swing.JMenuItem transmit;
	private javax.swing.JMenuItem receive;
    public javax.swing.JLabel piclabel;
    
    
}
