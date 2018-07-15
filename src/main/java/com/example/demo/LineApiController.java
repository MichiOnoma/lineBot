package com.example.demo;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.action.MessageAction;
import com.linecorp.bot.model.event.Event;
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

    private Logger log = LoggerFactory.getLogger(LineApiController.class);


	@EventMapping
	public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
		TextMessageContent message = event.getMessage();
		handleTextContent(event.getReplyToken(), event, message);
	}

	private void handleTextContent(String replyToken, Event event, TextMessageContent content)
			throws Exception {
		String text = content.getText();
		log.info("Got text message from {}: {}", replyToken, text);


        ConfirmTemplate confirmTemplate = new ConfirmTemplate(
                LineApiConst.MESSAGE.KAKKO1 + text + LineApiConst.MESSAGE.KAKKO2 + LineApiConst.MESSAGE.CONFIRM_MSG,
                new MessageAction(LineApiConst.BUTTON.REF, LineApiConst.BUTTON.REF),
                new MessageAction(LineApiConst.BUTTON.SAVE, LineApiConst.BUTTON.SAVE)
        );
        TemplateMessage templateMessage = new TemplateMessage("Confirm alt text", confirmTemplate);
        this.reply(replyToken, templateMessage);
		this.replyText(replyToken, "Bot can't use profile API without user ID");
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