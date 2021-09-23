import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        int[] A = new int[] { -2, -3, 4, -1, -2, 1, 5, -3 };
        int[] r = findMaxSubarray(A, 0, A.length - 1);
        System.out.println("The stock started rising at array index " + r[0] + ".\n" +
                            "The stock stopped rising at array index " + r[1] + ".\n" +
                            "The stock rose by " + r[2] + ".");
    }

    private static int[] findMaxSubarray(int[] A, int low, int high){
        if(high == low){
            return new int[] {low, high, A[low]};
        }
        else{
            int mid = (int) Math.floor((low + high) / 2.0);
            int[] left = findMaxSubarray(A, low, mid);

            int[] right = findMaxSubarray(A, mid + 1, high);

            int[] cross = findMaxCrossingSubarray(A, low, mid, high);

            if(left[2] >= right[2] && left[2] >= cross[2])
                return new int[] {left[0], left[1], left[2]};   // return leftLow, leftHigh, leftSum
            else if(right[2] >= left[2] && right[2] >= cross[2])
                return new int[] {right[0], right[1], right[2]};   // return rightLow, rightHigh, rightSum
            else
                return new int[] {cross[0], cross[1], cross[2]};   // return crossLow, crossHigh, crossSum

        }
    }

    private static int[] findMaxCrossingSubarray(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0;
        for(int i = mid; i >= low; i--){
            sum += A[i];
            if(sum > leftSum){
                leftSum = sum;
                maxLeft = i;
            }
        }
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = 0;
        for(int j = mid + 1; j <= high; j++){
            sum += A[j];
            if(sum > rightSum){
                rightSum = sum;
                maxRight = j;
            }
        }

        return new int[] {maxLeft, maxRight, leftSum + rightSum};

    }
}
