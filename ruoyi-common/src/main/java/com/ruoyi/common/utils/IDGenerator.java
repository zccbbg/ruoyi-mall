package com.ruoyi.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yangyincong on 15/8/16.
 * ID生成器 workId (1~4)
 */
public class IDGenerator {
    private final static Logger logger = LoggerFactory.getLogger(IDGenerator.class);
    private final static long twepoch = 1361753741828L;
    private final static long workerIdBits = 4L;
    private final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    private final static long sequenceBits = 10L;
    private long workerId;
    private long sequence = 0L;

    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    private final static long sequenceMask = -1L ^ -1L << sequenceBits;

    private long lastTimestamp = -1L;

    private IDGenerator(final long workerId) {
        super();
        this.workerId = workerId;
    }

    public static long generateMinId(int wid, long time) {
        return (time - twepoch << timestampLeftShift) | (wid << workerIdShift);
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(
                    String.format(
                        "Clock moved backwards.  Refusing to generate id for %d milliseconds",
                        this.lastTimestamp - timestamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift))
            | (this.workerId << workerIdShift) | (this.sequence);
        return nextId;
    }

    public static long generateMaxId(long wid, long time) {
        return (time - twepoch << timestampLeftShift) | (wid << workerIdShift) | sequenceMask;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    private static IDGenerator generator;

    public static synchronized void init(Long workerId) throws Exception {
        workerId = workerId % maxWorkerId;
        logger.info("程序中init的workid为：{}", workerId);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format(
                "worker Id can't be greater than %d or less than 0",
                IDGenerator.maxWorkerId));
        }
        generator = new IDGenerator(workerId);
    }

    public static Long generateId() {
        if (null == generator) {
            synchronized (IDGenerator.class) {
                if (null == generator) {
                    try {
                        init(2L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return generator.nextId();
    }
}
