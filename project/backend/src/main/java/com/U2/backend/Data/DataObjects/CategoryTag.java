package com.U2.backend.Data.DataObjects;

import com.U2.backend.Data.DataObjectContracts.ICategoryTag;

public class CategoryTag implements ICategoryTag, Comparable<ICategoryTag>{
    private String name;
    private int count;

    public CategoryTag(String name){
        this.name = name.toLowerCase();
        this.count = 1;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount(){
        count++;
    }

    @Override
    public int compareTo(ICategoryTag t) {
        return Integer.compare(this.count, t.getCount());
    }
}
