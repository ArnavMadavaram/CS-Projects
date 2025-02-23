package rectangraphic;

/*
 * Name:Arnav Madavaram
 * Username:madaa01
 */

public class Rectangle {
        private int rows;
        private int cols;
        private boolean filled;

        public Rectangle(int rows, int cols, boolean filled) {
            this.rows = rows;
            this.cols = cols;
            this.filled = filled;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (filled) {
                        sb.append('#');
                    } else {
                        if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
                            sb.append('#');
                        } else {
                            sb.append(' ');
                        }
                    }
                }
                sb.append('\n');
            }
            return sb.toString();
        }
    }


