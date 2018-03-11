package com.ynshun.config.websocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.java_websocket.WebSocket;

import com.ynshun.config.cache.MemoryCache;
import com.ynshun.domain.MessageInfo;
import com.ynshun.domain.UserInfo;

import net.sf.json.JSONObject;

public class WsPool {
	private static final Map<WebSocket, String> wsUserMap = new HashMap<WebSocket, String>();

	public static WebSocket getWsByUser(String username) {
		if (StringUtils.isBlank(username) || !wsUserMap.containsValue(username)) {
			return null;
		}
		
		for (WebSocket scoket : wsUserMap.keySet()) {
			if (wsUserMap.get(scoket).equals(username)) {
				return scoket;
			}
		}
		return null;
	}
	
	
	public static String getUsernameBySocket(WebSocket socket) {
		return wsUserMap.get(socket);
	}

	
	public static void addUser(String username, WebSocket socket) {
		wsUserMap.put(socket, username);
	}

	
	public static boolean removeUser(WebSocket socket) {
		if (wsUserMap.containsKey(socket)) {
			wsUserMap.remove(socket);
		}
		return true;
	}

	
	public static void sendMessageToUser(String username, MessageInfo message) {
		WebSocket socket = getWsByUser(username);
		UserInfo toUser = MemoryCache.getUserInfo(username);
		
		if (null != socket && message != null && toUser != null) {
			
			message.setTo_user(toUser);
			socket.send(JSONObject.fromObject(message).toString());
		}
	}

	
	public static void sendMessageToAll(String sender, MessageInfo message) {
		Set<WebSocket> sockets = wsUserMap.keySet();
		
		for (WebSocket socket : sockets) {
			String username = getUsernameBySocket(socket);
			if (null != username && message != null) {
				message.setTo_user(MemoryCache.getUserInfo(username));
				socket.send(JSONObject.fromObject(message).toString());
			}
		}
	}
}