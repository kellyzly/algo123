package kellyzly;

public class Square {
//

//    给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
//
//    示例:
//
//    输入: 3
//    输出:
//            [
//            [ 1, 2, 3 ],
//            [ 8, 9, 4 ],
//            [ 7, 6, 5 ]
//            ]

    static int N = 3;  // N=4 有bug
    private static int[][] data = new int[N][N];
    static int start = 1;

    public static void main(String[] args) {

        int i = 0;
        int j = 0;
        int r = N;

        while (r >= 1) {

            print(i, j, r);
            r = r / 2;
            i = i + 1;
            j = j + 1;
        }


    }


    public static void print(int i, int j, int r) {


        for (int m = i; m < i + r; m++) {
            data[i][m] = start;
            System.out.println(String.format("shangmian dot[%d][%d]= %d", i, m, start));
            start++;
        }

        for (int n = j + 1; n < j + r; n++) {
            data[n][i + r - 1] = start;
            System.out.println(String.format("youbian dot[%d][%d]= %d", n, i + r - 1, start));
            start++;
        }
//xia mian
        for (int o = i + r - 2; o >= i; o--) {
            data[i + r - 1][o] = start;
            System.out.println(String.format("xia mian dot[%d][%d]= %d", i + r - 1, o, start));
            start++;
        }


        for (int p = i + r - 2; p > j; p--) {
            data[p][j] = start;
            System.out.println(String.format("zuobian dot[%d][%d]= %d", p, j, start));
            start++;
        }
    }
}
