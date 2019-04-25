package cn.yeshaoting.sd;

import com.google.common.collect.Lists;

import java.util.List;

public class MakeOutOfMemory {

    public static void main(String[] args) throws InterruptedException {
        List<Byte[]> all = Lists.newArrayList();
        int cnt = 0;
        for (; ; ) {
            Byte[] bytes = new Byte[8 * 1024 * 1024];
            all.add(bytes);
            Thread.sleep(10000);
            cnt++;
            System.out.println("cnt=" + cnt);
        }
    }
}
