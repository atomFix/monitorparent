package com.code.monitor.mq.rabbit.config;

import com.code.monitor.exception.NotCreateConnectionException;
import com.code.monitor.mq.rabbit.constant.RabbitConstant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/1/28 21:16
 */
public class RabbitConfig {
    private static final int MAX_SIZE = 5;
    public static RabbitConfig rabbitConfig = new RabbitConfig();
    CopyOnWriteArrayList<ConnectionEntry> connectionPool;
    /**
     * 创建连接工厂
     */
    private ConnectionFactory connectionFactory;
    private Connection connection = null;
    private Channel channel = null;
    private AtomicInteger size = new AtomicInteger(0);
    private int number = 0;


    private RabbitConfig() {
        try {
            connectionFactory = new ConnectionFactory();
            //设置rabbitmq-server的地址
            connectionFactory.setHost("121.36.110.208");
            //使用的端口号
            connectionFactory.setPort(5672);
            //使用的虚拟主机
            connectionFactory.setVirtualHost("/");
            connection = connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取 Channel
     *
     * @return Channel
     */
    public synchronized ChannelEntry getChannel() {
        Channel channel = null;
        ChannelEntry channelEntry = null;
        //由连接工厂创建连接
        try {
            //通过连接创建信道
            ConnectionEntry connection = getConnection();
            channel = connection.connection.createChannel();
            // channel = this.connection.createChannel();
            channel.exchangeDeclare(RabbitConstant.EXCHANGE_NAME, RabbitConstant.EXCHANGE_TYPE, true, false, false, null);

            //通过信道声明一个queue，如果此队列已存在，则直接使用；如果不存在，会自动创建
            //参数：name、是否支持持久化、是否是排它的、是否支持自动删除、其他参数（map）
            channel.queueDeclare(RabbitConstant.QUEUE_NAME, true, false, false, null);

            //将queue绑定至某个exchange。一个exchange可以绑定多个queue
            channel.queueBind(RabbitConstant.QUEUE_NAME, RabbitConstant.EXCHANGE_NAME, RabbitConstant.EXCHANGE_ROUTING_KEY);
            channelEntry = new ChannelEntry(channel, connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channelEntry;
    }

    /**
     * 获取连接
     *
     * @return
     */
    @SneakyThrows
    private ConnectionEntry getConnection() {
        if (connectionPool == null) {
            connectionPool = new CopyOnWriteArrayList<>();
            Connection connection = connectionFactory.newConnection();
            ConnectionEntry connectionEntry = new ConnectionEntry(connection, false);
            connectionPool.add(connectionEntry);
            size.incrementAndGet();
            return connectionEntry;
        } else {
            ConnectionEntry connectionEntry = null;
            for (ConnectionEntry conn : connectionPool) {
                if (conn.getStatus()) {
                    connectionEntry = conn;
                    conn.changeStatus();
                }
            }
            if (Objects.isNull(connectionEntry)) {
                if (size.get() < MAX_SIZE) {
                    Connection connection = connectionFactory.newConnection();
                    connectionEntry = new ConnectionEntry(connection, false);
                    connectionPool.add(connectionEntry);
                } else {
                    TimeUnit.MICROSECONDS.sleep(100);
                    if (number++ < MAX_SIZE) {
                        return getConnection();
                    } else {
                        throw new NotCreateConnectionException();
                    }
                }
            }
            return connectionEntry;
        }
    }

    /**
     * 关闭所有连接
     */
    public void shutdown() {
        connectionPool.forEach(ConnectionEntry::release);
    }

     public class ChannelEntry{
        Channel channel;
        ConnectionEntry connectionEntry;

        public ChannelEntry(Channel channel, ConnectionEntry connectionEntry) {
            this.channel = channel;
            this.connectionEntry = connectionEntry;
        }

         public Channel getChannel() {
             return channel;
         }

         public ConnectionEntry getConnectionEntry() {
             return connectionEntry;
         }
     }

    public class ConnectionEntry {
        /**
         * 若为 false 则表示被占用
         */
        public volatile boolean status = true;

        public Connection connection;

        public ConnectionEntry(Connection connection) {
            this.connection = connection;
        }

        public ConnectionEntry(Connection connection, boolean status) {
            this.status = status;
            this.connection = connection;
        }


        /**
         * 关闭连接
         */
        @SneakyThrows
        public void release() {
            this.connection.close();
        }

        public boolean getStatus() {
            return status;
        }

        /**
         * 反转状态
         *
         * @return status
         */
        public synchronized boolean changeStatus() {
            return status != status;
        }

    }


}
