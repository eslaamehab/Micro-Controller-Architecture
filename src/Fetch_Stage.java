public class Fetch_Stage {
	public static PipeliningRegister fetch(int pc) throws Exceptions {

		PipeliningRegister p = new PipeliningRegister();
		p.setPc(pc);

		String instruction = "";
		for (int i = 0; i < CPU.memory.getInstruction(pc).length; i++) {
			instruction += CPU.memory.getInstruction(pc)[i];
			p.instruction[i] = CPU.memory.getInstruction(pc)[i];
		}

		p.setInstruction(CPU.memory.getInstruction(pc));

//		test cases output:
//		Next PC: 0000 0000 0000 0000 0000 0000 0000 0100
//		Instruction: 0010 0000 0000 1000 0000 0000 0000 0101

		System.out.println(PipeliningRegister.Instruction(p.getInstruction()) + " in Fetch Stage");

		int nextPc = pc + 4;
		String nextPcInBinary = CPU.convertToBinary(nextPc);

		System.out.println("Next Pc: " + nextPcInBinary);
		System.out.println("Instruction: " + instruction);

		return p;

	}
}