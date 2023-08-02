package me.carina.rpg.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import me.carina.rpg.common.util.Array2D;
import me.carina.rpg.common.util.FeatureArray;
import me.carina.rpg.common.util.FeatureArray2D;

public abstract class Display extends Group {
    transient Context context;
    transient boolean populated = false;
    @Override
    public void draw(Batch batch, float parentAlpha) {
        addContext();
        if (!populated) populateChild();
        setSize(getDisplayWidth(), getDisplayHeight());
        setPosition(getDisplayX(), getDisplayY(), getAlignment());
        super.draw(batch, parentAlpha);
    }
    public void addContext(){
        if (getParent() != null && getParent() instanceof Display){
            Display p = ((Display) getParent());
            if (p.context != null) {
                this.context = p.context.copy();
                this.context.add(this.getFeature());
            }
            else {
                this.context = new Context();
                this.context.add(this.getFeature());
            }
        }
        else {
            this.context = new Context();
            this.context.add(this.getFeature());
        }
    }
    public void populateChild(){
        Field[] fields = ClassReflection.getDeclaredFields(getFeature().getClass());
        for (Field field : fields) {
            if (field.getDeclaredAnnotation(AutoDisplay.class) != null){
                try {
                    Object o = field.get(getFeature());
                    if (ClassReflection.isInstance(Feature.class,o)){
                        Feature feature = (Feature) o;
                        if (feature.getDisplay() == null){
                            addActor(feature.newDisplay());
                        }
                    }
                    else if (ClassReflection.isInstance(FeatureArray.class,o)){
                        FeatureArray<?> array = (FeatureArray<?>) o;
                        for (Feature feature : array) {
                            if (feature.getDisplay() == null){
                                addActor(feature.newDisplay());
                            }
                        }
                    }
                    else if (ClassReflection.isInstance(FeatureArray2D.class,o)){
                        FeatureArray2D<?> array2D = (FeatureArray2D<?>) o;
                        for (Array2D.Array2DEntry<?> array2DEntry : array2D) {
                            if (ClassReflection.isInstance(Feature.class,array2DEntry.value)){
                                Feature feature = (Feature) array2DEntry.value;
                                if (feature.getDisplay() == null) {
                                    addActor(feature.newDisplay());
                                }
                            }
                        }
                    }
                } catch (ReflectionException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        populated = true;
    }
    @Override
    public void setSize(float width, float height) {
        float w = super.getWidth();
        float h = super.getHeight();
        super.setSize(width,height);
        for (Actor child : getChildren()) {
            if (fillChildren()){
                child.setSize(width, height);
            }
            else{
                float cw = child.getWidth();
                float ch = child.getHeight();
                child.setSize(cw*width/w, ch*height/h);
            }
        }
    }

    public Context getContext() {
        return context;
    }

    public abstract float getDisplayX();
    public abstract float getDisplayY();

    public abstract float getDisplayWidth();
    public abstract float getDisplayHeight();
    public abstract Feature getFeature();

    public abstract int getAlignment();

    public abstract boolean fillChildren();

    @Override
    public void setPosition(float x, float y, int alignment) {
        super.setPosition(x, y, alignment);
    }
}
