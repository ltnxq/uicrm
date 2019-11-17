package com.htsc.idp.dto;

/**
 * 链表节点的类
 */
public class Node<T> {

    private  T key;//key值

    private T value;//value值

    public Node pre = null;//上一个节点

    public Node next = null;//下一个节点

    public Node(T key, T value) {
        this.key = key;
        this.value = value;
    }
    /**
     * 返回key value字符串
     * @return
     */
    public String getStr(){
        return (key.toString() + value.toString() );
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
