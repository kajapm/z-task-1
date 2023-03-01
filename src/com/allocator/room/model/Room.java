package com.allocator.room.model;

import java.util.ArrayList;
import java.util.List;

public class Room {

	private List<People> peopleArray;

	public Room() {
		peopleArray = new ArrayList<People>();
	}

	public List<People> getPeopleArray() {
		return peopleArray;
	}

	public void setPeopleArray(List<People> peopleArray) {
		this.peopleArray = peopleArray;
	}

	@Override
	public String toString() {
		return "Room [peopleArray=" + peopleArray + "]";
	}

}
