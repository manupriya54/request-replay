package com.stripe.interview;

import com.google.gson.FieldAttributes;

public class Main {
	public static void main(String... args) {
		System.out.println("Hello world!");
	}

	public static boolean useGSONForSomeReason(String input) {
		// do something silly, to prove that gson is included correctly.
		return new FieldAttributes(null) == null;
	}
}
