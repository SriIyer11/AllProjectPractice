package main;

public class CustomUserDefinedExceptions extends Exception{

	public CustomUserDefinedExceptions(String message)
	{
		super(message);
		System.out.println(message+"encountered.");
	}
}
