package com.anyview.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @author jingshanccc
 * @description:
 * @create: 2019-03-24 18:44
 */
@Component
public class WebSocketServer {

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture channelFuture;

    //单例模式(静态内部类实现)
    private static class SingletonWebSocketServer{
        static final WebSocketServer instance = new WebSocketServer();
    }
    public static WebSocketServer getInstance(){
        return SingletonWebSocketServer.instance;
    }


    private WebSocketServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup,subGroup).channel(NioServerSocketChannel.class).childHandler(new WSServerInitializer());
    }
    public void start(){
        this.channelFuture = server.bind(8889);
        System.out.println("server starts...");
    }

}
