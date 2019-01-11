
//0-1背包问题
public class Main3 {



    int getMaxValue(int price[], int weight[], int pg){

        //递推pd[i][w]: 前ｉ个物品中，能够取得的最大重量不超过ｗ的最大的价值
        //pd[i+1][w] = max {pd[i][w], pd[i][W-w[i+1]]+price[i+1]}

        int len = price.length;
        int dp[][] = new int [len][pg+1];

        for(int w=0; w <= pg; w++){
            if(weight[0] <= w){
                dp[0][w] = price[0];
            }
        }

        for(int i = 1; i < price.length; i++){
            for(int w = 0; w <= pg; w++){
                if(w < weight[i]){
                    dp[i][w] = dp[i-1][w];
                }else{
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w- weight[i]] + price[i]);
                }
            }
        }
        return dp[len - 1][pg];
    }


    int getMaxValue1(int price[], int weight[], int pg){

        //递推pd[i][w]: 前ｉ个物品中，能够取得的最大重量不超过ｗ的最大的价值
        //pd[i+1][w] = max {pd[i][w], pd[i][W-w[i+1]]+price[i+1]}

        int len = price.length;
        int dp[] = new int [pg+1];

        for(int i = 0; i < price.length; i++){
            for(int w = 0; w <= pg; w++){
                if(i == 0){
                    dp[w] = (weight[i] <= w)?price[i]:0;
                    continue;
                }else if(w < weight[i]){
                    dp[w] = dp[w];
                }else{
                    dp[w] = Math.max(dp[w], dp[w- weight[i]] + price[i]);
                }
            }
        }
        return dp[pg+1];
    }

    public static void main(String args[]){
        int weight []= {5, 4, 7, 2, 6};
        int price [] = {12, 3, 10, 3, 6};

        new Main3().getMaxValue1(price, weight, 10);

    }



}
