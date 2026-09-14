package Player;
import SanmokuSystem.*;

public class Hashikara extends Player {
	int pos;
	
	@Override
	public void newgame(int firstplayer) {
		pos = 0;
		consoleClear();
	}

	@Override
	public int turn(boolean again) {
		if ( again == true ) {
			pos++;
		} else {
			consoleOutln("");
		}
		int hand = pos % (board_size * board_size); 
		consoleOut(hand+" ");
		return  hand;
	}

}
