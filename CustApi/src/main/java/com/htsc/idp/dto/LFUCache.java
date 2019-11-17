package com.htsc.idp.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LFUCache {
    private Integer capacity;

    private Map<Object,LRUNode> map;

    private Map<Integer,DoubleLinkedList> freqMap;//频率对应的双向链表

    private Integer size = 0;//设置链表的大小

    public LFUCache(Integer capacity) {
        this.capacity = capacity;
        map = new HashMap<Object, LRUNode>();//初始化缓存map集合
        freqMap = new HashMap<Integer, DoubleLinkedList>();//初始化频率对应的map集合
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * 更新节点频率的操作
     *
     *
     * @param lruNode
     */
    public void updateFreq( LRUNode lruNode){
        //获取节点的节点
        Integer freq = lruNode.getFreq();
        //首先进行删除freqMap集合中的双向链表的操作
        //为什么要先删除双向链表已经存在的节点，之前的节点的频率不该存在在那个双向链表中
        //
        Node node = this.freqMap.get(freq).remove(lruNode);//删除已经在双向链表中存在的节点，因为存在更新操作
        //如果删除节点后的双向链表大小为0，可以删除该双向链表
        Integer size = this.freqMap.get(freq).getSize();
        if(size<=0){
            freqMap.remove(freq);//删除该双向链表
        }
        //更新操作
        freq += 1;//频率加1操作
        lruNode.setFreq(freq);//设置传递进来的节点的新的频率值
        //如果这个频率对应的双向链表中，那么新建一个双向链表
        if( !freqMap.containsKey(freq)){
            //如果不存在，map集合中新建一个双向链表
            freqMap.put(freq,new DoubleLinkedList());//map集合新添加一个元素
        }
        //如果map集合中，添加新的节点
        this.freqMap.get(freq).addTail(lruNode);//添加新的lruNode
    }

    /**
     * 获取
     * @param key
     */
    public Object get(Object key){
        //如果在缓存的集合中不存在，返回-1
      if(!map.containsKey(key)){
          return -1;
      }
      //如果在集合中存在
        LRUNode lruNode = map.get(key);//获取节点值
        //调用更新操作的方法
        this.updateFreq(lruNode);
        return lruNode.getValue();//返回节点更新的值
    }

    public void put(Object key,Object value){
       //如果缓存的大小为0 直接退出
        if(this.capacity == 0){
            return;
        }
        //如果命中缓存
        if(this.map.containsKey(key)){
           //获取缓存的节点对象
            LRUNode lruNode = map.get(key);
            lruNode.setValue(value);//更新value值
            //进行频率的更新操作
            updateFreq(lruNode);
        }else {
            //如果没有命中缓存，也就是缓存中不存在该节点的值
            //如果缓存已经饱和,删除频率使用最少的双向链表的一个节点
            if (this.capacity == this.size){
                //如果缓存饱和,删除不常用的频率的双向链表的节点
                //获取使用频率最小的双向链表
                Set<Integer> freqSet = freqMap.keySet();//获取set集合
                Integer mimOfFreq = minOfSet(freqSet);//获取最小使用频率
                //删除最小使用的频率的双向链表的头部节点
                Node node = freqMap.get(mimOfFreq).remove(null);//删除头部节点
                map.remove(node.getKey());
                if(freqMap.get(mimOfFreq).getSize() == 0){
                    freqMap.remove(mimOfFreq);
                }

                this.size -= 1;//元素大小减去1
            }
                //如果缓存没有饱和
                LRUNode lruNode = new LRUNode(key,value);
                //设置lruNode的频率
                lruNode.setFreq(1);
                //向缓存中添加节点
                map.put(key,lruNode);
                //判断freqMap是否包含此频率的双向链表
                if( !freqMap.containsKey(lruNode.getFreq())){//如果此集合中不存在
                   freqMap.put(lruNode.getFreq(),new DoubleLinkedList());
                }
                //如果集合包含了对应的频率的双向链表
                freqMap.get(lruNode.getFreq()).addTail(lruNode);
                this.size += 1;
        }


    }

    public void print(){
        //遍历map集合
        Set<Integer> keySet = this.freqMap.keySet();
        for(Integer freq :keySet){
            System.out.println("freq=" + freq);
            System.out.println(freqMap.get(freq).print());
            System.out.println("***************************************");
        }
    }
    private Integer minOfSet(Set<Integer> set){
        //遍历set集合
        Integer min = 0;
        boolean  flag = false;
        try {
            for(Integer s : set){
                if(flag == false){
                    min =s;
                    flag = true;
                }
                if(s<min){
                    min = s;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return min;
    }

    public static void main(String[] args) {
        //创建一个缓存对象
        LFUCache cache = new LFUCache(2);
        cache.put(1,1);
        cache.print();
        cache.put(2,2);
        cache.print();
        cache.get(1);
        cache.print();
        cache.put(3,3);
        cache.print();
    }
}
