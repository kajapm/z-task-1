package com.allocator.room.repository;

import java.util.ArrayList;
import java.util.List;

import com.allocator.room.model.Room;

public class Database {

	private int peopleCount, indexTrack, idGenerator = 1,oneCapacityRoomCount;
	private List<Room> roomsList;
	public static Database database;
	private List<Integer> maintenanceRoomsList;

	private Database() {
		roomsList = new ArrayList<Room>();
		setMaintenanceRoomsList(new ArrayList<Integer>());
	}

	public static Database getInstance() {
		if (database == null)
			database = new Database();

		return database;
	}

	public int getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}

	public int getIndexTrack() {
		return indexTrack;
	}

	public void setIndexTrack(int indexTrack) {
		this.indexTrack = indexTrack;
	}

	public int getIdGenerator() {
		return idGenerator;
	}

	public void setIdGenerator(int idGenerator) {
		this.idGenerator = idGenerator;
	}

	public List<Room> getRoomsList() {
		return roomsList;
	}

	public void setRoomsList(List<Room> roomsList) {
		this.roomsList = roomsList;
	}

	public List<Integer> getMaintenanceRoomsList() {
		return maintenanceRoomsList;
	}

	public void setMaintenanceRoomsList(List<Integer> maintenanceRoomsList) {
		this.maintenanceRoomsList = maintenanceRoomsList;
	}

	public int getOneCapacityRoomCount() {
		return oneCapacityRoomCount;
	}

	public void setOneCapacityRoomCount(int oneCapacityRoomCount) {
		this.oneCapacityRoomCount = oneCapacityRoomCount;
	}
}
