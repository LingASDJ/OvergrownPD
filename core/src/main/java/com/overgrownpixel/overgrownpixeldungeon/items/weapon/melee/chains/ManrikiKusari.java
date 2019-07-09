/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2016 Evan Debenham
 *
 * Lovecraft Pixel Dungeon
 * Copyright (C) 2016-2017 Leon Horn
 *
 * Plugin Pixel Dungeon
 * Copyright (C) 2017 Leon Horn
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This Program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without eben the implied warranty of
 * GNU General Public License for more details.
 *
 * You should have have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses>
 */

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.chains;

import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Cripple;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Paralysis;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.melee.MeleeWeapon;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.utils.Random;

public class ManrikiKusari extends MeleeWeapon {

	{
		image = ItemSpriteSheet.MANRIKI_KUSARI;

		tier = 5;
		RCH = 5; //extra reach
	}

	@Override
	public int max(int lvl) {
        return  Math.round(4f*(tier+1)) +    //24 base, up from 30
                lvl*Math.round(0.65f*(tier+1)); //+4 per level, down from +6
	}

    @Override
    public int proc(Char attacker, Char defender, int damage) {
        Buff.prolong(defender, Cripple.class, damage);
        if (Random.Float() < 0.3f){
            Buff.prolong( defender, Paralysis.class, 2f);
        }
        return super.proc(attacker, defender, damage);
    }
}