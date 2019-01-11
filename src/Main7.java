
public class Main7 {

    public static void main(String args[]){


    }

    //dijistra, 单源最短路径
    public static void dijistra(int dis [][], int source){

        int len = dis.length;
        boolean vis[] = new boolean [len];
        int val[] = new int [len]; //源节点到这个节点的距离
        int pre[] = new int [len];

        vis[source] = true;
        for(int i = 0; i < len; i++){
            val[i] = dis[0][i];
            pre[i] = (dis[0][i] == Integer.MAX_VALUE)?-1:source;
        }
        val[0] = Integer.MAX_VALUE;

        for(int i = 1; i < len; i++){
            //找到最短节点
            int min = source;
            for(int j = 0; j < len; j++ ){
                if(!vis[j] && val[j] < val[min]) {
                    min = j;
                }
            }
            vis[min] = true;
            //更新距离
            for(int j = 0; j < len; j++){
                if(!vis[j] && val[min] + dis[min][j] < val[j]){
                    val[j] = val[min] + dis[min][j];
                    pre[j] = min;
                }
            }

        }

    }

    //floyd, 动态规划
    // dp[i][j]: 从节点ｉ到节点ｊ的最短路径
    public static void floyd(int dis[][]){

        int len = dis.length;

        int dp[][] = new int [len][len];
        int path[][] = new int[len][len];

        //dp[i][j] = dp[][]
        for(int i =0; i < len; i++){
            for(int j = 0; j < len; j++){
                dp[i][j] = dis[i][j];
                path[i][j] = -1;
            }
        }
//将k节点加入, 看能够成功的减少路径i到ｊ之间的长度
        for(int k = 0; k < len; k++){
            for(int i =0; i < len; i++){
                for(int j = 0; j < len; j++){
                    if(dp[i][j] > dp[i][k] + dp[k][j]){
                        dp[i][j] = dp[i][k] + dp[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }



    }




}
