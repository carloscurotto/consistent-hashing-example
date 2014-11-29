package ar.com.carloscurotto.hashing.consistent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ConsistentHashExample {

    public static void main(String[] args) {

        int numberOfNodes = 3;
        int numberOfObjects = 10000;
        int numberOfReplicas = 100;

        MD5HashFunction hashFunction = new MD5HashFunction();

        List<Member> nodes = new ArrayList<Member>();
        for (int i = 1; i <= numberOfNodes; i++) {
            nodes.add(new Member("member" + String.valueOf(i)));
        }

        ConsistentHash<Member> consistentHashFunction = new ConsistentHash<Member>(hashFunction,
                        numberOfReplicas, nodes);

        Map<Member, Integer> results = new HashMap<Member, Integer>();

        for (int i = 1; i <= numberOfObjects; i++) {
            Member member = consistentHashFunction.get(i);
            Integer memberCount = results.get(member);
            if (memberCount == null) {
                memberCount = 1;
            } else {
                memberCount++;
            }
            results.put(member, memberCount);
        }

        for (Entry<Member, Integer> entry : results.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

}
