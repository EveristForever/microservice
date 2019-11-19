package com.anyview.netty;

import io.netty.channel.Channel;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author jingshanccc
 * @description: Channel与user的关系map
 * @create: 2019-03-31 21:13
 */
public class ChannelIdRel {

    private static HashMap<String, Channel> manager = new HashMap<>();



    public static void put(String senderName, Channel channel) {
        manager.put(senderName, channel);
    }

    public static Channel get(String senderName) {
        return manager.get(senderName);
    }

    //用于Http版的Hashmap和方法
    private static HashMap<String,String> managerNew= new HashMap<>();

    public static void  set(String token,String Username){managerNew.put(token,Username);}

    public static void removeByToken(String token){
        managerNew.remove(token);
        System.out.println("token："+token);
    }


    //用于netty版的方法
    //移除已登录的用户
    public static void removeByKey(String senderName) {
        manager.remove(senderName);
        System.out.println("用户: " + senderName + "已退出");
    }

    //移除已登录的用户
    public static void removeByValue(Channel channel) {
        Collection<Channel> values = ChannelIdRel.getAllValue();
        for (Channel c : values) {
            if (c.id().equals(channel.id())) {
                values.remove(channel);
                System.out.println("用户已退出");
                break;
            }
        }

    }


    public static Collection<Channel> getAllValue() {
        return manager.values();
    }

    public static void output() {
        for (HashMap.Entry<String, Channel> entry : manager.entrySet()) {
            System.out.println("用户: " + entry.getKey()
                    + ", ChannelId: " + entry.getValue().id().asLongText());
        }
    }
}
