package com.eps.androidconnect4.util;


public class GameResult
{
	public String player_alias;
	private int board_size ;
	private String temps_total = "No temps";
	private boolean time_control;
	private String date;
	private int result_int; //status



	public GameResult(String player_name,int board_size,boolean time_control, String date,int result_int){
		this.player_alias = player_name;  // get from shared preferences
		this.board_size = board_size; // get from shared preferences
		this.time_control = time_control; // get from shared preferences
		this.date = date;  // get from result game and log
		this.result_int = result_int; //get from result game and log
		
	}

	public String getResultGameString(){
		switch (this.result_int){
			case constants.WIN_RESULT:
				return "Has guanyat!!";
			case constants.LOSE_RESULT:
				return "Has perdut :c";
			case constants.DRAW_RESULT:
				return "Empat!";
		}
		return "?";
	}

	public int getResult_int() {
		return result_int;
	}


	public String getPlayer_alias() {
		return player_alias;
	}

	public int getBoard_size() {
		return board_size;
	}

	public boolean getTime_control() {
		return time_control;
	}

	public String getDate() {
		return (date.equals(""))? "No hay fecha":date;
	}
}
