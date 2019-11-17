package com.htsc.idp.dto;


public class DoubleLinkedList {
   private Integer capacity = 65535;

   private Node head = null;//头部节点

   private Node tail = null;//尾部节点

    private Integer size = 0;//双向链表的当前容量

    public DoubleLinkedList() {
    }

    public DoubleLinkedList(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * 从双向链表的头部添加节点
     * @param node
     * @return
     */
    public Node addHead(Node node){
      // 首先判断链表的头部是否位空
        if(this.head == null){
            this.head = node;//设置头部节点
            this.tail = node;//设置尾部节点
            this.head.pre = null;//设置前一个节点为空
            this.tail.next = null;//设置后一个节点为空
        }else{
           node.next = this.head;//新添加的下一个节点为head节点，node指向head
            this.head.pre = node;//head指向node
            this.head = node;//设置头部节点的新的指向
            this.head.pre = null;
        }
        this.size += 1;
        return node;//返回节点值
    }

    /**
     * 向尾部添加节点
     * @param node
     * @return
     */
    public Node addTail(Node node){
        if(this.tail == null){
            this.tail = node;
            this.head = node;
            this.tail.pre = null;
            this.tail.next = null;
        }else{
            this.tail.next = node;//尾部的节点下一个节点指向node节点
            node.pre = this.tail;
            this.tail = node;//将尾部节点从新指向
            this.tail.next = null;//将尾部节点的下一个节点设置为空
        }
        this.size += 1;
        return  node;
    }

    /**
     * 从头部删除节点
     * @return
     */
    public Node deleHead(){
        //首先判断头部节点是否为空
        if(this.head == null){
            System.out.println("删除头部节点，头部节点为空，直接退出");
            return null ;
        }
        Node node = this.head;//将头部的节点进行赋值
        //判断头部节点是否有下一个节点
        if(node.next != null){//判断头部节点是否有下一个节点
            //如果有下一个节点，将头部的节点指向下一个节点
            this.head = node.next;
            this.head.pre = null;//将头部节点的前一个节点置为空，即删除前一个节点
        }else{
            //如果没有下一个节点，那么只有头部节点
            //那么将头部节点和尾部节点通知置为空
            this.head = this.tail = null;
        }
        size -= 1;
        return  node;//返回删除的节点
    }

    /**
     * 删除尾部节点
     * @return
     */
    public Node deleTail(){
        if(this.tail == null){
            System.out.println("删除尾部节点操作，尾部节点为空");
            return null;
        }
        //不为空
        Node node = this.tail;//将要删除的节点
        //判断改尾部节点是否有前一个节点
        if(node.pre != null){
            //将尾部节点指向前一个节点
            this.tail = node.pre;
            this.tail.next = null;//将下一个节点置为空
        }else{
            this.tail = this.head = null;
        }
        size -= 1;
        return  node;
    }

    /**
     * 删除任意节点
     * @return
     */
    public Node remove(Node node){
      //如果node为为null的话，删除尾部节点
        if(node == null){
            node = this.tail;
        }
        if(node == this.tail){//如果删除的节点等于尾部节点，删除尾部节点
            deleTail();
        }else if(node == this.head){//如果删除的节点是等于头部节点，删除头部节点
            deleHead();//删除头部节点
        }else{//正常删除节点的操作
            node.pre.next = node.next;
            node.next.pre = node.pre;
            size -= 1;
        }

      return  node;//返回删除的对象
    }

    /**
     * 打印当前链表结构
     */
    public String print(){
        Node p = this.head;//将头部节点赋值为p变量
        String line = "";
        while ( p != null){
            line +=  p.getStr();
            p = p.next;
            if(p != null){
                line += ">>>>";
            }
        }
        return line;
    }

    public static void main(String[] args) {
        //初始一个双向链表
     DoubleLinkedList d = new DoubleLinkedList(10);
     Node[] nodes = new Node[10];//定义一个长度为10的节点数组
        //循环初始化node
        for(int i=0;i<10;i++){
            Node node = new Node((i+""),(i+""));
            nodes[i] = node;
        }
        d.addHead(nodes[0]);
        System.out.println(d.print());
        d.addHead(nodes[1]);
        System.out.println(d.print());
        d.addTail(nodes[2]);
        System.out.println(d.print());
        d.addTail(nodes[3]);
        System.out.println(d.print());
        d.remove(nodes[0]);
        System.out.println(d.print());
        d.remove(nodes[1]);
        System.out.println(d.print());
    }
}
