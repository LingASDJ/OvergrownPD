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

package com.overgrownpixel.overgrownpixeldungeon.tiles.wallfauna;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.effects.CellEmitter;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.LeafParticle;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

public abstract class WallFauna implements Bundlable {

	public int image;
	public int pos;

	public void wither(){
        Dungeon.level.fauna.remove(pos);
        GameScene.updateMap(pos);

        if (Dungeon.level.heroFOV[pos]) {
            CellEmitter.get( pos ).burst( LeafParticle.GENERAL, 6 );
        }
	}

    public WallFauna set(int pos){
        this.pos = pos;
        return this;
    }

	private static final String POS	= "pos";

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		pos = bundle.getInt( POS );
	}

	@Override
	public void storeInBundle( Bundle bundle ) {
		bundle.put( POS, pos );
	}
}