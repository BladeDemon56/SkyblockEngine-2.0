package in.godspunky.skyblock.entity.end;

import in.godspunky.skyblock.entity.EntityDrop;
import in.godspunky.skyblock.entity.EntityDropType;
import in.godspunky.skyblock.item.SMaterial;

import java.util.Collections;
import java.util.List;

public class VoidlingDevotee extends BaseEnderman {
    @Override
    public String getEntityName() {
        return "Voidling Devotee";
    }

    @Override
    public double getEntityMaxHealth() {
        return 2.5E7;
    }

    @Override
    public double getDamageDealt() {
        return 5000.0;
    }

    @Override
    public double getXPDropped() {
        return 1500.0;
    }

    @Override
    public List<EntityDrop> drops() {
        return Collections.singletonList(new EntityDrop(SMaterial.NULL_SPHERE, EntityDropType.GUARANTEED, 1.0));
    }
}