void main() {
    List<String> fileLines;
    try {
        fileLines = Files.readAllLines(Paths.get("Day5-input.txt"));
    } catch (IOException e) {
        IO.println(e.getMessage());
        return;
    }

    var ranges = new ArrayList<Range>();
    for (String line : fileLines) {
        if ("".equals(line)) {
            break;
        }

        String[] parts = line.split("-");
        ranges.add(new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1])));
    }

    ranges.sort(Comparator.comparing(Range::lower));

    long freshIds = 0L;
    rangeloop: for (int rangeIdx = 0; rangeIdx < ranges.size();) {
        Range thisRange = ranges.get(rangeIdx);
        if (rangeIdx == ranges.size() - 1) {
            long toAdd = thisRange.upper - thisRange.lower + 1;
            freshIds += toAdd;
            // IO.println("Added " + toAdd + " from " + thisRange);
            break;
        }

        int nextRangeIdx = rangeIdx + 1;
        Range nextRange = ranges.get(nextRangeIdx);
        while (nextRange.upper <= thisRange.upper) {
            if (nextRangeIdx == ranges.size() - 1) {
                long toAdd = thisRange.upper - thisRange.lower + 1;
                freshIds += toAdd;
                // IO.println("Added " + toAdd + " from " + thisRange);
                break rangeloop;
            }
            nextRangeIdx++;
            nextRange = ranges.get(nextRangeIdx);
        }

        if (thisRange.upper >= nextRange.upper) {
            throw new IllegalStateException();
        } else if (thisRange.upper > nextRange.lower) {
            long toAdd = nextRange.lower - thisRange.lower;
            freshIds += toAdd;
            // IO.println("Added " + toAdd + " from nextRange.lower=" + nextRange.lower + ", thisRange.lower=" + thisRange.lower);
        } else {
            long toAdd = thisRange.upper - thisRange.lower;
            if (nextRange.lower > thisRange.upper) {
                toAdd++; // Add extra 1 when range bounds do not overlap
            }
            freshIds += toAdd;
            // IO.println("Added " + toAdd + " from " + thisRange);
        }

        rangeIdx = nextRangeIdx;
    }

    IO.println(freshIds);
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