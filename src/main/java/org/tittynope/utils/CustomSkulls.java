package org.tittynope.utils;

import org.bukkit.inventory.ItemStack;

public enum CustomSkulls {
    GREEN_CHECK ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDMxMmNhNDYzMmRlZjVmZmFmMmViMGQ5ZDdjYzdiNTVhNTBjNGUzOTIwZDkwMzcyYWFiMTQwNzgxZjVkZmJjNCJ9fX0="),
    RED_CROSS ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmViNTg4YjIxYTZmOThhZDFmZjRlMDg1YzU1MmRjYjA1MGVmYzljYWI0MjdmNDYwNDhmMThmYzgwMzQ3NWY3In19fQ=="),
    TRASH_RED ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGE2M2IwZjU2ZjdlYzY0ZWFjYmI3MWZjYTMxNTQ5ZDAyMjc0MGQ5YjdkNGI2MTc2MmEyZWZlNTg0MWE0YmYyNSJ9fX0="),
    ARROW_LEFT_BLACK ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdhZWU5YTc1YmYwZGY3ODk3MTgzMDE1Y2NhMGIyYTdkNzU1YzYzMzg4ZmYwMTc1MmQ1ZjQ0MTlmYzY0NSJ9fX0="),
    BLUE_RIGHT ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjNmYWJjMzg5MWM5NjJjOTEzOTU5ZDY1YmU5MGRhYTVkZTEyMTFmYTQwODdkYWUzODZmZjRlZDQyNDhmIn19fQ=="),
    BLUE_LEFT ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTE0NTljZmM0NGNjNTFkZGYyNzk4ODU5NmQyZGU4YWM4NTU2ZTkzZDc5NDYyMTljZjY0YzkwYzhjMDVmY2EifX19"),
    BUILDER ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGNiN2ExNWVkYTFjYmU0N2E4ZDVkN2Y3ODBlODliYmMzNWUwYzE3N2ZjYjljNjQ4MGExMWIwMmNjODE2NWMxYyJ9fX0="),
    YELLOW_CHECK ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWVmNDI1YjRkYjdkNjJiMjAwZTg5YzAxM2U0MjFhOWUxMTBiZmIyN2YyZDhiOWY1ODg0ZDEwMTA0ZDAwZjRmNCJ9fX0="),
    CLOCK ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzZjMGQwNDU5YzY3ZjJiZTgyZmJlNTQwNmFiOGQxMzc4ZGExZjI4ZjdhM2Y4MmYxZGZjNDc2MTVhOTU1YTcifX19"),
    CLOUDS ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjEyNzE0ZDliYzg2NWMyNGU3MWFmMjA0ZDA1ZTI1Njc5MDIwNzVhZmU2ZGI5ZmZiZDkzMmYyODc2NmQ1ZGUifX19"),
    FLAG ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDhmZDcxMjZjZDY3MGM3OTcxYTI4NTczNGVkZmRkODAyNTcyYTcyYTNmMDVlYTQxY2NkYTQ5NDNiYTM3MzQ3MSJ9fX0="),
    RED_O ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmFlY2VkZjViOGIwYWMzZjU2MTkyZmNjYjBhZGU0OGRjNWEwNDNlN2UwZWVhMmJjYzVmNTRhZDAyODFmNjdjOSJ9fX0=");

    private final String value;

    CustomSkulls(String value) {
        this.value = value;
    }

    public ItemStack getSkull(){
        return SkullBuilder.create(this.value);
    }
}
