package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Instruction;

public class Translator{

	private File input;
	private List <Instruction> instructions;
	private Instruction instruction;
	private String opCode;
	private static final int command = 0;
	private static final int destination = 1;
	private static final int source = 2;
	
	public Translator(File input) throws IOException {
		this.input = input;
		instructions = new ArrayList<Instruction>();
		getInstructions();
		translateInstructions();
	}
	
	private void translateInstructions() {
		for (Instruction instruction: instructions){
			String command = instruction.getCommand();
			switch(command) {
				case "mov":
					String source = instruction.getSource();
					String destination = instruction.getDestination();
					if(destination.contains("r")) {
						if(source.contains("r")) {
							opCode = "80";
						}else if(source.contains("[")) {
							opCode = "82";
						}else {
							opCode = "81";
						}
					}else {
						opCode = "83";
					}
					
				
			}
		}
	}
	
	private void getInstructions() throws IOException {
		FileReader fileReader = new FileReader(input);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			line = cleanLine(line);
			String[] tokens = line.split(" ");
			int tokenCount = tokens.length;
			switch(tokenCount) {
				case 1:
					instruction = new Instruction(tokens[0]);
					break;
				case 2:
					instruction = new Instruction(tokens[0], tokens[1]);
					break;
				case 3:
					instruction = new Instruction(tokens[0], tokens[1], tokens[2]);
					break;
			}
			instructions.add(instruction);
		}
		fileReader.close();
	}
	
	private String cleanLine(String line) {
		line = line.replace("\n", "");
		line = line.replace(",", " ");
		line = line.trim();
		return line;
	}
}
