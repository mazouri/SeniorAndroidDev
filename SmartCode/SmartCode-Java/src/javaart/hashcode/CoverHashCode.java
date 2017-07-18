package javaart.hashcode;

import java.util.Arrays;

/**
 * 通用的hashcode重写方案
 *
 * 如果重写equals方法，必须重写hashCode方法，
 * 并且如果两个对象用equals方法比较返回true，那么这两个对象hashCode返回的值也必须是相等的，
 * 并且对于同一个对象，equals方法需要比较的属性值没有被修改，那么每次调用hashCode返回的值应该是一致的
 *
 * hashCode主要是用于散列集合，通过对象hashCode返回值来与散列中的对象进行匹配，通过hashCode来查找散列中对象的效率为O(1)，
 * 如果多个对象具有相同的hashCode，那么散列数据结构在同一个hashCode位置处的元素为一个链表，需要通过遍历链表中的对象，
 * 并调用equals来查找元素。这也是为什么要求如果对象通过equals比较返回true,那么其hashCode也必定一致的原因
 *
 * Created by wangdongdong on 17/7/16.
 */
public class CoverHashCode {

    private String signalName;
    private int signalIValue;
    private float signalFValue;
    private double position;
    private boolean valid;
    private byte size;
    private long sendTime;
    private Account account;
    private int[] servers;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CoverHashCode) {
            CoverHashCode other = (CoverHashCode) obj;
            if (signalName.equals(other.signalName) && signalIValue == other.signalIValue
                    && signalFValue == other.signalFValue && position == other.position
                    && valid == other.valid && size == other.size && sendTime == other.sendTime
                    && account.uid == other.account.uid && servers == other.servers) {
                return true;
            }
        }
        return super.equals(obj);
    }

    /**
     * 针对每个属性，处理为int，相加
     * @return
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (signalName == null ? 0 :  signalName.hashCode()); //没有判断signalName.length()<=0, 是因为长度为0也就是""时，hashcode是默认的0
        result = prime * result + signalIValue; //int形，相加
        result = prime * result + Float.floatToIntBits(signalFValue); //float处理
        long posLong = Double.doubleToLongBits(position); // Double先处理成long，再处理为int
        result = prime * result + (int)(posLong^(posLong>>>32));
        result = prime * result + (valid ? 1 : 0); //布尔值
        result = prime * result + (int) size; //byte\char\short\int,则计算(int)f
        result = prime * result + (int) (sendTime^(sendTime>>>32));
        result = prime * result + (account == null ? 0 : account.hashCode());  //对象
        result = prime * result + Arrays.hashCode(servers); //数组
        return result;
    }

    public CoverHashCode(String signalName, int signalIValue, float signalFValue, double position
            , boolean valid, byte size, long sendTime, Account account, int[] servers) {
        this.signalName = signalName;
        this.signalIValue = signalIValue;
        this.signalFValue = signalFValue;
        this.position = position;
        this.valid = valid;
        this.size = size;
        this.sendTime = sendTime;
        this.account = account;
        this.servers = servers;
    }

    public CoverHashCode() {
    }

    public String getSignalName() {
        return signalName;
    }

    public void setSignalName(String signalName) {
        this.signalName = signalName;
    }

    public int getSignalIValue() {
        return signalIValue;
    }

    public void setSignalIValue(int signalIValue) {
        this.signalIValue = signalIValue;
    }

    public float getSignalFValue() {
        return signalFValue;
    }

    public void setSignalFValue(float signalFValue) {
        this.signalFValue = signalFValue;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public byte getSize() {
        return size;
    }

    public void setSize(byte size) {
        this.size = size;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int[] getServers() {
        return servers;
    }

    public void setServers(int[] servers) {
        this.servers = servers;
    }


    private static class Account {
        private String name;
        private int uid;

        public Account(String name, int uid) {
            this.name = name;
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (name == null ? 0 : name.hashCode());
            result = prime * result + uid;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Account) {
                Account other = (Account) obj;
                if (name.equals(other.getName()) && uid == other.getUid()) {
                    return true;
                }
            }
            return super.equals(obj);
        }
    }
}
