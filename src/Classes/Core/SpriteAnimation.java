/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rpg.Core;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Manolo
 */
public class SpriteAnimation {

    private int currentImageIndex;
    private int indexInc;
    private boolean loop;
    private ArrayList<BufferedImage> images;

    public SpriteAnimation() {
        this.currentImageIndex = 0;
        this.loop = false;
        this.indexInc = 1;
        this.images = new ArrayList<BufferedImage>();
    }

    public void setLoop(boolean value) {
        loop = value;
    }

    public boolean isLoop() {
        return this.loop;
    }

    public void addImage(BufferedImage img) {
        this.images.add(img);
    }

    public void update(int currentTick) {
        if (currentTick % 8 == 0) {
            this.currentImageIndex += this.indexInc;
            if (this.currentImageIndex == this.images.size() || this.currentImageIndex == -1) {
                if (this.loop) {
                    this.indexInc += -1;
                    this.currentImageIndex += this.indexInc;
                } else {
                    this.currentImageIndex = 0;
                }
            }
        }
    }

    public BufferedImage getImage() {
        return this.images.get(this.currentImageIndex);
    }
}
