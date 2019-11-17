package com.htsc.idp.dto;

public class LRUNode extends Node {
    private Integer freq = 0;//设置节点的使用频率

    public LRUNode(Object key, Object value) {
        super(key, value);
    }

    public Integer getFreq() {
        return freq;
    }

    public void setFreq(Integer freq) {
        this.freq = freq;
    }


}
