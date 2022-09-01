package com.tuanzi.tuanziblog.search.repository;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {

    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void test1() {

    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2));
        System.out.println(listNode);
    }
}
