public class WriteBack_Stage {
	public static PipeliningRegister writeBack(PipeliningRegister p) throws Exceptions {

		PipeliningRegister newP = new PipeliningRegister();
		newP = p;

		int writedata;

		if (newP.getControlUnit().getMemtoReg().equals("1")) {
			writedata = newP.getReadFromMemory();
		} else {
			writedata = newP.getResultOfALU();
		}

		if (newP.getControlUnit().getRegWrite().equals("1")) {
			CPU.registers.store(newP.getWriteRegister(), writedata);
		}

//		test cases output:
//		Lw $t1, 0($s0) in WB stage

		System.out.println(PipeliningRegister.Instruction(newP.getInstruction()) + " in WriteBack stage");

		return newP;
	}
}