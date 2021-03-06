package org.bistu.kjcx.godshandandthief.actor;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameActor {
	public enum ActorStatus {
		Action,		//行动
		NoDisp,		//不显示
		NoAction,	//无行动
		Dead,		//死亡
	}
	protected ActorStatus status;
	
	protected ArrayList<GameActor> children;
	protected String name;
	protected float actorX, actorY, shrink;
	protected long level;
	protected Bitmap actorBitmap;
	protected Paint paint;
	
	
	
	public GameActor() {
		this.status = ActorStatus.Action;
	}
	
	public GameActor(String name) {
		this();
		this.name = name;
		children = new ArrayList<GameActor>();
	}
	
	public void update(long elapsedTime) {
		for(GameActor actorChild : children) {		//增强的for循环
			if(actorChild.status == ActorStatus.Action || actorChild.status == ActorStatus.NoDisp)
				actorChild.update(elapsedTime);
		}
	}
	
	public void render() {
		for(GameActor actorChild : children)
			if(actorChild.status == ActorStatus.Action || actorChild.status == ActorStatus.NoAction)
				actorChild.render();
	}
	
	public void render(Canvas canvas) {
		for(GameActor actorChild : children)
			if(actorChild.status == ActorStatus.Action || actorChild.status == ActorStatus.NoAction)
				actorChild.render(canvas);
	}
	
	public void addChild(GameActor actor) {
		children.add(actor);
		actor.level = level + 1;
	}
	
	public GameActor search(String name) {
		if(this.name == name)
			return this;
		for(GameActor actorChild : children)
			if(actorChild.name == name)
				return actorChild;
		return null;
	}
	
	public void cleanUpDead() {
		if(this.status == ActorStatus.Dead)
			for(GameActor actorChild : children)
				actorChild.status = ActorStatus.Dead;
		for(GameActor actorChild : children)
			actorChild.cleanUpDead();
		children.remove(children);
	}
	
	boolean checkStatus(GameActor actor) {
		return false;
	}
	
}
