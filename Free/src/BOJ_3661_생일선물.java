import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

public class BOJ_3661_생일선물 {
    static int T, price, n, avg, res[];
    static List<Person> list;
    static class Person implements Comparable<Person> {
        int index;
        int limit;
        int pay;

        public Person(int index, int limit, int pay) {
            this.index = index;
            this.limit = limit;
            this.pay = pay;
        }

        @Override
        public int compareTo(Person o) {
            return o.limit-this.limit;
        }

        @Override
        public String toString() {
            return index+" "+limit+" "+pay;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t=0; t<T; t++) {
            st = new StringTokenizer(br.readLine());
            price = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();
            res = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++) list.add(new Person(i, Integer.parseInt(st.nextToken()), 0));

            avg = price/n;

            for (int i=0; i<list.size(); i++) {
                Person p = list.get(i);
                if (p.limit <= avg) {
                    price -= p.limit;
                    res[p.index] = p.limit;
                    list.remove(p);
                    i--;
                } else {
                    price -= avg;
                    p.pay = avg;
                }
            }

            Collections.sort(list);
            boolean flag = false;

            while (price > 0) {
//                System.out.println(list);
                if (list.size() == 0) {
                    flag = true;
                    break;
                }
                for (int i=0; i<list.size(); i++) {
                    Person p = list.get(i);
                     p.pay++;
                     price--;
                     if (p.pay == p.limit) {
                         res[p.index] = p.pay;
                         list.remove(p);
                         i--;
                     }
                     if (price == 0) break;
                }

            }


            if (flag) sb.append("IMPOSSIBLE\n");
            else {
                for (Person p : list) {
                    if (res[p.index] == 0) res[p.index] = p.pay;
                }
                for(int i=0; i<n; i++) {
                    sb.append(res[i]).append(" ");
                }
                sb.append("\n");
            }

        }
        System.out.println(sb);
    }
}
