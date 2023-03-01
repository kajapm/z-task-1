package com.allocator.room.controller;

import java.util.ArrayList;
import java.util.List;

import com.allocator.room.model.People;
import com.allocator.room.model.Room;
import com.allocator.room.repository.Database;

public class SequenceAllocationController extends RandomAllocationController {

	private Database db;

	public SequenceAllocationController() {

		db = Database.getInstance();
		db.setRoomsList(new ArrayList<Room>());

	}

	public void runSequence() {

		for (int i = 0; i < 10; i++) { // for add 10 rooms

			addRoom();
		}

		addPeople(30); // for add 30 people
		addPeople(5); // for add 5 people

		clearRoomForMaintenance(2); // 3rd room is allotted for maintenance
		clearRoomForMaintenance(5); // 6th room is allotted for maintenance

		addRoomWithOneCapacity();

		vacate(25);
		showRoomPeople();

	}

	public void vacate(int vacateCount) {
		roomsList = db.getRoomsList();

		for (int i = 1; i <= vacateCount; i++) {
			for (int j = 0; j < roomsList.size(); j++) {
				List<People> peopleList = roomsList.get(j).getPeopleArray();
				for (int k = 0; k < peopleList.size(); k++) {
					if (peopleList.get(k).getId() == i) {
						peopleList.remove(k);
						System.out.println("\nPerson " + i + " removed");
						break;
					}
				}
			}
		}

		db.setPeopleCount(db.getPeopleCount() - vacateCount);
		reAllocate(roomsList.size() - db.getOneCapacityRoomCount());
	}

	private void addRoomWithOneCapacity() {

		roomsList = db.getRoomsList();
		addRoom();
		db.setOneCapacityRoomCount(db.getOneCapacityRoomCount() + 1);
		roomsList.get(roomsList.size() - 1).getPeopleArray()
				.add(roomsList.get(0).getPeopleArray().remove(roomsList.get(0).getPeopleArray().size() - 1));
		db.setRoomsList(roomsList);
		reAllocate(roomsList.size() - db.getOneCapacityRoomCount());
	}

}
