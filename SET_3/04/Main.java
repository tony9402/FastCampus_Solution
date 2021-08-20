import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
    static HashMap<String, Folder> folders = new HashMap<>();
    public static void main(String[] args) {
        FastReader rd = new FastReader();

        int N = rd.nextInt();
        int M = rd.nextInt();
        for(int i = 0; i < N + M; i++) {
            String a = rd.next();
            String b = rd.next();
            int dir = rd.nextInt();
            Folder folderA = getFolder(a);
            if(dir == 1) {
                Folder folderB = getFolder(b);
                folderA.addFolder(folderB);
            }
            else {
                folderA.addFile(b);
            }
        }

        dfs(getFolder("main"));

        int Q = rd.nextInt();
        while(Q --> 0) {
            String path = rd.next();
            int index = path.lastIndexOf('/');
            if(index != -1) path = path.substring(index + 1);
            Folder current = getFolder(path);
            int answer1 = current.files.size();
            int answer2 = current.file_count;
            System.out.println(answer1 + " " + answer2);
        }
    }

    static void dfs(Folder cur) {
        for(Folder next : cur.child) {
            dfs(next);
            cur.mergeFolder(next);
        }
    }

    static Folder getFolder(String name) {
        if(folders.containsKey(name)) return folders.get(name);
        Folder newFolder = new Folder(name);
        folders.put(name, newFolder);
        return newFolder;
    }

    static class Folder {
        String folder_name;
        TreeSet<String> files = new TreeSet<>();
        ArrayList<Folder> child = new ArrayList<>();
        int file_count;

        Folder() { }
        Folder(String name) { this.setFolderName(name); }

        public void addFolder(Folder next) {
            child.add(next);
        }
        public void setFolderName(String name) {
            folder_name = name;
        }
        public void addFile(String name) {
            files.add(name);
            file_count ++;
        }
        public void mergeFolder(Folder other) {
            for(String filename : other.files) files.add(filename);
            file_count += other.file_count;
        }
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

