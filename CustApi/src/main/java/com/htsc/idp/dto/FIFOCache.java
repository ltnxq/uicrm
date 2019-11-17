package com.htsc.idp.dto;

import java.util.HashMap;
import java.util.Map;

public class FIFOCache {
    public Integer capacity;//缓存的容量大小

    public Integer size = 0;//设置双向的初始大小

    public Map<Object,Node> map;//设置存放在缓存的map集合

    public DoubleLinkedList list ;//设置一个双向链表

    public FIFOCache(Integer capacity) {
        this.capacity = capacity;
        map = new HashMap<Object, Node>();//初始化一个map结合，防止空指针
        list = new DoubleLinkedList(capacity);//初始双向链表的大小
    }

    /**
     * FIFO缓存置换算法，操作的是双向链表
     * 如果该key在缓存中存在，就进行替换
     * 如果该key不存在，判断双向链表是否达到最大值
     * 达到最大值，就进行弹出双向链表的操作，将key添加到双向链表的末尾
     * 如果没有达到最大值，直接添加到末尾
     */

    /**
     * get方法获取缓存key对应的value值
     * @param key
     * @return
     */
    public Object get(Object key){
        //判断key是否在map集合中，不在直接返回-1
        if(!map.containsKey(key)){
            return  -1;
        }
        return map.get(key).getValue();//获取value值
    }

    /**
     * 向缓存中添加key value值
     * @param key
     * @param value
     */
    public void put(Object key,Object value){
        //判断该缓存的容量是否大于0、
        if(this.capacity <= 0){
            System.out.println("该缓存的容量小于等于0，不允许存数据");
            return;
        }
        //判断map集合中是否有该key的存在，如果存在进行替换到双向链表的末尾
        if(map.containsKey(key)){
            //取出该key对应的value值
            Node node = map.get(key);//获取要替换的节点
            Node nodeOfDele = list.remove(node);
            System.out.println(list.print());//打印双向链表
            node.setValue(value);//设置该节点对象的新值
            list.addTail(node);//添加到末尾的操作
        }else{
            //判断链表的大小是否已经等于缓存的大小
            if (this.size == this.capacity){
                //如果缓存大小已经满了，弹出头部节点
                Node node = list.deleHead();//弹出头部节点
                map.remove(node.getKey());
                this.size -= 1;//双向链表的大小减1
            }
            //创建node对象
            Node node = new Node(key, value);
            map.put(key,node);//map集合中添加节点
            list.addTail(node);//双向链表中添加节点值
            this.size += 1;//双向链表值加1
        }
    }

    /**
     * 缓存的打印方法
     * @return
     */
    public String print(){
        return  list.print();
    }

    public static void main(String[] args) {
        //创建缓存对象
        FIFOCache fifoCache = new FIFOCache(2);//构造一个容量大小为2的缓存
        fifoCache.put(1,1);
        System.out.println(fifoCache.print());
        fifoCache.put(2,2);
        System.out.println(fifoCache.print());
        Object o = fifoCache.get(1);
        System.out.println(o.toString());
        fifoCache.put(3,3);
        System.out.println(fifoCache.print());
        Object o1 = fifoCache.get(2);
        System.out.println(o1.toString());
        fifoCache.put(4,4);
        System.out.println(fifoCache.print());
        Object o2 = fifoCache.get(1);
        System.out.println(o2.toString());

    }

}
