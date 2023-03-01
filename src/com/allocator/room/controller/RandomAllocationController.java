package com.allocator.room.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.allocator.room.model.People;
import com.allocator.room.model.Room;
import com.allocator.room.repository.Database;

public class RandomAllocationController {

	protected List<Room> roomsList;
	private List<Integer> maintenanceRoomsList;
	private Database db;
	private Scanner scanner;

	public RandomAllocationController() {

		db = Database.getInstance();
		db.setRoomsList(new ArrayList<Room>());
		scanner = new Scanner(System.in);

	}

	public void addPeople(int newPeopleCount) {

		roomsList = db.getRoomsList();

		if (roomsList.size() * 10 < db.getPeopleCount() + newPeopleCount) {
			System.out.println("\nNo vaccant for these people.! ");

		} else {

			for (int i = newPeopleCount; i > 0; i--) {
				roomsList.get(db.getIndexTrack()).getPeopleArray().add(new People(db.getIdGenerator()));
				db.setIndexTrack(db.getIndexTrack() + 1);
				db.setIdGenerator(db.getIdGenerator() + 1);

				if (db.getIndexTrack() == roomsList.size())
					db.setIndexTrack(0);
			}

			db.setPeopleCount(db.getPeopleCount() + newPeopleCount);
			db.setRoomsList(roomsList);

			showRoomPeople();
			System.out.println("\nSuccessfully people added..");
		}
	}

	public void clearRoomForMaintenance(int roomNo) {

		roomsList = db.getRoomsList();
		roomNo = roomNo - db.getMaintenanceRoomsList().size();

		if (roomNo < roomsList.size()) {

			if (!db.getMaintenanceRoomsList().contains(roomNo)) {

				if (roomNo == 0) {

					roomsList.get(1).getPeopleArray().addAll(roomsList.get(0).getPeopleArray());

				} else {

					roomsList.get(roomNo - 1).getPeopleArray().addAll(roomsList.get(roomNo).getPeopleArray());
				}

				roomsList.remove(roomNo);
				maintenanceRoomsList = db.getMaintenanceRoomsList();
				maintenanceRoomsList.add(roomNo);
				db.setMaintenanceRoomsList(maintenanceRoomsList);
				System.out.println("\nSuccessfully room cleared for maintenance..");
				db.setRoomsList(roomsList);
				reAllocate(roomsList.size());
				showRoomPeople();
			}

			else {

				System.out.println("\nAlready in maintenance.!");
			}
		}

		else {
			System.out.println("Entered room not available.!");
		}
	}

	public void addRoom() {

		roomsList = db.getRoomsList();
		roomsList.add(new Room());
		System.out.println("\nRoom " + (roomsList.size() - 1) + " added");
		reAllocate(roomsList.size());

	}

	public void allocatePeopleToMaintenanceRoom() {

		roomsList = db.getRoomsList();
		List<Integer> maintenanceRoomsList = db.getMaintenanceRoomsList();

		System.out.println("Rooms allotted for maintenace are :");

		for (int i = 0; i < maintenanceRoomsList.size(); i++) {

			System.out.println(i + ". " + maintenanceRoomsList.get(i));
		}

		System.out.println("\nChoose a room index for allocate people :");
		int roomNumber = Integer.parseInt(scanner.nextLine());

		if (maintenanceRoomsList.size() > roomNumber) {

			maintenanceRoomsList.remove(roomNumber);
			roomsList.add(roomNumber, new Room());
			db.setRoomsList(roomsList);
			reAllocate(roomsList.size());
			System.out.println("\nSuccessfully allocate people to maintenance room..");

		}

		else {
			System.out.println("\nEntered room number not in maintenance.!");
		}

	}

	public void vacate(int vacateCount) {

		roomsList = db.getRoomsList();
		int vacatedPeople = vacateCount;

		if (db.getPeopleCount() < vacateCount || vacateCount < 0) {
			System.out.println("\nNot having this amount of people in rooms.!");

		} else {

			showRoomPeople();
			for (int i = 0; i < vacateCount; i++) {
				boolean isRemoved = false;
				System.out.println("Enter " + (i + 1) + " person number to vacate :");
				int personId = Integer.parseInt(scanner.nextLine());

				for (int j = 0; j < roomsList.size(); j++) {

					List<People> peopleList = roomsList.get(j).getPeopleArray();

					for (int k = 0; k < peopleList.size(); k++) {

						if (personId == peopleList.get(k).getId()) {

							peopleList.remove(k);
							isRemoved = true;
							db.setRoomsList(roomsList);
							break;
						}
					}
				}
				if (!isRemoved) {
					System.out.println("\nCould'nt find person id.!");
					vacatedPeople--;
				}
				showRoomPeople();
			}

			db.setPeopleCount(db.getPeopleCount() - vacatedPeople);
			reAllocate(roomsList.size());
			System.out.println("Successfully vacated " + vacatedPeople + " people..");
		}
	}

	public void showRoomPeople() {

		int maintenanceRoomCount = 0;
		roomsList = db.getRoomsList();
		maintenanceRoomsList = db.getMaintenanceRoomsList();

		for (int i = 0; i < roomsList.size(); i++) {
			if (maintenanceRoomsList.contains(i)) {
				System.out.println("\nRoom " + (i + maintenanceRoomCount++) + " :");
				System.out.println("\t In maintenance.!");

			}
			System.out.println("\nRoom " + (i + maintenanceRoomCount) + " :");
			List<People> peopleList = roomsList.get(i).getPeopleArray();
			for (int j = 0; j < roomsList.get(i).getPeopleArray().size(); j++) {
				System.out.println("\t " + peopleList.get(j));
			}
		}
	}

	protected void reAllocate(int size) {

		roomsList = db.getRoomsList();

		while (!isEqual(roomsList, size)) {

			for (int i = 1; i < size; i++) {

				if (Math.abs(
						roomsList.get(i - 1).getPeopleArray().size() - roomsList.get(i).getPeopleArray().size()) != 0) {

					if (roomsList.get(i - 1).getPeopleArray().size() > roomsList.get(i).getPeopleArray().size()) {
						roomsList.get(i).getPeopleArray().add(roomsList.get(i - 1).getPeopleArray()
								.remove(roomsList.get(i - 1).getPeopleArray().size() - 1));

					} else if (roomsList.get(i).getPeopleArray().size() > roomsList.get(i - 1).getPeopleArray()
							.size()) {
						roomsList.get(i - 1).getPeopleArray().add(
								roomsList.get(i).getPeopleArray().remove(roomsList.get(i).getPeopleArray().size() - 1));
					}
				}
			}
		}
		setCurrentIndexTrack();
		db.setRoomsList(roomsList);
	}

	private boolean isEqual(List<Room> roomsList, int size) {

		int averageOfPeople = db.getPeopleCount() / roomsList.size();

		for (int i = 0; i < size; i++) {

			int difference = Math.abs(roomsList.get(i).getPeopleArray().size() - averageOfPeople);
			if (difference > 1) {
				return false;
			}
		}

		return true;
	}

	private void setCurrentIndexTrack() {

		roomsList = db.getRoomsList();

		for (int i = 1; i < roomsList.size(); i++) {

			if (roomsList.get(i - 1).getPeopleArray().size() > roomsList.get(i).getPeopleArray().size()) {
				db.setIndexTrack(i);
				return;
			}
		}

		db.setIndexTrack(0);
	}
}
