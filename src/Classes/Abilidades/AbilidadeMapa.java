/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Abilidades;

import Classes.EntidadeEvento.EntidadeEvento;
import Classes.EntidadeSimples.Entidade;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Manolo
 */
public abstract class AbilidadeMapa {

    public Rectangle2D.Double pos;
    //0-baixo,1-esquerda,2-cima,3-direita
    public int direcao;
    public boolean active;
    //Abilidade se propaga nao sendo destruida ao colidir com outras entidades
    public boolean abilidadeMapaConstante = false;
    public ArrayList<Entidade> colisoes;
    //Abilidade nao e destruida ao colidir com outras entidades,sendo usado
    //para representar mensagens,emotions,etc
    public boolean abilidadeMapaAtiva = false;
    
    public EntidadeEvento donoAbilidade;
    public int dano;
    
    public AbilidadeMapa(){
        colisoes = new ArrayList<>();
    }

    public abstract void init();

    public abstract void update(int currentTick);

    public abstract void render(Graphics2D g);
    
    public abstract void terminar(Entidade e);

}
