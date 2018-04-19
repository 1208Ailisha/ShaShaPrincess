package com.ailisha.jvm;

/**
 * 大对象直接进入老年代
 * JVM 参数：
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
 *
 * @author zengjx
 * @version $Id: JVM2.java 2018/3/27 zengjx $
 */
public class JVM2 {
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }

    public static void main(String[] args) throws InterruptedException {
        JVM2.testAllocation();
    }

    /**
     * outpur:
     Heap
     def new generation   total 9216K, used 2012K [0x04800000, 0x05200000, 0x05200000)
     eden space 8192K,  24% used [0x04800000, 0x049f73a0, 0x05000000)
     from space 1024K,   0% used [0x05000000, 0x05000000, 0x05100000)
     to   space 1024K,   0% used [0x05100000, 0x05100000, 0x05200000)
     tenured generation   total 10240K, used 4096K [0x05200000, 0x05c00000, 0x05c00000)
     the space 10240K,  40% used [0x05200000, 0x05600010, 0x05600200, 0x05c00000)
     Metaspace       used 233K, capacity 2280K, committed 2368K, reserved 4480K
     */
}
