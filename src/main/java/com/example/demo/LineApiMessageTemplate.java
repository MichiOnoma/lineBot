package com.example.demo;

import java.util.Arrays;
import java.util.List;

import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.CarouselColumn;
import com.linecorp.bot.model.message.template.CarouselTemplate;
import com.linecorp.bot.model.message.template.ConfirmTemplate;

public final class LineApiMessageTemplate {

	// 保存・参照の選択肢を出すメッセージ
	public static TemplateMessage getConfirmTemp(String text) {
		return 	new TemplateMessage(LineApiConst.MESSAGE.CONFIRM_TITLE,
					new ConfirmTemplate(
							LineApiConst.setKakko(text) + LineApiConst.MESSAGE.CONFIRM_MSG,
							new MessageAction(LineApiConst.BUTTON.REF, LineApiConst.BUTTON.REF),
							new MessageAction(LineApiConst.BUTTON.SAVE, LineApiConst.BUTTON.SAVE)
					)
				);
	}

	public static TemplateMessage getKeywordTemp() {
		List<CarouselColumn> list = Arrays.asList(
		new CarouselColumn(null, LineApiConst.TITLE.MYSELF, LineApiConst.MESSAGE.SONOTA,
				Arrays.asList(
						new MessageAction(LineApiConst.KEYWORD.WORD, LineApiConst.KEYWORD.WORD),
						new MessageAction(LineApiConst.KEYWORD.WORD1, LineApiConst.KEYWORD.WORD1),
						new MessageAction(LineApiConst.KEYWORD.WORD2, LineApiConst.KEYWORD.WORD2)
						)),
		new CarouselColumn(LineApiConst.URL.MYSELF_URL, LineApiConst.TITLE.MYSELF2,LineApiConst.KEYWORD.SONOTA,
				Arrays.asList(
						new MessageAction(LineApiConst.KEYWORD.WORD3, LineApiConst.KEYWORD.WORD3),
						new MessageAction(LineApiConst.KEYWORD.WORD4, LineApiConst.KEYWORD.WORD4),
						new MessageAction(LineApiConst.KEYWORD.WORD5, LineApiConst.KEYWORD.WORD5)
						)),
		new CarouselColumn(LineApiConst.URL.MYHEALTH_URL, LineApiConst.TITLE.MYHEALTH, LineApiConst.KEYWORD.SONOTA,
				Arrays.asList(
						new MessageAction(LineApiConst.KEYWORD.WORD6, LineApiConst.KEYWORD.WORD6),
						new MessageAction(LineApiConst.KEYWORD.WORD7, LineApiConst.KEYWORD.WORD7),
						new MessageAction(LineApiConst.KEYWORD.WORD8, LineApiConst.KEYWORD.WORD8)
						)),
		new CarouselColumn(LineApiConst.URL.MYLINK_URL, LineApiConst.TITLE.MYLINK, LineApiConst.KEYWORD.SONOTA,
				Arrays.asList(
						new MessageAction(LineApiConst.KEYWORD.WORD9, LineApiConst.KEYWORD.WORD9),
						new MessageAction(LineApiConst.KEYWORD.WORD10, LineApiConst.KEYWORD.WORD10),
						new MessageAction(LineApiConst.KEYWORD.WORD11, LineApiConst.KEYWORD.WORD11)
						)),
		new CarouselColumn(LineApiConst.URL.FUTURE_URL, LineApiConst.TITLE.MYFUTURE, LineApiConst.KEYWORD.SONOTA,
				Arrays.asList(
						new MessageAction(LineApiConst.KEYWORD.WORD12, LineApiConst.KEYWORD.WORD12),
						new MessageAction(LineApiConst.KEYWORD.WORD13, LineApiConst.KEYWORD.WORD13),
						new MessageAction(LineApiConst.KEYWORD.WORD14, LineApiConst.KEYWORD.WORD14)
						)),
		new CarouselColumn(LineApiConst.URL.FUTURE_URL, LineApiConst.TITLE.MYFUTURE2, LineApiConst.KEYWORD.SONOTA,
				Arrays.asList(
						new MessageAction(LineApiConst.KEYWORD.WORD15, LineApiConst.KEYWORD.WORD15),
						new MessageAction(LineApiConst.KEYWORD.WORD16, LineApiConst.KEYWORD.WORD16),
						new MessageAction(LineApiConst.KEYWORD.SONOTA, LineApiConst.KEYWORD.SONOTA)
						)),
		new CarouselColumn(LineApiConst.URL.FUTURE_URL, LineApiConst.TITLE.FUTURE, LineApiConst.KEYWORD.SONOTA,
				Arrays.asList(
						new MessageAction(LineApiConst.KEYWORD.WORD18, LineApiConst.KEYWORD.WORD18),
						new MessageAction(LineApiConst.KEYWORD.WORD19, LineApiConst.KEYWORD.WORD19),
						new MessageAction(LineApiConst.KEYWORD.SONOTA, LineApiConst.KEYWORD.SONOTA)
						)),
		new CarouselColumn(LineApiConst.URL.FORME_URL, LineApiConst.TITLE.FORME, LineApiConst.KEYWORD.SONOTA,
				Arrays.asList(
						new MessageAction(LineApiConst.KEYWORD.WORD20, LineApiConst.KEYWORD.WORD20),
						new MessageAction(LineApiConst.KEYWORD.WORD21, LineApiConst.KEYWORD.WORD21),
						new MessageAction(LineApiConst.KEYWORD.SONOTA, LineApiConst.KEYWORD.SONOTA)
						))
		);
		CarouselTemplate cal = new CarouselTemplate(list);
		return 	new TemplateMessage(LineApiConst.MESSAGE.KEYWORD_TITLE, cal);
	}
}
