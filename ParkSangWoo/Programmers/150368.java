class Solution {

	static int N, M, answer0, answer1, emoticonPlus, totalPrice;
	static int[] salePercent;
	static int[][] user;

	public int[] solution(int[][] users, int[] emoticons) {
		N = users.length;
		M = emoticons.length;
		user = users;
		answer0 = 0;
		answer1 = 1;
		salePercent = new int[M];
		permutation(0, emoticons);

		return new int[] { answer0, answer1 };
	}

	public static void permutation(int cnt, int[] emoticon) {
		if (cnt == M) {
			totalPrice = 0;
			emoticonPlus = 0;
			for (int i = 0; i < N; i++) {
				int price = 0;
				for (int j = 0; j < M; j++) {
					if (salePercent[j] < user[i][0]) {
						continue;
					}
					price += emoticon[j];
				}
				if (price < user[i][1]) {
					totalPrice += price;
					continue;
				}
				emoticonPlus++;
			}
			if (emoticonPlus < answer0) {
				return;
			}
			if (emoticonPlus == answer0) {
				if (totalPrice <= answer1) {
					return;
				}
				answer1 = totalPrice;
			}
			answer0 = emoticonPlus;
			answer1 = totalPrice;
			return;
		}
		for (int i = 0; i < 4; i++)
			switch (i) {
			case 0:
				emoticon[cnt] = emoticon[cnt] * 9 / 10;
				salePercent[cnt] = 10;
				permutation(cnt + 1, emoticon);
				emoticon[cnt] = emoticon[cnt] * 10 / 9;
				break;
			case 1:
				emoticon[cnt] = emoticon[cnt] * 8 / 10;
				salePercent[cnt] = 20;
				permutation(cnt + 1, emoticon);
				emoticon[cnt] = emoticon[cnt] * 10 / 8;
				break;
			case 2:
				emoticon[cnt] = emoticon[cnt] * 7 / 10;
				salePercent[cnt] = 30;
				permutation(cnt + 1, emoticon);
				emoticon[cnt] = emoticon[cnt] * 10 / 7;
				break;
			case 3:
				emoticon[cnt] = emoticon[cnt] * 6 / 10;
				salePercent[cnt] = 40;
				permutation(cnt + 1, emoticon);
				emoticon[cnt] = emoticon[cnt] * 10 / 6;
				break;
			}
	}
}
