package com.wisely.actualcombat7_6.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.wisely.actualcombat7_6.domain.WiselyMessage;
import com.wisely.actualcombat7_6.domain.WiselyResponse;

@Controller
public class WsController {
	/*
	 * @MessageMapping("/welcome")
	 * 当浏览器向服务器发送请求时，通过@MessageMapping映射/welcome这个地址。类似于@RequestMapping
	 * 
	 */
	@MessageMapping("/welcome")
	/*
	 * @SendTo("/topic/getResponse")
	 * 当服务器端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
	 * 
	 */
	@SendTo("/topic/getResponse")
	public WiselyResponse say(WiselyMessage message) throws Exception {
		Thread.sleep(3000);
		return new WiselyResponse("Welcome, " + message.getName() + "!");
	}

	// 通过simpMessagingTemplate 向浏览器发送消息
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) {
		if (principal.getName().equals("wwt")) {
			simpMessagingTemplate.convertAndSendToUser("wisely", "/queue/notifications",
					principal.getName() + "-send:" + msg);
		} else {
			simpMessagingTemplate.convertAndSendToUser("wwt", "/queue/notifications",
					principal.getName() + "-send:" + msg);

		}
	}
}
