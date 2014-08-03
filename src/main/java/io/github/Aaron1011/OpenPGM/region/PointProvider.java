package io.github.Aaron1011.OpenPGM.region;

import org.bukkit.util.Vector;

public class PointProvider {
	public int yaw;
	public int pitch;

	public Vector pos;
	
	public PointProvider() {
		this.yaw = 0;
		this.pitch = 0;
		this.pos = new Vector(0, 0, 0);
	}
	
	public PointProvider(int yaw, int pitch) {
		this.yaw = yaw;
		this.pitch = pitch;
		this.pos = new Vector(0, 0, 0);
	}
	
	public PointProvider(int yaw, int pitch, Vector pos) {
		this.yaw = yaw;
		this.pitch = pitch;
		this.pos = pos;
	}
	
	public int getYaw() {
		return yaw;
	}

	public void setYaw(int yaw) {
		this.yaw = yaw;
	}

	public int getPitch() {
		return pitch;
	}

	public void setPitch(int pitch) {
		this.pitch = pitch;
	}

	public Vector getPos() {
		return pos;
	}

	public void setPos(Vector pos) {
		this.pos = pos;
	}
}
