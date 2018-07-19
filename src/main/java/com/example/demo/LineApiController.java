package com.example.demo;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import com.example.demo.domain.KeyWord;
import com.example.demo.service.LineApiService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class LineApiController {
    @Autowired
    private LineMessagingClient lineMessagingClient;
    @Autowired
    LineApiService itemService;

    private Logger log = LoggerFactory.getLogger(LineApiController.class);


	@EventMapping
	public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
		ZonedDateTime time = ZonedDateTime.now(ZoneId.systemDefault());
		log.info("****************CleanUp!!!!" + time.toString());

		itemService.cleanUp(); //クリーンアップ
		handleTextContent(event.getReplyToken(), event.getMessage(), event);
	}

	private void handleTextContent(String replyToken,  TextMessageContent content, MessageEvent<TextMessageContent> event)
			throws Exception {
		String text = content.getText();
		String userId = event.getSource().getUserId();
		log.info("Got text message from {}: {}", replyToken, text);

		// キーワードメッセージを返したものには反応しないようにする
		for (int i= 0; i < LineApiConst.VAL.IGNORE_WORD.length; i++) {
			if (text.equals(LineApiConst.VAL.IGNORE_WORD[i])) return;
		}


		// 初めてかどうか見る
		List<KeyWord> list = getLastWord(userId);
		if(list.size() == 0) {
			// NGワードチェック
			if (LineApiConst.VAL.SHARP.contains(text))
				this.replyText(replyToken, LineApiConst.MESSAGE.NG_WORD);

			// 文字セットと確認ダイアログを返す
			setKeyWord(replyToken, userId, text);
			
		} else {
        	String content_str = list.get(0).getContent();

	        switch (text) {
	        	// 保存
	        	case LineApiConst.BUTTON.SAVE : {
	        		saveKeyWord(replyToken, userId, content_str);
	        		break;
	        	}
				// 参照
	        	case LineApiConst.BUTTON.REF : {
	        		refKeyWord(replyToken, userId, content_str);
	        		break;
	        	}
	        	// キャンセル
	        	case LineApiConst.BUTTON.CANCEL :
	        	case LineApiConst.BUTTON.CANCEL2 :
	        	case LineApiConst.BUTTON.CANCEL3 :
	        	case LineApiConst.BUTTON.CANCEL4 :
	        	case LineApiConst.BUTTON.CANCEL5 :
	        		cancelKeyWord(replyToken, userId);
	        		break;

	        	// その他
	        	default:
	        		// 保存内容を入力していたとき
	        		int idx = content_str.indexOf(LineApiConst.BUTTON.SAVE + LineApiConst.VAL.SHARP);
	        		if (idx >=0) {
	        			saveKeyContent(replyToken, userId,
	        					content_str.substring(new String(LineApiConst.BUTTON.SAVE + LineApiConst.VAL.SHARP).length() + 1), text);
	        		}
	        	break;
			}
		}
	}
	private List<KeyWord> getLastWord(String userId) {
		return itemService.find(userId, LineApiConst.VAL.SHARP);
	}

	// 1番最初のキーワードを入力したとき
	private void setKeyWord(String replyToken, String userId, String text) {
		itemService.save(userId, LineApiConst.VAL.SHARP, text);
		ConfirmTemplate confirmTemplate = new ConfirmTemplate(
				LineApiConst.setKakko(text) + LineApiConst.MESSAGE.CONFIRM_MSG,
				new MessageAction(LineApiConst.BUTTON.REF, LineApiConst.BUTTON.REF),
				new MessageAction(LineApiConst.BUTTON.SAVE, LineApiConst.BUTTON.SAVE)
				);
		TemplateMessage templateMessage = new TemplateMessage(LineApiConst.MESSAGE.CONFIRM_TITLE, confirmTemplate);
		this.reply(replyToken, templateMessage);
	}

	// 1番最初のキーワードを入力した後、保存を選択したとき
	private void saveKeyWord(String replyToken, String userId, String keyword) {
		itemService.save(userId, LineApiConst.VAL.SHARP, LineApiConst.BUTTON.SAVE + LineApiConst.VAL.SHARP + keyword);
		this.replyText(replyToken, LineApiConst.setKakko(keyword) + LineApiConst.MESSAGE.SAVE_MSG);
	}

	private void saveKeyContent(String replyToken, String userId, String keyword, String content) {
		itemService.save(userId, keyword, content);
		this.replyText(replyToken, LineApiConst.setKakko(keyword) + LineApiConst.MESSAGE.SAVE_COMPLETE_MSG);
	}

	// 1番最初のキーワードを入力した後、参照を選択したとき
	private void refKeyWord(String replyToken, String userId, String keyword) {
		itemService.deleteSharp(userId);
		List<KeyWord> key = itemService.find(userId, keyword);
		if (key.size() > 0)
		this.replyText(replyToken, key.get(0).getContent());
	}

	// キャンセルをしたとき
	private void cancelKeyWord(String replyToken, String userId) {
		itemService.deleteSharp(userId);
		this.replyText(replyToken, LineApiConst.MESSAGE.CANCEL);

	}

	private void replyText(@NonNull String replyToken, @NonNull String message) {
		if (replyToken.isEmpty()) {
			throw new IllegalArgumentException("replyToken must not be empty");
		}
		if (message.length() > 1000) {
			message = message.substring(0, 1000 - 2) + "……";
		}
		this.reply(replyToken, new TextMessage(message));
	}

    private void reply(@NonNull String replyToken, @NonNull Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

    private void reply(@NonNull String replyToken, @NonNull List<Message> messages) {
        try {
            BotApiResponse apiResponse = lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, messages))
                    .get();
            log.info("Sent messages: {}", apiResponse);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}