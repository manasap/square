package com.example.game.model;

import com.example.game.model.components.Speed;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class Square {
	
	private static final String TAG = Square.class.getSimpleName();

	private Bitmap bitmap;		// the animation sequence
	private int x;				// the X coordinate of the object (top left of the image)
	private int y;				// the Y coordinate of the object (top left of the image)
	private boolean touched; 
	private int score = 0;	
	private long timeTicker;	// the time of the last sqaure update
	private int timePeriod = 900;	// milliseconds between each square (1000/fps)
	
	public Square(Bitmap bitmap, int x, int y) {
		  this.bitmap = bitmap;
		  this.x = x;
		  this.y = y;
		 }

	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void updateScore() {
		this.score = score + 1;
	}
	
	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		 this.touched = touched;
	}
		 
	public void update(long gameTime, int width, int height){
		if (gameTime > timeTicker + timePeriod) {
			timeTicker = gameTime;
			int limitx = (int) (width/(bitmap.getWidth()/2)-1);
			int limity = (int) (height/(bitmap.getHeight()/2)-1);
			int randomx = (int )(Math.random() * 6);	
			//x = (int) (randomx * (bitmap.getWidth()/2));
			x = (int) (80 + randomx * 120);
			int randomy = (int )(Math.random() * 6);	
			//y = (int) (randomy * (bitmap.getHeight()/2));
			y = (int) (270 + randomy * 120);
		}
	}
	
	// the draw method which draws the corresponding frame
	public void draw(Canvas canvas) {		
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
		Paint paintStyle = new Paint(); 
		paintStyle.setColor(Color.BLACK);
		paintStyle.setTextSize(50); 
		canvas.drawText("Score: "+score+"", 400, 100, paintStyle);
		
	}
	
	
	public void handleActionDown(int eventX, int eventY) {
		 if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
			   if (eventY >= (y - bitmap.getHeight() / 2) && (eventY <= (y + bitmap.getHeight() / 2))) {
				   updateScore();
				   setTouched(true);
		   } else {
		    setTouched(false);
		   }
		  } else {
		   setTouched(false);
		  }

		 }
}
