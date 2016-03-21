package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class Hero extends GameObject {

	
	
	
	
	private int power;
	private int hp;
	private Status status;
	private int numberOfBombs;
	private Texture img;
	private Vector3 position;
	private float velocity;
	private Sprite sprite;
		
}

 enum Status {dead, alive};