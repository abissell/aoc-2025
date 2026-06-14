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
    var array = new char[rows][columns];

    for (int i = 0; i < rows; i++) {
        char[] lineChars = fileLines.get(i).toCharArray();
        for (int j = 0; j < lineChars.length; j++) {
            array[i][j] = lineChars[j];
        }
    }

    int initialTachyonIdx = fileLines.getFirst().indexOf('S');
    array[1][initialTachyonIdx] = '|';
    int splits = 0;
    for (int i = 1; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
            switch (array[i][j]) {
                case '|' -> {}
                case '.' -> {
                    if (array[i-1][j] == '|') {
                        array[i][j] = '|';
                    }
                }
                case '^' -> {
                    if (array[i-1][j] == '|') {
                        array[i][j-1] = '|';
                        array[i][j+1] = '|';
                        splits++;
                    }
                }
            }
        }
    }

    IO.println(splits);
}

