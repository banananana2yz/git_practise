import java.util.HashMap;
import java.util.Map;

public class Main5 {

    public static void main(String args[]){
        int nums []= {1,2,3,4,5,6,7,8};

        String str1 = "abcde";
        String str2 = "aabd";
        int len = new Main5().longestComSubseq(str1, str2);
        System.out.println(len);
    }

    //最长的斐波那契数子序列
    public int lenLongestFibSubseq(int[] A) {
        int N = A.length;
        Map<Integer, Integer> index = new HashMap();
        for (int i = 0; i < N; ++i)
            index.put(A[i], i);

        Map<Integer, Integer> longest = new HashMap();
        int ans = 0;

        for (int k = 0; k < N; ++k){
            for (int j = 0; j < k; ++j) {
                int i = index.getOrDefault(A[k] - A[j], -1);
                if (i >= 0 && i < j) {
                    // Encoding tuple (i, j) as integer (i * N + j)
                    int cand = longest.getOrDefault(i * N + j, 2) + 1;
                    longest.put(j * N + k, cand);
                    ans = Math.max(ans, cand);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }

    //最长递增子串
    public int longestSubseq(int nums[]){
        int len = nums.length;


        int l [] = new int [len];

        //l[i]: 第ｉ个加入时，表示的最长子串的长度
        for(int i = 0; i < l.length; i++){
            l[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    l[i] = Math.max(l[j] + 1, l[i]);
                }
            }
        }
        return l[len - 1];
    }


    //最长公共连续子串
    //要输出lcs的内容，　需要多一个二位数组来记录所
    public int longestComSubseq(String str1, String str2){
        //dp[i][j] : 都是表示str1和str2分别以ｉ和ｊ结尾时，最长的公共子序列
//        dp[i][j] = dp[i-1][j-1] + 1,  str[i] = str2[j]
//                 = max(dp[i][j-1], dp[i-1][j]), str1[i] != str2[j]
        int dp [][] = new int[str1.length()][str2.length()];
        int str[][] = new int[str1.length()][str2.length()];
        for(int i = 0; i < str1.length(); i++){
            for(int j = 0; j < str2.length(); j++){

                if(str1.charAt(i) == str2.charAt(j)){
                    dp[i][j] = (i == 0 || j == 0)? 1 : dp[i-1][j-1] + 1;
                    str[i][j] = 1;
                }else {

//                    dp[i][j] = Math.max((j == 0)? 0 : dp[i][j-1], (i == 0 ) ? 0 :dp[i-1][j]);
                    if(((j == 0)? 0 : dp[i][j-1]) > ((i == 0 ) ? 0 :dp[i-1][j])){
                        dp[i][j] = (j == 0)? 0 : dp[i][j-1];
                        str[i][j] = 2;
                    }else{
                        dp[i][j] = (i == 0 ) ? 0 :dp[i-1][j];
                        str[i][j] = 3;
                    }
                }
            }
        }
        return dp[str1.length() - 1][str2.length()-1];
    }

    //最长公共子串
    public int longestComSeq(String str1, String str2){
//        dp[i][j] = dp[i-1][j-1] + 1,  str[i] = str2[j]
//                 = 0, str1[i] != str2[j]

        int dp[][] = new int[str1.length()][str2.length()];
        int mx = -1;
        for(int i = 0; i < str1.length(); i++){
            for(int j = 0; j < str1.length(); j++){
                if(str1.charAt(i) == str2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if(dp[i][j] > mx)
                        mx = dp[i][j];
                }else{
                    dp[i][j] = 0;
                }
            }
        }
        return mx;
    }




}
