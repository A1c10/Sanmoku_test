package SanmokuSystem;

public abstract class Player implements UserClass {
    public static final int ATTACK = Server.ATTACK;
	public int board_size;
	public int id;
	public int nplayer;
	public int nstone;
	public int ngame;
	public Server server;
	public String player_name;
	public UserConsole myConsole; 

	public String init(int id, int nplayer, int nstone, int ngame, int board_size,
			Server server) {
		this.id = id;
		this.nplayer = nplayer;
		this.nstone = nstone;
		this.ngame = ngame;
		this.board_size = board_size;
		this.server = server;
		if(player_name == null){
			player_name = this.getClass().getName();
		}
		System.out.format("%d %d %d %d %d%n", id, nplayer, nstone, ngame, board_size);
		myConsole = new UserConsole(id,player_name,server);
		myConsole.setVisible(false);

		return player_name;
	}

	@Override
	public String toString() {
		return player_name + ":(" +  String.valueOf((char)('A'+id))+")";
	}


	public void newgame(int firstplayer){}

	public abstract int turn(boolean again);

	boolean isHuman(){
		return false;
	}
	
	boolean isAttackable() {
	    return server.isAttackable(id);
	}

	public void consoleOut( Object text ) {
		if ( server.enable_userConsole ) {
			myConsole.addText(text.toString());
			myConsole.setVisible(true);
		} else {
			myConsole.setVisible(false);
		}
	}
	public void consoleOutln( Object text ) {
		consoleOut(text.toString()+"\n");
	}
	public void consoleClear() {
		myConsole.clear();
	}
}
