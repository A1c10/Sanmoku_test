package Player;
import SanmokuSystem.*;
import java.util.Random;

public class Attacker2 extends Player {
    	Random random = new Random();
	int nturn;
	
	// コンストラクタは引数なしで。
	public Attacker2(){
		// プレーヤ名はデフォールトではクラス名が使われるが、独自に設定したい場合はこんな感じ
		player_name = "Random with ワイルド刈り取り";
	}

	@Override
	public void newgame(int firstplayer) {
		nturn = 0;
		consoleClear();
	}

	@Override
	public int turn(boolean again) {
		int[] board = server.get_current_board();
		if(again==false) {
 			nturn++;
			consoleOut("\n"+nturn+"手目:");
		}
		int suggest = random.nextInt(board_size * board_size);
		if ( nturn == 5 ) {
			while ( board[suggest] != server.WILD_STONE ) {
				suggest = random.nextInt(board_size * board_size);
			}
			suggest += server.ATTACK;
		}
		consoleOut(suggest+" ");
		return suggest;
	}

}
