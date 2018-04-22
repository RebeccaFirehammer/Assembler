package model;

public class Instruction {
	
	String command = "";
	String destination = "";
	String source = "";
	
	public Instruction(String command, String destination, String source) {
		this.command = command;
		this.destination = destination;
		this.source = source;
	}
	
	public Instruction(String command, String destination) {
		this.command = command;
		this.destination = destination;
	}
	
	public Instruction(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command.toLowerCase();
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDestination() {
		return destination.toLowerCase();
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSource() {
		return source.toLowerCase();
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
	
}
