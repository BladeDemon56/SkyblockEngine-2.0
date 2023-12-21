package in.godspunky.skyblock.item.entity;

import in.godspunky.skyblock.item.GenericItemType;
import in.godspunky.skyblock.item.MaterialFunction;
import in.godspunky.skyblock.item.Rarity;
import in.godspunky.skyblock.item.SkullStatistics;

public class BS6 implements SkullStatistics, MaterialFunction {
    @Override
    public String getURL() {
        return "f052be1c06a4a325129d6f41bb84f0ea1ca6f9f69ebdfff4316e742451c79c21";
    }

    @Override
    public String getDisplayName() {
        return "BZB6";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EXCLUSIVE;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ARMOR;
    }
}