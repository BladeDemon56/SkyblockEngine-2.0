package vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.minecraft;

import vn.giakhanhvn.skysim.nms.nmsutil.reflection.minecraft.Minecraft;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.ClassResolver;

public class NMSClassResolver extends ClassResolver
{
    @Override
    public Class resolve(final String... names) throws ClassNotFoundException {
        for (int i = 0; i < names.length; ++i) {
            if (!names[i].startsWith("net.minecraft.server")) {
                names[i] = "net.minecraft.server." + Minecraft.getVersion() + names[i];
            }
        }
        return super.resolve(names);
    }
}
