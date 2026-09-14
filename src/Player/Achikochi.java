package Player;
import SanmokuSystem.*;
import java.util.Random;

public class Achikochi extends Player {
	Random random = new Random();
	int nturn;
	
	// コンストラクタは引数なしで。
	public Achikochi(){
		// プレーヤ名はデフォールトではクラス名が使われるが、独自に設定したい場合はこんな感じ
		player_name = "Random Player";
	}

	@Override
	public void newgame(int firstplayer) {
		nturn = 0;
		consoleClear();
	}

	@Override
	public int turn(boolean again) {
		if(again==false) {
 			nturn++;
			consoleOut("\n"+nturn+"手目:");
		}
		int suggest = random.nextInt(board_size * board_size);
		consoleOut(suggest+" ");
		return suggest;
	}

}
