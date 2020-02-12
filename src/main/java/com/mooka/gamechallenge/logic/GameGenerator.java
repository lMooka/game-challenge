package com.mooka.gamechallenge.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameGenerator {
    private long seed = 0L;
    private float procreationChance = 0.75f;
    private final float procreationChanceDecay = 0.01f;
    private final int maxCellContainerAmount = 1000;
    private int cellContainerCount = 0;
    private final Random random;
    private boolean hasHead;

    public GameGenerator(long seed) {
        random = new Random(seed);
    }

    public CellContainer generate() throws Exception {
        CellContainer head = new CellContainer();
        List<CellContainer> branchHeads = new ArrayList<CellContainer>();
        List<CellContainer> nextGenBranchHeads = new ArrayList<CellContainer>();
        nextGenBranchHeads.add(head);

        while (cellContainerCount < maxCellContainerAmount && nextGenBranchHeads.size() > 0) {
            branchHeads.clear();
            branchHeads.addAll(nextGenBranchHeads);
            nextGenBranchHeads.clear();

            for (CellContainer branchHead : branchHeads) {
                CellContainer up = procreate(branchHead, CellContainer.Direction.up, procreationChance);
                CellContainer right = procreate(branchHead, CellContainer.Direction.right, procreationChance);
                CellContainer down = procreate(branchHead, CellContainer.Direction.down, procreationChance);
                CellContainer left = procreate(branchHead, CellContainer.Direction.left, procreationChance);

                if (up != null)
                    nextGenBranchHeads.add(up);

                if (right != null)
                    nextGenBranchHeads.add(right);

                if (down != null)
                    nextGenBranchHeads.add(down);

                if (left != null)
                    nextGenBranchHeads.add(left);
            }
        }

        return head;
    }

    private CellContainer procreate(CellContainer branchHead, CellContainer.Direction direction, float successChance) throws Exception {
        if(branchHead == null)
            return null;

        if (/*branchHead.isValidDirection(direction) &&*/ random.nextFloat() <= successChance) {
            //procreationChance -= procreationChanceDecay;
            cellContainerCount++;
            return branchHead.procreate(direction);
        }

        return null;
    }
}
