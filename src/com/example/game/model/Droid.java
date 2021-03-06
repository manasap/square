package com.example.game.model;
import com.example.game.model.components.*;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class Droid {

 private Bitmap bitmap; // the actual bitmap
 private int x;   // the X coordinate
 private int y;   // the Y coordinate
 private boolean touched; // if droid is touched/picked up
 private Speed speed;

 public Droid(Bitmap bitmap, int x, int y) {
  this.bitmap = bitmap;
  this.x = x;
  this.y = y;
  this.speed = new Speed(3,0);
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

 public boolean isTouched() {
  return touched;
 }

 public void setTouched(boolean touched) {
  this.touched = touched;
 }
 public Speed getSpeed() {
	return speed;
 }

 public void setSpeed(Speed speed) {
	this.speed = speed;
 }

 public void draw(Canvas canvas) {
  canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
 }

 public void handleActionDown(int eventX, int eventY) {
  if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
   if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2))) {
    // droid touched
    setTouched(true);
   } else {
    setTouched(false);
   }
  } else {
   setTouched(false);
  }

 }

 public void update(){
	 if (!touched) {
			x += (int)(speed.getXv() * speed.getxDirection());
			y += (int)(speed.getYv() * speed.getyDirection());
		}
 }
}