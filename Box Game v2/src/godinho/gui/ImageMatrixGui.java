package godinho.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.sun.javafx.scene.layout.region.Margins;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class ImageMatrixGui {

	private JFrame frame;
	private JPanel start;
	private JPanel menu;
	private JPanel heroes;
	private JPanel gameLevel;
	private JPanel panel;
	private JPanel levels;
	
	private static Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
	private static int buttonMargin = (int) screenDimension.getWidth()/30; 
	private static int buttonSize = (int) screenDimension.getWidth()/8;
	
	private static int NumberLevels = 20;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImageMatrixGui window = new ImageMatrixGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImageMatrixGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, (int) screenDimension.getWidth(), (int) screenDimension.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, "name_505157078968874");
		
//		start = new JPanel();
//		start.setBounds(0, 0, 434, 261);
//		layeredPane.add(start);
//		start.setLayout(new BorderLayout(0, 0));
		
//		JPanel panel_2 = new JPanel();
//		panel_2.setBounds(0, 0, 10, 10);
//		layeredPane.add(panel_2);
		
		menu = new JPanel();
		menu.setBounds(0, 0, (int) screenDimension.getWidth(), (int) screenDimension.getHeight()-60);
		layeredPane.add(menu);
		menu.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		menu.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		heroes = new JPanel();
		heroes.setBackground(new Color(0,0,0, 200));
		heroes.setLayout(new FlowLayout(FlowLayout.CENTER, buttonMargin, buttonMargin/3));
		JScrollPane heroesScroll = new JScrollPane(heroes);
		heroesScroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0,0));
		menu.add(heroesScroll, BorderLayout.SOUTH);
		
		for (int i=1; i<=NumberLevels; i++) {
			JButton btnNewButton = new JButton("");
			try {
				Image img = new ImageIcon("heroes/" + i + ".png").getImage();
				btnNewButton.setIcon(new ImageIcon(img.getScaledInstance(buttonSize-50, buttonSize-50, Image.SCALE_DEFAULT)));
			} catch (NullPointerException e) {
				
			}
			btnNewButton.setPreferredSize(new Dimension(buttonSize,buttonSize));
			btnNewButton.setBackground(new Color(0,0,0,0));
			heroes.add(btnNewButton);
		}
		
		levels = new JPanel();
		levels.setBackground(new Color(0,0,0, 200));
		levels.setLayout(new ModifiedFlowLayout(FlowLayout.CENTER, buttonMargin, buttonMargin));
		JScrollPane levelsScroll = new JScrollPane(levels);
		levelsScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0,0));
		menu.add(levelsScroll, BorderLayout.CENTER);
		
		for (int i=1; i<=NumberLevels; i++) {
			JButton btnNewButton = new JButton(Integer.toString(i));
			btnNewButton.setPreferredSize(new Dimension(buttonSize,buttonSize));
			btnNewButton.setFont(new Font("Arial Black", Font.BOLD,  56));
			btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					menu.setVisible(false);
					
				}
			});
			levels.add(btnNewButton);
		}
		if (NumberLevels%6!=0)
			for(int j=0; j<6-(NumberLevels%6); j++) {
				JButton btnNewButton = new JButton(Integer.toString(0));
				btnNewButton.setPreferredSize(new Dimension(buttonSize,buttonSize));
				btnNewButton.setFont(new Font("Arial Black", Font.BOLD,  56));
				levels.add(btnNewButton);
			}
		
		gameLevel = new JPanel();
		gameLevel.setBounds(0, 0, (int) screenDimension.getWidth(), (int) screenDimension.getHeight());
		layeredPane.add(gameLevel);
	}

	/**
	  * A modified version of FlowLayout that allows containers using this
	  * Layout to behave in a reasonable manner when placed inside a
	  * JScrollPane
	  * @author Babu Kalakrishnan
	  * Modifications by greearb and jzd
	  */

	 public class ModifiedFlowLayout extends FlowLayout {
	       public ModifiedFlowLayout() {
	              super();
	           }

	           public ModifiedFlowLayout(int align) {
	              super(align);
	           }
	       public ModifiedFlowLayout(int align, int hgap, int vgap) {
	          super(align, hgap, vgap);
	       }

	       public Dimension minimumLayoutSize(Container target) {
	          // Size of largest component, so we can resize it in
	          // either direction with something like a split-pane.
	          return computeMinSize(target);
	       }

	       public Dimension preferredLayoutSize(Container target) {
	          return computeSize(target);
	       }

	       private Dimension computeSize(Container target) {
	          synchronized (target.getTreeLock()) {
	             int hgap = getHgap();
	             int vgap = getVgap();
	             int w = target.getWidth();

	             // Let this behave like a regular FlowLayout (single row)
	             // if the container hasn't been assigned any size yet
	             if (w == 0) {
	                w = Integer.MAX_VALUE;
	             }

	             Insets insets = target.getInsets();
	             if (insets == null){
	                insets = new Insets(0, 0, 0, 0);
	             }
	             int reqdWidth = 0;

	             int maxwidth = w - (insets.left + insets.right + hgap * 2);
	             int n = target.getComponentCount();
	             int x = 0;
	             int y = insets.top + vgap; // FlowLayout starts by adding vgap, so do that here too.
	             int rowHeight = 0;

	             for (int i = 0; i < n; i++) {
	                Component c = target.getComponent(i);
	                if (c.isVisible()) {
	                   Dimension d = c.getPreferredSize();
	                   if ((x == 0) || ((x + d.width) <= maxwidth)) {
	                      // fits in current row.
	                      if (x > 0) {
	                         x += hgap;
	                      }
	                      x += d.width;
	                      rowHeight = Math.max(rowHeight, d.height);
	                   }
	                   else {
	                      // Start of new row
	                      x = d.width;
	                      y += vgap + rowHeight;
	                      rowHeight = d.height;
	                   }
	                   reqdWidth = Math.max(reqdWidth, x);
	                }
	             }
	             y += rowHeight;
	             y += insets.bottom;
	             return new Dimension(reqdWidth+insets.left+insets.right, y);
	          }
	       }

	       private Dimension computeMinSize(Container target) {
	          synchronized (target.getTreeLock()) {
	             int minx = Integer.MAX_VALUE;
	             int miny = Integer.MIN_VALUE;
	             boolean found_one = false;
	             int n = target.getComponentCount();

	             for (int i = 0; i < n; i++) {
	                Component c = target.getComponent(i);
	                if (c.isVisible()) {
	                   found_one = true;
	                   Dimension d = c.getPreferredSize();
	                   minx = Math.min(minx, d.width);
	                   miny = Math.min(miny, d.height);
	                }
	             }
	             if (found_one) {
	                return new Dimension(minx, miny);
	             }
	             return new Dimension(0, 0);
	          }
	       }

	    }
}


