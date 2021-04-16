import java.util.Random;

public class problem2 {
    public static void main(String[] args) {
        Random rnd = new Random();
        int power = 9;
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
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        float elapsedTimeSec = elapsedTimeMillis / 1000F;
        System.out.println("naive method: "+ elapsedTimeSec +" seconds");
        start = System.currentTimeMillis();

        // Strassen method

        resultMatrix = s.multiply(m, m2);

        elapsedTimeMillis = System.currentTimeMillis() - start;
        elapsedTimeSec = elapsedTimeMillis / 1000F;
        System.out.println("strassen method: "+ elapsedTimeSec +" seconds");

    }
}
