package io.github.kimmking.gateway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Data;

import java.util.List;

@Data
public class NettyHttpInboundServer {

    private int port;
    
    private List<String> proxyServers;

    public NettyHttpInboundServer(int port, List<String> proxyServers) {
        this.port=port;
        this.proxyServers = proxyServers;
    }

    public void run() throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128)
                    //即时发送包，关闭Nagle算法
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    //启动心跳，检测客户端连接是否正常
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //允许重复使用本地址及端口号链接
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    //设置接收缓冲区大小
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    //设置发送缓冲区大小
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    //允许多个线程绑定该接口
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //重用缓冲区
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new NettyHttpInboundInitializer(this.proxyServers));

            Channel ch = b.bind(port).sync().channel();
            System.out.println("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
