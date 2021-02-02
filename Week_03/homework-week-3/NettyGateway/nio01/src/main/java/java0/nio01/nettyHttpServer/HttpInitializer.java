package java0.nio01.nettyHttpServer;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpInitializer extends ChannelInitializer<SocketChannel> {
	
	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline p = ch.pipeline();
		p.addLast(new HttpServerCodec());
		//p.addLast(new HttpServerExpectContinueHandler());
		//HttpObjectAggregator的作用 将多个消息转换为单一的FullHttpRequest或者FullHttpResponse
		p.addLast(new HttpObjectAggregator(1024 * 1024));
		p.addLast(new HttpHandler());
	}
}
