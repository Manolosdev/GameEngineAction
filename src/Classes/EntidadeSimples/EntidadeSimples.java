/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.EntidadeSimples;

import Classes.Core.ImageManager;
import java.awt.Graphics2D;

/**
 *
 * @author Seven
 */
public class EntidadeSimples extends Entidade{

    public EntidadeSimples(int IDSprite, int posX, int posY) {
        super(IDSprite, posX, posY);
    }

    @Override
    public void init() {
    }

    @Override
    public void update(int currentTick) {
    }

    @Override
    public void render(Graphics2D g) {
        g.drawRect((int)pos.x,(int) pos.y, (int) pos.width, (int) pos.height);
        if (this.IDSprite != 0) {
            g.drawImage(ImageManager.getInstance().getSpriteMapa(this.IDSprite), (int) this.pos.x, (int) this.pos.y, null);
        } else {
            g.drawImage(ImageManager.getInstance().getSpriteMapa(1), (int) this.pos.x, (int) this.pos.y, null);
        }
    }
    
}
