import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Scanner;

public abstract class ProblemTest {

	protected Scanner scanner = null;
	
	public final void test(int maxTime, String...testCases) {
		for(int i = 0; i < testCases.length; i++) {
			if(i != 0) System.out.println();
			try {
				test(testCases[i], maxTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private final void test(String testName, int maxTime) throws Exception {
		File input = new File(testName + ".in");
		if(!input.exists()) {
			System.out.println("Unable to find input file " + input.getName());
			return;
		}
		File output = new File(testName + ".ans");
		if(!output.exists()) {
			System.out.println("Unable to find output file " + output.getName());
			return;
		}
		
		System.out.println("Running test case " + testName + "...");
		byte[] inputBytes = Files.readAllBytes(input.toPath());
		byte[] outputBytes = Files.readAllBytes(output.toPath());
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		scanner = new Scanner(new ByteArrayInputStream(inputBytes));
		
		PrintStream oldOut = System.out;
		
		PrintStream stream = new PrintStream(out);
		System.setOut(stream);
		
		long t1 = System.currentTimeMillis();
		run();
		long t2 = System.currentTimeMillis();
		
		stream.flush();
		
		out.flush();
		out.close();
		
		System.setOut(oldOut);
		
		byte[] bytes = out.toByteArray();
		
		String programOutput = new String(bytes).trim().replace("\r", "");
		String expectedOutput = new String(outputBytes).trim().replace("\r", "");
		
		boolean tookTooLong = (t2 - t1) > maxTime;
		
		if(programOutput.equals(expectedOutput)) {
			if(tookTooLong) System.out.println("Correct output, but took " + ((t2 - t1) - maxTime) + " milliseconds too long");
			else System.out.println("Passed");
		} else {
			if(tookTooLong) System.out.println("Incorrect output and took " +  ((t2 - t1) - maxTime) + " milliseconds too long");
			else System.out.println("Incorrect output:");
			
			compareOutput(programOutput, expectedOutput);
		}
	}
	
	private void compareOutput(String programOut, String expectedOut) {
		String[] programLines = programOut.split("\n");
		String[] outputLines = expectedOut.split("\n");
		
		int longestLine = 0;
		for(String line : programLines) {
			if(line.length() > longestLine) longestLine = line.length();
		}
		int max = Math.max(programLines.length, outputLines.length);
		for(int i = 0; i < max; i++) {
			String programLine = i >= programLines.length ? "" : programLines[i];
			String outputLine = i >= outputLines.length ? "" : outputLines[i];

			if(programLine.equals(outputLine)) continue;
			
			int spaces = (longestLine - programLine.length()) + 3;
			char[] charArr = new char[spaces];
			for(int j = 0; j < spaces; j++) charArr[j] = ' ';
			
			String str = (i + 1) + ". ";
			while((max + ". ").length() != str.length()) str += " ";
			
			System.out.println(str + programLine + new String(charArr) + outputLine);
		}
	}
	
	public abstract void run() throws Exception;
}
