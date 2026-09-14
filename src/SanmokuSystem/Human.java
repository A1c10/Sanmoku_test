package SanmokuSystem;

import javax.swing.JOptionPane;

public class Human extends Player {
	String realname = "Human(手動入力プレーヤー)";

	public Human(String realname) {
		this.realname = realname;
	}

	public Human() {
		super();
	}

	@Override
	public String init(int id, int nplayer, int nstone, int ngame, int board_size, Server server) {
		super.init(id, nplayer, nstone, ngame, board_size, server);
		return realname;
	}

	@Override
	public String toString() {
		return realname;
	}

	@Override
	public int turn(boolean again) {
		int[] board = server.get_current_board(); // ボードの状態を取得
		int pos = server.wait_jikkyo_click();
		if (board[pos] != -1) {
			if (isAttackable()) {
				int result = JOptionPane.showConfirmDialog(null, "アタックしますか？", "攻撃確認", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					pos += ATTACK;
				}
			}
		}
		return pos;
	}

	@Override
	boolean isHuman() {
		return true;
	}
}
