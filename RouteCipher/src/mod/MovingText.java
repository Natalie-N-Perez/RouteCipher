package mod;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

import cont.JOP;

import java.awt.event.*;
import javax.swing.Timer;

public class MovingText extends JFrame implements ActionListener
{
	
//Declaring and initializing variables needed to properly execute the code
JLabel label1;
JLabel label2;
private static int encodingFigure = -4;
private static int x = 0;
private static int y = 0;
private static int temp = 0;
private static int z = 0;
static char a = '$';

public MovingText()
{
//Sets up the main window
label1= new JLabel("            ROUTE CIPHER            ");
label1.setFont (new Font ("Monospaced Bold", 1 ,50));
label1.setBounds(0,10,400,100);//x axis, y axis, width, height 
add(label1);
Timer t = new Timer(100, this);
t.start();
}

//Makes it so that the text scrolls across the window
public void actionPerformed(ActionEvent e)
{
String oldText = label1.getText();
String newText= oldText.substring(1)+oldText.substring(0,1);
label1.setText(newText);
}

//encode method that encrypts the message that was entered
private static String encode(String s)
{
    char[] temp = new char[s.length()];
    for(int i = 0; i < s.length(); i++)
    {
        temp[i] = s.charAt(i); 
    }
    int[] numbers = new int[temp.length];
    for(int i = 0; i < numbers.length; i++)
    {
        numbers[i] = (int)temp[i];
        numbers[i] = numbers[i] + encodingFigure;
        temp[i] = (char)numbers[i];
    }
    String ss = "";
    for(int i = 0; i < temp.length; i++)
    {
        ss += temp[i];
    }
    return ss;
}

//decode method that decrypts an encrypted message
private static String decode(String s)
{
    char[] temp = new char[s.length()];
    for(int i = 0; i < s.length(); i++)
    {
        temp[i] = s.charAt(i); 
    }
    int[] numbers = new int[temp.length];
    for(int i = 0; i < numbers.length; i++)
    {
        numbers[i] = (int)temp[i];
        numbers[i] = numbers[i] - encodingFigure;
        temp[i] = (char)numbers[i];
    }
    String ss = "";
    for(int i = 0; i < temp.length; i++)
    {
    	char r;
    	r = temp[i];
    	if(r=='$')
    	{
    		ss+=" ";
    	}
    	else
    	{
        ss += temp[i];
    	}
    }
    return ss;
}


//main method
public static void main (String[] args)
{
	
//Places all the buttons and moving text on the main window
MovingText frame = new MovingText();
frame.setDefaultCloseOperation (EXIT_ON_CLOSE);
frame.setSize(400,500);
JButton e=new JButton("Encrypt");//creating instance of JButton  
e.setBounds(150,100,100, 40);//x axis, y axis, width, height  
JButton d=new JButton("Decrypt");//creating instance of JButton  
d.setBounds(150,150,100, 40);//x axis, y axis, width, height  
JButton exit=new JButton(new ImageIcon("vaporwave.png"));//creating instance of JButton


frame.add(e);//adding button in JFrame  
frame.add(d);//adding button in JFrame
frame.add(exit);//adding button in JFrame

frame.setVisible(true);

//Adds a button that when pressed, takes the user to the setup to encrypt their message
e.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        // do everything here...
    	String response = JOP.in("What's the message that you want to encrypt?\n\nps type exit if you want to close the program");
    	if (response.equalsIgnoreCase("exit"))
    		System.exit(0);
    	
    	//Encrypts the message and checks how many rows and columns would be needed in LetterBlock
    	//Adds A if the message does not fill all spots in the LetterBlocks
    	x = response.length();
    	y = (int) Math.sqrt(Math.sqrt(x));
    	response = encode(response);
    	if(x%(y*y) == 0)
    	{
    		z= x/(y*y);
    	}
    	if(x%(y*y) != 0)
    	{
    		z= (x/(y*y)) +1;
    		for(int i = (y*y)-(x%(y*y)); i>=0; i--)
    		{
    			response = response + "A";
    		}
    	}
    	JOP.msg("The message encrypted is: " + response); 
    	JOP.msg("There are " + y + " rows and columns in letterblock"); 
    		JOP.msg(z + " letterblocks were required to encrypt the message.");

    	x = 0;
    	y = 0;
    	z=0;
    	
    }
});

//Adds a button that when pressed, takes the user to the setup to decrypt their message
d.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        // do everything here...
    	String response = JOP.in("What's the message that you want to decrypt?\n\nps type exit if you want to close the program");
    	if (response.equalsIgnoreCase("exit"))
    		System.exit(0);
    	
    	//Decrypts the message, ignoring any A's
    	for(int i = 0; i<response.length();i++)
    	{
    		if(response.substring(i, i+1).equals("A")) 
    		{
    			response = response.substring(0,i);
    		}
    	}
    	response = decode(response);
    	JOP.msg("The message decrypted is: " + response);    	
    }
});

}
}