import java.util.*;
import java.lang.*;
import java.io.*;
class myFile implements Comparable<myFile>{
    private String name;
    private String ext;
    private int isNotExist;
    myFile(String name, String ext){
        this.name = name;
        this.ext = ext;
    }
    public void setIsNotExist(int x){
        this.isNotExist = x;
    }
    public String getName(){
        return this.name;
    }
    public String getExt(){
        return this.ext;
    }
    public int getIsNotExist(){
        return this.isNotExist;
    }
    @Override
    public int compareTo(myFile o) {
        if(this.name.compareTo(o.getName()) != 0)
            return this.name.compareTo(o.getName());
        if(this.isNotExist!=o.getIsNotExist())
            return this.isNotExist-o.getIsNotExist();
        return this.ext.compareTo(o.getExt());
    }
}
public class Main {
    public static void main(String []args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String []parse;
        List <myFile> li = new ArrayList<>();
        Map <String, String> eSet = new HashMap<>();
        parse = bf.readLine().split(" ");
        int n = Integer.parseInt(parse[0]);
        int m = Integer.parseInt(parse[1]);
        for(int i=0;i<n;i++){
            parse = bf.readLine().split("\\.");
            li.add(new myFile(parse[0], parse[1]));
        }
        for(int i=0;i<m;i++){
            String ext = bf.readLine();
            eSet.put(ext,"");
        }
        for(int i=0;i<n;i++){
            if(eSet.containsKey(li.get(i).getExt()))
                li.get(i).setIsNotExist(0);
            else
                li.get(i).setIsNotExist(1);
        }
        Collections.sort(li);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            String name = li.get(i).getName();
            String ext = li.get(i).getExt();
            sb.append(name + "." + ext + "\n");
        }
        System.out.println(sb.toString());
    }
}