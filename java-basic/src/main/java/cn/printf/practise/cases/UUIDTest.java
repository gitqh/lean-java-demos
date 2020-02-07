package cn.printf.practise.cases;

import java.util.UUID;

public class UUIDTest {
    public static void main(String[] args) {
        System.out.println(uuid());
    }

    private static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
