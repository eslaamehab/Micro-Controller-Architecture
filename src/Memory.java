import java.util.Vector;

public class Memory {

	String[] Mem = new String[4096];
	Vector<String> R_Type = new Vector<String>();
	Vector<String> I_Type = new Vector<String>();
	int last_instruction;

	public Memory() {

	}

	public Memory(String[] instructions) {

		for (int i = 0; i < 1024; i++) {

			if (i < instructions.length) {
				Mem[i] = instructions[i];
				last_instruction = i;
			}

			else {
				Mem[i] = "0000";
			}
		}

		R_Type.add("0000");
		R_Type.add("0001");
		R_Type.add("0100");
		R_Type.add("0110");
		R_Type.add("0111");
		R_Type.add("0101");
		R_Type.add("0011");

		I_Type.add("1111");
		I_Type.add("1101");
		I_Type.add("1000");
		I_Type.add("1011");
		I_Type.add("1010");
		I_Type.add("1001");
		I_Type.add("1100");

	}

	public String[] getInstruction(int address) throws Exceptions {
		if (address > last_instruction) {
			throw new Exceptions("There is no instruction in this address.");
		}

		String[] ins = new String[4];

		for (int i = 0; i < 4; i++) {
			ins[i] = Mem[address + i];
		}
		return ins;

	}

	public int loadMemory(int address) {
		int data;
		String x = "";
		for (int i = 0; i < 4; i++) {
			x = x + Mem[address + last_instruction + i + 1];
		}
		data = Integer.parseInt(x, 2);

		return data;
	}

	public void storeMemory(int address, int data) {
		String StringData = CPU.convertToBinary(data);
		int start = 0;
		for (int i = 0; i < 4; i++) {
			Mem[address + last_instruction + i + 1] = StringData.substring(start, start + 4);
			start += 4;
		}
	}

}
