package datastructure.simplecode.stack;

import java.util.Stack;

/**
 * 传入两个数组，一个代表入栈顺序，一个代表出站顺序，返回一个boolean值表示这个出栈顺序是否满足入栈顺序。
 *
 * 传入：  pushArray:12345  popArray:43512
 * 返回： false
 *
 * Created by wangdongdong on 17/7/17.
 */
public class PopOrder {


    /**
     * 将入栈序列依次入栈，如果入栈的元素，等与出栈序列的第一个元素，则出栈，直到入栈完毕，看新的栈内是否还有元素
     *
     * i = 0; stack:1; pop:4; while false;
     * i = 1; stack: 1,2; pop:4; while false;
     * i = 2; stack: 1,2,3; pop:4; while false;
     * i = 3; stack: 1,2,3,4; pop:4; while true; i_pop:1; stack:1,2,3;
     *                        pop:3; while true; i_pop:2; stack:1,2; pop:5;while false;
     * i = 4; stack: 1,2,4; pop:5; while false;
     * i = 5; stack: 1,2,4,5; pop:5; while true; i_pop:3; stack:1,2,4;
     *                        pop:1;while false; stack: 1,2,4
     *
     * stack.empty() -> false
     *
     * @param pushArray
     * @param popArray
     * @return
     */
    public static boolean isPopOrder(int[] pushArray, int[] popArray) {
        if (pushArray == null || popArray == null) {
            return false;
        }

        if (pushArray.length == 0 || popArray.length == 0) {
            return false;
        }

        int lengthPush = pushArray.length;
        int lengthPop = popArray.length;
        if (lengthPush != lengthPop) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int i_pop = 0; //出栈位置
        for (int i = 0; i < lengthPush; i++) {
            stack.push(pushArray[i]);
            //每次有入栈元素入栈，检查是否与出栈元素相等
            while (!stack.empty() && stack.peek() == popArray[i_pop]) {
                i_pop++;
                stack.pop();
            }
        }

        //如果入栈结束后，能将所有入栈元素出栈，则是它的出栈顺序
        return stack.empty();
    }

    public static void main(String[] args) {
        isPopOrder(null, null);
    }
}
