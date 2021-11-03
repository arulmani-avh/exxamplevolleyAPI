package com.example.examplevolleyapi;

import androidx.annotation.NonNull;
public class branches {
    private final String branch_id;
    private final String branch_name;
    public branches(String  branch_id, String branch_name) {
        this.branch_id = branch_id;
        this.branch_name = branch_name;
    }
    public String getBranch_id() {
        return branch_id;
    }
    public String getBranch_name() {
        return branch_name;
    }
    @NonNull
    @Override
    public String toString()
    {
        return this.branch_name;
    }
}
