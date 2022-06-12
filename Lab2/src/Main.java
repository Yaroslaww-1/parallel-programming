public class Main {
    public static void main(String[] args) {
        int tests = 12;
        int size = 500;
        int threadsCount = 100;

        for (int i = 0; i < tests; i++) {
            var matrix1 = Matrix.generateRandom(size, size);
            var matrix2 = Matrix.generateRandom(size, size);
            Main.benchmarkSequential(matrix1, matrix2);
            Main.benchmarkParallel(matrix1, matrix2, threadsCount);
            Main.benchmarkParallelFox(matrix1, matrix2, threadsCount);
        }
    }

    private static void benchmarkSequential(Matrix matrix1, Matrix matrix2) {
        long start = System.currentTimeMillis();
        var mult = matrix1.multiplySequential(matrix2);
        long time = System.currentTimeMillis() - start;
        System.out.println("Sequential: " + time + "ms");
    }

    private static void benchmarkParallel(Matrix matrix1, Matrix matrix2, int threadsCount) {
        long start = System.currentTimeMillis();
        var mult = matrix1.multiplyParallel(matrix2, threadsCount);
        long time = System.currentTimeMillis() - start;
        var equal = mult.equals(matrix1.multiplySequential(matrix2));
        if (!equal) System.out.println("Parallel gives incorrect result");
        System.out.println("Parallel  : " + time + "ms");
    }

    private static void benchmarkParallelFox(Matrix matrix1, Matrix matrix2, int threadsCount) {
        long start = System.currentTimeMillis();
        var mult = matrix1.multiplyParallelFox(matrix2, threadsCount);
        long time = System.currentTimeMillis() - start;
        var equal = mult.equals(matrix1.multiplySequential(matrix2));
        if (!equal) System.out.println("Fox method gives incorrect result");
        System.out.println("Fox method: " + time + "ms");
    }

}
