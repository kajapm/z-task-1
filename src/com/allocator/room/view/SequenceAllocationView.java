package com.allocator.room.view;

import java.util.Scanner;

import com.allocator.room.controller.SequenceAllocationController;


public class SequenceAllocationView {

	private AllocationView allocationView;
	private Scanner scanner;
	private SequenceAllocationController sequenceAllocationController;

	public SequenceAllocationView(AllocationView allocationView) {

		this.allocationView = allocationView;
		sequenceAllocationController = new SequenceAllocationController();
		scanner = new Scanner(System.in);

	}

	public void sequenceAllocationMenu() {
		System.out.println("\n1.Start sequence");
		System.out.println("2.Mainmenu");
		System.out.println("Exit\n");
		System.out.print("Enter your option :");

		option(scanner.nextLine());
	}

	private void option(String option) {

		switch (option) {
		case "1":

			sequenceAllocationController.runSequence();
			sequenceAllocationMenu();
			break;

		case "2":
			allocationView.init();
			break;

		case "3":
			System.out.println("Thank You..!");
			break;

		default:

			System.out.println("Wrong input.!");
			sequenceAllocationMenu();

		}

	}

}
