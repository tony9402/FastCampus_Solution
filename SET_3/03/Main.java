import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        String html = rd.nextLine();
        
        int L = 0, N = html.length();
        while(L < N) {
            int index = html.indexOf("title=\"", L);
            if(index == -1) break;
            int end_title = html.indexOf("\">", index);
            int end_tag   = html.indexOf("</div>", index);
            String title  = html.substring(index + 7, end_title);
            System.out.println("title : " + title);

            int cursor = L;
            while(true) {
                int start_p_tag = html.indexOf("<p>", cursor);
                if(start_p_tag == -1) break;
                if(start_p_tag >  end_tag) break;
                int end_p_tag = html.indexOf("</p>", start_p_tag);
                String paragraph = html.substring(start_p_tag + 3, end_p_tag);
                String erased = erase_simple_tag(paragraph);
                System.out.println(erased);
                cursor = end_p_tag;
            }
            L = end_tag;
        }
    }

    static String erase_simple_tag(String line) {
        String ret = "";
        boolean space = true;
        boolean bracket_open = false;
        for(int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == '>') {
                bracket_open = false;
                continue;
            }

            if(line.charAt(i) == '<') {
                bracket_open = true;
                continue;
            }

            if(bracket_open) continue;
            if(line.charAt(i) == ' ') {
                if(space) continue;
                space = true;
                ret += line.charAt(i);
            }
            else {
                space = false;
                ret += line.charAt(i);
            }
        }
        return ret.trim();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
