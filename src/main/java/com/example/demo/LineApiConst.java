package com.example.demo;

public final class LineApiConst {

	public static class MESSAGE {
		public static final String CONFIRM_MSG = "について保存しますか参照しますか。";
		public static final String CONFIRM_TITLE = "確認メッセージを送信しました。";
		public static final String NG_WORD = "この文字は利用できません。";
		public static final String SAVE_MSG = "の内容を入力してください。";
		public static final String CANCEL = "キャンセルしました。";
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

	public static class VAL {
		public static final int SHELF_LIFE = 7;
		public static final String NG_WORD = "#保存#参照#キャンセル#";
		public static final String SHARP = "#";
		public static final String[] IGNORE_WORD = {
			"絆ノートとは", "きずなノートとは", "きずなノート", "絆ノート",
			"使い方", "つかいかた",
			"キーワード"
		};
	}


	private final static String KAKKO1 = "「";
	private final static String KAKKO2 = "」";

    public static final String setKakko(String str) {
    	return KAKKO1 + str + KAKKO2;
    }

}
