package Player;

import SanmokuSystem.Player;

public class FailPlayer extends Player {
    private int numOfMatch;
    public FailPlayer() {
        player_name = "エラーしまくり";
        numOfMatch = 0;
    }
    @Override
	public void newgame(int firstplayer) {
        numOfMatch++;        
    }
    @Override
	public int turn(boolean again) {
        switch ( numOfMatch+id ) {
            case 1:
                consoleOutln("return (-1);");
                return ( -1 );
            case 2:
                consoleOutln("while (true);");
                while ( true );
            case 3:
                consoleOutln("配列のインデックスの不正参照");
                int[] board = new int[board_size];
                consoleOutln(board[board_size] );
            case 4:
                consoleOutln("ゼロ除算");
                consoleOutln(10/0);
            case 5:
            case 6:
                if (again==false) consoleOutln("座標:"+id);
                return ( id );
        }
        return(0);
    }

    
}
