package cn.yeshaoting.sd;

public class MakeStackOverflow {

    private static int cnt = 0;

    public static void main(String[] args) {
        display();
    }

    private static void display() {
        System.out.println(++cnt);
        display();
    }

}
