import java.util.Scanner;
import java.util.Vector;

public class CPU {

	static int pc = 0;
	static Memory memory = new Memory();
	static Registers registers = new Registers();
//	static ControlUnit controlUnit = new ControlUnit();
//	static String opcode;
//	static String rs;
//	static String rt;
//	static String rd;
//	static int immediate;
//	static int resultOfALU;
//	static int writeToMemory;
//	static int readFromMemory;
//	static String writeRegister;

	public static String convertToBinary(int num) {
		String s = Integer.toBinaryString(num);
		while (s.length() < 16) {
			s = "0" + s;
		}
		return s;
	}

	public static void clock_cycles() throws Exceptions {

		System.out.println();
		System.out.println();
		System.out.println("fetching in the first cycle");
//		String[] instruction = Fetch_Stage.fetch(pc);
		System.out.println();
		System.out.println();
		System.out.println("decoding in the second cycle");
//		Decode_Stage.decode(instruction);
		System.out.println();
		System.out.println();
		System.out.println("executing in the third cycle");
//		Execute_Stage.execute();
		System.out.println();
		System.out.println();
		System.out.println("memory in the fourth cycle");
//		Memory_Stage.memory();
		System.out.println();
		System.out.println();
		System.out.println("write back in the fifth cycle");
//		WriteBack_Stage.writeBack();
	}

	public static void main(String[] args) throws Exceptions {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter number of instructions in the program: ");
		int numberOfInstructions = sc.nextInt();

		String[] program = new String[numberOfInstructions * 4];
		System.out.println("Enter the program:");
		for (int i = 0; i < numberOfInstructions * 4; i++) {
			program[i] = sc.next();
		}

		Memory memory = new Memory(program);
		CPU.memory = memory;

//		for (int i = 0; i < program.length / 4; i++) {
//			clock_cycles();
//			System.out.println("===================================");
//		}

		//////////////////////// pipelining\\\\\\\\\\\\\\\\\\\\\\\

		int cycles = numberOfInstructions + 4;

		Vector<String> stages = new Vector<String>();
		stages.add("fetch");
		stages.add("decode");
		stages.add("execute");
		stages.add("memory");
		stages.add("writeback");
		stages.add("done");

		Vector<PipeliningRegister> instructions = new Vector<PipeliningRegister>();
		for (int i = 0; i < program.length / 4; i++) {
			PipeliningRegister p = new PipeliningRegister();
			instructions.add(i, p);
		}

		for (int i = 0; i < cycles; i++) {

			System.out.println("After " + (i + 1) + " clock cycles:");
			System.out.println();

			if (i == 0) {
				instructions.set(i, Fetch_Stage.fetch(pc));
				instructions.get(i).setCurrentStage("fetch");
				System.out.println();
				System.out.println();
			}

			for (int j = 0; j < program.length / 4; j++) {

				if (instructions.get(j).getCurrentStage().equals("fetch") && j != 0) {
					instructions.set(j, Fetch_Stage.fetch(pc));
					System.out.println();
					System.out.println();
				}

				else if (instructions.get(j).getCurrentStage().equals("decode")) {
					instructions.set(j, Decode_Stage.decode(instructions.get(j)));
					System.out.println();
					System.out.println();
				}

				else if (instructions.get(j).getCurrentStage().equals("execute")) {
					instructions.set(j, Execute_Stage.execute(instructions.get(j)));
					System.out.println();
					System.out.println();
				}

				else if (instructions.get(j).getCurrentStage().equals("memory")) {
					instructions.set(j, Memory_Stage.memory(instructions.get(j)));
					System.out.println();
					System.out.println();
				}

				else if (instructions.get(j).getCurrentStage().equals("writeback")) {
					instructions.set(j, WriteBack_Stage.writeBack(instructions.get(j)));
					System.out.println();
					System.out.println();
				}

			}

			// fetch decode execute memory writeback
			for (int j2 = 0; j2 < program.length / 4; j2++) {
				if (instructions.get(j2).getCurrentStage().equals("")) {
					instructions.get(j2).setCurrentStage("fetch");
					break;
				}
				instructions.get(j2)
						.setCurrentStage(stages.get(stages.indexOf(instructions.get(j2).getCurrentStage()) + 1));
			}

		}

	}

}
