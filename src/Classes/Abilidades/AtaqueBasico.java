/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Abilidades;

import Classes.EntidadeEvento.EntidadeEvento;
import Classes.EntidadeEvento.EntidadeMob;
import Classes.EntidadeSimples.Entidade;
import Classes.Game.Jogo;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Seven
 */
public class AtaqueBasico extends AbilidadeMapa {

    public int contador;
    public final int DELAY = 2;

    public AtaqueBasico(int posX, int posY, int direcao, EntidadeEvento e) {
        super();
        pos = new Rectangle2D.Double(posX, posY, 32, 32);
        donoAbilidade = e;
        abilidadeMapaConstante = true;
        this.direcao = direcao;
        contador = 0;
        active = true;
        dano = 10;
        init();
    }

    @Override
    public void init() {
        if (direcao == 0) {
            pos.y = pos.y + 32;
            pos.x = pos.x - 16;
            pos.width = 64;
        } else if (direcao == 1) {
            pos.x = pos.x - 32;
            pos.y = pos.y - 16;
            pos.height = 64;
        } else if (direcao == 2) {
            pos.y = pos.y - 32;
            pos.x = pos.x - 16;
            pos.width = 64;
        } else {
            pos.x = pos.x + 32;
            pos.y = pos.y - 16;
            pos.height = 64;
        }
    }

    @Override
    public void update(int currentTick) {
        if (contador < DELAY) {
            contador++;
        } else {
            active = false;
        }
    }

    @Override
    public void render(Graphics2D g) {
    }

    @Override
    public void terminar(Entidade e) {
        if (donoAbilidade instanceof EntidadeMob) {
            Jogo.abilidadesMob.add(new Explossao1((int) e.pos.x + 6, (int) e.pos.y + 5));
        } else {
            Jogo.abilidadesJogador.add(new Explossao1((int) e.pos.x + 6, (int) e.pos.y + 5));
        }
    }

}
