/*
给定K个有序数组，求一个最小长度的区间【s,t】，使得每个数组中最少有一个元素在这个区间内。如果有多个长度相等的区间满足条件，则选择起始点s最小的那一个。

 */

class HeapNode{
    public int value;
    public int arrNum;
    public int index;
    public HeapNode(int value,int arrNum,int index){
        this.value = value;
        this.arrNum = arrNum;
        this.index = index;

    }
}

public class Main4 {
    public void modifyHeap(HeapNode[] heap,int index,int heapSize){
        HeapNode temp = heap[index];
        int child = index * 2 + 1;
        while(child < heapSize){
            if(child + 1 < heapSize && heap[child + 1].value < heap[child].value)
                child = child + 1;
            if(temp.value > heap[child].value){
                heap[index] = heap[child];
                index = child;
            }
            else
                break;
            child = 2 * index + 1;
        }
        heap[index] = temp;
    }

    public void getMinScope(int[][] matrix){
        int heapSize = matrix.length;
        HeapNode[] heap = new HeapNode[heapSize];
        int max = Integer.MIN_VALUE;
        for(int i=0;i<heapSize;i++){
            heap[i] = new HeapNode(matrix[i][0],i,0);
            max = Math.max(heap[i].value,max);
        }
        for(int i=heapSize / 2 - 1;i>=0;i--){
            modifyHeap(heap,i,heapSize);
        }
        int min = heap[0].value;
        int res = max - min;
        int tempMax = max;
        int tempMin = min;
        while(heap[0].index < matrix[heap[0].arrNum].length){
            if(heap[0].index == matrix[heap[0].arrNum].length-1){
                System.out.println("最小范围为：" + tempMin + ":" + tempMax);
                break;
            }
            heap[0].value = matrix[heap[0].arrNum][++heap[0].index];
            if(max<heap[0].value)
                max = heap[0].value;
            modifyHeap(heap,0,heapSize);
            min = heap[0].value;
            if(max - min < res){
                res = max - min;
                tempMax = max;
                tempMin = min;
            }
        }
    }

    public static void main(String[] args) {
        int[][] martix = new int[][]{{1,3,5},{4,8},{2,5}};
        new Main4().getMinScope(martix);
    }



}