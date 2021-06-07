package cont;
//Controller package that manages JOptionPane

import javax.swing.JOptionPane;

public class JOP {

	//msg method that creates a shortcut for a more efficient way of using showMessageDialog
	public static void msg(String string) {
		JOptionPane.showMessageDialog(null, string);
	}
	
	//in method that creates a shortcut for a more efficient way of using showInputDialog
	public static String in(String msg){
		return JOptionPane.showInputDialog(msg);
	}
	
		
}
