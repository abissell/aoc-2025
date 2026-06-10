void main() {
    List<String> fileLines;
    try {
        fileLines = Files.readAllLines(Paths.get("Day3-input.txt"));
    } catch (IOException e) {
        IO.println(e.getMessage());
        return;
    }

    int joltage = 0;
    for (String line : fileLines) {
        int leftJoltage = 0;
        int leftJoltageIdx = 0;
        for (int i = 0; i < line.length() - 1; i++) {
            int thisJoltage = Integer.parseInt("" + line.charAt(i));
            if (thisJoltage > leftJoltage) {
                leftJoltage = thisJoltage;
                leftJoltageIdx = i;
            }
        }

        int rightJoltage = 0;
        for (int i = leftJoltageIdx + 1; i < line.length(); i++) {
            int thisJoltage = Integer.parseInt("" + line.charAt(i));
            if (thisJoltage > rightJoltage) {
                rightJoltage = thisJoltage;
            }
        }

        joltage += (leftJoltage * 10 + rightJoltage);
    }

    IO.println(joltage);
}
