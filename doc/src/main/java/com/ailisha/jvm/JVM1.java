package com.ailisha.jvm;

/**
 * 对象优先在Eden分配
 * JVM 参数：
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * @author zengjx
 * @version $Id: JVM1.java 2018/3/27 zengjx $
 */
public class JVM1 {
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    public static void main(String[] args) throws InterruptedException {
        JVM1.testAllocation();
    }

    /**
     * output:
     [GC (Allocation Failure) [DefNew: 7992K->691K(9216K), 0.0048783 secs] 7992K->6835K(19456K), 0.0049205 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
     Heap
     def new generation   total 9216K, used 4869K [0x04a00000, 0x05400000, 0x05400000)
     eden space 8192K,  51% used [0x04a00000, 0x04e14938, 0x05200000)
     from space 1024K,  67% used [0x05300000, 0x053accd8, 0x05400000)
     to   space 1024K,   0% used [0x05200000, 0x05200000, 0x05300000)
     tenured generation   total 10240K, used 6144K [0x05400000, 0x05e00000, 0x05e00000)
     the space 10240K,  60% used [0x05400000, 0x05a00030, 0x05a00200, 0x05e00000)
     Metaspace       used 233K, capacity 2280K, committed 2368K, reserved 4480K
     */
}
