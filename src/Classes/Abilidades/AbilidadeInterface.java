/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Abilidades;

import Classes.EntidadeEvento.EntidadeEvento;
import Classes.EntidadeSimples.Entidade;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Seven
 */
public class AbilidadeInterface extends AbilidadeMapa {

    public int contador;
    public final int DELAY = 50;

    public AbilidadeInterface(int posX, int posY, int direcao, EntidadeEvento e, int dano) {
        pos = new Rectangle2D.Double(posX, posY, 32, 32);
        donoAbilidade = e;
        this.direcao = direcao;
        contador = 0;
        active = true;
        this.dano = dano;
        abilidadeMapaAtiva = true;
    }

    @Override
    public void init() {

    }

    @Override
    public void update(int currentTick) {
        if (contador < DELAY) {
            contador++;
            pos.y -= 0.5;
        } else {
            active = false;
        }
    }

    @Override
    public void render(Graphics2D g) {

        if (contador < 20) {
            g.setComposite(AlphaComposite.SrcOver);
        } else if (contador < 40) {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        } else {
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        if (dano > 0) {
            g.setColor(Color.red);
            g.setFont(new Font("", Font.BOLD, 11));
            g.drawString(dano + "", (int) pos.x + 8, (int) pos.y + 5);
        }
    }

    @Override
    public void terminar(Entidade e) {

    }

}
