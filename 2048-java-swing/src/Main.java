import java.awt.GridLayout;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.color.*;

public class Main {

  public static void main(String[] args) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    /*
     * NAA.
     * This is procedural and imperative approach
     * https://stackoverflow.com/questions/26092495/jframe-setting-the-row-and-column-in-a-gridlayout
    JFrame frame = new JFrame("GridLayout Test");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new GridLayout(3, 2));
    frame.add(new JButton("Button 1"));
    frame.add(new JButton("Button 2"));
    frame.add(new JButton("Button 3"));
    frame.add(new JButton("Button 4"));
    frame.add(new JButton("Button 5"));
    frame.add(new JButton("Button 6"));
    frame.add(new JButton("Button 7"));
    frame.add(new JButton("Button 8"));
    frame.pack();
    frame.setVisible(true);
    */
    
    /*
     * Let's try "wrapping" JFrame into a class and using a more object-oriented approach.
     * Learning objectives:
     * - Encapsulation
     * e.g., Use board to keep its own implementation details
     * - Inheritance
     * e.g., inherit traits from parent "super" class like JFrame
     * - Assigning behaviors
     * e.g., allow board to have behaviors
     */
    Board board = new Board(4,4);
    // stay open until close JFrame
  }
  
}

class Board extends JFrame {
	private static final long serialVersionUID = 1L;
	private int _nrows;
	private int _ncols;
	private JButton[][] _buttons;
	private JTextField _textField;
	private GameGrid _gameGrid;
	private int[][] values;
	
	public Board(int nrows, int ncols) {
	  // NAA.
	  // 1.  Note how using a naming convention, we can avoid the verbose "this."
	  // and save some typing
		//initializeGameGrid();
		initializeJFrame(nrows, ncols);
		handleEvent(null, initializeGameGrid());
		
	}
	
	private void initializeJFrame(int nrows, int ncols) {
		_nrows = nrows;
		_ncols = ncols;
		
	    setTitle("2048");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new GridLayout(nrows, ncols));
	    
	    ImageIcon icon = new ImageIcon("images/button_background.png");
	    
	    _buttons = new JButton[_nrows][_ncols];
	    for (int i = 0; i < nrows; i++) {
	    	for (int j = 0; j < ncols; j++) {
	    		//_buttons[i][j] = new JButton(String.format("%d", values[i][j]));
	    		_buttons[i][j] = new JButton(String.format("%d,%d", i,j));
	    		_buttons[i][j].setIcon(icon);
	    		_buttons[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
	    		_buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
	    		add(_buttons[i][j]);
	    	}
	    }
	    setResizable(false);
	    setSize(1000, 1000);
	    pack();
	    setVisible(true);
	    setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	    //add(new JTextField());
	    KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
	    manager.addKeyEventDispatcher(new MyDispatcher(this));
	}
	
	private int[][] initializeGameGrid() {
		_gameGrid = new GameGrid();
		_gameGrid.startGrid();
		int[][] values =_gameGrid.returnG();
		return values;
	}
	
	void handleUp(String s) {
		_gameGrid.goUp();
		_gameGrid.randomSquare();
		int[][] values = _gameGrid.returnG();
		handleEvent(s, values);
	}
	
	void handleDown(String s) {
		_gameGrid.goDown();
		_gameGrid.randomSquare();
		int[][] values = _gameGrid.returnG();
		handleEvent(s, values);
	}
	
	void handleLeft(String s) {
		_gameGrid.goLeft();
		_gameGrid.randomSquare();
		int[][] values = _gameGrid.returnG();
		handleEvent(s, values);
	}
	
	void handleRight(String s) {
		_gameGrid.goRight();
		_gameGrid.randomSquare();
		int [][] values = _gameGrid.returnG();
		handleEvent(s, values);
	}
	
	public void handleEvent(String s, int[][] values) {
	    for (int i = 0; i < _nrows; i++) {
	    	for (int j = 0; j < _ncols; j++) {
	    		// transform matrices here
	    		//String text = "";
	    		//if (values[i][j] != 0) {
	    		//	String.format("%d", values[i][j]);
	    		//}
	    		String text = (values[i][j] != 0) ? String.format("%d",values[i][j]): ""; 
	    		_buttons[i][j].setText(text);
	    		setColor(i, j, values);
	    	}
	    this.repaint();
	}
	}
	private void setColor(int x, int y, int[][] values) {
		int gColor = values[x][y];
		switch (gColor) {
			case 2 : _buttons[x][y].setBackground(Color.RED);
				break;
			
			case 4 : _buttons[x][y].setBackground(Color.CYAN);
				break;
			
			case 8 : _buttons[x][y].setBackground(Color.green);
				break;
			
			case 16 : _buttons[x][y].setBackground(Color.MAGENTA);
				break;
			
			case 32 : _buttons[x][y].setBackground(Color.YELLOW);
				break;
			
			case 64 :  _buttons[x][y].setBackground(Color.ORANGE);
				break;
				
			case 128 : _buttons[x][y].setBackground(Color.BLACK);
			break;
			
			default : _buttons[x][y].setBackground(Color.white);
				break;
			
		}
	}	
}
//	private class GameSquareAdapter {
//		GameSquare _gameSquare;
//		public GameSquareAdapter(GameSquare gameSquare) {
//			_gameSquare = gameSquare;
//		}
//		public int getI() {
//			return _gameSquare.getRow();
//		}
//		public int getJ() {
//			return _gameSquare.getCol();
//		}
//		public int getValue() {
//			return _gameSquare.getExpower();
//		}
//	}
	
	// NAA.
	// 1.  Need a "helper" class to capture keyboard events. 
	// https://stackoverflow.com/questions/286727/unresponsive-keylistener-for-jframe
	class MyDispatcher implements KeyEventDispatcher {
		private Board _board;
		public MyDispatcher(Board board) {
			_board = board;
		}
       @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
         	   keyPressed(e);     	
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
            	keyReleased(e);
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
            	keyTyped(e);
            }
            return false;
        }
       
		private void keyPressed(KeyEvent e) {
		}
		
		private void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			
			switch (key) {
				case KeyEvent.VK_W: // up
					System.out.println("W pressed.");
					_board.handleUp("W" );
					break;
				case KeyEvent.VK_S: // down
					System.out.println("S pressed.");
					_board.handleDown("S");
					break;
				case KeyEvent.VK_A: // left
					System.out.println("A pressed.");
					_board.handleLeft("A");
					break;
				case KeyEvent.VK_D: // right
					System.out.println("D pressed.");
					_board.handleRight("D");
					break;
				default:
			}		
		}
		
		private void keyTyped(KeyEvent e) {
		}
	}
	
