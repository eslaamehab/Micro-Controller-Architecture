public class Memory_Stage {

	public static PipeliningRegister memory(PipeliningRegister p) {

		PipeliningRegister newP = new PipeliningRegister();
		newP = p;

		if (newP.getControlUnit().getMemWrite().equals("1")) {
			CPU.memory.storeMemory(newP.getResultOfALU(), CPU.registers.load(newP.rt));
		}

		if (newP.getControlUnit().getMemRead().equals("1")) {
			newP.setReadFromMemory(CPU.memory.loadMemory(newP.getResultOfALU()));
		}

//		test cases output:
//		ALU result: 0000 0000 0000 0000 0000 0000 0000 0000
//		memory word read: 1111 1111 1111 1111 0000 0000 0000 0000
//		rt/rd field: 01001
//		WB controls: MemToReg: 0, RegWrite: 1

		System.out.println(PipeliningRegister.Instruction(newP.getInstruction()) + " in Memory Stage");

		System.out.println("ALU result: " + CPU.convertToBinary(newP.getResultOfALU()));

		if (newP.getControlUnit().getMemRead().equals("1")) {
			System.out.println("memory word read: " + CPU.convertToBinary(newP.getReadFromMemory()));
		} else {
			System.out.println("memory word read: don't care");
		}

		if (CPU.memory.R_Type.contains(newP.getOpcode())) {
			System.out.println("rd field: " + newP.getRd());
		}
		if (CPU.memory.I_Type.contains(newP.getOpcode())) {
			System.out.println("rt field: " + newP.getRt());
		}

		if (newP.getControlUnit().getMemtoReg().equals("x")) {
			System.out.println(
					"WB controls: MemToReg: don't care" + ", RegWrite: " + newP.getControlUnit().getRegWrite());
		}

		else {
			System.out.println("WB controls: MemToReg: " + newP.getControlUnit().getMemtoReg() + ", RegWrite: "
					+ newP.getControlUnit().getRegWrite());
		}

		return newP;

	}
}
