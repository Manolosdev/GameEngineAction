/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.EntidadeFuncionais;

import Rpg.Core.DataManager;
import Classes.EntidadeEvento.EntidadeJogador;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Seven
 */
public class ControleJogador {

    public static ControleJogador instance;
    //Instancia da classe
    public EntidadeJogador jogador;
    //Multiplicador de xp obtida
    private int xpMultiplicador = 1;
    //Experiencia necessaria para alcançar o primeiro nivel
    private int xpPrimeiroNivel = 100;
    //Denominador comum que garante o aumento de xp necessesaria a cada nivel obtido
    private float dificultadorNivel = 1.5f;
    //Dados do jogador
    private final int vidaInicial = 100;
    private final int manaInicial = 100;

    private DataManager dm;

    private ControleJogador() {
        try {
            dm = new DataManager(getClass().getResource("/Rpg/Data/DataBase/jogador").toURI());
            this.init();
        } catch (URISyntaxException | IOException e) {
            System.out.println("Erro ao abrir base de dados do Jogador.\n"
                    + "Erro: " + e);
        }
    }

    public static ControleJogador getInstance() {
        if (instance == null) {
            instance = new ControleJogador();
        }
        return instance;
    }

    public void init() {
        int aux = 0;
        jogador = new EntidadeJogador(1, dm.read("posX", aux), dm.read("posY", aux), 1);
        dm.write("vidaAtual", getVidaMaxima());
    }

    public void adicionarXp(int addXp) {
        int newXp = (ControleJogador.getInstance().getXpAtual() + addXp) * ControleJogador.getInstance().xpMultiplicador;
        while (newXp >= ControleJogador.getInstance().getProximoNivel()) {
            newXp -= ControleJogador.getInstance().getProximoNivel();
            ControleJogador.getInstance().adicionarNivel();
            dm.write("xpAtual", newXp);
        }
        dm.write("xpAtual", newXp);
        dm.save();
    }

    public void adicionarNivel() {
        int novoNivel = getNivelAtual() + 1;
        dm.write("nivelAtual", novoNivel);
        dm.write("vidaAtual", getVidaMaxima());
        dm.write("manaAtual", getManaMaxima());
        dm.save();
    }

    public void salvarProgresso() {
        dm.write("posX", (int) jogador.pos.x);
        dm.write("posY", (int) jogador.pos.y);
        dm.save();
    }

    public float getProximoNivel() {
        return ControleJogador.instance.xpPrimeiroNivel * (ControleJogador.getInstance().getNivelAtual() + 1)
                * ControleJogador.getInstance().dificultadorNivel;
    }

    public int getNivelAtual() {
        int aux = 1;
        return dm.read("nivelAtual", aux);
    }

    public String getNome() {
        String aux = "";
        return dm.read("nomeJogador", aux);
    }

    public int getXpAtual() {
        int aux = 1;
        return dm.read("xpAtual", aux);
    }

    public int getVidaMaxima() {
        int aux = vidaInicial + (getNivelAtual() * 20);
        return aux;
    }

    public int getManaMaxima() {
        int aux = manaInicial + (getNivelAtual() * 10);
        return aux;
    }

    public int getVidaAtual() {
        int aux = 1;
        aux = dm.read("vidaAtual", aux);
        if (aux > getVidaMaxima()) {
            aux = getVidaMaxima();
        }
        return aux;
    }

    public int getManaAtual() {
        int aux = 1;
        aux = dm.read("manaAtual", aux);
        if (aux > getManaMaxima()) {
            aux = getManaMaxima();
        }
        return aux;
    }

    public void setDano(int dano) {
        if (dano > 0) {
            if (getVidaAtual() - dano < 0) {
                dm.write("vidaAtual", 0);
            } else {
                dm.write("vidaAtual", getVidaAtual() - dano);
            }
        }
    }

}
