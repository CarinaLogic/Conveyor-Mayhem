package me.carina.conveyor.client.battle;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.conveyor.Game;
import me.carina.conveyor.client.misc.UIColor;
import me.carina.conveyor.client.ui.UILabel;
import me.carina.conveyor.client.ui.UIProgressBar;
import me.carina.conveyor.client.ui.UITableView;
import me.carina.conveyor.common.Display;
import me.carina.conveyor.common.unit.UIUnitDisplay;
import me.carina.conveyor.common.unit.Unit;
import me.carina.conveyor.common.stat.StatType;

public class UIBattleStatPanel extends Stack implements Display<Unit> {
    UITableView tableView;
    Unit unit;
    UILabel nameLabel;
    UIUnitDisplay unitDisplay;
    UIHpBar hpBar;
    UISpBar spBar;
    public UIBattleStatPanel(Unit unit){
        this.unit = unit;
        {
            tableView = new UITableView();
            add(tableView);
            {
                nameLabel = new UILabel();
                nameLabel.addAction(Actions.forever(Actions.run(() -> nameLabel.text(
                        unit.getName()
                ))));
                tableView.add(nameLabel).left().colspan(2);
            }
            tableView.row();
            {
                tableView.add(new UILabel().text("HP")).padRight(1);
                hpBar = new UIHpBar();
                hpBar.addAction(Actions.forever(Actions.run(() -> hpBar.value(
                        unit.getStats().getStat(StatType.hp).get(unit) * 100 /
                                unit.getStats().getStat(StatType.hp).getMax(unit)))));
                tableView.add(hpBar).expandX().fillX();
            }
            tableView.row();
            {
                tableView.add(new UILabel().text("SP")).padRight(1);
                spBar = new UISpBar();
                spBar.addAction(Actions.forever(Actions.run(() -> spBar.value(
                        unit.getStats().getStat(StatType.sp).get(unit) * 100 /
                                unit.getStats().getStat(StatType.sp).getMax(unit)))));
                tableView.add(spBar).expandX().fillX();
            }
        }
        {
            Table unitContainer = new Table();
            add(unitContainer);
            Table unitTable = new Table();
            unitTable.setClip(true);
            unitContainer.add(unitTable).expand().top().right().size(32, 22);
            unitDisplay = unit.newDisplay(UIUnitDisplay.class);
            unitTable.add(unitDisplay).top().right();
        }
    }

    @Override
    public Unit getFeature() {
        return unit;
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        super.act(delta);
    }

    public static class UIHpBar extends UIProgressBar{
        public UIHpBar(){
            super();
            color(UIColor.hp);
        }
    }

    public static class UISpBar extends UIProgressBar{
        public UISpBar(){
            super();
            color(UIColor.sp);
        }
    }
}