package com.htsc.idp.dto;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Integer capacity;//设置缓存的容量的大小

    private Map<Object,Node> map;//存放数据的map集合

    private DoubleLinkedList list;//双向链表

    /**
     * 构造函数初始化  map集合 和双向链表集合
     * @param capacity
     */
    public LRUCache(Integer capacity) {
        this.capacity = capacity;
        map = new HashMap<Object, Node>();
        list = new DoubleLinkedList(this.capacity);//设置双向链表的大小
    }

    /**
     * 将节点设置到头部节点
     * @param key
     * @return
     */
    public Object get(Object key){
        if(map.containsKey(key)){
            Node node = map.get(key);//获取节点对应的value值
            list.remove(node);//移除node节点
            list.addHead(node);
            return node;
        }else{
            return -1;
        }
    }

    /**
     * 向缓存中添加节点操作
     * @param key
     * @param value
     */
    public void put(Object key,Object value){
        //首先判断该数据是否已经存在缓存中
        if(map.containsKey(key)){
            //如果存在，删除该节点，将该节点添加到头部
            Node node1 = map.get(key);//获取被替换的节点
            list.remove(node1);
            node1.setValue(node1);//替换节点的值
            list.addTail(node1);//将新的节点添加头部
        }
        else{//添加的数据不在缓存中
            if(list.getSize()>= this.capacity){//如果双向链表的大小大于缓存的大小，删除尾部节点
               //移除双向链表的尾部节点
                Node nodeLast = list.remove(null);//传递参数是Null移除尾部节点
                map.remove(nodeLast.getKey());//缓存中移除该节点
            }
            //双向链表的大小小于缓存的大小，双向链表直接在头部添加节点
            Node node = new Node(key,value);//创建新的node对象
            list.addHead(node);//双向链表添加新的节点
            map.put(key,node);//缓存中添加新的节点值
        }
    }

    /**
     * 打印方法
     */
    public void print(){
        System.out.println(list.print());//直接进行输出
    }

    public static void main(String[] args) {
        //初始化一个LRU缓存
        LRUCache lruCache = new LRUCache(2);//初始化一个大小为2的缓存
        lruCache.put(1,1);
        lruCache.print();
        lruCache.put(2,2);
        lruCache.print();
        lruCache.put(3,3);
        lruCache.print();
        lruCache.get(1);
        lruCache.print();
        lruCache.get(2);
        lruCache.print();
        lruCache.get(3);
        lruCache.print();


    }
}
