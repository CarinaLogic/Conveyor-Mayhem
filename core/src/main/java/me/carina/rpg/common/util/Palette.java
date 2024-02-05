package me.carina.rpg.common.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

public class Palette {
    Array<Color> srcColor = new Array<>();
    Array<Color> targetColor = new Array<>();
    public Palette(Pixmap palette, int targetIndex){
        if (targetIndex == 0) return;
        int x = 0;
        while (true){
            if (x >= palette.getWidth()) break;
            if (palette.getPixel(x,0) == 0) break;
            srcColor.add(new Color(palette.getPixel(x,0)));
            targetColor.add(new Color(palette.getPixel(x,targetIndex)));
            x++;
        }
    }
    public void recolor(Pixmap map){
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Color color = new Color(map.getPixel(x,y));
                int index = srcColor.indexOf(color,false);
                if (index != -1) map.drawPixel(x,y,Color.rgba8888(targetColor.get(index)));
            }
        }
    }
}
