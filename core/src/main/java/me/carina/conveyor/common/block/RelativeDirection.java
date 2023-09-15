package me.carina.conveyor.common.block;

public enum RelativeDirection {
    front(0,0,1), back(0,0,-1), up(0,1,0), down(0,-1,0), left(1,0,0), right(-1,0,0);
    final int x, y, z;
    RelativeDirection(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Direction relative(Direction direction){
        //Screw linear algebra
        if (z != 0){
            if (direction.x != 0) return Direction.getByValue(direction.x*z,0,0);
            if (direction.y != 0) return Direction.getByValue(0,direction.y*z,0);
            if (direction.z != 0) return Direction.getByValue(0,0,direction.z*z);
        }
        if (y != 0){
            return Direction.getByValue(0,y,0);
        }
        if (x != 0){
            if (direction.x != 0) return Direction.getByValue(0,0,direction.x * x);
            if (direction.z != 0) return Direction.getByValue(-direction.z * x,0,0);
        }
        return null;
    }
}
