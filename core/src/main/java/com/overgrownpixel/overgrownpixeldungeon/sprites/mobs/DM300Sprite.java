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

package com.overgrownpixel.overgrownpixeldungeon.sprites.mobs;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.watabou.noosa.TextureFilm;

public class DM300Sprite extends MobSprite {
	
	public DM300Sprite() {
		super();
		
		texture( Assets.DM300 );
		
		TextureFilm frames = new TextureFilm( texture, 22, 20 );
		
		idle = new Animation( 10, true );
		idle.frames( frames, 0, 1 );
		
		run = new Animation( 10, true );
		run.frames( frames, 2, 3 );
		
		attack = new Animation( 15, false );
		attack.frames( frames, 4, 5, 6 );
		
		die = new Animation( 20, false );
		die.frames( frames, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 8 );
		
		play( idle );
	}
	
	@Override
	public void onComplete( Animation anim ) {
		
		super.onComplete( anim );
		
		if (anim == die) {
			emitter().burst( Speck.factory( Speck.WOOL ), 15 );
		}
	}
	
	@Override
	public int blood() {
		return 0xFFFFFF88;
	}
}