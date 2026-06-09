void main() {
    final var position = new Position();
    try (Stream<String> lines = Files.lines(Paths.get("Day1-input.txt"))) {
        lines.forEach(line -> {
            String direction = line.substring(0, 1);
            String distance = line.substring(1);
            rotate(position, direction.charAt(0), Integer.parseInt(distance));
        });
    } catch (IOException e) {
        IO.println(e.getMessage());
        System.exit(1);
    }

    IO.println("zeroCount: " + position.zeroCount);
}

class Position {
    private int pos = 50;
    private int zeroCount = 0;

    private Position() {}

    int get() {
        return pos;
    }

    void set(int pos) {
        this.pos = pos;
        if (pos == 0) {
            zeroCount++;
        }
    }
}

void rotate(Position position, char direction, int distance) {
    int curPos = position.get();

    int newPos = curPos + distance * switch (direction) {
        case 'L' -> -1;
        case 'R' -> 1;
        default -> throw new IllegalArgumentException("bad direction: "+ direction);
    };

    newPos = newPos % 100;
    if (newPos < 0) {
        newPos = 100 + newPos;
    }

    position.set(newPos);
}