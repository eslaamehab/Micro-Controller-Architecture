
public class PipeliningRegister {

	int pc;
	String[] instruction;
	ControlUnit controlUnit;
	String opcode;
	String rs;
	String rt;
	String rd;
	int immediate;
	int resultOfALU;
	int writeToMemory;
	int readFromMemory;
	String writeRegister;
	String currentStage;

	public PipeliningRegister() {
		instruction = new String[4];
		controlUnit = new ControlUnit();
		currentStage = "";
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public static String Instruction(String[] instruction) {
		String ins = "";
		for (int i = 0; i < instruction.length; i++) {
			ins += instruction[i];
		}
		return ins;
	}

	public String[] getInstruction() {
		return instruction;
	}

	public void setInstruction(String[] instruction) {
		this.instruction = instruction;
	}

	public ControlUnit getControlUnit() {
		return controlUnit;
	}

	public void setControlUnit(ControlUnit controlUnit) {
		this.controlUnit = controlUnit;
	}

	public String getOpcode() {
		return opcode;
	}

	public void setOpcode(String opcode) {
		this.opcode = opcode;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getRt() {
		return rt;
	}

	public void setRt(String rt) {
		this.rt = rt;
	}

	public String getRd() {
		return rd;
	}

	public void setRd(String rd) {
		this.rd = rd;
	}

	public int getImmediate() {
		return immediate;
	}

	public void setImmediate(int immediate) {
		this.immediate = immediate;
	}

	public int getResultOfALU() {
		return resultOfALU;
	}

	public void setResultOfALU(int resultOfALU) {
		this.resultOfALU = resultOfALU;
	}

	public int getWriteToMemory() {
		return writeToMemory;
	}

	public void setWriteToMemory(int writeToMemory) {
		this.writeToMemory = writeToMemory;
	}

	public int getReadFromMemory() {
		return readFromMemory;
	}

	public void setReadFromMemory(int readFromMemory) {
		this.readFromMemory = readFromMemory;
	}

	public String getWriteRegister() {
		return writeRegister;
	}

	public void setWriteRegister(String writeRegister) {
		this.writeRegister = writeRegister;
	}

	public String getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(String currentStage) {
		this.currentStage = currentStage;
	}
}
