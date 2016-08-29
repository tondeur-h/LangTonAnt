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
public class Rules {
   

    //test the value of the square where is my ant
    // change color and direction
    /**
     *
     * @param x
     * @param y
     * @param world
     * @param ant
     */
    public void evaluate(int x,int y,World world, Ant ant){
    // Rules are very basics
     //At a black square, turn 90° left, flip the color of the square, move forward one unit
    //At a white square, turn 90° right, flip the color of the square, move forward one unit
   if (world.location.get(World.XY2I(x, y)).isColorized()){
       //turnRIGHT();
      
       ant.setDirection(turnRIGHT(ant.getDirection())); 
       System.out.println("turn right=>direction="+ ant.getDirection()+" poxX="+ant.getPosX()+" posY="+ant.getPosY());
       //change color to none
       world.getLocation().set(World.XY2I(x, y), new Color(false,"NONE"));
       
   }
   else
   {
       //turnLEFT();
        
       ant.setDirection(turnLEFT(ant.getDirection()));
       System.out.println("turn left=>direction="+ ant.getDirection()+" poxX="+ant.getPosX()+" posY="+ant.getPosY());
       //change color to currentColor
        world.getLocation().set(World.XY2I(x, y), new Color(true,"RED"));
   }
    
    }
    
    
    /**
     *
     * @param direction
     * @return
     */
    public int turnRIGHT(int direction){
        if (direction<World.WEST){
            direction++;
        } else 
        {direction=World.NORTH;}
        
        return direction;
    }
    
    /**
     *
     * @param direction
     * @return
     */
    public int turnLEFT(int direction){
        if (direction>World.NORTH){
            direction--;
        } else 
        {direction=World.WEST;}
        
        return direction;
    }
    
}
