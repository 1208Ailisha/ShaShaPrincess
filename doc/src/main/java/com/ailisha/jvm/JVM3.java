package com.ailisha.jvm;

/**
 * 长期存活对象进入老年代
 * JVM 参数：
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
 *
 * @author zengjx
 * @version $Id: JVM3.java 2018/3/27 zengjx $
 */
public class JVM3 {
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];

        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    public static void main(String[] args) throws InterruptedException {
        JVM3.testAllocation();
    }

    /**
     *-XX:MaxTenuringThreshold=1
     [GC (Allocation Failure) [DefNew
     Desired survivor size 524288 bytes, new threshold 1 (max 1)
     - age   1:     970384 bytes,     970384 total
     : 6200K->947K(9216K), 0.0050916 secs] 6200K->5043K(19456K), 0.0051325 secs] [Times: user=0.00 sys=0.01, real=0.01 secs]
     [GC (Allocation Failure) [DefNew
     Desired survivor size 524288 bytes, new threshold 1 (max 1)
     : 5043K->0K(9216K), 0.0011386 secs] 9139K->5008K(19456K), 0.0011599 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     Heap
     def new generation   total 9216K, used 4178K [0x04800000, 0x05200000, 0x05200000)
     eden space 8192K,  51% used [0x04800000, 0x04c14938, 0x05000000)
     from space 1024K,   0% used [0x05000000, 0x05000000, 0x05100000)
     to   space 1024K,   0% used [0x05100000, 0x05100000, 0x05200000)
     tenured generation   total 10240K, used 5008K [0x05200000, 0x05c00000, 0x05c00000)
     the space 10240K,  48% used [0x05200000, 0x056e41c0, 0x056e4200, 0x05c00000)
     Metaspace       used 233K, capacity 2280K, committed 2368K, reserved 4480K
     */

    /**
     *-XX:MaxTenuringThreshold=15
     [GC (Allocation Failure) [DefNew
     Desired survivor size 524288 bytes, new threshold 1 (max 15)
     - age   1:     970384 bytes,     970384 total
     : 6200K->947K(9216K), 0.0043156 secs] 6200K->5043K(19456K), 0.0043629 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     [GC (Allocation Failure) [DefNew
     Desired survivor size 524288 bytes, new threshold 15 (max 15)
     : 5043K->0K(9216K), 0.0018075 secs] 9139K->5043K(19456K), 0.0018536 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     Heap
     def new generation   total 9216K, used 4178K [0x04600000, 0x05000000, 0x05000000)
     eden space 8192K,  51% used [0x04600000, 0x04a14938, 0x04e00000)
     from space 1024K,   0% used [0x04e00000, 0x04e00000, 0x04f00000)
     to   space 1024K,   0% used [0x04f00000, 0x04f00000, 0x05000000)
     tenured generation   total 10240K, used 5043K [0x05000000, 0x05a00000, 0x05a00000)
     the space 10240K,  49% used [0x05000000, 0x054ecea0, 0x054ed000, 0x05a00000)
     Metaspace       used 233K, capacity 2280K, committed 2368K, reserved 4480K
     */
}
