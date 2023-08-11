package me.carina.rpg.common;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public abstract class Display extends Group {
    transient Context context;
    transient boolean populated = false;
    @Override
    public void draw(Batch batch, float parentAlpha) {
        addContext();
        if (!populated) populateChild();
        tick();
        setSize(getDisplayWidth(), getDisplayHeight());
        setPosition(getDisplayX(), getDisplayY(), getAlignment());
        super.draw(batch, parentAlpha);
    }
    public abstract void tick();
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
                    if (ClassReflection.isInstance(Feature.class,o)) {
                        Feature feature = (Feature) o;
                        if (feature.getDisplay() == null) {
                            addActor(feature.generateDisplay());
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

    public int getAlignment() {
        return Align.bottomLeft;
    }

    public abstract boolean fillChildren();

    public boolean isPopulated() {
        return populated;
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        super.setPosition(x, y, alignment);
    }
}
