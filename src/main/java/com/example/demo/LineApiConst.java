package com.example.demo;

public final class LineApiConst {

	public static class MESSAGE {
		public static final String CONFIRM_MSG = "について保存しますか参照しますか。";
		public static final String CONFIRM_TITLE = "確認メッセージを送信しました。";
		public static final String KEYWORD_TITLE = "キーワードリストを送信しました。";

		public static final String NG_WORD = "この文字は利用できません。";
		public static final String SAVE_MSG = "の内容を入力してください。";
		public static final String CANCEL = "キャンセルしました。";
		public static final String NO_DATA_MSG = "について保存内容はありません。";
		public static String SAVE_COMPLETE_MSG = "の内容を保存しました。";
	}

	public static class BUTTON {
		public static final String SAVE = "保存";
		public static final String REF = "参照";
		public static final String YES = "はい";
		public static final String NO = "いいえ";
		public static final String CANCEL = "キャンセル";
		public static final String CANCEL2 = "キヤンセル";
		public static final String CANCEL3 = "きゃんせる";
		public static final String CANCEL4 = "きやんせる";
		public static final String CANCEL5 = "やめる";

	}

	// 予約語
	public static class VAL {
		public static final int SHELF_LIFE = 7;
		public static final String NG_WORD = "#保存#参照#キャンセル#";
		public static final String SHARP = "#";
		public static final String[] IGNORE_WORD = {
			"絆ノートとは", "きずなノートとは", "きずなノート", "絆ノート",
			"使い方", "つかいかた",
			"画像にする"
		};
		public static final Object KEYWORD = "キーワード";
	}

	// カルーセルのタイトル
	public static class TITLE{
		public static final String MYSELF = "私について";
		public static final String MYHEALTH = "私の健康について";
		public static final String MYLINK = "私のつながりについて";
		public static final String MYFUTURE = "私の今後について";
		public static final String MYFUTURE2 = "今後について";
		public static final String FORME = "私へ";
	}

	// 保存できるキーワード
	public static class KEYWORD {
		public static final String WORD1 = "わたしのプロフィール";
		public static final String WORD2 = "仕事・学びの記録";
		public static final String WORD3 = "できること";
		public static final String WORD4 = "できないこと";

		public static final String WORD5 = "病歴";
		public static final String WORD6 = "健康診査の記録";
		public static final String WORD7 = "かかりつけ";
		public static final String WORD8 = "薬の記録";

		public static final String WORD9 = "緊急連絡先";
		public static final String WORD10= "大切な家族";
		public static final String WORD11= "お世話になっている人";
		public static final String WORD12= "成年後家人";

		public static final String WORD13= "住まい";
		public static final String WORD14= "医療";
		public static final String WORD15= "介護";
		public static final String WORD16= "生活費";


		public static final String WORD17= "財産";
		public static final String WORD18= "遺言";
		public static final String WORD19= "形見分け";
		public static final String WORD20= "葬式・墓";

		public static final String WORD22= "わたしへのメッセージ";
		public static final String WORD21= "保険証など大事なものの場所";

	}


	private final static String KAKKO1 = "「";
	private final static String KAKKO2 = "」";

    public static final String setKakko(String str) {
    	return KAKKO1 + str + KAKKO2;
    }

}
