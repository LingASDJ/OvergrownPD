/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Overgrown Pixel Dungeon
 * Copyright (C) 2016-2019 Anon
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

package com.overgrownpixel.overgrownpixeldungeon.plants;

import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.actors.Actor;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.actors.blobs.Blob;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Buff;
import com.overgrownpixel.overgrownpixeldungeon.actors.buffs.Slippery;
import com.overgrownpixel.overgrownpixeldungeon.actors.mobs.Bananaspider;
import com.overgrownpixel.overgrownpixeldungeon.effects.Pushing;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.poisonparticles.BananabeanPoisonParticle;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSpriteSheet;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.particles.PixelParticle;
import com.watabou.noosa.tweeners.AlphaTweener;

public class Bananabean extends Plant {

	{
		image = 57;
	}

    @Override
    public void attackProc(Char enemy, int damage) {
        Buff.prolong(enemy, Slippery.class, Slippery.DURATION);
    }

    @Override
    public void activate(Char ch) {
        Buff.prolong(ch, Slippery.class, Slippery.DURATION);
    }

    @Override
    public void activate() {
        Bananaspider bananaspider = new Bananaspider();
        bananaspider.pos = pos;
        bananaspider.spawn(Dungeon.depth);
        GameScene.add( bananaspider );
        Actor.addDelayed( new Pushing( bananaspider, pos, pos ), -1f );

        bananaspider.sprite.alpha( 0 );
        bananaspider.sprite.parent.add( new AlphaTweener( bananaspider.sprite, 1, 0.15f ) );
    }

    @Override
    public Blob immunity() {
        return null;
    }

    public static class Seed extends Plant.Seed{

		{
			image = ItemSpriteSheet.SEED_BANANABEAN;

			plantClass = Bananabean.class;
		}

        @Override
        public void procEffect(Char attacker, Char defender, int damage) {

        }

        @Override
        public Emitter.Factory getPixelParticle() {
            return BananabeanPoisonParticle.FACTORY;
        }

        @Override
        public PixelParticle poisonEmitterClass() {
            return new BananabeanPoisonParticle();
        }

        @Override
		public int price() {
			return 30 * quantity;
		}
	}
}