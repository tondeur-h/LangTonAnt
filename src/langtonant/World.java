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

import java.util.Vector;

/**
 *
 * @author herve
 */
public class World {
    
  //Directions values
    public static int NORTH=0;
    public static int EST=1;
    public static int SOUTH=2;
    public static int WEST=3;

    //caracteristics default why not!
    private static int sizeX=100;
    private static int sizeY=100;

    //the world is empty
    Vector<Color>location;
    
    
    /**
     * constructor : Et creavit Deus terrae!
     */
    public World() {
    //do nothing
    location=new Vector<Color>(sizeX*sizeY);
    init_world(sizeX,sizeY);
    }
    
    
    /**
     *
     * @param x
     * @param y
     */
    public void init_world(int x, int y){
        sizeX=x;
        sizeY=y;
        location.setSize(x*y);
         for (int pos=0;pos<x*y;pos++){
        location.add(pos, new Color(false, "none"));
    }
    }
    
    //read only World size

    /**
     *
     * @return
     */
    public static int getSizeX() {
        return sizeX;
    }

    /**
     *
     * @return
     */
    public static int getSizeY() {
        return sizeY;
    }
    
    /**
     *
     * @param x
     * @param y
     * @return
     */
    public static int XY2I(int x, int y){
        //convention first Line is 0 
        //and first column is 0
        return (y*sizeX)+x;
    }

    /**
     *
     * @param location
     */
    public void setLocation(Vector<Color> location) {
        this.location = location;
    }

    public Vector<Color> getLocation() {
        return location;
    }
    
    
    
}
