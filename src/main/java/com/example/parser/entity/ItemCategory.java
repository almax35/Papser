package com.example.parser.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class ItemCategory {
    private final ArrayList<String> categoryWeapon;
    private final ArrayList<String> categoryType;

    public ItemCategory() {
        categoryWeapon=new ArrayList<>();
        categoryType=new ArrayList<>();
        Collections.addAll(categoryWeapon, "weapon_ak47",
                "weapon_m4a1_silencer",
                "weapon_aug",
                "weapon_famas",
                "weapon_ssg08",
                "weapon_g3sg1",
                "weapon_awp",
                "weapon_m4a1",
                "weapon_sg556",
                "weapon_galilar",
                "weapon_scar20",
                "weapon_deagle",
                "weapon_glock",
                "weapon_p250",
                "weapon_revolver",
                "weapon_elite",
                "weapon_zeus",
                "weapon_usp_silencer",
                "weapon_hkp2000",
                "weapon_fiveseven",
                "weapon_tec9",
                "weapon_cz75a",
                "weapon_mp9",
                "weapon_ump45",
                "weapon_mp7",
                "weapon_mp5sd",
                "weapon_mac10",
                "weapon_p90",
                "weapon_bizon",
                "weapon_xm1014",
                "weapon_mag7",
                "weapon_sawedoff",
                "weapon_nova",
                "weapon_m249",
                "weapon_negev",
                "csgo_type_weaponcase",
                "csgo_type_ticket",
                "csgo_tool_patch",
                "csgo_type_musickit",
                "csgo_type_spray");
        Collections.addAll(categoryType, "knife", "hands", "sticker", "type_customplayer");
    }

    public ArrayList<String> getCategoryWeapon() {
        return categoryWeapon;
    }

    public ArrayList<String> getCategoryType() {
        return categoryType;
    }
}
