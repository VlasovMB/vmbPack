package testquickresto.menu.builder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * The type Console input output.
 */
public class ConsoleInputOutput implements InputOutput {

	
	@Override
	public String inputString(String prompt) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		outputLine("\n" + prompt);
		try {
			return br.readLine();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void output(Object obj) {
		System.out.print(obj);
	}

}
