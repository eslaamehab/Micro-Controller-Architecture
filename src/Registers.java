import java.util.HashMap;
import java.util.Map;

public class Registers {

	Map<String, String> RegName = new HashMap<>();
	Map<String, String> RegValue = new HashMap<>();

	public Registers() {

		RegName.put("0000", "$zero");
		RegName.put("0001", "$at");
		RegName.put("0010", "$v0");
		RegName.put("0011", "$a0");
		RegName.put("0100", "$t0");
		RegName.put("0101", "$t1");
		RegName.put("0110", "$t2");
		RegName.put("0111", "$s0");
		RegName.put("1000", "$s1");
		RegName.put("1001", "$s2");
		RegName.put("1010", "$k0");
		RegName.put("1011", "$k1");
		RegName.put("1100", "$gp");
		RegName.put("1101", "$sp");
		RegName.put("1110", "$fp");
		RegName.put("1111", "$ra");

		RegValue.put("0000", "0000000000000000");
		RegValue.put("0001", "0000000000000000");
		RegValue.put("0010", "0000000000000011");
		RegValue.put("0011", "0000000000000000");
		RegValue.put("0100", "0000000000000000");
		RegValue.put("0101", "0000000000000000");
		RegValue.put("0110", "0000000000000000");
		RegValue.put("0111", "0000000000000000");
		RegValue.put("1000", "0000000000000000");
		RegValue.put("1001", "0000000000000000");
		RegValue.put("1010", "0000000000000000");
		RegValue.put("1011", "0000000000000000");
		RegValue.put("1100", "0000000000000000");
		RegValue.put("1101", "0000000000000000");
		RegValue.put("1110", "0000000000000100");
		RegValue.put("1111", "0000000000000001");

	}

	public void store(String address, int data) throws Exceptions {
		if (address.equals("0000")) {
			throw new Exceptions("Can't change the value of $zero");
		}
		String StringData = CPU.convertToBinary(data);
		RegValue.put(address, StringData);
	}

	public int load(String address) {
		int value = Integer.parseInt(RegValue.get(address), 2);
		return value;
	}

}
