import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Matrix {
    private final int[][] matrix;

    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public static Matrix generateRandom(int n, int m) {
        var random = new Random();
        var matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }
        return new Matrix(matrix);
    }

    public int getHeight() {
        return matrix.length;
    }

    public int getWidth() {
        return matrix[0].length;
    }

    public int get(int i, int j) {
        return matrix[i][j];
    }

    public Matrix multiplySequential(Matrix other) {
        var height = getHeight();
        var width = other.getWidth();
        int[][] result = new int[height][width];

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                for (int k = 0; k < other.getHeight(); k++) {
                    result[row][col] += matrix[row][k] * other.get(k, col);
                }
            }
        }
        return new Matrix(result);
    }

    public Matrix multiplyParallel(Matrix other, int threadsCount) {
        var height = getHeight();
        var width = other.getWidth();
        int[][] result = new int[height][width];

        int threadRowsCount = (int) Math.round((double) height / threadsCount);
        var threads = new ArrayList<Thread>();
        for (int thread = 0; thread < threadsCount; thread++) {
            int from = thread * threadRowsCount;
            int to = Math.min(from + threadRowsCount, height);

            threads.add(new Thread(() -> {
                for (int row = from; row < to; row++) {
                    for (int col = 0; col < width; col++) {
                        for (int k = 0; k < other.getHeight(); k++) {
                            result[row][col] += matrix[row][k] * other.get(k, col);
                        }
                    }
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Matrix(result);
    }

    public Matrix multiplyParallelFox(Matrix other, int threadsCount) {
        var height = getHeight();
        var width = other.getWidth();
        int[][] result = new int[height][width];

        int threadRowsCount = (int) Math.round((double) height / threadsCount);
        var threads = new ArrayList<Thread>();
        for (int thread = 0; thread < threadsCount; thread++) {
            int from = thread * threadRowsCount;
            int to = Math.min(from + threadRowsCount, height);

            threads.add(new Thread(() -> {
                for (int row = from; row < to; row++) {
                    for (int col = 0; col < width; col++) {
                        result[row][col] = matrix[row][row] * other.get(row, col);
                    }
                }
                for (int stage = 1; stage < other.getHeight(); stage++) {
                    for (int row = from; row < to; row++) {
                        for (int col = 0; col < width; col++) {
                            int k = (row + stage) % other.getHeight();
                            result[row][col] += matrix[row][k] * other.get(k, col);
                        }
                    }
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Matrix(result);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Matrix)) return false;
        var other = (Matrix)obj;
        return Arrays.deepEquals(this.matrix, other.matrix);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                result.append("[").append(matrix[row][col]).append("]");
            }
            result.append('\n');
        }
        return result.toString();
    }
}
