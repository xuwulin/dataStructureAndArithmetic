package com.xwl.datastructure.queue;

import java.util.Scanner;

/**
 * @Author: xwl
 * @Date: 2019/6/4 17:28
 * @Description: 数组模拟环形队列
 * 对前面的数组模拟队列的优化，充分利用数组. 因此将数组看做是一个环形的。(通过 取模的方式来实现即可)
 *  分析说明：
 * 1) 尾索引的下一个为头索引时表示队列满，即将队列容量空出一个作为约定,这个在做判断队列满的
 * 时候需要注意 (rear + 1) % maxSize == front 满]
 * 2) rear == front [空]
 * <p>
 * 思路如下
 * 1、front变量的含义做一个调整：front就指向队列的第一个元素也就是说 arr[front]就是队列的第一个元素，front的初始值=0
 * 2、rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间做为约定，rear的初始值=0
 * 3、当队列满时，条件是(rear + 1) % maxsize == front【满】
 * 4、对队列为空的条件，rear == front【空】
 * 5、当我们这样分析，队列中有效的数据的个数(rear + maxsize - font) % maxsiz // 假如：rear = 1 front = 0 maxsize = 3；则队列中的有效数据为1个
 * 6、我们就可以在原来的队列上修改得到，一个环形队列
 */
public class CircleArrayQueue {

    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例~~~");

        // 创建一个环形队列
        // 说明：设置4, 其队列的有效数据最大是3
        CircleArray queue = new CircleArray(4);
        // 接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            // 接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }

}

/**
 * 环形数组
 */
class CircleArray {
    /**
     * 表示数组的最大容量
     */
    private int maxSize;

    /**
     * 队列头，默认值0
     * front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
     * front 的初始值 = 0
     */
    private int front;

    /**
     * 队列尾，默认值0
     * rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
     * rear 的初始值 = 0
     */
    private int rear;

    /**
     * 该数据用于存放数据, 模拟队列
     */
    private int[] arr;

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    /**
     * 判断队列是否满
     * 如maxSize == 4，数组下标 0, 1, 2, 3
     * 初始:
     * front == 0
     * rear == 0
     * 当向0位置中放数据时，此时rear后移一位，指向1位置，front保持不变...
     * 当rear指向3时，arr[0], arr[1], arr[2]均不位空，arr[3]为空
     * 此时 (3 + 1) % 4 == 0，队列满了
     *
     * 当front指向1位置，rear指向0位置时，
     * 此时 (0 + 1) % 4 == 1，队列满了
     * 想象成一个环形
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     *
     * @param n
     */
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        // 直接将数据加入
        arr[rear] = n;
        // 将 rear 后移, 这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列的数据, 出队列
     *
     * @return
     */
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        // 这里需要分析出 front是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        // 思路：从front开始遍历，遍历多少个元素
        // 动脑筋
        for (int i = front; i < front + this.size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 求出当前队列有效数据的个数
     *
     * @return
     */
    public int size() {
        // rear = 2
        // front = 1
        // maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列的头数据， 注意不是取出数据
     *
     * @return
     */
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }
}