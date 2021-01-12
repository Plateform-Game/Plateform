package fr.julman.platform.utils;

public enum GameState {
	IDLE("IDLE"), PLAYING("PLAYING"), SETTINGS("SETTINGS");
	
	private String str;
	private GameState(String str) {
		this.str = str;
	}
	public String toString() {
		return str;
	}
}
