package com.demo.jdbc.util;

/**
 * SnowFlake 算法，是 Twitter 开源的分布式 id 生成算法。
 * 其核心思想就是：使用一个 64 bit 的 long 型的数字作为全局唯一 id。在分布式系统中的应用十分广泛，且ID 引入了时间戳，基本上保持自增的。
 * 1 + 41 + 5 + 5 + 12 = 64
 * 第一个部分，是 1个bit：0，这个是无意义的,生成的 id 都是正数
 * 第二个部分是 41个bit：表示的是时间戳,单位是毫秒
 * 第三个部分是 5个bit：表示的是机房id，10001。
 * 第四个部分是 5个bit：表示的是机器id，11001。
 * 第五个部分是 12个bit：表示的序号，就是某个机房某台机器上这一毫秒内同时生成的 sequence序号，0000 00000000。
 *
 * 这个类不是工具类，因为要实例化后才能使用（需要初始化参数）
 * */
public class SnowFlakeUtil {

    private long workerId;// workerId 代表机器id
    private long dataCenterId;// dataCenterId 代表机房id
    private long sequence;// sequence 代表一毫秒内生成的多个id的最新序号

    private long workerIdBits = 5L;// workerIdBits 代表机器id bit数
    private long dataCenterIdBits = 5L;// dataCenterIdBits 代表机房id bit数
    private long sequenceBits = 12L;// sequenceBits 代表sequence序号 bit数

    private long maxWorkerId = -1L ^ (-1L << workerIdBits);// 机器id最多只能是32以内
    private long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);// 机房id最多只能是32以内

    private long workerIdShift = sequenceBits;
    private long dataCenterIdShift = sequenceBits + workerIdBits;
    private long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private long sequenceMask = -1L ^ (-1L << sequenceBits);
    private long lastTimestamp = -1L;
    private long twepoch = 1288834974657L;

    public SnowFlakeUtil(long workerId, long dataCenterId, long sequence) {
        // 要求就是你传递进来的机房id和机器id不能超过32，不能小于0
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        this.sequence = sequence;
    }

    // 这个是核心方法，通过调用nextId()方法，让当前这台机器上的snowflake算法程序生成一个全局唯一的id
    public synchronized Long nextId() {
        // 这儿就是获取当前时间戳，单位是毫秒
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards. Rejecting requests until %d.", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        // 下面是说假设在同一个毫秒内，又发送了一个请求生成一个id
        // 这个时候就得把sequence序号给递增1，最大为 2^12=4096
        if (lastTimestamp == timestamp) {
            // 这个意思是说一个毫秒内最多只能有4096个数字，无论你传递多少进来，
            //这个位运算保证始终就是在4096这个范围内，避免你自己传递个sequence超过了4096这个范围
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        // 这儿记录一下最近一次生成id的时间戳，单位是毫秒
        lastTimestamp = timestamp;
        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timestamp - twepoch) << timestampLeftShift) |
                (dataCenterId << dataCenterIdShift) |
                (workerId << workerIdShift) | sequence;
    }

    // 等待时间戳往后移动
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    // 获取当前时间戳
    private long timeGen(){
        return System.currentTimeMillis();
    }


}
