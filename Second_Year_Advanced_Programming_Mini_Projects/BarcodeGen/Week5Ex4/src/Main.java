import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.sourceforge.barbecue.*;

public class Main 
{

	public static void main(String[] args) 
	{
		Barcode bc = BarcodeSetup();
		GUISetup(bc);	
	}
	
	public static void GUISetup(Barcode bc)
	{
		JFrame s = new JFrame("Barcode Generator");
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = s.getContentPane();
		c.setLayout(new BorderLayout());
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		p1.setLayout(new FlowLayout());
		p2.setLayout(new FlowLayout());
		
		JTextField t = new JTextField("12345678");
		t.setPreferredSize(new Dimension(150, 24));
		JRadioButton rb1 = new JRadioButton("Code 128");
		JRadioButton rb2 = new JRadioButton("Code 39");
		JRadioButton rb3 = new JRadioButton("Codabar");
		ButtonGroup group = new ButtonGroup();
		group.add(rb1);
		group.add(rb2);
		group.add(rb3);
		rb1.setSelected(true);
		JButton b1 = new JButton("Generate");
		b1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				Barcode bc2;
				if (rb1.isSelected())
				{
					bc2 = CreateBarcode(t.getText(), "Code 128");
				}
				else if (rb2.isSelected())
				{
					bc2 = CreateBarcode(t.getText(), "Code 39");
				}
				else
				{
					bc2 = CreateBarcode(t.getText(), "Codabar");
				}
				
				if (bc2 != null)
				{
					p2.removeAll();
					p2.add(bc2);
					p2.repaint();
					c.revalidate();
				}
				
			}
		});
		
		p1.add(t);
		p1.add(rb1);
		p1.add(rb2);
		p1.add(rb3);
		p1.add(b1);
		
		p2.add(bc);
		
		c.add(p1, BorderLayout.NORTH);
		c.add(p2, BorderLayout.SOUTH);
		
		s.pack();
		s.setVisible(true);
	}
	
	public static Barcode BarcodeSetup()
	{
		try
		{
			Barcode bc = BarcodeFactory.createCode128("12345678");
			bc.setPreferredBarHeight(60);
			bc.setBarWidth(2);
			return bc;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static Barcode CreateBarcode(String text, String type)
	{
		if (!text.equals(""))
		{
			try
			{
				if (type.equals("Code 128"))
				{
					Barcode bc = BarcodeFactory.createCode128(text);
					bc.setPreferredBarHeight(60);
					bc.setBarWidth(2);
					return bc;
				}
				else if (type.equals("Code 39"))
				{
					Barcode bc = BarcodeFactory.createCode39(text, true);
					bc.setPreferredBarHeight(60);
					bc.setBarWidth(2);
					return bc;
				}
				else
				{
					Barcode bc = BarcodeFactory.createCodabar(text);
					bc.setPreferredBarHeight(60);
					bc.setBarWidth(2);
					return bc;
				}
			
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

}
