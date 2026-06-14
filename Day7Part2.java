void main() {
    List<String> fileLines;
    try {
        fileLines = Files.readAllLines(Paths.get("Day7-input.txt"));
    } catch (IOException e) {
        IO.println(e.getMessage());
        return;
    }

    final int rows = fileLines.size();
    final int columns = fileLines.getFirst().length();
    var array = new long[rows][columns];

    for (int i = 0; i < rows; i++) {
        char[] lineChars = fileLines.get(i).toCharArray();
        for (int j = 0; j < lineChars.length; j++) {
            array[i][j] = switch (lineChars[j]) {
                case 'S' -> 1L;
                case '.' -> 0L;
                case '^' -> -1L;
                default -> throw new IllegalArgumentException();
            };
        }
    }

    for (int i = 1; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
            if (array[i][j] != -1L) {
                if (array[i-1][j] != -1L) {
                    array[i][j] += array[i-1][j];
                }
            } else if (array[i][j] == -1L) {
                array[i][j-1] += array[i-1][j];
                array[i][j+1] += array[i-1][j];
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    long sum = 0L;
    for (int i = 0; i < columns; i++) {
        if (array[rows-1][i] != -1L)
        sum += array[rows-1][i];
    }

    IO.println(sum);
}