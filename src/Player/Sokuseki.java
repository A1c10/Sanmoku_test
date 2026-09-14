package Player;
import SanmokuSystem.*;
import java.util.Random;

public class Sokuseki extends Player {
	Random random = new Random();
	int pos;
	int turns;	//何手目かを管理する変数

	boolean firstPlay = false;

	public Sokuseki() {
		player_name = "2022-10-03即席プレーヤー";
	}
	@Override
	public void newgame(int firstplayer) {
		turns = 0;	//手数を初期化
		myConsole.clear();	//デバッグ用コンソールを初期化
		if ( id == firstplayer ) {
			firstPlay = true;	//初手が打てる場合は初手フラグを立てる
		} else {
			firstPlay = false;	//それ以外は初手フラグを下ろす
		}
	}

	@Override
	public int turn(boolean again) {
		if ( !again ) {	//打ち直しでなければ
			turns++;
			pos = 0;	//探索する場所を左上に設定する
		} else {
			pos++;
			if ( pos >= board_size * board_size) {
				return random.nextInt(board_size * board_size); //相手が打っていたらランダムで置く
			}
		}
		consoleOut(again ? " again " : "turns:"+turns+" ");
		consoleOutln(" pos:"+pos);
		int[] board = server.get_current_board();	//ボードの状態を取得
		while ( pos < board_size * board_size-1 ) {		//右下になるまでは探索する
			if ( board[pos] == id ) {	//自分の石が置かれていたら
				break;	//探索中断
			}
			pos++;	//場所を１つ進める
		}
		switch ( turns ) {	//手数によって処理を変える
			case 1 :	//1手目
			{
				if ( firstPlay == true ) {
					firstPlay = false;	//初手フラグを下ろす
					return ( board_size/2 * board_size + board_size/2 );	//初手だったら、盤面中央（天元）に打つ
				}
				break;
			}
			case 2 :	//2手目
			case 3 :	//3手目
			{
				int retPos = putRight(pos);
				if ( retPos != -1 ) {
					return ( retPos );	//右隣に置く
				}
				break;
			}
			case 4 :	//4手目
			{
				int retPos = putLeft(pos);
				if ( retPos != -1 ) {
					return ( retPos );	//左隣に置く
				}
				break;
			}
			default :	//上記以外
			{
				int preScore = server.get_current_score()[id];
				int trialPos = pos;
				while ( trialPos != pos-1 ) {
					int[] tryalBoard = server.get_current_board();	//ボードの状態を取得
					tryalBoard[trialPos] = id;
					if ( server.evaluate_board(tryalBoard)[id] > preScore ) {	//試し置きが現在の得点を上回ったら
						return ( trialPos );
					}
					trialPos++;
					trialPos = trialPos % (board_size*board_size);
				}

				int retPos = putRight(pos);
				if ( retPos != -1 ) {
					return ( retPos );	//右隣に置く
				}
				break;
			}
		}
		return random.nextInt(board_size * board_size); //相手が打っていたらランダムで置く
	}

	/**
	 * 右隣に置く
	 * @param pos 現在の座標
	 * @return 右隣の座標（置けない場合は-1）
	 */	
	private int putRight(int nowPos) {
		nowPos++;
		if ( nowPos < 0 || board_size * board_size <= nowPos ) {
			return ( -1 );
		} else {
			return ( nowPos );
		} 
	}
	/**
	 * 左隣に置く
	 * @param pos 現在の座標
	 * @return 左隣の座標（置けない場合は-1）
	 */	
	private int putLeft(int nowPos) {
		nowPos--;
		if ( nowPos < 0 || board_size * board_size <= nowPos ) {
			return ( -1 );
		} else {
			return ( nowPos );
		} 
	}
}
