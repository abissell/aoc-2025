void main() {
    List<String> fileLines;
    try {
        fileLines = Files.readAllLines(Paths.get("Day3-input.txt"));
    } catch (IOException e) {
        IO.println(e.getMessage());
        return;
    }

    long totalJoltage = 0L;
    for (String line : fileLines) {
        String joltageStr = "";
        int leftmostIdx = -1;
        for (int startPos = 12; startPos >= 1; startPos--) {
            int largest = 0;
            int largestIdx = line.length();
            for (int i = line.length() - startPos; i > leftmostIdx; i--) {
                int joltage = Integer.parseInt("" + line.charAt(i));
                if (joltage >= largest) {
                    largest = joltage;
                    largestIdx = i;
                }
            }
            leftmostIdx = largestIdx;
            joltageStr += largest;
        }

        totalJoltage += Long.parseLong(joltageStr);
    }

    IO.println(totalJoltage);
}
