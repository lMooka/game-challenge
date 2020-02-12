package com.mooka.gamechallenge.logic;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CellContainer {
    private static Random random = new Random(123);

    public enum Direction {
        up,
        right,
        left,
        down
    }

    private int generation;
    private Cell cell;
    private final CellContainer father;
    private final List<CellContainer> children;

    private int x, y;
    private final Direction direction;

    private Color color;

    public CellContainer() {
        father = null;
        children = new ArrayList<>();
        x = y = 0;
        direction = Direction.right;
        color = Color.GREEN;
    }

    private CellContainer(CellContainer father, Direction direction) {
        this.generation = father.generation + 1;
        this.father = father;
        this.children = new ArrayList<>();
        this.color = Color.getHSBColor(random.nextFloat(), random.nextFloat(), random.nextFloat());

        if (direction == Direction.right)
            x = father.x + 1;
        else if (direction == Direction.left)
            x = father.x - 1;
        else if (direction == Direction.up)
            y = father.y + 1;
        else if (direction == Direction.down)
            y = father.y - 1;

        this.direction = direction;
    }

    public CellContainer procreate(Direction direction) throws Exception {
        CellContainer child = new CellContainer(this, direction);

        if(children.contains(child))
            throw new Exception("Only one CellContainer by direction is permitted. Direction=" + direction.name());

        children.add(child);

        return child;
    }

    public List<CellContainer> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public CellContainer getFather() {
        return father;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isValidDirection(Direction direction) {
        if(this.direction == Direction.right && direction == Direction.left)
            return false;

        if(this.direction == Direction.left && direction == Direction.right)
            return false;

        if(this.direction == Direction.up && direction == Direction.down)
            return false;

        if(this.direction == Direction.down && direction == Direction.up)
            return false;

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellContainer that = (CellContainer) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }

    public Color getColor() {
        return color;
    }

    public int getGeneration() {
        return generation;
    }
}
