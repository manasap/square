package com.example.game;

import com.example.game.model.Square;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements
  SurfaceHolder.Callback {

 private MainThread thread;
 private Square square1;
 private static final String TAG = MainGamePanel.class.getSimpleName();
 
 public MainGamePanel(Context context) {
  super(context);
  getHolder().addCallback(this);

  square1 = new Square(BitmapFactory.decodeResource(getResources(), R.drawable.off3), 200, 270);
  
  // create the game loop thread
  thread = new MainThread(getHolder(), this);

  setFocusable(true);
 }

 @Override
 public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
 }

 @Override
 public void surfaceCreated(SurfaceHolder holder) {
  thread.setRunning(true);
  thread.start();
 }

 @Override
 public void surfaceDestroyed(SurfaceHolder holder) {
  boolean retry = true;
  while (retry) {
   try {
    thread.join();
    retry = false;
   } catch (InterruptedException e) {
    // try again shutting down the thread
   }
  }
 }

 @Override
 public boolean onTouchEvent(MotionEvent event) {
	 if (event.getAction() == MotionEvent.ACTION_DOWN) {
		 Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
		 Log.d(TAG, "Coords2: x=" + getWidth() + ",y=" + getHeight());
		 square1.handleActionDown((int)event.getX(), (int)event.getY());
		 	 
	 }if (event.getAction() == MotionEvent.ACTION_UP) {
		   // touch was released
		   if (square1.isTouched()) {
			   square1.setTouched(false);
		   }
	 }
	 return true;
}
 Bitmap background = BitmapFactory.decodeResource(getResources(),R.drawable.bg2);
 Bitmap l1 = BitmapFactory.decodeResource(getResources(),R.drawable.off2);
 Bitmap l2 = BitmapFactory.decodeResource(getResources(),R.drawable.on2);
 int i=0;
 protected void render(Canvas canvas) {
	 canvas.drawColor(Color.WHITE);
	 
	 /*canvas.drawBitmap(background, 10, 10, null);	 
	 canvas.drawBitmap(l1, 0, 200, null);	
	 canvas.drawBitmap(l1, 270, 200, null);	 
	 canvas.drawBitmap(l1, 540, 200, null);	 
	 
	 canvas.drawBitmap(l1, 0, 420, null);	
	 canvas.drawBitmap(l1, 270, 420, null);	 
	 canvas.drawBitmap(l1, 540, 420, null);	 
	 
	 canvas.drawBitmap(l1, 0, 640, null);	
	 canvas.drawBitmap(l1, 270, 640, null);	 
	 canvas.drawBitmap(l1, 540, 640, null);	
	 Shader shader2 = new LinearGradient(10, 10, 138, 138, Color.rgb(0, 51, 0), Color.rgb(0, 153, 0), TileMode.CLAMP);
	 Paint paint2 = new Paint(); 
	 paint2.setShader(shader2); 
	 canvas.drawRect(new RectF(10, 50, 138, 178), paint2);
	 
	 Shader shader3 = new LinearGradient(10, 10, 138, 138, Color.rgb(0, 51, 0), Color.rgb(0, 153, 0), TileMode.CLAMP);
	 Paint paint3 = new Paint(); 
	 paint3.setShader(shader2); 
	 canvas.drawRect(new RectF(148, 50, 276, 178), paint2);*/
	 
	 Paint paint = new Paint(); 
	 paint.setColor(Color.rgb(189, 209, 59));
	 paint.setStrokeWidth(10);        
	 paint.setStyle(Paint.Style.STROKE);         
	// canvas.drawRect(50, 100, 150, 200, paint);	 
	 
	 float x=30;
	 float xe=130;
	 
	 for (int xlim = 0; xlim < 12; xlim++) {	
		 float y=100;
		 float ye=200;
			 for (int ylim = 0; ylim < 12; ylim++) {
				 /*int random = (int )(Math.random() * 3);	
				 if(random == 1){
					 paint.setColor(Color.rgb(227, 11, 123));
				 }else if(random == 2){
					 paint.setColor(Color.rgb(189, 209, 59)); 
				 }else if(random == 3){
					 paint.setColor(Color.rgb(252, 198, 48)); 
				 }*/
				 y=y+120;
				 ye=ye+120;
				 ylim = ylim+1;
				 canvas.drawRect(x, y, xe, ye, paint);
				 //Log.d(TAG, "Coords: x=" + x + ",y=" + y);
			 }
			 x=x+120;
			 xe=xe+120;
			 xlim++; 
			// Log.d(TAG, "Coordsx: x=" + x + ",y=" + y);
		 
	 }
	 
	 square1.draw(canvas);
	
 }
 
 public void update() {		
	
	 square1.update(System.currentTimeMillis(), getWidth(), getHeight());

	}
 
 private void displayFps(Canvas canvas, String fps) {
		if (canvas != null && fps != null) {
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			canvas.drawText(fps, this.getWidth() - 50, 20, paint);
		}
	}
 public void onResume() {
	 thread.setRunning(true);
	  thread.start();
	}

	public void onPause() {
	    thread.setRunning(false);
	}
}
