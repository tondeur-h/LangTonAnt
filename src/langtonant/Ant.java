/*
MIT License

Copyright (c) 2016 Tondeur Herve

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package langtonant;

/**
 *
 * @author herve
 */
public class Ant {

  
    
    //caracteristics
    int posX=0;
    int posY=0;
    int direction=World.NORTH;
    Rules rules;

    
    /**
     * constructor
     */
    public Ant() {
    //do nothing
    rules=new Rules();
        set_Ant_pos(posX, posY, direction);
    }
   
    /**
     *
     * @param x
     * @param y
     * @param dir
     */
    public void set_Ant_pos(int x, int y, int dir){
         posX=x;
        posY=y;
        direction=dir;
    }
    
    
    /******************************
     * rules for the move of an Ant
     ******************************/
    public void move(){
    //System.out.println("move direction="+direction+" posX="+posX+" posY="+posY);
        switch(direction){
      
            case 0:
        //if direction is NORTH
      //and x>0 then x--
      //if x=0  set x=Wolrd.sizeX-1
      if (posX>0)
      {
        posX--;
      } else
      {
       posX=World.getSizeX()-1;
      }
      break;
      
            case 2:
      //if direction is SOUTH
      //and x<World.sizeX-1 then x++
      //if x=World.sizeX  set x=0
      if (posX<World.getSizeX()-1)
      {
        posX++;
      } else
      {
       posX=0;
      }
      break;
      
            case 1:
      //if direction is WEST
      //and y>0 then y--
      //if y=0  set y=Wolrd.sizeY-1
      if (posY>0)
      {
        posY--;
      } else
      {
       posY=World.getSizeY()-1;
      }
      break;
      
            case 3:
      //if direction is EST
      //and y<World.sizeY-1 then y++
      //if y=World.sizeY  set y=0
      if (posY<World.getSizeY()-1)
      {
        posY++;
      } else
      {
       posY=0;
      }
      break;
      //must not have default case!
      
    } //fin switch
        
    }
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    
    
    
}
