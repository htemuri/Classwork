public class NaiveMethod {
    public float[][] multiply(float[][] A, float[][] B, int rowA, int colA,
                              int rowB, int colB, int size) {
        float[][] C = new float[size][size];
        if (size == 1) {
            C[0][0] = A[rowA][colA] * B[rowB][colB];
        } else {
            int newSize = size / 2;
            // C11 = A11 * B11 + A12 * B21
            add(C, multiply(A, B, rowA, colA, rowB, colB, newSize),//A11*B11
                    multiply(A, B, rowA, colA + newSize, rowB + newSize, colB, newSize), //A12*B21
                    0, 0);//C11

            // C12 = A11 * B12 + A12 * B22
            add(C, multiply(A, B, rowA, colA, rowB, colB + newSize, newSize),//A11*B12
                    multiply(A, B, rowA, colA + newSize, rowB + newSize, colB + newSize, newSize),//A12*B22
                    0, newSize);//C12

            // C21 = A21 * B11 + A22 * B21
            add(C, multiply(A, B, rowA + newSize, colA, rowB, colB, newSize),//A21*B11
                    multiply(A, B, rowA + newSize, colA + newSize, rowB + newSize, colB, newSize),//A22*B21
                    newSize,
                    0);//C21

            // C22 = A21 * B12 + A22 * B22
            add(C, multiply(A, B, rowA + newSize, colA, rowB, colB + newSize, newSize),//A21*B12
                    multiply(A, B, rowA + newSize, colA + newSize, rowB + newSize, colB + newSize, newSize), //A22*B22
                    newSize, newSize);//C22
        }

        return C;
    }

    private void add(float[][] C, float[][] A, float[][] B, int rowC, int colC) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i + rowC][j + colC] = A[i][j] + B[i][j];
            }
        }

    }

    public float[][] matrixMultiplicationFinal(float[][] A, float[][] B) {

        return multiply(
                A, B, 0, 0,
                0, 0, A.length);

    }
}