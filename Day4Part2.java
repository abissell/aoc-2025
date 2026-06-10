void main() {
    List<String> fileLines;
    try {
        fileLines = Files.readAllLines(Paths.get("Day4-input.txt"));
    } catch (IOException e) {
        IO.println(e.getMessage());
        return;
    }

    int lineLength = fileLines.getFirst().length();
    int numLines = fileLines.size();
    var grid = new boolean[lineLength][numLines];

    int x = 0, y = 0;
    for (String line : fileLines) {
        for (char c : line.toCharArray()) {
            boolean roll = switch (c) {
                case '@' -> true;
                case '.' -> false;
                default -> throw new IllegalArgumentException();
            };
            grid[x++][y] = roll;
        }

        x = 0;
        ++y;
    }

    int totalRemoved = 0;
    int lastRemoved = 0;
    do {
        lastRemoved = removeReachables(grid, lineLength, numLines);
        totalRemoved += lastRemoved;
    } while (lastRemoved > 0);

    IO.println(totalRemoved);
}

int removeReachables(boolean[][] grid, int lineLength, int numLines) {
    int reachable = 0;
    for (int x = 0; x < lineLength; x++) {
        for (int y = 0; y < numLines; y++) {
            if (reachableRoll(x, y, grid, lineLength - 1, numLines - 1)) {
                reachable++;
                grid[x][y] = false;
            }
        }
    }
    return reachable;
}

boolean reachableRoll(int x, int y, boolean[][] grid, int xMax, int yMax) {
    if (grid[x][y] == false) {
        return false;
    }

    int surroundingRolls = 0;
    for (int i = x - 1; i <= x + 1; i++) {
        for (int j = y - 1; j <= y + 1; j++) {
            if ((i == x && j == y) || i < 0 || j < 0 || i > xMax || j > yMax) {
                continue;
            }

            if (grid[i][j]) {
                surroundingRolls++;
            }
        }
    }

    return surroundingRolls < 4;
}