package chat;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/socketdemo",configurator=GetHttpSessionConfigurator.class)



public class ChatAnnotation {
 
    private static final String GUEST_PREFIX = "Guest";
    //AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<ChatAnnotation> connections =new CopyOnWriteArraySet<ChatAnnotation>();
 
    private final String nickname;
    private Session session;
 public String name;
  //初始函数
    public ChatAnnotation() {
    	//connectionIds.getAndIncrement()以原子方式将当前值加 1
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }
 
 
    @OnOpen
    public void start(Session session,EndpointConfig config) {
        this.session = session;
        HttpSession httpSession= (HttpSession)config.getUserProperties().get(HttpSession.class.getName());
        name=(String) httpSession.getAttribute("name");
      //  name="未命名";
       // name=(String) ((HttpSession) session).getAttribute("name");
        connections.add(this);
        String message = String.format("%s %s",name, "进入聊天室");
        broadcast(message);//服务器给客户端发送信息
    }
 
 
    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("%s %s",
        		name, "断开连接");
        broadcast(message);
    }
 
    //服务器收到一条消息时，该监听器将被调
    @OnMessage
    public void incoming(String message) {
        // Never trust the client
       // String filteredMessage = String.format("%s: %s",
        //        nickname, HTMLFilter.filter(message.toString()));
    	
         //String filteredMessage = name+":"+message;
    	String filteredMessage=name+":"+message;
    	broadcast(filteredMessage);
    }
 
 
    private static void broadcast(String msg) {
        for (ChatAnnotation client : connections) {
            try {
            	//服务器给客户端发送信息
                client.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                connections.remove(client);
                try {
                    client.session.close();
                } catch (IOException e1) {
                    // Ignore
                }
                String message = String.format("%s %s",
                        client.name, "断开连接");
                broadcast(message);
            }
        }
    }}