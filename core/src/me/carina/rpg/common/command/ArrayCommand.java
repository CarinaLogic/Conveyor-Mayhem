package me.carina.rpg.common.command;

@SuppressWarnings("WrapperTypeMayBePrimitive")
public class ArrayCommand extends Command{
    @Override
    public boolean enabled() {
        return true;
    }
    @CommandFunction
    public Double[] range(Double i){
        Double[] a = new Double[i.intValue()];
        for (Double j = 0.0; j < i; j++) {
            a[j.intValue()] = j;
        }
        return a;
    }
    @CommandFunction
    public Double[] range(Double begin, Double end){
        Double[] a = new Double[(int) (end-begin)];
        for (Double i = begin; i < end; i++) {
            a[(int) (i-begin)] = i;
        }
        return a;
    }
    @CommandFunction
    public Double[] range(Double begin, Double end, Double increment){
        Double[] a = new Double[(int) ((end-begin)/increment)];
        for (Double i = begin; i < end; i += increment) {
            a[(int) (i - begin)] = i;
        }
        return a;
    }
}
