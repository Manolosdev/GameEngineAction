/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Game;

import Classes.Abilidades.AbilidadeMapa;
import Classes.Abilidades.AbilidadeInterface;
import Classes.EntidadeEvento.EntidadeEvento;
import Classes.EntidadeEvento.EntidadeJogador;
import Classes.EntidadeFuncionais.DetectorColisao;
import Classes.EntidadeFuncionais.InterfaseJogo;
import Classes.EntidadeSimples.Entidade;
import Classes.EntidadeSimples.EntidadeMedia;
import Classes.EntidadeFuncionais.ControleJogador;
import Rpg.Core.DataManager;
import Rpg.Core.Game;
import Classes.Core.ImageManager;
import Classes.EntidadeEvento.EntidadeMob;
import Classes.EntidadeSimples.EntidadeSimples;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Seven
 */
public class Jogo extends Game {

    public static ArrayList<Entidade> entidades;
    public static ArrayList<AbilidadeMapa> abilidadesMob;
    public static ArrayList<AbilidadeMapa> abilidadesJogador;
    DetectorColisao detectorColisao;
    InterfaseJogo interfase;
    EntidadeJogador jogador;

    Point rolagem;
    Dimension mundo;

    public Jogo() {
        super();
        this.showFPS = true;
        ImageManager.getInstance();
        rolagem = new Point(0, 0);
        mundo = new Dimension();
        Jogo.entidades = new ArrayList<>();
        Jogo.abilidadesMob = new ArrayList<>();
        Jogo.abilidadesJogador = new ArrayList<>();
        this.carregarMapa("level1");

    }

    @Override
    public void onLoad() {
        ControleJogador.getInstance();
        this.jogador = ControleJogador.getInstance().jogador;
        entidades.add(jogador);
        this.detectorColisao = new DetectorColisao();
        interfase = new InterfaseJogo(this);
        interfase.active = true;
        //Arbusto do mapa
        EntidadeMedia e = new EntidadeMedia(6, 400, 200);
        e.blockEntidade = true;
        EntidadeMedia e2 = new EntidadeMedia(7, 400, 168);
        e2.entidadeTopo = true;
        entidades.add(e);
        entidades.add(e2);

    }

    @Override
    public void onUnload() {
        //Salvar progresso do personagem
        ControleJogador.getInstance().salvarProgresso();
    }

    @Override
    public void onUpdate(int currentTick) {
        for (int i = entidades.size() - 1; i >= 0; i--) {
            entidades.get(i).update(currentTick);
        }

        for (int i = abilidadesJogador.size() - 1; i >= 0; i--) {
            abilidadesJogador.get(i).update(currentTick);
            if (abilidadesJogador.get(i).active == false) {
                abilidadesJogador.remove(abilidadesJogador.get(i));
            } else {
                testarColissaoAbilidadeJogador(abilidadesJogador.get(i));
            }
        }

        for (int i = abilidadesMob.size() - 1; i >= 0; i--) {
            abilidadesMob.get(i).update(currentTick);
            if (abilidadesMob.get(i).active == false) {
                abilidadesMob.remove(abilidadesMob.get(i));
            } else {
                testarColissaoAbilidadeMob(abilidadesMob.get(i));
            }
        }
        this.detectorColisao.update(currentTick);
        rolagem.x = (int) jogador.pos.x - getWidth() / 2;
        rolagem.y = (int) jogador.pos.y - getHeight() / 2;

        //Ordena o arrayList apartir do pos.y
        Collections.sort(entidades);

    }

    @Override
    public void onRender(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.translate(-rolagem.x, -rolagem.y);

        //Render entidade simples "Chao do mapa"
        for (int i = entidades.size() - 1; i >= 0; i--) {
            if (entidades.get(i) instanceof EntidadeSimples) {
                entidades.get(i).render(g2);
            }
        }
        //render entidade Media "Arvores,casas,etc"
        for (int i = entidades.size() - 1; i >= 0; i--) {
            if (entidades.get(i) instanceof EntidadeMedia && ((EntidadeMedia) entidades.get(i)).entidadeTopo == false) {
                entidades.get(i).render(g2);
            }
        }
        //Render entidades eventos "jogador,mobs,etc"
        for (int i = entidades.size() - 1; i >= 0; i--) {
            if (entidades.get(i) instanceof EntidadeEvento) {
                entidades.get(i).render(g2);
            }
        }
        //render entidade media com atributo top == true
        for (int i = entidades.size() - 1; i >= 0; i--) {
            if (entidades.get(i) instanceof EntidadeMedia && ((EntidadeMedia) entidades.get(i)).entidadeTopo == true) {
                entidades.get(i).render(g2);
            }
            if (entidades.get(i) instanceof EntidadeEvento) {
                interfase.renderEntidade(g2, (EntidadeEvento) entidades.get(i));
            }
        }

        //render abilidades
        for (int i = abilidadesMob.size() - 1; i >= 0; i--) {
            abilidadesMob.get(i).render(g2);
        }
        for (int i = abilidadesJogador.size() - 1; i >= 0; i--) {
            abilidadesJogador.get(i).render(g2);
        }
        interfase.render(g);
    }

    public void testarColissaoAbilidadeMob(AbilidadeMapa a) {
        for (int i = entidades.size() - 1; i >= 0; i--) {
            if (a.pos.intersects(entidades.get(i).pos) && a.donoAbilidade != entidades.get(i) && !a.abilidadeMapaAtiva) {
                if (entidades.get(i) instanceof EntidadeJogador && a.donoAbilidade != entidades.get(i)) {
                    if (a.abilidadeMapaConstante) {
                        if (!a.colisoes.contains(entidades.get(i))) {
                            ControleJogador.getInstance().setDano(a.dano);
                            AbilidadeMapa interfacce = new AbilidadeInterface((int) ControleJogador.getInstance().jogador.pos.x, (int) ControleJogador.getInstance().jogador.pos.y, 0, ControleJogador.getInstance().jogador, a.dano);
                            abilidadesMob.add(interfacce);
                            a.colisoes.add(entidades.get(i));
                            a.terminar(entidades.get(i));
                        }
                    } else {
                        ControleJogador.getInstance().setDano(a.dano);
                        AbilidadeMapa interfacce = new AbilidadeInterface((int) ControleJogador.getInstance().jogador.pos.x, (int) ControleJogador.getInstance().jogador.pos.y, 0, ControleJogador.getInstance().jogador, a.dano);
                        abilidadesMob.add(interfacce);
                        a.terminar(entidades.get(i));
                        a.active = false;
                        break;
                    }
                } else if (entidades.get(i).blockEntidade) {
                    if (a.abilidadeMapaConstante) {
                        if (!a.colisoes.contains(entidades.get(i))) {
                            a.colisoes.add(entidades.get(i));
                            a.terminar(entidades.get(i));
                        }
                    } else {
                        a.active = false;
                        a.terminar(entidades.get(i));
                        break;
                    }
                }
            }
        }
    }

    public void testarColissaoAbilidadeJogador(AbilidadeMapa a) {
        for (int i = entidades.size() - 1; i >= 0; i--) {
            if (a.pos.intersects(entidades.get(i).pos) && a.donoAbilidade != entidades.get(i) && !a.abilidadeMapaAtiva) {
                if (entidades.get(i) instanceof EntidadeMob) {
                    if (a.abilidadeMapaConstante) {
                        if (!a.colisoes.contains(entidades.get(i))) {
                            ((EntidadeMob) entidades.get(i)).setDano(a.dano);
                            AbilidadeMapa interfacce = new AbilidadeInterface((int) entidades.get(i).pos.x, (int) entidades.get(i).pos.y, 0, (EntidadeMob) entidades.get(i), a.dano);
                            abilidadesJogador.add(interfacce);
                            a.colisoes.add(entidades.get(i));
                            a.terminar(entidades.get(i));
                        }
                    } else {
                        ((EntidadeMob) entidades.get(i)).setDano(a.dano);
                        AbilidadeMapa interfacce = new AbilidadeInterface((int) entidades.get(i).pos.x, (int) entidades.get(i).pos.y, 0, (EntidadeMob) entidades.get(i), a.dano);
                        abilidadesJogador.add(interfacce);
                        a.colisoes.add(entidades.get(i));
                        a.terminar(entidades.get(i));
                        a.active = false;
                        break;
                    }
                } else if (entidades.get(i).blockEntidade) {
                    if (!a.abilidadeMapaConstante) {
                        a.active = false;
                    }
                    a.terminar(entidades.get(i));
                    break;
                }
            }
        }
    }

    public void carregarMapa(String levelName) {
        try {
            DataManager dm = new DataManager(getClass().getResource("/Rpg/Data/Level/" + levelName).toURI());
            Jogo.entidades.clear();
            mundo.setSize(getWidth() + 10, getHeight() + 10);
            int qtdEntidades = 0;
            qtdEntidades = dm.read("EntidadesMapa", qtdEntidades);
            for (int i = 0; i < qtdEntidades; i++) {

                EntidadeSimples e = new EntidadeSimples(0, 0, 0);
                e.IDSprite = dm.read("EntidadeSimples." + i + ".IDSprite", (int) e.IDSprite);
                e.pos.x = dm.read("EntidadeSimples." + i + ".posX", (int) e.pos.x);
                e.pos.y = dm.read("EntidadeSimples." + i + ".posY", (int) e.pos.y);
                Jogo.entidades.add(e);
            }
        } catch (URISyntaxException | IOException e) {
            System.out.println("ERRO:" + e);
        }
        EntidadeMob mob = new EntidadeMob(2, 500, 150, 32, 32, 10);
        entidades.add(mob);
//        EntidadeMob mob2 = new EntidadeMob(2, 100, 600, 32, 32, 10);
//        entidades.add(mob2);
//        EntidadeMob mob3 = new EntidadeMob(2, 500, 200, 32, 32, 10);
//        entidades.add(mob3);
//        EntidadeMob mob4 = new EntidadeMob(2, 500, 600, 32, 32, 10);
//        entidades.add(mob4);
    }
}
