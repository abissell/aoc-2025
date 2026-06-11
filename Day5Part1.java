void main() {
    List<String> fileLines;
    try {
        fileLines = Files.readAllLines(Paths.get("Day5-input.txt"));
    } catch (IOException e) {
        IO.println(e.getMessage());
        return;
    }

    var ranges = new ArrayList<Range>();
    var ingredients = new ArrayList<Long>();
    boolean parseRanges = true;
    for (String line : fileLines) {
        if ("".equals(line)) {
            parseRanges = false;
            continue;
        }

        if (parseRanges) {
            String[] parts = line.split("-");
            ranges.add(new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
        } else {
            ingredients.add(Long.parseLong(line));
        }
    }

    ranges.sort(Comparator.comparing(Range::lower));

    int fresh = 0;
    for (long ingredient : ingredients) {
        if (isInRange(ingredient, ranges)) {
            fresh++;
        }
    }

    IO.println(fresh);
}

record Range(long lower, long upper) {}

boolean isInRange(long ingredient, List<Range> sortedRanges) {
    for (Range range : sortedRanges) {
        if (range.lower > ingredient) {
            return false;
        } else if (range.upper >= ingredient) {
            return true;
        }
    }

    return false;
}