package me.carina.rpg.client.actions;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Null;

public class Actions extends com.badlogic.gdx.scenes.scene2d.actions.Actions {
    public static UISizeToAction uiSizeTo (float x, float y) {
        return uiSizeTo(x, y, 0, null);
    }

    public static UISizeToAction uiSizeTo (float x, float y, float duration) {
        return uiSizeTo(x, y, duration, null);
    }

    public static UISizeToAction uiSizeTo (float x, float y, float duration, @Null Interpolation interpolation) {
        UISizeToAction action = action(UISizeToAction.class);
        action.setTargetWidth(x);
        action.setTargetHeight(y);
        action.setDuration(duration);
        action.setInterpolation(interpolation);
        return action;
    }

    public static UISizeByAction uiSizeBy (float amountX, float amountY) {
        return uiSizeBy(amountX, amountY, 0, null);
    }

    public static UISizeByAction uiSizeBy (float amountX, float amountY, float duration) {
        return uiSizeBy(amountX, amountY, duration, null);
    }

    public static UISizeByAction uiSizeBy (float amountX, float amountY, float duration, @Null Interpolation interpolation) {
        UISizeByAction action = action(UISizeByAction.class);
        action.setWidthDelta(amountX);
        action.setHeightDelta(amountY);
        action.setDuration(duration);
        action.setInterpolation(interpolation);
        return action;
    }
}
