package com.anyview.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author jingshanccc
 * @description: handler初始化器
 * @create: 2019-03-24 14:59
 */
public class WSServerInitializer extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //http处理的协议
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(1024*64));

        //webSocket服务器处理的协议 指定客户端访问的路由：/ws
        pipeline.addLast(new WebSocketServerProtocolHandler("/anyview"));
        System.out.println("哈哈哈ha");
        //自定义助手类
        pipeline.addLast("myHandler",new WSServerHandler());
    }
}
