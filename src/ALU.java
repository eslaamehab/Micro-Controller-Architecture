//import java.util.*;

public class ALU {

	static int zeroFlag;

	public static int ALU_EXECUTE(String Op, int Operand1, int Operand2) {

		zeroFlag = 0;

		if (Op.equals("0001") || Op.equals("1111") || Op.equals("0101")) {
			return Operand1 + Operand2;
		}

		else if (Op.equals("0000")) {
			if (Operand1 - Operand2 == 0)
				zeroFlag = 1;
			return Operand1 - Operand2;
		}

		else if (Op.equals("0011")) {
			return Operand1 * Operand2;
		}

		else if (Op.equals("0100")) {
			return Operand1 | Operand2;
		}

		else if (Op.equals("1101")) {
			return Operand1 & Operand2;
		}

		else if (Op.equals("0110")) {
			int x = Operand1 >> Operand2;
			return x;
		}

		else if (Op.equals("0111")) {
			int x = Operand1 << Operand2;
			return x;
		}

		else if (Op.equals("1011")) {
			if (Operand1 < Operand2)
				zeroFlag = 1;
			return Operand1 - Operand2;
		}

		else if (Op.equals("1100")) {
			if (Operand1 < Operand2)
				return 1;
			return 0;
		}

		return 0;
	}

}
