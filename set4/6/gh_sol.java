import java.util.*;
public class Main {
    private static final String []cardOp = new String[10];
    private static final List<ArrayList<Integer>> cardTurn = new ArrayList<>();
    private static final Set<String> ans = new TreeSet<>();
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int m = sc.nextInt();

        for(int i=0; i<=n; i++)
            cardTurn.add(new ArrayList<>());
        for(int i=1; i<=n; i++){
            int cNum = sc.nextInt();
            for(int j=0;j<cNum;j++){
                int card = sc.nextInt();
                cardTurn.get(i).add(card);
            }
        }
        sc.nextLine();
        for(int i=1;i<=m;i++)
            cardOp[i] = sc.nextLine();

        List<Integer> curLoc = new ArrayList<>(Collections.nCopies(n+1, 0));
        List<Integer> stack = new ArrayList<>();

        //dfs search
        //사람 p 가 카드를 몇 개 냈는지를 trace 한다.
        dfs(curLoc, stack, n);
        for(String cur:ans)
            sb.append(cur+"\n");
        System.out.print(sb.toString());
    }

    private static void dfs(List<Integer> curLoc, List<Integer> stack, int n) {
        if(check(curLoc, n)){
            work(stack);
            return;
        }
        for(int i=1; i<=n; i++){
            if(curLoc.get(i) == cardTurn.get(i).size())
                continue;
            int cl = curLoc.get(i);
            curLoc.set(i, cl+1); stack.add(cardTurn.get(i).get(cl));
            dfs(curLoc, stack, n);
            curLoc.set(i, cl); stack.remove(stack.size() -1);
        }
    }

    private static void work(List<Integer> stack) {
        String s = "";
        for (Integer lo : stack) {
            String cardOper = cardOp[lo];
            for (String oper: cardOper.split(",")){
                //쿼리 문자열의 length 는 무조건 5이다.
                //ADD x나 DEL x로 들어오므로, startsWith 가 적절하다.
                if(oper.startsWith("ADD"))
                    s = s + oper.charAt(4);
                else {
                    int idx = Integer.parseInt(String.valueOf(oper.charAt(4)));
                    s = rmIdx(s, idx);
                }
                if(s.equals("ERROR"))
                    return;
            }
        }
        if("".equals(s))
            ans.add("EMPTY");
        else
            ans.add(s);
    }

    private static String rmIdx(String s, int idx) {
        if(s.length() <= idx){
            ans.add("ERROR");
            return "ERROR";
        }
        return s.substring(0, idx) + s.substring(idx+1);
    }

    private static boolean check(List<Integer> curLoc, int n) {
        for(int i=1; i<=n; i++){
            if(curLoc.get(i) != cardTurn.get(i).size())
                return false;
        }
        return true;
    }
}
