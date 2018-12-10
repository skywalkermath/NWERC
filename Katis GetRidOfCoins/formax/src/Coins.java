public class Coins extends ProblemTest {

	static int target;
	static int[] coinsUsed = {0,0,0,0};
	static int[] coins;

	public static void main(String[] args) {
		new Coins().test(1000, "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13");
	}
	
	public static boolean canSolve1(int curTar) {
		curTar = doStuff(3,25, curTar);
		curTar = doStuff(2,10, curTar);
		curTar = doStuff(1,5, curTar);
		curTar = doStuff(0,1, curTar);
		
		if (curTar == 0) return true;
		if(coins[3] > 0 && target > 25) {
			coinsUsed[0] = 0;
			coinsUsed[1] = 0;
			coinsUsed[2] = 0;
			coinsUsed[3] = 0;
			curTar = target;
			curTar = doStuff(3,25, curTar);
			coinsUsed[3] -= 1;
			curTar += 25;
			curTar = doStuff(2,10, curTar);
			curTar = doStuff(1,5, curTar);
			curTar = doStuff(0,1, curTar);
			if (curTar == 0) return true;
		}
		return false;
	}
	
	private static int doStuff(int col, int val, int actingOn) {
		if (actingOn < (coins[col] - coinsUsed[col]) * val) {
			coinsUsed[col] += actingOn/val;
			actingOn = actingOn % val;
		} else {
			actingOn = actingOn - (coins[col] - coinsUsed[col]) * val;
			coinsUsed[col] = coins[col];
		}
		return actingOn;
	}



	@Override
	public void run() throws Exception {
		target = Integer.parseInt(scanner.nextLine());
		String[] line2 = scanner.nextLine().split(" ");
		int[] things = {Integer.parseInt(line2[0]), Integer.parseInt(line2[1]), Integer.parseInt(line2[2]), Integer.parseInt(line2[3])};
		coins = things;
		
		int curTar = target;
		
		
		if (!canSolve1(curTar)) {
			System.out.print("Impossible");
			return;
		}
		
		
		int howManycanSolve = coins[0]-coinsUsed[0]+coins[1]*5-coinsUsed[1]*5+coins[2]*10-coinsUsed[2]*10;
		howManycanSolve = Math.min(coinsUsed[3], howManycanSolve/25) * 25;
		coinsUsed[2] -= howManycanSolve/25;

		
		howManycanSolve = doStuff(2,10, howManycanSolve);
		howManycanSolve = doStuff(1,5, howManycanSolve);
		howManycanSolve = doStuff(0,1, howManycanSolve);
		
		howManycanSolve = coins[0]-coinsUsed[0]+coins[1]*5-coinsUsed[1]*5;
		howManycanSolve = Math.min(coinsUsed[2], howManycanSolve/10) * 10;
		coinsUsed[2] -= howManycanSolve/10;
		
		howManycanSolve = doStuff(1,5, howManycanSolve);
		howManycanSolve = doStuff(0,1, howManycanSolve);
		
		howManycanSolve = coins[0]-coinsUsed[0];
		howManycanSolve = Math.min(coinsUsed[1], howManycanSolve/5) * 5;
		coinsUsed[2] -= howManycanSolve/5;
		
		howManycanSolve = doStuff(0,1,howManycanSolve);
		
		System.out.print(coinsUsed[0] +coinsUsed[1] +coinsUsed[2] +coinsUsed[3]);
	}
}