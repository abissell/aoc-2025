void main() {
    List<String> fileLines;
    try {
        fileLines = Files.readAllLines(Paths.get("Day6-input.txt"));
    } catch (IOException e) {
        IO.println(e.getMessage());
        return;
    }

    String operationsLine = fileLines.getLast();
    char[] operationsLineChars = operationsLine.toCharArray();
    var array = new char[fileLines.size()][operationsLineChars.length];
    final int numberRows = fileLines.size() - 1;

    for (int i = 0; i < fileLines.size(); i++) {
        String line = fileLines.get(i);
        char[] chars = line.toCharArray();
        for (int j = 0; j < chars.length; j++) {
            array[i][j] = chars[j];
        }
    }

    long sum = 0L;
    for (int i = 0; i < operationsLineChars.length;) {
        char operationChar = operationsLineChars[i];

        int nextOperationIdx = i + 1;
        while (' ' == operationsLineChars[nextOperationIdx]) {
            nextOperationIdx++;
            if (nextOperationIdx == operationsLineChars.length) {
                break;
            }
        }

        int columns = nextOperationIdx - i;
        if (nextOperationIdx != operationsLineChars.length) {
            columns -= 1;
        }
        var numbers = new ArrayList<Long>();
        for (int j = 0; j < columns; j++) {
            char[] numberArray = new char[numberRows];
            for (int k = 0; k < numberRows; k++) {
                numberArray[k] = array[k][i + j];
            }

            String numberStr = String.valueOf(numberArray).strip();
            numbers.add(Long.parseLong(numberStr));
        }

        long newVal = switch (operationChar) {
            case '+' -> Operation.ADD.apply(numbers);
            case '*' -> Operation.MUL.apply(numbers);
            default -> throw new IllegalArgumentException();
        };

        sum += newVal;

        i = nextOperationIdx;
    }

    IO.println(sum);
}

enum Operation {
    ADD,
    MUL;

    long apply(List<Long> values) {
        return switch (this) {
            case ADD -> values.stream().mapToLong(Long::longValue).sum();
            case MUL -> values.stream().reduce(1L, (a, b) -> a * b);
        };
    }
}

