package in.godspunky.skyblock.nms.nmsutil.reflection.minecraft;

import jdk.internal.reflect.ConstructorAccessor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import in.godspunky.skyblock.nms.nmsutil.reflection.resolver.ConstructorResolver;
import in.godspunky.skyblock.nms.nmsutil.reflection.resolver.FieldResolver;
import in.godspunky.skyblock.nms.nmsutil.reflection.resolver.MethodResolver;
import in.godspunky.skyblock.nms.nmsutil.reflection.resolver.minecraft.NMSClassResolver;
import in.godspunky.skyblock.nms.nmsutil.reflection.resolver.minecraft.OBCClassResolver;
import in.godspunky.skyblock.nms.nmsutil.reflection.util.AccessUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Minecraft {
    static final Pattern NUMERIC_VERSION_PATTERN;
    public static final Version VERSION;
    private static NMSClassResolver nmsClassResolver;
    private static OBCClassResolver obcClassResolver;
    private static Class<?> NmsEntity;
    private static Class<?> CraftEntity;

    public static String getVersion() {
        return Minecraft.VERSION.name() + ".";
    }

    public static Object getHandle(final Object object) throws ReflectiveOperationException {
        Method method;
        try {
            method = AccessUtil.setAccessible(object.getClass().getDeclaredMethod("getHandle", new Class[0]));
        } catch (final ReflectiveOperationException e) {
            method = AccessUtil.setAccessible(Minecraft.CraftEntity.getDeclaredMethod("getHandle", new Class[0]));
        }
        return method.invoke(object);
    }

    public static Entity getBukkitEntity(final Object object) throws ReflectiveOperationException {
        Method method;
        try {
            method = AccessUtil.setAccessible(Minecraft.NmsEntity.getDeclaredMethod("getBukkitEntity", new Class[0]));
        } catch (final ReflectiveOperationException e) {
            method = AccessUtil.setAccessible(Minecraft.CraftEntity.getDeclaredMethod("getHandle", new Class[0]));
        }
        return (Entity) method.invoke(object);
    }

    public static Object getHandleSilent(final Object object) {
        try {
            return getHandle(object);
        } catch (final Exception e) {
            return null;
        }
    }

    public static Object newEnumInstance(final Class clazz, final Class[] types, final Object[] values) throws ReflectiveOperationException {
        final Constructor constructor = new ConstructorResolver(clazz).resolve(new Class[][]{types});
        final Field accessorField = new FieldResolver(Constructor.class).resolve("constructorAccessor");
        ConstructorAccessor constructorAccessor = (ConstructorAccessor) accessorField.get(constructor);
        if (constructorAccessor == null) {
            new MethodResolver(Constructor.class).resolve("acquireConstructorAccessor").invoke(constructor);
            constructorAccessor = (ConstructorAccessor) accessorField.get(constructor);
        }
        return constructorAccessor.newInstance(values);
    }

    static {
        NUMERIC_VERSION_PATTERN = Pattern.compile("v([0-9])_([0-9]*)_R([0-9])");
        Minecraft.nmsClassResolver = new NMSClassResolver();
        Minecraft.obcClassResolver = new OBCClassResolver();
        VERSION = Version.getVersion();
        System.out.println("[SkySim Reflection Injector] Version is " + Minecraft.VERSION);
        try {
            Minecraft.NmsEntity = Minecraft.nmsClassResolver.resolve("Entity");
            Minecraft.CraftEntity = Minecraft.obcClassResolver.resolve("entity.CraftEntity");
        } catch (final ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public enum Version {
        UNKNOWN(-1) {
            @Override
            public boolean matchesPackageName(final String packageName) {
                return false;
            }
        },
        v1_7_R1(10701),
        v1_7_R2(10702),
        v1_7_R3(10703),
        v1_7_R4(10704),
        v1_8_R1(10801),
        v1_8_R2(10802),
        v1_8_R3(10803),
        v1_8_R4(10804),
        v1_9_R1(10901),
        v1_9_R2(10902),
        v1_10_R1(11001),
        v1_11_R1(11101),
        v1_12_R1(11201);

        private final int version;

        Version(final int version) {
            this.version = version;
        }

        public int version() {
            return this.version;
        }

        public boolean olderThan(final Version version) {
            return this.version() < version.version();
        }

        public boolean newerThan(final Version version) {
            return this.version() >= version.version();
        }

        public boolean inRange(final Version oldVersion, final Version newVersion) {
            return this.newerThan(oldVersion) && this.olderThan(newVersion);
        }

        public boolean matchesPackageName(final String packageName) {
            return packageName.toLowerCase().contains(this.name().toLowerCase());
        }

        public static Version getVersion() {
            final String name = Bukkit.getServer().getClass().getPackage().getName();
            final String versionPackage = name.substring(name.lastIndexOf(46) + 1) + ".";
            for (final Version version : values()) {
                if (version.matchesPackageName(versionPackage)) {
                    return version;
                }
            }
            System.err.println("[SkySim Reflection Injector] Failed to find version enum for '" + name + "'/'" + versionPackage + "'");
            System.out.println("[SkySim Reflection Injector] Generating dynamic constant...");
            final Matcher matcher = Minecraft.NUMERIC_VERSION_PATTERN.matcher(versionPackage);
            while (matcher.find()) {
                if (matcher.groupCount() < 3) {
                    continue;
                }
                final String majorString = matcher.group(1);
                String minorString = matcher.group(2);
                if (minorString.length() == 1) {
                    minorString = "0" + minorString;
                }
                String patchString = matcher.group(3);
                if (patchString.length() == 1) {
                    patchString = "0" + patchString;
                }
                final String numVersionString = majorString + minorString + patchString;
                final int numVersion = Integer.parseInt(numVersionString);
                final String packge = versionPackage.substring(0, versionPackage.length() - 1);
                try {
                    final Field valuesField = new FieldResolver(Version.class).resolve("$VALUES");
                    final Version[] oldValues = (Version[]) valuesField.get(null);
                    final Version[] newValues = new Version[oldValues.length + 1];
                    System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
                    final Version dynamicVersion = (Version) Minecraft.newEnumInstance(Version.class, new Class[]{String.class, Integer.TYPE, Integer.TYPE}, new Object[]{packge, newValues.length - 1, numVersion});
                    newValues[newValues.length - 1] = dynamicVersion;
                    valuesField.set(null, newValues);
                    System.out.println("[SkySim Reflection Injector] Injected dynamic version " + packge + " (#" + numVersion + ").");
                    System.out.println("[SkySim Reflection Injector] Please inform inventivetalent about the outdated version, as this is not guaranteed to work.");
                    return dynamicVersion;
                } catch (final ReflectiveOperationException e) {
                    e.printStackTrace();
                    continue;
                }
            }
            return Version.UNKNOWN;
        }

        @Override
        public String toString() {
            return this.name() + " (" + this.version() + ")";
        }
    }
}