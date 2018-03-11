package com.ynshun.config.websocket;

import java.net.InetSocketAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.ynshun.config.cache.MemoryCache;
import com.ynshun.domain.MessageInfo;
import com.ynshun.domain.UserInfo;

import net.sf.json.JSONObject;

public class WsServer extends WebSocketServer {
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	
	public WsServer(int port) {
		super(new InetSocketAddress(port));
	}

	public WsServer(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void onOpen(WebSocket socket, ClientHandshake handshake) {
		// WsPool.addUser(username, conn);
		// WsPool.sendMessageToAll(username, String.format("用户%s上线了", username));
		
		WsPool.addUser(null, socket);
	}

	
	@Override
	public void onClose(WebSocket socket, int code, String reason, boolean remote) {
		WsPool.removeUser(socket);
		// WsPool.sendMessageToAll("用户下线了");
	}

	@Override
	public void onMessage(WebSocket socket, String message) {
		if (message.startsWith("userlogin:")) {
			String username = message.substring(10);
			WsPool.addUser(username, socket);
		} else {
			JSONObject json = JSONObject.fromObject(message);
			
			String fromUsername = WsPool.getUsernameBySocket(socket);
			
			UserInfo fromUser = MemoryCache.getUserInfo(fromUsername);
			
			String toObject = json.getString("toUser");
			
			String messageContent = json.getString("messageContent");
			
			int targetType = json.getInt("targetType");
			
			MessageInfo messageInfo = new MessageInfo();
			messageInfo.setFrom_user(fromUser);
			messageInfo.setSend_time(dateFormat.format(new Date()));
			messageInfo.setMessage_content(messageContent);
			
			if (targetType == 0) { // 发给所有用户
				WsPool.sendMessageToAll(fromUsername, messageInfo);
			} else if (targetType == 1) { // 发送给群
				
			} else if (targetType == 2) { // 发送给指定用户
				WsPool.sendMessageToUser(toObject, messageInfo);
			}
		}
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
	}

}