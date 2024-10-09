package cleancode.minesweeper.tobe.minesweeper.board.cell;

import java.util.Objects;

// VO
public class CellSnapshot {

    private final CellSnapshotStatus status;
    private final int nearbyLandMineCount;

    public CellSnapshot(CellSnapshotStatus status, int nearbyLandMineCount) {
        this.status = status;
        this.nearbyLandMineCount = nearbyLandMineCount;
    }

    public static CellSnapshot of(CellSnapshotStatus status, int nearbyLandMineCount) {
        return new CellSnapshot(status, nearbyLandMineCount);
    }

    public static CellSnapshot ofEmpty() {
        return CellSnapshot.of(CellSnapshotStatus.EMPTY, 0);
    }

    public static CellSnapshot ofFlag(){
        return CellSnapshot.of(CellSnapshotStatus.FLAG, 0);
    }

    public static CellSnapshot ofLandMine(){
        return CellSnapshot.of(CellSnapshotStatus.LAND_MINE, 0);
    }

    public static CellSnapshot ofNumber(int nearbyLandMineCount){
        return CellSnapshot.of(CellSnapshotStatus.NUMBER, nearbyLandMineCount);
    }

    public static CellSnapshot ofUnchecked(){
        return CellSnapshot.of(CellSnapshotStatus.UNCHECKED, 0);
    }

    public int getNearbyLandMineCount() {
        return nearbyLandMineCount;
    }

    public CellSnapshotStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellSnapshot that = (CellSnapshot) o;
        return nearbyLandMineCount == that.nearbyLandMineCount && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, nearbyLandMineCount);
    }

    public boolean isSameStatus(CellSnapshotStatus cellSnapshotStatus) {
        return this.status == cellSnapshotStatus;
    }
}
