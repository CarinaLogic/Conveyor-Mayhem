package me.carina.rpg.client.battle;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.Game;
import me.carina.rpg.client.misc.UIColor;
import me.carina.rpg.client.ui.UILabel;
import me.carina.rpg.client.ui.UIProgressBar;
import me.carina.rpg.client.ui.UITableView;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.UIUnitDisplay;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.stat.StatType;

import java.util.function.Supplier;

public class UIBattleStatPanel extends Stack implements Display<Unit> {
    UITableView tableView;
    Supplier<Unit> unitSupplier = () -> null;
    UILabel nameLabel;
    UIUnitDisplay unitDisplay;
    public UIBattleStatPanel(){
    }

    public void init() {
        {
            tableView = new UITableView();
            add(tableView);
            {
                nameLabel = new UILabel();
                nameLabel.supplyString(()->unitSupplier.get().getName());
                tableView.add(nameLabel).left().colspan(2);
            }
            tableView.row();
            {
                tableView.add(new UILabel().supplyString(()->"HP")).padRight(1);
                tableView.add(new UIHpBar()
                    .supplyValue(()-> unitSupplier.get().getStats().getStat(StatType.hp).get(unitSupplier.get()))
                    .supplyMax(()-> unitSupplier.get().getStats().getStat(StatType.hp).getMax(unitSupplier.get())))
                    .expandX().fillX();
            }
            tableView.row();
            {
                tableView.add(new UILabel().supplyString(()->"SP")).padRight(1);
                tableView.add(new UISpBar()
                        .supplyValue(()-> unitSupplier.get().getStats().getStat(StatType.sp).get(unitSupplier.get()))
                        .supplyMax(()-> unitSupplier.get().getStats().getStat(StatType.sp).getMax(unitSupplier.get())))
                    .expandX().fillX();
            }
        }
        {
            Table unitContainer = new Table();
            add(unitContainer);
            Table unitTable = new Table();
            unitTable.setClip(true);
            unitContainer.add(unitTable).expand().top().right().size(32, 22);
            unitDisplay = Game.getClient().getDisplays().get(unitSupplier, UIUnitDisplay.class);
            unitTable.add(unitDisplay).top().right();
        }
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        super.act(delta);
    }

    @Override
    public Supplier<Unit> getFeatureSupplier() {
        return unitSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Unit> supplier) {
        this.unitSupplier = supplier;
    }

    public static class UIHpBar extends UIProgressBar{
        public UIHpBar(){
            super();
            supplyColor(()->UIColor.hp);
        }
    }

    public static class UISpBar extends UIProgressBar{
        public UISpBar(){
            super();
            supplyColor(()->UIColor.sp);
        }
    }
}
