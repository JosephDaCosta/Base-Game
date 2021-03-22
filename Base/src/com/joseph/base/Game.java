package com.joseph.base;

import java.util.Random;
import java.util.Scanner;

public class Game implements Runnable {
	
	public static Random rand;
	
	private Thread thread;
	private boolean isRunning;
	
	public static String direction;
	public static String right = "right", left = "left", up = "up",down = "down";
	public String yes = "yes", no = "no";
	
	public String gameState = "NORMAL";
	private boolean restartGame = false; 
	
	public Game() {
		rand = new Random();
		Scanner texto = new Scanner(System.in);
		System.out.println("Game Start...");
		System.out.println("We can see tree enemies. If you want to fight write 'up', or 'down' to scape.");
		direction = texto.nextLine();
		if(direction.equals(up)) {
			if(Game.rand.nextInt(100) < 10) {
				System.out.println("You defeat the tree enemies!");			
		}else {
			System.out.println("You had lose. Do you want to restart? 'yes' or 'no'.");
			if(direction.equals(no)) {
				System.out.println("GAME OVER!");
				gameState = "GAME-OVER";
			}else if(direction.equals(yes)) {
				gameState = "NORMAL";
			}
		}
		}else if(direction.equals(down)) {
			System.out.println("You have scaped.");
		}
	}
	
	public synchronized void Start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	/*
	public synchronized void Stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	*/
	public static void main(String[] args) {
		Game game = new Game();
		game.Start();
	}
	
	public void Update() {
		if(gameState == "NORMAL") {
			this.restartGame = false;
			//System.out.println("Normal");
		}else if(gameState == "GAME-OVER") {
			if(direction.equals(yes)) {
			this.restartGame = true;
			gameState = "NORMAL";
			System.out.println("Game Over");
			}
		}
		
		
		//Game.main(null);
	}

	@Override
	public void run() {
		while(isRunning) {
			Update();
		}
		
	}
	
	public void RestartGame() {
		Scanner texto = new Scanner(System.in);
		System.out.println("Game Start...");
		System.out.println("We can see tree enemies. If you want to fight write 'up', or 'down' to scape.");
		direction = texto.nextLine();
		return;
	}
	
}
