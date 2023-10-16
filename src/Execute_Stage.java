public class Execute_Stage {
	public static PipeliningRegister execute(PipeliningRegister p) {

		PipeliningRegister newP = new PipeliningRegister();
		newP = p;

		String Op = ControlUnit.AluControl(newP.getOpcode(), newP.getControlUnit().getALUop());
		int Operand1 = CPU.registers.load(newP.getRs());
		int Operand2 = CPU.registers.load(newP.getRt());

		if (newP.getControlUnit().getALUSrc().equals("1") || CPU.memory.I_Type.contains(Op)) {
			Operand2 = newP.getImmediate();
		}

		newP.setResultOfALU(ALU.ALU_EXECUTE(Op, Operand1, Operand2));

		int expected_pc = CPU.pc + 4 + (newP.getImmediate() * 4);

		if (ALU.zeroFlag == 1 && newP.getControlUnit().getBranch().equals("1")) {
			CPU.pc = expected_pc;
		} else if (newP.getControlUnit().getJump().equals("1")) {
			CPU.pc = CPU.registers.load(newP.getRs());
		} else {
			CPU.pc += 4;
		}

//		test cases output:
//		zero flag: 0
//		branch address: 0000 0000 0000 0000 0000 0000 0001 1000
//		ALU result/address: 0000 0000 0000 0000 0000 0000 0000 0101
//		register value to write to memory: 0000 0000 0000 0000 0000 0000 0000 0000
//		rt/rd register: 01000
//		WB controls: MemToReg: 1, RegWrite: 1
//		MEM controls: MemRead: 0, MemWrite: 0, Branch: 0	

		System.out.println(PipeliningRegister.Instruction(newP.getInstruction()) + " in Execute Stage");

		System.out.println("zero flag: " + ALU.zeroFlag);
		System.out.println("branch address: " + CPU.convertToBinary(newP.getImmediate()));
		System.out.println("ALU result/address: " + CPU.convertToBinary(newP.getResultOfALU()));
		System.out.println("register value to write to memory: " + CPU.convertToBinary(newP.getWriteToMemory()));

		if (CPU.memory.R_Type.contains(newP.getOpcode())) {
			System.out.println("rd register: " + newP.getRd());
		}
		if (CPU.memory.I_Type.contains(newP.getOpcode())) {
			System.out.println("rt register: " + newP.getRt());
		}

		if (newP.getControlUnit().getMemtoReg().equals("x")) {
			System.out.println(
					"WB controls: MemToReg: don't care" + ", RegWrite: " + newP.getControlUnit().getRegWrite());
		}

		else {
			System.out.println("WB controls: MemToReg: " + newP.getControlUnit().getMemtoReg() + ", RegWrite: "
					+ newP.getControlUnit().getRegWrite());
		}

		System.out.println("MEM controls: MemRead: " + newP.getControlUnit().getMemRead() + ", MemWrite: "
				+ newP.getControlUnit().getMemWrite() + ", Branch: " + newP.getControlUnit().getBranch());

		return newP;

	}
}
