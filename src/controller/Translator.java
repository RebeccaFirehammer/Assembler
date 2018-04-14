package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Assembly;
import model.OpCode;

public class Translator{

	File input;
	List <String> instructions;
	Assembly assembly;
	OpCode opCode;
	
	public Translator(File input) throws IOException {
		this.input = input;
		instructions = new ArrayList<String>();
		getInstructions();
	}
	
	private List<String> getInstructions() throws IOException {
		FileReader fileReader = new FileReader(input);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			instructions.add(line);
			System.out.println(line);
		}
		fileReader.close();
		return instructions;
	}
	
}
