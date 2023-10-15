
public class ControlUnit {
	String ALUSrc;
	String ALUop;
	String MemRead;
	String MemWrite;
	String RegWrite;
	String MemtoReg;
	String RegDst;
	String Branch;
	String Jump;

	public void setSignals(String opcode) {
		switch (opcode) {
		case "1000": // load word
			ALUSrc = "1";
			ALUop = "00";
			MemRead = "1";
			MemWrite = "0";
			RegWrite = "1";
			MemtoReg = "1";
			RegDst = "0";
			Branch = "0";
			Jump = "0";
			break;
		case "1001": // store word
			ALUSrc = "1";
			ALUop = "00";
			MemRead = "0";
			MemWrite = "1";
			RegWrite = "0";
			MemtoReg = "x";
			RegDst = "x";
			Branch = "0";
			Jump = "0";
			break;
		case "1010": // branch equal
			ALUSrc = "0";
			ALUop = "01";
			MemRead = "0";
			MemWrite = "0";
			RegWrite = "0";
			MemtoReg = "x";
			RegDst = "x";
			Branch = "1";
			Jump = "0";
			break;
		case "0101": // jump
			ALUSrc = "x";
			ALUop = "xx";
			MemRead = "0";
			MemWrite = "0";
			RegWrite = "0";
			MemtoReg = "x";
			RegDst = "x";
			Branch = "0";
			Jump = "1";
			break;
		default: // R-type
			if (CPU.memory.R_Type.contains(opcode)) {
				ALUSrc = "0";
				ALUop = "10";
				MemRead = "0";
				MemWrite = "0";
				RegWrite = "1";
				MemtoReg = "0";
				RegDst = "1";
				Branch = "0";
				Jump = "0";
				break;
			}
			if (CPU.memory.I_Type.contains(opcode)) {
				ALUSrc = "1";
				ALUop = "10";
				MemRead = "0";
				MemWrite = "0";
				RegWrite = "1";
				MemtoReg = "0";
				RegDst = "0";
				Branch = "0";
				Jump = "0";
				break;
			}

		}
	}

	public static String AluControl(String opcode, String AluOp) {
		if (AluOp.equals("00"))
			return "0001";
		else if (AluOp.equals("01"))
			return "0000";
		return opcode;
	}

	public String getALUSrc() {
		return ALUSrc;
	}

	public String getALUop() {
		return ALUop;
	}

	public String getMemRead() {
		return MemRead;
	}

	public String getMemWrite() {
		return MemWrite;
	}

	public String getRegWrite() {
		return RegWrite;
	}

	public String getMemtoReg() {
		return MemtoReg;
	}

	public String getRegDst() {
		return RegDst;
	}

	public String getBranch() {
		return Branch;
	}

	public String getJump() {
		return Jump;
	}

}
