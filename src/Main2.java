
public class Main2 {

    public static void main(String args[]){
        int[][] martix = new int[][]{{1,3,5},{4,8},{2,5}};
        new Main2().getMinScope(martix);
    }

    public void getMinScope(int martix[][]){
        int len = martix.length;
        Node [] nodes = new Node [len];
        int max = Integer.MIN_VALUE;

        for(int i=0;i<len;i++){
            nodes[i] = new Node(martix[i][0],i,0);
            if(max < martix[i][0])
                max = martix[i][0];
        }

        //堆排序,小根堆
        for(int i = len/2 -1; i>=0; i--){
            modify(nodes, i, len);
        }

        int min = nodes[0].value;
        int res = max - min;
        int tmpMax = max;
        int tmpMin = min;

        while(nodes[0].index < martix[nodes[0].arrNum].length){
            if(nodes[0].index == martix[nodes[0].arrNum].length - 1){
                System.out.println("最小范围为：" + tmpMin + ":" + tmpMax);
                break;
            }
            nodes[0].value = martix[nodes[0].arrNum][++nodes[0].index];
            if(max < nodes[0].value)
                max = nodes[0].value;
            modify(nodes, 0, len);
            min = nodes[0].value;
            if(max - min < res){
                res = max - min;
                tmpMax = max;
                tmpMin = min;
            }

        }
    }

    public void modify(Node[] nodes, int i, int len){
        Node node = nodes[i];
        for(int j = 2 * i + 1; j <len; j = 2 * j + 1){
            if(j + 1 < len && nodes[j + 1].value < nodes[j].value)
                j = j + 1;
            if(node.value > nodes[j].value){
                //交换元素
                nodes[i] = nodes[j];
                i = j;
            }else{
                break;
            }
        }
        nodes[i] = node;
    }

}


class Node{
    public int value;
    public int arrNum;
    public int index;

    public Node(int value, int arrNum, int index) {
        this.value = value;
        this.arrNum = arrNum;
        this.index = index;
    }
}
