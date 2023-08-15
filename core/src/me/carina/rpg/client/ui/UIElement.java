package me.carina.rpg.client.ui;

public interface UIElement {
    UIElement pos(float x, float y);
    UIElement align(int align);
    UIElement size(float width, float height);
}
