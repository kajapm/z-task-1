package com.allocator.room.view;

import java.util.Scanner;

import com.allocator.room.controller.RandomAllocationController;

public class RandomAllocationView {

	private AllocationView allocationView;
	private RandomAllocationController randomAllocationController;
	private Scanner scanner = new Scanner(System.in);

	public RandomAllocationView(AllocationView allocationView) {

		randomAllocationController = new RandomAllocationController();
		this.allocationView = allocationView;

	}

	public void showAllocationMenu() {
		System.out.println("\n1.Add people");
		System.out.println("2.vacate people");
		System.out.println("3.Clear room for maintenance");
		System.out.println("4.Allocate people to maintenance room");
		System.out.println("5.Add room");
		System.out.println("6.Show rooms");
		System.out.println("7.Main Menu");
		System.out.println("8.Exit\n");
		System.out.print("Enter your option :");

		option(scanner.nextLine());

	}

	private void option(String option) {
		switch (option) {
		case "1":

			System.out.print("\nEnter no of people :");
			randomAllocationController.addPeople(Integer.parseInt(scanner.nextLine()));
			showAllocationMenu();
			break;

		case "2":

			System.out.print("\nEnter no of people :");
			randomAllocationController.vacate(Integer.parseInt(scanner.nextLine()));
			showAllocationMenu();
			break;

		case "3":

			System.out.print("\nEnter maintenance room number :");
			randomAllocationController.clearRoomForMaintenance(Integer.parseInt(scanner.nextLine()));
			showAllocationMenu();
			break;

		case "4":

			randomAllocationController.allocatePeopleToMaintenanceRoom();
			showAllocationMenu();
			break;

		case "5":

			randomAllocationController.addRoom();
			showAllocationMenu();
			break;

		case "6":

			randomAllocationController.showRoomPeople();
			showAllocationMenu();
			break;

		case "7":

			allocationView.init();
			break;

		case "8":

			System.out.println("Thank You..!");
			break;

		default:

			System.out.println("Wrong input.!");
			showAllocationMenu();
		}
	}

}
