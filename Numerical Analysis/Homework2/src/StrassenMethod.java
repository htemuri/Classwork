public class StrassenMethod {
    public float[][] multiply(float[][] A, float[][] B) {
        // Order of matrix
        int n = A.length;

        // Creating a 2D square matrix with size n
        // n is input from the user
        float[][] R = new float[n][n];

        // Base case
        // If there is only single element
        if (n == Math.pow(2,6))

            // Returning the simple multiplication of
            // two elements in matrices
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                for (int l = 0; l < A[i].length; l++) {
                    R[i][j] += A[i][l] * B[l][j];
                }
            }
        }

            // Matix
        else {
            // Step 1: Dividing Matrix into parts
            // by storing sub-parts to variables
            float[][] A11 = new float[n / 2][n / 2];
            float[][] A12 = new float[n / 2][n / 2];
            float[][] A21 = new float[n / 2][n / 2];
            float[][] A22 = new float[n / 2][n / 2];
            float[][] B11 = new float[n / 2][n / 2];
            float[][] B12 = new float[n / 2][n / 2];
            float[][] B21 = new float[n / 2][n / 2];
            float[][] B22 = new float[n / 2][n / 2];

            // Step 2: Dividing matrix A into 4 halves
            split(A, A11, 0, 0);
            split(A, A12, 0, n / 2);
            split(A, A21, n / 2, 0);
            split(A, A22, n / 2, n / 2);

            // Step 2: Dividing matrix B into 4 halves
            split(B, B11, 0, 0);
            split(B, B12, 0, n / 2);
            split(B, B21, n / 2, 0);
            split(B, B22, n / 2, n / 2);

            // Using Formulas as described in algorithm

            // M1:=(A1+A3)×(B1+B2)
            float[][] M1
                    = multiply(add(A11, A22), add(B11, B22));

            // M2:=(A2+A4)×(B3+B4)
            float[][] M2 = multiply(add(A21, A22), B11);

            // M3:=(A1−A4)×(B1+A4)
            float[][] M3 = multiply(A11, sub(B12, B22));

            // M4:=A1×(B2−B4)
            float[][] M4 = multiply(A22, sub(B21, B11));

            // M5:=(A3+A4)×(B1)
            float[][] M5 = multiply(add(A11, A12), B22);

            // M6:=(A1+A2)×(B4)
            float[][] M6
                    = multiply(sub(A21, A11), add(B11, B12));

            // M7:=A4×(B3−B1)
            float[][] M7
                    = multiply(sub(A12, A22), add(B21, B22));

            // P:=M2+M3−M6−M7
            float[][] C11 = add(sub(add(M1, M4), M5), M7);

            // Q:=M4+M6
            float[][] C12 = add(M3, M5);

            // R:=M5+M7
            float[][] C21 = add(M2, M4);

            // S:=M1−M3−M4−M5
            float[][] C22 = add(sub(add(M1, M3), M2), M6);

            // Step 3: Join 4 halves into one result matrix
            join(C11, R, 0, 0);
            join(C12, R, 0, n / 2);
            join(C21, R, n / 2, 0);
            join(C22, R, n / 2, n / 2);
        }

        // Step 4: Return result
        return R;
    }

    public float[][] sub(float[][] A, float[][] B)
    {
        //
        int n = A.length;

        //
        float[][] C = new float[n][n];

        // Iterating over elements of 2D matrix
        // using nested for loops

        // Outer loop for rows
        for (int i = 0; i < n; i++)

            // Inner loop for columns
            for (int j = 0; j < n; j++)

                // Substracting corresponding elements
                // from matrices
                C[i][j] = A[i][j] - B[i][j];

        // Returning the resultant matrix
        return C;
    }

    // Method 3
    // Funtion to add two matrices
    public float[][] add(float[][] A, float[][] B)
    {

        //
        int n = A.length;

        // Creating a 2D square matrix
        float[][] C = new float[n][n];

        // Iterating over elements of 2D matrix
        // using nested for loops

        // Outer loop for rows
        for (int i = 0; i < n; i++)

            // Inner loo pfor columns
            for (int j = 0; j < n; j++)

                // Adding corresponding elements
                // of matrices
                C[i][j] = A[i][j] + B[i][j];

        // Returning the resultant matrix
        return C;
    }

    // Method 4
    // Funtion to split parent matrix
    // into child matrices
    public void split(float[][] P, float[][] C, int iB, int jB)
    {
        // Iterating over elements of 2D matrix
        // using nested for loops

        // Outer loop for rows
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)

            // Inner loop for columns
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)

                C[i1][j1] = P[i2][j2];
    }

    // Method 5
    // Funtion to join child matrices
    // into (to) parent matrix
    public void join(float[][] C, float[][] P, int iB, int jB)

    {
        // Iterating over elements of 2D matrix
        // using nested for loops

        // Outer loop for rows
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)

            // Inner loop for columns
            for (int j1 = 0, j2 = jB; j1 < C.length;
                 j1++, j2++)

                P[i2][j2] = C[i1][j1];
    }

}
