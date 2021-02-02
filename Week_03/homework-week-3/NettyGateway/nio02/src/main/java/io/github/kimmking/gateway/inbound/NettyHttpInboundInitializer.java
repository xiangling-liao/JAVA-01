package io.github.kimmking.gateway.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

public class NettyHttpInboundInitializer extends ChannelInitializer<SocketChannel> {
	
	private List<String> proxyServer;
	
	public NettyHttpInboundInitializer(List<String> proxyServer) {
		this.proxyServer = proxyServer;
	}
	
	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline p = ch.pipeline();
		//HttpServerCodec - A combination of HttpRequestDecoder and
		// HttpResponseEncoder which enables easier server side HTTP implementation.
		p.addLast(new HttpServerCodec());
		//p.addLast(new HttpServerExpectContinueHandler());
		// HttpObjectAggregator的作用 将多个消息转换为单一的FullHttpRequest或者FullHttpResponse
		p.addLast(new HttpObjectAggregator(1024 * 1024));
		p.addLast(new NettyHttpInboundHandler(this.proxyServer));
	}
}
