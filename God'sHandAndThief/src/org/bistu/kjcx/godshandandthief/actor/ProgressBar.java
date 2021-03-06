package org.bistu.kjcx.godshandandthief.actor;

import org.bistu.kjcx.godshandandthief.MainSurfaceView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class ProgressBar extends GameActor {
	public static long TOTAL_Long = 30000;
	
	private long playTime;
	private boolean isPlay;
	
	
	
	public ProgressBar(Bitmap actorBitmap) {
		playTime = 0;
		isPlay = true;
		
		paint = new Paint();
	}
	
	@Override
	public void update(long elapsedTime) {
		playTime += elapsedTime;
		if(playTime > TOTAL_Long) {
			isPlay = false;
			playTime = 0;
		}
		
	}
	
	@Override
	public void render(Canvas canvas) {
		
		canvas.drawLine(MainSurfaceView.SCREEN_W / 4, 14, MainSurfaceView.SCREEN_W * 3 / 4, 14, paint);
		canvas.drawCircle(MainSurfaceView.SCREEN_W / 4 + MainSurfaceView.SCREEN_W / 2 * (playTime / (float) TOTAL_Long), 14, 7, paint);
		
	}
	
	public boolean isPlay() {
		return isPlay;
	}
	
	public long getProgressL() {
		return playTime;
	}
	
	public float getProgressP() {
		return playTime;
	}
	
}
