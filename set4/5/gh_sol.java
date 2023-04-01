import java.io.*;
import java.lang.*;
import java.util.*;
class Card{
    private final String op;
    private final int id;
    private final int resourceName;
    Card(String op, int id, int resourceName){
        this.op = op;
        this.id = id;
        this.resourceName = resourceName;
    }

    public String getOp() {
        return op;
    }

    public int getId() {
        return id;
    }

    public int getResourceName() {
        return resourceName;
    }
}
/*
manager 안에 state 를 넣음.
manager 가 state 를 가지고 판단하기 때문.
*/
class manager{
    /*
    manager 바깥에서 game state 의 상태를 변경하지 못하도록 처리함.
    card 덱은 바깥에서 한 번 초기화 하면 여기에서만 변경될 수 있도록 처리.
    */
    private final gameState gs;
    manager(int peopleNum, Deque<Card> deck){
        gs = new gameState(peopleNum, deck);
    }

    public int doTurn(int peopleNum) {
        Card curCard = gs.getCurCard(peopleNum);
        switch(curCard.getOp()){
            case "acquire":
                if(!gs.checkOccupyResource(curCard.getResourceName())){
                    gs.occupyResource(curCard.getResourceName());
                    gs.discardCard(peopleNum);
                }
                break;
            case "release":
                gs.releaseResource(curCard.getResourceName());
            case "next":
                gs.discardCard(peopleNum);
                break;
        }
        return curCard.getId();
    }
    /*
    game state 는 resource 의 occupy 상태,
    누가 어떤 카드를 들고 있는지, deck 이 들어가 있음.
    */
    static class gameState{
        private final HashSet<Integer> resource = new HashSet<>();
        private final Card []peopleKeepCard;
        private final Deque<Card> deck;
        gameState(int peopleNum, Deque<Card> deck){
            this.peopleKeepCard = new Card[peopleNum + 1];
            this.deck = deck;
        }
        public Card getCurCard(int peopleNum){
            if(peopleKeepCard[peopleNum] == null)
                peopleKeepCard[peopleNum] = deck.pollFirst();
            return peopleKeepCard[peopleNum];
        }

        public void discardCard(int peopleNum) {
            peopleKeepCard[peopleNum] = null;
        }

        public void occupyResource(int resourceNum){
            resource.add(resourceNum);
        }

        public void releaseResource(int resourceNum) {
            resource.remove(resourceNum);
        }

        public boolean checkOccupyResource(int resourceNum) {
            return resource.contains(resourceNum);
        }
    }
}
public class Main {
    public static void main(String []args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String []line = bf.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int T = Integer.parseInt(line[1]);
        int []game = new int[T];
        Deque<Card> cList = new ArrayDeque<>();

        line = bf.readLine().split(" ");
        for(int i=0;i<T;i++)
            game[i] = Integer.parseInt(line[i]);
        for(int i=0;i<T;i++){
            line = bf.readLine().split(" ");
            int id = Integer.parseInt(line[0]);
            if(line[1].compareTo("next") == 0)
                cList.add(new Card(line[1], id,-1));
            else
                cList.add(new Card(line[1], id, Integer.parseInt(line[2])));
        }
        manager mg = new manager(n, new ArrayDeque<>(cList));
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<T;i++){
            int peopleNum = game[i];
            int ret = mg.doTurn(peopleNum);
            sb.append(ret).append("\n");
        }
        System.out.println(sb.toString());
    }
}
