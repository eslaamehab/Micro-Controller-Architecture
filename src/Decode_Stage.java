public class Decode_Stage {

	public static PipeliningRegister decode(PipeliningRegister p) {

		PipeliningRegister newP = new PipeliningRegister();
		newP = p;

		int nextPc = newP.getPc() + 4;
		String nextPcInBinary = CPU.convertToBinary(nextPc);

		String Opcode = newP.getInstruction()[0];

		ControlUnit c = new ControlUnit();
		c.setSignals(Opcode);

		newP.setControlUnit(c);

		newP.setOpcode(Opcode);

		newP.setRs(newP.getInstruction()[1]);

		newP.setRt(newP.getInstruction()[2]);

		newP.setRd(newP.getInstruction()[3]);

		newP.setImmediate(Integer.parseInt(newP.getRd(), 2));

		if (newP.getControlUnit().getRegDst().equals("0")) {
			newP.setRd(newP.getInstruction()[2]);
		}

		newP.setWriteRegister(newP.getRd());
		newP.setWriteToMemory(CPU.registers.load(newP.getRt()));

//		test cases output:
//		read data 1: 0000 0000 0000 0000 0000 0000 0000 0000
//		read data 2: 0000 0000 0000 0000 0000 0000 0000 0000
//		sign-extend: 0000 0000 0000 0000 0000 0000 0000 0101
//		Next PC: 0000 0000 0000 0000 0000 0000 0000 0100
//		rt: 01001
//		rd: don’t care
//		WB controls: MemToReg: 1, RegWrite: 1
//		MEM controls: MemRead: 0, MemWrite: 0, Branch: 0
//		EX controls: RegDest: 0, ALUOp: 010, ALUSrc: 1

		System.out.println(PipeliningRegister.Instruction(newP.getInstruction()) + " in Decode Stage");

		System.out.println("read data 1: " + CPU.convertToBinary(CPU.registers.load(newP.getRs())));
		System.out.println("read data 2: " + CPU.convertToBinary(CPU.registers.load(newP.getRt())));
		System.out.println("sign-extend: " + CPU.convertToBinary(newP.getImmediate()));
		System.out.println("Next PC: " + nextPcInBinary);

		System.out.println("rt: " + newP.getRt());

		if (CPU.memory.R_Type.contains(newP.getOpcode())) {
			System.out.println("rd: " + newP.getRd());
		}
		if (CPU.memory.I_Type.contains(newP.getOpcode())) {
			System.out.println("rd: " + "don't care");
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
		System.out.println("EX controls: RegDest: " + newP.getControlUnit().getRegDst() + ", ALUOp: "
				+ newP.getControlUnit().getALUop() + ", ALUSrc: " + newP.getControlUnit().getALUSrc());

		return newP;

	}
}