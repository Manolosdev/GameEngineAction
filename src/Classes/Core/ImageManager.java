/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Core;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Manolo
 */
public class ImageManager {

    public HashMap<Integer, BufferedImage> listaImagensMapa;
    public HashMap<Integer, BufferedImage> listaImagensPersonagem;
    public HashMap<Integer, BufferedImage> listaImagensArma;
    public HashMap<Integer, BufferedImage> listaImagensAbilidade;
    private static ImageManager instance;

    private ImageManager() {
        this.listaImagensMapa = new HashMap<>();
        this.listaImagensPersonagem = new HashMap<>();
        this.listaImagensArma = new HashMap<>();
        this.listaImagensAbilidade = new HashMap<>();
        this.carregarSpriteMapa();
        this.carregarSpritePersonagem();
        this.carregarSpriteArma();
        this.carregarSpriteAbilidade();
    }

    private void carregarSpriteMapa() {
        for (int i = 1; i < 101; i++) {
            try {
                URL url = getClass().getResource("/Rpg/Data/Grafico/Mapa/" + i + ".png");
                BufferedImage img = ImageIO.read(url);
                listaImagensMapa.put(i, img);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao carregar Map de imagens de mapa." + i + "\nERRO: " + e);
            }
        }
    }

    private void carregarSpritePersonagem() {
        for (int i = 1; i < 3; i++) {
            try {
                URL url = getClass().getResource("/Rpg/Data/Grafico/Personagem/" + i + ".png");
                BufferedImage img = ImageIO.read(url);
                listaImagensPersonagem.put(i, img);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao carregar Map de imagens de personagem." + i + "\nERRO: " + e);
            }
        }
    }

    private void carregarSpriteArma() {
        for (int i = 1; i < 2; i++) {
            try {
                URL url = getClass().getResource("/Rpg/Data/Grafico/Armas/" + i + ".png");
                BufferedImage img = ImageIO.read(url);
                listaImagensArma.put(i, img);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao carregar Map de imagens de Arma." + i + "\nERRO: " + e);
            }
        }
    }
    
    private void carregarSpriteAbilidade() {
        for (int i = 1; i < 4; i++) {
            try {
                URL url = getClass().getResource("/Rpg/Data/Grafico/Abilidade/" + i + ".png");
                BufferedImage img = ImageIO.read(url);
                listaImagensAbilidade.put(i, img);
            } catch (Exception e) {
                throw new RuntimeException("Erro ao carregar Map de imagens de Abilidade." + i + "\nERRO: " + e);
            }
        }
    }

    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    //Map de sprite de mapa 32 x 32 px
    public BufferedImage getSpriteMapa(int ID) {
        if (ID < 0) {
            throw new RuntimeException("ID imagem < 0");
        } else {
            try {
                return this.listaImagensMapa.get(ID);
            } catch (Exception e) {
                throw new RuntimeException("ID de imagem de mapa não encontrado/nID: " + ID + "/nERRO:" + e);
            }
        }
    }

    public BufferedImage getSpritePersonagem(int ID) {
        if (ID < 0) {
            throw new RuntimeException("ID imagem < 0");
        } else {
            try {
                return this.listaImagensPersonagem.get(ID);
            } catch (Exception e) {
                throw new RuntimeException("ID de imagem de personagem não encontrado/nID: " + ID + "/nERRO:" + e);
            }
        }
    }

    public BufferedImage getImagePersonagem(int ID, int posIniX, int posIniY, int posFinX, int posFinY) {
        BufferedImage aux = this.getSpritePersonagem(ID);
        try {
            BufferedImage img = aux.getSubimage(posIniX, posIniY, posFinX, posFinY);
            return img;
        } catch (Exception e) {
            throw new RuntimeException("SubImagem de ID: " + ID + "/ERRO:" + e);
        }
    }

    public BufferedImage getSpriteArma(int ID) {
        if (ID < 0) {
            throw new RuntimeException("ID imagem < 0");
        } else {
            try {
                return this.listaImagensArma.get(ID);
            } catch (Exception e) {
                throw new RuntimeException("ID de imagem de arma não encontrado/nID: " + ID + "/nERRO:" + e);
            }
        }
    }

    public BufferedImage getImageArma(int ID, int posIniX, int posIniY, int posFinX, int posFinY) {
        BufferedImage aux = this.getSpriteArma(ID);
        try {
            BufferedImage img = aux.getSubimage(posIniX, posIniY, posFinX, posFinY);
            return img;
        } catch (Exception e) {
            throw new RuntimeException("SubImagem de ID: " + ID + "/ERRO:" + e);
        }
    }
    
    public BufferedImage getSpriteAbilidade(int ID) {
        if (ID < 0) {
            throw new RuntimeException("ID imagem < 0");
        } else {
            try {
                return this.listaImagensAbilidade.get(ID);
            } catch (Exception e) {
                throw new RuntimeException("ID de imagem de abilidade não encontrado/nID: " + ID + "/nERRO:" + e);
            }
        }
    }

    public BufferedImage getImageAbilidade(int ID, int posIniX, int posIniY, int posFinX, int posFinY) {
        BufferedImage aux = this.getSpriteAbilidade(ID);
        try {
            BufferedImage img = aux.getSubimage(posIniX, posIniY, posFinX, posFinY);
            return img;
        } catch (Exception e) {
            throw new RuntimeException("SubImagem de ID: " + ID + "/ERRO:" + e);
        }
    }
}
