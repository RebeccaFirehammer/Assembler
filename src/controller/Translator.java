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
			String destination;
			switch(command) {
				case "mov":
					switch(dstSrcPattern(instruction)){
						case 0:
							opCode = "80";
							break;
						case 1:
							opCode = "81";
							break;
						case 2:
							opCode = "82";
							break;
						case 3:
							opCode = "83";
							break;
					}
					break;
				case "addc":
					switch(dstSrcPattern(instruction)){
						case 0:
							opCode = "10";
							break;
						case 1:
							opCode = "11";
							break;
						case 2:
							opCode = "12";
							break;
						case 3:
							opCode = "13";
							break;
					}
					break;
				case "subb":
					switch(dstSrcPattern(instruction)){
						case 0:
							opCode = "20";
							break;
						case 1:
							opCode = "21";
							break;
						case 2:
							opCode = "22";
							break;
						case 3:
							opCode = "23";
							break;
					}
					break;
				case "cmp":
					switch(dstSrcPattern(instruction)){
						case 0:
							opCode = "30";
							break;
						case 1:
							opCode = "31";
							break;
						case 2:
							opCode = "32";
							break;
						case 3:
							opCode = "33";
							break;
					}
					break;
				case "not":
					destination = instruction.getDestination();
					if (destination.contains("["))
						opCode = "43";
					else
						opCode = "40";
					break;
				case "and":
					switch(dstSrcPattern(instruction)){
						case 0:
							opCode = "50";
							break;
						case 1:
							opCode = "51";
							break;
						case 2:
							opCode = "52";
							break;
						case 3:
							opCode = "53";
							break;
					}
				break;
				case "or":
					switch(dstSrcPattern(instruction)){
						case 0:
							opCode = "60";
							break;
						case 1:
							opCode = "61";
							break;
						case 2:
							opCode = "62";
							break;
						case 3:
							opCode = "63";
							break;
					}
					break;
				case "xor":
					switch(dstSrcPattern(instruction)){
						case 0:
							opCode = "70";
							break;
						case 1:
							opCode = "71";
							break;
						case 2:
							opCode = "72";
							break;
						case 3:
							opCode = "73";
							break;
					}
					break;
				case "jmp":
					destination = instruction.getDestination();
					if (destination.contains("$"))
						opCode = "B9";
					else
						opCode = "B8";
					break;
				case "jlo":
					opCode = "D6";
					break;
				case "jhs":
					opCode = "D7";
					break;
				case "jeq":
					opCode = "D8";
					break;
				case "jne":
					opCode = "D9";
					break;
				case "jmi":
					opCode = "DA";
					break;
				case "jpl":
					opCode = "DB";
					break;
				case "nop":
					opCode = "E0";
					break;
					
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
	
	private int dstSrcPattern(Instruction instruction){
		String source = instruction.getSource();
		String destination = instruction.getDestination();
		if(destination.contains("[")) {
			return 3;
		}else {
			if(source.contains("[")) {
				return 2;
			}else if(source.contains("$")) {
				return 1;
			}else {
				return 0;
			}
		}
	}
}
