package com.allocator.room.model;

public class People {
	int id;

	public People(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Person " + id;
	}
}
