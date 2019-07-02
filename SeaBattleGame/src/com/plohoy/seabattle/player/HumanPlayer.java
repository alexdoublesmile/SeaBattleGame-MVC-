package com.plohoy.seabattle.player;

import com.plohoy.seabattle.main.*;
import com.plohoy.seabattle.model.*;
import com.plohoy.seabattle.view.*;

public class HumanPlayer implements Player {

	private String name;
	private String pasword;

	private int age;
	
	private int score;
	private int scoreRecord;
	
	GameView theView;
	GameModel theModel;
	
	public HumanPlayer() {
		
		
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScoreRecord() {
		return scoreRecord;
	}
	
	public void setScoreRecord(int scoreRecord) {
		this.scoreRecord = scoreRecord;
	}
}
