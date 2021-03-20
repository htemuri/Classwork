import java.util.Random;

//import org.ejml.*;
//import org.ejml.simple.SimpleMatrix;
//import org.ejml.simple.SimpleOperations;

public class problem2 {
    public static void main(String[] args) {
        Random rnd = new Random();
//         2^9 x 2^9 matrices
        int power = 12;
        StrassenMethod s = new StrassenMethod();
        float[][] m = new float[(int) Math.pow(2, power)][(int) Math.pow(2, power)];
        float[][] m2 = new float[(int) Math.pow(2, power)][(int) Math.pow(2, power)];
        float[][] resultMatrix = new float[(int) Math.pow(2, power)][(int) Math.pow(2, power)];
        //
        long start = System.currentTimeMillis();

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
        // naive method
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                for (int l = 0; l < m[i].length; l++) {
                    resultMatrix[i][j] += m[i][l] * m2[l][j];
                }
            }
        }

//        resultMatrix = n.matrixMultiplicationFinal(m,m2);
//
        // Strassen method

        resultMatrix = s.multiply(m, m2);




        long elapsedTimeMillis = System.currentTimeMillis() - start;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        float elapsedTimeMin = elapsedTimeMillis / (60 * 1000F);
        System.out.println(elapsedTimeSec +" seconds");

    }
}
