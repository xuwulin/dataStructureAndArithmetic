package com.xwl.datastructure.linkedlist;

import java.util.Stack;

/**
 * @Author: xwl
 * @Date: 2019/5/31 11:37
 * @Description: 演示栈Stack的基本使用
 */
public class TestStack {
	public static void main(String[] args) {
		Stack<String> stack = new Stack();
		// 入栈
		stack.add("jack");
		stack.add("tom");
		stack.add("smith");

		// 出栈
		// smith, tom , jack
		while (stack.size() > 0) {
			// pop就是将栈顶的数据取出
			System.out.println(stack.pop());
		}
	}

}
