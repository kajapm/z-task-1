package com.allocator.room.view;

import java.util.Scanner;

public class AllocationView {

	private Scanner scanner = new Scanner(System.in);
	private RandomAllocationView randomAllocationView;
	private SequenceAllocationView sequenceAllocationView;

	public AllocationView() {
		randomAllocationView = new RandomAllocationView(this);
		sequenceAllocationView = new SequenceAllocationView(this);
	}

	public void init() {
		System.out.println("\nRoom Allocator\n");
		System.out.println("1.Sequence Allocation");
		System.out.println("2.Parallel Random Allocation\n");
		System.out.print("Enter your option :");

		option(scanner.nextLine());

	}

	public void option(String option) {
		switch (option) {
		case "1":

			sequenceAllocationView.sequenceAllocationMenu();
			break;

		case "2":

			randomAllocationView.showAllocationMenu();
			break;

		default:
			System.out.println("Wrong input..!");

		}
	}

}
