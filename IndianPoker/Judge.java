package IndianPoker;

import java.util.stream.IntStream;

public class Judge {
	// 処理結果の勝つ価値の数の参照
	public int maxNum;

	public void cardJudge(int a) {
		this.maxNum = a;
	}

	public void cardJudge(int a, int b) {
		cardJudge(a, b, 0);
	}

	public void cardJudge(int a, int b, int c) {
		cardJudge(a, b, c, 0);
	}

	public void cardJudge(int a, int b, int c, int d) {
		cardJudge(a, b, c, d, 0);
	}

	public void cardJudge(int a, int b, int c, int d, int e) {
		cardJudge(a, b, c, d, e, 0);
	}

	public void cardJudge(int a, int b, int c, int d, int e, int f) {
		int[] nums = { a, b, c, d, e, f };
		boolean has14 = IntStream.of(nums).anyMatch(n -> n == 14);// 後で調べる。
		boolean has1 = IntStream.of(nums).anyMatch(n -> n == 1);
		if (has14 && has1) {
			// JOKER（価値：14）は3（価値：1）に負ける
			this.maxNum = 1;
		} else {
			// 引数aを代入
			int max = nums[0];
			// 繰り返し比較し、最大数を代入し
			for (int n : nums) {
				if (n > max) {
					max = n;
				}
			}
			this.maxNum = max;
		}
	}
}