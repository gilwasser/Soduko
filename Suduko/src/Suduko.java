import java.util.Scanner;

// This up Generate Soduko board

public class Suduko {
	static int sizeOfBoard = 9;
	static int[][] board = new int[sizeOfBoard][sizeOfBoard];
	static int many = 45;

	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		// initializing board with zeros
		init();

		// create sudoko board
		startGame();

		// making random squere to remove first
		int x = (int) Math.floor(Math.random() * 9);
		int y = (int) Math.floor(Math.random() * 9);
		// removing many numbers from board
		removeRand(x, y);

		// print board
		printBoard();

		
	}

	// removingnumbers from ranom space on the board
	
	public static boolean removeRand(int x, int y) {
		while (many != 0) {
			while (board[y][x] == 0) {
				x = (int) Math.floor(Math.random() * 9);
				y = (int) Math.floor(Math.random() * 9);
			}
			board[y][x] = 0;
			many--;
		}
		return true;
	}
	
	// initializing the baord to hold 0 on all squres
	public static void init() {
		for (int i = 0; i < sizeOfBoard; i++) {
			for (int j = 0; j < sizeOfBoard; j++) {			
				board[i][j] = 0;
			}
		}
	}
	
	// creating full sloved board
	
	public static void startGame() {
		// filling diagonal boxes
		for (int i = 0; i < sizeOfBoard; i += 3) {
			for (int j = 1; j < 10; j++) {
				randomPlace(i, j);
			}
		}
		slove(0, 0);
	}
	
	//find random place on the borad with 0 on it

	public static void randomPlace(int i, int number) {
		boolean state = false;
		int randX = 0;
		int randY = 0;
		while (!state) {
			randX = (int) Math.floor(Math.random() * 3 + i);
			randY = (int) Math.floor(Math.random() * 3 + i);

			if (board[randY][randX] == 0) state = true;
		}
		board[randY][randX] = number;
	}
	
	//slove the board with recursion

	public static boolean slove(int x, int y) {
		//stop condition 
		if (y == sizeOfBoard || x == sizeOfBoard) return true;
		//check for validation by the rules
		if (!validRow(x, y) || !validCol(x, y) || !validBox(x, y)) {
			board[y][x] = 0;
			return false;
		}
		// incrementing 
		while (y < sizeOfBoard && x < sizeOfBoard && board[y][x] != 0) {
			if (x == 8) {
				x = 0;
				y++;
			} else x++;
		}
		
		
		//stop condition
		if (y == sizeOfBoard || x == sizeOfBoard)return true;
		
		//calling the recursion if it return true the slove is done
		for (int i = 1; i <= sizeOfBoard; i++) {
			board[y][x] = i;
			if (!slove(x, y)) continue;
			else return true;
		}
		// if we get to these part we nned to go back 1 step backword
		board[y][x] = 0;
		return false;
	}
	
	//row validation
	public static boolean validRow(int x, int y) {
		for (int i = 0; i < sizeOfBoard; i++) {
			if (i == x) continue;
			if (board[y][i] == board[y][x])return false;
		}
		return true;
	}

	//column validation
	public static boolean validCol(int x, int y) {
		for (int i = 0; i < sizeOfBoard; i++) {
			if (i == y)continue;
			if (board[i][x] == board[y][x])return false;
		}
		return true;
	}

	//box validation
	public static boolean validBox(int x, int y) {
		for (int i = y - y % 3; i < y - y % 3 + 3; i++) {
			for (int j = x - x % 3; j < x - x % 3 + 3; j++) {
				if (j == x && i == y) continue;
				if (board[y][x] == board[i][j])return false;
			}
		}
		return true;
	}

	//printing board
	public static void printBoard() 
	{
		String print;
		System.out.println("\n\n");
		System.out.println("       1     2     3      4     5     6      7     8     9");
		for (int i = 0; i < sizeOfBoard; i++) {

			if (i % 3 == 0) {
				System.out.print( "     -------------------------------------------------------\n");
			}
			System.out.print(i+1);
			for (int j = 0; j < sizeOfBoard; j++) {
				if (board[i][j] == 0)print = " ";
				else print = Integer.toString(board[i][j]);
				if (j % 3 == 0) {
					System.out.print("  ||  " + print);
				}
				else {
					System.out.print("  |  " + print);
				}
			}
			System.out.print("  ||\n     -------------------------------------------------------\n");

		}

	}
}
