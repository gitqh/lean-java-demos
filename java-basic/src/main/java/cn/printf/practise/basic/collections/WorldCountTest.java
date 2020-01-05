package cn.printf.practise.basic.collections;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 使用 HashMap 实现单词统计
 */
public class WorldCountTest {
    private String text = "you are not alone and you are a good man";

    @Test
    public void shouldOutWorldCunt() {
        HashMap<String, Letter> stringLetterHashMap = new HashMap<String, Letter>();

        String[] words = text.split(" ");
        for (String world : words) {
            if (!stringLetterHashMap.containsKey(world)) {
                stringLetterHashMap.put(world,new Letter());
            }

            stringLetterHashMap.get(world).updateCount();
        }

        Iterator<Map.Entry<String, Letter>> iterator1 = stringLetterHashMap.entrySet().iterator();

        while (iterator1.hasNext()){
            Map.Entry<String, Letter> entry = iterator1.next();
            String line = entry.getKey() + ":" + entry.getValue().getCount();
            System.out.println(line);
        }

    }

    class Letter {
        private int count = 0;

        public void updateCount() {
            count++;
        }

        public int getCount() {
            return this.count;
        }
    }
}
