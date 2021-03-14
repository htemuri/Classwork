import java.util.Random;

import org.ejml.*;
import org.ejml.simple.SimpleMatrix;
import org.ejml.simple.SimpleOperations;

public class Problem2 {
    public static void main(String[] args) {
        Random rnd = new Random();
//         2^9 x 2^9 matrices
        float[][] m = new float[(int) Math.pow(2, 12)][(int) Math.pow(2, 12)];
        float[][] m2 = new float[(int) Math.pow(2, 12)][(int) Math.pow(2, 12)];
        float[][] resultMatrix = new float[(int) Math.pow(2, 12)][(int) Math.pow(2, 12)];
        //
        long start = System.currentTimeMillis();
//        SimpleMatrix firstMatrix = SimpleMatrix.random_FDRM((int) Math.pow(2, 12), (int) Math.pow(2, 12), -2, 2, rnd);
//        SimpleMatrix secondMatrix = SimpleMatrix.random_FDRM((int) Math.pow(2, 12), (int) Math.pow(2, 12), -2, 2, rnd);

//        SimpleMatrix resultMatrix = new SimpleMatrix((int) Math.pow(2,9),(int) Math.pow(2,9));
//        SimpleMatrix resultMatrix = firstMatrix.mult(secondMatrix);

//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print(resultMatrix.get(i,j)+", ");
//            }
//            System.out.println();
//        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = (rnd.nextFloat() * 4) - 2;
            }
        }
        for (int i = 0; i < m2.length; i++) {
            for (int j = 0; j < m2[i].length; j++) {
                m2[i][j] = (rnd.nextFloat() * 4) - 2;
            }
        }
//        System.out.println("m");
//        for (int i = 0; i < m.length; i++) {
//            for (int j = 0; j < m[i].length; j++) {
//                System.out.print(m[i][j] + ", ");
//            }
//            System.out.println();
//        }
//        System.out.println("m2");
//        for (int i = 0; i < m.length; i++) {
//            for (int j = 0; j < m[i].length; j++) {
//                System.out.print(m2[i][j] + ", ");
//            }
//            System.out.println();
//        }


        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                for (int l = 0; l < m[i].length; l++) {
                    resultMatrix[i][j] += m[i][l] * m2[l][j];
                }
            }
        }
//
//        System.out.println("result");
//        for (int i = 0; i < m.length; i++) {
//            for (int j = 0; j < m[i].length; j++) {
//                System.out.print(resultMatrix[i][j] + ", ");
//            }
//            System.out.println();
//        }

        long elapsedTimeMillis = System.currentTimeMillis() - start;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        float elapsedTimeMin = elapsedTimeMillis / (60 * 1000F);
        System.out.println(elapsedTimeSec);

    }

    public float[][] multiply(float[][] A, float[][] B) {
        // Order of matrix
        int n = A.length;

        // Creating a 2D square matrix with size n
        // n is input from the user
        float[][] R = new float[n][n];

        // Base case
        // If there is only single element
        if (n == 1)

            // Returnng the simple multiplication of
            // two elements in matrices
            R[0][0] = A[0][0] * B[0][0];

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
        float n = A.length;

        //
        float[][] C = new float[(int) n][(int) n];

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
        float n = A.length;

        // Creating a 2D square matrix
        float[][] C = new float[(int) n][(int) n];

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
    public void split(float[][] P, float[][] C, float iB, float jB)
    {
        // Iterating over elements of 2D matrix
        // using nested for loops

        // Outer loop for rows
        for (float i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)

            // Inner loop for columns
            for (float j1 = 0, j2 = jB; j1 < C.length;
                 j1++, j2++)

                C[(int) i1][(int) j1] = P[(int) i2][(int) j2];
    }

    // Method 5
    // Funtion to join child matrices
    // into (to) parent matrix
    public void join(float[][] C, float[][] P, float iB, float jB)

    {
        // Iterating over elements of 2D matrix
        // using nested for loops

        // Outer loop for rows
        for (float i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)

            // Inner loop for columns
            for (float j1 = 0, j2 = jB; j1 < C.length;
                 j1++, j2++)

                P[(int) i2][(int) j2] = C[(int) i1][(int) j1];
    }

}
