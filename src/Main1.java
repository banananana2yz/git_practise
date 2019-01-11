import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;

//数组的全排列
public class Main1 {

    public void printNum(int [] nums, int index, int len){

        if(index == len - 1){
            System.out.println(Arrays.toString(nums));
            return;
        }

        for(int i = index; i < len; i++){
            swap(nums, index, i);
            printNum(nums, index + 1, len);
            swap(nums, i, index);
        }

    }

    public void swap(int nums[], int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String args[]){
        int nums[] ={1,2,3};
        new Main1().printNum(nums,0,nums.length);
    }

}
