void main() {
    List<String> fileLines;
    try {
        fileLines = Files.readAllLines(Paths.get("Day2-input.txt"));
    } catch (IOException e) {
        IO.println(e.getMessage());
        return;
    }

    String fileLine = fileLines.get(0);
    String[] ranges = fileLine.split(",");

    long invalidCount = 0;
    for (String range : ranges) {
        String[] rangeParts = range.split("-");
        long start = Long.parseLong(rangeParts[0]);
        long end = Long.parseLong(rangeParts[1]);
        for (long id = start; id <= end; id++) {
            if (!isValid(id)) {
                invalidCount = invalidCount + id;
            }
        }
    }

    IO.println("invalidCount=" + invalidCount);
}

boolean isValid(long id) {
    String idStr = Long.toString(id);
    int length = idStr.length();

    for (int divisor = 1; divisor <= length / 2; divisor++) {
        if (length % divisor != 0) {
            continue;
        }

        if (repeats(idStr, divisor)) {
            return false;
        }
    }

    return true;
}

boolean repeats(String idStr, int divisor) {
    final String portion = idStr.substring(0, divisor);
    int cursor = divisor;
    while (cursor < idStr.length()) {
        String nextPortion = idStr.substring(cursor, cursor + divisor);
        if (!nextPortion.equals(portion)) {
            return false;
        }
        cursor = cursor + divisor;
    }

    return true;
}