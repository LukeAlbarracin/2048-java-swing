import java.util.*;

public class GameGrid {
	private GameSquare[][] gameLoc = new GameSquare[4][4];
	private int gScore = 0;

	// private boolean gAgain = false;
	void startGrid() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// gameLoc[i][j] = GameSquare.getexPower();
				gameLoc[i][j] = new GameSquare(i, j, 0);
			}
		}
		randomSquare();
		randomSquare();
	}

	void printGrid() {
		for (int i = 0; i < 4; i++) {
			System.out.print("                       ");
			for (int j = 0; j < 4; j++) {
				// gameLoc[i][j] = GameSquare.getexPower();
				if (this.gameLoc[i][j].getExpower() == 0) {
					System.out.print("|0|");
				} else {
					System.out.print("|" + gameLoc[i][j].getExpower() + "|");
				}
			}
			System.out.println("");
		}
		// if (gAgain == true) {
		System.out.println("                       Gamescore: " + gScore);
		// gAgain = false;
		// } else {
		// gAgain = true;
		// }
		System.out.println("");

	}

	void randomSquare() {
		Random ranLoc = new Random();
		int lCheck = 0;
		int rand[] = { ranLoc.nextInt(4), ranLoc.nextInt(4) };
		if (this.gameLoc[rand[0]][rand[1]].getExpower() == 0) {
			this.gameLoc[rand[0]][rand[1]].setExpower(2);
		} else {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (this.gameLoc[i][j].getExpower() == 0) {// checks if there is no more spaces to fill
						lCheck = lCheck + 1;
					}
				}
			}
			if (lCheck > 0) {
				randomSquare();
			}

		}

	}

	void goDown() {
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j >= 0; j--) {
				if (this.gameLoc[j][i].getExpower() > 0) {
					if (j!= 3) {
					if (this.gameLoc[j][i].getExpower() == this.gameLoc[j + 1][i].getExpower()) {
						this.gameLoc[j + 1][i].setExpower(this.gameLoc[j][i].getExpower() * 2);
						gScore = Score(this.gameLoc[j + 1][i].getExpower());
						if (j == 0) {
							this.gameLoc[j][i].setExpower(0);
						} else {
							this.gameLoc[j][i].setExpower(this.gameLoc[j - 1][i].getExpower());
							this.gameLoc[j-1][i].setExpower(0);
						}
					} else {
						if (this.gameLoc[j + 1][i].getExpower() == 0) {
							this.gameLoc[j + 1][i].setExpower(this.gameLoc[j][i].getExpower());
							if (j == 0) {
								this.gameLoc[j][i].setExpower(0);
							} else {
								this.gameLoc[j][i].setExpower(this.gameLoc[j - 1][i].getExpower());
								this.gameLoc[j-1][i].setExpower(0);
					
							}
							j++;
							j++;
						} else {
						}
					}
				} else {
					// nothing
				}
			}
			}
		}
		// randomSquare();

		// keyPress();
	}

	void keyPress() {
		boolean kPress = false;
		Scanner input = new Scanner(System.in);
		String input2 = "";
		while (kPress == false) {
			System.out.println("Enter WASD:");
			input2 = input.next().toLowerCase();
			if (input2.equals("w")) {
				goUp();
				kPress = true;
			} else {
				if (input2.equals("a")) {
					goLeft();
					kPress = true;
				} else {
					if (input2.equals("s")) {
						goDown();
						kPress = true;
					} else {
						if (input2.equals("d")) {
							goRight();
							kPress = true;
						} else {
							System.out.println("Enter again please...");
						}
					}
				}

			}
		}

	}

	void goUp() {
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				if (j!= 0) {
				if (this.gameLoc[j][i].getExpower() > 0) {
					if (this.gameLoc[j][i].getExpower() == this.gameLoc[j - 1][i].getExpower()) {
						this.gameLoc[j - 1][i].setExpower(this.gameLoc[j][i].getExpower() * 2);
						gScore = Score(this.gameLoc[j - 1][i].getExpower());
						if (j == 3) {
							this.gameLoc[j][i].setExpower(0);
						} else {
							this.gameLoc[j][i].setExpower(this.gameLoc[j + 1][i].getExpower());
							this.gameLoc[j+1][i].setExpower(0);
						}
					} else {
						if (this.gameLoc[j - 1][i].getExpower() == 0) {
							this.gameLoc[j - 1][i].setExpower(this.gameLoc[j][i].getExpower());
							if (j == 3) {
								this.gameLoc[j][i].setExpower(0);
							} else {
								this.gameLoc[j][i].setExpower(this.gameLoc[j + 1][i].getExpower());
								this.gameLoc[j+1][i].setExpower(0);
							}
							j--;
							j--;
						} else {
							System.out.println("Ok");
						}
					}
				} else {
					// nothing
				}
			}
			}
		}
		// randomSquare();
		// printGrid();
		// keyPress();

	}

	void goRight() {
		for (int j = 0; j < 4; j++) {
			for (int i = 2; i >= 0; i--) {
				if (i != 3) {
				if (this.gameLoc[j][i].getExpower() > 0) {
					if (this.gameLoc[j][i].getExpower() == this.gameLoc[j][i + 1].getExpower()) {
						this.gameLoc[j][i + 1].setExpower(this.gameLoc[j][i].getExpower() * 2);
						gScore = Score(this.gameLoc[j][i + 1].getExpower());
						if (i == 0) {
							this.gameLoc[j][i].setExpower(0);
						} else {
							this.gameLoc[j][i].setExpower(this.gameLoc[j][i - 1].getExpower());
							this.gameLoc[j][i-1].setExpower(0);
						}
					} else {
						if (this.gameLoc[j][i + 1].getExpower() == 0) {
							this.gameLoc[j][i + 1].setExpower(this.gameLoc[j][i].getExpower());
							if (i == 0) {
								this.gameLoc[j][i].setExpower(0);
							} else {
								this.gameLoc[j][i].setExpower(this.gameLoc[j][i - 1].getExpower());
								this.gameLoc[j][i-1].setExpower(0);
							}
							i++;
							i++;
						} else {
						}
					}
				} else {
					// nothing
				}
			}
			}
		}
		/// randomSquare();
		/// printGrid();
		/// keyPress();
	}

	void goLeft() {
		for (int j = 0; j < 4; j++) {
			for (int i = 1; i < 4; i++) {
				if (i != 0) {
				if (this.gameLoc[j][i].getExpower() > 0) {
					if (this.gameLoc[j][i].getExpower() == this.gameLoc[j][i - 1].getExpower()) {
						this.gameLoc[j][i - 1].setExpower(this.gameLoc[j][i].getExpower() * 2);
						gScore = Score(this.gameLoc[j][i - 1].getExpower());
						if (i == 3) {
							this.gameLoc[j][i].setExpower(0);
						} else {
							this.gameLoc[j][i].setExpower(this.gameLoc[j][i + 1].getExpower());
							this.gameLoc[j][i+1].setExpower(0);
						}
					} else {
						if (this.gameLoc[j][i - 1].getExpower() == 0) {
							this.gameLoc[j][i - 1].setExpower(this.gameLoc[j][i].getExpower());
							if (i == 3) {
								this.gameLoc[j][i].setExpower(0);
							} else {
								this.gameLoc[j][i].setExpower(this.gameLoc[j][i + 1].getExpower());
								this.gameLoc[j][i+1].setExpower(0);
							}
							i--;
							i--;
						} else {
						}
					}
				} else {
					// nothing
				}
			}
			}
		}
		/// randomSquare();
		/// printGrid();
		/// keyPress();
	}

	int Score(int pts) {
		pts = gScore + pts;
		return pts;
	}

	int[][] returnG() {
		int[][] arrayz = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				arrayz[i][j] = gameLoc[i][j].getExpower();
			}
		}
		return arrayz;
	}
}