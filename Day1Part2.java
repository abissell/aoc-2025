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
    int pos = 50;
    int zeroCount = 0;

    private Position() {}
}

void rotate(Position position, char direction, int distance) {
    int pos = position.pos;

    int inc = switch (direction) {
        case 'L' -> -1;
        case 'R' -> 1;
        default -> throw new IllegalArgumentException("bad direction: "+ direction);
    };

    for (int i = 0; i < distance; i++) {
        pos = pos + inc;
        if (pos == 0) {
            position.zeroCount++;
        } else if (pos == -1) {
            pos = 99;
        } else if (pos == 100) {
            position.zeroCount++;
            pos = 0;
        }
    }

    position.pos = pos;
}