void main() {
    List<String> fileLines;
    try {
        fileLines = Files.readAllLines(Paths.get("Day6-input.txt"));
    } catch (IOException e) {
        IO.println(e.getMessage());
        return;
    }

    List<List<Long>> numbers = new ArrayList<>();
    for (int i = 0; i < fileLines.size() - 1; i++) {
        List<Long> thisLine = new ArrayList<>();
        String line = fileLines.get(i);
        String[] thisLineNumbers = line.stripLeading().split("\\s+");
        for (String num : thisLineNumbers) {
            thisLine.add(Long.parseLong(num));
        }
        numbers.add(thisLine);
    }

    String[] opsStrs = fileLines.getLast().split("\\s+");
    long sum = 0L;
    int i = 0;
    for (String operation : opsStrs) {
        List<Long> columnVals = new ArrayList<>();
        for (List<Long> row : numbers) {
            columnVals.add(row.get(i));
        }
        sum += switch (operation) {
            case "+" -> Operation.ADD.apply(columnVals);
            case "*" -> Operation.MUL.apply(columnVals);
            default -> throw new IllegalArgumentException();
        };
        i++;
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

