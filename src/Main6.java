import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//所有的排序算法
public class Main6 {

    public static void main(String args[]){

        int nums [] = {2,7,8,9,4,5,0,1,6};
//        bubbleSort(nums, nums.length);
//        insertSort(nums, nums.length);
//        selectSort(nums, nums.length);
//        shellSort(nums, nums.length);
//        mergeSort(nums);

//        quicklySort(nums);
//        heapSort(nums);
//        countingSort(nums, 0, 10);
//        bucketSort(nums);
        radixSort(nums);
        System.out.println(Arrays.toString(nums));
        
    }



    //基数排序, 稳定排序, 排序收集.　首先按照低位进行排序, 然后收集, 然后按照高位排序, 然后收集, 直到最高位.
    private static void radixSort(int[] nums) {
    }

    //桶排序是计数排序的升级版, 高效与否在于映射函数的确认. 桶排序的工作原理: 假设数据服从均匀分布, 将数据分到有限的桶中,　然后对每个桶里的数据进行排序. 桶排序最好的情况下使用线性时间o(n),　桶越多，则各个桶中的数据量越多,　则排序的时间越大, 但消耗的空间越多. 稳定排序
    private static void bucketSort(int[] nums) {
    }



    //计数排序, 数组的大小范围, 线性排序
    private static void countingSort(int[] nums, int min, int max) {

        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i],0)+1);
        }

        int i = 0, len = nums.length;
        for(int ii : map.keySet()){
            int n = map.get(ii);
            while( n > 0 ){
                nums[i++] = ii;
                n--;
            }
        }

    }

    //堆排序, o(nlog2(n)), 不稳定
    //首先调整成大根堆, 最后的一个元素和堆顶元素对调,重新开始新的调整操作
    private static void heapSort(int[] nums) {

        int len = nums.length;
        for(int i = len/2 - 1; i >= 0; i--){
            modifyHeap(nums, i, nums.length);
        }
        for(int i = 0; i< nums.length - 1; i++){
            swap(nums, 0, nums.length - i - 1);
            modifyHeap(nums, 0,nums.length - i - 1);
        }
    }

    private static void modifyHeap(int[] nums, int i, int len) {
        int j = i * 2 + 1, tmp = nums[i];
        for(; j < len ; j = 2 * j + 1){
            if(j+1 < len && nums[j] < nums[j + 1])
                j++;

            if(tmp < nums[j]) {
                nums[i] = nums[j];
                i = j;
            }else{
                break;
            }
        }
        nums[i] = tmp;
    }

    //快速排序, o(nlog2(n)), 最坏o(n^2), 不稳定
    private static void quicklySort(int[] nums) {
        quicklySort(nums, 0, nums.length - 1);

    }

    private static void quicklySort(int[] nums, int left, int right) {
        if(left >= right)
            return;

        int part = partition(nums, left, right);
        if(part != -1) {
            quicklySort(nums, left, part - 1);
            quicklySort(nums, part + 1, right);
        }
    }

    private static int partition(int[] nums, int left, int right) {

        if(left >= right)
            return -1;

        int tmp = nums[right];
        while(left < right){
            while(left < right && nums[left] <= tmp){
                left ++;
            }
            if(left < right && nums[left] > tmp){
                nums[right--] = nums[left];
            }
            while(left < right && nums[right] >= tmp){
                right--;
            }
            if(left < right && nums[right] < tmp){
                nums[left++] = nums[right];
            }
        }
        nums[left] = tmp;
        return left;
    }

    //归并排序, 稳定, o(nlog2(n))
    private static void mergeSort(int[] nums) {

        mergeSort0(nums, 0, nums.length - 1);

    }

    private static void mergeSort0(int[] nums, int left, int right) {
        if(left >= right)
            return;

        int mid = (right + left) / 2;
        mergeSort0(nums, left, mid);
        mergeSort0(nums, mid+1, right);

        merge(nums, left, mid, mid+1, right);

    }

    private static void merge(int[] nums, int left, int right, int left1, int right1) {


        int nn [] = new int[right1 - left + 1];
        int i = 0, ff = left;
        while(left <= right && left1 <= right1){
            if(nums[left] < nums[left1]){
                nn[i++] = nums[left++];
            }else{
                nn[i++] = nums[left1++];
            }
        }

        while(left <= right){
            nn[i++] = nums[left++];
        }
        while(left1 <= right1){
            nn[i++] = nums[left1++];
        }

        for(int ii: nn){
            nums[ff++] = ii;
        }
    }


    //希尔排序, 一个优化的插入排序, 缩小增量排序. 直接插入在基本有序的情况下, 时间效率是很高的
    private static void shellSort(int[] nums, int length) {
        int i, j, gap;  //同一分组中的元素　ｉ, gap+i
        for(gap = length/2 ; gap > 0; gap /= 2){
            for(i = 0; i< gap; i++){
                //直接插入排序
                for(j = i + gap; j < length; j += gap){
                    if(nums[j] < nums[j-gap]){

                        int tmp = nums[j], k = j - gap; // j-gap , j
                        while(k >= 0 && nums[k] > tmp){
                            nums[k + gap] = nums[k];
                            k -= gap;
                        }

                        nums[k + gap] = tmp;
                    }
                }
            }
        }
    }


    //选择排序, 没趟选择一个最小值放到最前面
    //时间复杂度o(n^2), 不稳定
    private static void selectSort(int[] nums, int length) {

        for(int i = 0; i < length - 1; i++){
            int min = i;
            for(int j = i + 1; j < length; j++){
                if(nums[min] > nums[j]){
                    min = j;
                }
            }
            swap(nums, i, min);
        }
        System.out.println(Arrays.toString(nums));
    }


    //插入排序, 选择数据,　不断地将数据插入到前面已排好序的数组中
    //o(n^2), 最好o(n), 稳定
    private static void insertSort(int[] nums, int length) {

        for(int i = 1; i < length; i++){
            int tmp = nums[i], j = i - 1;
            for(; j >= 0; j--){

                if(nums[j] > tmp){
                    nums[j + 1] = nums[j];
                }else{
                    break;
                }
            }
            nums[j + 1] = tmp;
        }
        System.out.println(Arrays.toString(nums));
    }

    //时间复杂度: 对排序数据的总的操作次数。反映当n变化时，操作次数呈现什么规律。
    //冒泡排序, 时间o(n^2), 最好o(n), 稳定算法
    private static void bubbleSort(int[] nums, int length) {
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length - i - 1; j++){
                if(nums[j] > nums[j + 1])
                    swap(nums, j, j+1);
            }
        }
        System.out.println(Arrays.toString(nums));
    }
    
    

    private static void swap(int nums [], int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


}
